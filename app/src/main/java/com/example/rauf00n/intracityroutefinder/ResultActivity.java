package com.example.rauf00n.intracityroutefinder;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;
import com.example.rauf00n.intracityroutefinder.AI.Util.OutputNode;

import javax.crypto.Mac;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        LinearLayout nodes_layout = (LinearLayout) findViewById(R.id.nodes);

        Output output = Machine.getInstance().getOutput();
        output.constructMessages();

        int idx=1;
        for(int i=0;i< output.getPath().size();i++)
        {
            OutputNode o = output.getPath().get(i);
            TextView title= new TextView(this);
            title.setText(idx+". "+o.getName());
            title.setTextSize(30);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,50,0,0);

            title.setLayoutParams(lp);
            ((LinearLayout.LayoutParams)title.getLayoutParams()).topMargin = 50;
            title.setTextColor(Color.parseColor("#FFFFFF"));
            title.setBackgroundResource(R.color.wallet_holo_blue_light);


            TextView desc= new TextView(this);
            desc.setText(o.msg);
            desc.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            desc.setTextSize(20);

            nodes_layout.addView(title);
            nodes_layout.addView(desc);

            idx++;
        }
    }

    public void googleMap(View view)
    {
        startActivity(new Intent(ResultActivity.this,MapsActivity.class));
    }
}
