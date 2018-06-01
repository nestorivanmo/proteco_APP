package com.example.alice.appproteco1.Objetos;

public class Titular {

    private String titular;
    private String image;
    private String quote;
    private String curso;

    public Titular(String titular, String image, String quote, String curso) {
        this.titular = titular;
        this.image = image;
        this.quote = quote;
        this.curso = curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String nombre) {
        this.titular = nombre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Titular() {

    }
}
