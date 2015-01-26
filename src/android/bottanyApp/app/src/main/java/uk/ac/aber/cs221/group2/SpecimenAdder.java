package uk.ac.aber.cs221.group2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.ac.aber.cs221.group2.dataClasses.Specimen;
import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.dataClasses.Visit;
import uk.ac.aber.cs221.group2.utils.IntentRequestCodes;
import uk.ac.aber.cs221.group2.utils.PlantDataSource;
import uk.ac.aber.cs221.group2.utils.SiteDataSource;
import uk.ac.aber.cs221.group2.utils.SpecimenDataSource;
import uk.ac.aber.cs221.group2.utils.UserDataSource;

public class SpecimenAdder extends BaseActivity {

    public static List<String> latinNames;
    public AutoCompleteTextView latinNamesAutoComplete;
    private SpecimenDataSource specimenDataSource;
    private UserDataSource userDataSource;
    private SiteDataSource siteDataSource;
    private Double latitude = 0D,
                   longitude = 0D;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specimen_adder);

        //Get the dataSource for the specimens
        specimenDataSource = new SpecimenDataSource(this);
        userDataSource = new UserDataSource(this);
        siteDataSource = new SiteDataSource(this);

        //Screen Rotaion will reset some aspects of the app and call onCreate again
        //if so, we need to reset the images for the buttons
        if(sceneThumbnail != null){
            ((ImageButton)findViewById(R.id.sceneView)).setImageBitmap(sceneThumbnail);
        }

        if(specimenThumbnail != null){
            ((ImageButton)findViewById(R.id.specimenView)).setImageBitmap(specimenThumbnail);
        }

        //get the latin names autcomplete box and add a watcher for sql queries
        latinNamesAutoComplete = (AutoCompleteTextView)(findViewById(R.id.latinNameAutoComplete));
        latinNamesAutoComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(s.length()>3){
                    PlantDataSource p = new PlantDataSource(SpecimenAdder.this);
                    p.open();
                    latinNames = p.findMatches(s.toString());
                    System.out.println(latinNames.size());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SpecimenAdder.this,
                            android.R.layout.simple_dropdown_item_1line, latinNames);
                    latinNamesAutoComplete.setAdapter(adapter);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
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
        String filename = "botanyApp_" + new SimpleDateFormat("yyyyMMdd-HHmmSS").format(new Date()) + ".jpg";
        File f = new File(android.os.Environment.getExternalStorageDirectory(), filename);
        System.out.println("FILEPATH: " + f.getAbsolutePath() + " FILENAME: " + filename);
        i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        switch (intentReturnId){
            case IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO:
                scenePhoto = f;
                break;
            case IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO:
                specimenPhoto = f;
                break;
            default:
                System.out.println("ERROR, Unknown intent request code, ignoring it");
                return;
        }
        startActivityForResult(i, intentReturnId);
    }

    private static File scenePhoto;
    private static File specimenPhoto;
    private static Bitmap sceneThumbnail;
    private static Bitmap specimenThumbnail;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ImageButton imageButton;
            File f;

            switch(requestCode) {
                case IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO:
                    imageButton = (ImageButton) findViewById(R.id.sceneView);
                    f = scenePhoto;
                    break;
                case IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO:
                    imageButton = (ImageButton) findViewById(R.id.specimenView);
                    f = specimenPhoto;
                    break;
                default:
                    System.out.println("ERROR, unknown request code, ignoring!" + requestCode);
                    return;
            }

            BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                    btmapOptions);

            bitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, true);

            switch(requestCode) {
                case IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO:
                    sceneThumbnail = bitmap;
                    break;
                case IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO:
                    specimenThumbnail = bitmap;
                    break;
                default:
                    return;
            }

            imageButton.setImageBitmap(bitmap);
        }
    }

    public void onNewSpecimenButtonClick (View view){
        //save everything to the database and reset fields for a new specimen

        User user = User.CurrentUser;
        Visit visit = Visit.CurrentVisit;

        //Sanity Check

        if(user == null){
            throw new NullPointerException("User was Null, expecting something else!");
        }

        if(visit == null){
            throw new NullPointerException("Visit was Null, expecting something different");
        }

        //else we're good to go sync with the database!
        EditText latinNameAutoComplete = (EditText)findViewById(R.id.latinNameAutoComplete);
        String latinName = latinNameAutoComplete.getText().toString();

        //do some error checking
        if(latinName == null || latinName.length() < 3){
            latinNameAutoComplete.setError("The latin name should be more than 3 characters!");
            return;
        } else {
            latinNameAutoComplete.setError(null);
        }


        String comment = ""; //TODO add comment box
        Specimen.AbundanceEnum abundanceEnum = Specimen.AbundanceEnum.values()
                [((SeekBar)findViewById(R.id.abundanceSlider)).getProgress()];

        Specimen specimen = new Specimen(latinName,
                                         latitude,
                                         longitude,
                                         abundanceEnum,
                                         comment,
                                         (scenePhoto == null)?"":scenePhoto.getAbsolutePath(),
                                         (specimenPhoto == null)?"":specimenPhoto.getAbsolutePath(),
                                         Integer.parseInt(userDataSource.FindByNamegetIndex(user.getName())),
                                         Integer.parseInt(siteDataSource.FindByNameGetIndex(visit.getVisitName())));

        specimenDataSource.create(specimen, user, visit);


        //Now clear the input fields
        ((SeekBar)findViewById(R.id.abundanceSlider)).setProgress(2);
        ((EditText) findViewById(R.id.latinNameAutoComplete)).setText("");
        ((ImageButton)findViewById(R.id.sceneView)).setImageResource(R.drawable.specimen);
        ((ImageButton)findViewById(R.id.specimenView)).setImageResource(R.drawable.specimen);
        Toast.makeText(this, "New Specimein '" + latinName + " added!", Toast.LENGTH_LONG).show();


        for(Specimen s : specimenDataSource.findAll()){
            System.out.println("Specimen:" + s.getName() + " (" + s.getLatitude() + "," + s.getLongitude() +")" + s.getAbundance() +
                               " Comment " + s.getComment() + " scene " + s.getScenePhotoURI() + " specimen: " + s.getSpecimenPhotoURI());
        }

    }

    public final String serverURLString = "http://users.aber.ac.uk/mta2/groupapi/addResource.php";
    //public final String serverURLString = "http://192.168.1.74/testing/index.php";


    //http://stackoverflow.com/questions/23921356/android-upload-image-to-php-server
    public void onUploadButtonClick(View view){
        onNewSpecimenButtonClick(view);
        startActivity(new Intent(this, ReviewActivity.class));
    }
}
