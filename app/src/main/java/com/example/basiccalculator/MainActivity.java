package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
            btn_clear, btn_del, btn_plus, btn_minus, btn_multiply, btn_divide, btn_equal,
            btn_percent, btn_plus_minus, btn_dot, up_down_btn;
    TextView display;
    EditText equation;
    String total = "", result = "";
    GridLayout gridLayout;
    Boolean value = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridLayout = findViewById(R.id.gridLayout3);

        display = findViewById(R.id.display);
        equation = findViewById(R.id.equation);

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_delete);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_percent = findViewById(R.id.btn_percent);
        btn_plus_minus = findViewById(R.id.btn_plus_minus);
        btn_dot = findViewById(R.id.btn_dot);
        btn_equal = findViewById(R.id.btn_equal);
        up_down_btn = findViewById(R.id.up_down_btn);

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "0";
                equation.setText(total);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "1";
                equation.setText(total);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "2";
                equation.setText(total);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "3";
                equation.setText(total);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "4";
                equation.setText(total);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "5";
                equation.setText(total);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "6";
                equation.setText(total);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "7";
                equation.setText(total);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "8";
                equation.setText(total);
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = total + "9";
                equation.setText(total);
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = " = ";
                display.setText(total);
                equation.setText("");
            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total.equals("0") || total.equals("")) {
                    display.setText(" = ");
                } else {
                    total = total.substring(0, total.length() - 1);
                }
                equation.setText(total);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check_last = total.substring(0, total.length() - 1);
                if(check_last.equals(" + ") || check_last.equals(" - ") || check_last.equals(" x ") || check_last.equals(" / ")) {
                    total = total.substring(0, total.length() - 1);
                }
                total = total + " + ";
                equation.setText(total);
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check_last = total.substring(0, total.length() - 1);
                if(check_last.equals(" + ") || check_last.equals(" - ") || check_last.equals(" x ") || check_last.equals(" / ")) {
                    total = total.substring(0, total.length() - 1);
                }
                total = total + " - ";
                equation.setText(total);
            }
        });

        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check_last = total.substring(0, total.length() - 1);
                if(check_last.equals(" + ") || check_last.equals(" - ") || check_last.equals(" x ") || check_last.equals(" / ")) {
                    total = total.substring(0, total.length() - 1);
                }
                total = total + " x ";
                equation.setText(total);
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check_last = total.substring(0, total.length() - 1);
                if(check_last.equals(" + ") || check_last.equals(" - ") || check_last.equals(" x ") || check_last.equals(" ÷ ")) {
                    total = total.substring(0, total.length() - 1);
                }
                total = total + " ÷ ";
                equation.setText(total);
            }
        });

        btn_plus_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = Integer.toString(Integer.parseInt(total) * (-1));
                equation.setText(total);
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check_last = total.substring(0, total.length() - 1);
                if(check_last.equals(" + ") || check_last.equals(" - ") || check_last.equals(" x ") || check_last.equals(" / ")) {
                    total = total.substring(0, total.length() - 1);
                }
                total = total + ".";
                equation.setText(total);
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //process complete equation
                equation.setText(total);
                display.setText(" = " + result);
            }
        });

        up_down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value == false) {
                    gridLayout.setVisibility(View.VISIBLE);
                    up_down_btn.setText("▼");
                    value = true;
                } else {
                    gridLayout.setVisibility(View.GONE);
                    up_down_btn.setText("▲");
                    value = false;
                }
            }
        });
    }
}