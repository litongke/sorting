package com.chufinfo.sorting.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamRunnable implements Runnable {

    BufferedReader bReader = null;
    String type = null;

    public InputStreamRunnable(InputStream is, String _type) {
        try {
            bReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), "UTF-8"));
            type = _type;
        } catch (Exception ex) {
        }
    }

    @SuppressWarnings("unused")
    public void run() {
        String line;
        int lineNum = 0;

        try {
            while ((line = bReader.readLine()) != null) {
                lineNum++;
                // Thread.sleep(200);
            }
            bReader.close();
        } catch (Exception ex) {
        }
    }
}
