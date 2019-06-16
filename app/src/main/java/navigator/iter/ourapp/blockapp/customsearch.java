package navigator.iter.ourapp.blockapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by kesha on 12-04-2018.
 */

public class customsearch extends ArrayAdapter<String> {

    private Activity context;
    ArrayList<String> name;
    //private String[] name;
    private String[] online;
    private String[] mobile;
    private String[] email;
    private String[] roomno;
    private String[] designation;

    int way=3;

    public customsearch(Activity contex, ArrayList<String> n, String[] o, String[] m, String[] e, String[] r, String[] d ) {
        super(contex, R.layout.rowfiles,o);
        this.context=contex;
        this.name=n;
        this.online=o;
        this.mobile=m;
        this.email=e;
        this.roomno=r;
        this.designation=d;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        final View v=inflater.inflate(R.layout.rowfiles,null,true);

        TextView n=(TextView)v.findViewById(R.id.faculty);
        n.setText(name.get(position));

        TextView d = (TextView) v.findViewById(R.id.type);
        d.setText(designation[position]);

        TextView m = (TextView) v.findViewById(R.id.number);
        m.setText(mobile[position]);

        TextView r = (TextView) v.findViewById(R.id.roomNum);
        r.setText(roomno[position]);

        TextView e = (TextView) v.findViewById(R.id.email);
        e.setText(email[position]);

        ImageView map = (ImageView)v.findViewById(R.id.imageMap);

        Picasso.with(context)
                .load("http://argetim2k17.com/ITER_Navigator/Image_ways/C_block/" + roomno[position] + ".jpg")
                .into(map);

        LinearLayout l=(LinearLayout)v.findViewById(R.id.lineraout);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Imageview_1.class);
                if(roomno[position].compareTo("")!=0) {
                    intent.putExtra("v", String.valueOf(roomno[position]));
                    context.startActivity(intent);
                    }
            }
        });

        if(online[position].compareTo("1")==0)
        {
            l.setVisibility(View.VISIBLE);
        }
        else
        {
            l.setVisibility(View.INVISIBLE);
        }

        return v;

    }




}
