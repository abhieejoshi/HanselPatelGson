package com.example.abhinav.hanselpatelgson;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FragmentOnDialog extends Fragment {

    @Override
    public Context getContext() {
        return super.getContext();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.customfragment,container,false);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView2);
        TextView textView = (TextView) rootView.findViewById(R.id.textViewDescription);
        textView.setText("");
//        String desc = getArguments().getString("instructions");
//        String path = getArguments().getString("photo");
        ArrayList<Flowers> arrayList = getArguments().getParcelableArrayList("arraylist");
        int position = getArguments().getInt("position");

        if(position<1)
        {
            position = 0;
            Flowers flowers2 = arrayList.get(0);
            Glide.with(getActivity().getApplicationContext()).load("http://services.hanselandpetal.com/photos/"+flowers2.getPhoto()).placeholder(R.drawable.loading).into(imageView);
            textView.setText(flowers2.getInstructions());

        }
        else {
            Flowers flowers = arrayList.get(position);
            Glide.with(getActivity().getApplicationContext()).load("http://services.hanselandpetal.com/photos/" + flowers.getPhoto()).placeholder(R.drawable.loading).into(imageView);
            textView.setText(flowers.getInstructions());
        }

        return rootView;
    }
}