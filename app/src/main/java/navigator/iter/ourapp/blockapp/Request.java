package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Request extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list;
    TextView txt;
    Context c=this;
    Toolbar mtoolbar;
Handler mHandler;
    int size;
    int z=0;
    SharedPreferences sh;
    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable()
    {
        return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_request);



        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);
        list=(ListView)findViewById(R.id.list);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       //Auto Refresh
        //this.mHandler = new Handler();
       // this.mHandler.postDelayed(m_Runnable,5000);
        new ExecuteTask().execute("fetchfaculty");
        sh=getSharedPreferences("notification",MODE_PRIVATE);
        String noti=sh.getString("cnt","");
        if(noti.compareTo("1")==0)
        {
            Backtask3 bb=new Backtask3();
            bb.execute(" ");
            sh=getSharedPreferences("notification",MODE_PRIVATE);
            SharedPreferences.Editor editor=sh.edit();
            editor.putString("cnt","0");
            editor.commit();
        }

    }
    private final Runnable m_Runnable = new Runnable()
    {
        public void run()

        {
           // Toast.makeText(Request.this,"in runnable",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Request.this, Request.class);
                PendingIntent pIntent = PendingIntent.getActivity(Request.this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(Request.this)

                        .setSmallIcon(R.drawable.back_image)

                        .setTicker("Requests")

                        .setContentTitle("New Requests Available")

                        .setContentText("click to view")

                        .setContentIntent(pIntent)

                        .setAutoCancel(true);

                // Create Notification Manager
                NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Build Notification with Notification Manager
                notificationmanager.notify(0, builder.build());


            new ExecuteTask().execute("fetchfaculty");
            Request.this.mHandler.postDelayed(m_Runnable, 5000);
        }

    };
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Button bt=(Button)view.findViewById(R.id.allow);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Allow button clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

     class ExecuteTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/allsignup.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
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

            String [] n;
            String [] m;
            String [] r;
            String [] e;
            String [] d;
            String [] p;
            String [] otp;
            try {
                JSONArray array=new JSONArray(s);
                size=array.length();

                n = new String[size];
                //n=new String[size];
                m=new String[size];
                r=new String[size];
                e=new String[size];
                d=new String[size];
                p=new String[size];
                otp=new String[size];
                int i=0;
                int j=0;
                for(i=0;j<array.length();j++)
                {
                    JSONObject obj=array.getJSONObject(j);

                    otp[i]=obj.getString("otp");
                    if(otp[i].compareTo("1")==0) {

                        n[i]=obj.getString("name");
                        // n[i]=obj.getString("Name");
                        m[i] = obj.getString("mobile");
                        r[i] = obj.getString("roomno");
                        e[i] = obj.getString("Email");
                        d[i] = obj.getString("designation");
                        p[i] = obj.getString("passwd");
                        i++;
                    }

                }
                customfaculty cs=new customfaculty(Request.this,n,p,m,e,r,d);
                list.setAdapter(cs);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                        Button bt=view.findViewById(R.id.allow);

                        bt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getApplicationContext(),"Allow button clicked",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            } catch (JSONException e1) {
                e1.printStackTrace();
            }


        }
    }
    public class Backtask3 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/cntdefault.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("cnt", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String j;
                StringBuffer sb=new StringBuffer();

                while((j=bufferedReader.readLine())!=null)
                {
                    sb.append(j);
                }
                return_text=sb.toString();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return return_text ;
        }


    }

    @Override
    public void onBackPressed() {
        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
