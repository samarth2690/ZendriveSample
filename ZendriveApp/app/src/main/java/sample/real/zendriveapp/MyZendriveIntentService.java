package sample.real.zendriveapp;

import android.util.Log;

import com.zendrive.sdk.AccidentInfo;
import com.zendrive.sdk.DriveInfo;
import com.zendrive.sdk.DriveStartInfo;
import com.zendrive.sdk.ZendriveIntentService;

/**
 * Created by Samarth on 5/15/2016.
 */

//This class will receive callbacks from the Zendrive Intent service.


public class MyZendriveIntentService extends ZendriveIntentService {

    protected MyZendriveIntentService(String s) {
        super("MyZendriveIntentService");
    }

    @Override
    public void onCreate() {
        Log.d(Constants.LOG_TAG_DEBUG, " ZendriveSdkNotificationService Created");
        super.onCreate();
    }

    @Override
    public void onDriveStart(DriveStartInfo startInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Drive Start");
        ZendriveManager.getSharedInstance(getApplicationContext()).onDriveStart(startInfo);
    }

    @Override
    public void onDriveEnd(DriveInfo driveInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Drive End");
        ZendriveManager.getSharedInstance(getApplicationContext()).onDriveEnd(driveInfo);
    }

    @Override
    public void onAccident(AccidentInfo accidentInfo) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Accident Detected");
        ZendriveManager.getSharedInstance(getApplicationContext()).onAccident(accidentInfo);
    }

    @Override
    public void onLocationPermissionsChange(boolean granted) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Location Permission : " +
                granted);
        ZendriveManager.getSharedInstance(getApplicationContext()).onLocationPermissionsChange(granted);
    }

    @Override
    public void onLocationSettingsChange(boolean enabled) {
        Log.d(Constants.LOG_TAG_DEBUG, "CallBack From SDK: Location Setting : " + enabled);
        ZendriveManager.getSharedInstance(getApplicationContext()).onLocationSettingsChange(enabled);
    }
}
