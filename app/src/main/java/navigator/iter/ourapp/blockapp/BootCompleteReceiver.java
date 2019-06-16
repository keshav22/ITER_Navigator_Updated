package navigator.iter.ourapp.blockapp;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

/**
 * Created by kesha on 21-08-2018.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    Context context;
    SharedPreferences sh;
    @SuppressLint("WrongConstant")
    private boolean isNetworkAvailable()
    {
        return ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context=context;

        if(!isNetworkAvailable()) {
            if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
                Intent pushIntent = new Intent(context, Myservice.class);
                context.startService(pushIntent);
            }
            Intent pushIntent = new Intent(context, Myservice.class);
            context.stopService(pushIntent);
        }
        else
        {
            sh=context.getSharedPreferences("desg",0);
            String facdesg=sh.getString("facdesg","");
            facdesg=facdesg.trim().toLowerCase();
            if(facdesg.compareTo("hod")==0 || facdesg.compareTo("head of department")==0)
            {
                Intent pushIntent = new Intent(context, Myservice.class);
                context.startService(pushIntent);
            }
        }

    }
}
