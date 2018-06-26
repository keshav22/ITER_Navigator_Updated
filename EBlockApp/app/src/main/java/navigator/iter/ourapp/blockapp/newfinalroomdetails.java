package navigator.iter.ourapp.blockapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class newfinalroomdetails extends AppCompatActivity {

    ListView list;
    Toolbar mtoolbar;
    Context c=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newfinalroomdetails);

        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);
        list=(ListView)findViewById(R.id.list);

        Intent in=getIntent();
        String k=in.getStringExtra("number");

        ExecuteTask ex=new ExecuteTask();
        ex.execute(k);

    }

    class ExecuteTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {
            try {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/finalroomdetails.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("search", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String str;
                String l = "";

                while ((str = bufferedReader.readLine()) != null) {
                    l += str;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return l;
            }
            catch (Exception e)
            {

            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            // txt.setText(s);

            final ArrayList<String> n;
            String [] m;
            String [] r;
            String [] e;
            String [] d;
            String [] o;
            try {
                JSONArray array=new JSONArray(s);
                int size=array.length();

                n = new ArrayList<String>();
                //n=new String[size];
                m=new String[size];
                r=new String[size];
                e=new String[size];
                d=new String[size];
                o=new String[size];

                int i=0;

                for(i=0;i<array.length();i++)
                {
                    JSONObject obj=array.getJSONObject(i);
                    n.add(obj.getString("name"));
                    // n[i]=obj.getString("Name");
                    m[i]=obj.getString("mobile");
                    r[i]=obj.getString("roomno");
                    e[i]=obj.getString("Email");
                    d[i]=obj.getString("designation");
                    o[i]=obj.getString("online");
                }
                customsearch cs=new customsearch(newfinalroomdetails.this,n,o,m,e,r,d);
                list.setAdapter(cs);

            } catch (JSONException e1) {
                e1.printStackTrace();
            }


        }
    }

}
