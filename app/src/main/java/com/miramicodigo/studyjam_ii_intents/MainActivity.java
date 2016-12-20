package com.miramicodigo.studyjam_ii_intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSegundaActivity;
    private Button btnEnvioDatos;
    private Button btnIrPaginaWeb;
    private Button btnIrGoogleMaps;
    private Button btnIrMarcadoTelefonico;
    private Button btnLlamar;
    private Button btnCompartirTexto;
    private Button btnCompartirImagen;

    private Intent intent;

    /**
     * @author Gustavo Lizarraga
     * @version 1.0
     * @date 20/12/2016
     * #DevStudyJam
     * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        initOnclick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSegundaActivity:
                intent = new Intent(getApplicationContext(), SegundaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnEnvioDatos:
                intent = new Intent(getApplicationContext(), SegundaActivity.class);
                intent.putExtra("valor1", "Hola");
                intent.putExtra("valor2", "Android");
                intent.putExtra("valor3", 15);
                startActivity(intent);
                break;
            case R.id.btnIrPaginaWeb:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.miramicodigo.com"));
                startActivity(intent);
                break;
            case R.id.btnIrGoogleMaps:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:-16.503964, -68.129894"));
                startActivity(intent);
                break;
            case R.id.btnIrMarcadoTelefonico:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:77752810"));
                startActivity(intent);
                break;
            case R.id.btnLlamar:
                llamar();
                break;
            case R.id.btnCompartirTexto:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                //intent.setPackage("com.facebook.katana");
                //intent.setPackage("com.whatsapp");
                intent.putExtra(Intent.EXTRA_TEXT, "Feliz Navidad");
                startActivity(intent);
                break;
            case R.id.btnCompartirImagen:
                Uri uri = Uri.parse("android.resource://com.miramicodigo.studyjam_ii_intents/"+R.drawable.imagen);
                intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/jpeg");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(intent, "Enviar como regalo"));
                break;
        }
    }

    public void initUI() {
        btnSegundaActivity = (Button) findViewById(R.id.btnSegundaActivity);
        btnEnvioDatos = (Button) findViewById(R.id.btnEnvioDatos);
        btnIrPaginaWeb = (Button) findViewById(R.id.btnIrPaginaWeb);
        btnIrGoogleMaps = (Button) findViewById(R.id.btnIrGoogleMaps);
        btnIrMarcadoTelefonico = (Button) findViewById(R.id.btnIrMarcadoTelefonico);
        btnLlamar = (Button) findViewById(R.id.btnLlamar);
        btnCompartirTexto = (Button) findViewById(R.id.btnCompartirTexto);
        btnCompartirImagen = (Button) findViewById(R.id.btnCompartirImagen);
    }

    public void initOnclick() {
        btnSegundaActivity.setOnClickListener(this);
        btnEnvioDatos.setOnClickListener(this);
        btnIrPaginaWeb.setOnClickListener(this);
        btnIrGoogleMaps.setOnClickListener(this);
        btnIrMarcadoTelefonico.setOnClickListener(this);
        btnLlamar.setOnClickListener(this);
        btnCompartirTexto.setOnClickListener(this);
        btnCompartirImagen.setOnClickListener(this);
    }

    public void llamar() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
        } else {
            intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:77752810"));
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    llamar();
                } else {
                    Log.d("TAG", "Permiso de llamada no concedido");
                }
                break;
            default:
                break;
        }
    }
}
