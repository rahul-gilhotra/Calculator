package com.example.mytempapplication;

//import static com.example.mytempapplication.R.id.editText;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TextView> nums;
    ArrayList<TextView> operations;
    TextView answer,opeation_C;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        init();
    }
    void init(){
        answer = (TextView) findViewById(R.id.answer);

        nums = new ArrayList<>();

        nums.add((TextView) findViewById(R.id.num_0));
        nums.add((TextView) findViewById(R.id.num_1));
        nums.add((TextView) findViewById(R.id.num_2));
        nums.add((TextView) findViewById(R.id.num_3));
        nums.add((TextView) findViewById(R.id.num_4));
        nums.add((TextView) findViewById(R.id.num_5));
        nums.add((TextView) findViewById(R.id.num_6));
        nums.add((TextView) findViewById(R.id.num_7));
        nums.add((TextView) findViewById(R.id.num_8));
        nums.add((TextView) findViewById(R.id.num_9));

        for(TextView num : nums){
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(answer.getText().equals("0")) answer.setText(num.getText());
                    else answer.append(num.getText());
                }
            });
        }

        findViewById(R.id.operation_C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText().subSequence(0,answer.getText().length()-1));
            }
        });

        findViewById(R.id.operation_AC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("0");
            }
        });
    }
}