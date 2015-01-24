package uk.ac.aber.cs221.group2;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.dataClasses.Visit;
import uk.ac.aber.cs221.group2.utils.PlantDataSource;
import uk.ac.aber.cs221.group2.utils.SiteDataSource;
import uk.ac.aber.cs221.group2.utils.UserDataSource;

public class LaunchActivity extends BaseActivity  {

    UserDataSource userdb;
    public static List<String> autoCompleteEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        userdb = new UserDataSource(this);
        userdb.open();
        if (savedInstanceState == null) {
            //Add fragments here
        }

        //Run the background thread
        InitThread i = new InitThread(new PlantDataSource(this).open(), this);
        i.execute();


        autoCompleteEntries = userdb.findAllNames();
        System.out.println(autoCompleteEntries.size());
        if(autoCompleteEntries.size()>0){
            System.out.println(autoCompleteEntries.size());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autoCompleteEntries) ;
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.editNameAutoComplete);
        textView.setAdapter(adapter);
        }

    }

    public void newVisitOnClick(View buttonView){
        boolean errors = false;
        EditText name = (EditText) findViewById(R.id.editNameAutoComplete);
        EditText phone = (EditText) findViewById(R.id.editPhoneNumber);
        EditText email = (EditText) findViewById(R.id.editEmail);
        System.out.println("NAME: " + name.getText() + " PHONE: " + phone.getText() + " EMAIL: " + email.getText());
        User user = new User(name.getText().toString(),phone.getText().toString(),email.getText().toString());
        UserDataSource userdb = new UserDataSource(this);
        userdb.create(user);

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

        if(errors){
            return;
        }
        else {
            //we're good to move on!
            Intent intent = new Intent(this, SiteChooserActivity.class);
            startActivity(intent);
        }
    }

    public class InitThread extends AsyncTask {

        PlantDataSource plantDataSource;
        Context context;

        public InitThread(PlantDataSource plantDataSource, Context context){
            this.plantDataSource = plantDataSource;
            this.context = context;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {

                long rows = plantDataSource.getRows();
                System.out.println("ROWS: " + rows);
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

                    plantDataSource.close();
                    long timeToProcess = System.currentTimeMillis() - startTime;
                    System.out.println(String.format("DB done processing in %2.2f seconds (%2.2f for the download)", (float) timeToProcess / 1000, (float) timeToDownload / 1000));
                    Toast.makeText(context, "Plant Database is now up to date!", Toast.LENGTH_LONG).show();
                }

                //////////////////////////////

                DefaultHttpClient httpclient2 = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost2 = new HttpPost("http://users.aber.ac.uk/mta2/groupapi/getLocations.php");
                // Depends on your web service
                httppost2.setHeader("Content-type", "application/json");

                InputStream inputStream2 = null;
                String result2 = null;
                HttpResponse response2 = httpclient2.execute(httppost2);
                HttpEntity entity2 = response2.getEntity();

                inputStream2 = entity2.getContent();
                // json is UTF-8 by default
                BufferedReader reader2 = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"), 8);
                StringBuilder sb2 = new StringBuilder();

                String line2 = null;
                while ((line2 = reader2.readLine()) != null) {
                    sb2.append(line2 + "\n");
                }
                result2 = sb2.toString();

                SiteDataSource sitedb = new SiteDataSource(LaunchActivity.this);
                sitedb.open();
                JSONArray a = new JSONArray(result2);
                //ArrayList<Visit> locations = new ArrayList<Visit>();
                for(int i = 0; i<a.length();i++){
                    Visit v = new Visit(a.getJSONObject(i).getString("LocationName"),a.getJSONObject(i).getString("LocationOS"));
                    sitedb.create(v);
                    System.out.print("added" +v.getVisitName()+ " "+v.getVisitOS());
                }
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
    }
}
