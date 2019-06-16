package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
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
import java.io.File;
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


public class FacultyNameHome extends ActionBarActivity {
    AutoCompleteTextView text1;
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;
    Toolbar mtoolbar;
    SharedPreferences sh;
    String facdesg="";
    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    String names[];
    int roomno1[];
    int i=0;
    Context context;
    Button b1;

    ArrayList<String> theList = new ArrayList();
    NavigationView navigationView;
    @Override
    protected void onResume() {
        super.onResume();
        sh=getSharedPreferences("Signin",getApplicationContext().MODE_PRIVATE);
        String n=sh.getString("number","");
        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
        sh=getSharedPreferences("name",MODE_PRIVATE);
        String name1=sh.getString("facname","");
        String name2="";
        sh=getSharedPreferences("email",MODE_PRIVATE);
        String email1=sh.getString("facemail","");
        if(name1.compareTo("")!=0)
        {
            TextView name14=(TextView)header.findViewById(R.id.nametext);
            name14.setText(name1);
            TextView email=(TextView)header.findViewById(R.id.emailtext);
            email.setText(email1);
        }
        sh=getSharedPreferences("desg",MODE_PRIVATE);
        String facdesg=sh.getString("facdesg","");
        facdesg=facdesg.trim().toLowerCase();

        if (n.compareTo("1")==0)
        {
            if( facdesg.compareTo("head of department")==0 ||facdesg.compareTo("hod")==0)
            {
                Menu m = navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(true);
                m.findItem(R.id.databaseaccess).setVisible(true);
                m.findItem(R.id.passwdchange).setVisible(true);
            }
            else
            {
                Menu m=navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(false);
                m.findItem(R.id.databaseaccess).setVisible(false);
                m.findItem(R.id.passwdchange).setVisible(true);
            }
        }
        else
        {
            Menu m=navigationView.getMenu();
            m.findItem(R.id.myaccount).setVisible(false);
            m.findItem(R.id.databaseaccess).setVisible(false);
            m.findItem(R.id.Signout).setVisible(false);
            m.findItem(R.id.requests).setVisible(false);
            m.findItem(R.id.passwdchange).setVisible(false);
            m.findItem(R.id.Signin).setVisible(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sh=getSharedPreferences("Signin",getApplicationContext().MODE_PRIVATE);
        String n=sh.getString("number","");
        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
        sh=getSharedPreferences("name",MODE_PRIVATE);
        String name1=sh.getString("facname","");
        String name2="";
        sh=getSharedPreferences("email",MODE_PRIVATE);
        String email1=sh.getString("facemail","");
        if(name1.compareTo("")!=0)
        {
            TextView name14=(TextView)header.findViewById(R.id.nametext);
            name14.setText(name1);
            TextView email=(TextView)header.findViewById(R.id.emailtext);
            email.setText(email1);
        }
        sh=getSharedPreferences("desg",MODE_PRIVATE);
        facdesg=sh.getString("facdesg","");
        facdesg=facdesg.trim().toLowerCase();

        if (n.compareTo("1")==0)
        {
            if( facdesg.compareTo("head of department")==0 ||facdesg.compareTo("hod")==0)
            {
                Menu m = navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(true);
                m.findItem(R.id.databaseaccess).setVisible(true);
                m.findItem(R.id.passwdchange).setVisible(true);
            }
            else
            {
                Menu m=navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(false);
                m.findItem(R.id.databaseaccess).setVisible(false);
                m.findItem(R.id.passwdchange).setVisible(true);
            }
        }
        else
        {
            Menu m=navigationView.getMenu();
            m.findItem(R.id.myaccount).setVisible(false);
            m.findItem(R.id.databaseaccess).setVisible(false);
            m.findItem(R.id.Signout).setVisible(false);
            m.findItem(R.id.requests).setVisible(false);
            m.findItem(R.id.passwdchange).setVisible(false);
            m.findItem(R.id.Signin).setVisible(true);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_faculty_name_home, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }
    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable()
    {
        return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_name_home);
        b1=(Button)findViewById(R.id.search_name);
        text1=(AutoCompleteTextView)findViewById(R.id.roomInput);
        context=this;

        // These lines are needed to display the top-left hamburger button
        //getSupportActionBar().setHomeButtonEnabled(true);
        navigationView=(NavigationView)findViewById(R.id.navigation);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.Signin:
                        Intent intent=new Intent(getApplicationContext(),singin_1.class);
                        startActivity(intent);
                        break;
                    case R.id.Signup:
                        if(!isNetworkAvailable())
                        {
                            Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            intent = new Intent(getApplicationContext(), Signup.class);
                            startActivity(intent);
                        }
                        break;

                    case R.id.Signout:

                        if(facdesg.compareTo("hod")==0 || facdesg.compareTo("head of department")==0)
                        {
                            Intent in1=new Intent(getApplicationContext(),Myservice.class);
                            stopService(in1);
                        }
                        sh=getSharedPreferences("Signin",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sh.edit();
                        editor.putString("number","0");
                        editor.commit();
                        sh=getSharedPreferences("name",MODE_PRIVATE);
                        SharedPreferences.Editor editor1=sh.edit();
                        editor1.putString("facname","name");
                        editor1.commit();
                        sh=getSharedPreferences("email",MODE_PRIVATE);
                        SharedPreferences.Editor editor2=sh.edit();
                        editor2.putString("facemail","email");
                        editor2.commit();
                        sh=getSharedPreferences("desg",MODE_PRIVATE);
                        SharedPreferences.Editor editor3=sh.edit();
                        editor2.putString("facdesg","desg");
                        editor2.commit();
                        Intent it=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(it);
                        finish();
                        break;
                    case R.id.myaccount:
                        if(!isNetworkAvailable())
                        {
                            Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            sh = getSharedPreferences("name", MODE_PRIVATE);
                            String name1 = sh.getString("facname", "");
                            Intent in = new Intent(getApplicationContext(), myaccount.class);
                            in.putExtra("v", name1);
                            startActivity(in);
                        }
                        break;

                    case R.id.passwdchange:
                        if(!isNetworkAvailable())
                        {
                            Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            passwordfragment fragment = new passwordfragment(0);
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.main5, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        break;

                    case R.id.requests:
                        if(!isNetworkAvailable())
                        {
                            Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent in = new Intent(getApplicationContext(), Request.class);
                            startActivity(in);
                        }

                        break;

                    case R.id.databaseaccess:
                        if(!isNetworkAvailable())
                        {
                            Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent in = new Intent(getApplicationContext(), Database.class);
                            startActivity(in);
                        }

                        break;

                    case R.id.impdetails:
                        Toast.makeText(getApplicationContext(),"Comming Soon",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Report:
                        Toast.makeText(getApplicationContext(),"Comming Soon",Toast.LENGTH_SHORT).show();
                        break;

                }
                mDrawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        theList.clear();
        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.facultyname);
        mDrawerToggle= new ActionBarDrawerToggle(this,mDrawer,R.string.Open,R.string.Close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //deleteCache(getApplicationContext());
        //university1 uni=new university1(context,2);
        //uni.execute(text1.getText().toString());
        Backtask bsk=new Backtask();
        bsk.execute("1");
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
        text1.setAdapter(adapter);
        b1=(Button)findViewById(R.id.search_name);
        text1.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_SEND)
                {
                   b1.performClick();
                }
                return true;
            }
        });
    }


    public void searchRoom_1(View v)
    {
        if(!isNetworkAvailable())
        {
            Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
        }
        else {
            //NEW

            if(theList.contains(text1.getText().toString()) && text1.getText().length()>0)
            {
                Intent in=new Intent(this,newfinalroomdetails.class);
                text1=(AutoCompleteTextView)findViewById(R.id.roomInput);
                in.putExtra("number",text1.getText().toString());
                startActivity(in);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Select name from drop down list",Toast.LENGTH_SHORT).show();
            }


            /*OLD
            university1 uni = new university1(this, 1);
            uni.execute(text1.getText().toString());
            */
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

            try {
                JSONArray array = new JSONArray(s);


                int i = 0;

                for (i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    if(obj.getString("name").compareTo("null")!=0)
                    theList.add(obj.getString("name"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        if(paramMenuItem.getItemId()==R.id.action_help) {
            HelpFragment fragment = new HelpFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main5, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
            transaction.addToBackStack(null);  // this will manage backstack
            transaction.commit();
            return true;
        }
        if(mDrawerToggle.onOptionsItemSelected(paramMenuItem))
        {
            return true;
        }
        return true;
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {}
    }

    public static boolean deleteDir(File dir)
    {
        if (dir != null && dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }



}
