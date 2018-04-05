package com.example.valentina.valentina_libruary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main extends AppCompatActivity {

    @BindView(R.id.log) Button log;
    @BindView(R.id.cancel) Button cancel;
    @BindView(R.id.login) EditText login;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.left1) TextView left;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.log)
    public void log(){
        if(login.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(),
                    "Redirecting...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MenuBar.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
            left.setVisibility(View.VISIBLE);
            counter--;
            left.setText(Integer.toString(counter));
            if (counter == 0) {
                log.setEnabled(false);
            }
        }
    }

    @OnClick(R.id.cancel)
    public void cancel() {
        finish();
    }
}
