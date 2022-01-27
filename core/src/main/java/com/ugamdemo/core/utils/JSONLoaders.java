package com.ugamdemo.core.utils;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class JSONLoaders {
    public static String readJson(String url){
        StringBuffer output = new StringBuffer();
        try{
            URL urlupdate = new URL(url);
            Scanner scanner = new Scanner(urlupdate.openStream());
            String line = "hello";
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                output.append(line);
            }
            return output.toString();
        }catch (MalformedURLException malformedURLException){

        }catch (IOException ioException){

        }
        return "Error";

    }
}