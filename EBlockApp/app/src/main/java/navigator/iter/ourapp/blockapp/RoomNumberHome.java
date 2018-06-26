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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.lang.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class RoomNumberHome extends ActionBarActivity {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;
    Button btnSearch;
    EditText roomNum;
    String roomnum;
    SharedPreferences sh;
    Spinner s;
    ListView l;
    public int[] room;
    NavigationView navigationView;
    Toolbar mtoolbar;
    String facdesg = "";

    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable() {
        return ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        sh = getSharedPreferences("Signin", getApplicationContext().MODE_PRIVATE);
        String n = sh.getString("number", "");
        navigationView = (NavigationView) findViewById(R.id.navigation);
        View header = navigationView.getHeaderView(0);
        sh = getSharedPreferences("name", MODE_PRIVATE);
        String name1 = sh.getString("facname", "");
        String name2 = "";
        s = (Spinner) findViewById(R.id.text2);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roomType));
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(aa);
        sh = getSharedPreferences("email", MODE_PRIVATE);
        String email1 = sh.getString("facemail", "");
        if (name1.compareTo("") != 0) {
            TextView name14 = (TextView) header.findViewById(R.id.nametext);
            name14.setText(name1);
            TextView email = (TextView) header.findViewById(R.id.emailtext);
            email.setText(email1);
        }
        sh = getSharedPreferences("desg", MODE_PRIVATE);
        String facdesg = sh.getString("facdesg", "");
        facdesg = facdesg.trim().toLowerCase();

        if (n.compareTo("1") == 0) {
            if (facdesg.compareTo("head of department") == 0 || facdesg.compareTo("hod") == 0) {
                Menu m = navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(true);
                m.findItem(R.id.databaseaccess).setVisible(true);
                m.findItem(R.id.passwdchange).setVisible(true);
            } else {
                Menu m = navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(false);
                m.findItem(R.id.databaseaccess).setVisible(false);
                m.findItem(R.id.passwdchange).setVisible(true);
            }
        } else {
            Menu m = navigationView.getMenu();
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
        sh = getSharedPreferences("Signin", getApplicationContext().MODE_PRIVATE);
        String n = sh.getString("number", "");
        navigationView = (NavigationView) findViewById(R.id.navigation);
        View header = navigationView.getHeaderView(0);
        sh = getSharedPreferences("name", MODE_PRIVATE);
        String name1 = sh.getString("facname", "");
        String name2 = "";
        sh = getSharedPreferences("email", MODE_PRIVATE);
        String email1 = sh.getString("facemail", "");
        if (name1.compareTo("") != 0) {
            TextView name14 = (TextView) header.findViewById(R.id.nametext);
            name14.setText(name1);
            TextView email = (TextView) header.findViewById(R.id.emailtext);
            email.setText(email1);
        }
        sh = getSharedPreferences("desg", MODE_PRIVATE);
        facdesg = sh.getString("facdesg", "");
        facdesg = facdesg.trim().toLowerCase();

        if (n.compareTo("1") == 0) {
            if (facdesg.compareTo("head of department") == 0 || facdesg.compareTo("hod") == 0) {
                Menu m = navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(true);
                m.findItem(R.id.databaseaccess).setVisible(true);
                m.findItem(R.id.passwdchange).setVisible(true);
            } else {
                Menu m = navigationView.getMenu();
                m.findItem(R.id.Signin).setVisible(false);
                m.findItem(R.id.myaccount).setVisible(true);
                m.findItem(R.id.Signout).setVisible(true);
                m.findItem(R.id.requests).setVisible(false);
                m.findItem(R.id.databaseaccess).setVisible(false);
                m.findItem(R.id.passwdchange).setVisible(true);
            }
        } else {
            Menu m = navigationView.getMenu();
            m.findItem(R.id.myaccount).setVisible(false);
            m.findItem(R.id.databaseaccess).setVisible(false);
            m.findItem(R.id.Signout).setVisible(false);
            m.findItem(R.id.requests).setVisible(false);
            m.findItem(R.id.passwdchange).setVisible(false);
            m.findItem(R.id.Signin).setVisible(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.menu_faculty_name_home, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_number_home);
        mtoolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mtoolbar);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.Signin:
                        Intent intent = new Intent(getApplicationContext(), singin_1.class);
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

                        if (facdesg.compareTo("hod") == 0 || facdesg.compareTo("head of department") == 0) {
                            Intent in1 = new Intent(getApplicationContext(), Myservice.class);
                            stopService(in1);
                        }
                        sh = getSharedPreferences("Signin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("number", "0");
                        editor.commit();
                        sh = getSharedPreferences("name", MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = sh.edit();
                        editor1.putString("facname", "name");
                        editor1.commit();

                        sh = getSharedPreferences("email", MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = sh.edit();
                        editor2.putString("facemail", "email");
                        editor2.commit();
                        sh = getSharedPreferences("desg", MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = sh.edit();
                        editor2.putString("facdesg", "desg");
                        editor2.commit();
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                        finish();

                        break;
                    case R.id.myaccount:
                        if (!isNetworkAvailable()) {
                            Toast.makeText(getApplicationContext(), "Turn on Internet", Toast.LENGTH_SHORT).show();
                        } else {
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
                            transaction.replace(R.id.main4, fragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                        }
                        break;

                    case R.id.requests:
                        if (!isNetworkAvailable()) {
                            Toast.makeText(getApplicationContext(), "Turn on Internet", Toast.LENGTH_SHORT).show();
                        } else {
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
        roomNum = (EditText) findViewById(R.id.roomInput);
        btnSearch = (Button) findViewById(R.id.searchButton);
        room = getResources().getIntArray(R.array.roomArray);
        mDrawer = (DrawerLayout) findViewById(R.id.facultynum);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.Open, R.string.Close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        // These lines are needed to display the top-left hamburger button
        //getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        roomNum.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    btnSearch.performClick();
                }
                return true;
            }
        });
    }

    public void searchRoom(View v)

    {
        if (!isNetworkAvailable()) {
            Toast.makeText(getApplicationContext(), "Turn on Internet", Toast.LENGTH_SHORT).show();
        } else {

            if (roomNum.getText().toString().length() == 3) {

                String block = s.getSelectedItem().toString();
                roomnum = block + roomNum.getText().toString();
                ExecuteTask ex=new ExecuteTask();
                ex.execute(roomnum);

            } else {
                Toast.makeText(getApplicationContext(), "Enter a valid room number", Toast.LENGTH_SHORT).show();
            }
            //OLD
        /*university1 uni = new university1(this, 1);
        if (roomNum.getText().toString().compareTo("") != 0) {
            String block = s.getSelectedItem().toString();
            String roomnum = block + roomNum.getText().toString();
            uni.execute(roomnum);
        } else
            Toast.makeText(getApplicationContext(), "Enter any number like 002", Toast.LENGTH_SHORT).show();
            */
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        if (paramMenuItem.getItemId() == R.id.action_help) {
            HelpFragment fragment = new HelpFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.main4, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        if (mDrawerToggle.onOptionsItemSelected(paramMenuItem)) {
            return true;
        }
        return true;
    }

    class ExecuteTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL("http://argetim2k17.com/ITER_Navigator/found.php");
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
            } catch (Exception e) {

            }

            return null;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.compareTo("Found")==0)
            {
                Intent in = new Intent(RoomNumberHome.this, newfinalroomdetails.class);
                in.putExtra("number",roomnum);
                startActivity(in);
            }
            else
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();

        }
    }
}
    /*public void searchRoom1(View view){
        if(roomNum.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Please Enter a number!", Toast.LENGTH_SHORT).show();
        }
        else {
            final int x = Integer.parseInt(roomNum.getText().toString().trim());
            java.util.Arrays.sort(room);
            final int res = java.util.Arrays.binarySearch(room, x);
            if (res < 0) {
                Toast.makeText(getApplicationContext(), "Room E-" + x + " doesn't exist! try again!", Toast.LENGTH_LONG).show();
                roomNum.setText("");
            } else {
                roomNum.setText("");
                Intent intent = new Intent(getApplicationContext(), FinalRoomDetails.class);
                intent.putExtra("res", String.valueOf(res));
                intent.putExtra("x", String.valueOf(x));
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_room_number_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

