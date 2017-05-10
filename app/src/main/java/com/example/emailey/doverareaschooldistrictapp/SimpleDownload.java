package com.example.emailey.doverareaschooldistrictapp;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsengia on 5/10/2017.
 */

public class SimpleDownload {

    public static byte[] readInputStream(InputStream in) throws IOException {
        List<Byte> bytes = new ArrayList<Byte>();
        byte b = 0;
        while((b = (byte) in.read()) != -1) { // Assign b to the next byte until the next byte is -1, which means the end of the stream has been reached
            bytes.add(b); // Add b to the list of bytes read
        }
        in.close(); // Close the InputStream
        byte[] data = new byte[bytes.size()]; //Make a byte array that has jus enough size to hold all of the bytes we have read already
        for(int i = 0; i < bytes.size(); i++) { // Iterate through the List and move each byte to our array
            data[i] = bytes.get(i);
        }
        return data; // Return the array of bytes
    }
//TODO: Network activity must happen inside of a AsyncTask object
    public static byte[] getUrl(String address) throws Exception {
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // We have opened a connection, but nothing has been transmitter, we have time to set an parameters for our connection
        conn.setConnectTimeout(3000);
        conn.setReadTimeout(3000);
        conn.connect(); // Parameters set, start the connection
        int response = conn.getResponseCode();
        if(response == HttpURLConnection.HTTP_OK) { // Check to make sure we actually get a response and not an error
            byte[] bytes = readInputStream(conn.getInputStream()); // grab the InputStream and leech out the bytes in the response's content
            conn.disconnect(); // Disconnect/end the HTTP connection
            return bytes;
        }
        else { // Throw an exception with the HTTP response code
            throw new Exception("HTTP Request failed: " + Integer.toString(response));
        }
    }
}
