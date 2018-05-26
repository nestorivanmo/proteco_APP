package com.example.alice.appproteco1;

public class Blog {

    private String title;
    private String desc;
    private String image;
    private String desc_larga;

    public Blog(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public String getDesc_larga() {
        return desc_larga;
    }

    public void setDesc_larga(String desc_larga) {
        this.desc_larga = desc_larga;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Blog(){

    }
}
