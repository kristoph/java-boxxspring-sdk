package com.boxxspring;
import java.net.*;
import java.io.*;
import org.json.*;
public class Request
{
    protected URL requestURL;
    
    public Request( String path )
    {
        try {
            Configuration configuration = Configuration.getInstance();
 
            requestURL = new URL(configuration.getURL(), path);
        }
        catch ( Exception e ) {
        } 
    }

    public Response getResponse() throws Exception {

        HttpURLConnection connection = 
                ( HttpURLConnection ) requestURL.openConnection();
            connection.setRequestMethod( "GET" );
            connection.connect();
 
        return new Response( connection );
        
    }
    
    /**
    public static String getText(String url) throws Exception
    {
        URL website= new URL(url);
        URLConnection connection= website.openConnection();
        BufferedReader in= new BufferedReader(new InputStreamReader(connection.
                getInputStream()));

        StringBuilder response= new StringBuilder();
        String inputLine;

        while ((inputLine= in.readLine()) != null) 
            response.append(inputLine);
        
        in.close();

        return response.toString(); 
    }
    **/ 
}



