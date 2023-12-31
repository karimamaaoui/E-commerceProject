package com.example.ecommerceproject.models;

public class Product {
    private String id;
    private String price,name,description,category;
    private String imageUrl;


    public Product(String id,String name, String price,String description,String category,String imageUrl) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.description=description;
        this.category=category;
        this.imageUrl=imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
