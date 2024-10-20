package org.jvrb.java.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jvrb.java.bank.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ManageActivity extends AppCompatActivity {

    // Constantes
    private final NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));

    // Variables
    private double balance = 1000;

    // Elementos en pantalla
    TextView messageDNI;
    TextView messageNumber;
    TextView messageBalance;
    EditText inputAmount;
    Button depositAmount;
    Button extractAmount;
    Button closeSession;
    TextView messageInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_layout);

        // Referencias
        messageDNI = findViewById(R.id.msg_dni);
        messageNumber = findViewById(R.id.msg_account_number);
        messageBalance = findViewById(R.id.msg_account_balance);
        inputAmount = findViewById(R.id.ipt_amount);
        depositAmount = findViewById(R.id.btn_deposit);
        extractAmount = findViewById(R.id.btn_extract);
        closeSession = findViewById(R.id.btn_close_sesion);
        messageInfo = findViewById(R.id.msg_info);

        // Inicialización de valores
        messageDNI.setText(getResources().getText(R.string.account_dni));
        messageNumber.setText(getResources().getText(R.string.account_number));
        messageBalance.setText(FORMATTER.format(balance));

        // Eventos
        depositAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateAmount()) {
                    balance = balance + getAmount();
                    setMessageBalance();
                    setMessageInfo(R.string.manage_deposit_success);
                }
            }
        });

        extractAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateAmount()) {
                    if (validateMinimumBalance()) {
                        balance = balance - getAmount();
                        setMessageBalance();
                        setMessageInfo(R.string.manage_extract_success);
                    } else {
                        setMessageInfo(R.string.manage_extract_error);
                    }
                }
            }
        });

        closeSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManageActivity.this, MainActivity.class));
            }
        });
    }

    // Métodos privados
    private double getNumericValue(String textValue) {
        double numericValue = 0;
        try {
            numericValue = Double.parseDouble(textValue);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return numericValue;
    }

    private double getAmount() {
        return getNumericValue(inputAmount.getText().toString());
    }

    private void setMessageBalance() {
        messageBalance.setText(FORMATTER.format(balance));
    }

    private void setMessageInfo(int resource) {
        messageInfo.setText(getResources().getText(resource));
    }

    private boolean validateAmount() {
        return getAmount() > 0;
    }

    private boolean validateMinimumBalance() {
        int minimumBalance = -50;
        return balance - getAmount() >= minimumBalance;
    }
}
