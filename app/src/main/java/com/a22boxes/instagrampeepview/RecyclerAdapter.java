package com.a22boxes.instagrampeepview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<RecyclerItem> list;
    FreezeRecycler listener;

    public RecyclerAdapter(Context context, ArrayList<RecyclerItem> list, FreezeRecycler listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final RecyclerItem item = list.get(position);
        holder.userName.setText(item.getUserName());
        holder.userImage.setImageDrawable(context.getResources().getDrawable(item.getUserImage()));
        holder.image.setImageDrawable(context.getResources().getDrawable(item.getImage()));

        holder.image.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public boolean onLongClick(View v) {
                FragmentTransaction ft = ((MainActivity) context).getSupportFragmentManager().beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("userImage", item.userImage);
                bundle.putString("userName", item.userName);
                bundle.putInt("image", item.image);
                Fragment f = new FragmentPeep();
                ft.setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out);
                f.setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.image_transition));
                f.setEnterTransition(TransitionInflater.from(context).inflateTransition(R.transition.image_transition));
                f.setArguments(bundle);

                listener.freezeView();

                try {
                    ft.add(R.id.fragment_container, f).addSharedElement(holder.image, "image").addToBackStack(null).commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });

        holder.image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    FragmentManager fm = ((MainActivity) context).getSupportFragmentManager();
                    fm.popBackStack();
                    listener.unfreezeView();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        ImageView image;
        TextView userName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            userName = itemView.findViewById(R.id.user_name);
            image = itemView.findViewById(R.id.image);
        }
    }

    public interface FreezeRecycler{
        void freezeView();
        void unfreezeView();
    }
}
