package com.example.guertz.tp3.Tools.LogicalCode;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by guertz on 26/08/17.
 */

public final class AsyncRequest  {
    private Context ctx;
    private Boolean showDialog;
    private ProgressDialog dialog;

    public AsyncRequest(Context ctx,Boolean showDialog,String... dialogMessage) {
        this.ctx = ctx;
        this.showDialog = showDialog;
        if (showDialog) {
            dialog = new ProgressDialog(ctx);
            dialog.setMessage(dialogMessage.length > 0 ? dialogMessage[0] : "");
            dialog.setProgressStyle(2);
        }
    }

    public class GET    extends AsyncTask<String,String,Boolean> {
        private String url;
        private RequestCommand command;

        public GET(String url,RequestCommand command){
            this.url = url;
            this.command = command;
        }

        @Override
        protected void onPreExecute(){
            if(showDialog) dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... args) {
            try {
                return getData();
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(showDialog)
                if (dialog.isShowing())
                    dialog.dismiss();

            command.onPostExecute(result);
        }

        @NonNull
        private Boolean getData()  throws Exception {
            command.onPreExecute();
            boolean error = false;
            JSONObject jsonObject = null;
            URL url = new URL(this.url);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(url.openStream()));
            } catch (Exception e) {
                error = true;
            }
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null)
                sb.append(line+"\n");

            br.close();

            jsonObject = new JSONObject(sb.toString());
            if (error) {
                command.onFailure(jsonObject);
                return false;
            }
            command.onSuccess(jsonObject);
            return true;
        }
    }

    public class POST   extends AsyncTask<String,String,Boolean> {
        private RequestCommand command;
        private String url_adress;
        private String postData;

        public POST(String url, String postData,RequestCommand command) {
            this.url_adress = url;
            this.postData = postData;
            this.command = command;
        }

        @Override
        protected void onPreExecute() {
            if(showDialog) dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                return sendData();
            } catch (Exception e) {
                return false;
            }
        }

        private Boolean sendData() throws Exception {
            command.onPreExecute();
            boolean error = false;
            JSONObject jsonObject = null;
            URL url = new URL(url_adress);
            HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            urlConnection.connect();

            OutputStream out = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            writer.write(postData);
            writer.flush();
            writer.close();
            out.close();

            InputStream in;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (Exception e) {
                in = new BufferedInputStream(urlConnection.getErrorStream());
                error = true;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String line = null;
            StringBuilder reponseJSON = new StringBuilder();

            while ((line = reader.readLine()) != null)
                reponseJSON.append(line + "\n");

            reader.close();
            in.close();
            urlConnection.disconnect();

            jsonObject = new JSONObject(reponseJSON.toString());
            if (error) {
                command.onFailure(jsonObject);
                return false;
            }
            command.onSuccess(jsonObject);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(showDialog)
                if (dialog.isShowing())
                    dialog.dismiss();

            command.onPostExecute(result);
        }
    }

    public class DELETE extends AsyncTask<String,String,Boolean> {
        @Override
        protected void onPreExecute() {
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialog.dismiss();
        }
    }

    public class PUT    extends AsyncTask<String,String,Boolean> {
        private String url;
        private String putData;
        private RequestCommand command;

        public PUT(String url,String putData,RequestCommand command) {
            this.url = url;
            this.putData = putData;
            this.command = command;
        }

        @Override
        protected void onPreExecute() {
            if (showDialog) dialog.show();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                return modifyData();
            } catch(Exception e) {
                return false;
            }
        }

        private Boolean modifyData() throws Exception {
            command.onPreExecute();
            boolean error = false;
            URL url = new URL(this.url);
            HttpURLConnection urlConnection;

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("PUT");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            urlConnection.connect();

            OutputStream out = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));

            writer.write(putData);
            writer.flush();
            writer.close();
            out.close();

            InputStream in;
            try {
                in = new BufferedInputStream(urlConnection.getInputStream());
            } catch (Exception e) {
                in = new BufferedInputStream(urlConnection.getErrorStream());
                error = true;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
            String line = null;
            StringBuilder reponseJSON = new StringBuilder();

            while ((line = reader.readLine()) != null)
                reponseJSON.append(line + "\n");

            reader.close();
            in.close();
            urlConnection.disconnect();

            final JSONObject jsonObject = new JSONObject(reponseJSON.toString());
            if (error) {
                ((Activity)ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            command.onFailure(jsonObject);
                        } catch (Exception e) {
                            Log.e("Error OnFailure",e.getMessage());
                        }
                    }
                });
                return false;
            }
            ((Activity)ctx).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        command.onSuccess(jsonObject);
                    } catch(Exception e) {
                        Log.e("ErrorSuccess",e.getMessage());
                    }
                }
            });
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(showDialog)
                if(dialog.isShowing())
                    dialog.dismiss();
            command.onPostExecute(result);
        }
    }
}
