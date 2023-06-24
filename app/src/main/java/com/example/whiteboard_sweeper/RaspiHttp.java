package com.example.whiteboard_sweeper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RaspiHttp extends AsyncTask<String,Void,Void> {//ここが引数
                                                            //これはdoInBackgroundメソッドの引数の型, onProgressUpdateメソッドの引数の型, onPostExecuteメソッドの戻り値の型
    private Activity parentActivity;
    private ProgressDialog dialog=null;
    private final String DEFAULTURL="http://192.168.11.42:8000";
    private String uri=null;
    public RaspiHttp(Activity parentActivity){
        this.parentActivity=parentActivity;
    }
    @Override
    protected void onPreExecute(){

        dialog=new ProgressDialog(parentActivity);
        dialog.setMessage("通信中");
        dialog.show();
    }

    @Override
    protected  Void doInBackground(String...arg0){//ここが引数
        uri=DEFAULTURL+"/"+arg0[0];
        exec_get();
        return  null;
    }

    @Override
    protected void onPostExecute(Void result){
        dialog.dismiss();
    }

    private String exec_get(){
        Log.d("RaspiHttp",uri);
        HttpURLConnection http=null;
        InputStream in=null;
        String src=new String();
        try{
            URL url=new URL(uri);
            http=(HttpURLConnection) url.openConnection();
            http.setRequestMethod("GET");
            http.connect();

            in=http.getInputStream();
            byte[] line=new byte[1024];
            int size;
            while (true){
                size=in.read(line);
                if(size<=0)break;
                src+=new String(line);
            }
        }
        catch (Exception e){
            Log.d("RaspiHttp","ERROR");
            e.printStackTrace();
        }finally {
            try{
                if(http!=null)http.disconnect();
                if(in!=null)in.close();
            }catch (Exception e){}
        }
        return  src;
    }
}
