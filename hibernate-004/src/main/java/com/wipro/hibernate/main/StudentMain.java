package com.wipro.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.wipro.hibernate.entities.Student;
import java.io.IOException;
import java.util.Properties;

public class StudentMain {
    public static void main(String[] args) {
        // Load properties from application.properties
        Properties properties = new Properties();
        try {
            properties.load(StudentMain.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            System.out.println("Failed to load application.properties: " + e.getMessage());
            return;
        }

        // Create Hibernate configuration
        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(Student.class);

        // Build SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open Hibernate session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert students
        Student student1 = new Student();
        student1.setName("Prakhar Suraj");

        Student student2 = new Student();
        student2.setName("Vinay");

        Student student3 = new Student();
        student3.setName("Meera");

        // Save students to database
        session.save(student1);
        session.save(student2);
        session.save(student3);

        // Commit transaction
        transaction.commit();

        System.out.println("Students Inserted Successfully: ");
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

        // Close session
        session.close();
        sessionFactory.close();
    }
}
