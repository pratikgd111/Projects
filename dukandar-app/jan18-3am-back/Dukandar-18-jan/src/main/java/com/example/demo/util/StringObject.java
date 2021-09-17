package com.example.demo.util;

public class StringObject {
    private String string;

    public StringObject(String string){
        this.string=string;
    }

    public StringObject(){  }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "StringObject{" +
                "string='" + string + '\'' +
                '}';
    }
}
