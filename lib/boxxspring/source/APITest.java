package com.boxxspring;

import java.util.Arrays;
import java.lang.reflect.*;

import org.json.*;

public class APITest
{
    
    public static void main(String[] args) throws Exception {
        Configuration configuration = Configuration.getInstance();
        configuration.setURL( "http://api.boxxspring.com" );
        
        Request request = new Request( "properties/2/artifacts" );
        Response response = request.getResponse();
        Parse parse = new Parse( response.getBody() );
        
        System.out.println( response.getBody() );
        //System.out.println(parse.getName());

        System.out.println( parse.getName() );
        //System.out.println( parse.getText() );
        //parse.setIDs();
         
        parse.getIDs();
        System.out.println( Arrays.toString( parse.getResources() ) );
       
        //System.out.println( parse.isExisiting(0) );
        
        //System.out.println(parse.getTypeName());
      
        Class reflectClass = Parse.class; 
        
        int  classModifier = reflectClass.getModifiers();
        
        Method[] classMethods = reflectClass.getMethods();
        
        for(Method method : classMethods) {
            System.out.println("Method Name: " + method.getName() );
        }
        
        Object[] obj = parse.getResources();
        String[] str = new String[obj.length];
        for( int i =0; i < obj.length; i++) {
            str = JSONObject.getNames(obj[i]);

        }
        
        System.out.println(Arrays.toString(str));
        
    }
}

