package com.example.abhinav.hanselpatelgson;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomDialogFragment extends DialogFragment {
    TextView page;
    int position;
    String desc,path;
    ArrayList<Flowers> arrayList;
    public CustomDialogFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle("INFORMATION");
        return dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, final Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragmentlayout,container,false);
        getDialog().setTitle("Information");
        desc = getArguments().getString("instructions");
        path = getArguments().getString("photo");
        arrayList = getArguments().getParcelableArrayList("arraylist");
        position = getArguments().getInt("position");

        FragmentOnDialog customDialogImage = new FragmentOnDialog();
        final Bundle bundle = new Bundle();
        bundle.putString("instructions",desc);
        bundle.putString("photo",path);
        bundle.putParcelableArrayList("arraylist", arrayList);
        bundle.putInt("position", position);
        page = (TextView) rootView.findViewById(R.id.textViewPagesNo);
        page.setText("Product: "+(position+1));
        customDialogImage.setArguments(bundle);
        CustomDialogFragment.this.getChildFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right)
                .replace(R.id.aonboarding_fl_inputs, customDialogImage).commit();

        final Button prev = (Button) rootView.findViewById(R.id.btn_previous);
        final Button button = (Button) rootView.findViewById(R.id.button2);
        if ((position+1) >= arrayList.size()) {
            button.setVisibility(View.INVISIBLE);
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position = position + 1;
                if (position >= arrayList.size() ) {
                    position = arrayList.size() ;
                    button.setVisibility(View.INVISIBLE);
                } else {
                    prev.setVisibility(View.VISIBLE);
                    page.setText("Product: " + (position+1));
                    FragmentOnDialog customDialogImage = new FragmentOnDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("instructions", desc);
                    bundle.putString("photo", path);
                    bundle.putParcelableArrayList("arraylist", arrayList);
                    bundle.putInt("position", position);
                    customDialogImage.setArguments(bundle);
                    CustomDialogFragment.this.getChildFragmentManager().beginTransaction()
                            .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right)
                            .replace(R.id.aonboarding_fl_inputs, customDialogImage).commit();

                }
            }
        });


        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position = position - 1;
                if (position <= 0) {
                    position = 0;
                    prev.setVisibility(View.INVISIBLE);
                }

                button.setVisibility(View.VISIBLE);
                page.setText("Product: " + (position + 1));
                FragmentOnDialog customDialogImage = new FragmentOnDialog();
                Bundle bundle = new Bundle();
                bundle.putString("instructions", desc);
                bundle.putString("photo", path);
                bundle.putParcelableArrayList("arraylist", arrayList);
                bundle.putInt("position", position );
                customDialogImage.setArguments(bundle);
                CustomDialogFragment.this.getChildFragmentManager().beginTransaction()
                        .setCustomAnimations(R.animator.exit_to_left, R.animator.enter_from_right)
                        .replace(R.id.aonboarding_fl_inputs, customDialogImage).commit();

            }
        });

        if(position == 0)
        {
            prev.setVisibility(View.INVISIBLE);
        }
        if(position == arrayList.size() )
        {
            button.setVisibility(View.INVISIBLE);
        }

        final GestureDetector gesture = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onDown(MotionEvent e) {
                        return true;
                    }

                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                           float velocityY) {
                        Log.i("fragment", "onFling has been called!");
                        final int SWIPE_MIN_DISTANCE = 120;
                        final int SWIPE_MAX_OFF_PATH = 250;
                        final int SWIPE_THRESHOLD_VELOCITY = 200;
                        try {
                            if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                                return false;
                            if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                Log.i("fragment", "Right to Left");
                                position = position + 1;
                                if (position >= arrayList.size() ) {
                                    position = arrayList.size()-1;
                                    button.setVisibility(View.INVISIBLE);
                                    page.setText("Product: " + (position+1));
                                }
                                prev.setVisibility(View.VISIBLE);
                                page.setText("Product: " + (position+1));
                                FragmentOnDialog customDialogImage = new FragmentOnDialog();
                                Bundle bundle = new Bundle();
                                bundle.putString("instructions", desc);
                                bundle.putString("photo", path);
                                bundle.putParcelableArrayList("arraylist", arrayList);
                                bundle.putInt("position", position);
                                customDialogImage.setArguments(bundle);
                                CustomDialogFragment.this.getChildFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.animator.enter_from_left, R.animator.exit_to_right)
                                        .replace(R.id.aonboarding_fl_inputs, customDialogImage).commit();
                            } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                                    && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                                Log.i("fragment", "Left to Right");
                                position = position - 1;
                                if (position <= 0) {
                                    position = 0;
                                    page.setText("Product: " + (position + 1));
                                    prev.setVisibility(View.INVISIBLE);
                                }
                                button.setVisibility(View.VISIBLE);
                                page.setText("Product: " + (position + 1));
                                FragmentOnDialog customDialogImage = new FragmentOnDialog();
                                Bundle bundle = new Bundle();
                                bundle.putString("instructions", desc);
                                bundle.putString("photo", path);
                                bundle.putParcelableArrayList("arraylist", arrayList);
                                bundle.putInt("position", position );
                                customDialogImage.setArguments(bundle);
                                CustomDialogFragment.this.getChildFragmentManager().beginTransaction()
                                        .setCustomAnimations(R.animator.exit_to_left, R.animator.enter_from_right)
                                        .replace(R.id.aonboarding_fl_inputs, customDialogImage).commit();

                            }
                        } catch (Exception e) {
                            // nothing
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                });

        rootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });

        return  rootView;
    }


}
