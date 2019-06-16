package navigator.iter.ourapp.blockapp;

import android.content.Context;
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



class university extends AsyncTask<String, Void, String>
{
  int con;
  Context context;
  
  public university(Context Context, int con1)
  {
    context = Context;
    con = con1;

  }

@Override
  protected String doInBackground(String... params)
  {
      if (con == 1)
      {
          String roomnum = params[0];
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
              onPostExecute1(l);
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
   /* if (this.con == 2)
    {
      paramArrayOfString = paramArrayOfString[0];
      try
      {
        localHttpURLConnection = (HttpURLConnection)new URL("http://argetim2k17.com/Sqldatabase/Cblock/namesearch.php").openConnection();
        localHttpURLConnection.setRequestMethod("POST");
        localHttpURLConnection.setDoOutput(true);
        localHttpURLConnection.setDoInput(true);
        localObject1 = localHttpURLConnection.getOutputStream();
        localObject2 = new BufferedWriter(new OutputStreamWriter((OutputStream)localObject1, "UTF-8"));
        ((BufferedWriter)localObject2).write(URLEncoder.encode("search", "UTF-8") + "=" + URLEncoder.encode(paramArrayOfString, "UTF-8"));
        ((BufferedWriter)localObject2).flush();
        ((BufferedWriter)localObject2).close();
        ((OutputStream)localObject1).close();
        new BufferedReader(new InputStreamReader(localHttpURLConnection.getInputStream()));
        return null;
      }
      catch (MalformedURLException paramArrayOfString)
      {
        for (;;)
        {
          paramArrayOfString.printStackTrace();
        }
      }
      catch (IOException paramArrayOfString)
      {
        for (;;)
        {
          paramArrayOfString.printStackTrace();
        }
      }
    }
    return null;
  }*/
  
  protected void onPostExecute1(String ans)
  {
    if (ans.compareTo("Found") == 0)
    {
        Toast.makeText(context,"Found", Toast.LENGTH_SHORT).show();
      /*Intent in= new Intent(this.context, FinalRoomDetails.class);
     // in.putExtra("number",0);
      this.context.startActivity(in);*/
    }
    else
    {

    }
  }
}


