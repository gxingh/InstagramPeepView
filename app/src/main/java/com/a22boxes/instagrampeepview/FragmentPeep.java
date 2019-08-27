package com.a22boxes.instagrampeepview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentPeep extends Fragment {
    ImageView userImage;
    ImageView image;
    TextView userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_peep, container, false);
        userImage = v.findViewById(R.id.user_image);
        userName = v.findViewById(R.id.user_name);
        image = v.findViewById(R.id.image);

        Bundle bundle = getArguments();
        userImage.setImageDrawable(getContext().getResources().getDrawable(bundle.getInt("userImage")));
        image.setImageDrawable(getContext().getResources().getDrawable(bundle.getInt("image")));
        userName.setText(bundle.getString("userName"));


        return v;
    }
}
