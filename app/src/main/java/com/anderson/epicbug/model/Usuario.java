package com.anderson.epicbug.model;

public class Usuario {
    private String nomeCadastro;
    private String emailCadastro;
    private String senhaCadastro;


    public Usuario(){

    }

    public Usuario(String nomeCadastro, String emailCadastro, String senhaCadastro) {
        this.nomeCadastro = nomeCadastro;
        this.emailCadastro = emailCadastro;
        this.senhaCadastro = senhaCadastro;

    }

    public String getNomeCadastro() {
        return nomeCadastro;
    }

    public void setNomeCadastro(String nomeCadastro) {
        this.nomeCadastro = nomeCadastro;
    }

    public String getEmailCadastro() {
        return emailCadastro;
    }

    public void setEmailCadastro(String emailCadastro) {
        this.emailCadastro = emailCadastro;
    }

    public String getSenhaCadastro() {
        return senhaCadastro;
    }

    public void setSenhaCadastro(String senhaCadastro) {
        this.senhaCadastro = senhaCadastro;
    }



}
