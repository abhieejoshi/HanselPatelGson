package com.example.abhinav.hanselpatelgson;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Abhinav on 12/28/2017.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>
{
    ArrayList<Flowers> arrayList = new ArrayList<Flowers>();
    Context context;
    public MyRecyclerAdapter(ArrayList<Flowers> arrayList, Context ctx)
    {
        this.arrayList = arrayList;
        context=ctx;
    }

    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).

                inflate(R.layout.onerow,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view,context,arrayList);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Flowers flowers=arrayList.get(position);
        Log.i("Array", flowers.getProductId()+"");
        holder.tvId.setText("Product ID: "+flowers.getProductId()+"");
        holder.tvName.setText("Product Name: "+flowers.getName());
        holder.tvCat.setText("Product Category: "+flowers.getCategory());
        holder.tvPrice.setText("Product Price: "+flowers.getPrice()+"\n");

    }


    @Override
    public int getItemCount() {
        return  arrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView tvId,tvCat,tvName,tvPrice;
        public ArrayList<Flowers> list= new ArrayList<Flowers>();
        Context cobject;
        android.app.FragmentManager fm;

        public MyViewHolder(View itemView,Context cob,ArrayList<Flowers> lob) {
            super(itemView);
            cobject=cob;
            list=lob;
            itemView.setOnClickListener(this);
            tvId = (TextView) itemView.findViewById(R.id.textViewId);
            tvCat = (TextView) itemView.findViewById(R.id.textViewCategory);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvPrice = (TextView) itemView.findViewById(R.id.textViewPrice);
            fm=((Activity) cobject).getFragmentManager();
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Flowers flowers = list.get(position);
            Bundle bundle = new Bundle();
            bundle.putString("instructions",flowers.getInstructions());
            bundle.putParcelableArrayList("arraylist", list);
            bundle.putInt("position", position);
            bundle.putString("photo","http://services.hanselandpetal.com/photos/"+flowers.getPhoto());
            CustomDialogFragment customDialogFragment =new CustomDialogFragment();
            customDialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
            customDialogFragment.setArguments(bundle);
            customDialogFragment.show(fm,"Dialog Fragment");
        }
    }
}
