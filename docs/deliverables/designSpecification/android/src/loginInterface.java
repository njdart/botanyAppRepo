package uk.ac.aber.cs211.group02.plantrecordapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email and password.
 */
public interface LoginPage extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * Called when building page for the GUI interface;
     */
     
    protected void onCreate(Bundle savedInstanceState);

    /**
     * AutoFill
     */
    private void populateAutoComplete();

    /**
     * Check email
     */
    private boolean isEmailValid(String email);

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show);

    
    /**
     * Called when building page for the GUI ;
     */
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle);

    /**
     * Called when building page for the GUI is finished;
     */
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor);

     
    public void onLoaderReset(Loader<Cursor> cursorLoader);

    /**
     * Called when building page for the GUI interface;
     */
    private void addEmailsToAutoComplete(List<String> emailAddressCollection);

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public interface UserLoginTask extends AsyncTask<Void, Void, Boolean> {
         
        protected Boolean doInBackground(Void... params);

        protected void onPostExecute(final Boolean success);
         
        protected void onCancelled();
    }
}