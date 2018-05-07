package com.example.valentina.valentina_libruary.Fragments.TabBarFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valentina.valentina_libruary.R;

import butterknife.BindView;

public class BookDescription extends Fragment {

    @BindView(R.id.description)
    TextView description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.book_description, container, false);
    }
}
