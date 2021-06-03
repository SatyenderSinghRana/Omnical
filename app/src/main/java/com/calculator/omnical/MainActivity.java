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

    private String expression = "";
    private String answer = "";
    private String lastInput = "";
    private String spExpression = "EXPRESSION";
    private String spAnswer = "ANSWER";

    private String invalidExpression;
    private String invalidOperator;

    private String doubleZero;
    private String zero;
    private String one;
    private String two;
    private String three;
    private String four;
    private String five;
    private String six;
    private String seven;
    private String eight;
    private String nine;
    private String plus;
    private String minus;
    private String multiply;
    private String divide;
    private String decimal;

    private String rightParenthesis;
    private String leftParenthesis;

    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();

        invalidExpression = this.getString(R.string.invalidExpression);
        invalidOperator = this.getString(R.string.invalidOperator);

        doubleZero = this.getString(R.string.button_double_zero);
        zero = this.getString(R.string.button_zero);
        one = this.getString(R.string.button_one);
        two = this.getString(R.string.button_two);
        three = this.getString(R.string.button_three);
        four = this.getString(R.string.button_four);
        five = this.getString(R.string.button_five);
        six = this.getString(R.string.button_six);
        seven = this.getString(R.string.button_seven);
        eight = this.getString(R.string.button_eight);
        nine = this.getString(R.string.button_nine);
        plus = this.getString(R.string.button_plus);
        minus = this.getString(R.string.button_minus);
        multiply = this.getString(R.string.button_multiply);
        divide = this.getString(R.string.button_divide);
        decimal = this.getString(R.string.button_decimal);

        rightParenthesis = this.getString(R.string.button_right_parenthesis);
        leftParenthesis = this.getString(R.string.button_left_parenthesis);

        sharedPrefs = getSharedPreferences("SHARED PREFS", Context.MODE_PRIVATE);

        expression = sharedPrefs.getString(spExpression, "");
        answer = sharedPrefs.getString(spAnswer, "");
        binding.userExpression.setText(expression);
        binding.calculatedAnswer.setText(answer);
        if (!expression.equals("")) {
            binding.btnDel.setVisibility(View.VISIBLE);
        }

        binding.userExpression.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Do nothing
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
                //Do nothing
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners() {

        binding.btn00.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + doubleZero;
            } else {
                expression += doubleZero;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn0.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + zero;
            } else {
                expression += zero;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn1.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + one;
            } else {
                expression += one;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn2.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + two;
            } else {
                expression += two;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn3.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + three;
            } else {
                expression += three;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn4.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + four;
            } else {
                expression += four;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn5.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + five;
            } else {
                expression += five;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn6.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + six;
            } else {
                expression += six;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn7.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + seven;
            } else {
                expression += seven;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn8.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + eight;
            } else {
                expression += eight;
            }
            binding.userExpression.setText(expression);
        });
        binding.btn9.setOnClickListener(v -> {
            if (expression.endsWith(rightParenthesis)) {
                expression += multiply + nine;
            } else {
                expression += nine;
            }
            binding.userExpression.setText(expression);
        });
        binding.btnDot.setOnClickListener(v -> {
            if(!expression.endsWith(decimal)) {
                expression = expression + decimal;
            }
            binding.userExpression.setText(expression);
        });

        binding.btnPlus.setOnClickListener(v -> checkOperator(plus));
        binding.btnMinus.setOnClickListener(v -> checkOperator(minus));
        binding.btnMultiply.setOnClickListener(v -> checkOperator(multiply));
        binding.btnDivide.setOnClickListener(v -> checkOperator(divide));

        binding.btnLeftBracket.setOnClickListener(v -> {
            if (!expression.equals("") && (expression.endsWith(rightParenthesis) ||
                    Character.isDigit(expression.charAt(expression.length() - 1)))) {
                expression += multiply + leftParenthesis;
            } else {
                expression += leftParenthesis;
            }
            binding.userExpression.setText(expression);
        });

        binding.btnRightBracket.setOnClickListener(v -> {
            expression += rightParenthesis;
            binding.userExpression.setText(expression);
        });

        binding.btnClear.setOnClickListener(v -> {
            expression = "";
            answer = "";
            binding.calculatedAnswer.setText(answer);
            binding.userExpression.setText(expression);
            binding.userExpression.setError(null);

            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(spExpression, "");
            editor.putString(spAnswer, "");
            editor.apply();
        });
        binding.btnDel.setOnClickListener(v -> {
            binding.userExpression.setError(null);
            if (expression.length() != 0) {
                expression = expression.substring(0, expression.length() - 1);
                binding.userExpression.setText(expression);
            }
        });
        binding.btnEqual.setOnClickListener(v -> {
            if (expression.length() == 0) {
                binding.userExpression.setError(null);
            } else {
                if (!expression.equals(sharedPrefs.getString(spExpression, ""))) {
                    lastInput = expression.substring(expression.length() - 1);
                    if (lastInput.equals(plus) || lastInput.equals(minus) ||
                            lastInput.equals(multiply) || lastInput.equals(divide)) {

                        binding.userExpression.requestFocus();
                        binding.userExpression.setError(invalidExpression);
                    } else {
                        binding.userExpression.setError(null);

                        //get tokens from expression into ArrayList
                        ArrayList<String> myTokens = createTokens(expression.replaceAll("\\s", ""));

                        //parse tokens and get answer
                        try {
                            myTokens = evaluateBrackets(myTokens, rightParenthesis);
                            myTokens = evaluateOperator(myTokens, divide);
                            myTokens = evaluateOperator(myTokens, multiply);
                            myTokens = evaluateOperator(myTokens, minus);
                            myTokens = evaluateOperator(myTokens, plus);
                            answer = myTokens.get(0);
                            if (answer.endsWith(decimal + zero))
                                answer = answer.substring(0, answer.length() - 2);
                        } catch (Exception e) {
                            binding.userExpression.requestFocus();
                            binding.userExpression.setError(invalidExpression);
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
        boolean checkPlusMinus = operator.equals(plus) || operator.equals(minus);
        if (expression.isEmpty()) {
            // + or - is entered
            if (checkPlusMinus) {
                expression = operator;
                binding.userExpression.setError(null);
                binding.userExpression.setText(expression);
            } else {
                //if x or / is entered , show error
                binding.userExpression.requestFocus();
                binding.userExpression.setError(invalidOperator + operator);
            }
        } else {
            //when input size = 1
            if (expression.length() == 1) {
                //if single input is + or -
                if (expression.equals(plus) || expression.equals(minus)) {
                    //display error when x or / are entered
                    if (operator.equals(multiply) || operator.equals(divide)) {
                        binding.userExpression.requestFocus();
                        binding.userExpression.setError(invalidOperator + operator);
                    } else if (checkPlusMinus) {
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
                if (lastInput.equals(plus) || lastInput.equals(minus) ||
                        lastInput.equals(multiply) || lastInput.equals(divide)) {
                    expression = expression.substring(0, expression.length() - 1) + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
                } else if (operator.equals(plus) || operator.equals(minus) ||
                        operator.equals(multiply) || operator.equals(divide)) {
                    expression = expression + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
                }
            }
        }
    }

    //Take the expressiion and divide it into tokens
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

    //Count the occurrence of operator in the expression
    public int countOperator(String operator, ArrayList<String> tokens) {
        int count = 0;
        for (String token : tokens) if (token.equals(operator)) count++;
        return count;
    }

    //Evaluate all the brackets in the expression
    public ArrayList<String> evaluateBrackets(ArrayList<String> tokens, String operator) {
        int count = countOperator(operator, tokens);
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                int indexOfRightParan = tokens.indexOf(rightParenthesis);
                ArrayList<String> temp = new ArrayList<>(tokens.subList(0, indexOfRightParan));
                int indexOfLeftParan = temp.lastIndexOf(leftParenthesis);
                ArrayList<String> subToken = new ArrayList<>(temp.subList(indexOfLeftParan + 1, indexOfRightParan));
                subToken = evaluateOperator(subToken, divide);
                subToken = evaluateOperator(subToken, multiply);
                subToken = evaluateOperator(subToken, minus);
                subToken = evaluateOperator(subToken, plus);

                tokens.set(indexOfLeftParan, subToken.get(0));
                tokens.subList(indexOfLeftParan + 1, indexOfRightParan + 1).clear();
            }
        }
        return tokens;
    }

    //Find an operator and evaluate the left and right values, return a smaller expression
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
                    if(operatorIndex == 0 && !operator.equals(plus)) {
                        tokens.set(operatorIndex + 1, minus + rightValue);
                    }
                    tokens.remove(operatorIndex);
                    continue;
                }
                String value = "";
                boolean checkBrackets = !(leftValue.equals(leftParenthesis) || leftValue.equals(rightParenthesis));
                switch (operator) {
                    case "/":
                        //divide
                        value = Float.toString(Float.parseFloat(leftValue) / Float.parseFloat(rightValue));
                        break;
                    case "x":
                        //multiply
                        value = Float.toString(Float.parseFloat(leftValue) * Float.parseFloat(rightValue));
                        break;
                    case "-":
                        //subtract
                        if (checkBrackets) {
                            value = Float.toString(Float.parseFloat(leftValue) - Float.parseFloat(rightValue));
                        }
                        break;
                    case "+":
                        //sum
                        if (checkBrackets) {
                            value = Float.toString(Float.parseFloat(leftValue) + Float.parseFloat(rightValue));
                        }
                        break;
                    default:
                        throw new IllegalStateException(invalidOperator + operator);
                }
                tokens.set(operatorIndex, value);
                tokens.remove(operatorIndex + 1);
                tokens.remove(operatorIndex - 1);
            }
        }
        return tokens;
    }

    //When app pauses store the values of expression and answer
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(spExpression, expression);
        editor.putString(spAnswer, answer);
        editor.apply();
    }
}
