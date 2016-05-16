package com.example.rauf00n.intracityroutefinder.AI;

import android.app.Activity;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Bus;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Edge;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Node;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.TrafficFactor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/16/2016.
 */

/*
this class holds the graph of the city map.
this class provides interfaces to manipulate the graph which is stored by XML
*/
public class CityMapInfo {

    private XmlPullParser xmlPullParser;

    public ArrayList<Node>nodes;
    public ArrayList<Edge>edges;
    public ArrayList<Bus>buses;

    Activity activity;

    public CityMapInfo(Activity activity)
    {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        buses = new ArrayList<>();
        this.activity = activity;
        initXML();
    }

    /*
    you don't need to look at this code, i just parsed the XML
    */
    void initXML()
    {
        try{
            xmlPullParser = Xml.newPullParser();
            XmlPullParserFactory pullParserFactory;
            pullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = pullParserFactory.newPullParser();
            InputStream in_s = activity.getApplicationContext().getAssets().open("graph.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in_s, null);
            parseXML(parser);
        }
        catch (Exception e)
        {

        }
    }

    /* no need to look at this function*/
    private void parseXML(XmlPullParser parser) throws Exception
    {
        int eventType = parser.getEventType();

        ArrayList stoppages = new ArrayList();


        Node node=null;
        int id=-1;
        String Name=null;
        double Lat=0,Lng=0;


        Edge edge=null;
        int src=-1;
        int dest=-1;
        TrafficFactor trafficFactor = null;
        int morning=0,noon=0,night=0;

        Bus bus;
        int FareRate=1;

        while (eventType != XmlPullParser.END_DOCUMENT){
            String name = null;


            switch (eventType)
            {
                case XmlPullParser.START_DOCUMENT:
                    break;

                case XmlPullParser.START_TAG:
                    name = parser.getName();

                    if(name.equals("Stoppages")) stoppages.removeAll(stoppages);
                    else if(name.equals("id")) id = Integer.parseInt(parser.nextText());
                    else if(name.equals("Name")) Name = parser.nextText();
                    else if(name.equals("Lat")) Lat = Double.parseDouble(parser.nextText());
                    else if(name.equals("Lng")) Lng = Double.parseDouble(parser.nextText());

                    else if(name.equals("Src")) src = Integer.parseInt(parser.nextText());
                    else if(name.equals("Dest")) dest = Integer.parseInt(parser.nextText());
                    else if(name.equals("Morning")) morning = Integer.parseInt(parser.nextText());
                    else if(name.equals("Noon")) noon = Integer.parseInt(parser.nextText());
                    else if(name.equals("Night")) night = Integer.parseInt(parser.nextText());

                    else if(name.equals("FareRate")) FareRate = Integer.parseInt(parser.nextText());
                    else if(name.equals("Stoppage")) stoppages.add(parser.nextText());


                    break;

                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equals("Node"))
                    {
                        this.nodes.add(new Node(id,Name,Lat,Lng));
                    }
                    else if(name.equals("Edge"))
                    {
                        this.edges.add(new Edge(src,dest,trafficFactor));
                    }
                    else if(name.equals("Bus"))
                    {
                        this.buses.add(new Bus(id,Name,FareRate,stoppages));
                    }
                    else if(name.equals("TrafficFactor"))
                    {
                        trafficFactor = new TrafficFactor(morning,noon,night);
                    }
                    break;
            }
            eventType = parser.next();
        }
    }



    /* The interfaces you will use in the AI */

    // create a graph from the parsed XML data
    // use appropriate data structure
    void CreateGraph()
    {

    }




}
