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
import br.com.fzlbpms.persistence.GenericHibernateDAOImp;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.IGenericDAO;


public class PessoaListView extends VerticalLayout implements View {
	
	Logger logger = Logger.getLogger(PessoaListView.class.getCanonicalName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numacessos = 0;
	
	public static String VIEW_NAME="pessoa_list_view";
	
	private List<Pessoa> listPessoas;	
	private IGenericDAO<Pessoa,Long> pessoaDAO;

	private Grid<Pessoa> grid;

	
	public PessoaListView() {
		logger.info(" ### public PessoaListView() {... ");
		this.grid = new Grid<>();
		this.grid.addColumn(Pessoa::getNome).setCaption("Nome");
		this.grid.addColumn(Pessoa::getId).setCaption("Identificador");
		this.grid.addColumn(Pessoa::getRg).setCaption("RG");
		this.grid.addColumn(Pessoa::getCpf).setCaption("CPF");
		this.grid.setFrozenColumnCount(2);                
		this.addComponent(this.grid);
	}


	@Override
	public void enter(ViewChangeEvent event) {
		logger.info(" ### public void enter(ViewChangeEvent event) {... [numacessos] => "+this.numacessos++);
		
		//Cada vez que a view entra a gente quer controlar o seu estado desde o inicio
		//a gente descarta o estado anterior e inicia o estado dela tudo de novo
		this.listPessoas = null;
		
		if(this.pessoaDAO != null) {
			this.pessoaDAO.clearSession();
			this.pessoaDAO.closeSession();
			this.pessoaDAO = null;
		}
		
		Session session = HibernateUtil.getSessionFactory().openSession();		
		//this.pessoaDAO = new GenericHibernateDAOImp<Pessoa>(session,Pessoa.class, Long.class);
		pessoaDAO = new GenericHibernateDAOImp<>(session, Pessoa.class, Long.class);
		this.listPessoas = this.pessoaDAO.ListarTodos();

		logger.info(" ### public void enter(ViewChangeEvent event) {... [this.listPessoas.size()] => "+this.listPessoas.size());
		//https://stackoverflow.com/questions/31861375/vaadin-refresh-grid-after-row-modification
		
		//setEditorEnabled(true);
		//this.grid.setEditorEnabled(false);
		this.grid.getDataProvider().refreshAll();
		this.grid.setItems(this.listPessoas);
		View.super.enter(event);
	}
}
