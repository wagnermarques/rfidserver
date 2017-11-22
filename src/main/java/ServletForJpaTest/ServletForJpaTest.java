package ServletForJpaTest;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import br.com.fzlbpms.model.Pessoa;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.JPAUtil;
import br.com.fzlbpms.persistence.JdbcUtil;

import br.com.fzlbpms.persistence.PessoaJPADAO;

/**
 * Servlet implementation class ServletForJpaTest
 */

@WebServlet("/ServletForJpaTest")
public class ServletForJpaTest extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletForJpaTest() {
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
		//EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();
                //EntityManager em = JPAUtil.getEntityManager();
		//java.sql.Connection conn  = JdbcUtil.getConnection();
                
		//PessoaJPADAO pessoaJPADAO = new PessoaJPADAO(JPAUtil.getEntityManager());
		Pessoa p = new Pessoa();
		//p.setNome("PessTeste");
		//pessoaJPADAO.inserir(p);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
