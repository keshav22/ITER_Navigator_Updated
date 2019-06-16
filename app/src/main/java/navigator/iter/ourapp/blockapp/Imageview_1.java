package navigator.iter.ourapp.blockapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;




public class Imageview_1 extends ActionBarActivity {
   // ImageView map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview_1);
       // map=(ImageView)findViewById(R.id.image1);
     WebView map=(WebView)findViewById(R.id.image1);
        Intent n=getIntent();
        String res=n.getStringExtra("v");
        String url="http://argetim2k17.com/ITER_Navigator/Image_ways/C_block/" + res + ".jpg";
        map.getSettings().setJavaScriptEnabled(true);
        WebSettings web=map.getSettings();
        web.supportZoom();
        web.setSupportZoom(true);
        web.setBuiltInZoomControls(true);
        map.loadUrl(url);
        /*Picasso.with(getApplicationContext())
                .load("http://argetim2k17.com/Sqldatabase/Cblock/Images_Ways/"+res+".jpg")
                .into(map);*/
    }


}
