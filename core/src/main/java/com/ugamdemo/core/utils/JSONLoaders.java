package com.ugamdemo.core.utils;

        import org.slf4j.ILoggerFactory;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import java.io.IOException;
        import java.net.MalformedURLException;
        import java.net.URL;
import java.util.Scanner;

public class JSONLoaders {


    private static final Logger log = LoggerFactory.getLogger(JSONLoaders.class);

    public static String readJson(String url) {
        StringBuffer output = new StringBuffer();
        try{
            log.info("before url");
            URL url1 = new URL(url);
            log.info("after url");
            Scanner scanner = new Scanner(url1.openStream()); //error is occuring here
            log.info("after scanner");
            //BufferedReader br = new BufferedReader(new InputStreamReader(url1.openStream()));
            String line = "";
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                output.append(line);
            }
            return output.toString();
        }catch (MalformedURLException malformedURLException){
            log.info("inside 1st catch");

        }catch (IOException ioException){
            log.info("inside 2nd catch");

        }
        return "There was no data found";

    }
}