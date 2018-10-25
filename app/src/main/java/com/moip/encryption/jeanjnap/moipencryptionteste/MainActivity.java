package com.moip.encryption.jeanjnap.moipencryptionteste;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
<<<<<<< HEAD

import com.moip.encryption.entities.CreditCard;
import com.moip.encryption.exception.MoipEncryptionException;
=======
>>>>>>> parent of 61cd198... Moip Implementation

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
        
<<<<<<< HEAD
        CreditCard creditCard = new CreditCard();
        creditCard.setCvc(cvcTxt);
        creditCard.setNumber(numberTxt);
        creditCard.setExpirationMonth(expMTxt);
        creditCard.setExpirationYear(expYTxt);
        creditCard.setPublicKey(PUBLIC_KEY);

        String hashTxt = "";

        try{
            hashTxt = creditCard.encrypt();
            Log.i("RESULT", "Hash: \n"+ hashTxt);
            hash.setText(hashTxt);
        }catch(MoipEncryptionException mee){
            Log.i("RESULT", "Erro ao criptografar cartão:\n" + mee.getMessage());

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            //define o titulo
            builder.setTitle("Erro");
            //define a mensagem
            builder.setMessage(mee.getMessage());
            //define um botão como positivo
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            builder.show();

        }
=======
>>>>>>> parent of 61cd198... Moip Implementation
    }


}
