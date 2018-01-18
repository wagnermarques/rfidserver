package br.com.fzlbpms.view.crudview;

import java.util.List;

import java.util.logging.Logger;

import org.hibernate.Session;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.model.sistema.SistemaUsuario;
import br.com.fzlbpms.persistence.GenericHibernateDAOImp;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.IGenericDAO;


public class SistemaUsuarioListView extends VerticalLayout implements View {
	
	Logger logger = Logger.getLogger(SistemaUsuarioListView.class.getCanonicalName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numacessos = 0;
	
	public static String VIEW_NAME="sistema_usuario_list_view";
	
	private List<SistemaUsuario> listSistUsuarios;	
	private IGenericDAO<SistemaUsuario,Long> suDAO;

	private Grid<SistemaUsuario> grid;

	
	public SistemaUsuarioListView() {
		logger.info(" ### public SistemaUsuarioListView() {... ");
		this.grid = new Grid<>();
		this.grid.addColumn(SistemaUsuario::getId).setCaption("id");
		this.grid.addColumn(SistemaUsuario::getLogin).setCaption("Login");
		this.grid.addColumn(SistemaUsuario::getSenha).setCaption("senha");		
		this.grid.setFrozenColumnCount(2);                
		this.addComponent(this.grid);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		logger.info(" ### public void enter(ViewChangeEvent event) {... [numacessos] => "+this.numacessos++);
		
		//Cada vez que a view entra a gente quer controlar o seu estado desde o inicio
		//a gente descarta o estado anterior e inicia o estado dela tudo de novo
		this.listSistUsuarios = null;
		
		if(this.suDAO != null) {
			this.suDAO.clearSession();
			this.suDAO.closeSession();
			this.suDAO = null;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();				
		suDAO = new GenericHibernateDAOImp<>(session, SistemaUsuario.class, Long.class);
		this.listSistUsuarios = this.suDAO.ListarTodos();
		logger.info(this.listSistUsuarios.toString());
		//https://stackoverflow.com/questions/31861375/vaadin-refresh-grid-after-row-modification
		
		//setEditorEnabled(true);
		//this.grid.setEditorEnabled(false);
		this.grid.getDataProvider().refreshAll();
		this.grid.setItems(this.listSistUsuarios);
		View.super.enter(event);
	}
}
