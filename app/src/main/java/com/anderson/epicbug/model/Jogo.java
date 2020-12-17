package com.anderson.epicbug.model;

//classe jogo
public class Jogo {
    private String id;
    private String uriImagem;
    private String nomeJogo;
    private Double precoJogo;
    private Float estrelas;

    public Jogo(String s, String toString, String nome, double parseDouble, double parseDouble1) {
    }

    public Float getEstrelas() {
        return estrelas;
    }

    public void setEstrelas(Float estrelas) {
        this.estrelas = estrelas;
    }

    //constructor vazio
    public Jogo(){
    }



    //Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;

    }

    public String getUriImagem(){
        return uriImagem;
    }

    public void setUriImagem(String uriImagem){
        this.uriImagem = uriImagem;
    }

    public String getNomeJogo(){
        return nomeJogo;
    }

    public void setNomeJogo(String nomeJogo){
        this.nomeJogo = nomeJogo;
    }

   public Double getPrecoJogo(){
        return precoJogo;
   }

    public void setPrecoJogo(Double precoJogo){
        this.precoJogo = precoJogo;
    }

    public Jogo(String id, String uriImagem, String nomeJogo, Double precoJogo,float estrelas){
        this.id = id;
        this.uriImagem = uriImagem;
        this.nomeJogo = nomeJogo;
        this.precoJogo = precoJogo;
        this.estrelas = estrelas;
    }
}
