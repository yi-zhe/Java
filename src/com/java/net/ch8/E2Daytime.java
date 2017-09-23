package com.java.net.ch8;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class E2Daytime {

    public static void main(String[] args) throws IOException, ParseException {
        try (Socket socket = new Socket("time.nist.gov", 13)) {
            socket.setSoTimeout(3000);

            InputStream in = socket.getInputStream();
            StringBuilder builder = new StringBuilder();

            InputStreamReader reader = new InputStreamReader(in);

            for (int c = reader.read(); c != -1; c = reader.read()) {
                builder.append((char) c);
            }

            System.out.println(parseDate(builder.toString()));
        }
    }

    private static Date parseDate(String s) throws ParseException {
        String[] pieces = s.split(" ");
        String dateTime = pieces[1]+" "+pieces[2]+" UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }
}
