package org.jvrb.java.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.jvrb.java.bank.R;

public class LoginActivity extends AppCompatActivity {

    // Elementos en pantalla
    TextView messageInfo;
    EditText inputDNI;
    EditText inputPassword;
    Button enterValues;
    Button returnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        // Referencias
        messageInfo = findViewById(R.id.msg_H2);
        inputDNI = findViewById(R.id.ipt_dni);
        inputPassword = findViewById(R.id.ipt_password);
        enterValues = findViewById(R.id.btn_enter);
        returnMain = findViewById(R.id.btn_exit);

        // Eventos
        enterValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateDNI() && validatePassword()) {
                    startActivity(new Intent(LoginActivity.this, ManageActivity.class));
                } else {
                    setMessageInfo(R.string.login_error);
                }
            }
        });

        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    // Métodos privados
    private boolean validateDNI() {
        return inputDNI.getText().toString().equals(getResources().getString(R.string.account_dni));
    }

    private boolean validatePassword() {
        return inputPassword.getText().toString().equals(getResources().getString(R.string.account_password));
    }

    private void setMessageInfo(int resource) {
        messageInfo.setText(getResources().getText(resource));
    }
}