package com.example.jyang.miappandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.browse.MediaBrowser;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PermissionActivity extends AppCompatActivity {

    private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCruz;
    private ImageButton imgBtnContact;
    private ImageButton imgBtnCamara;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        editTextPhone = (EditText) findViewById(R.id.editText);
        editTextWeb = (EditText) findViewById(R.id.editText2);
        imgBtnPhone = (ImageButton) findViewById(R.id.imageView);
        imgBtnWeb = (ImageButton) findViewById(R.id.imageView2);
        imgBtnCruz = (ImageButton) findViewById(R.id.imageView3);
        imgBtnContact = (ImageButton) findViewById(R.id.contact);
        imgBtnCamara = (ImageButton) findViewById(R.id.camara);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.length() >= 2) {
                    // comprobar version actual de android que estamos corriendo
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        // comprobar si ha aceptado, no ha aceptado, o nunca se le ha preguntado ???
                        if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                            // ha aceptado
                            Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                            if (ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(i);
                        } else {
                            // o no ha aceptado, o es la primera vez que le aparece
                            if (!shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                                // no se ha preguntado aun
                                requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                            } else {
                                // si ha denegado
                                Toast.makeText(PermissionActivity.this, "Aceptar la permision de llamada", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                i.addCategory(Intent.CATEGORY_DEFAULT);
                                i.setData(Uri.parse("package:" + getPackageName()));
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                startActivity(i);
                            }
                        }

//                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
                } else {
                    Toast.makeText(PermissionActivity.this, "No hay telefono ingresado valido", Toast.LENGTH_LONG).show();
                }
            }

            private void OlderVersions(String phoneNumber) {

                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                if (CheckPermission(Manifest.permission.CALL_PHONE)) {
                    if (ActivityCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    startActivity(intentCall);
                } else {
                    Toast.makeText(PermissionActivity.this, "No acepto el permiso", Toast.LENGTH_LONG).show();
                }

            }


        });
        imgBtnCruz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(PermissionActivity.this, MainActivity.class);
                startActivity(home);
            }
        });

        imgBtnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editTextWeb.getText().toString();
                if ( url != null && !url.isEmpty() ) {
                    Intent intentWeb = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
//                    Intent intentWeb = new Intent();
//                    intentWeb.setAction(Intent.ACTION_VIEW);
//                    intentWeb.setData(Uri.parse("http://" + url));
                    startActivity(intentWeb);
                }
            }
        });
        imgBtnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContacts = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people"));
                startActivity(intentContacts);
//                String contactID = "1";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(1));
//                intent.setData(uri);
//                startActivity(intent);
            }
        });
        imgBtnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentCamara = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"+"lalal"));
//                startActivity(intentCamara);
                // Mail completo
//                Intent intentCamara = new Intent(Intent.ACTION_VIEW, Uri.parse("mail"));
//                intentCamara.setType("plain/text");
                //verificar esto
//                intentCamara.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
//                intentCamara.putExtra(Intent.EXTRA_SUBJECT, "Titulo");
//                intentCamara.putExtra(Intent.EXTRA_TEXT, "texto del mail");
//                intentCamara.putExtra(Intent.EXTRA_EMAIL, new String[] {"mail1", "mail2"});
//                startActivity(intentCamara);
                // seria
//                intentCamara.setType("message/rfc822");
//                startActivity(Intent.createChooser(intentCamara, "Elige cliente de correo"));

                // Telefono sin necesidad de permisos
//                String phoneNumber = editTextPhone.getText().toString();
//                if (phoneNumber != null && !phoneNumber.isEmpty() && phoneNumber.length() >= 2) {
//                    Intent intentPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
//                    startActivity(intentPhone);
//                }
                Intent intentCamara = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intentCamara);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PHONE_CALL_CODE:

                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals(Manifest.permission.CALL_PHONE)) {
                    // Comprobar si ha sido aceptado o denegado el permiso

                    if (result == PackageManager.PERMISSION_GRANTED) {
                        // ACEPTO
                        String phoneNumber = editTextPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                    } else {
                        // no concedio
                        Toast.makeText(PermissionActivity.this, "No acepto permiso", Toast.LENGTH_LONG).show();
                    }
                }

                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean CheckPermission(String permission) {
        int result = this.checkCallingOrSelfPermission(permission);
        return (result == PackageManager.PERMISSION_GRANTED);
    }
}
