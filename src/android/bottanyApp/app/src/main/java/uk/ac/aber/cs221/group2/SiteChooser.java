package uk.ac.aber.cs221.group2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import uk.ac.aber.cs221.group2.utils.SiteDataSource;


public class SiteChooser extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site_chooser);
        //Toast.makeText(getApplicationContext(), "Hello World! I'm Toast!", Toast.LENGTH_LONG).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, autoCompleteEntries);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.siteNameAutoComplete);
        textView.setAdapter(adapter);
    }

    public static ArrayList<String> autoCompleteEntries = new ArrayList<String>() {{
        add("Foo");
        add("Bar");
        add("FooBar");
        add("BarFoo");
        add("Faz");
        add("Baz");
    }};



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_site_chooser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View view){
        SiteDataSource source = new SiteDataSource(this);

        source.open();

        source.create(((AutoCompleteTextView)findViewById(R.id.siteNameAutoComplete)).getText().toString());
        List<String> list = source.findAll();

        System.out.println(list.toString());
        System.out.println(list.get(0));

    }

    public void onUseSiteClick(View view){
        String compareTo = ((AutoCompleteTextView)findViewById(R.id.siteNameAutoComplete))
                .getText().toString();
        if(!autoCompleteEntries.contains(compareTo)) {
            autoCompleteEntries.add(compareTo);
            System.out.println("Adding entry " + compareTo);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, autoCompleteEntries);
            AutoCompleteTextView textView = (AutoCompleteTextView)
                    findViewById(R.id.siteNameAutoComplete);
            textView.setAdapter(adapter);

            new AlertDialog.Builder(this)
                    .setTitle("New Site")
                    .setMessage("No site was previously found by this name, Are you sure?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            Intent i = new Intent(SiteChooser.this, SpeciesAdder.class);
                            startActivity(i);
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        } else {
            Intent i = new Intent(SiteChooser.this, SpeciesAdder.class);
            startActivity(i);
        }
    }

    public class ArrayListCaseInsensitive extends ArrayList<String> {
        @Override
        public boolean contains(Object o) {
            String paramStr = (String)o;
            for (String s : this) {
                if (paramStr.equalsIgnoreCase(s)) return true;
            }
            return false;
        }
    }
}
