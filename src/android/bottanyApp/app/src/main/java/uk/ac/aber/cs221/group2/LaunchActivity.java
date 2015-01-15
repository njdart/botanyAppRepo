package uk.ac.aber.cs221.group2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LaunchActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if (savedInstanceState == null) {
            //Add fragments here
        }
    }

    public void newVisitOnClick(View buttonView){
        boolean errors = false;
        EditText name = (EditText) findViewById(R.id.editName);
        EditText phone = (EditText) findViewById(R.id.editPhoneNumber);
        EditText email = (EditText) findViewById(R.id.editEmail);
        EditText locDescription = (EditText) findViewById(R.id.editText);
        System.out.println("NAME: " + name.getText() + " PHONE: " + phone.getText() + " EMAIL: " + email.getText() + " SITE_DESC: " + locDescription);

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
            if(!matcher.matches()){
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
            Intent intent = new Intent(this, SpeciesAdder.class);
            intent.putExtra("NAME", name.getText());
            intent.putExtra("EMAIL", email.getText());
            intent.putExtra("PHONE", phone.getText());
            intent.putExtra("LOC", locDescription.getText());
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
}
