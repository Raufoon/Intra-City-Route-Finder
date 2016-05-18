package com.example.rauf00n.intracityroutefinder.AI;

import android.app.Activity;
import android.util.Log;

import com.example.rauf00n.intracityroutefinder.AI.Algo.Cheapest;
import com.example.rauf00n.intracityroutefinder.AI.Algo.Dijakstra;
import com.example.rauf00n.intracityroutefinder.AI.Algo.Fastest;
import com.example.rauf00n.intracityroutefinder.AI.Util.Constraint;
import com.example.rauf00n.intracityroutefinder.AI.Util.OutputNode;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Bus;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Edge;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Node;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.TrafficFactor;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/15/2016.
 */
public class Machine {
    public static Machine machine = null;
    public Output output;

    // XML data
    public ArrayList<Node>nodes;
    public ArrayList<Edge>edges;
    public ArrayList<Bus>buses;

    /*
    Inputs
    */
    String constraint;
    String source;
    String destination;

    public int time;
    public static int MORNING=1;
    public static int NOON=2;
    public static int NIGHT=3;



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
    public void giveInput(String source, String destination, String constraint, int HR, int MN)
    {
        this.constraint = constraint;
        this.source = source;
        this.destination = destination;

        findTimeOfDay(HR,MN);


    }
    void findTimeOfDay(int HR, int MN)
    {
        Log.w("~~~~~~~~~~~~0",HR+" "+MN+" "+TrafficFactor.m_H+" "+TrafficFactor.m_M);
        if(HR>= TrafficFactor.m_H && HR< TrafficFactor.n_H)
        {
            if(HR== TrafficFactor.m_H && MN < TrafficFactor.m_M )
            {
                // night
                time = NIGHT;
                Log.w("~~~~~~~~~~~~1","night");
                return;
            }
            // morning
            time = MORNING;
            Log.w("~~~~~~~~~~~~2","morning");
        }
        else if(HR>= TrafficFactor.n_H && HR<TrafficFactor.N_H)
        {
            if(HR== TrafficFactor.n_H && MN < TrafficFactor.n_M )
            {
                // morning
                time = MORNING;
                Log.w("~~~~~~~~~~~~3","morning");
                return;
            }
            // noon
            time = NOON;
            Log.w("~~~~~~~~~~~~4","noon");
        }
        else if(HR>= TrafficFactor.N_H || (HR>=0 && HR<TrafficFactor.m_H ))
        {
            if(HR<= 24 && HR== TrafficFactor.N_H && MN < TrafficFactor.N_M )
            {
                // noon
                time = NOON;
                Log.w("~~~~~~~~~~~~5","noon");
                return;
            }
            // night
            time = NIGHT;
            Log.w("~~~~~~~~~~~~6","night");
        }

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
        Log.w("!!!!!!!!",constraint);
        if(constraint.equals("Fastest Route"))
        {
            return FastestRoute();
        }
        else
        {
            return CheapestRoute();
        }
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


    Output FastestRoute()
    {
        Log.w("!!!!!!!!","enter method FR");
        output = new Output();
        Dijakstra dijakstra = new Dijakstra();
        int sn=-1, dn=-1;
        for(Node n: nodes)
        {
            if(n.Name.equals(this.source))
            {
                sn = n.id;
                break;
            }
        }
        for(Node n: nodes)
        {
            if(n.Name.equals(this.destination))
            {
                dn = n.id;
                break;
            }
        }
        Fastest fastest = new Fastest();
        ArrayList path = fastest.FastestRoute(sn,dn);

        for(Object o: path)
        {
            int p = Integer.parseInt(o.toString());

            for(Node n: nodes)
            {
                if(n.id == p)
                {
                    output.addNodes(new OutputNode(p,n.Name, n.Lat,n.Long,""));

                    break;
                }
            }
        }

        return  output;
    }

    Output CheapestRoute()
    {
        output = new Output();
        int sn=-1, dn=-1;
        for(Node n: nodes)
        {
            if(n.Name.equals(this.source))
            {
                sn = n.id;
                break;
            }
        }
        for(Node n: nodes)
        {
            if(n.Name.equals(this.destination))
            {
                dn = n.id;
                break;
            }
        }
        Cheapest cheapest = new Cheapest();
        ArrayList path = cheapest.CheapestRoute(sn,dn);

        int[][] minbus = cheapest.min_bus;
        output.minbus = minbus;

        for(Object o: path)
        {
            int p = Integer.parseInt(o.toString());

            for(Node n: nodes)
            {
                if(n.id == p)
                {
                    output.addNodes(new OutputNode(p,n.Name, n.Lat,n.Long,""));

                    break;
                }
            }
        }

        return  output;
    }

    public int getDijakstraCost(int I,int J)
    {
        for(Edge e: this.edges)
        {
            if(e.Src==I && e.Dest == J)
            {
                if(time==MORNING) return e.trafficFactor.morning;
                else if(time==NOON) return e.trafficFactor.noon;
                else if(time==NIGHT) return e.trafficFactor.night;
                break;
            }
        }
        return  -1;
    }

}
