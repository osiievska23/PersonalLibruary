package com.example.valentina.valentina_libruary.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentina.valentina_libruary.Drawer.Drawer;
import com.example.valentina.valentina_libruary.R;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookFragment extends Fragment {

    @BindView(R.id.name)
    TextView name;

    @BindView(R.id.author)
    TextView author;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.link)
    TextView link;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.photo)
    ImageView photo;

    String bookImage;

    public MyBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_book, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            name.setText(bundle.getString("name"));
            author.setText(bundle.getString("author"));
            category.setText(bundle.getString("category"));
            link.setText(bundle.getString("link"));
            description.setText(bundle.getString("description"));
            bookImage = bundle.getString("image");
            Picasso
                    .with(getActivity().getApplicationContext())
                    .load(new File(bookImage))
                    .resize(400, 400)
                    .into(photo);
        } else{
            Toast.makeText(getActivity(),
                    "null", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

}
