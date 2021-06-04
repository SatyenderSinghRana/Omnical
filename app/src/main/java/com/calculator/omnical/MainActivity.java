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

/**
 * Basic calculator logic:
 * 1) Take input from user and create tokens.
 * 2) Evaluate Parenthesis followed by /, x, - and +.
 * 3) Again check for /, x, - and + as expression can be like 2 - 1 x ( 5 x 3 )
 * Step 3) checks the remaining elements after parenthesis is evaluated.
 * 4) Finally display the result.
 * <p>
 * At different steps validations are inserted to get the desired behaviour of the calculator.
 */

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private String EXPRESSION = "";
    private String ANSWER = "";
    private String LAST_INPUT = "";
    private String INVALID_EXPRESSION;
    private String INVALID_OPERATOR;
    private static final String EXPRESSION_PREFS_KEY = "EXPRESSION";
    private static final String ANSWER_PREFS_KEY = "ANSWER";

    private String DOUBLE_ZERO;
    private String ZERO;
    private String ONE;
    private String TWO;
    private String THREE;
    private String FOUR;
    private String FIVE;
    private String SIX;
    private String SEVEN;
    private String EIGHT;
    private String NINE;
    private String PLUS;
    private String MINUS;
    private String MULTIPLY;
    private String DIVIDE;
    private String DECIMAL;
    private String RIGHT_PARENTHESIS;
    private String LEFT_PARENTHESIS;
    private String SHARED_PREFS;

    SharedPreferences SHARED_PREFERENCES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        INVALID_EXPRESSION = this.getString(R.string.invalidExpression);
        INVALID_OPERATOR = this.getString(R.string.invalidOperator);
        SHARED_PREFS = this.getString(R.string.sharedPrefs);

        DOUBLE_ZERO = this.getString(R.string.button_double_zero);
        ZERO = this.getString(R.string.button_zero);
        ONE = this.getString(R.string.button_one);
        TWO = this.getString(R.string.button_two);
        THREE = this.getString(R.string.button_three);
        FOUR = this.getString(R.string.button_four);
        FIVE = this.getString(R.string.button_five);
        SIX = this.getString(R.string.button_six);
        SEVEN = this.getString(R.string.button_seven);
        EIGHT = this.getString(R.string.button_eight);
        NINE = this.getString(R.string.button_nine);
        PLUS = this.getString(R.string.button_plus);
        MINUS = this.getString(R.string.button_minus);
        MULTIPLY = this.getString(R.string.button_multiply);
        DIVIDE = this.getString(R.string.button_divide);
        DECIMAL = this.getString(R.string.button_decimal);
        RIGHT_PARENTHESIS = this.getString(R.string.button_right_parenthesis);
        LEFT_PARENTHESIS = this.getString(R.string.button_left_parenthesis);

        SHARED_PREFERENCES = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        EXPRESSION = SHARED_PREFERENCES.getString(EXPRESSION_PREFS_KEY, "");
        ANSWER = SHARED_PREFERENCES.getString(ANSWER_PREFS_KEY, "");
        binding.userExpression.setText(EXPRESSION);
        binding.calculatedAnswer.setText(ANSWER);
        if (!EXPRESSION.isEmpty()) {
            binding.btnDel.setVisibility(View.VISIBLE);
        }

        binding.userExpression.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
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
                // Do nothing
            }
        });
    }

    public void buttonPressed(View view) {
        int id = view.getId();
        String PASSED_VALUE = view.getTag().toString();
        if (id == R.id.btn_00 || id == R.id.btn_0 || id == R.id.btn_1 || id == R.id.btn_2 || id == R.id.btn_3 || id == R.id.btn_4
                || id == R.id.btn_5 || id == R.id.btn_6 || id == R.id.btn_7 || id == R.id.btn_8 || id == R.id.btn_9) {
            if (EXPRESSION.endsWith(RIGHT_PARENTHESIS)) {
                EXPRESSION += MULTIPLY + PASSED_VALUE;
            } else {
                EXPRESSION += PASSED_VALUE;
            }
            binding.userExpression.setText(EXPRESSION);
        } else if (id == R.id.btn_plus || id == R.id.btn_minus || id == R.id.btn_multiply || id == R.id.btn_divide) {
            checkOperator(PASSED_VALUE);
        } else if (id == R.id.btn_decimal) {
            if (!EXPRESSION.endsWith(DECIMAL)) {
                EXPRESSION += PASSED_VALUE;
            }
            binding.userExpression.setText(EXPRESSION);
        } else if (id == R.id.btn_left_parenthesis) {
            if (!EXPRESSION.equals("") && (EXPRESSION.endsWith(RIGHT_PARENTHESIS) ||
                    Character.isDigit(EXPRESSION.charAt(EXPRESSION.length() - 1)))) {
                EXPRESSION += MULTIPLY + PASSED_VALUE;
            } else {
                EXPRESSION += PASSED_VALUE;
            }
            binding.userExpression.setText(EXPRESSION);
        } else if (id == R.id.btn_right_parenthesis) {
            EXPRESSION += PASSED_VALUE;
            binding.userExpression.setText(EXPRESSION);
        } else if (id == R.id.btn_clear) {
            EXPRESSION = ANSWER = "";
            binding.calculatedAnswer.setText(ANSWER);
            binding.userExpression.setText(EXPRESSION);
            binding.userExpression.setError(null);

            SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
            editor.putString(EXPRESSION_PREFS_KEY, "");
            editor.putString(ANSWER_PREFS_KEY, "");
            editor.apply();
        } else if (id == R.id.btn_del) {
            binding.userExpression.setError(null);
            if (EXPRESSION.length() != 0) {
                EXPRESSION = EXPRESSION.substring(0, EXPRESSION.length() - 1);
                binding.userExpression.setText(EXPRESSION);
            }
        } else if (id == R.id.btn_equal) {
            if (EXPRESSION.length() == 0) {
                binding.userExpression.setError(null);
            } else {
                if (!EXPRESSION.equals(SHARED_PREFERENCES.getString(EXPRESSION_PREFS_KEY, ""))) {
                    LAST_INPUT = EXPRESSION.substring(EXPRESSION.length() - 1);
                    if (LAST_INPUT.equals(PLUS) || LAST_INPUT.equals(MINUS) ||
                            LAST_INPUT.equals(MULTIPLY) || LAST_INPUT.equals(DIVIDE)) {

                        binding.userExpression.requestFocus();
                        binding.userExpression.setError(INVALID_EXPRESSION);
                    } else {
                        binding.userExpression.setError(null);

                        // Extract tokens from expression and store in ArrayList
                        ArrayList<String> myTokens = createTokens(EXPRESSION.replaceAll("\\s", ""));
                        if (countOperator(LEFT_PARENTHESIS, myTokens) != countOperator(RIGHT_PARENTHESIS, myTokens)) {
                            binding.userExpression.requestFocus();
                            binding.userExpression.setError(INVALID_EXPRESSION);
                        } else {
                            // Parse tokens and evaluate the answer
                            try {
                                myTokens = evaluateBrackets(myTokens, RIGHT_PARENTHESIS);
                                myTokens = evaluateOperator(myTokens, DIVIDE);
                                myTokens = evaluateOperator(myTokens, MULTIPLY);
                                myTokens = evaluateOperator(myTokens, MINUS);
                                myTokens = evaluateOperator(myTokens, PLUS);
                                ANSWER = myTokens.get(0);
                                if (ANSWER.endsWith(DECIMAL + ZERO))
                                    ANSWER = ANSWER.substring(0, ANSWER.length() - 2);
                            } catch (Exception e) {
                                binding.userExpression.requestFocus();
                                binding.userExpression.setError(INVALID_EXPRESSION);
                            }
                            binding.calculatedAnswer.setText(ANSWER);
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void checkOperator(String operator) {
        // Check if the expression is empty
        boolean checkPlusMinus = operator.equals(PLUS) || operator.equals(MINUS);
        if (EXPRESSION.isEmpty()) {
            // CHeck if + or - is entered
            if (checkPlusMinus) {
                EXPRESSION = operator;
                binding.userExpression.setError(null);
                binding.userExpression.setText(EXPRESSION);
            } else {
                // Display error if x or / is entered
                binding.userExpression.requestFocus();
                binding.userExpression.setError(INVALID_OPERATOR + operator);
            }
        } else {
            // Check if input size = 1
            if (EXPRESSION.length() == 1) {
                // Check if single input is + or -
                if (EXPRESSION.equals(PLUS) || EXPRESSION.equals(MINUS)) {
                    // Display error if x or / is entered
                    if (operator.equals(MULTIPLY) || operator.equals(DIVIDE)) {
                        binding.userExpression.requestFocus();
                        binding.userExpression.setError(INVALID_OPERATOR + operator);
                    } else if (checkPlusMinus) {
                        // Replace input if + or - is entered
                        EXPRESSION = operator;
                        binding.userExpression.setText(EXPRESSION);
                        binding.userExpression.setError(null);
                    }
                } else {
                    // If a number is entered, concatenate it to the expression
                    EXPRESSION = EXPRESSION + operator;
                    binding.userExpression.setText(EXPRESSION);
                    binding.userExpression.setError(null);
                }
            } else {
                // When expression length is greater than 2
                LAST_INPUT = EXPRESSION.substring(EXPRESSION.length() - 1);
                if (LAST_INPUT.equals(PLUS) || LAST_INPUT.equals(MINUS) ||
                        LAST_INPUT.equals(MULTIPLY) || LAST_INPUT.equals(DIVIDE)) {
                    EXPRESSION = EXPRESSION.substring(0, EXPRESSION.length() - 1) + operator;
                    binding.userExpression.setText(EXPRESSION);
                    binding.userExpression.setError(null);
                } else if (operator.equals(PLUS) || operator.equals(MINUS) ||
                        operator.equals(MULTIPLY) || operator.equals(DIVIDE)) {
                    EXPRESSION = EXPRESSION + operator;
                    binding.userExpression.setText(EXPRESSION);
                    binding.userExpression.setError(null);
                }
            }
        }
    }

    // Split the expression into tokens
    // Return an ArrayList of individual tokens
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

    // Count the occurrence of operator in the expression
    public int countOperator(String operator, ArrayList<String> tokens) {
        int count = 0;
        for (String token : tokens) if (token.equals(operator)) count++;
        return count;
    }

    // Evaluate all the brackets in the expression
    public ArrayList<String> evaluateBrackets(ArrayList<String> tokens, String operator) {
        int count = countOperator(operator, tokens);
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                int indexOfRightParen = tokens.indexOf(RIGHT_PARENTHESIS);
                ArrayList<String> temp = new ArrayList<>(tokens.subList(0, indexOfRightParen));
                int indexOfLeftParen = temp.lastIndexOf(LEFT_PARENTHESIS);
                ArrayList<String> subToken = new ArrayList<>(temp.subList(indexOfLeftParen + 1, indexOfRightParen));
                subToken = evaluateOperator(subToken, DIVIDE);
                subToken = evaluateOperator(subToken, MULTIPLY);
                subToken = evaluateOperator(subToken, MINUS);
                subToken = evaluateOperator(subToken, PLUS);

                tokens.set(indexOfLeftParen, subToken.get(0));
                tokens.subList(indexOfLeftParen + 1, indexOfRightParen + 1).clear();
            }
        }
        return tokens;
    }

    /*
     * Look for the operator and evaluate the left and right values
     * Returns a smaller expression
     */
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
                    if (operatorIndex == 0 && !operator.equals(PLUS)) {
                        tokens.set(operatorIndex + 1, MINUS + rightValue);
                    }
                    tokens.remove(operatorIndex);
                    continue;
                }
                String value = "";
                boolean checkBrackets = !(leftValue.equals(LEFT_PARENTHESIS) || leftValue.equals(RIGHT_PARENTHESIS));
                switch (operator) {
                    case "/":
                        // Divide
                        value = Float.toString(Float.parseFloat(leftValue) / Float.parseFloat(rightValue));
                        break;
                    case "x":
                        // Multiply
                        value = Float.toString(Float.parseFloat(leftValue) * Float.parseFloat(rightValue));
                        break;
                    case "-":
                        // Subtract
                        if (checkBrackets) {
                            value = Float.toString(Float.parseFloat(leftValue) - Float.parseFloat(rightValue));
                        }
                        break;
                    case "+":
                        // Sum
                        if (checkBrackets) {
                            value = Float.toString(Float.parseFloat(leftValue) + Float.parseFloat(rightValue));
                        }
                        break;
                    default:
                        throw new IllegalStateException(INVALID_OPERATOR + operator);
                }
                tokens.set(operatorIndex, value);
                tokens.remove(operatorIndex + 1);
                tokens.remove(operatorIndex - 1);
            }
        }
        return tokens;
    }

    // When app pauses store the values of expression and answer
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
        editor.putString(EXPRESSION_PREFS_KEY, EXPRESSION);
        editor.putString(ANSWER_PREFS_KEY, ANSWER);
        editor.apply();
    }
}
