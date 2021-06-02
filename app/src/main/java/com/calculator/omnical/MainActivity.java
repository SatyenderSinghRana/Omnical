package com.calculator.omnical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.calculator.omnical.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    Button btn_00, btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,
            btn_clear, btn_plus, btn_minus, btn_multiply, btn_divide, btn_equal,
            btn_dot, btn_left_bracket, btn_right_bracket;

    ImageView img_btn_del;
    TextView tv_answer, tv_expression;
    String expression = "", answer = "", lastInput = "";

    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initializeViews();
        setListeners();


        sharedPrefs = getSharedPreferences("SHARED PREFS", Context.MODE_PRIVATE);

        expression = sharedPrefs.getString("EXPRESSION", "");
        answer = sharedPrefs.getString("ANSWER", "");
        tv_expression.setText(expression);
        tv_answer.setText(answer);
        if (!expression.equals("")) {
            img_btn_del.setVisibility(View.VISIBLE);
        }

        tv_expression.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    img_btn_del.setVisibility(View.VISIBLE);
                } else {
                    img_btn_del.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initializeViews() {
        tv_answer = findViewById(R.id.calculated_answer);
        tv_expression = findViewById(R.id.user_expression);

        btn_00 = findViewById(R.id.btn_00);
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
        btn_left_bracket = findViewById(R.id.btn_left_paran);
        btn_right_bracket = findViewById(R.id.btn_right_paran);

        btn_clear = findViewById(R.id.btn_clear);
        img_btn_del = findViewById(R.id.btn_del);
        btn_equal = findViewById(R.id.btn_equal);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {

        btn_00.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x00";
            } else {
                expression += "00";
            }
            tv_expression.setText(expression);
        });
        btn_0.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x0";
            } else {
                expression += "0";
            }
            tv_expression.setText(expression);
        });
        btn_1.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x1";
            } else {
                expression += "1";
            }
            tv_expression.setText(expression);
        });
        btn_2.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x2";
            } else {
                expression += "2";
            }
            tv_expression.setText(expression);
        });
        btn_3.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x3";
            } else {
                expression += "3";
            }
            tv_expression.setText(expression);
        });
        btn_4.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x4";
            } else {
                expression += "4";
            }
            tv_expression.setText(expression);
        });
        btn_5.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x5";
            } else {
                expression += "5";
            }
            tv_expression.setText(expression);
        });
        btn_6.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x6";
            } else {
                expression += "6";
            }
            tv_expression.setText(expression);
        });
        btn_7.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x7";
            } else {
                expression += "7";
            }
            tv_expression.setText(expression);
        });
        btn_8.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x8";
            } else {
                expression += "8";
            }
            tv_expression.setText(expression);
        });
        btn_9.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x9";
            } else {
                expression += "9";
            }
            tv_expression.setText(expression);
        });
        btn_dot.setOnClickListener(v -> {
            expression = expression + ".";
            tv_expression.setText(expression);
        });

        btn_plus.setOnClickListener(v -> checkOperator("+"));
        btn_minus.setOnClickListener(v -> checkOperator("-"));
        btn_multiply.setOnClickListener(v -> checkOperator("x"));
        btn_divide.setOnClickListener(v -> checkOperator("/"));
        btn_left_bracket.setOnClickListener(v -> {
            if (!expression.equals("") && (expression.endsWith(")") ||
                    Character.isDigit(expression.charAt(expression.length() - 1)))) {
                expression += "x(";
            } else {
                expression += "(";
            }
            tv_expression.setText(expression);
        });
        btn_right_bracket.setOnClickListener(v -> {
            expression += ")";
            tv_expression.setText(expression);
        });

        btn_clear.setOnClickListener(v -> {
            expression = "";
            answer = "";
            tv_answer.setText(answer);
            tv_expression.setText(expression);
            tv_expression.setError(null);

            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("EXPRESSION", "");
            editor.putString("ANSWER", "");
            editor.apply();
        });
        img_btn_del.setOnClickListener(v -> {

            if (expression.length() != 0) {
                expression = expression.substring(0, expression.length() - 1);
                tv_expression.setText(expression);
            }
        });
        btn_equal.setOnClickListener(v -> {
            if (expression.length() == 0) {
                tv_expression.setError(null);
            } else {
                if (!expression.equals(sharedPrefs.getString("EXPRESSION", ""))) {
                    lastInput = expression.substring(expression.length() - 1);
                    if (lastInput.equals("+") || lastInput.equals("-") ||
                            lastInput.equals("x") || lastInput.equals("/")) {

                        tv_expression.requestFocus();
                        tv_expression.setError("Invalid Expression!");
                    } else {
                        tv_expression.setError(null);

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
                            if (answer.endsWith(".0"))
                                answer = answer.substring(0, answer.length() - 2);
                        } catch (Exception e) {
                            tv_expression.requestFocus();
                            tv_expression.setError("Invalid Expression");
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
            if (operator.equals("+") || operator.equals("-")) {
                expression = operator;
                tv_expression.setError(null);
                tv_expression.setText(expression);
            } else {
                //if x or / is entered , show error
                tv_expression.requestFocus();
                tv_expression.setError("Invalid Operator: " + operator);
            }
        } else {
            //when input size = 1
            if (expression.length() == 1) {
                //if single input is + or -
                if (expression.equals("+") || expression.equals("-")) {
                    //error incase x or / are entered
                    if (operator.equals("x") || operator.equals("/")) {
                        tv_expression.requestFocus();
                        tv_expression.setError("Invalid Operator: " + operator);
                    } else if (operator.equals("+") || operator.equals("-")) {
                        //replace single input if + or - is entered
                        expression = operator;
                        tv_expression.setText(expression);
                        tv_expression.setError(null);
                    }
                } else {
                    //if 0 to 9 are entered add to expression
                    expression = expression + operator;
                    tv_expression.setText(expression);
                    tv_expression.setError(null);
                }
            } else {
                //if more than 2 inputs are entered
                lastInput = expression.substring(expression.length() - 1);
                if (lastInput.equals("+") || lastInput.equals("-") ||
                        lastInput.equals("x") || lastInput.equals("/")) {
                    expression = expression.substring(0, expression.length() - 1) + operator;
                    tv_expression.setText(expression);
                    tv_expression.setError(null);
                } else if (operator.equals("+") || operator.equals("-") ||
                        operator.equals("x") || operator.equals("/")) {
                    expression = expression + operator;
                    tv_expression.setText(expression);
                    tv_expression.setError(null);
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
        /*
        // if token before or after paranthesis is number
        // insert * between them in the arraylist
        int tokenSize = myTokens.size();
        for (int j = 1; j < tokenSize; j++) {
            if (myTokens.get(j).equals("(")) {
                try {
                    if (!(myTokens.get(j - 1).equals("+") || myTokens.get(j - 1).equals("-") ||
                            myTokens.get(j - 1).equals("x") || myTokens.get(j - 1).equals("/") ||
                            myTokens.get(j - 1).equals("("))) {
                        myTokens.add(j, "x");
                        tokenSize++;
                    }
                } catch (Exception ignored) {
                }
            }
            if (myTokens.get(j).equals(")") && j < myTokens.size() - 1) {
                try {
                    if (!(myTokens.get(j + 1).equals("+") || myTokens.get(j + 1).equals("-") ||
                            myTokens.get(j + 1).equals("x") || myTokens.get(j + 1).equals("/") ||
                            myTokens.get(j + 1).equals(")"))) {
                        myTokens.add(j, "x");
                        tokenSize++;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        Log.d("tokens", "" + myTokens);
        */
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
                        if (!(leftValue.equals("(") || leftValue.equals(")"))) {
                            value = Float.toString(Float.parseFloat(leftValue) + Float.parseFloat(rightValue));
                        }
                        break;
                    case "-":
                        //subtract
                        if (!(leftValue.equals("(") || leftValue.equals(")"))) {
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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("EXPRESSION", expression);
        editor.putString("ANSWER", answer);
        editor.apply();
    }
}
