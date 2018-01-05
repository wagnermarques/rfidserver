package br.com.fzlbpms.persistence;
 
import java.util.List;


 
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.persistence.HibernateUtil;

 
public class QueryObjectDemo2 {
 
    public static void main(String[] args) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
 
        Session session = factory.getCurrentSession();
 
        try {
 
            // All the action with DB via Hibernate
            // must be located in one transaction
            // Start Transaction.                
            session.getTransaction().begin();
             
            // Create an HQL statement, query the object.
            // HQL with parameters.            
            // Equivalent to the SQL statement:
            // Select e.* from EMPLOYEE e cross join DEPARTMENT d
            // where e.DEPT_ID = d.DEPT_ID and d.DEPT_NO = :deptNo;        
            String sql = "Select e from " + Pessoa.class.getName();  
            // Create query object.
            Query<Pessoa> query = session.createQuery(sql);
 
            query.setParameter("nome", "wagner");
 
  
            // Execute query.
            List<Pessoa> pessoas = query.getResultList();
 
            for (Pessoa p : pessoas) {
                System.out.println("Pessoa: " + p.getNome());
            }
  
            // Commit data
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }
}
