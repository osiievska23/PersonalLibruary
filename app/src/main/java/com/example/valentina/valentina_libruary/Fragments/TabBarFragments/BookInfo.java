package com.example.valentina.valentina_libruary.Fragments.TabBarFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentina.valentina_libruary.R;

import butterknife.BindView;


public class BookInfo extends Fragment {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.photo)
    ImageView photo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            Toast.makeText(getActivity(),
                    bundle.getString("name"), Toast.LENGTH_SHORT).show();
            /*name.setText(getArguments().getString("name"));
            author.setText(getArguments().getString("author"));
            category.setText(getArguments().getString("category"));*/
        } else{
            Toast.makeText(getActivity(),
                    "null", Toast.LENGTH_SHORT).show();
        }
        
        return inflater.inflate(R.layout.book_info, container, false);
    }


}
