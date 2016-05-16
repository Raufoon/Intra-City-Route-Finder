package com.example.rauf00n.intracityroutefinder.AI.Util;

import java.util.ArrayList;

/**
 * Created by RAUF00N on 5/15/2016.
 */

/*
This class will contain the result calculated by our AI.
It must contain a 'PATH' from source to destination nodes
*/
public class Output {
    ArrayList<OutputNode>path;

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
}
