package br.com.fzlbpms.view.crudview;

import java.util.List;

import java.util.logging.Logger;

import org.hibernate.Session;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import br.com.fzlbpms.model.sistema.SistemaUsuario;
import br.com.fzlbpms.persistence.GenericHibernateDAOImp;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.IGenericDAO;


public class SistemaUsuarioListViewForDeletion extends VerticalLayout implements View {

	Logger logger = Logger.getLogger(SistemaUsuarioListViewForDeletion.class.getCanonicalName());

	private List<SistemaUsuario> listSistemaUsuarios;
	private IGenericDAO<SistemaUsuario,Long> pDAO;
	private Grid<SistemaUsuario> grid;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String VIEW_NAME = "SistemaUsuario_list_view_for_deletion";

	public SistemaUsuarioListViewForDeletion() {
		logger.info(" ### public SistemaUsuarioListViewForDeletion() {... ");
	}

	
	
	@Override
	public void enter(ViewChangeEvent event) {
		logger.info(" ### SistemaUsuarioListViewForDeletion -> public void enter(ViewChangeEvent event) {...");

		this.listSistemaUsuarios = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		this.pDAO = new GenericHibernateDAOImp<>(session, SistemaUsuario.class, Long.class);		
		this.listSistemaUsuarios = this.pDAO.ListarTodos();
		
		this.grid = new Grid<>();
		this.grid.setItems(this.listSistemaUsuarios);
		this.grid.addColumn(SistemaUsuario::getId).setCaption("Id");
		this.grid.addColumn(SistemaUsuario::getLogin).setCaption("Login");
			
		this.grid.setFrozenColumnCount(2);
		this.grid.addComponentColumn(this::buildDeleteButton);
		
		this.addComponent(grid);
		View.super.enter(event);
	}

	private Button buildDeleteButton(SistemaUsuario p) {
		Button button = new Button(VaadinIcons.CLOSE);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(e -> exclui(p));
		return button;
	}

	private void exclui(SistemaUsuario p) {
		this.pDAO.beginTransaction();
		this.pDAO.excluir(p);
		this.pDAO.commit();
		this.listSistemaUsuarios = pDAO.ListarTodos();
		this.grid.setItems(this.listSistemaUsuarios);
	}



	@Override
	public void beforeLeave(ViewBeforeLeaveEvent event) {
		if(this.pDAO != null) {
			this.pDAO.closeSession();
			this.pDAO = null;
		}
		View.super.beforeLeave(event);
	}

	
	
}
