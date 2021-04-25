package com.example.basiccalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
            btn_clear, btn_plus, btn_minus, btn_multiply, btn_divide, btn_equal;

    ImageView btn_camera;
    TextView tv_answer, tv_user_expression;
    String expression = "", answer = "", lastInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setListeners();
    }

    private void initializeViews(){
        tv_answer = findViewById(R.id.calculated_answer);
        tv_user_expression = findViewById(R.id.user_expression);
        btn_camera = findViewById(R.id.openCamera);

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

        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);

        btn_clear = findViewById(R.id.btn_clear);
        btn_equal = findViewById(R.id.btn_equal);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners(){
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CameraLayout.class);
                startActivity(i);
            }
        });

        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "0";
                tv_user_expression.setText(expression);
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "1";
                tv_user_expression.setText(expression);
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "2";
                tv_user_expression.setText(expression);
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "3";
                tv_user_expression.setText(expression);
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "4";
                tv_user_expression.setText(expression);
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "5";
                tv_user_expression.setText(expression);
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "6";
                tv_user_expression.setText(expression);
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "7";
                tv_user_expression.setText(expression);
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "8";
                tv_user_expression.setText(expression);
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "9";
                tv_user_expression.setText(expression);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOperator("+");
            }
        });
        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOperator("-");
            }
        });
        btn_multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOperator("x");
            }
        });
        btn_divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOperator("/");
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = "";
                tv_answer.setText(expression);
                tv_user_expression.setText("");
                tv_user_expression.setError(null);
            }
        });
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() == 0) {
                    tv_user_expression.setError(null);
                } else {
                    lastInput = expression.substring(expression.length() - 1);
                    //Toast.makeText(getBaseContext(), lastInput, Toast.LENGTH_SHORT).show();
                    if(lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("x") || lastInput.equals("/")) {

                        tv_user_expression.requestFocus();
                        tv_user_expression.setError("Invalid Expression!");
                    } else {
                        tv_user_expression.setError(null);

                        //get tokens from expression into ArrayList
                        ArrayList<String> myTokens = createTokens(expression);

                        //parse tokens and get answer
                        myTokens = evaluateOperators(myTokens, "/");
                        myTokens = evaluateOperators(myTokens, "x");
                        myTokens = evaluateOperators(myTokens, "+");
                        myTokens = evaluateOperators(myTokens, "-");
//                        StringBuffer sb = new StringBuffer();
//                        for (String s : myTokens) {
//                            sb.append(s);
//                        }
//                        answer = sb.toString();
                        answer = myTokens.get(0);

                        //answer
                        tv_answer.setText(answer);
                    }
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void checkOperator(String operator) {
        //if expression is empty
        if (expression.isEmpty()) {
            //if + or - is entered
            if(operator.equals("+") || operator.equals("-")) {
                expression = operator;
                tv_user_expression.setError(null);
                tv_user_expression.setText(expression);
            } else {
                //if x or / is entered , show error
                tv_user_expression.requestFocus();
                tv_user_expression.setError("Invalid Operator: " + operator);
            }
        } else {
            //when only 1 input is given
            if(expression.length() == 1) {
                //if single input is + or -
                if(expression.equals("+") || expression.equals("-")){
                    //error incase x or / are entered
                    if (operator.equals("x") || operator.equals("/")) {
                        tv_user_expression.requestFocus();
                        tv_user_expression.setError("Invalid Operator: " + operator);
                    } else if (operator.equals("+") || operator.equals("-")) {
                        //replace single input if + or - is entered
                        expression = operator;
                        tv_user_expression.setText(expression);
                        tv_user_expression.setError(null);
                    }
                } else {
                    //if 0 to 9 are entered add to expression
                    expression = expression + operator;
                    tv_user_expression.setText(expression);
                    tv_user_expression.setError(null);
                }
            } else {
                //if more than 2 inputs are entered
                lastInput = expression.substring(expression.length() - 1);
                if(lastInput.equals("+") || lastInput.equals("-") || lastInput.equals("x") || lastInput.equals("/")) {
                    expression = expression.substring(0, expression.length() - 1) + operator;
                    tv_user_expression.setText(expression);
                    tv_user_expression.setError(null);
                } else if(operator.equals("+") || operator.equals("-") || operator.equals("x") || operator.equals("/")) {
                    expression = expression + operator;
                    tv_user_expression.setText(expression);
                    tv_user_expression.setError(null);
                }
            }
        }
    }

    //Lexer
    public ArrayList<String> createTokens(String e) {
        ArrayList<String> myTokens = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        for(int i = 0; i < e.length(); i++) {
            if(i == 0 && (e.charAt(i) == '+' || e.charAt(i) == '-')) {
                word.append(e.charAt(i));
            } else {
                if(e.charAt(i) == '+' || e.charAt(i) == '-' || e.charAt(i) == 'x' || e.charAt(i) == '/') {
                    myTokens.add(word.toString());
                    myTokens.add("" + e.charAt(i));
                    word = new StringBuilder();
                } else {
                    if(i != (e.length()-1)) {
                        word.append(e.charAt(i));
                    } else {
                        word.append(e.charAt(i));
                        myTokens.add(word.toString());
                        word = new StringBuilder();
                    }
                }
            }
        }
        return myTokens;
    }

    //count
    public int countOperator(String operator, ArrayList<String> tokens) {
        int count = 0;
        for(int i = 0; i < tokens.size(); i++) {
            if(tokens.get(i).equals(operator)) {
                count++;
            }
        }
        return count;
    }

    public ArrayList<String> evaluateOperators(ArrayList<String> tokens, String operator) {
        int count = countOperator(operator, tokens);
        if(count == 0) {
            return tokens;
        } else {
            for(int i = 0; i < count; i++) {
                int operatorIndex = tokens.indexOf(operator);
                String leftValue = tokens.get(operatorIndex - 1);
                String rightValue = tokens.get(operatorIndex + 1);
                String value = "";
                if(operator.equals("/")) {
                    //divide
                    value = Integer.toString(Integer.parseInt(leftValue)/Integer.parseInt(rightValue));
                } else if(operator.equals("x")) {
                    //multiply
                    value = Integer.toString(Integer.parseInt(leftValue)*Integer.parseInt(rightValue));
                } else if(operator.equals("+")) {
                    //sum
                    value = Integer.toString(Integer.parseInt(leftValue)+Integer.parseInt(rightValue));
                } else if(operator.equals("-")) {
                    //subtract
                    value = Integer.toString(Integer.parseInt(leftValue)-Integer.parseInt(rightValue));
                }
                tokens.set(operatorIndex, value);
                tokens.remove(operatorIndex+1);
                tokens.remove(operatorIndex-1);
            }
            return tokens;
        }
    }
}

/*

Toast.makeText(getBaseContext(),operator, Toast.LENGTH_SHORT).show();

 */