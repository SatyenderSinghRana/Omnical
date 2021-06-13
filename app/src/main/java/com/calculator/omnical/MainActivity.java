package com.calculator.omnical;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.calculator.omnical.databinding.ActivityMainBinding;

import java.util.ArrayList;

/**
 * Basic calculator logic:
 * 1) Take input from user and create tokens.
 * 2) Evaluate Parenthesis followed by /, x, - and +.
 * 3) Again check for /, x, - and + as expression can be like 2 - 1 x ( 5 x 3 )
 * and it evaluates the remaining elements after parenthesis is evaluated.
 * 4) Finally display the result.
 * At different steps validations are inserted to get the desired behaviour of the calculator.
 */

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private static final String EXPRESSION_PREFS_KEY = "EXPRESSION";
    private static final String ANSWER_PREFS_KEY = "ANSWER";
    private static final String CONTINUOUS_INPUT_VALUE = "INPUT TYPE";
    private static final String CONTINUOUS_INPUT_ENABLED = "Continuous Input Enabled";
    private static final String CONTINUOUS_INPUT_DISABLED = "Continuous Input Disabled";

    private String INVALID_EXPRESSION;
    private String INVALID_OPERATOR;

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
    boolean is_input_continuous;
    boolean check_decimal_in_number = true;
    String passed_value;

    private String expression = "";
    private String answer = "";
    private String last_input = "";

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

        expression = SHARED_PREFERENCES.getString(EXPRESSION_PREFS_KEY, "");
        answer = SHARED_PREFERENCES.getString(ANSWER_PREFS_KEY, "");
        is_input_continuous = SHARED_PREFERENCES.getBoolean(CONTINUOUS_INPUT_VALUE, true);

        binding.switchInputType.setChecked(is_input_continuous);

        binding.switchInputType.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                is_input_continuous = true;
                Toast.makeText(this, CONTINUOUS_INPUT_ENABLED, Toast.LENGTH_LONG).show();
            } else {
                is_input_continuous = false;
                Toast.makeText(this, CONTINUOUS_INPUT_DISABLED, Toast.LENGTH_LONG).show();
            }
            SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
            editor.putBoolean(CONTINUOUS_INPUT_VALUE, is_input_continuous);
            editor.apply();
        });

        binding.userExpression.setText(expression);
        binding.calculatedAnswer.setText(answer);
        if (!expression.isEmpty()) {
            binding.btnDel.setVisibility(View.VISIBLE);
        }

        binding.userExpression.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    binding.btnDel.setVisibility(View.INVISIBLE);
                } else {
                    binding.btnDel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // When number buttons are pressed
    public void numberPressed(View view) {
        passed_value = view.getTag().toString();
        if (expression.endsWith(RIGHT_PARENTHESIS)) {
            expression += MULTIPLY + passed_value;
        } else {
            expression += passed_value;
        }
        binding.userExpression.setText(expression);
    }

    // When operator button is pressed
    public void operatorPressed(View view) {
        passed_value = view.getTag().toString();
        checkOperator(passed_value);
        check_decimal_in_number = true;
    }

    // When decimal button is pressed
    public void decimalPressed(View view) {
        passed_value = view.getTag().toString();
        if (!expression.endsWith(DECIMAL) && check_decimal_in_number) {
            expression += passed_value;
        }
        binding.userExpression.setText(expression);
        check_decimal_in_number = false;
    }

    // When delete button is pressed
    public void deletePressed(View view) {
        binding.userExpression.setError(null);
        if (expression.length() != 0) {
            expression = expression.substring(0, expression.length() - 1);
            binding.userExpression.setText(expression);
        }
        last_input = expression.substring(expression.length() - 1);
        if(last_input.equals(".")) {
            check_decimal_in_number = true;
        }
    }

    // When clear button is pressed
    public void clearPressed(View view) {
        expression = answer = "";
        binding.calculatedAnswer.setText(answer);
        binding.userExpression.setText(expression);
        binding.userExpression.setError(null);

        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
        editor.putString(EXPRESSION_PREFS_KEY, "");
        editor.putString(ANSWER_PREFS_KEY, "");
        editor.apply();
        check_decimal_in_number = true;
    }

    // When parenthesis buttons are pressed
    public void parenthesisPressed(View view) {
        check_decimal_in_number = true;
        int id = view.getId();
        passed_value = view.getTag().toString();
        if (id == R.id.btn_left_parenthesis) {
            if(expression.endsWith(DECIMAL)) {
                expression = expression.substring(0, expression.length() - 1);
            }
            if (!expression.equals("") && (expression.endsWith(RIGHT_PARENTHESIS) ||
                    Character.isDigit(expression.charAt(expression.length() - 1)))) {
                expression += MULTIPLY + passed_value;
            } else {
                expression += passed_value;
            }
            binding.userExpression.setText(expression);
        } else if (id == R.id.btn_right_parenthesis) {
            if(expression.endsWith(DECIMAL)) {
                expression = expression.substring(0, expression.length() - 1);
            }
            expression += passed_value;
            binding.userExpression.setText(expression);
        }
    }

    // When equal to button is pressed
    public void equal_To_Pressed(View view) {
        if (expression.length() == 0) {
            binding.userExpression.setError(null);
        } else {
            if (!expression.equals(SHARED_PREFERENCES.getString(EXPRESSION_PREFS_KEY, ""))) {
                last_input = expression.substring(expression.length() - 1);
                if (last_input.equals(PLUS) || last_input.equals(MINUS) ||
                        last_input.equals(MULTIPLY) || last_input.equals(DIVIDE)) {

                    binding.userExpression.requestFocus();
                    binding.userExpression.setError(INVALID_EXPRESSION);
                } else {
                    binding.userExpression.setError(null);

                    // Extract tokens from expression and store in ArrayList
                    ArrayList<String> myTokens = createTokens(expression.replaceAll("\\s", ""));
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
                            answer = myTokens.get(0);
                            if (answer.endsWith(DECIMAL + ZERO))
                                answer = answer.substring(0, answer.length() - 2);
                        } catch (Exception e) {
                            binding.userExpression.requestFocus();
                            binding.userExpression.setError(INVALID_EXPRESSION);
                        }
                        if (is_input_continuous) {
                            binding.calculatedAnswer.setText(answer);
                        } else {
                            binding.calculatedAnswer.setText(null);
                            binding.calculatedAnswer.setText(answer);
                            expression = answer;
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
        if (expression.isEmpty()) {
            // CHeck if + or - is entered
            if (checkPlusMinus) {
                expression = operator;
                binding.userExpression.setError(null);
                binding.userExpression.setText(expression);
            } else {
                // Display error if x or / is entered
                binding.userExpression.requestFocus();
                binding.userExpression.setError(INVALID_OPERATOR + operator);
            }
        } else {
            // Check if input size = 1
            if (expression.length() == 1) {
                // Check if single input is + or -
                if (expression.equals(PLUS) || expression.equals(MINUS)) {
                    // Display error if x or / is entered
                    if (operator.equals(MULTIPLY) || operator.equals(DIVIDE)) {
                        binding.userExpression.requestFocus();
                        binding.userExpression.setError(INVALID_OPERATOR + operator);
                    } else if (checkPlusMinus) {
                        // Replace input if + or - is entered
                        expression = operator;
                        binding.userExpression.setText(expression);
                        binding.userExpression.setError(null);
                    }
                } else {
                    // If a number is entered, concatenate it to the expression
                    expression = expression + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
                }
            } else {
                // When expression length is greater than 2
                last_input = expression.substring(expression.length() - 1);
                if (last_input.equals(PLUS) || last_input.equals(MINUS) ||
                        last_input.equals(MULTIPLY) || last_input.equals(DIVIDE)) {
                    expression = expression.substring(0, expression.length() - 1) + operator;
                    binding.userExpression.setText(expression);
                    binding.userExpression.setError(null);
                } else if (operator.equals(PLUS) || operator.equals(MINUS) ||
                        operator.equals(MULTIPLY) || operator.equals(DIVIDE)) {
                    expression = expression + operator;
                    binding.userExpression.setText(expression);
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
            if (Character.isDigit(e.charAt(i)) || e.charAt(i) ==  DECIMAL.charAt(0)) {
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
                    if (operatorIndex == 0 && operator.equals(MINUS)) {
                        tokens.set(operatorIndex + 1, MINUS + rightValue);
                    }
                    tokens.remove(operatorIndex);
                    continue;
                }
                try {
                    if(tokens.get(operatorIndex - 2).equals(MINUS)) {
                        tokens.set(operatorIndex - 1, MINUS + leftValue);
                        tokens.set(operatorIndex - 2, PLUS);
                    }
                } catch (Exception ignored) {}

                String value = "";
                boolean checkBrackets = !(leftValue.equals(LEFT_PARENTHESIS) || leftValue.equals(RIGHT_PARENTHESIS));
                switch (operator) {
                    case "/":
                        // Divide
                        value = Double.toString(Double.parseDouble(leftValue) / Double.parseDouble(rightValue));
                        break;
                    case "x":
                        // Multiply
                        value = Double.toString(Double.parseDouble(leftValue) * Double.parseDouble(rightValue));
                        break;
                    case "-":
                        // Subtract
                        if (checkBrackets) {
                            value = Double.toString(Double.parseDouble(leftValue) - Double.parseDouble(rightValue));
                        }
                        break;
                    case "+":
                        // Sum
                        if (checkBrackets) {
                            value = Double.toString(Double.parseDouble(leftValue) + Double.parseDouble(rightValue));
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

    // When app pauses/stops store the values of expression and answer
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
        editor.putString(EXPRESSION_PREFS_KEY, expression);
        editor.putString(ANSWER_PREFS_KEY, answer);
        editor.apply();
    }
}
