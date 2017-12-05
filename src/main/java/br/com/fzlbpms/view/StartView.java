package br.com.fzlbpms.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class StartView extends VerticalLayout implements View {

	public StartView() {
		setSizeFull();

		Button button = new Button("Go to Main View", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				//navigator.navigateTo(MAINVIEW);
				new Notification("This is a warning",
					    "This is the <i>last</i> warning",
					    Notification.Type.WARNING_MESSAGE, true)
					    .show(Page.getCurrent());
			}

		});
		addComponent(button);
		setComponentAlignment(button, Alignment.MIDDLE_CENTER);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Animal Farm");
	}
}