package com.example.walter.artifactlogger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Vector;

import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class artifactList extends AppCompatActivity implements View.OnClickListener{

    private Button newArtifact;
    private ArrayList<artifactObject> current_artifactList;
    ListView listView;
    private static customAdapter adapter;
    public DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artifact_list);

        //get list of all artifacts in DB
        db = new DBHandler(this, null, null, 1);

        artifactObject TestArtifact1 = new artifactObject("number","location", "depth", "age", "desc", "preview");
        db.addArtifact(TestArtifact1);
        current_artifactList = db.getAllArtifacts();

        listView=(ListView)findViewById(R.id.artifact_list_view);
        //createArtifactTable(current_artifactList);

        adapter = new customAdapter(current_artifactList, getApplicationContext());
        listView.setAdapter(adapter);


        View new_artifact_but = findViewById(R.id.new_artifact_button);
        new_artifact_but.setOnClickListener(this);

    }



    public void newArtifact(){
        Intent intent = new Intent(this, Artifact.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        newArtifact();
    }

    /*
    public void createArtifactTable(ArrayList<artifactObject> thisArtifactList){
        TableLayout thisArtifactTable = (TableLayout) findViewById(R.id.artifact_list_view);


        TextView numText=new TextView(this.getApplicationContext());
        TextView locText=new TextView(this.getApplicationContext());
        TextView depthText=new TextView(this.getApplicationContext());
        TextView ageText=new TextView(this.getApplicationContext());
        TextView descText=new TextView(this.getApplicationContext());
        TextView photoText=new TextView(this.getApplicationContext());
        int size = thisArtifactList.size();
        for (int i = 0; i < size; i++) {

            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            artifactObject thisArtifact = thisArtifactList.get(i);
            String thisArtifactNumber = thisArtifact.getArtifact_number();
            numText.setText(thisArtifactNumber);
            String thisArtifactLocation = thisArtifact.getLocation();
            locText.setText(thisArtifactLocation);
            String thisArtifactDepth = thisArtifact.getDepth();
            depthText.setText(thisArtifactDepth);
            String thisArtifactAge = thisArtifact.getHist_pre();
            ageText.setText(thisArtifactAge);
            String thisArtifactDesc = thisArtifact.getDescription();
            descText.setText(thisArtifactDesc);
            String thisArtifactPhoto = thisArtifact.getPhoto(0);
            photoText.setText(thisArtifactPhoto);

            row.addView(numText);
            row.addView(locText);
            row.addView(depthText);
            row.addView(ageText);
            row.addView(descText);
            row.addView(photoText);
            thisArtifactTable.addView(row,i);
        }
    }
    */


}



