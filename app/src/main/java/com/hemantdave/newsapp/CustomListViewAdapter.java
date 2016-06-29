package com.hemantdave.newsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by INDIA on 6/28/2016.
 */
public class CustomListViewAdapter extends BaseAdapter {

    private static final String URL_FIXED = "http://content.guardianapis.com/search?show-fields=thumbnail&q=android&api-key=test";
    JSONObject details = null;
    ArrayList<FeedArchitecturePojo> detailedData = new ArrayList<>();
    private String result;
    Context context;

    public CustomListViewAdapter(Context context) {
        this.context = context;
        getData();
    }

    @Override
    public int getCount() {
        return detailedData.size();
    }

    @Override
    public Object getItem(int position) {
        return detailedData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.row_layout, parent, false);
        }

        TextView title = (TextView) row.findViewById(R.id.titleTV);
        TextView webpublivationdate = (TextView) row.findViewById(R.id.webPublicationDateTV);
        Button storyAction = (Button) row.findViewById(R.id.storyButton);
        final FeedArchitecturePojo temp_obj = detailedData.get(position);
        title.setText(temp_obj.getWebTitle());
        webpublivationdate.setText(temp_obj.getWebPublicationDate());
        storyAction.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(temp_obj.getThumbnail()));
                                               Intent browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice");
                                               context.startActivity(browserChooserIntent);
                                           }
                                       }

        );
        DownloadImgTask imgdata = new DownloadImgTask();
        try

        {
            Bitmap result = imgdata.execute(temp_obj.getThumbnail()).get();
            ImageView view = (ImageView) row.findViewById(R.id.imageView);
            view.setImageBitmap(result);
        } catch (
                InterruptedException e
                )

        {
            e.printStackTrace();
        } catch (
                ExecutionException e
                )

        {
            e.printStackTrace();
        }


        return row;
    }

    public void getData() {
        try {
            DownloadFeedTask downloaddata = new DownloadFeedTask();
            result = downloaddata.execute(URL_FIXED).get();
            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    details = jsonObj.getJSONObject("response");
                    if (details.getString("status").equals("ok")) {
                        JSONArray results = details.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject c = results.getJSONObject(i);
                            String webtitle = c.getString("webTitle");
                            String webPublicationDate = c.getString("webPublicationDate");
                            String webUrl = c.getString("webUrl");
                            JSONObject fieldobj = c.getJSONObject("fields");
                            String thumbnail = fieldobj.getString("thumbnail");
                            detailedData.add(new FeedArchitecturePojo(webtitle, webPublicationDate, webUrl, thumbnail));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }

    }
}
