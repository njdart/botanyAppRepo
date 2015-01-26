package uk.ac.aber.cs221.group2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uk.ac.aber.cs221.group2.OScode.LatLng;
import uk.ac.aber.cs221.group2.OScode.OSRef;
import uk.ac.aber.cs221.group2.dataClasses.Visit;
import uk.ac.aber.cs221.group2.utils.SiteDataSource;


public class SiteChooserActivity extends BaseActivity {
    AutoCompleteTextView textView;
    private LocationManager locationManager = null;
    boolean useCustomGridRef = false;
    public static List<String> autoCompleteEntries;
    SiteDataSource siteDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_chooser);
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        siteDataSource = new SiteDataSource(this);

        siteDataSource.open();

        autoCompleteEntries = siteDataSource.findAll();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, autoCompleteEntries);
        textView = (AutoCompleteTextView)
                findViewById(R.id.siteNameAutoComplete);
        textView.setAdapter(adapter);
        textView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AutoCompleteTextView temp = textView;
                System.out.println(temp.getText());

                EditText os = (EditText)findViewById(R.id.customGridReferenceEditText);

                os.setText(siteDataSource.FindByName(temp.getText().toString()));

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600000, 1000, onLocationChange);
    }

    public void onUseSiteClick(View view){
        AutoCompleteTextView siteNameAutoComplete = ((AutoCompleteTextView)findViewById(R.id.siteNameAutoComplete));
        TextView gridRefTextView = ((TextView)findViewById(R.id.customGridReferenceEditText));
        final String siteName = siteNameAutoComplete.getText().toString();
        final String gridRef = gridRefTextView.getText().toString();
        String siteNameLower = siteName.toLowerCase();
        boolean foundErrors = false;

        if(siteName.length() < 3 || siteName.length() > 20){
            foundErrors = true;
            siteNameAutoComplete.setError("The site name should be between 3 and 20 characters");
        } else {
            siteNameAutoComplete.setError(null);
        }

        if(gridRef.length() != 8) {
            foundErrors = true;
            gridRefTextView.setError("Expected a 6 fig grid ref without spaces");
        } else {
            gridRefTextView.setError(null);
        }

        //If errors are found, abort the transaction!
        if(foundErrors) return;

        //check if the site is already in the list
        for(String s : autoCompleteEntries){
            if(s.toLowerCase().equals(siteNameLower)){
                goToSpeciesAdderActivity();
                return;
            }
        }

        //if we get to here, it isn't in the list, alert the user and if
        //it's a genuine new site, add it
        new AlertDialog.Builder(this)
                .setTitle("Create Site")
                .setMessage("No site was previously found by this name, Do you want to create this site")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        siteDataSource.create(new Visit(siteName, gridRef));
                        goToSpeciesAdderActivity();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    private void goToSpeciesAdderActivity() {
        Intent i = new Intent(this, SpecimenAdder.class);
        startActivity(i);
    }

    public void onClickUseCustomGridRef(View view){
        CheckBox checkBox = (CheckBox)(view);
        TextView customGridRefEdit = (TextView)(findViewById(R.id.customGridReferenceEditText));
        useCustomGridRef = checkBox.isChecked();
        customGridRefEdit.setEnabled(useCustomGridRef);
    }

    LocationListener onLocationChange = new LocationListener(){

        @Override
        public void onLocationChanged(Location location) {

            LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
            OSRef os2 = loc.toOSRef();
            TextView gridRef = (TextView)findViewById(R.id.customGridReferenceEditText);
            if(!useCustomGridRef) {
                //gridRef.setText(location.getLatitude() + "\n" + location.getLongitude());
                gridRef.setText(os2.toSixFigureString());
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("STATUS: " + provider.toString() + ", " + status);
            if(status == LocationProvider.OUT_OF_SERVICE) {
                new AlertDialog.Builder(SiteChooserActivity.this)
                        .setTitle("GPS Required")
                        .setMessage("GPS is required for automatic Grid Reference Detection. Would you like to open GPS settigns?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                Intent i = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(i);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            } else if(status == LocationProvider.TEMPORARILY_UNAVAILABLE) {
                Toast.makeText(SiteChooserActivity.this, "GPS temporally unavailable", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("Provider Enables");
        }

        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("Provider Disabled");
            Toast.makeText(SiteChooserActivity.this, "GPS is required for automatic OS grid referencing", Toast.LENGTH_LONG).show();
        }
    };
}
