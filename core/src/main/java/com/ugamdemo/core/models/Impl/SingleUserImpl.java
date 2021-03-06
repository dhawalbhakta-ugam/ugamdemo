package com.ugamdemo.core.models.Impl;

import com.ugamdemo.core.models.Singleuser;
import com.ugamdemo.core.models.SingleuserOsgi;
import com.ugamdemo.core.utils.JSONLoaders;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Iterator;

@Model(adaptables = Resource.class,
        adapters = Singleuser.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SingleUserImpl implements Singleuser {

    final Logger log = LoggerFactory.getLogger(SingleUserImpl.class);

  @OSGiService
    SingleuserOsgi singleuserOsgi;


    @Inject
    String url;
    String fname;
    String lname;
    String email;
    String avatar;


    @Override
    public String getUrl(){
        return singleuserOsgi.getUserLinkData()+url;
    }


    @Override
    public String getMessage() throws IOException, JSONException {

        String response = JSONLoaders.readJson(getUrl());

        JSONObject jsonObject =  new JSONObject(response);

        Iterator x = jsonObject.keys();
        JSONArray jsonArray = new JSONArray();
        while (x.hasNext()){
            String key = (String) x.next();
            jsonArray.put(jsonObject.get(key));
        }
        email = jsonArray.getJSONObject(0).getString("email");
        fname=jsonArray.getJSONObject(0).getString("first_name");
        lname=jsonArray.getJSONObject(0).getString("last_name");
        avatar=jsonArray.getJSONObject(0).getString("avatar");
        return response;
    }


    @Override
    public String getFname() {
        return fname;
    }

    @Override
    public String getLname() {
        return lname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getAvatar() {
        String[] imgno = avatar.split("faces/");
        String Dell = imgno[1];

        // String imgPath = avatar.replaceAll("https://reqres.in/img/faces/","/content/dam/ugamdemo/");
        // return imgPath;
        //String imgPath = avatar.replaceAll(singleUserOsgi.getUserLinkImage(), "/content/dam/project/");


        StringBuffer imgPath = new StringBuffer(avatar);
        imgPath.delete(0,27).insert(0,singleuserOsgi.getDamPath());
        log.info(imgPath.toString());
        return imgPath.toString();
    }

}
