package com.a22boxes.instagrampeepview;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FragmentMain extends Fragment {

    RecyclerView recyclerView;
    MyLinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = v.findViewById(R.id.recycler);
        ArrayList<RecyclerItem> list = new ArrayList<>();
        RecyclerItem temp1 = new RecyclerItem("Some Username", R.drawable.person1, R.drawable.person1);
        RecyclerItem temp2 = new RecyclerItem("Best Username", R.drawable.person2, R.drawable.person2);
        RecyclerItem temp3 = new RecyclerItem("Good Username", R.drawable.person3, R.drawable.person3);
        RecyclerItem temp4 = new RecyclerItem("A Username", R.drawable.person4, R.drawable.person4);
        RecyclerItem temp5 = new RecyclerItem("Any Username", R.drawable.person5, R.drawable.person5);
        RecyclerItem temp6 = new RecyclerItem("The Username", R.drawable.person6, R.drawable.person6);
        RecyclerItem temp7 = new RecyclerItem("Bad Username", R.drawable.person7, R.drawable.person7);
        RecyclerItem temp8 = new RecyclerItem("Sad Username", R.drawable.person8, R.drawable.person8);
        RecyclerItem temp9 = new RecyclerItem("Great Username", R.drawable.person9, R.drawable.person9);
        RecyclerItem temp10 = new RecyclerItem("Worst Username", R.drawable.person1, R.drawable.person1);
        list.add(temp1);
        list.add(temp2);
        list.add(temp3);
        list.add(temp4);
        list.add(temp5);
        list.add(temp6);
        list.add(temp7);
        list.add(temp8);
        list.add(temp9);
        list.add(temp10);

        linearLayoutManager = new MyLinearLayoutManager(getContext());
        FreezeRecyclerListener listener = new FreezeRecyclerListener();

        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), list, listener);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return v;
    }

    public class MyLinearLayoutManager extends LinearLayoutManager{
        private boolean isScrolling = true;
        public MyLinearLayoutManager(Context context) {
            super(context);
        }

        public void setScrolling(boolean isScrolling){
            this.isScrolling = isScrolling;
        }

        @Override
        public boolean canScrollVertically() {
            return isScrolling&&super.canScrollVertically();
        }
    }

    public class FreezeRecyclerListener implements RecyclerAdapter.FreezeRecycler{

        @Override
        public void freezeView() {
            linearLayoutManager.setScrolling(false);
        }

        @Override
        public void unfreezeView() {
            linearLayoutManager.setScrolling(true);
        }
    }

}
