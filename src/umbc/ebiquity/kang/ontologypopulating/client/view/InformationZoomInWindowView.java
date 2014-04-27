package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.HashMap;

import umbc.ebiquity.kang.ontologypopulating.client.presenter.InformationZoomInWindowPresenter.Display;
import umbc.ebiquity.kang.ontologypopulating.client.ui.UpdatedInstanceRecord;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TextAreaWrap;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class InformationZoomInWindowView extends Window implements Display {
	
	protected VLayout windowLayout;
	private DynamicForm informationPresentingForm;
	private TextAreaItem informationPresentingTextArea;
	
	public InformationZoomInWindowView(String info){
		super();
		this.setTitle("Zoom In");
		this.setWidth100();
		this.setHeight100();
		this.setAlign(Alignment.CENTER);
		this.setShowMinimizeButton(false);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();
		this.addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				InformationZoomInWindowView.this.destroy();
			}
		});
		
		this.windowLayout = this.createWindowLayout();
		this.informationPresentingForm = this.createDynamticForm();
		this.informationPresentingTextArea = this.createInfortiomPresentingTextArea(info);
		this.windowLayout.addMember(informationPresentingForm);
		this.informationPresentingForm.setFields(informationPresentingTextArea);
		this.addItem(windowLayout);
//		this.draw();
		
	}
	
	private TextAreaItem createInfortiomPresentingTextArea(String info) {
		TextAreaItem textArea = new TextAreaItem("TRIPLE");
		textArea.setTitle("");
		textArea.setShowTitle(false);
		textArea.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
			}
		});

		textArea.setDisabled(true);
		// textArea.setValue(content);
		textArea.setWidth("*");
		textArea.setHeight("*");
		textArea.setCanEdit(false);
		textArea.setShowDisabled(false);
		textArea.setTextBoxStyle("triplesTextArea");
		textArea.setBrowserSpellCheck(false);
		textArea.setCanFocus(true);
		textArea.setWrap(TextAreaWrap.OFF);
		textArea.setValue(info);
		return textArea;
	}
	
	private VLayout createWindowLayout(){
		VLayout layout = new VLayout(2);
		layout.setWidth100();
		layout.setHeight100();
		return layout;
	}
	
	private DynamicForm createDynamticForm(){
		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setCellPadding(2);
		dynamicForm.setOverflow(Overflow.HIDDEN);
		dynamicForm.setShowEdges(true);
		// this.rdfForm.setWidth("800");
		// this.rdfForm.setHeight("500");
		dynamicForm.setHeight100();
		dynamicForm.setWidth100();
		dynamicForm.setMargin(2);
		dynamicForm.setNumCols(2);
		dynamicForm.setColWidths("*", 0);
		dynamicForm.setAutoFocus(false);
		return dynamicForm;
	}
	
	@Override
	public void showWindow(){
		this.show();
	}

	
	
}
