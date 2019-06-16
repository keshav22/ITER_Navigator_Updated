package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.Fragment;
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

import static android.content.Context.MODE_PRIVATE;


@SuppressLint("ValidFragment")
public class passwordfragment extends Fragment {
    View v;
    EditText ed1,ed2,otppass;
    SharedPreferences sh;
    String email;
    int m=1;
    NavigationView navigationView;
    int mode;

    public passwordfragment(int mode)
    {
        this.mode=mode;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(m==0) {
            Toast.makeText(v.getContext(),"Sign in now",Toast.LENGTH_SHORT).show();
            getActivity().recreate();
            getActivity().onBackPressed();
            //getActivity().finish();
           //getActivity().findViewById(R.id.Signout).performClick();
        }


    }

    @Nullable
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle)
    {
        v=paramLayoutInflater.inflate(R.layout.fragment_passwordfragment, paramViewGroup, false);
        Button sub=(Button)v.findViewById(R.id.btnpass);
        ed1=(EditText)v.findViewById(R.id.passwden);
        ed2=(EditText)v.findViewById(R.id.passwden1);
        otppass=(EditText)v.findViewById(R.id.otppass);

        sh=v.getContext().getSharedPreferences("email",MODE_PRIVATE);
        email=sh.getString("facemail","");



        Backtask2 bc=new Backtask2();
        bc.execute();
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otppass.length()==0)
                {
                    otppass.requestFocus();
                }
                else if(ed1.getText().toString().compareTo(ed2.getText().toString())==0 && ed1.length()>8)
                {
                    //Toast.makeText(v.getContext(),"Pass match",Toast.LENGTH_SHORT).show();
                    Backtask1 bc1=new Backtask1();
                    bc1.execute();
                }
                else
                {
                    ed2.requestFocus();
                }


            }
        });

        return v;
    }

    public class Backtask1 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/otpmatch.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")+"&"+
                        URLEncoder.encode("otp", "UTF-8") + "=" + URLEncoder.encode(otppass.getText().toString(), "UTF-8")+"&"+
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("2", "UTF-8") +"&"+
                        URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(ed1.getText().toString(), "UTF-8")
                        ;
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

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if(s.compareTo("Password changed")==0)
            {
                Toast.makeText(v.getContext(), "Password Changed", Toast.LENGTH_SHORT).show();
                m=0;
                sh = v.getContext().getSharedPreferences("Signin", MODE_PRIVATE);
                SharedPreferences.Editor editor = sh.edit();
                editor.putString("number", "0");
                editor.commit();
                sh = v.getContext().getSharedPreferences("name", MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sh.edit();
                editor1.putString("facname", "name");
                editor1.commit();
                sh = v.getContext().getSharedPreferences("email", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = sh.edit();
                editor2.putString("facemail", "email");
                editor2.commit();
                //Intent it = new Intent(v.getContext(), MainActivity.class);
                //startActivity(it);
                onCreate(Bundle.EMPTY);
            }
            else
                Toast.makeText(v.getContext(),s, Toast.LENGTH_SHORT).show();
        }
    }
    public class Backtask2 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/OTPgen.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
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

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if(s.compareTo("success")==0) {
                Toast.makeText(v.getContext(), "Request Generated", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(v.getContext(), s, Toast.LENGTH_SHORT).show();
        }
    }


}
