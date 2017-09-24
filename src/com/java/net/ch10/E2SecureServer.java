package com.java.net.ch10;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.Socket;
import java.security.KeyStore;
import java.util.Arrays;

public class E2SecureServer {

    private final static int PORT = 8090;
    private final static String algorithm = "SSL";

    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algorithm);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");
            char[] password = {'2', 'a', 'n', 'd', 'n', 'o', 't', 'a', 'f', 'n', 'o', 'r', 'd'};
//            char[] password = System.console().readPassword(); in IDE this produces NullPointerException
            ks.load(new FileInputStream("jnp4e.keys"), password);
            kmf.init(ks, password);
            context.init(kmf.getKeyManagers(), null, null);

            Arrays.fill(password, '0');
            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PORT);

            String[] supported = server.getSupportedCipherSuites();
            String[] anonCipherSuitesSupported = new String[supported.length];
            int numAnonCipherSuitesSupported = 0;
            for (int i = 0; i < supported.length; i++) {
                if (supported[i].indexOf("_anon_") > 0) {
                    anonCipherSuitesSupported[numAnonCipherSuitesSupported++] = supported[i];
                }
            }

            String[] oldEnabled = server.getEnabledCipherSuites();
            String[] newEnabled = new String[oldEnabled.length + numAnonCipherSuitesSupported];

            System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
            System.arraycopy(anonCipherSuitesSupported, 0, newEnabled, oldEnabled.length, numAnonCipherSuitesSupported);
            server.setEnabledCipherSuites(newEnabled);

            while (true) {
                try (Socket connection = server.accept()) {

                    Writer out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
                    out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
                    out.write("<BODY><H1>Document moved</H1>\r\n");
                    out.write("The document "
                            + "has moved to \r\n<A HREF=\"" + "\">"
                            + "</A>.\r\n Please update your bookmarks<P>");
                    out.write("</BODY></HTML>\r\n");
                    out.flush();

                    InputStream in = connection.getInputStream();
                    int c;
                    while ((c = in.read()) != -1) {
                        System.out.write(c);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
