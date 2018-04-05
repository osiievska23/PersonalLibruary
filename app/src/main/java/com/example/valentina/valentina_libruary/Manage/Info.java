package com.example.valentina.valentina_libruary.Manage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import com.example.valentina.valentina_libruary.Info_category;
import com.example.valentina.valentina_libruary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Info extends AppCompatActivity {

    @BindView(R.id.nameListBut)
    RadioButton nameListBut;
    @BindView(R.id.authorListBut)
    RadioButton authorListBut;
    @BindView(R.id.categoryListBut)
    RadioButton categoryListBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        ButterKnife.bind(this);
    }

    /*@OnClick(R.id.nameListBut)
    public void NameList(){
        Intent intent = new Intent(this, Info_name.class);
        startActivity(intent);
    }

    @OnClick(R.id.authorListBut)
    public void AuthorList(){
        Intent intent = new Intent(this, Info_author.class);
        startActivity(intent);
    }*/

    @OnClick(R.id.categoryListBut)
    public void CategoryList(){
        Intent intent = new Intent(this, Info_category.class);
        startActivity(intent);
    }
}
