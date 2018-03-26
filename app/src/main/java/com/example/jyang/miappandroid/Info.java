package com.example.jyang.miappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Info extends AppCompatActivity {

    WebView web1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        web1=(WebView)findViewById(R.id.webV1);
        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("url");
        web1.setWebViewClient(new WebViewClient());
        web1.loadUrl(dato);
    }
    public void salir(View v){
        finish();
    }

    public void otraVista(View view) {
        Intent i = new Intent(this, PermissionActivity.class );
        startActivity(i);
    }
}
