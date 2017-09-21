package com.java.net.ch7;

import java.util.Date;
import java.util.Locale;

public class E6CacheControl {

    public Date getMaxAge() {
        return maxAge;
    }

    public Date getsMaxAge() {
        return sMaxAge;
    }

    public boolean isMustRevalidate() {
        return mustRevalidate;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public boolean isNoStore() {
        return noStore;
    }

    public boolean isProxyRevalidate() {
        return proxyRevalidate;
    }

    public boolean isPublicCache() {
        return publicCache;
    }

    public boolean isPrivateCache() {
        return privateCache;
    }

    private Date maxAge;
    private Date sMaxAge;
    private boolean mustRevalidate;
    private boolean noCache;
    private boolean noStore;
    private boolean proxyRevalidate;
    private boolean publicCache;
    private boolean privateCache;

    public E6CacheControl(String s) {
        if (s == null || s.contains(":")) return;

        String value = s.split(":")[1].trim();
        String[] components = value.split(",");

        Date now = new Date();
        for (String component : components) {
            try {
                component = component.trim().toLowerCase(Locale.US);
                if (component.startsWith("max-age=")) {
                    int secondsInTheFuture = Integer.parseInt(component.substring(8));
                    maxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
                } else if (component.startsWith("s-maxage=")) {
                    int secondsInTheFuture = Integer.parseInt(component.substring(8));
                    sMaxAge = new Date(now.getTime() + 1000 * secondsInTheFuture);
                } else if (component.equals("must-revalidate")) {
                    mustRevalidate = true;
                } else if (component.equals("proxy-revalidate")) {
                    proxyRevalidate = true;
                } else if (component.equals("no-cache")) {
                    noCache = true;
                } else if (component.equals("public")) {
                    publicCache = true;
                } else if (component.equals("private")) {
                    privateCache = true;
                }
            } catch (Exception e) {
            }
        }
    }

}
