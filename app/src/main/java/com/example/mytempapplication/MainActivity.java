package com.example.mytempapplication;

//import static com.example.mytempapplication.R.id.editText;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<TextView> nums,operations;
    TextView answer,equals;
    static String TAG = "MainActivity";
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

        operations = new ArrayList<>();
        operations.add((TextView) findViewById(R.id.operation_add));
        operations.add((TextView) findViewById(R.id.operation_mul));
        operations.add((TextView) findViewById(R.id.operation_div));
        operations.add((TextView) findViewById(R.id.operation_sub));

        equals = (TextView) findViewById(R.id.equals);

        setOnClickListeners();
    }

    private void setOnClickListeners() {
        for(TextView num : nums){
            num.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(answer.getText().length()==1 && answer.getText().charAt(0)=='0') answer.setText(num.getText());
                    else answer.append(num.getText());
                }
            });
        }

        findViewById(R.id.operation_C).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText(answer.getText().subSequence(0,answer.getText().length()-1));
                if(answer.getText().length()==0) answer.setText("0");
            }
        });

        findViewById(R.id.operation_AC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("0");
            }
        });

        for (TextView op : operations){
            op.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i(TAG,"setting operations' on click listeners ");
                    String s = answer.getText().toString();
                    if(s.length()!=0){
                        if(Character.isDigit(s.charAt(s.length()-1))){
                            Log.i(TAG,"is a digit"+s.charAt(s.length()-1));
                            answer.append(op.getText());
                        }
                        else{
                            Log.i(TAG,"is not a digit"+s.charAt(s.length()-1));
                            answer.setText(
                                    s.substring(0,s.length()-1)+op.getText()
                            );
                        }
                    }
                }
            });

            equals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("rahul","i am clicked");
                    answer.setText(EquationSolver.getResult(answer.getText().toString(),getApplicationContext()));
                }
            });
        }
    }
}