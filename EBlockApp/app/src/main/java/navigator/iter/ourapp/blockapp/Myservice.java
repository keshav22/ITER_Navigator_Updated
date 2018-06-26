package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;



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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kesha on 25-03-2018.
 */


public class Myservice extends Service {
    Handler mHandler;
    static final int UPDATE_INTERNAL=100;

    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;
    SharedPreferences sh;

    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable()
    {
        return ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }
    final class Mythreadclass implements  Runnable
    {
        int service_id;
        Mythreadclass (int service_id)
        {
            this.service_id=service_id;
        }

        @Override
        public void run() {
            timer = new Timer();
            timerTask = new TimerTask() {
                public void run() {
                    if(isNetworkAvailable()) {
                        Backtask2 b = new Backtask2();
                        b.execute(" ");
                    }
                }
            };
            timer.schedule(timerTask, 1000, 1000);

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

       /* sh=getSharedPreferences("signin_1",MODE_PRIVATE);
        String name1=sh.getString("cnt","");
        if(name1.compareTo("1")==0)
        {
            SharedPreferences.Editor editor=sh.edit();
            editor.putString("cnt","2");/*/
        Thread thread= new Thread(new Mythreadclass(startId));
            thread.start();




        //}
        /* int i=0;;
        synchronized (this)
        {
            while(i<10)
            {
                try {
                    wait(1500);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }*/


        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        //Toast.makeText(this,"Service destroyed",Toast.LENGTH_SHORT).show();
        timer.cancel();
        timer = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class Backtask2 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {

            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/cnt.txt");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
                String j;

                return_text="";
                while((j=bufferedReader.readLine())!=null)
                {
                    return_text=return_text+j;
                }

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return return_text;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

           //Toast.makeText(Myservice.this,s,Toast.LENGTH_SHORT).show();

            if (s.compareTo("1")!= 0 && s!=null &&s.compareTo("")!=0  && s.compareTo(" ")!=0)
            {
                Intent intent = new Intent(Myservice.this, Request.class);
                PendingIntent pIntent = PendingIntent.getActivity(Myservice.this, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(Myservice.this)

                        .setSmallIcon(R.drawable.back_image)

                        .setTicker("Requests")

                        .setContentTitle("New Request Available")

                        .setContentText("Click to view")

                        .setContentIntent(pIntent)

                        .setAutoCancel(true);

                // Create Notification Manager
                NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                // Build Notification with Notification Manager
                notificationmanager.notify(0, builder.build());
               Backtask3 bb=new Backtask3();
                bb.execute(" ");

            }
        }
    }

    public class Backtask3 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/cntdefault.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("cnt", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
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
                return_text=sb.toString();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return return_text ;
        }


    }

}
