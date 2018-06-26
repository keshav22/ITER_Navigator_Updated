package navigator.iter.ourapp.blockapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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


public class FinalRoomDetails extends ActionBarActivity {

    ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawer;
    TextView room,type,faculty,num,email,onlinetxt;
    String room_type[],room_faculty[];
    ImageView map,onlineimg;
    int res;
    ListView list12;
Toolbar mtoolbar;
String k;
    String s1[]=new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_room_details);
        Intent in=getIntent();
        k=in.getStringExtra("number");
        room = (TextView) findViewById(R.id.roomNum);


        //mtoolbar=(Toolbar)findViewById(R.id.nav_action);
        //setSupportActionBar(mtoolbar);
       /* mDrawer = (DrawerLayout) findViewById(R.id.final1);
        mDrawerToggle= new ActionBarDrawerToggle(this,mDrawer,R.string.Open,R.string.Close);
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();*/
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Backtask bsk=new Backtask();
        bsk.execute(k);

    }
    public void loadPhoto(View view)
    {
        Intent intent=new Intent(getApplicationContext(),Imageview_1.class);
        if(s1[0].compareTo("")!=0)
        intent.putExtra("v",String.valueOf(s1[0].substring(0,4)));
        startActivity(intent);
    }

    public class Backtask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {

            String roomnum=strings[0];
            try
            {
                URL url = new URL("http://argetim2k17.com/Sqldatabase/Cblock/university.php");
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
                int i=0;
                while((j=bufferedReader.readLine())!=null)
                {
                    s1[i]=j;
                    i++;
                }

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            room = (TextView) findViewById(R.id.roomNum);
            type = (TextView) findViewById(R.id.type);

            map = (ImageView)findViewById(R.id.imageMap);
            if(s1[0].compareTo("")!=0) {
                s1[0] = s1[0].replace("<br>", "\n");
                room.setText(s1[0].substring(0, 4));
                type.setText(s1[0]);
                s = s1[0].replace("<br>", "\n");
                String[] separated = s.split("\n");
                Backtask1 bv=new Backtask1();
                int length1=separated.length;
                if(length1>3) {
                    if (separated[3].compareTo("") != 0)
                        bv.execute(separated[3]);
                }
                Picasso.with(getApplicationContext())
                        .load("http://argetim2k17.com/Sqldatabase/Cblock/Images_Ways/" + s1[0].substring(0,4) + ".jpg")
                        .into(map);
            }

            /*faculty.setText("Name: " + s1[4]);
            email.setText("Email: " + s1[5]);
            num.setText("Number: " + s1[2]);*/


            //list12=(ListView)findViewById(R.id.listview);
            //multidata mul=new multidata(getApplicationContext(),s1);
            //list12.setAdapter(mul);

        }
    }

    public class Backtask1 extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... strings)
        {

            String roomnum=strings[0];
            try
            {
                URL url = new URL("http://argetim2k17.com/Sqldatabase/Cblock/onlineshow.php");
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

            LinearLayout linear= (LinearLayout)findViewById(R.id.lineraout);
            onlineimg=(ImageView)findViewById(R.id.image6);
            onlinetxt=(TextView)findViewById(R.id.onlinetext);
            if(s.compareTo("1")==0)
            {
                linear.setVisibility(View.VISIBLE);
                onlineimg.setVisibility(View.VISIBLE);
                onlinetxt.setVisibility(View.VISIBLE);

            }
            else
            {
                linear.setVisibility(View.INVISIBLE);
                onlineimg.setVisibility(View.INVISIBLE);
                onlinetxt.setVisibility(View.INVISIBLE);
            }

        }
    }
  /*  @Override
    public boolean onCreateOptionsMenu(Menu paramMenu)
    {
        getMenuInflater().inflate(R.menu.menu_faculty_name_home, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem paramMenuItem)
    {
        if (paramMenuItem.getItemId()==R.id.action_help)
        {
        HelpFragment fragment= new HelpFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main6, fragment); // fragment container id in first parameter is the  container(Main layout id) of Activity
        transaction.addToBackStack(null);  // this will manage backstack
        transaction.commit();}
        if(mDrawerToggle.onOptionsItemSelected(paramMenuItem))
        {
            return true;
        }
        return true;
    }*/


}

/*class multidata extends ArrayAdapter<String>
{
    Context c;
    String[] s=new String[100];
    int i=0;
    public multidata(@NonNull Context context,String[] kk) {
        super(context,R.layout.rowfiles,R.id.roomNum,kk);
        c=context;
        s= kk;
        i=0;
    }



        TextView room = (TextView) findViewById(R.id.roomNum);
        TextView type = (TextView) findViewById(R.id.type);
        TextView faculty = (TextView) row.findViewById(R.id.faculty);
        TextView email=(TextView)row.findViewById(R.id.email);
        TextView num=(TextView)row.findViewById(R.id.number);
        ImageView map = (ImageView) row.findViewById(R.id.imageMap);
        room.setText(s[i+1]);
        type.setText("Designation: " + s[i+3]);
        faculty.setText("Name: " + s[i+4]);
        email.setText("Email: " + s[i+5]);
        num.setText("Number: " + s[i+2]);
        Picasso.with(c)
                .load("http://argetim2k17.com/Sqldatabase/Cblock/Images_Ways/" + s[i+0] + ".jpg")
                .into(map);
        i=i+6;
        return row;
    }*/
//}
