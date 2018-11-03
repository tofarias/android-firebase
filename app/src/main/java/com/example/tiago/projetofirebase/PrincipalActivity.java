package com.example.tiago.projetofirebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        this.mFirebaseAuth = FirebaseAuth.getInstance();
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        this.mDatabase = this.mFirebaseDatabase.getReference("clientes");

        //this.mDatabase.child("2").child("nome").setValue("Montgomery");

        this.salvarCliente(1,"Tiago", 33, "M");

        this.mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Cliente value = dataSnapshot.getValue(Cliente.class);

                Toast.makeText(PrincipalActivity.this, "Ok", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = this.mFirebaseAuth.getCurrentUser();

        if( currentUser != null ){
            Toast.makeText(this, currentUser.getEmail(), Toast.LENGTH_LONG).show();

            Log.i("FirebaseUser P",currentUser.getDisplayName());
            Log.i("FirebaseUser P",currentUser.getEmail());
        }else{
            Toast.makeText(this, "NAO AUTENTICADO", Toast.LENGTH_LONG).show();
        }
    }

    private void salvarCliente(int clienteId, String nome, int idade, String sexo){

        Cliente cliente = new Cliente(nome, idade, sexo);

        this.mDatabase.child( Integer.toString(clienteId) ).setValue(cliente);
    }
}
