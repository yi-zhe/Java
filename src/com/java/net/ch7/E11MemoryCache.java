package com.java.net.ch7;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class E11MemoryCache extends ResponseCache {

    private final Map<URI, E10SimpleCacheResponse> responses = new ConcurrentHashMap<>();
    private final int maxEntries;

    public E11MemoryCache() {
        this(100);
    }

    public E11MemoryCache(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    @Override
    public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException {
        if("GET".equals(rqstMethod)) {
            E10SimpleCacheResponse response = responses.get(uri);
            if(response!=null && response.isExpired()) {
                responses.remove(response);
                response = null;
            }
            return response;
        }
        return null;
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        if (responses.size() > maxEntries) return null;

        E6CacheControl control = new E6CacheControl(conn.getHeaderField("Cache-Control"));
        if (control.isNoStore()) {
            return null;
        } else if (!conn.getHeaderField(0).startsWith("GET")) {
            return null;
        }

        E8SimpleCacheRequest request = new E8SimpleCacheRequest();
        E10SimpleCacheResponse response = new E10SimpleCacheResponse(request, conn, control);

        responses.put(uri, response);
        return request;
    }

    public static void main(String[] args) {
        ResponseCache.setDefault(new E11MemoryCache());
        System.out.println(ResponseCache.getDefault().getClass().getName());
    }
}
