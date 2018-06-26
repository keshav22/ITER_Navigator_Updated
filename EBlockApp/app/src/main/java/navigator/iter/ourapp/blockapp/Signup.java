package navigator.iter.ourapp.blockapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
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

public class Signup extends AppCompatActivity {
    int way1=0;
    EditText name,number,otp,roomno,ed1,ed2;
    AutoCompleteTextView email,designation;
    Button btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ArrayList<String> theList = new ArrayList();
    ArrayList<String> theList1 = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Backtask b=new Backtask();
        b.execute(" ");
        name=(EditText)findViewById(R.id.namesignup);
        number=(EditText)findViewById(R.id.numbersignup);
        designation=(AutoCompleteTextView) findViewById(R.id.designation);
        otp=(EditText)findViewById(R.id.otpsignup);
        email=(AutoCompleteTextView)findViewById(R.id.emailsignup);
        roomno=(EditText)findViewById(R.id.roomsignup);
        btn=(Button)findViewById(R.id.btnsbm);
        ed1=(EditText)findViewById(R.id.passwden);
        ed2=(EditText)findViewById(R.id.passwden1);
        theList1.add("Assistant professor");
        theList1.add("HOD");
        theList1.add("Associate professor");
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList1);
        designation.setAdapter(adapter);
        name.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
            if(i==EditorInfo.IME_ACTION_NEXT)
            {
                number.requestFocus();
            }
            return true;
        }
    });
        number.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    roomno.requestFocus();
                }
                return true;
            }
        });
        roomno.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    designation.requestFocus();
                }
                return true;
            }
        });

        designation.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    email.requestFocus();
                }
                return true;
            }
        });
        email.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    ed1.requestFocus();
                }
                return true;
            }
        });
        ed1.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i== EditorInfo.IME_ACTION_NEXT)
                {
                    ed2.requestFocus();
                }
                return true;
            }
        });
        ed2.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent)
            {
                if(i== EditorInfo.IME_ACTION_DONE)
                {
                    btn.performClick();
                }
                return true;
            }
        });

        btn.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                if(way1==0) {


                    if (name.getText().toString().compareTo("") == 0) {
                        Toast.makeText(getApplicationContext(), "Enter Your Name", Toast.LENGTH_SHORT).show();
                        name.requestFocus();
                    } else if (number.length() != 10) {
                        Toast.makeText(getApplicationContext(), "Enter a valid number", Toast.LENGTH_SHORT).show();
                        number.requestFocus();
                    } else if (roomno.getText().toString().compareTo("") == 0  ) {
                        Toast.makeText(getApplicationContext(), "Enter a valid Room no", Toast.LENGTH_SHORT).show();
                        roomno.requestFocus();
                    } else if (designation.getText().toString().compareTo("") == 0) {
                        Toast.makeText(getApplicationContext(), "Enter Designation", Toast.LENGTH_SHORT).show();
                        designation.requestFocus();
                    } else if ((email.getText().toString().matches(emailPattern) && email.getText().toString().length() > 0) == false) {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                        email.requestFocus();
                    }
                    else if(ed1.getText().toString().length()<=8)
                    {
                        Toast.makeText(getApplicationContext(),"Enter Password more than 8 characters",Toast.LENGTH_SHORT).show();
                        ed1.requestFocus();
                    }
                    else if(ed1.getText().toString().compareTo(ed2.getText().toString())!=0)
                    {
                        Toast.makeText(getApplicationContext(),"Password Doestn't match",Toast.LENGTH_SHORT).show();
                        ed2.requestFocus();
                    }
                    else
                    {
                        if(theList.contains(email.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"Email already on database",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Backtask1 bc = new Backtask1();
                            bc.execute();
                        }
                    }
                }
                else if(way1==1)
                {
                  Backtask2 bc = new Backtask2();
                  bc.execute(email.getText().toString());
                    //Toast.makeText(getApplicationContext(),email.getText().toString(),Toast.LENGTH_SHORT).show();
                }
            }

        });



   /*
        Backtask bc=new Backtask();
        bc.execute();
*/
    }

    public class Backtask2 extends AsyncTask<String,Void,String>
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
                String post = URLEncoder.encode("otp", "UTF-8") + "=" + URLEncoder.encode(otp.getText().toString(), "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8")+"&"+
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8");
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
                return return_text;
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
            if(s.compareTo("Match Success")==0) {

                Toast.makeText(getApplicationContext(), "Request Generated", Toast.LENGTH_SHORT).show();
                finish();
                //otp.setFocusable(false);
                //btn.setVisibility(View.INVISIBLE);
            }
            else
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        }

    @Override
    public void onBackPressed() {
        if(way1==0)
        {
            super.onBackPressed();
        }
        else
        {

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class Backtask1 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                String c=roomno.getText().toString().substring(0,1).toUpperCase();
                String rest=roomno.getText().toString().substring(1,4);
                c=c+rest;
                String name12=name.getText().toString();
                if(designation.getText().toString().trim().toLowerCase().compareTo("hod")==0 || designation.getText().toString().trim().toLowerCase().compareTo("head of department")==0)
                {
                    name12="HOD "+name.getText().toString();
                }
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/mailbox.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name12, "UTF-8")+"&"+
                        URLEncoder.encode("roomno", "UTF-8") + "=" + URLEncoder.encode(c, "UTF-8")+"&"+
                        URLEncoder.encode("number", "UTF-8") + "=" + URLEncoder.encode(number.getText().toString(), "UTF-8")+"&"+
                        URLEncoder.encode("desig", "UTF-8") + "=" + URLEncoder.encode(designation.getText().toString(), "UTF-8")+"&"+
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email.getText().toString(), "UTF-8")+"&"+
                        URLEncoder.encode("passwd", "UTF-8") + "=" + URLEncoder.encode(ed2.getText().toString(), "UTF-8");
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
                return return_text ;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return return_text;
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if(s.trim().compareTo("Success")==0) {
                Toast.makeText(getApplicationContext(), "Now Input OTP", Toast.LENGTH_SHORT).show();
                otp.setVisibility(View.VISIBLE);
                otp.requestFocus();
                roomno.setFocusable(false);
                name.setFocusable(false);
                designation.setFocusable(false);
                number.setFocusable(false);
                ed1.setFocusable(false);
                ed2.setFocusable(false);
                btn.setText("Submit OTP");
                way1++;
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
        protected String doInBackground(String... strings) {
            String str="";
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/Allemails.php");
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
                    if(obj.getString("email").compareTo("")!=0)
                        theList.add(obj.getString("email"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    //Code for json object read
    public class Backtask12 extends AsyncTask<String,Void,String>
    {
        String return_text;
        @Override
        protected String doInBackground(String... strings)
        {
            try
            {
                URL url=new URL("http://argetim2k17.com/ITER_Navigator/json.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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
            number=(EditText)findViewById(R.id.numbersignup);
            designation=(AutoCompleteTextView) findViewById(R.id.designation);
            email=(AutoCompleteTextView) findViewById(R.id.emailsignup);
            String v="{\"employee\":"+s+"}";
            try
            {
                email.setText(v);
                JSONObject j = new JSONObject(v);
                JSONObject obc =j.getJSONObject("employee");
                name=(EditText)findViewById(R.id.namesignup);
                name.setText(obc.getString("bbsr"));
                designation.setText(obc.getString("ctc"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}
