package com.example.walter.artifactlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Unit extends AppCompatActivity implements View.OnClickListener{

    private Button newUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        View new_artifact_but = findViewById(R.id.new_artifact_button);
        new_artifact_but.setOnClickListener(this);
    }

    public void newUnit(){
        Intent intent = new Intent(this, Artifact.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        newUnit();
    }
}

