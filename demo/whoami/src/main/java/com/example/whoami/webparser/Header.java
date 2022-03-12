package com.example.whoami.webparser;

import lombok.*;

public abstract class Header implements Comparable<Header>{

    private String name;
    public String getName(){return name;}

    public int compareTo(Header header){
        return name.compareTo(header.name);
    }

}
