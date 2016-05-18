package com.example.rauf00n.intracityroutefinder.AI.Util;

import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Edge;

import java.util.ArrayList;

import javax.crypto.Mac;

/**
 * Created by RAUF00N on 5/15/2016.
 */

/*
This class will contain the result calculated by our AI.
It must contain a 'PATH' from source to destination nodes
*/
public class Output {
    ArrayList<OutputNode>path;
    public int[][] minbus=null;

    public Output()
    {
        path = new ArrayList<>();
    }

    /*
    pass the nodes (calculated by AI) in this method from source to destination
    */
    public void addNodes(OutputNode... nodes)
    {
        for (OutputNode node : nodes) {
            path.add(node);
        }
    }
    public int countNodes()
    {
        return path.size();
    }
    public ArrayList<OutputNode> getPath()
    {
        return path;
    }

    public  int cost;
    public void constructMessages()
    {
        cost=0;
        for(int i=0;i<path.size() -1;i++)
        {
            OutputNode nod = path.get(i);
            OutputNode nextnod = path.get(i+1);
            ArrayList buses = new ArrayList();

            if(minbus==null) {
                for (Edge e : Machine.getInstance().edges) {
                    if (nod.id == e.Src && nextnod.id == e.Dest || nod.id == e.Dest && nextnod.id == e.Src) {
                        buses.add(e.bus_ids);

                        break;
                    }
                }
            }
            else{
                buses.add(minbus[nod.id][nextnod.id]);
            }




            String B="";
            for(Object o: buses)
            {
                B += o.toString();
            }

            nod.msg = "Stoppage Name: "+ nod.name+"\n" +
                    "Next Stoppage: "+ nextnod.name +"\n" +
                    "Buses: "+B;

        }
        path.get(path.size()-1).msg = "Destination: "+ path.get(path.size()-1).name;
    }
}
