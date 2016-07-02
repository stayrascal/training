package com.thoughtworks.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestContextUtil {

    private static Logger logger = LoggerFactory.getLogger(RequestContextUtil.class);

    private RequestContextUtil() {
    }

    private static ThreadLocal<String> usernames = new ThreadLocal<>();

    public static String getUsername() {
        return usernames.get();
    }

    public static void setUsername(String username) {
        usernames.set(username);
        logger.debug("RequestContextUtil added username {} to current thread", username);
    }

    public static void init() {
        usernames.set(null);
    }
}
