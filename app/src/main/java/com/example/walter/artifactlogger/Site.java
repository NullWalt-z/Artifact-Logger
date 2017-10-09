package com.example.walter.artifactlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Site extends AppCompatActivity implements View.OnClickListener {

    private Button newUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);

        View new_unit_but = findViewById(R.id.new_unit_button);
        new_unit_but.setOnClickListener(this);
    }

    public void newUnit(){
        Intent intent = new Intent(this, Unit.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        newUnit();
    }
}
