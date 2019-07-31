package com.example.lich96tb.tichhopgooglefacebook;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    private SignInButton btnSignIn;
    private GoogleApiClient googleApiClient;
    private TextView txtName,txtMail;
    private ImageView imgAvata;
    private Button btnSigout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = findViewById(R.id.btnSigin);
        txtMail=findViewById(R.id.txtMail);
        txtName=findViewById(R.id.txtName);
        imgAvata=findViewById(R.id.imgAvata);
        btnSigout=findViewById(R.id.btnSignout);
        btnSignIn.setOnClickListener(this);
        btnSigout.setOnClickListener(this);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSigin:
                SignIn();
                break;
            case R.id.btnSignout:
                SignOut();
                break;
                
        }

    }

    private void SignOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(MainActivity.this, ""+status, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SignIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                String name = account.getDisplayName();
                String mail=account.getEmail();
                Uri avata=account.getPhotoUrl();
                txtName.setText(name);
                txtMail.setText(mail);
                Picasso.get().load(avata).into(imgAvata);
            }else
                Toast.makeText(this, "aaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
        }
    }
}
