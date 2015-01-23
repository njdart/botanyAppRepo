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
    public AutoCompleteTextView latinNamesAutoComplete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        latinNamesAutoComplete = (AutoCompleteTextView)(findViewById(R.id.latinNameAutoComplete));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_adder);
        if (savedInstanceState == null) {}


        latinNamesAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>4){
                    PlantDataSource p = new PlantDataSource(SpeciesAdder.this);
                    p.open();
                    latinNames = p.findMatches(s.toString());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpeciesAdder.this,
                            android.R.layout.simple_dropdown_item_1line, latinNames);

                    latinNamesAutoComplete.setAdapter(adapter);

                }
            }
        });
        

    }

    public void onSpecimenPhotoClick(View view){
        System.out.println("Taking a specimen photo");

    }

    public void onScenePhotoClick(View view){
        System.out.println("Taking a scene photo");
    }

}
