package uk.ac.aber.cs221.group2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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
import java.util.ArrayList;
import java.util.List;

import uk.ac.aber.cs221.group2.dataClasses.Specimen;
import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.dataClasses.Visit;
import uk.ac.aber.cs221.group2.utils.SiteDataSource;
import uk.ac.aber.cs221.group2.utils.SpecimenDataSource;
import uk.ac.aber.cs221.group2.utils.UserDataSource;

/**
 * Created by nic on 26/01/15.
 */
public class ReviewActivity extends BaseActivity {

    private SpecimenDataSource specimenDataSource;
    private SiteDataSource siteDataSource;
    private UserDataSource userDataSource;
    private ArrayList<String> specimenNames;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_submit);

        specimenDataSource = new SpecimenDataSource(this);
        siteDataSource = new SiteDataSource(this);
        userDataSource = new UserDataSource(this);
        final List<Specimen> specimens = specimenDataSource.findAll();
        final List<String> specimenNames = new ArrayList<>();
        for(Specimen s : specimens){
            specimenNames.add(s.getName());
            System.out.println("adding specimen " + s.getName());
        }

        listView = (ListView)findViewById(R.id.specimenList);
        updateAdapters();

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                final ListView listView = (ListView)v;

                AdapterView.AdapterContextMenuInfo info =
                        (AdapterView.AdapterContextMenuInfo) menuInfo;

                final String selectedWord = ((TextView) info.targetView).getText().toString();

                System.out.println("SELECTED WORD: " + selectedWord);

                final Dialog dialog = new Dialog(ReviewActivity.this);
                dialog.setContentView(R.layout.edit_delete_alert_dialog);

                dialog.setTitle("Edit or Delete");

                Button editButton = (Button) dialog.findViewById(R.id.editButton);
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("edit");
                        Intent i = new Intent(ReviewActivity.this, SpecimenAdder.class);
                        i.putExtra("NAME", selectedWord);
                        startActivity(i);
                    }
                });

                Button deleteButton = (Button) dialog.findViewById(R.id.deleteButton);
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("Delete");

                        specimenDataSource.removeByName(selectedWord);
                        updateAdapters();

                        dialog.cancel();
                    }
                });


                dialog.show();

            }
        });
    }

    public void updateAdapters() {
        List<Specimen> specimens = specimenDataSource.findAll();
        specimenNames = new ArrayList<String>();
        for(Specimen s : specimens){
            specimenNames.add(s.getName());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, specimenNames);
        listView.setAdapter(arrayAdapter);
    }

    public void onSubmitButtonClick(View view) {
        if (!isNetworkAvailable()) {
            new AlertDialog.Builder(this)
                    .setTitle("No Internet Connection")
                    .setMessage("An internet connection is required for upload")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    }).show();
            return;
        }

        Toast.makeText(this, "Uploading...", Toast.LENGTH_LONG).show();
        //begin upload!
        new AsyncUploadImageThread().execute();
    }

    public final String serverAddResourceUrl = "http://users.aber.ac.uk/mta2/groupapi/addResource.php";
    public final String serverAddSpecimenUrl = "http://users.aber.ac.uk/mta2/groupapi/addRecord.php";

    private boolean isNetworkAvailable() {
        return true;

        //TODO check internet connectivity
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private int uploadImage(File file){
        return uploadImage(file, true);
    }

    private int uploadImage(File file, Boolean allowRetry){

        int imageId = -1;

        System.out.println("FILE PATH: " + file.toString() + " FILE NAME " + file.getName());
        if(file == null){
            System.out.println("FILE WAS NULL");
            return -1;
        }
        //upload images and get their
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

        try {
            if (!file.isFile()) {
                System.out.println("ERROR, File " + file.getAbsolutePath() + " file wasn't a file!. ABORTING UPLOAD");
                //continue to next file
                return -1;  //server expects -1 for a non-image
            }
            FileInputStream inputStream = new FileInputStream(file);
            URL url = new URL(serverAddResourceUrl);

            System.out.println("URL: " + url.toString());

            //Open a connection to the server
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);  //required for max's return id's
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("ENCTYPE", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            conn.setRequestProperty("resource", file.getName());

            System.out.println("CONN:" + conn.toString());
            OutputStream outputStream1 = conn.getOutputStream();
            outputStream = new DataOutputStream(outputStream1);

            outputStream.writeBytes(twoHyphens + boundary + lineEnding);
            outputStream.writeBytes("Content-Disposition: form-data; name=\"resource\";filename=" + file.getName() + "" + lineEnding);
            outputStream.writeBytes(lineEnding);

            bytesAvailable = inputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            bytesRead = inputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0) {
                outputStream.write(buffer, 0, bufferSize);
                bytesAvailable = inputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                bytesRead = inputStream.read(buffer, 0, bufferSize);
            }

            outputStream.writeBytes(lineEnding);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnding);

            int responseCode = conn.getResponseCode();
            String responseMessage = conn.getResponseMessage();

            //read back the page
            InputStream responseStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder serverResponsePage = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                serverResponsePage.append(line);
            }
            if(responseCode != 200){
                if(allowRetry) {
                    return uploadImage(file, false);
                } else {
                    Toast.makeText(ReviewActivity.this, "Server responded with an error, please try later", Toast.LENGTH_LONG).show();
                    return -1;
                }
            }

            imageId = Integer.parseInt(serverResponsePage.toString());
            System.out.println("UPLOAD RESPONSE: " + responseCode + " " + responseMessage + ", ID: " + imageId);

            //inputStream.close();
            //outputStream.flush();
            //outputStream.close();

            return imageId;

        } catch (FileNotFoundException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to open connection to the server!");
            e.printStackTrace();
        }
        return imageId;
    }

    private boolean postData(String json){
        InputStream inputStream = null;
        String result = "";
        System.out.println("JSON TO SEND: " + json);
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(serverAddSpecimenUrl);

            StringEntity se = new StringEntity(json);

            httpPost.setEntity(se);

            httpPost.setHeader("Cache-Control", "no-cache");
            httpPost.setHeader("Content-Disposition", "form-data");

            System.out.println(httpPost);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
            nameValuePairs.add(new BasicNameValuePair("record", json));

            for(Header h : httpPost.getAllHeaders()){
                System.out.println(h);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse httpResponse = httpclient.execute(httpPost);
            System.out.println(httpResponse.getStatusLine().toString());

            inputStream = httpResponse.getEntity().getContent();

            if(inputStream != null){
                //we dont actually care about the return page, it;s for a browser!
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
                String line = "";
                while((line = bufferedReader.readLine()) != null)
                    result += line;

                System.out.println("RESULT: " + result);

                inputStream.close();
                return true;
            } else {
                System.out.println("ERROR, input stream was null, probebly didnt upload ok");
                Toast.makeText(ReviewActivity.this, "Upload Failed", Toast.LENGTH_LONG).show();
                return false;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }


    public class AsyncUploadImageThread extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {

            //required to make toast (YUM)
            Looper.prepare();


            //get all the specimens
            List<Specimen> specimens = specimenDataSource.findAll();
            System.out.println("SPECIMEN COUNT: " + specimens.size());
            for(Specimen s : specimens){
                System.out.println("NAME: " + s.getName() + " UID: " + s.getUserId() + " VID: " + s.getVisitId());
            }

            List<Visit> sites = siteDataSource.findAll();
            System.out.println("SITE COUNT " + sites.size());
            for(Visit v : sites){
                System.out.println("VISIT ID: " + v.getVisitId() + ", NAME: " + v.getVisitName());
            }

            List<User> users = userDataSource.findAll();
            System.out.println("USER COUNT " + users.size());
            for(User u: users){
                System.out.println("USER ID: " + u.getUserId() + ", NAME; " + u.getName());
            }

            //now construct the json for the specimen
            User user = User.CurrentUser;
            Visit visit = Visit.CurrentVisit;

            if(user == null){
                System.out.println("Unknown User by id " + specimens.get(0).getUserId());
            }
            if(visit == null){
                System.out.println("Unknown Visit by id " + specimens.get(0).getVisitId());
            }

            String json = "{";

            json += "\"UserName\":" + "\"" + user.getName() + "\",";
            json += "\"UserPhone\":" + "\"" + user.getUserPhoneNumber() + "\",";
            json += "\"UserEmail\":" + "\"" + user.getUserEmail() + "\",";
            json += "\"Timestamp\":" + visit.getVisitDate() + ",";
            json += "\"ReserveID\":" + visit.getVisitId() + ",";
            json += "\"Specimens\":";
            json += "[";

            //make a hash map out of the files for receiving their id's
            for(int i = 0; i < specimens.size(); i++) {
                int specimenId = uploadImage(new File(specimens.get(i).getSpecimenPhotoURI()));
                int sceneId = uploadImage(new File(specimens.get(i).getScenePhotoURI()));

                json += "{";

                json += "\"SpeciesName\":" + "\"" + specimens.get(i).getName() + "\",";
                json += "\"LocationLatitude\":" + specimens.get(i).getLatitude() + ",";
                json += "\"LocationLongitude\":" + specimens.get(i).getLongitude() + ",";
                json += "\"Abundance\":" + (specimens.get(i).getAbundance().ordinal() + 1) + ",";
                //json += "\"Comment\":" + "\"" + ((specimens.get(i).getComment() == "")?"N/A":specimens.get(i).getComment()) + "\",";
                json += "\"Comment\":" + "\"N/A\",";
                json += "\"ScenePhoto\":" + "\"" + sceneId + "\",";
                json += "\"SpecimenPhoto\":" + "\"" + specimenId + "\"";

                json += "}";
                if(i < specimens.size()-1){
                    json += ",";
                }
            }

            json += "]";

            json += "}";

            //we dont need to return anything
            System.out.println(json);
            boolean postResponse = postData(json);
            if(postResponse){
                Toast.makeText(ReviewActivity.this, "Upload Succeeded", Toast.LENGTH_LONG).show();
                ListView listView = (ListView)findViewById(R.id.specimenList);

                specimenDataSource.empty();

//                Handler handler = new Handler(Looper.getMainLooper());
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(ReviewActivity.this, LauncherActivity.class);
//                        startActivity(intent);
//                    }
//                });
            } else {
                Toast.makeText(ReviewActivity.this.getApplicationContext(), "Upload Failed", Toast.LENGTH_LONG).show();
            }

            System.out.println("JSON POST " + ((postResponse)?"SUCCEEDED!":"FAILED!"));
            return null;

        }
    }
}