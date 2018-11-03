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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrincipalActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabase, mDatabaseRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        this.mFirebaseAuth = FirebaseAuth.getInstance();
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();

        this.mDatabase = this.mFirebaseDatabase.getReference("clientes");
        this.mDatabaseRoot = this.mFirebaseDatabase.getReference("");

        this.salvarCliente(1,"Alfredo", 31, "M");
        this.salvarCliente(2,"Bianca", 48, "F");
        this.salvarCliente(3,"Fred", 29, "M");
        this.removerCliente(1);

        this.atualizarCliente(2, "Daniela", 26, "F");

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

    private void atualizarCliente(int clienteId, String nome, int idade, String sexo){

        Cliente cliente = new Cliente(nome, idade, sexo);

        Map<String, Object> clienteDados = cliente.toMap();

        Map<String, Object> atualizacoes = new HashMap<>();
        atualizacoes.put("clientes/"+Integer.toString(clienteId), clienteDados);
        atualizacoes.put("versao", "2.0.1");

        this.mDatabaseRoot.updateChildren(atualizacoes);
    }

    private void removerCliente(int clienteId){

        //this.mDatabase.child(Integer.toString(clienteId)).child("idade").removeValue();
        this.mDatabase.child(Integer.toString(clienteId)).removeValue();
    }
}
