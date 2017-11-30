package com.example.walter.artifactlogger;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Walter on 11/29/2017.
 */


public class customAdapter extends ArrayAdapter<artifactObject> {

    private ArrayList<artifactObject> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView artifactNumText;
        TextView locText;
        TextView ageText;
        TextView depthText;
        TextView descText;
        TextView photoText;
        //ImageView preview;
    }

    public customAdapter(ArrayList<artifactObject> data, Context context) {
        super(context, R.layout.artifact_list_layout, data);
        this.dataSet = data;
        this.mContext = context;
    }

    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        artifactObject artifactList=(artifactObject)object;
        /*
        switch (v.getId())
        {
            case R.id.item_info:
                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
        */
    }
    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        artifactObject artifact = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.artifact_list_layout, parent, false);
            viewHolder.artifactNumText = (TextView) convertView.findViewById(R.id.artifact_num_col);
            viewHolder.locText = (TextView) convertView.findViewById(R.id.location_col);
            viewHolder.depthText = (TextView) convertView.findViewById(R.id.depth_col);
            viewHolder.ageText = (TextView) convertView.findViewById(R.id.age_col);
            viewHolder.descText = (TextView) convertView.findViewById(R.id.desc_col);
            viewHolder.photoText = (TextView) convertView.findViewById(R.id.thumbnail_col);
            //viewHolder.preview = (ImageView) convertView.findViewById(R.id.thumbnail_col);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        //Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        //result.startAnimation(animation);
        lastPosition = position;

        viewHolder.artifactNumText.setText(artifact.getArtifact_number());
        viewHolder.locText.setText(artifact.getLocation());
        viewHolder.depthText.setText(artifact.getDepth());
        viewHolder.ageText.setText(artifact.getHist_pre());
        viewHolder.descText.setText(artifact.getDescription());
        //viewHolder.preview.setOnClickListener(this);
        //viewHolder.preview.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
