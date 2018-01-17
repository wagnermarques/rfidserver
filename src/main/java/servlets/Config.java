package servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.com.fzlbpms.model.sistema.SistemaUsuario;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.SistemaUsuarioHibernateDAO;

/**
 * Servlet implementation class ServletForJpaTest
 * Eu utilizo este servlet pra fazer configuracoes em geral...
 * Comentando ou descomentando o codigo... pelo menos por enquanto
 */

@WebServlet("/config")
public class Config extends HttpServlet {

	Logger logger = Logger.getLogger(Config.class.getName());

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Config() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.info(
				"protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {...");
		logger.info(
				"protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {...");

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		//SistemaUsuarioHibernateDAO usuarioDAO = new SistemaUsuarioHibernateDAO(session,);
		SistemaUsuarioHibernateDAO usuarioDAO = new SistemaUsuarioHibernateDAO(session, SistemaUsuario.class, Long.class);
		usuarioDAO.beginTransaction();
		usuarioDAO.excluiTodos();
		usuarioDAO.createDefaultUsers();
		usuarioDAO.commit();		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
