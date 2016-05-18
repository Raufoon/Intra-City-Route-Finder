package com.example.rauf00n.intracityroutefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.rauf00n.intracityroutefinder.AI.CityMapInfo;
import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;
import com.example.rauf00n.intracityroutefinder.AI.XMLObjects.Node;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class InputActivity extends AppCompatActivity {

    Machine cityMapInfo = Machine.getInstance();
    Spinner src_spinner, dest_spinner,const_spinner;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        setUpSpinners();
    }

    @Override
    protected void onResume() {
        super.onResume();

       //



        /* following are demo works, delete em*/
        /*
        <ScrollView android:layout_width="match_parent"
        android:layout_height="wrap_content">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/test"
        />
    </ScrollView>

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
            tv.append("buses: "+cityMapInfo.edges.get(i).bus_ids.size()+"\n");
            for(Object o:cityMapInfo.edges.get(i).bus_ids)
            {
                tv.append(o.toString()+" ");
            }
            tv.append("\n");
        }

        tv.append("Buses:\n");
        for(int i=0;i<cityMapInfo.buses.size();i++)
        {
            tv.append("name = "+cityMapInfo.buses.get(i).Name+" id = "
                    +cityMapInfo.buses.get(i).id+" fare = "
                    +cityMapInfo.buses.get(i).FareRate_CostPerKM+"\n");
            tv.append("\n");
        }
    */

    }

    public void FindRoute(View view)
    {
        Intent intent = new Intent(InputActivity.this, ResultActivity.class);

        timePicker = (TimePicker) findViewById(R.id.time_of_journey);
        int HR = timePicker.getCurrentHour();
        int MIN = timePicker.getCurrentMinute();

        // it's null, change it when u can figure out how to give inputs
        Machine.getInstance().giveInput(src_spinner.getSelectedItem().toString()
                ,dest_spinner.getSelectedItem().toString()
                ,const_spinner.getSelectedItem().toString()
                ,HR
                ,MIN
        );


        Machine.getInstance().getOutput();
        // start new activity
        startActivity(intent);
    }
    void setUpSpinners()
    {
        src_spinner = (Spinner) findViewById(R.id.src_spinner);
        dest_spinner = (Spinner) findViewById(R.id.dest_spinner);

        List<String> categories = new ArrayList<String>();


        for(Node node: Machine.getInstance().nodes)
        {
            categories.add(node.Name);

        }
        Collections.sort(categories);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        src_spinner.setAdapter(dataAdapter);
        dest_spinner.setAdapter(dataAdapter);

        List<String> categories2 = new ArrayList<String>();
        const_spinner = (Spinner) findViewById(R.id.const_spinner);
        categories2.add("Cheapest Route");
        categories2.add("Fastest Route");
        Collections.sort(categories2);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        const_spinner.setAdapter(dataAdapter2);


        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        dataAdapter2.setDropDownViewResource(R.layout.spinner_item);

    }
}

