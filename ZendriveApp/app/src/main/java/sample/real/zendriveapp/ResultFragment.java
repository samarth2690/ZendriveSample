package sample.real.zendriveapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zendrive.sdk.DriveInfo;

/**
 * Created by Samarth on 5/16/2016.
 */
public class ResultFragment extends Fragment {

    private TextView drivername;
    private TextView startTime;
    private TextView endTime;
    private TextView avgSpeed;
    private TextView maxSpeed;
    private Button   newTest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatep) {


        View rootView = inflater.inflate(R.layout.result_fragment, container, false);
        setUpUI(rootView);
        return rootView;
    }

    private  void setUpUI(View rootView){
        drivername =(TextView)rootView.findViewById(R.id.driverName);
        startTime = (TextView)rootView.findViewById(R.id.startTime);
        endTime =   (TextView)rootView.findViewById(R.id.endTime);
        avgSpeed = (TextView)rootView.findViewById(R.id.averageSpeed);
        maxSpeed =   (TextView)rootView.findViewById(R.id.maxSpeed);
        newTest = (Button)rootView.findViewById(R.id.beginNewTest);
        newTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchBeginNewTest();
            }
        });
    }

    private void diplayResult(){
        TripListDetails tripDetails = loadTripDetails();
        DriveInfo info = tripDetails.tripList.get(0);
        drivername.setText("Samarth");
        startTime.setText(info.startTimeMillis+"");
        endTime.setText(info.endTimeMillis+"");
        maxSpeed.setText(info.maxSpeed+"");
        avgSpeed.setText(info.averageSpeed+"");
    }

    private void launchBeginNewTest(){


    }

    private TripListDetails loadTripDetails() {

        SharedPreferences sharedPreferences = PreferenceManager.
                getDefaultSharedPreferences(this.getActivity().getApplicationContext());
        String tripDetailsJsonString = sharedPreferences.getString(SharedPreferenceManager.TRIP_DETAILS_KEY, null);
        if (null == tripDetailsJsonString) {
            return new TripListDetails();
        }
        return new Gson().fromJson(tripDetailsJsonString, TripListDetails.class);
    }

}
