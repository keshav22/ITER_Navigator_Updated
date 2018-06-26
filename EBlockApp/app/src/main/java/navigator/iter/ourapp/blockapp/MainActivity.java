package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends ActionBarActivity {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;
    NavigationView navigationView;
    Toolbar mtoolbar;
    SharedPreferences sh,sh1,sh5;
    String facdesg="";
    @Override
    protected void onResume() {
        super.onResume();
        sh=getSharedPreferences("Signin",getApplicationContext().MODE_PRIVATE);
        String n=sh.getString("number","");
        sh=getSharedPreferences("name",MODE_PRIVATE);
        String name1=sh.getString("facname","");
        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
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
        if(n.compareTo("12")==0)
        {
            Toast.makeText(getApplicationContext(),"Sign in failed!!",Toast.LENGTH_SHORT).show();
            sh=getSharedPreferences("Signin",MODE_PRIVATE);
            SharedPreferences.Editor editor=sh.edit();
            editor.putString("number","0");
            editor.commit();
        }

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
        sh=getSharedPreferences("name",MODE_PRIVATE);
        String name1=sh.getString("facname","");

        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
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

    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable()
    {
        return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }
    Button btnFindByRoom,btnFindByName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (!isNetworkAvailable())
        {
            Toast.makeText(getApplicationContext(), "Turn On Internet",Toast.LENGTH_SHORT).show();
        }

        
        btnFindByRoom = (Button) findViewById(R.id.buttonFindByNum);
        btnFindByName = (Button) findViewById(R.id.buttonFindByName);
        mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);
        
        navigationView=(NavigationView)findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);

       mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
       mDrawerToggle= new ActionBarDrawerToggle(this,mDrawer,R.string.Open,R.string.Close);
       mDrawer.addDrawerListener(mDrawerToggle);
       mDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*mDrawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };*/
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
                            transaction.replace(R.id.main3, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        break;

                    case R.id.requests:
                        if(!isNetworkAvailable())
                        {
                         Toast.makeText(getApplicationContext(),"Turn on Internet",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
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

    }


    public void roomNumber(View view)
    {
        Intent intent = new Intent(getApplicationContext(),RoomNumberHome.class);
        startActivity(intent);
    }

    public void facultyName(View view)
    {
        Intent intent = new Intent(getApplicationContext(),FacultyNameHome.class);
        startActivity(intent);
    }
@Override
    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_faculty_name_home, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        if(paramMenuItem.getItemId()==R.id.action_help)
        {
            HelpFragment fragment = new HelpFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main3, fragment);
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
