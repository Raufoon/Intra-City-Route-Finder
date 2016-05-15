package com.example.rauf00n.intracityroutefinder.AI;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Created by RAUF00N on 5/16/2016.
 */

/*
this class holds the graph of the city map.
this class provides interfaces to manipulate the graph which is stored by XML
*/
public class CityMapInfo {

    public static CityMapInfo instance = null;
    private static XmlPullParserFactory xmlFactoryObject ;
    private static XmlPullParser myparser;

    private CityMapInfo()
    {

    }

    public static CityMapInfo getInstance()
    {
        if(instance == null)
        {
            try{
                xmlFactoryObject = XmlPullParserFactory.newInstance();
                myparser = xmlFactoryObject.newPullParser();
            }
            catch (Exception e)
            {

            }

        }
        return instance;
    }
}
