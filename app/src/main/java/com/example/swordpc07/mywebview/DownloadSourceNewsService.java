package com.example.swordpc07.mywebview;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by swordpc07 on 1/18/2017.
 */

public class DownloadSourceNewsService extends IntentService {


    static String URL_SOURCE_NEWS = "https://newsapi.org/v1/articles?source=";
    static String API_KEY = "&apiKey=0f81a5b7005c46ff9a7bc12c552e458d";
    String URL_Load_DATA;
    String news_id;

    public DownloadSourceNewsService() {
        super("My service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle b = intent.getExtras();

        news_id = b.getString("id");

        URL_Load_DATA = URL_SOURCE_NEWS + news_id + API_KEY;
        Log.d("URL=" ,URL_Load_DATA);


        new Timer().schedule(new TimerTask() {
            public void run() {

                new GetNewsDataDetails().execute(URL_Load_DATA);
            }
        }, 0);

    }


        private class GetNewsDataDetails extends AsyncTask<String, String, String> {

            String loadnewsjson;

            String url;

            public GetNewsDataDetails() {
            }


            @Override
            protected String doInBackground(String... params) {
                try {

                    url = (String) params[0];

                    Log.d("url =", url);
                    DownloadUrl downloadUrl = new DownloadUrl();

                    loadnewsjson = downloadUrl.readUrl(url);

                } catch (Exception e) {
                    Log.d("GooglePlacesReadTask", e.toString());
                }

                return loadnewsjson;
            }

            @Override
            protected void onPostExecute(String result) {

                final int chunkSize = 2048;
            for (int i = 0; i < result.length(); i += chunkSize) {
                Log.d("reault:", result.substring(i, Math.min(result.length(), i + chunkSize)));
            }

                LoadNewsJsonFromSouce loadjsonnews = new LoadNewsJsonFromSouce();

                try {

                    loadjsonnews.dataParser(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                Intent dialogIntent = new Intent(DownloadSourceNewsService.this, ThirdListViewNewsActivity.class);
                dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(dialogIntent);
            }
        }


        private class LoadNewsJsonFromSouce {

            public LoadNewsJsonFromSouce() {
            }

            public void dataParser(String jsonData) throws JSONException {

                JSONObject jsonRootObject= new JSONObject(jsonData);

                SingeltonArrayListClass.sourceNewsArrayList= new ArrayList<LoadSourceNewsGetterSetter>();

                String status= jsonRootObject.getString("status");

                if(status.equals("ok")){

                    JSONArray jsonArray= jsonRootObject.getJSONArray("articles");

                    for(int k=0; k<jsonArray.length(); k++){

                        JSONObject jsonObject= (JSONObject) jsonArray.get(k);
                        String author= jsonObject.getString("author");
                        String title= jsonObject.getString("title");
                        String url= jsonObject.getString("url");
                        String urlToImage= jsonObject.getString("urlToImage");
                        String publishedAt= jsonObject.getString("publishedAt");

                        Log.d(author, url);

                        LoadSourceNewsGetterSetter getset= new LoadSourceNewsGetterSetter();

                        getset.setAuthor(author);
                        getset.setTitle(title);
                        getset.setUrl(url);
                        getset.setUrl_Image(urlToImage);
                        getset.setPublished_At(publishedAt);

                        SingeltonArrayListClass.sourceNewsArrayList.add(getset);
                    }



                }


            }

        }
   }

