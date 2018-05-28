package com.example.alice.appproteco1.Objetos;

public class Noticia {

    private String tituloNoticia;
    private String imagenNoticia;
    private String cuerpoNoticia;

    public Noticia(String tituloNoticia, String imagenNoticia, String cuerpoNoticia) {
        this.tituloNoticia = tituloNoticia;
        this.imagenNoticia = imagenNoticia;
        this.cuerpoNoticia = cuerpoNoticia;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getImagenNoticia() {
        return imagenNoticia;
    }

    public void setImagenNoticia(String imagenNoticia) {
        this.imagenNoticia = imagenNoticia;
    }

    public String getCuerpoNoticia() {
        return cuerpoNoticia;
    }

    public void setCuerpoNoticia(String cuerpoNoticia) {
        this.cuerpoNoticia = cuerpoNoticia;
    }
}
