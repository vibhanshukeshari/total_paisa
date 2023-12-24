package com.vibhunorby.totalpaisa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcLite extends AppCompatActivity {

    Button btnClearDisplay;
    Button btnPercentage;
    Button btnDivision;
    Button btnMultiplication;
    Button btnSubmission;
    Button btnSum;
    Button btnEqual;
    Button btnDot;
    String tempNew = "";
    ImageButton btnDelete;
    boolean btnPercentagePressed = false;
    boolean isBtn0Pressed = false;
    BigDecimal tempNumber;
    boolean cPressed = false;
    TextView tvDisplay, tvPhrase;
    boolean isOperatorPressed = false;
    boolean isEqualPressed = false;
    BigDecimal num1 = BigDecimal.ZERO;
    BigDecimal num2 = BigDecimal.ZERO;
    char op = ' ';

    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;
    Button btn00;
    NumberFormatLocale numberFormatLocale;

    Vibrator vibrator;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_lite);


        final BottomNavigationView bottomNavigationView = findViewById(R.id.botton_navigation);

        findViews();

        toolbar = findViewById(R.id.myToolBar);
        toolbar.setTitle("Calc Lite");
        setSupportActionBar(toolbar);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btnClearDisplay.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.ac)));
        btnEqual.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.equal)));

        numberFormatLocale = new NumberFormatLocale();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(Color.parseColor("#15202b"));
        }

        bottomNavigationView.setSelectedItemId(R.id.simpleCalc);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.gst:
                        startActivity(new Intent(getApplicationContext(), Gst.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                    case R.id.totalPaisa:
                        onBackPressed();
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.simpleCalc:
//                        startActivity(new Intent(getApplicationContext(),CalcLite.class));
//                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

//////////////////////// this is used to auto increment and decrement the text size

// Create a TextWatcher to monitor changes to the text

        // 06/10/23 Amrit hostel
        // it took two days to achive increment and decrement along with horizontal scroll view


        tvDisplay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before the text changes.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called as the text changes.
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after the text has changed.

                int characterCount = s.length(); // Get the character count

                // Calculate the new text size based on the character count
                int newTextSize = 60 - (characterCount * 1); // Start with a base text size of 60sp

                // Ensure that the text size doesn't go below a minimum value
                int minTextSize = 30; // Set your desired minimum text size
                if (newTextSize < minTextSize) {
                    newTextSize = minTextSize;
                }

                // Set the new text size to the TextView
                tvDisplay.setTextSize(TypedValue.COMPLEX_UNIT_SP, newTextSize);
            }
        });

