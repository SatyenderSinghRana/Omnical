package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class newCalculator extends AppCompatActivity {

    TextView display;
    String total = "";
    Button btn_plus, btn_minus, btn_multiply, btn_divide, btn_clear, btn_sine, btn_cos, btn_tan, btn_log;
    EditText et1, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_calculator);

        display = findViewById(R.id.display);

        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_clear = findViewById(R.id.btn_clear);

        btn_sine = findViewById(R.id.btn_sine);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_log = findViewById(R.id.btn_log);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = "";
                et1.setText("");
                et2.setText("");
                display.setText(total);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter valid input!",Toast.LENGTH_SHORT).show();
                } else {
                    String a = et1.getText().toString();
                    String b = et2.getText().toString();
                    int c = (Integer.parseInt(a) + Integer.parseInt(b));
                    display.setText(Integer.toString(c));
                }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter valid input!",Toast.LENGTH_SHORT).show();
                } else {
                    String a = et1.getText().toString();
                    String b = et2.getText().toString();
                    int c = (Integer.parseInt(a) - Integer.parseInt(b));
                    display.setText(Integer.toString(c));
                }
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter valid input!",Toast.LENGTH_SHORT).show();
                } else {
                    String a = et1.getText().toString();
                    String b = et2.getText().toString();
                    int c = (Integer.parseInt(a) * Integer.parseInt(b));
                    display.setText(Integer.toString(c));
                }
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter valid input!",Toast.LENGTH_SHORT).show();
                } else {
                    String a = et1.getText().toString();
                    String b = et2.getText().toString();
                    int c = (Integer.parseInt(a) / Integer.parseInt(b));
                    display.setText(Integer.toString(c));
                }
            }
        });

        btn_sine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() && et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                } else {
                    if(et1.getText().toString().isEmpty()) {
                        if (et2.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                        } else {
                            //process et2
                            display.setText("" + Math.sin(Double.valueOf(et2.getText().toString()).doubleValue()));
                        }
                    } else {
                        //process et1
                        display.setText("" + Math.sin(Double.valueOf(et1.getText().toString()).doubleValue()));
                    }
                }
            }
        });

        btn_cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() && et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                } else {
                    if(et1.getText().toString().isEmpty()) {
                        if (et2.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                        } else {
                            //process et2
                            display.setText("" + Math.cos(Double.valueOf(et2.getText().toString()).doubleValue()));
                        }
                    } else {
                        //process et1
                        display.setText("" + Math.cos(Double.valueOf(et1.getText().toString()).doubleValue()));
                    }
                }
            }
        });

        btn_tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() && et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                } else {
                    if(et1.getText().toString().isEmpty()) {
                        if (et2.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                        } else {
                            //process et2
                            display.setText("" + Math.tan(Double.valueOf(et2.getText().toString()).doubleValue()));
                        }
                    } else {
                        //process et1
                        display.setText("" + Math.tan(Double.valueOf(et1.getText().toString()).doubleValue()));
                    }
                }
            }
        });

        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et1.getText().toString().isEmpty() && et2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                } else {
                    if(et1.getText().toString().isEmpty()) {
                        if (et2.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(),"Enter any 1 field",Toast.LENGTH_SHORT).show();
                        } else {
                            //process et2
                            display.setText("" + Math.log(Double.valueOf(et2.getText().toString()).doubleValue()));
                        }
                    } else {
                        //process et1
                        display.setText("" + Math.log(Double.valueOf(et1.getText().toString()).doubleValue()));
                    }
                }
            }
        });
    }
}