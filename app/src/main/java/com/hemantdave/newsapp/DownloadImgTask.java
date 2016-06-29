package com.hemantdave.newsapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by INDIA on 6/28/2016.
 */
public class DownloadImgTask extends AsyncTask<String,Void,Bitmap> {
    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            URL url=new URL(urls[0]);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.connect();
            InputStream stream=connection.getInputStream();
            Bitmap res= BitmapFactory.decodeStream(stream);
            return res;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
