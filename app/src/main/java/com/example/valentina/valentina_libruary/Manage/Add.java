package com.example.valentina.valentina_libruary.Manage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valentina.valentina_libruary.Drawer.Drawer;
import com.example.valentina.valentina_libruary.R;
import com.example.valentina.valentina_libruary.RealmModel.RealmModel;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Add extends AppCompatActivity {

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.author)
    EditText author;

    /*@BindView(R.id.status)
    EditText status;

    @BindView(R.id.id)
    EditText id;*/

    @BindView(R.id.link)
    EditText link;

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

    private String beforeAuthor;
    private String beforeCategory;
    private String beforeName;
    private String beforeLink;
    private String beforeDescription;
    private String beforeImage;
    public boolean flag = false;
    public String bookImage;
    private final int GALLERY_REQUEST = 1;
    private final int IDD_LIST_CAT = 1;
    private final String[] categoryList = new String[] { "Crime", "Western", "Science fiction",
            "Popular science", "Reference book", "Textbook", "Cookbook", "Encyclopedia",
            "Mystery", "Drama", "Post-apocalyptic", "Folklore", "Distopia", "Fantasy",
            "Noire", "Political film", "Adventure movie", "Fairy tale", "Fan fiction",
            "Romantic novel", "Horror", "Classic", "Tragedy", "Tragicomedy", "Folklore",
            "Historical fiction", "Humor", "Thriller", "Biograpy", "Novel"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        ButterKnife.bind(this);

        Arrays.sort(categoryList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.toString().compareTo(o2.toString());
            }
        });

        Intent intent = getIntent();
        flag = intent.getBooleanExtra("flag", false);
        if (flag)
            setEditTexts();
    }

    public void setEditTexts(){
        Intent intent = getIntent();

        beforeName = intent.getStringExtra("name");
        beforeAuthor = intent.getStringExtra("author");
        beforeCategory = intent.getStringExtra("category");
        beforeLink = intent.getStringExtra("link");
        beforeDescription = intent.getStringExtra("description");
        beforeImage = intent.getStringExtra("image");

        name.setText(beforeName);
        author.setText(beforeAuthor);
        category.setText(beforeCategory);
        link.setText(beforeLink);
        description.setText(beforeDescription);
        bookImage = beforeImage;
        // image.setText(intent.getStringExtra("image"));

    }

    @OnClick(R.id.image)
    public void setImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_REQUEST);
    }

    @OnClick(R.id.add)
    public void add(){
        RealmModel realm = new RealmModel(this);
        if (flag == false){
            realm.setBook(name, author, category, link, description, bookImage);
        } else {
            realm.updateBook(beforeName, beforeAuthor, beforeCategory, beforeLink,
                    beforeDescription, beforeImage, name, author, category, link, description,
                    bookImage);
        }

        Intent intent = new Intent(this, Drawer.class);
        startActivity(intent);
    }

    @OnClick(R.id.category_button)
    public void category_button(){
        showDialog(IDD_LIST_CAT);
    }

    @Override
    public Dialog onCreateDialog(int cat) {
        switch (cat) {
            case IDD_LIST_CAT:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Genre")
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case GALLERY_REQUEST:
                Uri selectedImage = data.getData();
                if(selectedImage != null){
                    bookImage = getRealPathFromURI(selectedImage);
                    Picasso
                            .with(this)
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
}