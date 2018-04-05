package com.example.valentina.valentina_libruary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.valentina.valentina_libruary.Manage.Info;
import com.example.valentina.valentina_libruary.Manage.Search;
import com.example.valentina.valentina_libruary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class Commands extends AppCompatActivity {

    @BindView(R.id.ADD) Button ADD;
    @BindView(R.id.DEL) Button DEL;
    @BindView(R.id.search) Button search;
    @BindView(R.id.info) Button info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commands);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ADD)
    public void ADD(){
        Intent intent = new Intent(this, com.example.valentina.valentina_libruary.Manage.ADD.class);
        startActivity(intent);
    }

    @OnClick(R.id.DEL)
    public void DEL(){
        Intent intent = new Intent(this, com.example.valentina.valentina_libruary.Manage.DEL.class);
        startActivity(intent);
    }

    @OnClick(R.id.search)
    public void search(){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    @OnClick(R.id.info)
    public void info(){
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }
}
