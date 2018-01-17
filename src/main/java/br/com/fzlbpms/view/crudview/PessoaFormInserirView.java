package br.com.fzlbpms.view.crudview;

import java.io.File;

import org.hibernate.Session;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
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
import br.com.fzlbpms.persistence.HibernateUtil;
import br.com.fzlbpms.rfidserver.Main;

public class PessoaFormInserirView extends FormLayout implements View {

	public final static String VIEW_NAME = "entityForm";
	private Pessoa pessoa;

	
	public PessoaFormInserirView(Pessoa p, String formCaption) {
		
		this.pessoa = p;
		Binder<Pessoa> pessBinder = new Binder<>(Pessoa.class);
		pessBinder.setBean(this.pessoa);
		

		this.setCaption(formCaption);		
		
		
		GridLayout gridLayout = new GridLayout(2,5);//2 colunas e 5 linhas
		gridLayout.setWidth("80%");
		gridLayout.setHeight("70%");
		
		
		// Mostra imagem a foto da pessoa
		// TODO: criar uma maneira pro upload da imagem da foto da pessoa
		FileResource resource = new FileResource(new File(Main.basepath + "/WEB-INF/images/defaultAccountImg.jpg"));
		Image image = new Image("Foto", resource);		
		image.setWidth("90%");
		image.setHeight("300px");
		gridLayout.addComponent(image, 0, 0, 0, 3);
		gridLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		
		//TextField Nome
		TextField txtNome = new TextField("Nome");
		txtNome.setWidth("80%");
		txtNome.setIcon(VaadinIcons.USER);		
		txtNome.setRequiredIndicatorVisible(true);
		
		pessBinder.forField(txtNome).bind("nome");		
				
		gridLayout.addComponent(txtNome, 1, 0, 1, 0);
		gridLayout.setComponentAlignment(txtNome, Alignment.TOP_LEFT);

		
		//TextField Identificador
		TextField txtIdentificador = new TextField("Identificador");
		txtIdentificador.setWidth("80%");
		txtIdentificador.setIcon(VaadinIcons.CODE);		
		txtIdentificador.setRequiredIndicatorVisible(true);
		
		pessBinder.forField(txtIdentificador).bind("identificador");
		
		gridLayout.addComponent(txtIdentificador, 1, 1, 1, 1);


		//TextField RG
		TextField txtRg = new TextField("RG");
		txtRg.setWidth("80%");
		txtRg.setIcon(VaadinIcons.CODE);		
		txtRg.setRequiredIndicatorVisible(true);
		
		pessBinder.forField(txtRg).bind("rg");
		
		gridLayout.addComponent(txtRg, 1, 2, 1, 2);
		

		//TextField CPF
		TextField txtCpf = new TextField("CPF");
		txtCpf.setWidth("80%");
		txtCpf.setIcon(VaadinIcons.CODE);		
		txtCpf.setRequiredIndicatorVisible(true);
		
		pessBinder.forField(txtCpf).bind("cpf");
		
		gridLayout.addComponent(txtCpf, 1, 3, 1, 3);
		
		
		
		
		Button btnSalvar = new Button("Salvar");
		btnSalvar.addClickListener((e)->{
			try {
				pessBinder.writeBean(this.pessoa);
				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.save(this.pessoa);
				session.getTransaction().commit();
				new Notification("Operacao Concluida com Sucesso",
					    "Msg de Operac Concluid com sucess",
					    Notification.Type.HUMANIZED_MESSAGE, true)
					    .show(Page.getCurrent());
			} catch (ValidationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

                gridLayout.addComponent(btnSalvar, 0, 4, 1, 4);

//		JsLabel fzlJsLabel = new JsLabel("asdfasdfafds");
//		gridLayout.addComponent(fzlJsLabel);

		this.addComponent(gridLayout);

	}

}
