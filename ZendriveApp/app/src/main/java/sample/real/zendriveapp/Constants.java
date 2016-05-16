package sample.real.zendriveapp;

/**
 * Created by Samarth on 5/15/2016.
 */
/**
 * List of constants.
 */
public class Constants {

    // TODO: Set your Zendrive SDK key and driver id here.
    // The Zendrive SDK key is available in your account at https://www.zendrive.com
    public static final String zendriveSDKKey = "k0RYFzfzJ6TMYeXxPSwAV9gIj01OR5OI";

    // logging key.
    public static final String LOG_TAG_DEBUG = "ZendriveSDKDemo";

    // keys used for local broadcast to update UI.
    public static String DRIVE_START = "drive_start";
    public static String ACCIDENT = "accident";
    public static String DRIVE_END = "drive_end";
    public static String EVENT_LOCATION_PERMISSION_CHANGE = "location_permission_change";
    public static String EVENT_LOCATION_SETTING_CHANGE = "location_setting_change";
    // data field names used in local broadcast messages.
    public static final String DRIVE_DISTANCE = "drive_distance";
    public static final String ACCIDENT_ID = "accident_id";
}
