package uk.ac.aber.cs221.group2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import java.util.List;

import uk.ac.aber.cs221.group2.utils.IntentRequestCodes;
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

        latinNamesAutoComplete = (AutoCompleteTextView)(findViewById(R.id.latinNameAutoComplete));

        latinNamesAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()>3){
                    PlantDataSource p = new PlantDataSource(SpeciesAdder.this);
                    p.open();
                    System.out.println("hit");
                    latinNames = p.findMatches(s.toString());
                    System.out.println(latinNames.size());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpeciesAdder.this,
                            android.R.layout.simple_dropdown_item_1line, latinNames);

                    latinNamesAutoComplete.setAdapter(adapter);

                }
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
        takePhoto(IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO);
    }

    public void onScenePhotoClick(View view){
        System.out.println("Taking a scene photo");
        takePhoto(IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO);
    }

    private void takePhoto(int intentReturnId){
        Intent i = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivityForResult(i, intentReturnId);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap bitmapThumbnail = (Bitmap) extras.get("data");
            ImageView imageView;

            if(requestCode == IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO){
                imageView = (ImageView)findViewById(R.id.sceneView);
            } else if(requestCode == IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO){
                imageView = (ImageView)findViewById(R.id.specimenView);
            } else {
                System.out.println("SPECIES ADDER: Got an unknown result code back, ignoring it!");
                return;
            }
            imageView.setImageBitmap(bitmapThumbnail);
        } else {
            System.out.println("ERROR! Something happened!");
        }
    }
}
