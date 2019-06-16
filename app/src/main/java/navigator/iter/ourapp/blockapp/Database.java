package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
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

public class Database extends AppCompatActivity {
    AutoCompleteTextView text1;
    Toolbar mtoolbar;
    ArrayList<String> theList = new ArrayList();
    Button del,mod;
    EditText t5;
    EditText t4;
    EditText t3;
    EditText t2;
    EditText t1;
    String [] sno;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);

        text1=(AutoCompleteTextView)findViewById(R.id.choose);

        del=(Button)findViewById(R.id.delete1);
        mod=(Button)findViewById(R.id.modify1);

        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
        text1.setAdapter(adapter);
        Backtask bc=new Backtask();
        bc.execute(" ");

        text1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE)
                {

                }
                return  true;
            }
        });

        text1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                    if(theList.contains(text1.getText().toString()))
                    {
                            new ExecuteTask().execute(text1.getText().toString());
                    }
            }

        });


    }

    public void modify1(View v)
    {
        if(text1.getText().toString().compareTo("")!=0 && t5.length()!=0) {
            new ExecuteTask1().execute("4",t1.getText().toString(),t2.getText().toString(),t3.getText().toString(),t4.getText().toString(),t5.getText().toString(),sno[0]);
        }
    }

    public void delete1(View v)
    {
        if(text1.getText().toString().compareTo("")!=0 && t5.length()!=0) {
            new ExecuteTask1().execute("3",sno[0]);
        }
    }

    class ExecuteTask1 extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                String j1=strings[0];

                URL url=new URL("http://argetim2k17.com/ITER_Navigator/del_ins.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post="";
                if(j1.compareTo("3")==0) {
                    post = URLEncoder.encode("sno", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8") + "&" +
                            URLEncoder.encode("way", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8");
                }
                if(j1.compareTo("4")==0)
                {
                    post = URLEncoder.encode("room", "UTF-8") + "=" + URLEncoder.encode(strings[3], "UTF-8") + "&" +
                            URLEncoder.encode("desg", "UTF-8") + "=" + URLEncoder.encode(strings[4], "UTF-8") + "&" +
                            URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8") + "&" +
                            URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(strings[2], "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(strings[5], "UTF-8") + "&" +
                            URLEncoder.encode("sno", "UTF-8") + "=" + URLEncoder.encode(strings[6], "UTF-8") + "&" +
                            URLEncoder.encode("way", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8");
                }
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
                return sb.toString();
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.compareTo("Deleted")==0) {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                //theList.remove(text1.getText().toString());
                text1.setText("");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                finish();
                startActivity(getIntent());
            }
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            if(s.compareTo("Modified")==0)
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                finish();
                startActivity(getIntent());
                text1.setText("");
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
            }
        }
    }

    class ExecuteTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
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


            try {
                JSONArray array=new JSONArray(s);
                int size=array.length();

                n = new ArrayList<String>();
                //n=new String[size];
                m=new String[size];
                r=new String[size];
                e=new String[size];
                d=new String[size];
                sno=new String[size];
                int i;

                for(i=0;i<array.length();i++)
                {
                    JSONObject obj = array.getJSONObject(i);
                    n.add(obj.getString("name"));

                    m[i] = obj.getString("mobile");
                    r[i] = obj.getString("roomno");
                    e[i] = obj.getString("Email");
                    d[i] = obj.getString("designation");
                    sno[i]=obj.getString("sno1");
                }
                if(m[0].compareTo("")!=0) {
                     t1 = (EditText) findViewById(R.id.faculty1);
                     t2 = (EditText) findViewById(R.id.number1);
                     t3 = (EditText) findViewById(R.id.roomNum1);
                     t4 = (EditText) findViewById(R.id.type1);
                     t5 = (EditText) findViewById(R.id.email1);

                    t1.setText(n.get(0));
                    t2.setText(m[0]);
                    t3.setText(r[0]);
                    t4.setText(d[0]);
                    t5.setText(e[0]);

                }


            } catch (JSONException e1) {
                e1.printStackTrace();
            }


        }
    }


    public class Backtask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String str="";
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/namesearch.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String j;

                while((j=bufferedReader.readLine())!=null)
                {
                    str=str+j;
                }
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str ;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject obj;
            try {
                JSONArray array = new JSONArray(s);


                int i;

                for (i = 0; i < array.length(); i++) {
                     obj = array.getJSONObject(i);
                    if(obj.getString("name").compareTo("null")!=0)
                    theList.add(obj.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
           // adapter=new ArrayAdapter<ArrayList>(this,android.R.layout.simple_list_item_1,theList);

        }
    }

}
