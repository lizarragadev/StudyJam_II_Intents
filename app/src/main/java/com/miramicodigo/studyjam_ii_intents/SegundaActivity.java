package com.miramicodigo.studyjam_ii_intents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SegundaActivity extends AppCompatActivity {

    private TextView tvValor1;
    private TextView tvValor2;
    private TextView tvValor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        tvValor1 = (TextView) findViewById(R.id.tvValor1);
        tvValor2 = (TextView) findViewById(R.id.tvValor2);
        tvValor3 = (TextView) findViewById(R.id.tvValor3);

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            String valor1 = getIntent().getStringExtra("valor1");
            String valor2 = getIntent().getStringExtra("valor2");
            int valor3 = bundle.getInt("valor3", 0);

            tvValor1.setText(valor1);
            tvValor2.setText(valor2);
            tvValor3.setText(valor3 + "");
        }
    }
}
