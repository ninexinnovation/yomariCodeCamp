package nenexinnovation.com.agrimetrix;
import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JSONParser {
    private final String USER_AGENT = "Mozilla/5.0";
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
    public int response;
    URLConnection urlConn;
    HttpURLConnection conn;
    // constructor
    public JSONParser() {

    }

    // function get json from url
// by making HTTP POST or GET method
    public JSONObject makeHttpRequest(String url, String method, String[] name, String[] value) throws IOException {

            URL url1 = new URL(url);
            urlConn=url1.openConnection();

//            String encoded = Base64.encodeToString((username + ":" + password).getBytes("UTF-8"), Base64.NO_WRAP);
            conn = (HttpURLConnection)urlConn;
//            conn.setRequestProperty("Authorization", "Basic " + encoded);
            if(method.equals("GET")) {
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", USER_AGENT);
            }else{
                conn.setRequestMethod("POST");
                conn.setRequestProperty("User-Agent", USER_AGENT);
                conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                String urlParameters = "";
                String ALLOWED_URI_CHARS = "@#*+-_.,:!?()/~'%";
                if(name!=null) {
                    for (int i = 0; i < name.length; i++) {
//                    conn.setRequestProperty(name[i],value[i]);
                        urlParameters += name[i] + "=" + Uri.encode(value[i], ALLOWED_URI_CHARS);
                        if (i != name.length - 1)
                            urlParameters += "&";
                    }
                }

                conn.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();
                Log.e("rumesh request",urlParameters);
            }
            conn.connect();


            response = conn.getResponseCode();

            Log.e("rumesh response", "" + response);


//            if(method.equals("POST")){
//                return null;
//            }

            is = conn.getInputStream();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String

        return jObj;

    }
    public void cancel(){
        if(conn!=null) {
            conn.disconnect();
        }
    }
}