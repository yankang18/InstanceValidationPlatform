package umbc.ebiquity.kang.ontologypopulating.client.view;

import umbc.ebiquity.kang.ontologypopulating.client.datasource.WebSiteHomeUrlDataSource;
import umbc.ebiquity.kang.ontologypopulating.client.event.ExtractTriplesEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ExtractTriplesEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.presenter.WebSiteSelectionBarPresenter.Display;
import umbc.ebiquity.kang.ontologypopulating.client.util.UrlValidator;
import umbc.ebiquity.kang.ontologypopulating.client.util.WidgetSizeController;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TextMatchStyle;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.grid.CellFormatter;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

public class WebSiteSelectionBarView implements Display{
	
	/*
	 * Trigger the event of extract triples from a particular web site
	 */
	private ExtractTriplesEventHandler extractTriplesEventHandler;
	
	/*
	 * 
	 */
	private HLayout webSiteSelectionBarViewLayout;
	private HLayout buttonLayout;
	private IButton extractTripleButton;
	private IButton extractTripleAndOntologyPopulatingButton;
	private ComboBoxItem webSiteHomeUrlBox;
	private DynamicForm webSiteHomeUrlForm;
	
	
	public WebSiteSelectionBarView(){
		extractTripleButton = this.createExtractTripleButton();
		extractTripleAndOntologyPopulatingButton = this.createExtractTripleAndOntologyPopulatingButton();
		webSiteHomeUrlBox = this.createWebSiteHomeUrlBox();
		webSiteHomeUrlForm = this.createWebSiteHomeUrlForm();
		webSiteHomeUrlForm.setItems(webSiteHomeUrlBox);
		buttonLayout = new HLayout();
		buttonLayout.setMembersMargin(5);
		buttonLayout.setPadding(2);
		webSiteSelectionBarViewLayout = new HLayout();
		webSiteSelectionBarViewLayout.setMembersMargin(5);
		webSiteSelectionBarViewLayout.setPadding(2);
		webSiteSelectionBarViewLayout.setHeight(25);
		buttonLayout.addMember(extractTripleButton);
		buttonLayout.addMember(extractTripleAndOntologyPopulatingButton);
		webSiteSelectionBarViewLayout.addMember(webSiteHomeUrlForm);
		webSiteSelectionBarViewLayout.addMember(buttonLayout);
	}
	
	private DynamicForm createWebSiteHomeUrlForm() {
		
		DynamicForm webSiteHomeUrlForm = new DynamicForm();
		webSiteHomeUrlForm.setNumCols(4);
		webSiteHomeUrlForm.setAlign(Alignment.CENTER);
		webSiteHomeUrlForm.setHeight(22);
		return webSiteHomeUrlForm;
	}

	private ComboBoxItem createWebSiteHomeUrlBox() {
		final ComboBoxItem comboBox = new ComboBoxItem("URL");
		comboBox.setWidth(420);
		comboBox.setPickListWidth(400);
		comboBox.setPickListHeight(300);
		comboBox.setRequired(true);
		comboBox.setTitle("URL");
		comboBox.setTextBoxStyle("textBox");
		comboBox.setShowTitle(false);
//		comboBox.addKeyPressHandler(new KeyPressHandler() {
//
//			@Override
//			public void onKeyPress(KeyPressEvent event) {
//				// if(event.getKeyName().equals("Enter")){
//				// elementSelected(namespaceSearchBox.getValueAsString());
//				// }
//				// else {
//				// // TODO add the current key to the value
//				// String input = namespaceSearchBox.getValueAsString();
//				//
//				// }
//			}
//		});

		ListGrid pickListProperties = new ListGrid();
		pickListProperties.setCellFormatter(new CellFormatter() {
			@Override
			public String format(Object value, ListGridRecord record,
					int rowNum, int colNum) {
				String ns = record.getAttribute("URL");
				String styleStr = "font-family:arial;font-size:14px;";
				String retStr = "<span style='" + styleStr + " float:left'>"+ ns + "<span>";
				return retStr;

			}
		});

		comboBox.setPickListProperties(pickListProperties);
		comboBox.setTextMatchStyle(TextMatchStyle.SUBSTRING);
		return comboBox;
	}

	private IButton createExtractTripleAndOntologyPopulatingButton() {
		
		IButton button = new IButton("Extract&Map");
//		button.setMargin(WidgetSizeController.BUTTON_MARGIN);
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				//TODO: 
			}
		});
		return button;
	}

	private IButton createExtractTripleButton() {
		IButton button = new IButton("Extract");
//		button.setMargin(WidgetSizeController.BUTTON_MARGIN);
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				String url = webSiteHomeUrlBox.getValueAsString();
				System.out.println("Extracted triples from: " + url);
				if (url.trim().equals("")) {
					SC.warn("The inputed URL should not be empty!");
				} else if (!UrlValidator.isValidUrl(url)) {
					SC.warn("The inputed URL is invalid!");
				} else {
					ExtractTriplesEvent extractTriplesEvent = new ExtractTriplesEvent(url);
					extractTriplesEventHandler.extractTriples(extractTriplesEvent);
				}
			}
		});
		return button;
	}

	@Override
	public void addExtractTriplesEventHandler(ExtractTriplesEventHandler handler){
		this.extractTriplesEventHandler = handler;
	}
	
	@Override
	public Widget asWidget() {
		return webSiteSelectionBarViewLayout;
	}
	
	@Override
	public void fillWebSiteHomeUrls(WebSiteHomeUrlDataSource datasource) {
		webSiteHomeUrlBox.setOptionDataSource(datasource);
	}
	
}
