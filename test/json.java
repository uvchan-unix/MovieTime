package test;

import org.json.JSONObject;

public class json {
    public static void main(String[] args) {
    
    String json = "{\"heroname\": \"Ranveer Singh\", \"heroinname\": \"Alia Bhatt\", \"director\": \"Zoya Akhtar\", \"moviedescription\": \"A coming-of-age story based on the lives of street rappers in Mumbai.\", \"duration\": \"153 mins\", \"language\": \"Hindi\"}";

    JSONObject jsonobj = new JSONObject(json);

    System.out.println(json);
    
        
    }
}
