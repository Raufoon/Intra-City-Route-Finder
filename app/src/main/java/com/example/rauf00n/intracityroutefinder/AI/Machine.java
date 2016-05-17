package com.example.rauf00n.intracityroutefinder.AI;

import android.app.Activity;

import com.example.rauf00n.intracityroutefinder.AI.Util.Constraint;
import com.example.rauf00n.intracityroutefinder.AI.Util.OutputNode;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Bus;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Edge;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Node;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/15/2016.
 */
public class Machine {
    public static Machine machine = null;

    // XML data
    public ArrayList<Node>nodes;
    public ArrayList<Edge>edges;
    public ArrayList<Bus>buses;

    /*
    Inputs
    */
    Constraint constraint;
    String source;
    String destination;

    /* no need to look at bellow section, i just made it singleton which is unnecessary*/
    private Machine()
    {

    }

    public static Machine getInstance()
    {
        if(machine == null) machine = new Machine();
        return machine;
    }
    public void ReadXML(Activity activity)
    {
        CityMapInfo cityMapInfo = new CityMapInfo(activity);
        this.nodes = cityMapInfo.nodes;
        this.edges = cityMapInfo.edges;
        this.buses = cityMapInfo.buses;
    }
    //.......................................

    /*
    this method receives a Source, Destination and Constraint object.
    the UI will pass user-given inputs in this method
    */
    public void giveInput(String source, String destination, Constraint constraint)
    {
        this.constraint = constraint;
        this.source = source;
        this.destination = destination;
    }

    /*
    UI will receive the output by calling this method.
    it will return a Output object
    */
    public Output getOutput()
    {
        return calculateRoute();
    }


    /*
    This is the method we will use to calculate our output as an Output object.
    We will use our AI from the inside of this function
    */
    Output calculateRoute()
    {
        //demo... delete them
        Output output = new Output();
        output.addNodes(new OutputNode("A",23.8103,90.4125,"")
                , new OutputNode("C",23.8120,90.4155,"")
                , new OutputNode("D",23.8110,90.4105,"")
        );
        return output;
    }

    /*
    these methods returns the nodes, edges and buses.
    to get a clear idea about the internal structure of these 3 objects,
    have a look at "/AI/XMLObjects/"
    * */
    ArrayList<Node> getNodes()
    {
        return nodes;
    }
    ArrayList<Edge> getEdges()
    {
        return edges;
    }
    ArrayList<Bus> getBuses()
    {
        return  buses;
    }

    double getDrivingDistance(String src, String dest)
    {
        return 1.0;
    }

}
