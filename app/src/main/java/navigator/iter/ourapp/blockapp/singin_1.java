package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class singin_1 extends AppCompatActivity {
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;
    AutoCompleteTextView autoCompleteTextView;
    EditText editText;
    ArrayList<String> theList = new ArrayList();
    Context context;
    Toolbar mtoolbar;
    Button signinbtn,bt1;
    SharedPreferences sh;
    private Context mContext;
    TextView forget;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            sh = getSharedPreferences("email", MODE_PRIVATE);
            SharedPreferences.Editor editor2 = sh.edit();
            editor2.putString("facemail", "email");
            editor2.commit();

    }

    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable()
    {
        return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_faculty_name_home, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin_1);
        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.name);
        editText=(EditText)findViewById(R.id.passwd);
        context=this;

        forget=(TextView)findViewById(R.id.forgetpass);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autoCompleteTextView.length()!=0 && theList.contains(autoCompleteTextView.getText().toString()))
                {

                    sh = getSharedPreferences("email", MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = sh.edit();
                    editor2.putString("facemail", autoCompleteTextView.getText().toString());
                    editor2.commit();
                    passwordfragment fragment = new passwordfragment(1);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.main70, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                else
                {
                    autoCompleteTextView.requestFocus();
                    Toast.makeText(getApplicationContext(),"Error in email",Toast.LENGTH_SHORT).show();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
       // university1 uni=new university1(context,2);
        //uni.execute(autoCompleteTextView.getText().toString());
        Backtask bsk=new Backtask();
        bsk.execute("1");
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId== EditorInfo.IME_ACTION_SEND)
                {
                    editText.requestFocus();
                }

                return true;
            }
        });
        signinbtn=(Button)findViewById(R.id.signinbtn);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==EditorInfo.IME_ACTION_DONE)
                {

                    signinbtn.performClick();
                }
                return true;
            }
        });



        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // Toast.makeText(getApplicationContext(),trimmed,Toast.LENGTH_SHORT).show();
                if(!isNetworkAvailable())
                {
                    Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                }
                else {

                    if (theList.contains(autoCompleteTextView.getText().toString()) && autoCompleteTextView.getText().length()>0) {

                        Backtask12 uni = new Backtask12(context);
                        uni.execute(autoCompleteTextView.getText().toString(), editText.getText().toString());
                    }
                    else
                    {
                        Toast.makeText(singin_1.this,"Enter a valid name",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
    public class Backtask12 extends AsyncTask<String,Void,String> {

        public Backtask12(final Context context)
        {
            mContext = context;
        }

        @Override
        protected String doInBackground(String... strings) {
            String name = strings[0];
            String passwd = strings[1];
            String l = "";
            try {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/passwdmatch.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("passwd", "UTF-8") + "=" + URLEncoder.encode(passwd, "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String str;

                while ((str = bufferedReader.readLine()) != null) {
                    l += str;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            onPostExecute1(l,name);
            return null;
        }

        protected void onPostExecute1(String s,String name) {
            super.onPostExecute(s);

                if (s.compareTo("Login Success") == 0)
                {

                    Backtask123 b=new Backtask123();

                    b.execute(autoCompleteTextView.getText().toString());

                    String trimmed="";

                    if(autoCompleteTextView.length()>2)
                        trimmed=autoCompleteTextView.getText().toString().trim().toLowerCase().substring(0,3);
                    if (trimmed.compareTo("hod") == 0)
                    {

                    }

                    sh=getSharedPreferences("Signin",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sh.edit();
                    editor.putString("number","13");
                    editor.commit();
                }

                else
                {
                    sh=getSharedPreferences("Signin",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sh.edit();
                    editor.putString("number","12");
                    editor.commit();
                    finish();
                }


        }
    }

    public class Backtask123 extends AsyncTask<String,Void,String>
    {
        String roomnum;
        @Override
        protected String doInBackground(String... strings)
        {

            roomnum= strings[0];
            try
            {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/university.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("search", "UTF-8") + "=" + URLEncoder.encode(roomnum, "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String j;
                String i4 = "";
                while ((j = bufferedReader.readLine()) != null)
                {
                    i4 += j;
                }
                return i4;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {

            super.onPostExecute(s);

            s = s.replace("<br>", "\n");
            String[] separated = s.split("\n");
            int n = separated.length;

            for (int i = 0; i < n; i++)
            {
                if(i==4)
                {
                    sh=getSharedPreferences("name",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sh.edit();
                    editor.putString("facname",separated[4]);
                    editor.commit();
                }
                if (i == 3)
                {
                    sh = getSharedPreferences("desg", MODE_PRIVATE);
                    SharedPreferences.Editor ed1 = sh.edit();
                    ed1.putString("facdesg", separated[3]);
                    ed1.commit();
                }
                if (i == 5)
                {
                        sh = getSharedPreferences("email", MODE_PRIVATE);
                        SharedPreferences.Editor ed = sh.edit();
                        ed.putString("facemail", separated[5]);
                        ed.commit();
                }
            }
            Intent in=new Intent(context,myaccount.class);
            in.putExtra("v",roomnum);
            startActivity(in);
            finish();
            }
        }

    public class Backtask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String str="";
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/Allemails.php");
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

            try {
                JSONArray array = new JSONArray(s);


                int i = 0;

                for (i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    if(obj.getString("email").compareTo("")!=0)
                    theList.add(obj.getString("email"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        if(paramMenuItem.getItemId()==R.id.action_help)
        {
            HelpFragment fragment = new HelpFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main70, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        if(mDrawerToggle.onOptionsItemSelected(paramMenuItem))
        {
            return true;
        }
        return true;
    }
}
