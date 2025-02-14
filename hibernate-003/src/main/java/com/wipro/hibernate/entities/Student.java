package com.wipro.hibernate.entities;

public class Student {
    private int rollNumber;
    private String name;

    public Student() {}

    public Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{ rollNumber=" + rollNumber + ", name='" + name + "' }";
    }
}
