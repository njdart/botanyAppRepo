package uk.ac.aber.cs221.group2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

import uk.ac.aber.cs221.group2.utils.PlantDataSource;


public class SpeciesAdder extends BaseActivity {

    public static List<String> latinNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_adder);
        if (savedInstanceState == null) {}

        AutoCompleteTextView latinNamesAutoComplete = (AutoCompleteTextView)(findViewById(R.id.latinNameAutoComplete));

        latinNamesAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        
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
