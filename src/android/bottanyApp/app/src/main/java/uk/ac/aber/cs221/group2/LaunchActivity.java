package uk.ac.aber.cs221.group2;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.ac.aber.cs221.group2.utils.PlantDataSource;

public class LaunchActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if (savedInstanceState == null) {
            //Add fragments here
        }
        new PlantDataSource(this).open().create("FooBar");
        //Run the background thread
        //InitThread i = new InitThread(new PlantDataSource(this).open());
        //i.run();
    }

    public void newVisitOnClick(View buttonView){
        boolean errors = false;
        EditText name = (EditText) findViewById(R.id.editName);
        EditText phone = (EditText) findViewById(R.id.editPhoneNumber);
        EditText email = (EditText) findViewById(R.id.editEmail);
        System.out.println("NAME: " + name.getText() + " PHONE: " + phone.getText() + " EMAIL: " + email.getText());

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
            Intent intent = new Intent(this, SiteChooser.class);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            System.out.println("Settings Button Clicked!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class InitThread implements Runnable {

        PlantDataSource plantDataSource;

        public InitThread(PlantDataSource plantDataSource){
            this.plantDataSource = plantDataSource;
        }
        @Override
        public void run() {
            try {
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

                JSONObject jObject = new JSONObject(result);
                //System.out.println("JSON object: " + jObject.toString());
                JSONArray jsonArray = jObject.getJSONArray("plantList");

                //for(int i = 0; i < jsonArray.length(); i++){
                //    String name = jsonArray.getString(i);
                //    System.out.println(name);
                //    plantDataSource.create(name);
                //}

                plantDataSource.close();


            } catch (JSONException e){
                System.out.println("Error, bad json?");
                e.printStackTrace();
                //TODO nice errors
            } catch (MalformedURLException ex){
                System.out.println("Unable to download Latin Names Resource");
                ex.printStackTrace();
                //TODO some nice error for the user
            } catch (IOException exc){
                System.out.println("OH SHIT! some IO exception occured");
                exc.printStackTrace();
                //TODO more nice errors
            }
        }
    }
}
