package com.example.abhinav.hanselpatelgson;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnGetjson;
    String json_string;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        btnGetjson = (Button) findViewById(R.id.button);
        progressBar.setVisibility(View.INVISIBLE);
        isInternetOn();
        btnGetjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInternetOn()) {
                    progressBar.setVisibility(View.VISIBLE);
                    if (json_string == null)
                        new BackgroundTask().execute();
                } else {



                }
            }
        });
    }

    //Function to check internet connection
    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            // if connected with internet
            btnGetjson.setVisibility(View.VISIBLE);

            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            btnGetjson.setVisibility(View.INVISIBLE);
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Connect to internet");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);

                }

            });
            AlertDialog dialog = builder.create();
            dialog.show();


            return false;
        }
        return false;
    }

    //Class to request json from api
    class BackgroundTask extends AsyncTask<Void, Void, String> {
        String jsonUrl;

        @Override
        protected void onPreExecute() {
            jsonUrl = "http://services.hanselandpetal.com/feeds/flowers.json";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(jsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;
            Log.i("Json data: ", json_string);
            Gson gson = new Gson();

            Type type = new TypeToken<List<Flowers>>() {
            }.getType();
            List<Flowers> fromJson = gson.fromJson(json_string, type);
            for (Flowers flowers : fromJson) {
                Log.i("parsing", flowers.toString());
            }
            ArrayList<Flowers> arrayList = new ArrayList<Flowers>(fromJson);
            Log.i("ArrayList", arrayList.toString());
            recyclerView = (RecyclerView) findViewById(R.id.recycleView);
            adapter = new MyRecyclerAdapter(arrayList, MainActivity.this);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(MainActivity.this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            progressBar.setVisibility(View.INVISIBLE);
            btnGetjson.setEnabled(false);
        }
    }
}

