package com.calculator.omnical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.calculator.omnical.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    String expression = "", answer = "", lastInput = "";
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();

        sharedPrefs = getSharedPreferences("SHARED PREFS", Context.MODE_PRIVATE);

        expression = sharedPrefs.getString("EXPRESSION", "");
        answer = sharedPrefs.getString("ANSWER", "");
        binding.userExpression.setText(expression);
        binding.calculatedAnswer.setText(answer);
        if (!expression.equals("")) {
            binding.btnDel.setVisibility(View.VISIBLE);
        }

        binding.userExpression.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    binding.btnDel.setVisibility(View.VISIBLE);
                } else {
                    binding.btnDel.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {

        binding.btn00.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x00";
            } else {
                expression += "00";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn0.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x0";
            } else {
                expression += "0";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn1.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x1";
            } else {
                expression += "1";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn2.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x2";
            } else {
                expression += "2";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn3.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x3";
            } else {
                expression += "3";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn4.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x4";
            } else {
                expression += "4";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn5.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x5";
            } else {
                expression += "5";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn6.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x6";
            } else {
                expression += "6";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn7.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x7";
            } else {
                expression += "7";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn8.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x8";
            } else {
                expression += "8";
            }
            binding.userExpression.setText(expression);
        });
        binding.btn9.setOnClickListener(v -> {
            if (expression.endsWith(")")) {
                expression += "x9";
            } else {
                expression += "9";
            }
            binding.userExpression.setText(expression);
        });
        binding.btnDot.setOnClickListener(v -> {
            expression = expression + ".";
            binding.userExpression.setText(expression);
        });

        binding.btnPlus.setOnClickListener(v -> checkOperator("+"));
        binding.btnMinus.setOnClickListener(v -> checkOperator("-"));
        binding.btnMultiply.setOnClickListener(v -> checkOperator("x"));
        binding.btnDivide.setOnClickListener(v -> checkOperator("/"));
        binding.btnLeftBracket.setOnClickListener(v -> {
            if (!expression.equals("") && (expression.endsWith(")") ||
                    Character.isDigit(expression.charAt(expression.length() - 1)))) {
                expression += "x(";
            } else {
                expression += "(";
            }
            binding.userExpression.setText(expression);
        });
        binding.btnRightBracket.setOnClickListener(v -> {
            expression += ")";
            binding.userExpression.setText(expression);
        });

        binding.btnClear.setOnClickListener(v -> {
            expression = "";
            answer = "";
            binding.calculatedAnswer.setText(answer);
            binding.userExpression.setText(expression);
            binding.userExpression.setError(null);

            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("EXPRESSION", "");
            editor.putString("ANSWER", "");
            editor.apply();
        });
        binding.btnDel.setOnClickListener(v -> {

            if (expression.length() != 0) {
                expression = expression.substring(0, expression.length() - 1);
                binding.userExpression.setText(expression);
            }
        });
        binding.btnEqual.setOnClickListener(v -> {
            if (expression.length() == 0) {
                binding.userExpression.setError(null);
            } else {
                if (!expression.equals(sharedPrefs.getString("EXPRESSION", ""))) {
                    lastInput = expression.substring(expression.length() - 1);
                    if (lastInput.equals("+") || lastInput.equals("-") ||
                            lastInput.equals("x") || lastInput.equals("/")) {

                        binding.userExpression.requestFocus();
                        binding.userExpression.setError("Invalid Expression!");
                    } else {
                        binding.userExpression.setError(null);

                        //get tokens from expression into ArrayList
                        ArrayList<String> myTokens = createTokens(expression.replaceAll("\\s", ""));

                        //parse tokens and get answer
                        try {
                            myTokens = evaluateBrackets(myTokens, ")");
                            myTokens = evaluateOperator(myTokens, "/");
                            myTokens = evaluateOperator(myTokens, "x");
                            myTokens = evaluateOperator(myTokens, "-");
                            myTokens = evaluateOperator(myTokens, "+");
                            answer = myTokens.get(0);
                            if (answer.endsWith(".0"))
                                answer = answer.substring(0, answer.length() - 2);
                        } catch (Exception e) {
                            binding.userExpression.requestFocus();
                            binding.userExpression.setError("Invalid Expression");
                        }
                        binding.calculatedAnswer.setText(answer);
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
                binding.userExpression.setError(null);
                binding.userExpression.setText(expression);
            } else {
                //if x or / is entered , show error
                binding.userExpression.requestFocus();
                binding.userExpression.setError("Invalid Operator: " + operator);
            }
        } else {
            //when input size = 1
            if (expression.length() == 1) {
                //if single input is + or -
                if (expression.equals("+") || expression.equals("-")) {
                    //display error when x or / are entered
                    if (operator.equals("x") || operator.equals("/")) {
                        binding.userExpression.requestFocus();
                        binding.userExpression.setError("Invalid Operator: " + operator);
                    } else if (operator.equals("+") || operator.equals("-")) {
                        //replace single input if + or - is entered
                        expression = operator;
                        binding.userExpression.setText(expression);
                        binding.userExpression.setError(null);
                    }
                } else {
                    //if 0 to 9 are entered add to expression
                    expression = expression + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
                }
            } else {
                //if more than 2 inputs are entered
                lastInput = expression.substring(expression.length() - 1);
                if (lastInput.equals("+") || lastInput.equals("-") ||
                        lastInput.equals("x") || lastInput.equals("/")) {
                    expression = expression.substring(0, expression.length() - 1) + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
                } else if (operator.equals("+") || operator.equals("-") ||
                        operator.equals("x") || operator.equals("/")) {
                    expression = expression + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
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

    public ArrayList<String> evaluateBrackets(ArrayList<String> tokens, String operator) {
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
