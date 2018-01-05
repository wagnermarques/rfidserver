package br.com.fzlbpms.persistence;
 
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.persistence.HibernateUtil;
 

public class QueryObjectDemo {
 
   public static void main(String[] args) {
       SessionFactory factory = HibernateUtil.getSessionFactory();
 
       Session session = factory.getCurrentSession();
 
       try {
            
           // All the action with DB via Hibernate
           // must be located in one transaction.
           // Start Transaction.            
           session.getTransaction().begin();
            
           // Create an HQL statement, query the object.
           // Equivalent to the SQL statement:
           // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
           String sql = "Select e from " + Pessoa.class.getName();
 
   
           // Create Query object.
           Query<Pessoa> query = session.createQuery(sql);
 
    
           // Execute query.
           List<Pessoa> pessoas = query.getResultList();
 
           for (Pessoa p : pessoas) {
               System.out.println("Pessoa: " + p.getNome());
           }
  
           // Commit data.
           session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           // Rollback in case of an error occurred.
           session.getTransaction().rollback();
       }
   }
    
}
