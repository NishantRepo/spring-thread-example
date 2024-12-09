package com.nishant.generics;

public class StudentGenerics<T> {

    private T name;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentGenerics{" +
                "name=" + name +
                '}';
    }
}
