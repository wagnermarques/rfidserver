package br.com.fzlbpms.view.vaadin.fzlcomponents.JLabel;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;


//https://vaadin.com/blog/vaadin-7-loves-javascript-components


@JavaScript({ "FzlVaadinJsLabel.js" })
public class JsLabel extends AbstractJavaScriptComponent {

  public JsLabel(String xhtml) {
    getState().xhtml = xhtml;
  }

  @Override
  protected JsLabelState getState() {
    return (JsLabelState) super.getState();
  }
}
