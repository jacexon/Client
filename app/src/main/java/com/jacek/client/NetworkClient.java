package com.jacek.client;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Jacek on 21.05.2017.
 */

public class NetworkClient extends AsyncTask<String,Void,String> {
    String response = "a";
    String destinationAddress = "194.29.130.14";
    int destinationPort = 20001;
    TextView textResponse;


    public NetworkClient(TextView textResponse) {
        this.textResponse = textResponse;
    }

    @Override
    protected String doInBackground(String... params){
        Socket socket = null;
        Log.i("Probuje utworzyc socket", "probujee@1321312");
        try{
            Log.i("Probuje utworzyc socket", "probujee");
            socket = new Socket(destinationAddress, destinationPort);
            Log.i("Socket", "SOCKET UTWORZONY");
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            pw.write("Siemka");
        }
        catch(IOException e){
            Log.d("IOException", e.getMessage());
        }
        finally{
            if (socket != null) {
                try {
                    socket.close();
                    Log.i("ZAMYKANIE", "SOCKETA");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        textResponse.setText(response);
        super.onPostExecute(result);
    }
}
