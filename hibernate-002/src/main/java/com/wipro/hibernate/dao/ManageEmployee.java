package com.wipro.hibernate.dao;  // ✅ Ensure the correct package is used

import com.wipro.hibernate.entities.Employee;  // ✅ Correct import
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ManageEmployee {
   private static SessionFactory factory; 

   public static void main(String[] args) {
      try {
         factory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object: " + ex);
         throw new ExceptionInInitializerError(ex); 
      }
      
      ManageEmployee ME = new ManageEmployee();

      /* Add few employee records in the database */
      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
      Integer empID3 = ME.addEmployee("John", "Paul", 10000);

      /* List down all the employees */
      ME.listEmployees();

      /* Update employee's records */
      ME.updateEmployee(empID1, 5000);

      /* Delete an employee from the database */
      ME.deleteEmployee(empID2);

      /* List down new list of the employees */
      ME.listEmployees();
   }

   /* Method to CREATE an employee in the database */
   public Integer addEmployee(String fname, String lname, int salary){
      Session session = factory.openSession();
      Transaction tx = null;
      Integer employeeID = null;

      try {
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary);  // ✅ Corrected entity class reference
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
      return employeeID;
   }

   /* Method to READ all the employees */
   public void listEmployees() {
      Session session = factory.openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list(); // ✅ Corrected query
         for (Employee employee : employees) {
            System.out.print("First Name: " + employee.getFirstName()); 
            System.out.print("  Last Name: " + employee.getLastName()); 
            System.out.println("  Salary: " + employee.getSalary()); 
         }
         tx.commit();
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }

   /* Method to UPDATE salary for an employee */
   public void updateEmployee(Integer EmployeeID, int salary) {
      Session session = factory.openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         Employee employee = session.get(Employee.class, EmployeeID); // ✅ Corrected entity class reference
         if (employee != null) {
            employee.setSalary(salary);
            session.update(employee); 
            tx.commit();
         } else {
            System.out.println("Employee with ID " + EmployeeID + " not found!");
         }
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }

   /* Method to DELETE an employee from the records */
   public void deleteEmployee(Integer EmployeeID) {
      Session session = factory.openSession();
      Transaction tx = null;

      try {
         tx = session.beginTransaction();
         Employee employee = session.get(Employee.class, EmployeeID); // ✅ Corrected entity class reference
         if (employee != null) {
            session.delete(employee); 
            tx.commit();
         } else {
            System.out.println("Employee with ID " + EmployeeID + " not found!");
         }
      } catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } finally {
         session.close(); 
      }
   }
}