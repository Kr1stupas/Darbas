package com.example.penktaspraktinisdarbas;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private OnDataLoadListener listener;
    private List<String> allData;

    public DataLoader(OnDataLoadListener listener) {
        this.listener = listener;
        this.allData = new ArrayList<>();
        new DownloadDataTask().execute("http://www.floatrates.com/daily/usd.xml");
    }

    public interface OnDataLoadListener {
        void onDataLoaded(List<String> data);
    }

    public void filterData(String filter) {
        List<String> filteredData = new ArrayList<>();
        for (String item : allData) {
            if (item.toLowerCase().contains(filter.toLowerCase())) {
                filteredData.add(item);
            }
        }
        listener.onDataLoaded(filteredData);
    }

    private class DownloadDataTask extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... urls) {
            List<String> data = new ArrayList<>();
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                }

                data = Parser.parseXml(content.toString());
                allData.addAll(data);


                reader.close();
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(List<String> data) {
            super.onPostExecute(data);
            listener.onDataLoaded(allData); // Update UI with allData
        }
    }
}
