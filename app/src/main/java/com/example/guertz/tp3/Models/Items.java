package com.example.guertz.tp3.Models;

/**
 * Created by Guijet on 2017-11-16.
 */

public class Items {

    private String textValue;
    private Integer id;

    public Items(String textValue,Integer id){
        setId(id);
        setTextValue(textValue);
    }

    public Integer getId() {
        return id;
    }

    public String getTextValue() {
        return textValue;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTextValue(String textValue) {
        this.textValue = textValue;
    }

    @Override
    public String toString(){
        return textValue;
    }
}
