package uk.ac.aber.cs221.group2;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by Nic on 23/01/2015.
 */
public class BaseActivity  extends Activity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_preferences) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
