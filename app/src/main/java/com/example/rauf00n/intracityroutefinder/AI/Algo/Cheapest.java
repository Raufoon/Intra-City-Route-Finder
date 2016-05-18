package com.example.rauf00n.intracityroutefinder.AI.Algo;

import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Bus;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Edge;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Node;
import com.example.rauf00n.intracityroutefinder.MapsActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by RAUF00N on 5/18/2016.
 */
public class Cheapest {
    int[][] COST;;
    public int[][] min_bus;

    public Cheapest()
    {
        COST = new int[100][100];
        min_bus= new int[100][100];
        for(int i=0;i<100;i++) for(int j=0;j<100;j++)
        {
            COST[i][j]=-1;
            min_bus[i][j]=-1;
        }

        for(Edge e: Machine.getInstance().edges)
        {
            LatLng sl=null,dl=null;
            for(Node n: Machine.getInstance().nodes)
            {
                if(n.id == e.Src)
                {
                    sl = new LatLng(n.Lat,n.Long);
                    break;
                }
            }
            for(Node n: Machine.getInstance().nodes)
            {
                if(n.id == e.Dest)
                {
                    dl = new LatLng(n.Lat,n.Long);
                    break;
                }
            }

            int min_fare_rate=9999;
            int busid=-1;
            for(Object o: e.bus_ids)
            {
                for(Bus o1: Machine.getInstance().buses)
                {
                    if(o1.id == Integer.parseInt(o.toString()))
                    {
                        if(o1.FareRate_CostPerKM < min_fare_rate)
                        {
                            busid = o1.id;
                            min_fare_rate = o1.FareRate_CostPerKM;
                        }
                        break;
                    }

                }
            }

            min_bus[e.Src][e.Dest] = busid;
            min_bus[e.Dest][e.Src] = busid;
            COST[e.Src][e.Dest] = e.distance*min_fare_rate;
            COST[e.Dest][e.Src] = e.distance*min_fare_rate;
        }
    }
    int[] MIN_COST=new int[100];

    public ArrayList CheapestRoute(int S, int D)
    {
        ArrayList temp_path = new ArrayList();
        ArrayList path2 = new ArrayList();

        for(int i=0;i<100;i++)  MIN_COST[i]=-1;

        int parent[];
        parent = new int[100];

        for(int i=0;i<100;i++)  parent[i]=-1;

        Comparator<Q_Element> comparator = new Q_Elements_Comparator();
        PriorityQueue<Q_Element> queue =
                new PriorityQueue<Q_Element>(10, comparator);

        MIN_COST[S]=0;
        queue.add(new Q_Element(0,S));

        while(!queue.isEmpty())
        {
            int cur = queue.remove().node_id;
            temp_path.add(cur);
            if(cur==D)
            {
                ArrayList path = new ArrayList();
                int x = D;
                path.add(x);
                while(parent[x]!=-1)
                {
                    x = parent[x];
                    path.add(x);
                }


                for(int i=path.size()-1;i>=0;i--)
                {
                    path2.add(path.get(i));
                }

                return path2;
            }

            for(int i=0;i<100;i++)
            {
                if(COST[cur][i]!=-1)
                {
                    if(MIN_COST[i]==-1 || MIN_COST[cur]+COST[cur][i] < MIN_COST[i] )
                    {
                        MIN_COST[i] = MIN_COST[cur]+COST[cur][i];
                        queue.add(new Q_Element(MIN_COST[i], i));
                        parent[i]=cur;
                    }
                }
            }


        }
        return path2;
    }






    class Q_Element{
        int cost;
        int node_id;
        Q_Element(int cost,int id)
        {
            this.cost = cost;
            this.node_id = id;
        }
    }
    class Q_Elements_Comparator implements Comparator<Q_Element>
    {
        @Override
        public int compare(Q_Element x, Q_Element y)
        {
            // Assume neither string is null. Real code should
            // probably be more robust
            // You could also just return x.length() - y.length(),
            // which would be more efficient.
            if (x.cost < y.cost)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
}
