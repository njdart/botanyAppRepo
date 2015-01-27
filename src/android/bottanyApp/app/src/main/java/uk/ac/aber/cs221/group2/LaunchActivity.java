package uk.ac.aber.cs221.group2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.dataClasses.Visit;
import uk.ac.aber.cs221.group2.utils.PlantDataSource;
import uk.ac.aber.cs221.group2.utils.SiteDataSource;
import uk.ac.aber.cs221.group2.utils.UserDataSource;

public class LaunchActivity extends BaseActivity  {
    AutoCompleteTextView textView;
    AutoCompleteTextView reservesView;

    UserDataSource userDataSource;
    SiteDataSource siteDataSource;
    PlantDataSource plantDataSource;

    public static List<String> autoCompleteNames;
    public static List<String> autoCompleteReserves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        textView = (AutoCompleteTextView)findViewById(R.id.editNameAutoComplete);
        reservesView = (AutoCompleteTextView)findViewById(R.id.autoCompleteReserveNames);

        //Create and open connections to the databases
        userDataSource = new UserDataSource(this);
        siteDataSource = new SiteDataSource(this);
        plantDataSource = new PlantDataSource(this);
        userDataSource.open();
        siteDataSource.open();
        plantDataSource.open();

        //Run the background thread
        InitThread i = new InitThread(plantDataSource, this);
        i.execute();

        //Autocomplete users details. No need to check for validity, entries should already
        // exist in the autocomplete and therefore the database
        updateAutocompleteAdapters();
        textView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompleteTextView temp = textView;

