package com.ugamdemo.core.models;


import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

    public interface MultiUser {
        public List<Map<String,String>> takeUser() throws JSONException, IOException;
        public String getUrl();
    }


