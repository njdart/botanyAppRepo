package uk.ac.aber.cs221.group2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        String filename = "botanyApp_" + new SimpleDateFormat("yyyyMMdd-HHmmSS").format(new Date()) + ".jpg";
        System.out.println("Saving image as " + filename);
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
                System.out.println("ERROR, Unknown intent request code");
        }
        startActivityForResult(i, intentReturnId);
    }

    File scenePhoto;
    File specimenPhoto;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            ImageButton imageButton;
            File f;

            if (requestCode == IntentRequestCodes.SPECIES_ADDER_SCENE_PHOTO) {
                imageButton = (ImageButton) findViewById(R.id.sceneView);
                f = scenePhoto;
            } else if (requestCode == IntentRequestCodes.SPECIES_ADDER_SPECIMEN_PHOTO) {
                imageButton = (ImageButton) findViewById(R.id.specimenView);
                f =specimenPhoto;
            } else {
                System.out.println("SPECIES ADDER: Got an unknown result code back, ignoring it!");
                return;
            }

            BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

            Bitmap bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                    btmapOptions);

            bitmap = Bitmap.createScaledBitmap(bitmap, 256, 256, true);

            imageButton.setImageBitmap(bitmap);
        }
    }

    //public final String serverURLString = "http://users.aber.ac.uk/mta2/groupapi/addResource.php";
    public final String serverURLString = "http://192.168.1.74/testing/index.php";


    //http://stackoverflow.com/questions/23921356/android-upload-image-to-php-server
    public void onUploadButtonClick(View view){
        new AsyncUploadImageThread().execute();
    }

    public class AsyncUploadImageThread extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            HttpURLConnection conn = null;
            DataOutputStream outputStream = null;
            String lineEnding = "\n";
            String twoHyphens = "--";
            String boundary = "*****";

            int bytesRead = 0,
                    bytesAvailable = 0,
                    bufferSize;

            byte[] buffer;

            int maxBufferSize = 1024 * 1024; // 1MB

            List<File> specimenFiles = new ArrayList<File>();
            if(specimenPhoto != null){
                specimenFiles.add(specimenPhoto);
            } else {
                System.out.println("Specimen photo file empty? ignoring!");
            }

            if(scenePhoto != null){
                specimenFiles.add(scenePhoto);
            } else {
                System.out.println("Scene photo file empty? ignoring!");
            }

            try {
                for (File f : specimenFiles) {
                    if (!f.isFile()) {
                        System.out.println("ERROR, File " + f.getAbsolutePath() + " file wasn't a file!. ABORTING UPLOAD");
                        //continue to next file
                        continue;
                    }
                    FileInputStream inputStream = new FileInputStream(f);
                    URL url = new URL(serverURLString);

                    System.out.println("URL: " + url.toString());

                    //Open a connection to the server
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);  //required for max's return id's
                    conn.setDoOutput(true);
                    conn.setUseCaches(false);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("ENCTYPE", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    conn.setRequestProperty("uploaded_file", f.getName());

                    System.out.println("CONN:" + conn.toString());
                    OutputStream outputStream1 = conn.getOutputStream();
                    outputStream = new DataOutputStream(outputStream1);

                    outputStream.writeBytes(twoHyphens + boundary + lineEnding);
                    outputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename="+ f.getName() + "" + lineEnding);
                    outputStream.writeBytes(lineEnding);

                    bytesAvailable = inputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];

                    bytesRead = inputStream.read(buffer, 0, bufferSize);

                    while(bytesRead > 0) {
                        outputStream.write(buffer, 0, bufferSize);
                        bytesAvailable = inputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = inputStream.read(buffer, 0, bufferSize);
                    }

                    outputStream.writeBytes(lineEnding);
                    outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnding);

                    int responseCode = conn.getResponseCode();
                    String responseMessage = conn.getResponseMessage();

                    System.out.println("Upload completed, response code " + responseCode + " msg: " + responseMessage);

                    inputStream.close();
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (FileNotFoundException | MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Unable to open connection to the server!");
                e.printStackTrace();
            } finally {
                return null;
            }
        }
    }
}
