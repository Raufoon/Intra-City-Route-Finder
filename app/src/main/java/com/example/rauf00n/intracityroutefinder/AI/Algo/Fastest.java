package com.example.rauf00n.intracityroutefinder.AI.Algo;

import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by RAUF00N on 5/18/2016.
 */
public class Fastest {
    int[][] COST;;


    public  Fastest()
    {
        COST = new int[100][100];
        for(int i=0;i<100;i++) for(int j=0;j<100;j++) COST[i][j]=-1;

        for(Edge e: Machine.getInstance().edges)
        {
            if(Machine.getInstance().time == Machine.MORNING)
            {
                COST[e.Src][e.Dest] = e.trafficFactor.morning;
                COST[e.Dest][e.Src] = e.trafficFactor.morning;
            }
            else if(Machine.getInstance().time == Machine.NOON)
            {
                COST[e.Src][e.Dest] = e.trafficFactor.noon;
                COST[e.Dest][e.Src] = e.trafficFactor.noon;
            }
            else if(Machine.getInstance().time == Machine.NIGHT)
            {
                COST[e.Src][e.Dest] = e.trafficFactor.night;
                COST[e.Dest][e.Src] = e.trafficFactor.night;
            }
        }
    }
    int[] MIN_COST=new int[100];

    int totalCost=0;

    public ArrayList FastestRoute(int S, int D)
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
