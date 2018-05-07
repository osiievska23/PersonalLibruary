package com.example.valentina.valentina_libruary.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.valentina.valentina_libruary.Drawer.Drawer;
import com.example.valentina.valentina_libruary.R;
import com.example.valentina.valentina_libruary.RealmModel.RealmModel;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.author)
    EditText author;

    @BindView(R.id.description)
    EditText description;

    @BindView(R.id.image)
    Button image;

    @BindView(R.id.photo)
    ImageView photo;

    @BindView(R.id.category_button)
    Button category_button;

    @BindView(R.id.choosen_caterory)
    TextView category;

    //public boolean flag = false;
    public String bookImage;
    private final int GALLERY_REQUEST = 1;
    private final int IDD_LIST_CAT = 1;
    private final String[] categoryList = new String[] { "Crime", "Western", "Science fiction",
            "Popular science", "Reference book", "Textbook", "Cookbook", "Encyclopedia",
            "Mystery", "Drama", "Post-apocalyptic", "Folklore", "Distopia", "Fantasy",
            "Noire", "Political film", "Adventure movie", "Fairy tale", "Fan fiction",
            "Romantic novel", "Horror", "Classic", "Tragedy", "Tragicomedy", "Folklore",
            "Historical fiction", "Humor", "Thriller", "Biograpy", "Novel"};

    public AddFragment() {

    }
/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.image)
    public void setImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @OnClick(R.id.add)
    public void add(){
        RealmModel realm = new RealmModel(getActivity());
        realm.setBook(name, author, category, description, bookImage);
    }

    @OnClick(R.id.category_button)
    public void category_button(){
        showDialog(IDD_LIST_CAT);
    }

    public Dialog onCreateDialog(int cat) {
        switch (cat) {
            case IDD_LIST_CAT:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Categories")
                        .setItems(categoryList, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                category.setText(categoryList[item]);
                            }
                        });
                return builder.create();
            default:
                return null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GALLERY_REQUEST:
                Uri selectedImage = data.getData();
                if(selectedImage != null){
                    bookImage = getRealPathFromURI(selectedImage);
                    Picasso
                            .with(getActivity())
                            .load(new File(bookImage))
                            .resize(300, 300)
                            .into(photo);
                }
        }
    }

    protected String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver()
                .query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    */
}