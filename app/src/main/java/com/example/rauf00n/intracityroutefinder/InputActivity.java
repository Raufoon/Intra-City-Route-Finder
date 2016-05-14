package com.example.rauf00n.intracityroutefinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rauf00n.intracityroutefinder.AI.Machine;
import com.example.rauf00n.intracityroutefinder.AI.Util.Output;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
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

