package edu.upc.eetac.dsa.minimodos.domain;

public class Repository {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Repository(String name) {
        this.name = name;
    }
}
