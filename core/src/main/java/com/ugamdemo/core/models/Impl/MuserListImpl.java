package com.ugamdemo.core.models.Impl;
import com.ugamdemo.core.models.MuserList;
import com.ugamdemo.core.utils.JSONLoaders;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.util.*;

@Model(adaptables = Resource.class,
        adapters = MuserList.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class MuserListImpl implements MuserList {

    final Logger log = LoggerFactory.getLogger(MuserListImpl.class);

    @Inject
    String url;

    @Override
    public List<Map<String, String>> getData() throws JSONException, IOException {

        String response = JSONLoaders.readJson(getUrl());
        JSONObject jsonObject =  new JSONObject(response);

        log.info(String.valueOf(jsonObject));

        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
        log.info("==============="+jsonArray1);
        log.info("==============="+jsonArray1.length());

        List<Map<String, String>> javawithmultipleuser = new ArrayList<>();
        for (int i=0;i<jsonArray1.length();i++){
            Map<String,String> user =new HashMap<>();
            user.put("fname",jsonArray1.getJSONObject(i).getString("first_name"));
            user.put("lname",jsonArray1.getJSONObject(i).getString("last_name"));
            user.put("email",jsonArray1.getJSONObject(i).getString("email"));
            user.put("avatar",jsonArray1.getJSONObject(i).getString("avatar"));
            javawithmultipleuser.add(user);
        }
        log.info("===list==="+javawithmultipleuser);
        return javawithmultipleuser;
    }

    @Override
    public String getUrl() {
        return "https://reqres.in/api/users?page="+url;
    }
}












