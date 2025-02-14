package com.wipro.hibernate.student;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.wipro.hibernate.entities.Student;
import com.wipro.hibernate.utilities.SessionFactoryProvider;

public class Main {
    public static void main(String[] args) {
        try {
            Session session = SessionFactoryProvider.provideSessionFactory().openSession();
            Transaction tx = session.beginTransaction();

            Student student1 = new Student(109, "bhim singh");
            Student student2 = new Student(110, "Kayne Kohli");

            session.save(student1);
            session.save(student2);
            tx.commit();

            session.close();
            System.out.println("Students inserted successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