////////////////////// end auto scale tvDisplay/////////////////////////////////////////////////////

        btn0.setOnClickListener(view -> {
            vibrate();
            handleNumericButtonClick("0");

            isBtn0Pressed = true;
        });

        btn1.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("1");
                }
        );

        btn2.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("2");
        });

        btn3.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("3");
        });

        btn4.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("4");
        });

        btn5.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("5");
        });

        btn6.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("6");
        });

        btn7.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("7");
        });

        btn8.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("8");
        });

        btn9.setOnClickListener(v -> {
            vibrate();
            handleNumericButtonClick("9");
        });


        btn00.setOnClickListener(view -> {
            btn0.performClick(); // First click
            btn0.performClick(); // Second click
        });


        btnClearDisplay.setOnClickListener(v -> {
            vibrate();
            tvDisplay.setText("0");

            btnClearDisplay.setText(R.string.a_c);


//            btnEqual.setText("=");
//            btnEqual.setTextColor(Color.parseColor("#FFFFFF"));
            btnEqual.setEnabled(true);
//            btnEqual.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.blue_inactive)));


            String tvPhraseText = tvPhrase.getText().toString().replace(",", "");
            boolean containsOperator = tvPhraseText.contains("+") || tvPhraseText.contains("-") || tvPhraseText.contains("x") || tvPhraseText.contains("÷");


            if (isEqualPressed || !containsOperator) {
                clearScreen();
                return;

            }

            if (cPressed) {

                clearScreen();
                return;

            } else {

                isOperatorPressed = true;

            }

            String newNumber = "";  // New number to replace the last number

            // Find the index of the last operator
            int lastOperatorIndex = Math.max(Math.max(tvPhraseText.lastIndexOf("+"), tvPhraseText.lastIndexOf("-")),
                    Math.max(tvPhraseText.lastIndexOf("x"), tvPhraseText.lastIndexOf("÷")));

            if (lastOperatorIndex != -1) {
                // Get the substring after the last operator
                String afterOperator = tvPhraseText.substring(lastOperatorIndex + 1).trim();

                // Check if the substring is a number or starts with "0."
                if (!afterOperator.isEmpty() && afterOperator.matches("-?\\d+(\\.\\d*)?")) {
                    // Replace the last number with the new number
                    tvPhraseText = tvPhraseText.substring(0, lastOperatorIndex + 2) + newNumber;
                }
                cPressed = true;
            }

            addCommaAgain(tvPhraseText);

        });

        btnPercentage.setOnClickListener(v -> {
            vibrate();
            String tvPhraseText = tvPhrase.getText().toString().replace(",", "");

            String myTvPhraseNumberIfZeroOrNot = calculateViaLibrary(tvPhraseText);

            btnEqual.setEnabled(true);
//            btnEqual.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(),R.color.blue_inactive)));

            if (!tvPhraseText.isEmpty()) {

                if (myTvPhraseNumberIfZeroOrNot.equals("0")) {
                    tvPhraseText = "0";
                }


                if (tvPhraseText.endsWith(".")) {
                    tvPhraseText += 0;
                    tvPhrase.setText(tvPhraseText += 0);
                }
//
                btnPercentagePressed = true;


                // Find the last operator index
                int lastOperatorIndex = Math.max(Math.max(tvPhraseText.lastIndexOf("+"), tvPhraseText.lastIndexOf("-")),
                        Math.max(tvPhraseText.lastIndexOf("x"), tvPhraseText.lastIndexOf("÷")));

                if (lastOperatorIndex != -1 && !isOperatorPressed) {

                    char lastOperator = tvPhraseText.charAt(lastOperatorIndex);

                    // Check if the last operator is "+" or "-"
                    if (lastOperator == '+' || lastOperator == '-') {


                        if (lastOperatorIndex == tvPhraseText.length() - 1) {
                            // Check if the tvPhraseText string is a number
                            if (tvPhraseText.matches("-?\\d+(\\.\\d+)?")) {
                                // Convert the tvPhraseText string to a double
                                double number = Double.parseDouble(tvPhraseText);

                                double tvPhraseWithoutLastNumber = 0.0;


                                int lastNumberIndex = tvPhraseText.lastIndexOf(" ", lastOperatorIndex - 1);
                                if (lastNumberIndex != -1) {
                                    tvPhraseWithoutLastNumber = Double.parseDouble(calculateViaLibrary(tvPhraseText.substring(0, lastNumberIndex).trim()));

                                }

                                // Calculate the percentage value based on the previous number
                                double percentageValue = (number * tvPhraseWithoutLastNumber) / 100;
                                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                                String roundedPercentageValue = decimalFormat.format(percentageValue);


                                // Convert the percentage value back to a string

                                // Replace the last number in the tvPhraseText string with the percentage value
                                tvPhraseText = tvPhraseText.substring(0, lastOperatorIndex + 1) + " " + roundedPercentageValue;
                            }
                        } else {
                            // Get the substring after the last operator
                            String afterOperator = tvPhraseText.substring(lastOperatorIndex + 1).trim();

                            // Check if the substring is a number
                            if (afterOperator.matches("-?\\d+(\\.\\d+)?")) {
                                // Convert the last number to a double
                                double lastNumber = Double.parseDouble(afterOperator);

                                double tvPhraseWithoutLastNumber = 0.0;

                                int lastNumberIndex = tvPhraseText.lastIndexOf(" ", lastOperatorIndex - 1);
                                if (lastNumberIndex != -1) {


                                    tvPhraseWithoutLastNumber = Double.parseDouble(calculateViaLibrary(tvPhraseText.substring(0, lastNumberIndex).trim()));


                                }


                                // Calculate the percentage value based on the previous number
                                double percentageValue = (lastNumber * tvPhraseWithoutLastNumber) / 100;

                                DecimalFormat decimalFormat = new DecimalFormat("#0.00");
                                String roundedPercentageValue = decimalFormat.format(percentageValue);


                                // Convert the percentage value back to a string

                                // Replace the last number in the tvPhraseText string with the percentage value
                                tvPhraseText = tvPhraseText.substring(0, lastOperatorIndex + 1) + " " + roundedPercentageValue;
                            }
                        }


                    } else {


                        // Check if there are no operators in the tvPhraseText string
                        if (lastOperatorIndex == tvPhraseText.length() - 1) {
                            // Check if the tvPhraseText string is a number
                            if (tvPhraseText.matches("-?\\d+(\\.\\d+)?")) {
                                // Convert the tvPhraseText string to a double
                                double number = Double.parseDouble(tvPhraseText);

                                // Divide the number by 10
                                double updatedNumber = number / 100;

                                DecimalFormat decimalFormat = new DecimalFormat("#0.00");

                                // Set the updated number as the new tvPhraseText
                                tvPhraseText = decimalFormat.format(updatedNumber);


                            }
                        } else {
                            // Get the substring after the last operator
                            String afterOperator = tvPhraseText.substring(lastOperatorIndex + 1).trim();

                            // Check if the substring is a number
                            if (afterOperator.matches("-?\\d+(\\.\\d+)?")) {
                                // Convert the last number to a double
                                double lastNumber = Double.parseDouble(afterOperator);

                                // Divide the last number by 10
                                double updatedNumber = lastNumber / 100;

                                DecimalFormat decimalFormat = new DecimalFormat("#0.00");


                                // Convert the updated number back to a string
                                String updatedNumberString = decimalFormat.format(updatedNumber);

                                // Replace the last number in the tvPhraseText string
                                tvPhraseText = tvPhraseText.substring(0, lastOperatorIndex + 1) + " " + updatedNumberString;
                            }
                        }

                    }

                } else {


                    if ((!isOperatorPressed && !tvPhraseText.endsWith((" = ")) && !myTvPhraseNumberIfZeroOrNot.equals("0"))) {
                        double number = Double.parseDouble(tvPhraseText) / 100;

                        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

                        // Convert the updated number back to a string
                        tvPhraseText = decimalFormat.format(number);

                    }

                }

                if (isEqualPressed) {

                    double number = Double.parseDouble(tvDisplay.getText().toString().replace(",", "")) / 100;

                    DecimalFormat decimalFormat = new DecimalFormat("#0.00");

                    // Convert the updated number back to a string
                    tvPhraseText = decimalFormat.format(number);
                    isEqualPressed = false;

                }


                addCommaAgain(tvPhraseText);

                calculateViaLibrary(tvPhraseText);

            }

        });




        btnDelete.setOnClickListener(v -> {
            vibrate();
            String tvPhraseText = tvPhrase.getText().toString().replace(",","");
            String tvDisplayText = tvDisplay.getText().toString().replace(",","");

            if (!tvPhraseText.isEmpty() && !isEqualPressed) {

                while (tvPhraseText.endsWith(" ")) {
                    tvPhraseText = tvPhraseText.substring(0, tvPhraseText.length() - 1);
                }

                int lastOperatorIndex = Math.max(Math.max(tvPhraseText.lastIndexOf("+"), tvPhraseText.lastIndexOf("-")),
                        Math.max(tvPhraseText.lastIndexOf("x"), tvPhraseText.lastIndexOf("÷")));

                if (lastOperatorIndex >= 0 && lastOperatorIndex == tvPhraseText.length() - 1) {

                    isOperatorPressed = true;

                    return;
                }

                // Delete one digit at a time
                tvPhraseText = tvPhraseText.substring(0, tvPhraseText.length() - 1);


                if (!tvDisplayText.isEmpty()) {
                    tvDisplayText = tvDisplayText.substring(0, tvDisplayText.length() - 1);
                }

                if (tvDisplayText.isEmpty()) {
                    tvDisplayText = "0";
                }

                tvPhrase.setText(tvPhraseText);
                tvDisplay.setText(tvDisplayText);

                if (!tvPhraseText.isEmpty() && tvDisplayText.equals("0")) {

                    boolean hasZeroAtEnd = tvPhraseText.charAt(tvPhraseText.length() - 1) == '0';

                    if (!hasZeroAtEnd) {
                        tvPhraseText += "";
                        tvPhrase.setText(tvPhraseText);
                    }

                    btnClearDisplay.setText(R.string.a_c);
                    cPressed = true;
                    isOperatorPressed = true;
                }


                Pattern pattern = Pattern.compile("(?<=\\D|^)\\d*\\.?\\d+(?!\\S*\\D)");

                Matcher matcher = pattern.matcher(tvPhraseText);

                String lastNumber;

                while (matcher.find()) {
                    lastNumber = matcher.group();

                    BigDecimal bigDecimalLastNumber = new BigDecimal(lastNumber);
                    numberFormatLocale.setNumber(bigDecimalLastNumber);
                    tvDisplay.setText(numberFormatLocale.getNumberAfterFormatUnlimitedDecimal());
                }


                 pattern = Pattern.compile("[0-9]+\\.?[0-9]*");

// Create a matcher for the input string
                matcher = pattern.matcher(tvPhraseText);


                StringBuffer formattedText = new StringBuffer();

// Find all matches and replace them with formatted versions
                while (matcher.find()) {
                    String operands = matcher.group();
                    BigDecimal operandBigDecimal = new BigDecimal(operands);


                    BigDecimal tvPhraseTextBigDecimal = new BigDecimal(String.valueOf(operandBigDecimal));
                    numberFormatLocale.setNumber(tvPhraseTextBigDecimal);


                    // Append the formatted operand to the result
                    matcher.appendReplacement(formattedText,numberFormatLocale.getNumberAfterFormatUnlimitedDecimal());
                }

// Append the remaining part of the input string
                matcher.appendTail(formattedText);

                String formattedResult = formattedText.toString();
// Set the formatted result to your desired output (e.g., set it to a TextView)
                tvPhrase.setText(formattedResult);


            }
        });


        btnDivision.setOnClickListener(v -> {
            vibrate();
            btnEqual.setEnabled(true);
//
            String tvPhraseText = tvPhrase.getText().toString().replace(",","");


// Check if the string ends with an operator
            if (tvPhraseText.endsWith(" + ") || tvPhraseText.endsWith(" - ") || tvPhraseText.endsWith(" x ") || tvPhraseText.endsWith(" ÷ ")) {
                // Replace the last operator with 'x'
                String modifiedText = tvPhraseText.substring(0, tvPhraseText.length() - 3) + " ÷ ";

                // Set the modified text back to the TextView
//
                addCommaAgainUnlimited(modifiedText);

            } else {

                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString().replace(",", "");

                if (op != ' ') {
                    calculateViaLibrary(tvPhraseText);
                }

                if (btnPercentagePressed && !tvPhraseText.isEmpty()) {
                    tvPhraseText += " ÷ ";
//
                    addCommaAgainUnlimited(tvPhraseText);
                    btnPercentagePressed = false;

                } else {
                    tempNew += tvDisplayText + " ÷ ";
                    addCommaAgainUnlimited(tempNew);
                }

            }

            op = '÷';
        });

        btnMultiplication.setOnClickListener(v -> {

            vibrate();

            btnEqual.setEnabled(true);

            String tvPhraseText = tvPhrase.getText().toString().replace(",", "");

            if (tvPhraseText.endsWith(" + ") || tvPhraseText.endsWith(" - ") || tvPhraseText.endsWith(" x ") || tvPhraseText.endsWith(" ÷ ")) {
                // Replace the last operator with 'x'
                String modifiedText = tvPhraseText.substring(0, tvPhraseText.length() - 3) + " x ";

                tvPhrase.setText(modifiedText);
                addCommaAgainUnlimited(modifiedText);
            } else {

                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString().replace(",", "");

                if (op != ' ') {
                    calculateViaLibrary(tvPhraseText);
                }

                if (btnPercentagePressed && !tvPhraseText.isEmpty()) {
                    tvPhraseText += " x ";

                    addCommaAgainUnlimited(tvPhraseText);
                    btnPercentagePressed = false;


                } else {
                    tempNew += tvDisplayText + " x ";

                    addCommaAgainUnlimited(tempNew);

                }

            }
            op = 'x';
        });


        btnSubmission.setOnClickListener(v -> {
            vibrate();
            btnEqual.setEnabled(true);

            String tvPhraseText = tvPhrase.getText().toString().replace(",", "");

            // Check if the string ends with an operator
            if (tvPhraseText.endsWith(" + ") || tvPhraseText.endsWith(" - ") || tvPhraseText.endsWith(" x ") || tvPhraseText.endsWith(" ÷ ")) {
                // Replace the last operator with 'x'
                String modifiedText = tvPhraseText.substring(0, tvPhraseText.length() - 3) + " - ";

                // Set the modified text back to the TextView

                addCommaAgainUnlimited(modifiedText);
            } else {
                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString().replace(",", "");

                if (op != ' ') {
                    calculateViaLibrary(tvPhraseText);

                }

                if (btnPercentagePressed && !tvPhraseText.isEmpty()) {
                    tvPhraseText += " - ";
//                    tvPhrase.setText(tvPhraseText);
                    addCommaAgainUnlimited(tvPhraseText);
                    btnPercentagePressed = false;

                } else {
                    tempNew += tvDisplayText + " - ";
                    addCommaAgainUnlimited(tempNew);

                }

            }

            op = '-';

        });

        btnSum.setOnClickListener(v -> {
            vibrate();

            btnEqual.setEnabled(true);

            String tvPhraseText = tvPhrase.getText().toString().replace(",", "");

            // Check if the string ends with an operator
            if (tvPhraseText.endsWith(" + ") || tvPhraseText.endsWith(" - ") || tvPhraseText.endsWith(" x ") || tvPhraseText.endsWith(" ÷ ")) {
                // Replace the last operator with 'x'
                String modifiedText = tvPhraseText.substring(0, tvPhraseText.length() - 3) + " + ";

                // Set the modified text back to the TextView
                addCommaAgainUnlimited(modifiedText);
            } else {

                isOperatorPressed = true;
                String tvDisplayText = tvDisplay.getText().toString().replace(",", "");

                if (op != ' ') {
                    calculateViaLibrary(tvPhraseText);
                }

                op = '+';

                if (btnPercentagePressed && !tvPhraseText.isEmpty()) {
                    tvPhraseText += " + ";
                    addCommaAgainUnlimited(tvPhraseText);
                    btnPercentagePressed = false;

                } else {
                    tempNew += tvDisplayText + " + ";
                    addCommaAgainUnlimited(tempNew);

                }

            }

            op = '+';

        });


        btnEqual.setOnClickListener(v -> {
            String tvDisplayText = tvDisplay.getText().toString().replace(",", "");
            String tvPhraseText = tvPhrase.getText().toString().replace(",", "");
            vibrate();
            if (tvPhraseText.contains("=") || tvPhraseText.isEmpty()) {

                return;
            }


            if (tvPhraseText.endsWith(" + ") || tvPhraseText.endsWith(" - ") || tvPhraseText.endsWith(" x ") || tvPhraseText.endsWith(" ÷ ")) {
                // Replace the last operator with 'x'
                String modifiedText = tvPhraseText.substring(0, tvPhraseText.length() - 3);


                // Set the modified text back to the TextView
                addCommaAgainUnlimited(modifiedText);

            }

            btnClearDisplay.setText(R.string.a_c);

            num2 = new BigDecimal(tvDisplayText);

            isOperatorPressed = false;
            isEqualPressed = true;
            btnPercentagePressed = false;


            calculateViaLibrary(tvPhrase.getText().toString().replace(",", ""));

            String temp = tvPhrase.getText().toString().replace(",", "") + " = ";

            addCommaAgainUnlimited(temp);
            op = ' ';
            num1 = BigDecimal.ZERO;
            num2 = BigDecimal.ZERO;
            tempNew = "";
            tempNumber = BigDecimal.ZERO;

        });


        btnDot.setOnClickListener(v -> {


            vibrate();


            btnDot.setEnabled(false);


            new Handler().postDelayed(() -> {
                btnDot.setEnabled(true); // Enable the button again
            }, 1000);

            btnEqual.setEnabled(true);


            isBtn0Pressed = false;

            String tvDisplayText = tvDisplay.getText().toString();
            String tvPhraseText = tvPhrase.getText().toString();

            String lastNumber = "";

            // Check if the tvPhraseText contains an operator
            if (tvPhraseText.contains("+") || tvPhraseText.contains("-") || tvPhraseText.contains("x") || tvPhraseText.contains("÷")) {

                // Split the tvPhraseText into an array of strings based on the operators
                String[] numbers = tvPhraseText.split("[+\\-x÷]");

                // Get the last number from the array (which will be the number after the last operator)
                if (numbers.length > 0) {
                    lastNumber = numbers[numbers.length - 1].trim();
                }
            }

            if ((lastNumber.contains(".") || tvDisplayText.contains(".")) && (!isEqualPressed && !isOperatorPressed)) {

                return;
            }

            if (isOperatorPressed || isEqualPressed || tvPhraseText.isEmpty()) {

                if (!isBtn0Pressed) {
                    btn0.performClick();
                }

            }


            Handler handler = new Handler();
            handler.postDelayed(() -> {

                String tvDisplayText1 = tvDisplay.getText().toString();
                String tvPhraseText1 = tvPhrase.getText().toString();


                tvDisplayText1 = tvDisplayText1 + ".";

                tvPhraseText1 = tvPhraseText1 + ".";
                cPressed = false;

                tvDisplay.setText(tvDisplayText1);
                tvPhrase.setText(tvPhraseText1);


            }, 100);


        });
    }

    private void handleNumericButtonClick(String value) {

        btnEqual.setEnabled(true);

        if (isEqualPressed) {

            tvDisplay.setText("");


            if (!isOperatorPressed) {
                tvPhrase.setText("");

            }


            isEqualPressed = false;
        }


        if (isOperatorPressed) {

            tvDisplay.setText("");

            isOperatorPressed = false;
        }

        String tvDisplayText = tvDisplay.getText().toString().replace(",", "");
        String tvPhraseText = tvPhrase.getText().toString().replace(",", "");

        if (tvDisplayText.equals("0")) {
            tvDisplayText = "";

        }

        int lastOperatorIndex = Math.max(Math.max(tvPhraseText.lastIndexOf("+"), tvPhraseText.lastIndexOf("-")),
                Math.max(tvPhraseText.lastIndexOf("x"), tvPhraseText.lastIndexOf("÷")));

        String lastDigit = tvPhraseText.substring(lastOperatorIndex + 1).trim();
        if (lastDigit.equals("0")) {
            tvPhraseText = tvPhraseText.substring(0, tvPhraseText.length() - 1);
        }

        //---------------23/09/23--------------

        tvDisplayText = tvDisplayText + value;

        BigDecimal tvDisplayTextInBigDecimal = new BigDecimal(tvDisplayText.replace(",", ""));
        numberFormatLocale.setNumber(tvDisplayTextInBigDecimal);

        tvDisplay.setText(numberFormatLocale.getNumberAfterFormatUnlimitedDecimal());

        //----------------------------
        tvPhraseText = tvPhraseText + value;

        addCommaAgainUnlimited(tvPhraseText);


        isBtn0Pressed = false;
        btnClearDisplay.setText("C");
        cPressed = false;
    }

    private void clearScreen() {
        isOperatorPressed = false;
        num1 = BigDecimal.ZERO;
        num2 = BigDecimal.ZERO;
        tempNumber = BigDecimal.ZERO;
        tempNew = "";
        op = ' ';
        tvPhrase.setText("");
        tvDisplay.setText("0");
        isEqualPressed = false;
        isOperatorPressed = false;
        isBtn0Pressed = false;
        tvDisplay.setText("0");
    }


    private String calculateViaLibrary(String expression) {


        try {
            double result = eval(expression.replaceAll("x", "*").replaceAll("÷", "/"));
            BigDecimal roundedResult = BigDecimal.valueOf(result);

            // Check if the rounded result is a whole number
            if (roundedResult.stripTrailingZeros().scale() <= 0) {

                numberFormatLocale.setNumber(roundedResult);
                tvDisplay.setText(numberFormatLocale.getNumberAfterFormat());


                String temp = tvPhrase.getText().toString();
                tvPhrase.setText(temp);



                return roundedResult.stripTrailingZeros().toPlainString();

            } else {

                numberFormatLocale.setNumber(roundedResult);

                tvDisplay.setText(numberFormatLocale.getNumberAfterFormat());

                String temp = tvPhrase.getText().toString();
                tvPhrase.setText(temp);
                return roundedResult.toPlainString();
            }


        } catch (RuntimeException e) {
            // Handle expression evaluation error
            e.printStackTrace();
        }

        return "";
    }

    public static double eval(final String str) {
        try {
            Expression expression = new ExpressionBuilder(str)
                    .build();
            return expression.evaluate();
        } catch (Exception e) {
            // Handle any evaluation or parsing errors
            e.printStackTrace();
            return Double.NaN;
        }
    }

    private void findViews() {
        btnClearDisplay = findViewById(R.id.btn_clear_display);
        btnPercentage = findViewById(R.id.btn_percentage);
        btnDelete = findViewById(R.id.btn_delete);
        btnDivision = findViewById(R.id.btn_division);
        btnMultiplication = findViewById(R.id.btn_multiplication);
        btnSubmission = findViewById(R.id.btn_submission);
        btnSum = findViewById(R.id.btn_sum);
        btnEqual = findViewById(R.id.btn_equal);
        btnDot = findViewById(R.id.btn_dot);
        tvDisplay = findViewById(R.id.tv_display);
        tvPhrase = findViewById(R.id.tv_phrase);

        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn00 = findViewById(R.id.btn_00);
    }

    public void vibrate() {
        if (vibrator != null) {

            vibrator.vibrate(1);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calclite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.CopyButton) {

            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // this is used because if user has not pressed the equal button the intentional amount may not be copied.
            btnEqual.performClick();
            ClipData clipData = ClipData.newPlainText("Calc Lite", tvDisplay.getText().toString());
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Amount copied to clipboard", Toast.LENGTH_SHORT).show();



        }
        return super.onOptionsItemSelected(item);
    }


    public void addCommaAgain(String tvPhraseText){


        Pattern  pattern = Pattern.compile("[0-9]+\\.?[0-9]*");

// Create a matcher for the input string
        Matcher matcher = pattern.matcher(tvPhraseText);

        StringBuffer formattedText = new StringBuffer();

// Find all matches and replace them with formatted versions
        while (matcher.find()) {
            String operands = matcher.group();
            BigDecimal operandBigDecimal = new BigDecimal(operands);


            BigDecimal tvPhraseTextBigDecimal = new BigDecimal(String.valueOf(operandBigDecimal));
            numberFormatLocale.setNumber(tvPhraseTextBigDecimal);


// there was a bug due to getNumberAfterFormat a logic was getting wrong so that i converted it to getNumberAfterFormatUnlimitedDecimal;
            // Append the formatted operand to the result
            matcher.appendReplacement(formattedText,numberFormatLocale.getNumberAfterFormatUnlimitedDecimal());
        }

// Append the remaining part of the input string
        matcher.appendTail(formattedText);

        String formattedResult = formattedText.toString();
// Set the formatted result to your desired output (e.g., set it to a TextView)
        tvPhrase.setText(formattedResult);

    }
    public void addCommaAgainUnlimited(String tvPhraseText){


        Pattern  pattern = Pattern.compile("[0-9]+\\.?[0-9]*");

// Create a matcher for the input string
        Matcher matcher = pattern.matcher(tvPhraseText);

        StringBuffer formattedText = new StringBuffer();

// Find all matches and replace them with formatted versions
        while (matcher.find()) {
            String operands = matcher.group();
            BigDecimal operandBigDecimal = new BigDecimal(operands);


            BigDecimal tvPhraseTextBigDecimal = new BigDecimal(String.valueOf(operandBigDecimal));
            numberFormatLocale.setNumber(tvPhraseTextBigDecimal);


            // Append the formatted operand to the result
            matcher.appendReplacement(formattedText,numberFormatLocale.getNumberAfterFormatUnlimitedDecimal());
        }

// Append the remaining part of the input string
        matcher.appendTail(formattedText);

        String formattedResult = formattedText.toString();
// Set the formatted result to your desired output (e.g., set it to a TextView)
        tvPhrase.setText(formattedResult);





    }

    @Override
    protected void onPause() {
        super.onPause();

// Save text to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tvDisplayText", tvDisplay.getText().toString());
        editor.putString("tvPhraseText", tvPhrase.getText().toString());
        editor.putBoolean("equal_button",isEqualPressed);
        editor.putBoolean("operator_button",isOperatorPressed);
        editor.apply();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String tvDisplayText = sharedPreferences.getString("tvDisplayText", "0");
        String tvPhraseText = sharedPreferences.getString("tvPhraseText", "");
        boolean isEqualButtonPressed = sharedPreferences.getBoolean("equal_button",false);
        boolean isOperatorButtonPressed = sharedPreferences.getBoolean("operator_button",false);

        // Set the retrieved text to the TextViews
        tvDisplay.setText(tvDisplayText);
        tvPhrase.setText(tvPhraseText);
        isEqualPressed =  isEqualButtonPressed;
        isOperatorPressed = isOperatorButtonPressed;

        if(!isEqualPressed && !tvDisplay.getText().toString().equals("0")){
            btnClearDisplay.setText("C");
        }
    }
}

