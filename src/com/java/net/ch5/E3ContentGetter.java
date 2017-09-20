package com.java.net.ch5;

import java.io.IOException;
import java.net.URL;

public class E3ContentGetter {

    public static void main(String[] args) throws IOException {
        URL u = new URL("http://img002.21cnimg.com/photos/album/20150702/m600/2D79154370E073A2BA3CD4D07868861D.jpeg");
        Object o = u.getContent(); // getContent(Class[] classes) 尝试按照给定的类型解析
        System.out.println("I got a " + o.getClass().getName());
    }

}
