package sample.real.zendriveapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private static String FRAG_NAME = "regist_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_activity);
        loadRegisterUSerFragment();

    }

    private void loadRegisterUSerFragment(){
        RegisterUserFragment fragment = new RegisterUserFragment();
        this.getFragmentManager().beginTransaction()
                .replace(R.id.blank_activity, fragment,FRAG_NAME)
                .addToBackStack(null)
                .commit();
    }


}
