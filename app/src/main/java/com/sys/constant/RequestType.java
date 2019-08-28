package com.sys.constant;

import okhttp3.MediaType;

public class RequestType {

    public static final String BASE_URL = "http://10.17.191.55";
    public static final MediaType MEDIA_TYPE_NORMAL = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
    public static final MediaType MEDIA_TYPE_FORM = MediaType.parse("multipart/form-data;charset=utf-8");
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType MEDIA_TYPE_TEXT= MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
    public static final MediaType MEDIA_TYPE_STREAM = MediaType.parse("application/octet-stream");
    public static final int TYPE_GET = 0;
    public static final int TYPE_POST_JSON = 1;
    public static final int TYPE_POST_FORM = 2;
}