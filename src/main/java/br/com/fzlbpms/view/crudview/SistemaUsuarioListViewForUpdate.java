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
import br.com.fzlbpms.rfidserver.Main;


public class SistemaUsuarioListViewForUpdate extends VerticalLayout implements View {

	Logger logger = Logger.getLogger(SistemaUsuarioListViewForUpdate.class.getCanonicalName());

	private List<SistemaUsuario> listSistemaUsuarios;
	private IGenericDAO<SistemaUsuario,Long> pDAO;
	private Grid<SistemaUsuario> grid;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String VIEW_NAME = "SistemaUsuario_list_view_for_Update";

	public SistemaUsuarioListViewForUpdate() {
		logger.info(" ### public SistemaUsuarioListViewForUpdate() {... ");
	}

	
	
	@Override
	public void enter(ViewChangeEvent event) {
		logger.info(" ### SistemaUsuarioListViewForUpdate -> public void enter(ViewChangeEvent event) {...");

		this.listSistemaUsuarios = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		this.pDAO = new GenericHibernateDAOImp<>(session, SistemaUsuario.class, Long.class);		
		this.listSistemaUsuarios = this.pDAO.ListarTodos();
		
		this.grid = new Grid<>();
		this.grid.setItems(this.listSistemaUsuarios);
		this.grid.addColumn(SistemaUsuario::getId).setCaption("Id");
		this.grid.addColumn(SistemaUsuario::getLogin).setCaption("Login");
			
		this.grid.setFrozenColumnCount(2);
		this.grid.addComponentColumn(this::buildUpdateButton);
		
		this.addComponent(grid);
		View.super.enter(event);
	}

	private Button buildUpdateButton(SistemaUsuario su) {
		Button button = new Button(VaadinIcons.EDIT);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(e -> Main.navigator.navigateTo(SistemaUsuarioFormViewForUpdate.VIEW_NAME+"/"+su.getId()));
		return button;
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
