package com.example.tiago.projetofirebase;

import java.util.HashMap;
import java.util.Map;

public class Cliente {

    private String nome;
    private int idade;
    private String sexo;

    public Cliente(){ }

    public Cliente(String nome, int idade, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Map<String, Object> toMap(){

        HashMap<String, Object> result = new HashMap<>();

        result.put("nome", this.getNome());
        result.put("idade", this.getIdade());
        result.put("sexo", this.getSexo());

        return result;
    }
}
