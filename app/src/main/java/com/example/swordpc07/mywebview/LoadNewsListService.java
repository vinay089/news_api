package com.example.swordpc07.mywebview;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by swordpc07 on 1/17/2017.
 */


public class LoadNewsListService extends IntentService {

    //String URL_LOAD_NEWS_API= "https://newsapi.org/v1/sources?language=en";
    public LoadNewsListService(){
        super("Service to load News");
    }

    String URL_Load_DATA;
    String news_id, news_name, news_Description, news_url, news_category, news_Url_Logo;

    @Override
    protected void onHandleIntent(Intent intent) {

        Bundle b= intent.getExtras();

        URL_Load_DATA= b.getString("url");


        new Timer().schedule(new TimerTask(){
            public void run() {

                new GetWeatherDataDetails().execute(URL_Load_DATA);
            }
        }, 0);

    }

    private class GetWeatherDataDetails extends AsyncTask<String, String, String> {

        String loadnewsjson;

        String url;

        public GetWeatherDataDetails(){ }


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

            LoadNewsJson newsjsonData= new LoadNewsJson();

            try {

                newsjsonData.dataParser(result);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            Intent dialogIntent = new Intent(LoadNewsListService.this, StaggerdViewActivity.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);

        }
    }


    public class LoadNewsJson {

        public LoadNewsJson() {
        }

        public void dataParser(String jsonData) throws JSONException {

            JSONObject jsonRootObject= new JSONObject(jsonData);
            String status= jsonRootObject.getString("status");
            Log.d("status ", status);

            if(status.equals("ok")){

                SingeltonArrayListClass.allNewsArrayList= new ArrayList<LoadAllNewsGetterSetter>();

                JSONArray jsonRootArray= jsonRootObject.getJSONArray("sources");

                for(int count=0; count<jsonRootArray.length(); count++){

                    JSONObject jsonObject= (JSONObject) jsonRootArray.get(count);

                    news_id= jsonObject.getString("id");
                    news_name= jsonObject.getString("name");
                    news_Description= jsonObject.getString("description");
                    news_url= jsonObject.getString("url");
                    news_category= jsonObject.getString("category");

                    JSONObject jObj= jsonObject.getJSONObject("urlsToLogos");
                    news_Url_Logo= jObj.getString("small");

                    LoadAllNewsGetterSetter getset= new LoadAllNewsGetterSetter();

                    getset.setId(news_id);
                    getset.setName(news_name);
                    getset.setCategory(news_category);
                    getset.setDesription(news_Description);
                    getset.setUrl(news_url);
                    getset.setUrlLogo(news_Url_Logo);

                    SingeltonArrayListClass.allNewsArrayList.add(getset);

                   //Log.d(count+") id="+ news_id, "Desciption" + news_Description);




                }

            }
            else {
                Toast.makeText(LoadNewsListService.this, "Something Wrong !!", Toast.LENGTH_LONG).show();
            }



        }
    }


}

