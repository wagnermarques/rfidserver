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

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.persistence.GenericHibernateDAOImp;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.IGenericDAO;


public class PessoaListViewForDeletion extends VerticalLayout implements View {

	Logger logger = Logger.getLogger(PessoaListViewForDeletion.class.getCanonicalName());

	private List<Pessoa> listPessoas;
	private IGenericDAO<Pessoa,Long> pDAO;
	private Grid<Pessoa> grid;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String VIEW_NAME = "pessoa_list_view_for_deletion";

	public PessoaListViewForDeletion() {
		logger.info(" ### public PessoaListViewForDeletion() {... ");
	}

	
	
	@Override
	public void enter(ViewChangeEvent event) {
		logger.info(" ### PessoaListViewForDeletion -> public void enter(ViewChangeEvent event) {...");

		this.listPessoas = null;
		Session session = HibernateUtil.getSessionFactory().openSession();		
		this.pDAO = new GenericHibernateDAOImp<>(session, Pessoa.class, Long.class);		
		this.listPessoas = this.pDAO.ListarTodos();
		
		this.grid = new Grid<>();
		this.grid.setItems(this.listPessoas);
		this.grid.addColumn(Pessoa::getNome).setCaption("Nome");
		this.grid.addColumn(Pessoa::getId).setCaption("Identificador");
		this.grid.addColumn(Pessoa::getRg).setCaption("RG");
		this.grid.addColumn(Pessoa::getCpf).setCaption("CPF");

		this.grid.setFrozenColumnCount(2);
		this.grid.addComponentColumn(this::buildDeleteButton);
		
		this.addComponent(grid);
		View.super.enter(event);
	}

	private Button buildDeleteButton(Pessoa p) {
		Button button = new Button(VaadinIcons.CLOSE);
		button.addStyleName(ValoTheme.BUTTON_SMALL);
		button.addClickListener(e -> exclui(p));
		return button;
	}

	private void exclui(Pessoa p) {
		this.pDAO.beginTransaction();
		this.pDAO.excluir(p);
		this.pDAO.commit();
		this.listPessoas = pDAO.ListarTodos();
		this.grid.setItems(this.listPessoas);
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
