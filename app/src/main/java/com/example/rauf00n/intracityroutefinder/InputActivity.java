package com.example.rauf00n.intracityroutefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.rauf00n.intracityroutefinder.AI.CityMapInfo;
import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;

public class InputActivity extends AppCompatActivity {

    CityMapInfo cityMapInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        cityMapInfo = new CityMapInfo(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView tv = (TextView)findViewById(R.id.test);
        tv.setText("Nodes:\n");
        for(int i=0;i<cityMapInfo.nodes.size();i++)
        {
            tv.append("name: "+ cityMapInfo.nodes.get(i).Name+" " +
                    " id ="+cityMapInfo.nodes.get(i).id+" Lat ="
                    +cityMapInfo.nodes.get(i).Lat
                    +"Long= "+cityMapInfo.nodes.get(i).Long+"\n");
        }
        tv.append("Edges:\n");
        for(int i=0;i<cityMapInfo.edges.size();i++)
        {
            tv.append("src = "+cityMapInfo.edges.get(i).Src+" dest = "
                    +cityMapInfo.edges.get(i).Dest+" traffic factor="
                    +cityMapInfo.edges.get(i).trafficFactor.morning+" "
                    +cityMapInfo.edges.get(i).trafficFactor.noon+" "
                    +cityMapInfo.edges.get(i).trafficFactor.night+" \n");
        }

        tv.append("Buses:\n");
        for(int i=0;i<cityMapInfo.buses.size();i++)
        {
            tv.append("name = "+cityMapInfo.buses.get(i).Name+" id = "
                    +cityMapInfo.buses.get(i).id+" fare = "
                    +cityMapInfo.buses.get(i).FareRate_CostPerKM+"\nRoute:");
            for(int j=0;j<cityMapInfo.buses.get(i).Stoppages.size();j++)
            {
                tv.append(cityMapInfo.buses.get(i).Stoppages.get(j)+"");
            }
            tv.append("\n");
        }

    }

    public void FindRoute(View view)
    {
        Intent intent = new Intent(InputActivity.this, MapsActivity.class);

        // it's null, change it when u can figure out how to give inputs
        Machine.getInstance().giveInput(null,null,null);

        // start new activity
        startActivity(intent);
    }
}

