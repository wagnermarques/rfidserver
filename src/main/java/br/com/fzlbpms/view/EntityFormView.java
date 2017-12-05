package br.com.fzlbpms.view;

import java.io.File;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import br.com.fzlbpms.model.Pessoa;
import br.com.fzlbpms.view.vaadin.fzlcomponents.JLabel.JsLabel;
//import br.com.fzlbpms.model.*;

public class EntityFormView extends FormLayout implements View{

	public final static String VIEW_NAME = "entityForm";
	
	public EntityFormView(Pessoa p,String formCaption) {

		this.setCaption(formCaption);
		GridLayout gridLayout = new GridLayout(4, 4);

		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();

		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath + "/WEB-INF/images/defaultAccountImg.jpg"));

		// Show the image in the application
		Image image = new Image("Image from file", resource);

		image.setWidth("200px");
		image.setHeight("180px");

		gridLayout.addComponent(image, 0, 0, 0, 1);
		gridLayout.setComponentAlignment(image, Alignment.TOP_LEFT);

		TextField textField = new TextField("Nome");
		textField.setRequiredIndicatorVisible(true);
		textField.setIcon(VaadinIcons.USER);
		gridLayout.addComponent(textField, 1, 0, 2, 0);
		gridLayout.setComponentAlignment(textField, Alignment.TOP_LEFT);

		TextField matricula = new TextField("Matrícula");
		matricula.setIcon(VaadinIcons.CODE);
		gridLayout.addComponent(matricula, 1, 1, 2, 1);

		Button salvar = new Button("Salvar");
		gridLayout.addComponent(salvar, 1, 3, 1, 3);
		
		JsLabel fzlJsLabel = new JsLabel("asdfasdfafds");
		gridLayout.addComponent(fzlJsLabel);
		

		this.addComponent(gridLayout);

	}

	
	
}
