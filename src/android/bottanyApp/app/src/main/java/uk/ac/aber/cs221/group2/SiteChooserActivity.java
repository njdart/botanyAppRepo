package uk.ac.aber.cs221.group2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uk.ac.aber.cs221.group2.OScode.LatLng;
import uk.ac.aber.cs221.group2.OScode.OSRef;
import uk.ac.aber.cs221.group2.dataClasses.Visit;
import uk.ac.aber.cs221.group2.utils.SiteDataSource;


public class SiteChooserActivity extends BaseActivity {

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
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.siteNameAutoComplete);
        textView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3600000, 1000, onLocationChange);
    }

    public void onUseSiteClick(View view){
        final String siteName = ((AutoCompleteTextView)findViewById(R.id.siteNameAutoComplete))
                .getText().toString();
        String siteNameLower = siteName.toLowerCase();

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
                        siteDataSource.create(new Visit(siteName, "FOOBAR"));

                        //TODO site ref is NOT "foobar", but fuck it...

                        goToSpeciesAdderActivity();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }

    private void goToSpeciesAdderActivity() {
        Intent i = new Intent(this, SpeciesAdder.class);
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
            System.out.println("Converted to OS Grid Ref: " + os2.toString() + " - "
                    + os2.toSixFigureString());

            //System.out.println("LAT: " + location.getLatitude() + " LNG: " + location.getLongitude());
            //Toast.makeText(SiteChooserActivity.this, "LAT: " + location.getLatitude() + " LNG: " + location.getLongitude(), Toast.LENGTH_LONG).show();
            TextView gridRef = (TextView)findViewById(R.id.customGridReferenceEditText);
            if(!useCustomGridRef) {
                gridRef.setText(location.getLatitude() + "\n" + location.getLongitude());
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            System.out.println("STATUS: " + provider.toString() + ", " + status);
            Toast.makeText(SiteChooserActivity.this, "Status " + status, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            System.out.println("Provider Enables");
        }

        @Override
        public void onProviderDisabled(String provider) {
            System.out.println("Provider Disabled");
        }
    };
}
