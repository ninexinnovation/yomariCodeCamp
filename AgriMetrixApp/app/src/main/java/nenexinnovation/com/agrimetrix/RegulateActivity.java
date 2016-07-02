package nenexinnovation.com.agrimetrix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import com.kyleduo.switchbutton.SwitchButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

public class RegulateActivity extends AppCompatActivity {

    SharedPreferences preferences;

    private static final String url="http://192.168.1.2/AgriMetric/index.php/MainController/api/";

    Handler dataHandler;
    Runnable runnable;

    JSONParser jsonParser;

    SwitchButton motor;
    Switch actionView;

    TextView moisture;
    TextView temp;
    TextView humidity;
//
//    TextView mStatus;
//    TextView tStatus;
//    TextView hStatus;

    volatile boolean motorDataSending=false;
    volatile boolean systemDataSending=false;

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
        setContentView(R.layout.activity_regulate);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        motor=(SwitchButton)findViewById(R.id.switch1);

        moisture=(TextView) findViewById(R.id.txtMoisture);
        temp=(TextView)findViewById(R.id.txtTemp);
        humidity=(TextView)findViewById(R.id.txtHumidity);

//        mStatus=(TextView)findViewById(R.id.statusM);
//        tStatus=(TextView)findViewById(R.id.statusT);
//        hStatus=(TextView)findViewById(R.id.statusH);

        jsonParser=new JSONParser();
        dataHandler=new Handler(getMainLooper());
        runnable=new Runnable() {
            @Override
            public void run() {
                if(isOnline()) {
                    GetDataTask gtask = new GetDataTask();
                    gtask.execute();
                    dataHandler.postDelayed(runnable, 1000);
                }else{
                    dataHandler.postDelayed(runnable, 5000);
                }
            }
        };
        dataHandler.postDelayed(runnable,100);

        motor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                motorDataSending=true;
                dataHandler.removeCallbacks(runnable);
                ChangeMotorState m=new ChangeMotorState();
                m.execute();
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
    public class GetDataTask extends AsyncTask<Void,Void,JSONObject>{

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject jsonObject= null;
            try {
                jsonObject = jsonParser.makeHttpRequest(url+"getAllData?id=1","get",null,null);
//                Log.d("rumesh",jsonObject.toString());

            } catch (IOException e) {
                dataHandler.postDelayed(runnable,10000);
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
//            try {
//                Log.d("rumesh",jsonObject.getJSONObject("data").getJSONObject("message")+"");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            if(jsonObject!=null) {
                try {
                    int mData = jsonObject.getJSONObject("data").getJSONObject("message").getInt("moisture");
                    int tData = jsonObject.getJSONObject("data").getJSONObject("message").getInt("temperature");
                    int hData = jsonObject.getJSONObject("data").getJSONObject("message").getInt("humidity");
                    moisture.setText(mData + "%");
                    temp.setText(tData +""+ (char) 0x00B0+"C");
                    humidity.setText(hData + "%");
//
//                    if(hData>=0 && hData<=45){
//                        hStatus.setText("BAD");
//                    }else if((hData>45 && hData<50) || (hData>60 && hData<=65)){
//                        hStatus.setText("GOOD");
//                    }else if(hData>=50 && hData<=60){
//                        hStatus.setText("EXCELLENT");
//                    }else if(hData>=65 && hData<=100){
//                        hStatus.setText("Not Good");
//                    }

                    if (!systemDataSending) {
                        if (jsonObject.getJSONObject("data").getJSONObject("message").getInt("system") == 1)
                            actionView.setChecked(true);
                        else
                            actionView.setChecked(false);
                    }

                    if (!motorDataSending) {
                        if (jsonObject.getJSONObject("data").getJSONObject("message").getInt("motor") == 1)
                            motor.setChecked(true);
                        else
                            motor.setChecked(false);
                    }
//
                } catch (JSONException e) {
                }
            }
        }

    }

    public class ChangeMotorState extends AsyncTask<Void,Void,JSONObject>{
        int motorState=0;

        @Override
        protected void onPreExecute() {
            if(motor.isChecked())
                motorState=1;
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject jsonObject= null;
            try {
                jsonObject = jsonParser.makeHttpRequest(url+"setMotorStatus?id=1&status="+motorState+"&dataType=json","get",null,null);
//                Log.d("rumesh",jsonObject.toString());
            } catch (IOException e) {
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
//            try {
//                if(jsonObject.getJSONObject("data").getJSONObject("message").getString("motor_status")=="1")
//                    motor.setChecked(true);
//                else
//                    motor.setChecked(false);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            motorDataSending=false;
            dataHandler.postDelayed(runnable,100);
        }
    }

    public class ChangeSystemState extends AsyncTask<Void,Void,JSONObject>{
        int systemState=0;

        @Override
        protected void onPreExecute() {
            if(actionView.isChecked())
                systemState=1;
        }

        @Override
        protected JSONObject doInBackground(Void... voids) {
            JSONObject jsonObject= null;
            try {
                jsonObject = jsonParser.makeHttpRequest(url+"setSystemStatus?id=1&status="+systemState+"&dataType=json","get",null,null);
//                Log.d("rumesh",jsonObject.toString());
            } catch (IOException e) {
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
//            try {
//                if(jsonObject.getJSONObject("data").getJSONObject("message").getString("motor_status")=="1")
//                    motor.setChecked(true);
//                else
//                    motor.setChecked(false);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            systemDataSending=false;
            dataHandler.postDelayed(runnable,100);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        // Get the action view used in your toggleservice item
        final MenuItem toggleservice = menu.findItem(R.id.toggle_test);
        actionView = (Switch) toggleservice.getActionView();
        actionView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //To Do System Status here
                systemDataSending=true;
                dataHandler.removeCallbacks(runnable);
                ChangeSystemState s=new ChangeSystemState();
                s.execute();
//                Toast.makeText(RegulateActivity.this,"on off",Toast.LENGTH_LONG).show();
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        dataHandler.removeCallbacks(runnable);
        super.onStop();
    }
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
