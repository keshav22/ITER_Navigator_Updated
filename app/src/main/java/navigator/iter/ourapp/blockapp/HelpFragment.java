package navigator.iter.ourapp.blockapp;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class HelpFragment
  extends Fragment
{
  @Nullable
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup paramViewGroup, Bundle paramBundle)
  {
    final View view = inflater.inflate(R.layout.help_fragment,
            paramViewGroup, false);
    ImageView button = (ImageView) view.findViewById(R.id.cross);
    button.setOnClickListener(new View.OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        getActivity().onBackPressed();
      }
    });
    return view;
  }




}
