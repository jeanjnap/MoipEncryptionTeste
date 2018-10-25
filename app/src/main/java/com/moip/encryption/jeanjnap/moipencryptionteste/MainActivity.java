package com.moip.encryption.jeanjnap.moipencryptionteste;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextInputEditText number, cvc, expM, expY;
    Button encrypt;
    TextView hash, brand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.cardNumber);
        cvc = findViewById(R.id.cvc);
        expM = findViewById(R.id.expMonth);
        expY = findViewById(R.id.expYear);

        encrypt = findViewById(R.id.encrypt);

        hash = findViewById(R.id.hash);
        brand = findViewById(R.id.brand);

        encrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criptografar();
            }
        });
    }

    private void criptografar() {

        String cvcTxt, numberTxt, expMTxt, expYTxt;

        cvcTxt = String.valueOf(cvc.getText());
        numberTxt = String.valueOf(number.getText());
        expMTxt = String.valueOf(expM.getText());
        expYTxt = String.valueOf(expY.getText());
        



        String hashTxt = "";


    }


}
