package uk.ac.aber.cs221.group2;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aber.cs221.group2.utils.IntentRequestCodes;
import uk.ac.aber.cs221.group2.utils.PlantDataSource;


public class SpeciesAdder extends BaseActivity {

    public static List<String> latinNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_adder);
        if (savedInstanceState == null) {}

        AutoCompleteTextView latinNamesAutoComplete = (AutoCompleteTextView)(findViewById(R.id.latinNameAutoComplete));
        PlantDataSource plantDataSource = new PlantDataSource(this);
        plantDataSource.open();
        latinNames = plantDataSource.findAll();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, latinNames);
        latinNamesAutoComplete.setAdapter(adapter);
    }

    public void onSpecimenPhotoClick(View view){
        System.out.println("Taking a specimen photo");
    }

    public void onScenePhotoClick(View view){
        System.out.println("Taking a scene photo");
    }

}
