package com.example.tiago.projetofirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity{

    private FirebaseAuth mFirebaseAuth;
    EditText editTextUser, editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mFirebaseAuth = FirebaseAuth.getInstance();

        this.editTextUser  = (EditText) findViewById(R.id.editTextEmail);
        this.editTextSenha = (EditText) findViewById(R.id.editTextSenha);

        findViewById(R.id.btnLoginSenha).setOnClickListener(btnLoginSenhaOnClickListener);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = this.mFirebaseAuth.getCurrentUser();
    }

    private View.OnClickListener btnLoginSenhaOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String user = editTextUser.getText().toString();
            String senha = editTextSenha.getText().toString();

            mFirebaseAuth.signInWithEmailAndPassword(user, senha)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                         if( task.isSuccessful() ){

                             startActivity(new Intent(MainActivity.this, PrincipalActivity.class));
                             finish();
                         }else{
                             Toast.makeText(MainActivity.this,task.getException().toString(), Toast.LENGTH_LONG).show();
                         }
                        }
                    });

        }
    };
}