                User user = userDataSource.FindByName(temp.getText().toString());
                EditText phone = (EditText) findViewById(R.id.editPhoneNumber);
                EditText email = (EditText) findViewById(R.id.editEmail);
                phone.setText(user.getUserPhoneNumber());
                email.setText(user.getUserEmail());
            }
        });
    }

    public void updateAutocompleteAdapters(){
        //find all the existing resources for autocompleting names and reserves
        userDataSource.open();
        siteDataSource.open();
        autoCompleteNames = userDataSource.findAllNames();
        autoCompleteReserves = siteDataSource.findAllNames();

        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteNames);
        textView.setAdapter(namesAdapter);

        ArrayAdapter<String> reservesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autoCompleteReserves);
        reservesView.setAdapter(reservesAdapter);
    }

    public void onNewVisitClick(View buttonView){
        boolean errors = false;
        EditText name = (EditText) findViewById(R.id.editNameAutoComplete);
        EditText phone = (EditText) findViewById(R.id.editPhoneNumber);
        EditText email = (EditText) findViewById(R.id.editEmail);
        AutoCompleteTextView reserveName = (AutoCompleteTextView) findViewById(R.id.autoCompleteReserveNames);

        //Validate the name field
        if(name.length() < 3 || name.length() > 25){
            name.setError(name.getHint() + " Should be between 3 and 10 characters");
            errors = true;
        } else {
            //remove the error message
            name.setError(null);
        }

        //validate the phone field
        if(phone.length() < 5 || phone.length() > 20){
            phone.setError(phone.getHint() + " Should be more than 5 digits");
            errors = true;
        } else {
            phone.setError(null);
        }

        //validate the email field
        if(email.length() < 5 || email.length() > 40) {
            email.setError(email.getHint() + " Should be between 5 and 20 characters");
            errors = true;
        } else {
            //More rigorous validation
            Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9._%+-]+@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,4}$"); //regex taken from http://www.regular-expressions.info/email.html
            Matcher matcher = emailRegex.matcher(email.getText());
            if(!matcher.matches()) {
                email.setError("This doesn't look like an email address");
                errors = true;
            } else {
                email.setError(null);
            }
        }

        if(reserveName.getText().toString().length() <= 0){
            errors = true;
            reserveName.setError("That does not look like a reserve name");
        } else {
            boolean found = false;
            for(String r : siteDataSource.findAllNames()){
                if(r.toLowerCase().toLowerCase().equals(reserveName.getText().toString().toLowerCase())) {
                    found = true;
                    break;
                }
            }
            if(!found){
                errors = true;
                reserveName.setError("No reserve found by that name");
            } else {
                reserveName.setError(null);
            }
        }


        if(!errors) {
            //we're good to move on!
            User user = new User(name.getText().toString(),phone.getText().toString(),email.getText().toString());
            userDataSource = new UserDataSource(this);

            if(userDataSource.FindByName(user.getName())==null){
                user.setId((int)userDataSource.create(user));
                System.out.println("Inserting a user with id " + user.getUserId());
            }

            //and save the current user for this session
            User.CurrentUser = userDataSource.FindByName(user.getName());
            Visit.CurrentVisit = siteDataSource.findByName(reserveName.getText().toString());
            Intent intent = new Intent(this, SpecimenAdder.class);
            startActivity(intent);
        }
    }

    public class InitThread extends AsyncTask<String, Void, String> {

        PlantDataSource plantDataSource;
        Context context;

        public InitThread(PlantDataSource plantDataSource, Context context){
            this.plantDataSource = plantDataSource;
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {

                System.out.println("===Init thread starting===");
                //////////////////////////////////////
                // update the reserves list
                //////////////////////////////////////

                System.out.println("INIT: Updating reserve list");

                URL url = new URL("http://users.aber.ac.uk/mta2/groupapi/getReserves.php");
                // Read all the text returned by the server
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String str;
                StringBuilder stringBuilder = new StringBuilder();
                while ((str = in.readLine()) != null)
                {
                    stringBuilder.append(str);
                }
                in.close();

                SiteDataSource sitedb = new SiteDataSource(LaunchActivity.this);
                sitedb.open();
                JSONArray a = new JSONArray(stringBuilder.toString());
                System.out.println("Downloaded string: " + a.toString());

                for(int i = 0; i<a.length();i++){
                    Visit v = new Visit(a.getJSONObject(i).getString("LocationName"),
                                        a.getJSONObject(i).getString("LocationOS"));
                    v.setDescription(a.getJSONObject(i).getString("Description"));
                    v.setId(Integer.parseInt(a.getJSONObject(i).getString("ReserveID")));
                    autoCompleteNames.add(v.getVisitName());
                    sitedb.create(v);
                    System.out.println("added reserve " + v.getVisitName());
                }
                System.out.println("INIT: Reserve Updated Successfully");


                ///////////////////////////////////////
                //Update the latin names plant database
                ///////////////////////////////////////
                System.out.println("INIT: Updating Latin Names database");
                long rows = plantDataSource.getRows();
                if(rows > 1l) {
                    System.out.println("A table exists by the name of plants and it has data, skipping db upgrade!");
                } else {
                    long startTime = System.currentTimeMillis();
                    DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                    HttpPost httppost = new HttpPost("http://nic-dart.co.uk/~nic/res/plantlist.json");
                    // Depends on your web service
                    httppost.setHeader("Content-type", "application/json");

                    InputStream inputStream = null;
                    String result = null;
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                    long timeToDownload = System.currentTimeMillis() - startTime;
                    startTime = System.currentTimeMillis();

                    //Dont bother saving anything of the decoded json array, just do the transaction really fast!
                    plantDataSource.jsonTransaction(new JSONObject(result).getJSONArray("plantList"));

                    long timeToProcess = System.currentTimeMillis() - startTime;
                    System.out.println(String.format("DB done processing in %2.2f seconds (%2.2f for the download)", (float) timeToProcess / 1000, (float) timeToDownload / 1000));

                    System.out.println("INIT: Latin Names Database Updated Successfully");
                }

                System.out.println("===Init thread completed successfully===");

            } catch (JSONException e){
                System.out.println("Error, bad json?");
                e.printStackTrace();
                //TODO nice errors
            } catch (MalformedURLException ex){
                System.out.println("Unable to download Latin Names Resource?");
                ex.printStackTrace();
                //TODO some nice error for the user
            } catch (IOException exc){
                System.out.println("OH SHIT! some IO exception occurred");
                exc.printStackTrace();
                //TODO more nice errors
            } finally {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("Processing post init thread adapters");
            updateAutocompleteAdapters();
        }
    }
}
