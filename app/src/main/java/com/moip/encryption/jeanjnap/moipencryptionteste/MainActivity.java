package com.moip.encryption.jeanjnap.moipencryptionteste;

import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.spongycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

import br.com.moip.encryption.entities.CreditCard;
import br.com.moip.encryption.entities.types.CreditCardBrand;
import br.com.moip.encryption.exception.MoipEncryptionException;
import br.com.moip.encryption.helper.MoipValidator;


public class MainActivity extends AppCompatActivity {

    TextInputEditText number, cvc, expM, expY;
    Button encrypt;
    TextView hash, brand;

    private static final String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAopPr9r5KIWy0kw8jDnJN\n" +
            "uhY4mRRCEiplG+oMjTIdsML2k1etApYccuwK/qbgToxPMuAXwX3i3EkATFkWCwCG\n" +
            "QxQvfwBIzaWCDA6qE0dMBHOu4jDmuaQWa5MnnQhFoPkjGC+axmNFzMxzIm6xWI1c\n" +
            "mtsmt9dO7VOZbkcwa9s2K9d7fjQg351zcU51KiW5RHKl1jmjkjvOWmu+UmTMRthB\n" +
            "MdJv5n+1O4JxO5L2S0IrhKKV2H0uPyFRkpMrCV5gfFWhNV17FKPM3ZdH+I1ZIEvj\n" +
            "IugRtZYuaqDvulQHFxwMqsAC9fHrwq8AgRgHgmHly9QGXPOyzuvJJGbjGMoI0H2Z\n" +
            "ZwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";

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
        getBrand();
    }

    private void getBrand() {
        String numero = String.valueOf(number.getText());


        CreditCardBrand brandObj = MoipValidator.quicklyBrand(numero);
        String brandStr = brandObj.getDescription();

        brand.setText(brandStr);

    }

    private void criptografar() {
        getBrand();

        String cvcTxt, numberTxt, expMTxt, expYTxt;

        cvcTxt = String.valueOf(cvc.getText());
        numberTxt = String.valueOf(number.getText());
        expMTxt = String.valueOf(expM.getText());
        expYTxt = String.valueOf(expY.getText());

        String hashTxt = "";

        Security.addProvider(new BouncyCastleProvider());

        CreditCard creditCard = new CreditCard();
        creditCard.setCvc(cvcTxt);
        creditCard.setNumber(numberTxt);
        creditCard.setExpirationMonth(expMTxt);
        creditCard.setExpirationYear(expYTxt);
        creditCard.setPublicKey(PUBLIC_KEY);

        number.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String numero = String.valueOf(number.getText());

                if(numero.length() >= 4) {
                    getBrand();
                }
            }
        });

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

    }


}
