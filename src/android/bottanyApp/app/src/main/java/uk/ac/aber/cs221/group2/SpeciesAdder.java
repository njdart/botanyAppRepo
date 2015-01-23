package uk.ac.aber.cs221.group2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //set the filename to the date and time now
        filename = "botanyApp_" + new SimpleDateFormat("yyyyMMdd-HHmmSS").format(new Date()) + ".jpg";
        System.out.println("Saving image as " + filename);
        File f = new File(android.os.Environment.getExternalStorageDirectory(), filename);
        System.out.println("FILEPATH: " + f.getAbsolutePath());
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        //i.putExtra("Filename", fileName);
        startActivityForResult(i, intentReturnId);
    }

    String filename;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            File f = new File(Environment.getExternalStorageDirectory(), filename);
            System.out.println("FILEPATH: " + f.getAbsolutePath());

            BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                    btmapOptions);

            bitmap = Bitmap.createScaledBitmap(bitmap, 256, 256, true);
            ImageButton imageButton;

            if (requestCode == IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO) {
                imageButton = (ImageButton) findViewById(R.id.sceneView);
            } else if (requestCode == IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO) {
                imageButton = (ImageButton) findViewById(R.id.specimenView);
            } else {
                System.out.println("SPECIES ADDER: Got an unknown result code back, ignoring it!");
                return;
            }

            imageButton.setImageBitmap(bitmap);
        }
    }
}
