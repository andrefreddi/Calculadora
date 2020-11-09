package com.example.calculadora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private String pendingOperation = "=";

    private static final String STATE_PENDING_OPERATION = "PendingOperation";
    private static final String STATE_OPERAND1 = "Operand1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button = findViewById(R.id.button);
        Button button16 = findViewById(R.id.button16);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button6 = findViewById(R.id.button6);
        Button button8 = findViewById(R.id.button8);
        Button button7 = findViewById(R.id.button7);
        Button button5 = findViewById(R.id.button5);
        Button button13 = findViewById(R.id.button13);
        Button button15 = findViewById(R.id.button15);

        Button button14 = findViewById(R.id.button14);
        Button button10= findViewById(R.id.button10);
        Button button11= findViewById(R.id.button11);
        Button button12 = findViewById(R.id.button12);
        Button button9 = findViewById(R.id.button9);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
         public void onClick(View view) {
            Button b = (Button) view;
            newNumber.append(b.getText().toString());
            }
        };

        button.setOnClickListener(listener);
        button16.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button13.setOnClickListener(listener);
        button15.setOnClickListener(listener);
        button6.setOnClickListener(listener);


        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();

                try{
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue, op);
                } catch (NumberFormatException e ) {
                    newNumber.setText("");
                }

                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        button14.setOnClickListener(opListener);
        button10.setOnClickListener(opListener);
        button11.setOnClickListener(opListener);
        button12.setOnClickListener(opListener);
        button9.setOnClickListener(opListener);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(STATE_PENDING_OPERATION, pendingOperation);
        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
        displayOperation.setText(pendingOperation);
    }

    private void performOperation(Double value, String operation) {
        if (operand1 == null) {
            operand1 = value;
        } else {


            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }

            switch (pendingOperation) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if(value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }

}