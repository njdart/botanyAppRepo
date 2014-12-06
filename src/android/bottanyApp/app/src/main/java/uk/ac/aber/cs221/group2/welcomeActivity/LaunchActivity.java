package uk.ac.aber.cs221.group2.welcomeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import uk.ac.aber.cs221.group2.R;
import uk.ac.aber.cs221.group2.SpeciesAdder;

public class LaunchActivity extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        if (savedInstanceState == null) {
            //Add fragments here
        }

        if(savedInstanceState != null){
            Button gpsButton = new Button(this);
            gpsButton.setText("USE GPS");
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class NewVisitActionListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            int editNameLength = 10; // = ((Button)v.findViewById(R.id.editName)).getText().length();
            if(editNameLength > 3){
                setIntent(new Intent(LaunchActivity.this, SpeciesAdder.class));
            }
        }
    }
}
