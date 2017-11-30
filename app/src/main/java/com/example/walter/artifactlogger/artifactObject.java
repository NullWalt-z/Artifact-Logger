package com.example.walter.artifactlogger;

import java.util.Vector;

/**
 * Created by Walter on 9/12/2017.
 */


public class artifactObject extends unitObject {
    protected String artifact_number;
    protected String location;
    protected String depth;
    protected String hist_pre;
    protected String description;
    protected String photo;
    //protected String site_number;
    //protected String unit_number;


    artifactObject(){
        this.artifact_number = "";
        this.location = "";
        this.depth = "";
        this.hist_pre = "";
        this.description = "";
        this.photo = "";
    }

    artifactObject(String artNum, String loc, String dep, String age, String desc, String photo){
        this.artifact_number = artNum;
        this.location = loc;
        this.depth = dep;
        this.hist_pre = age;
        this.description = desc;
        this.photo = photo;
    }


    //Getters
    public String getArtifact_number(){
        return artifact_number;
    }
    public String getLocation(){
        return location;
    } //change to string
    public String getDepth(){
        return depth;
    }
    public String getHist_pre(){
        return hist_pre;
    }
    public String getDescription(){
        return description;
    }
    public String getPhoto() {
        return photo;
    }
    /*public String getSite_number() {
        return site_number;
    }*/
    /*public String getUnit_number() {
        return unit_number;
    }*/


    //Setters
    public void setArtifact_number(String new_artifact_number){
        artifact_number = new_artifact_number;
    }
    public void setLocation(String new_location){
        this.location = new_location;
    }
    public void setDepth(String new_depth){
        this.depth = new_depth;
    }
    public void setHist_pre(String new_hist_pre){
        this.hist_pre = new_hist_pre;
    }
    public void setDescription(String new_description){
        this.description = new_description;
    }
    public void setPhoto(String new_photoPath) { this.photo = new_photoPath; }
    /*public void setSite_number(String site_number) {
        this.site_number = site_number;
    }
    public void setUnit_number(String unit_number) {
        this.unit_number = unit_number;
    }*/
}
