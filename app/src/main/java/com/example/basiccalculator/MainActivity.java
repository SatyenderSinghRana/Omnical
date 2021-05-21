package com.example.basiccalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
            btn_clear, btn_plus, btn_minus, btn_multiply, btn_divide, btn_equal,
            btn_dot, btn_del, btn_left_paran, btn_right_paran;

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
        btn_dot = findViewById(R.id.btn_dot);

        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_left_paran = findViewById(R.id.btn_left_paran);
        btn_right_paran = findViewById(R.id.btn_right_paran);

        btn_clear = findViewById(R.id.btn_clear);
        btn_del = findViewById(R.id.btn_del);
        btn_equal = findViewById(R.id.btn_equal);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners(){
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
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + ".";
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
        btn_left_paran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + "(";
                tv_user_expression.setText(expression);
            }
        });
        btn_right_paran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expression = expression + ")";
                tv_user_expression.setText(expression);
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
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() != 0) {
                    expression = expression.substring(0, expression.length()-1);
                    tv_user_expression.setText(expression);
                }
            }
        });
        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expression.length() == 0) {
                    tv_user_expression.setError(null);
                } else {
                    lastInput = expression.substring(expression.length() - 1);
                    if(lastInput.equals("+") || lastInput.equals("-") ||
                            lastInput.equals("x") || lastInput.equals("/")) {

                        tv_user_expression.requestFocus();
                        tv_user_expression.setError("Invalid Expression!");
                    } else {
                        tv_user_expression.setError(null);

                        //get tokens from expression into ArrayList
                        ArrayList<String> myTokens = createTokens(expression.replaceAll("\\s", ""));

                        //parse tokens and get answer
                        try {

                            myTokens = evaluateParanthesis(myTokens, ")");
                            myTokens = evaluateOperator(myTokens, "/");
                            myTokens = evaluateOperator(myTokens, "x");
                            myTokens = evaluateOperator(myTokens, "-");
                            myTokens = evaluateOperator(myTokens, "+");
                            answer = myTokens.get(0);

                            //answer

                        } catch (Exception e) {
                            answer = "Invalid Expression\n" + "Caused by: " + e;
                        }
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
            // + or - is entered
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
            //when input size = 1
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
                if(lastInput.equals("+") || lastInput.equals("-") ||
                        lastInput.equals("x") || lastInput.equals("/")) {
                    expression = expression.substring(0, expression.length() - 1) + operator;
                    tv_user_expression.setText(expression);
                    tv_user_expression.setError(null);
                } else if(operator.equals("+") || operator.equals("-") ||
                        operator.equals("x") || operator.equals("/")) {
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
        for (int i = 0; i < e.length(); i++) {
            if (Character.isDigit(e.charAt(i)) || e.charAt(i) == '.') {
                word.append(e.charAt(i));
                if (i == e.length() - 1) {
                    myTokens.add(word.toString());
                }
            } else {
                if (!word.toString().equals("")) {
                    myTokens.add(word.toString());
                    word = new StringBuilder();
                }
                myTokens.add("" + e.charAt(i));
            }
        }
        return myTokens;
    }

    //count
    public int countOperator(String operator, ArrayList<String> tokens) {
        int count = 0;
        for (String token : tokens) if (token.equals(operator)) count++;
        return count;
    }

    public ArrayList<String> evaluateParanthesis(ArrayList<String> tokens, String operator) {
        int count = countOperator(operator, tokens);
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                int indexOfRightParan = tokens.indexOf(")");
                ArrayList<String> temp = new ArrayList<>(tokens.subList(0, indexOfRightParan));
                int indexOfLeftParan = temp.lastIndexOf("(");
                ArrayList<String> subToken = new ArrayList<>(temp.subList(indexOfLeftParan + 1, indexOfRightParan));
                subToken = evaluateOperator(subToken, "/");
                subToken = evaluateOperator(subToken, "x");
                subToken = evaluateOperator(subToken, "-");
                subToken = evaluateOperator(subToken, "+");

                tokens.set(indexOfLeftParan, subToken.get(0));
                tokens.subList(indexOfLeftParan + 1, indexOfRightParan + 1).clear();
            }
        }
        return tokens;
    }

    public ArrayList<String> evaluateOperator(ArrayList<String> tokens, String operator) {
        int count = countOperator(operator, tokens);
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                int operatorIndex = tokens.indexOf(operator);
                String rightValue = tokens.get(operatorIndex + 1);
                String leftValue = "";
                try {
                    leftValue = tokens.get(operatorIndex - 1);
                } catch (Exception exception) {
                    tokens.set(operatorIndex + 1, "-" + rightValue);
                    tokens.remove(operatorIndex);
                    continue;
                }
                String value = "";
                switch (operator) {
                    case "/":
                        //divide
                        value = Float.toString(Float.parseFloat(leftValue) / Float.parseFloat(rightValue));
                        break;
                    case "x":
                        //multiply
                        value = Float.toString(Float.parseFloat(leftValue) * Float.parseFloat(rightValue));
                        break;
                    case "+":
                        //sum
                        if(leftValue.equals("(") || leftValue.equals(")")) {
                            //ignore
                        } else {
                            value = Float.toString(Float.parseFloat(leftValue) + Float.parseFloat(rightValue));
                        }
                        break;
                    case "-":
                        //subtract
                        if(leftValue.equals("(") || leftValue.equals(")")) {
                            //ignore
                        } else {
                            value = Float.toString(Float.parseFloat(leftValue) - Float.parseFloat(rightValue));
                        }
                        break;
                }
                tokens.set(operatorIndex, value);
                tokens.remove(operatorIndex + 1);
                tokens.remove(operatorIndex - 1);
            }
        }
        return tokens;
    }
}