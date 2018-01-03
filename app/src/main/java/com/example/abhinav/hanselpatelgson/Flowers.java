package com.example.abhinav.hanselpatelgson;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Abhinav on 12/28/2017.
 */

public class Flowers implements Parcelable {
    private int productId;
    private String category,name,instructions,photo;
    private float price;


    public Flowers() {
    }



    public Flowers(int productId, String category, String name, float price, String instructions, String photo) {
        this.productId = productId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.instructions=instructions;
        this.photo=photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {

        this.instructions = instructions;
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
        stringBuilder.append("Instructions: "+instructions+"\n");
        stringBuilder.append("photo"+photo);
        return stringBuilder.toString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
