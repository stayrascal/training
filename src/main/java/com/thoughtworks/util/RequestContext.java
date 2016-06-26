package com.thoughtworks.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestContext {

    private static Logger logger = LoggerFactory.getLogger(RequestContext.class);

    private RequestContext() {
    }

    private static ThreadLocal<String> usernames = new ThreadLocal<>();

    public static String getUsername() {
        return usernames.get();
    }

    public static void setUsername(String username) {
        usernames.set(username);
        logger.debug("RequestContext added username {} to current thread", username);
    }

    public static void init() {
        usernames.set(null);
    }
}
