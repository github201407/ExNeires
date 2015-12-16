package com.jen.change.exneires.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jen.change.exneires.R;

/**
 * Created by chenmingqun on 2015/12/10.
 */
public class PostShellActivity extends AppCompatActivity {

    public static void Instance(Context context){
        Intent intent = new Intent(context, PostShellActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postshell);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
