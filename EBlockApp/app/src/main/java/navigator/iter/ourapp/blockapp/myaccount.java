package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;



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

public class myaccount extends AppCompatActivity {

    EditText name,room,design,email,numb;
    SharedPreferences sh;
    Button editbn;
    String Sno;
    String name12;
    String name2="";
    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;
    Toolbar mtoolbar;
    ToggleButton toggleButton;
    NavigationView navigationView;
    String facdesg="";
    int conn=0;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.*[a-z]*";

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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onResume() {
        super.onResume();
        sh=getSharedPreferences("Signin",getApplicationContext().MODE_PRIVATE);
        String n=sh.getString("number","");
        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
        sh=getSharedPreferences("name",MODE_PRIVATE);
        String name1=sh.getString("facname","");

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
            //Toast.makeText(getApplicationContext(),facdesg,Toast.LENGTH_SHORT).show();
            if((facdesg.compareTo("head of department")==0) || (facdesg.compareTo("hod")==0))
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
    protected void onStart()
    {
       // Toast.makeText(getApplicationContext(),"Oncreate1 invoked",Toast.LENGTH_SHORT).show();
        super.onStart();


        sh=getSharedPreferences("Signin",getApplicationContext().MODE_PRIVATE);
        String n=sh.getString("number","");
        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
        sh=getSharedPreferences("name",MODE_PRIVATE);
        String name1=sh.getString("facname","");
        if(n.compareTo("13")==0)
        {
            Toast.makeText(getApplicationContext(),"Signin success!!",Toast.LENGTH_SHORT).show();
            sh=getSharedPreferences("Signin",this.MODE_PRIVATE);
            SharedPreferences.Editor editor1=sh.edit();
            editor1.putString("number","1");
            editor1.commit();
        }
        sh=getSharedPreferences("email",MODE_PRIVATE);
        String email1=sh.getString("facemail","");

        Backtask bsk=new Backtask();
        bsk.execute(email1);
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
            //Toast.makeText(getApplicationContext(),facdesg,Toast.LENGTH_SHORT).show();
            if((facdesg.compareTo("head of department")==0) || (facdesg.compareTo("hod")==0))
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myaccount);
        Intent in=getIntent();
        name12=in.getStringExtra("v");
       // Toast.makeText(getApplicationContext(),"Oncreate invoked",Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        sh=getSharedPreferences("email",MODE_PRIVATE);
        final String email1=sh.getString("facemail","");
        Backtask bsk=new Backtask();
        bsk.execute(email1);
        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.myacctdraw);
        mDrawerToggle= new ActionBarDrawerToggle(this,mDrawer,R.string.Open,R.string.Close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        // These lines are needed to display the top-left hamburger button
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                            transaction.replace(R.id.main51, fragment);
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
        name=(EditText)findViewById(R.id.name);
        room=(EditText)findViewById(R.id.Roomno);
        email=(EditText)findViewById(R.id.email10);
        design=(EditText)findViewById(R.id.desig);
        numb=(EditText)findViewById(R.id.number50);
        editbn=(Button)findViewById(R.id.editbtn);
        toggleButton=(ToggleButton)findViewById(R.id.toggleonline);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(String.valueOf(compoundButton.isChecked()).compareTo("true")==0) {
                    Toast.makeText(getApplicationContext(), "online", Toast.LENGTH_SHORT).show();
                    conn=1;
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "offline", Toast.LENGTH_SHORT).show();
                    conn=0;
                }
                Backtask1 bsk=new Backtask1();
                bsk.execute(name12);
            }
        });
       // Backtask bsk=new Backtask();
       // bsk.execute(name12);

        editbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (name.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getApplicationContext(), "Enter Your Name", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                } else if (numb.length() != 10) {
                    Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
                    numb.requestFocus();
                } else if (room.getText().toString().compareTo("") == 0  ) {
                    Toast.makeText(getApplicationContext(), "Enter a valid Room no", Toast.LENGTH_SHORT).show();
                    room.requestFocus();
                } else if (design.getText().toString().compareTo("") == 0) {
                    Toast.makeText(getApplicationContext(), "Enter Designation", Toast.LENGTH_SHORT).show();
                    design.requestFocus();
                } else if ((email.getText().toString().matches(emailPattern) && email.getText().toString().length() > 0) == false) {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }
                else
                {
                    ExecuteTask1 ex=new ExecuteTask1();
                    ex.execute("4",name.getText().toString(),numb.getText().toString(),room.getText().toString(),design.getText().toString(),email.getText().toString(),Sno);
                }

            }
        });
        name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    room.requestFocus();
                }
                return true;

            }
        });
        room.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    design.requestFocus();
                }
                return true;

            }
        });
        design.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    numb.requestFocus();
                }
                return true;

            }
        });
        numb.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    email.requestFocus();
                }
                return true;
            }
        });
        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE)
                {
                    editbn.performClick();
                }
                return true;

            }
        });

    Backtask12 bvn=new Backtask12();
    bvn.execute(name12);

    }

    class ExecuteTask1 extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            try {


                URL url=new URL("http://argetim2k17.com/ITER_Navigator/del_ins.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post="";
                post = URLEncoder.encode("room", "UTF-8") + "=" + URLEncoder.encode(strings[3], "UTF-8") + "&" +
                        URLEncoder.encode("desg", "UTF-8") + "=" + URLEncoder.encode(strings[4], "UTF-8") + "&" +
                        URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8") + "&" +
                        URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(strings[2], "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(strings[5], "UTF-8") + "&" +
                        URLEncoder.encode("sno", "UTF-8") + "=" + URLEncoder.encode(strings[6], "UTF-8") + "&" +
                        URLEncoder.encode("way", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8");
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
            if(s.compareTo("Modified")==0)
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public class Backtask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {

            String roomnum=strings[0];
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
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String j;
                String i4="";
                while((j=bufferedReader.readLine())!=null)
                {
                    i4 +=j;
                }
              return i4;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s)
        {

            super.onPostExecute(s);

            name=(EditText)findViewById(R.id.name);
            room=(EditText)findViewById(R.id.Roomno);
            email=(EditText)findViewById(R.id.email10);
            design=(EditText)findViewById(R.id.desig);
            numb=(EditText)findViewById(R.id.number50);
            s=s.replace("<br>","\n");
            String[] separated = s.split("\n");
            int n=separated.length;

            for(int i=0;i<n;i++)
            {
                if(i==0)
                {
                    Sno=separated[0];
                }
                if(i==1)
                    room.setText(separated[1]);

                if(i==2)
                    numb.setText(separated[2]);

                if(i==3)
                {
                    design.setText(separated[3]);
                    sh=getSharedPreferences("desg",MODE_PRIVATE);
                    SharedPreferences.Editor ed1=sh.edit();
                    ed1.putString("facdesg",separated[3]);
                    ed1.commit();
                }
                if(i==4) {
                    name.setText(separated[4]);

                }
                if(i==5)
                {
                    email.setText(separated[5]);
                    sh=getSharedPreferences("email",MODE_PRIVATE);
                    SharedPreferences.Editor ed=sh.edit();
                    ed.putString("facemail",separated[5]);
                    ed.commit();
                    navigationView=(NavigationView)findViewById(R.id.navigation);
                    View header=navigationView.getHeaderView(0);
                    TextView email=(TextView)header.findViewById(R.id.emailtext);
                    email.setText(separated[5]);
                }
            }
        }
    }
    public class Backtask1 extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {

            String roomnum=strings[0];
            if(conn==1)
            {
            try
            {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/online.php");
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
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String j;
                String i4="";
                while((j=bufferedReader.readLine())!=null)
                {
                    //s1[i]=j;
                    i4 +=j;
                }
                return i4;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
            else if(conn==0)
            {
                try
                {
                    URL url = new URL("http://argetim2k17.com/ITER_Navigator/offline.php");
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
                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                    String j;
                    String i4="";
                    while((j=bufferedReader.readLine())!=null)
                    {
                        //s1[i]=j;
                        i4 +=j;
                    }
                    return i4;
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        if(paramMenuItem.getItemId()==R.id.action_help) {
            HelpFragment fragment = new HelpFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main51, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
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
    public class Backtask12 extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {

            String roomnum=strings[0];
            try
            {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/onlineshow.php");
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
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String j;
                String k1="";
                while((j=bufferedReader.readLine())!=null)
                {
                    k1=k1+j;
                }
                return k1;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.compareTo("1")==0)
            {
               toggleButton.setChecked(true);

            }
            else
            {
                toggleButton.setChecked(false);
            }

        }
    }

}
