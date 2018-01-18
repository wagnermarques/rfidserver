package br.com.fzlbpms.view.crudview;


import java.io.File;

import org.hibernate.Session;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewBeforeLeaveEvent;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import br.com.fzlbpms.model.common.Pessoa;
import br.com.fzlbpms.model.sistema.SistemaUsuario;
import br.com.fzlbpms.persistence.GenericHibernateDAOImp;
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.persistence.IGenericDAO;
import br.com.fzlbpms.rfidserver.Main;


public class SistemaUsuarioFormViewForUpdate extends FormLayout implements View {

		public final static String VIEW_NAME = "sistema_usuario_form_update";
		public final static String FORM_CAPTION = "Formulario: Alterar Usuario do Sistema";
		private SistemaUsuario sistUsuario;
		private Binder<SistemaUsuario> pessBinder; 
		private IGenericDAO<SistemaUsuario,Long> pDAO;
		
		public SistemaUsuarioFormViewForUpdate() {
			
			this.sistUsuario = null; //A instancia de usuairo a ser alterada sera setada no evento enter da view			
			this.setCaption(SistemaUsuarioFormViewForUpdate.FORM_CAPTION);		
						
			GridLayout gridLayout = new GridLayout(2,3);//2 colunas e 3 linhas
			gridLayout.setWidth("80%");
			gridLayout.setHeight("70%");
			
			// Mostra imagem a foto da pessoa
			// TODO: criar uma maneira pro upload da imagem da foto da pessoa
			// TODO: maneira de inserir primeiro uma pessoa e depois um usuario correspondente
			FileResource resource = new FileResource(new File(Main.basepath + "/WEB-INF/images/defaultAccountImg.jpg"));			
			Image image = new Image("Foto", resource);		
			image.setWidth("90%");
			image.setHeight("300px");

	        //Primeira coluna,Primeira linha ate a Primeira coluna mesmo e terceira linha
			gridLayout.addComponent(image, 0, 0, 0, 2);
			gridLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
			
			
			
			this.pessBinder = new Binder<>(SistemaUsuario.class);
			
			//TextField para login
			TextField txtLogin = new TextField("Login");
			txtLogin.setWidth("80%");
			txtLogin.setIcon(VaadinIcons.USER);			
			txtLogin.setRequiredIndicatorVisible(true);			
			pessBinder.forField(txtLogin).bind("login");		
					
			gridLayout.addComponent(txtLogin, 1, 0, 1, 0);
			gridLayout.setComponentAlignment(txtLogin, Alignment.TOP_LEFT);

			
			//TextField para Senha
			TextField txtSenha = new TextField("Senha");
			txtSenha.setWidth("80%");
			txtSenha.setIcon(VaadinIcons.CODE);
			
			txtLogin.setRequiredIndicatorVisible(true);
			
			gridLayout.addComponent(txtSenha, 1, 1, 1, 1);

			Button btnSalvar = new Button("Salvar");
			btnSalvar.addClickListener((e)->{
				try {
					pessBinder.writeBean(this.sistUsuario);
					Session session = HibernateUtil.getSessionFactory().openSession();
					this.pDAO.beginTransaction();
					this.pDAO.alterar(this.sistUsuario);
					this.pDAO.commit();
					
					new Notification("Operacao Concluida com Sucesso",
						    "Msg de Operac Concluid com sucess",
						    Notification.Type.HUMANIZED_MESSAGE, true)
						    .show(Page.getCurrent());
				} catch (ValidationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
	                
			gridLayout.addComponent(btnSalvar, 1, 2, 1, 2);
			this.addComponent(gridLayout);

		}

		@Override
		public void enter(ViewChangeEvent event) {
			Long suId = Long.parseLong(event.getParameters());
			Session session = HibernateUtil.getSessionFactory().openSession();
			this.pDAO = new GenericHibernateDAOImp<>(session, SistemaUsuario.class, Long.class);
			this.sistUsuario = this.pDAO.getById(suId);
						
			this.pessBinder.setBean(this.sistUsuario);
			View.super.enter(event);
		}

		@Override
		public void beforeLeave(ViewBeforeLeaveEvent event) {
			this.pDAO.closeSession();
			this.pDAO = null;
			View.super.beforeLeave(event);
		}

	}
