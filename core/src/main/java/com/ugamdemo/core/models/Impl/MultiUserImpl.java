package com.ugamdemo.core.models.Impl;


import com.ugamdemo.core.models.MultiUser;
import com.ugamdemo.core.models.MultiUserOsgi;
import com.ugamdemo.core.utils.JSONLoaders;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;


    @Model(adaptables = Resource.class,adapters = MultiUser.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

    public class MultiUserImpl implements MultiUser{

        @OSGiService
        MultiUserOsgi multipleUserOsgi;

        private static final Logger log = (Logger) LoggerFactory.getLogger(MultiUserImpl.class);

        @Inject
        String url;

        @Override
        public List<Map<String, String>> takeUser () throws JSONException, IOException {
            String response = JSONLoaders.readJson(getUrl(multipleUserOsgi.getMultipleLinkData()));
            JSONObject jsonObject =  new JSONObject(response);

            log.info(String.valueOf(jsonObject));

            JSONArray jsonArray1 = jsonObject.getJSONArray("data");
            log.info("==============="+jsonArray1);
            log.info("==============="+jsonArray1.length());

            List<Map<String, String>> userList = new ArrayList<>();
            for (int i=0;i<jsonArray1.length();i++){
                Map<String,String> user =new HashMap<>();
                user.put("fname",jsonArray1.getJSONObject(i).getString("first_name"));
                user.put("lname",jsonArray1.getJSONObject(i).getString("last_name"));
                user.put("email",jsonArray1.getJSONObject(i).getString("email"));
                user.put("avatar",jsonArray1.getJSONObject(i).getString("avatar"));
                userList.add(user);
            }
            log.info("===list===\n"+userList);
            return userList;
        }

        @Override
        public String getUrl(String initialPath) {
            return initialPath+url;
        }
    }


