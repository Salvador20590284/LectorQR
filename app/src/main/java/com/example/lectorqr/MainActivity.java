package com.example.lectorqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    Button btnScan;
    EditText txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScan = findViewById(R.id.btnScan);
        txtResultado = findViewById(R.id.txtResultado);

       btnScan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
               integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
               integrator.setPrompt("Lector - CDP");
               integrator.setCameraId(0);
               integrator.setBeepEnabled(true);
               integrator.setBarcodeImageEnabled(true);
               integrator.initiateScan();


           }
       });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result !=null){
            if(result.getContents()==null){
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                txtResultado.setText(result.getContents());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);

        }

    }
}