package com.example.abhinav.hanselpatelgson;

/**
 * Created by Abhinav on 12/28/2017.
 */

public class Flowers {
    private int productId;
    private String category,name;
    private float price;

    public Flowers() {
    }

    public Flowers(int productId, String category, String name, float price) {
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("Product Id:"+productId+"\n");
        stringBuilder.append("Category: "+category+"\n");
        stringBuilder.append("Name: "+name+"\n");
        stringBuilder.append("Price: "+price+"\n");

        return stringBuilder.toString();

    }

}
