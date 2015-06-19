package com.boxxspring;

import java.io.*;
import java.net.*;
import java.lang.reflect.*;

import org.json.*;

public class Parse 
{
    protected JSONObject response;
    protected JSONObject responseDescription;
    protected String name;
    protected String typeName;
    protected JSONArray text;
    protected int[] resourceIds;
    
//    Param1Type param;
  //  Param2Type param2;
    
    public Parse( JSONObject response )
    {
        this.response = response;
        
        try {
            responseDescription = response.getJSONObject( "$this" );
        }
        catch ( Exception e ) {
            responseDescription = null;
        }
    }
    
    public String getName() {
        if ( name == null ) {
            if ( responseDescription != null ) {
                try {
                    name = responseDescription.getString( "name" );
                }
                catch( Exception e ) {       
                }
            }
        }
        
        return name;
    }
   
    public String getTypeName() {
        if ( typeName == null ) {
            if( responseDescription != null) {
                try {
                    typeName = responseDescription.getString( "type_name" );
                }
                catch(Exception e) {
                }
            }
        }
        
        return typeName;
    }
    
    /**
    public void setIDs() throws Exception
    {
       JSONArray array=responseDescription.getJSONArray("ids");

       int len=array.length();
       if(id.length>1)
       {
           for(int i=0;i<id.length;i++)
           {
               id[i]=array.getInt(i);
           }
       }
       else
       {
           id[0]=array.getInt(0);
       }
    }
    **/ 
  
    public int[] getIDs() {
        //return Arrays.toString(id);
        if ( resourceIds == null ) {
            try {
                    JSONArray ids = responseDescription.getJSONArray( "ids" );
                    int idsLength = ids.length();
                    this.resourceIds = new int[ idsLength ];
                    for ( int index = 0; index < idsLength; index++ ) {
                    this.resourceIds[ index ] = ids.getInt( index );
                }
            }
            catch( Exception e ) {
            }
        }
        return resourceIds;
    }
    
    public Object[] getResources() {
        
        int[] ids = this.getIDs();
        Object[] resources = new Object[ ids.length ];
        boolean exists = false;
        try {
            JSONArray resourcesAsJson = response.getJSONArray( this.getName() );
            //int len = jArray.length();
            
            JSONArray id = responseDescription.getJSONArray( "ids" );
            int idsLength = id.length();
            
            for(int i = 0; i < idsLength; i++ ) {
                if( resourcesAsJson.getJSONObject( i ).getInt( "id" ) == id.getInt(i) )
                    resources[i]=resourcesAsJson.getJSONObject(i);
            }
        }    
        catch ( Exception e ){           
        }
        
        return resources;
        
    }
    

    protected Object realizeResource() throws Exception {
        //article_atrtifact 
        //ArticleArtifact 
        //com.boxxspring.ArticleArtifact
        //articleArtifact = resourceClass.newInsrance( attributes );
        
        String className = "Article_Artfact";
        Class newClass = Class.forName(className);
        
        // // Constructor classConstructor = newClass.getConstructor
        // //     (Param1Type.class, Param2Type.class);
        
        // // Object articleArtifact = classConstructor.newInstance(param1, param2);
        
        // return articleArtifact;
        return null;
        
    }
   
    /**
    public boolean isExisiting(int num) throws Exception {
        boolean exists = false;
        JSONArray jArray = response.getJSONArray( name );
        //int len = jArray.length();
            
        JSONArray ids = responseDescription.getJSONArray( "ids" );
        int idsLength = ids.length();
            
        for(int i = 0; i < idsLength; i++ ) {
            if( jArray.getJSONObject( i ).getInt( "id" ) == num )
                exists = true;
        }    
        
        return exists;
    }
    **/
            
    public JSONArray getText() {
        try {
            text = response.getJSONArray(name);
        }
        catch ( Exception e) {
        }
        
         return text;
    }
}

/**
var resourcesAsJson = response.getJSONArray( this.getName() );
var resourceIds = response.getIds();

for
**/ 