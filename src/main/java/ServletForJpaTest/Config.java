package ServletForJpaTest;

import java.io.IOException;


import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.fzlbpms.model.Pessoa;
import br.com.fzlbpms.model.SistemaUsuario;
import br.com.fzlbpms.model.SistemaUsuarioRole;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.JPAUtil;
import br.com.fzlbpms.persistence.JdbcUtil;

import br.com.fzlbpms.persistence.PessoaJPADAO;

/**
 * Servlet implementation class ServletForJpaTest
 */

@WebServlet("/config")
public class Config extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Config() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

        SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
			
		SistemaUsuario userAdmin = new SistemaUsuario("Admin","admin","admin123");
		userAdmin.setRole("admin");
		SistemaUsuario userTest = new SistemaUsuario("TestUser", "test", "test123");
		userTest.setRole("test");
		
		session.beginTransaction();
		session.save(userAdmin);
		session.save(userTest);
		session.getTransaction().commit();
		session.flush();
		session.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
