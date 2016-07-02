package nenexinnovation.com.agrimetrix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    String[] menuArray;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);

        String lang=preferences.getString("language_list","");
        Log.e("rumesh pref",preferences.getString("language_list",""));

        if(lang.equals("np")) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }else{
            lang="en";
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
        setContentView(R.layout.activity_main);

        menuArray=getResources().getStringArray(R.array.menu_items);


        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activity_listview,menuArray);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView clickedView=(TextView)view;
//                Log.d("rumesh index",i+"");
//                switch (clickedView.getText().toString()){
                switch (i){
//                    case "Regulate":
                    case 0:
                        Intent regIntent=new Intent(MainActivity.this,RegulateActivity.class);
                        startActivity(regIntent);
//                        Toast.makeText(MainActivity.this,"hello regulate",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Intent forum=new Intent(MainActivity.this,CropDiseasesForum.class);
                        startActivity(forum);
                        break;
                }
            }
        });

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(
                mMessageReceiver, new IntentFilter("setting.Changed"));

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("rumesh","received");
//            startActivity(getIntent());
            finish();
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.setting:
                Intent setting=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(setting);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMessageReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(
                    mMessageReceiver);
        }
    }
}
