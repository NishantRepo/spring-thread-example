package com.nishant;

public final class ImmutableClass {

    private final String name;

    private final int id;

    public ImmutableClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

}
