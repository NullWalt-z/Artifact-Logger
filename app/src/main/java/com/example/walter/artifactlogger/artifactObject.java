package com.example.walter.artifactlogger;

/**
 * Created by Walter on 9/12/2017.
 */


public class artifactObject extends unitObject {
    protected String artifact_number;
    protected String location;
    protected String depth;
    protected String hist_pre;
    protected String description;
    protected String site_number;
    protected String unit_number;


    artifactObject(){
        artifact_number = "";
        location = "";
        depth = "";
        hist_pre = "";
        description = "";
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
    public String getSite_number() {
        return site_number;
    }
    public String getUnit_number() {
        return unit_number;
    }


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
    public void setSite_number(String site_number) {
        this.site_number = site_number;
    }
    public void setUnit_number(String unit_number) {
        this.unit_number = unit_number;
    }
}
