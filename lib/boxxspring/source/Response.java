package com.boxxspring;

import java.net.*;
import java.io.*;

import org.json.*;

public class Response
{
    protected int responseCode;
    protected JSONObject body;
   
    public Response( HttpURLConnection connection ) throws Exception {   
        responseCode = connection.getResponseCode();

        String body = readBody( connection );
        if ( body != null && !body.isEmpty() ) {
            try {
                this.body = new JSONObject( body );
            }
            catch( Exception e ) {
            }
        }
    }
    
    public boolean isSuccessful() throws Exception {
        return responseCode >= 200 && 
               responseCode <= 299 &&
               body != null &&
               !body.getJSONObject( "$this" ).getString( "name" ).
                       equals( "errors" );
    }
   
    public JSONObject getBody() {
        return this.body;
    }
 
    protected String readBody( HttpURLConnection connection ) throws Exception {
        BufferedReader in = new BufferedReader(
            new InputStreamReader( connection.getInputStream() ) );

        StringBuilder response= new StringBuilder();
        String inputLine;

        while ( (inputLine= in.readLine() ) != null) 
            response.append(inputLine);
        
        in.close();

        String str=response.toString();
        
        return str;
    }   
}