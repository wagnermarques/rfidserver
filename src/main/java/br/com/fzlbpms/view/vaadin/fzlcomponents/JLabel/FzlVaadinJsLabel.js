/**
 * https://vaadin.com/blog/vaadin-7-loves-javascript-components
 * https://vaadin.com/download/prerelease/7.0/7.0.0/7.0.0.alpha3/docs/api/com/vaadin/ui/AbstractJavaScriptComponent.html
 */

/**
 * There's a naming convention we need to follow. This helps Vaadin find the
 * correct JavaScript function to call when our component is attached. We take
 * the fully qualified class name of the server-side component, and just replace
 * the dots with underscores.
 * 
 *
 * We take the fully qualified class name 
 */

br_com_fzlbpms_view_vaadin_fzlcomponents_JLabel_JsLabel = function() {

	// this.getElement() gives the DOM element that was created for this widget
	// by Vaadin
	var el = this.getElement();
	
	/**
	 * In our case, we just read the XHTML string from the state, 
	 *    and set the DOM element's innerHTML as whatever we got over the wire. 
	 * Unfortunately, a component's state can't be modified on the client side, 
	 *    to be updated on the server side.
	 */
	this.onStateChange = function() {
		el.innerHTML = this.getState().xhtml; 
	}
}

br_com_fzlbpms_view_vaadin_fzlcomponents_JLabel_JsLabel.tag = "span";
