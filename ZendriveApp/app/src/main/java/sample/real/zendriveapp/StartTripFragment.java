package sample.real.zendriveapp;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zendrive.sdk.Zendrive;

/**
 * Created by Samarth on 5/15/2016.
 */
public class StartTripFragment extends Fragment {

    Button startTrip;
    boolean isDriving=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatep) {


        View rootView = inflater.inflate(R.layout.start_trip, container, false);
        setUpUI(rootView);
        return rootView;
    }

    //Initialize the UI edit text fields.
    public void setUpUI(View rootView){
        startTrip =(Button)rootView.findViewById(R.id.startTripButton);
        startTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isDriving){
                    String trackingId = "" + System.currentTimeMillis();
                    Zendrive.startDrive(trackingId);
                    startTrip.setText("END DRIVING");
                    startTrip.setBackgroundColor(Color.RED);
                    isDriving =true;
                }else{
                    Zendrive.stopDrive();
                    isDriving=false;
                    startTrip.setText("START DRIVING");
                    startTrip.setBackgroundColor(Color.BLUE);
                    launchResultFragment();
                }
            }
        });
    }

    private void launchResultFragment(){

    }



}
