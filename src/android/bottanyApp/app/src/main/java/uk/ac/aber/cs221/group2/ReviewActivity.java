package uk.ac.aber.cs221.group2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.ac.aber.cs221.group2.dataClasses.Specimen;
import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.utils.SpecimenDataSource;

/**
 * Created by nic on 26/01/15.
 */
public class ReviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            //add fragments here
        }

        SpecimenDataSource specimenDataSource = new SpecimenDataSource(this);
        final List<Specimen> specimens = specimenDataSource.findAll();
        final List<String> specimenNames = new ArrayList<>();int userId, visitId;
        for(Specimen s : specimens){
            specimenNames.add(s.getName());
        }

        StableArrayAdapter stableArrayAdapter = new StableArrayAdapter(this, R.id.specimenReviewList, specimenNames);
        ((ListView)findViewById(R.id.specimenReviewList)).setAdapter(stableArrayAdapter);
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
