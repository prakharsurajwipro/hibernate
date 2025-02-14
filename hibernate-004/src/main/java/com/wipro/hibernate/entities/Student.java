package com.wipro.hibernate.entities;



import jakarta.persistence.*;

@Entity  // Marks this class as a Hibernate entity
@Table(name = "students")  // Maps to "students" table in DB
public class Student {

    @Id // Marks as Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "roll_number", nullable = false, unique = true)
    private int rollNumber;

    @Column(name = "name", nullable = false)
    private String name;

    // Default Constructor 
    public Student() {
    }

    // Parameterized Constructor
    public Student(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    // Getters and Setters
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
