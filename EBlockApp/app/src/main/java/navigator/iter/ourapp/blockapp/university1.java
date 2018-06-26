package navigator.iter.ourapp.blockapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
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
import java.lang.*;

/**
 * Created by kesha on 22-12-2017.
 */

public class university1 extends AsyncTask<String,Void,String>
{
    Context context;
    AlertDialog alertDialog;
    int con;
    public university1(Context context1,int con1)
    {
        context=context1;
        con=con1;
    }
    public int getCon()
    {
        return con;
    }
    @Override
    protected String doInBackground(String... strings)
    {
        if (con == 1)
        {
            String roomnum = strings[0];
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
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String str;
                String l = "";

                while ((str = bufferedReader.readLine()) != null)
                {
                    l += str;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                onPostExecute1(l,roomnum);
                return l;
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException i)
            {
                i.printStackTrace();
            }

        }

        if(con==2)
        {
            String namefac=strings[0];
            try
            {
                URL url = new URL("http://argetim2k17.com/Sqldatabase/Cblock/namessearchnew.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("search", "UTF-8") + "=" + URLEncoder.encode(namefac, "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                /*String str;
                String l = "";
                while ((str = bufferedReader.readLine()) != null)
                {
                    l += str;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                onPostExecute1(l);*/
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException i)
            {
                i.printStackTrace();
            }
        }

        if(con==3)
        {
            String name=strings[0];
            String passwd=strings[1];
            try {
                URL url = new URL("http://argetim2k17.com/Sqldatabase/Cblock/passwdmatch.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")+"&"+
                        URLEncoder.encode("passwd", "UTF-8") + "=" + URLEncoder.encode(passwd, "UTF-8");
                bufferedWriter.write(post);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String str;
                String l = "";
                while ((str = bufferedReader.readLine()) != null)
                {
                    l += str;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                onPostExecute12(l,name);

            }
            catch (MalformedURLException e)
                {
                    e.printStackTrace();
                }
            catch (IOException i)
                {
                    i.printStackTrace();
                }

        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if(con==2)
        {
            Toast.makeText(context,"Enter a valid data",Toast.LENGTH_SHORT).show();
        }
        con=1;
    }



    protected void onPostExecute1(String ans, String num)
    {

        if (ans.compareTo("") != 0)
        {
      Intent in= new Intent(this.context, FinalRoomDetails.class);
      in.putExtra("number",num);
      this.context.startActivity(in);
        }
        else {
            con++;
        }

    }

    protected void onPostExecute12(String ans,String name)
    {
        if (ans.compareTo("Login Success") == 0)
        {
                Intent in=new Intent(context,myaccount.class);
                in.putExtra("v",name);
                this.context.startActivity(in);
        }
        else
        {

            con++;
        }

    }
}
