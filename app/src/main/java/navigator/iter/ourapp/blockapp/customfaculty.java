package navigator.iter.ourapp.blockapp;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



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

import static android.view.View.INVISIBLE;


/**
 * Created by keshav on 14-03-2018.
 */

public class customfaculty extends ArrayAdapter<String>
{
    private Activity context;

    private String[] name;
    private String[] password;
    private String[] mobile;
    private String[] email;
    private String[] roomno;
    private String[] designation;


    int way=3;

    public customfaculty(Activity contex, String[] n, String[] p, String[] m, String[] e, String[] r, String[] d) {
        super(contex, R.layout.faculties_list,p);
        this.context=contex;
        this.name=n;
        this.password=p;
        this.mobile=m;
        this.email=e;
        this.roomno=r;
        this.designation=d;
    }
    
    @Override
    public View getView(final int position, @Nullable  View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View v=inflater.inflate(R.layout.faculties_list,null,true);
        final Button allow=(Button)v.findViewById(R.id.allow);
        final Button deny=(Button)v.findViewById(R.id.deny);

        if(name[position]==null)
            {
                allow.setVisibility(INVISIBLE);
                deny.setVisibility(INVISIBLE);
            }
        TextView n=(TextView)v.findViewById(R.id.namereq);

            n.setText(name[position]);
            TextView d = (TextView) v.findViewById(R.id.desigreq);
            d.setText(designation[position]);
            TextView p = (TextView) v.findViewById(R.id.passwdreq);
            p.setText(password[position]);
            TextView m = (TextView) v.findViewById(R.id.mobilereq);
            m.setText(mobile[position]);
            TextView r = (TextView) v.findViewById(R.id.roomreq);
            r.setText(roomno[position]);
            TextView e = (TextView) v.findViewById(R.id.emailreq);
            e.setText(email[position]);



        final LinearLayout falrow=(LinearLayout) v.findViewById(R.id.faculty_row);
        allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                way=1;
                allow.setVisibility(INVISIBLE);
                deny.setVisibility(INVISIBLE);
                new ExecuteTask().execute(designation[position]);
                falrow.setVisibility(INVISIBLE);

                /* Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
                name.remove(position);
                notifyDataSetChanged();
                */
                //customfaculty cs=new customfaculty(context,name,password,mobile,email,roomno,designation)
            }
        });
        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                way=2;
                allow.setVisibility(INVISIBLE);
                deny.setVisibility(INVISIBLE);
               // Toast.makeText(getContext(),designation[position],Toast.LENGTH_SHORT).show();
                new ExecuteTask().execute(designation[position]);
                falrow.setVisibility(INVISIBLE);
            }
        });
        return v;
    }

    class ExecuteTask extends AsyncTask<String,Integer,String> {

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
                String post = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8")+"&"+
                        URLEncoder.encode("way", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(way), "UTF-8");
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

            Toast.makeText(context,s,Toast.LENGTH_SHORT).show();
        }
    }

}
