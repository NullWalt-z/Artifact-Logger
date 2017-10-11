package com.example.walter.artifactlogger;

import com.example.walter.artifactlogger.siteObject;

import java.util.Vector;

/**
 * Created by Walter on 9/12/2017.
 */

public class unitObject extends siteObject {
    protected String unit_number;
    protected Vector<artifactObject> artifact_vector;
    protected String location;


    unitObject(){
        unit_number = "";
        location = "";
        //artifact_vector
    }
}
