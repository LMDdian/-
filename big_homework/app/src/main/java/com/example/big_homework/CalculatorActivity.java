package com.example.big_homework;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder currentInput = new StringBuilder();
    private double firstOperand = Double.NaN;
    private double secondOperand;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.calculator_display);

        setNumericButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumericButtonListeners() {
        int[] numericButtons = {
                R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
                R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9, R.id.button_dot
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                currentInput.append(button.getText().toString());
                display.setText(currentInput.toString());
            }
        };

        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorButtonListeners() {
        int[] operatorButtons = {
                R.id.button_clear, R.id.button_delete, R.id.button_divide, R.id.button_multiply,
                R.id.button_minus, R.id.button_plus, R.id.button_equals
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                int buttonId = button.getId();

                if (buttonId == R.id.button_clear) {
                    currentInput.setLength(0);
                    firstOperand = Double.NaN;
                    operator = "";
                    display.setText("0");
                } else if (buttonId == R.id.button_delete) {
                    if (currentInput.length() > 0) {
                        currentInput.setLength(currentInput.length() - 1);
                        display.setText(currentInput.length() > 0 ? currentInput.toString() : "0");
                    }
                } else if (buttonId == R.id.button_equals) {
                    if (!Double.isNaN(firstOperand)) {
                        secondOperand = Double.parseDouble(currentInput.toString());
                        performCalculation();
                    }
                } else if (buttonId == R.id.button_divide || buttonId == R.id.button_multiply ||
                        buttonId == R.id.button_minus || buttonId == R.id.button_plus) {
                    if (currentInput.length() > 0) {
                        operator = button.getText().toString();
                        firstOperand = Double.parseDouble(currentInput.toString());
                        currentInput.setLength(0);
                    }
                }
            }
        };

        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void performCalculation() {
        switch (operator) {
            case "+":
                firstOperand += secondOperand;
                break;
            case "-":
                firstOperand -= secondOperand;
                break;
            case "×":
                firstOperand *= secondOperand;
                break;
            case "÷":
                if (secondOperand != 0) {
                    firstOperand /= secondOperand;
                } else {
                    display.setText("Error");
                    currentInput.setLength(0);
                    return;
                }
                break;
            default:
                break;
        }
        display.setText(String.valueOf(firstOperand));
        currentInput.setLength(0);
        currentInput.append(firstOperand);
        firstOperand = Double.NaN;
    }
}
