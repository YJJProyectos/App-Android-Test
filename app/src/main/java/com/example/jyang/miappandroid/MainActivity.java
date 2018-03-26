package com.example.jyang.miappandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperActivityToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;

public class MainActivity extends AppCompatActivity {


    private EditText num1;
    private EditText num2;
    private EditText txtURL;
    private TextView resultado;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1=(EditText)findViewById(R.id.num1);
        num2=(EditText)findViewById(R.id.num2);
        txtURL=(EditText)findViewById(R.id.txtBuscar);
        resultado=(TextView)findViewById(R.id.resultado);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        txtURL.setText(prefe.getString("url",""));
//        Activity activity = this;
//        Context context = this;
//        Test.myToast(context);
        Toast.makeText(this, "CREATE" , Toast.LENGTH_LONG).show();
        SuperActivityToast.create(this, new Style(), Style.TYPE_BUTTON)
                .setButtonText("Cerrar")
                .setButtonIconResource(R.drawable.selector_button_kitkat)
                .setOnButtonClickListener("good_tag_name", null, null)
                .setProgressBarColor(Color.WHITE)
                .setText("Pantalla inicial")
                .setDuration(Style.DURATION_LONG)
                .setFrame(Style.FRAME_LOLLIPOP)
                .setColor(PaletteUtils.getSolidColor(PaletteUtils.MATERIAL_BLUE_GREY))
                .setAnimations(Style.ANIMATIONS_POP).show();

    }

    //Este método se ejecutará cuando se presione el botón
    public void calcular(View view) {
        String valor1=num1.getText().toString();
        String valor2=num2.getText().toString();
        resultado.setText(valor1 + "nada" + valor2);
        float res = 0;
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            resultado.setText("Seleccione una accion");
        }
        else if (!valor1.isEmpty() && !valor2.isEmpty()){
            float nro1=Integer.parseInt(valor1);
            float nro2=Integer.parseInt(valor2);
            int selectedId = radioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = (RadioButton)findViewById(selectedId);
            String seleccionado = selectedRadioButton.getText().toString();
            switch (seleccionado){
                case "Suma" : res = nro1 + nro2; break;
                case "Resta" : res = nro1 - nro2; break;
                case "Dividir": res = nro1 / nro2; break;
                case "Multiplicar": res = nro1 * nro2; break;
            }

            resultado.setText("Resultado: " + res);
            Toast notificacion= Toast.makeText(this, Float.toString(res),Toast.LENGTH_LONG);
            notificacion.show();
        } else {
            resultado.setText("Complete los campos de numeros");
        }

    }

    public void otraVista(View view) {
        String texto = txtURL.getText().toString();
        Intent i = new Intent(this, Info.class );
        i.putExtra("url","http://" + texto);
        startActivity(i);
    }

    public void ejecutar(View v) {
        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("url", txtURL.getText().toString());
        editor.commit();
        Toast.makeText(this, "URL guardada", Toast.LENGTH_LONG).show();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(this, "ON START" , Toast.LENGTH_LONG).show();
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(this, "ON RESUME" , Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(this, "ON PAUSE" , Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(this, "ON STOP" , Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(this, "ON RESTART" , Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this, "ON DESTROY" , Toast.LENGTH_LONG).show();
//
//    }

}
