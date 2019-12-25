package com.bishwajit.medicalassistant;

/**
 * Created by bishwajit on 3/1/2016.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;

public class MyTask extends AsyncTask<String, Integer, String> {

    Context c; TextView t;
    public MyTask(Context c, TextView t) {
        this.c = c;
        this.t = t;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        t.setText(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
      //  t.setText("setting up to fetch data...");

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        t.setText(result);
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuffer stringBuffer = new StringBuffer("");
        BufferedReader bufferedReader = null;
        try {
            // creating the Httpclient
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("https://api.infermedica.com/v2/diagnosis");
            // HttpPost httpPost = new HttpPost("https://api.infermedica.com/v2/conditions/c_537");

            // adding the headers to the HttpPost
            httpPost.addHeader("Content-Type","application/json");
            httpPost.addHeader("Accept", "application/json");
            httpPost.addHeader("app_id", "588b8e21");
            httpPost.addHeader("app_key", "fa3021f5f8467c1b5806c68482d57e44");

            //URLConnection httpURLConnection = new HttpURLConnection();

            httpPost.setEntity(new StringEntity(params[0]));
//          data.setText("execute call next...");

            HttpResponse httpResponse = httpClient.execute(httpPost);
            InputStream inputStream = httpResponse.getEntity().getContent();
            bufferedReader = new BufferedReader(new InputStreamReader(
                    inputStream));

            String readLine = bufferedReader.readLine();
            while (readLine != null) {
                stringBuffer.append(readLine);
                stringBuffer.append("\n");
                readLine = bufferedReader.readLine();
            }
        } catch (Exception e) {
            // TODO: handle exception
            String val = e.toString();
            //data.setText(val);
            return val;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    String s = stringBuffer.toString();
                    //data.setText(s);
                    return s;
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }
        return "out of everything";
    }
}

