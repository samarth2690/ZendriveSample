package sample.real.zendriveapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.zendrive.sdk.Zendrive;
import com.zendrive.sdk.ZendriveConfiguration;
import com.zendrive.sdk.ZendriveOperationResult;
import com.zendrive.sdk.ZendriveSetupCallback;

/**
 * Created by Samarth on 5/15/2016.
 */
public class RegisterUserFragment extends Fragment {

    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText email;
    Button next;
    static String FRAG_NAME = "register_user";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceStatep) {


        View rootView = inflater.inflate(R.layout.activity_main, container, false);
        setUpUI(rootView);
        return rootView;


    }

    //Initialize the UI edit text fields.
    public void setUpUI(View rootView){
        firstName = (EditText)rootView.findViewById(R.id.firstName);
        lastName = (EditText)rootView.findViewById(R.id.lastName);
        phone = (EditText)rootView.findViewById(R.id.phone);
        email = (EditText)rootView.findViewById(R.id.email);
        next = (Button)rootView.findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpSDK();

            }
        });
      }


    public void saveUserInfoInSharedPref(){
        UserInfo info = new UserInfo();
        info.setFirstName(firstName.getText().toString());
        info.setLastName(lastName.getText().toString());
        info.setEmail(email.getText().toString());
        info.setPhoneNumber(phone.getText().toString());
        Gson gson = new Gson();
        String userJson = gson.toJson(info);
        SharedPreferenceManager.setPreference(getActivity(),SharedPreferenceManager.USER_DETAILS,userJson);
        SharedPreferenceManager.setPreference(getActivity(),SharedPreferenceManager.DRIVER_ID_KEY,firstName.getText().toString());

    }

    public void setUpSDK(){
        if(!Zendrive.isSDKSetup()) {
            initializeZendriveSDK();
        }
 }

    private void initializeZendriveSDK(){
        ZendriveManager zendriveManager = ZendriveManager.getSharedInstance(getActivity().getApplicationContext());
        if (zendriveManager.isSdkInitialized()) {
            return;
        }
        // check for valid sdk key.
        if(null == Constants.zendriveSDKKey || Constants.zendriveSDKKey.equalsIgnoreCase("")){
            AlertDialog alertDialog = showErrorDialog(getResources().getString(R.string.invalid_sdk_key),getResources().getString(R.string.default_sdk_key));
            alertDialog.show();
            return;
        }
        saveUserInfoInSharedPref();

        ZendriveConfiguration configuration = zendriveManager.getSavedConfiguration();
        if(null == configuration){
            return;
        }

        zendriveManager.initializeZendriveSDK(configuration, new ZendriveSetupCallback() {
            @Override
            public void onSetup(ZendriveOperationResult zendriveOperationResult) {
                if(zendriveOperationResult.isSuccess()){


                    launchNextFragment();
                }else{
                    showErrorDialog("Oops", "Some problem with sdk Initializtion.").show();
                }
            }
        });


    }


    private AlertDialog showErrorDialog(String title ,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        AlertDialog alert = builder.create();
        return alert;
    }

    private void launchNextFragment(){
        StartTripFragment nextFrag= new StartTripFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.blank_activity, nextFrag,FRAG_NAME)
                .addToBackStack(null)
                .commit();

    }





}
