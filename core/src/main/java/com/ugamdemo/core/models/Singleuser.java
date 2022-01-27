package com.ugamdemo.core.models;

import org.json.JSONException;

import java.io.IOException;


public interface Singleuser {

    public String getMessage() throws JSONException, IOException;
    public String getUrl();
    public String getFname();
    public String getLname();
    public String getEmail();
    public String getAvatar();

}
