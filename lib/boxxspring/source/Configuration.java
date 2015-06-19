package com.boxxspring;

import java.net.*;

public class Configuration
{
    protected URL url;
    private static Configuration SingletonHolder=new Configuration();
   
    private Configuration(){
    }
    public static Configuration getInstance(){
        if( SingletonHolder == null )
            SingletonHolder = new Configuration();
        
        return SingletonHolder;
    }  
    public void setURL( String address ) {
        try{
            url = new URL ( address );
        }
        catch ( Exception e ){       
        }
            
    }
    public URL getURL() {
        return url;
    }
    
}

