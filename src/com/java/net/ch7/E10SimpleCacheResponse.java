package com.java.net.ch7;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CacheResponse;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class E10SimpleCacheResponse extends CacheResponse {

    private final Map<String, List<String>> headers;
    private final E8SimpleCacheRequest request;
    private final Date expires;
    private final E6CacheControl control;

    public E10SimpleCacheResponse(E8SimpleCacheRequest request, URLConnection uc, E6CacheControl control) {
        this.headers = Collections.unmodifiableMap(uc.getHeaderFields());
        this.request = request;
        this.expires = new Date(uc.getExpiration());
        this.control = control;
    }

    @Override
    public Map<String, List<String>> getHeaders() throws IOException {
        return headers;
    }

    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(request.getData());
    }

    public boolean isExpired() {
        Date now = new Date();
        if (control.getMaxAge().before(now)) return true;
        else if (expires != null && control.getMaxAge() != null) {
            return expires.before(now);
        } else {
            return false;
        }
    }
}
