package com.moviecatalog.moviecatalogservice.models;

public class CatalogItem {
    private String name;
    private String description ;
    private int reting;

    public CatalogItem(String name, String description, int reting) {
        this.name = name;
        this.description = description;
        this.reting = reting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReting() {
        return reting;
    }

    public void setReting(int reting) {
        this.reting = reting;
    }
}
