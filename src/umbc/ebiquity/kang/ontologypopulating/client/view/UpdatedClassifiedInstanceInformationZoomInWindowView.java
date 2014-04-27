package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.presenter.UpdatedClassifiedInstanceInformationZoomInWindowPresenter.Display;
import umbc.ebiquity.kang.ontologypopulating.client.ui.Concept2OntoClassTriple;
import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.client.ui.UpdatedInstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.client.util.WidgetSizeController;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TextAreaWrap;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class UpdatedClassifiedInstanceInformationZoomInWindowView extends Window implements Display {

	private HLayout buttonLayout;
	private IButton saveButton;
	protected VLayout windowLayout;
	private DynamicForm informationPresentingForm;
	private TextAreaItem informationPresentingTextArea;
	private UpdatedInstancesSaveEventHandler updatedInstancesSaveEventHandler;
	private Collection<InstanceRecord> instanceRecords;
	
	public UpdatedClassifiedInstanceInformationZoomInWindowView(Collection<InstanceRecord> instanceRecords){
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
				UpdatedClassifiedInstanceInformationZoomInWindowView.this.destroy();
			}
		});
		
		this.instanceRecords = instanceRecords;
		this.windowLayout = this.createWindowLayout();
		this.buttonLayout = this.createButtonLayout();
		this.saveButton = this.createSaveButton();
		this.buttonLayout.addMember(saveButton);
		this.informationPresentingForm = this.createDynamticForm();
		this.informationPresentingTextArea = this.createInfortiomPresentingTextArea(this.getInformationString(this.instanceRecords));
		this.windowLayout.addMember(informationPresentingForm);
		this.windowLayout.addMember(buttonLayout);
		this.informationPresentingForm.setFields(informationPresentingTextArea);
		this.addItem(windowLayout);
//		this.draw();
	}
	
	private String getInformationString(Collection<InstanceRecord> instanceRecords) {
		
		Set<String> concept = new HashSet<String>();
		StringBuilder instanceClassificationBuilder = new StringBuilder();
		StringBuilder concept2OntoClass = new StringBuilder();
		for(InstanceRecord record: instanceRecords){
			String instanceLabel;
			String ontoClassLabel;
			boolean isUpdated;
			if (record instanceof UpdatedInstanceRecord) {
				UpdatedInstanceRecord updatedInstance = (UpdatedInstanceRecord) record;
				instanceLabel = updatedInstance.getChangedInstanceLabel();
				ontoClassLabel = updatedInstance.getChangedOntoClassLabel();
				isUpdated = true;
			} else {
				 instanceLabel = record.getInstanceLabel();
				 ontoClassLabel = record.getOntoClassLabel();
				 isUpdated = false;
			}
			
			/*
			 * 
			 */
			for(Concept2OntoClassTriple triple : record.getRelatedConcepts()){
				String conceptLabel = triple.getConceptLabel();
				if (!concept.contains(conceptLabel) && triple.isMappedConcept()) {
					concept2OntoClass.append("<" + conceptLabel + ">  <" + triple.getRelationLabel() + ">  <" + triple.getUpdatedOntoClassLabel() + ">");
					concept2OntoClass.append(this.getLineSeparator());
					concept.add(conceptLabel);
				} 
			}
			
			String header = isUpdated == true ? "[Updated]:  " : "";
			instanceClassificationBuilder.append(header + "<" + instanceLabel + ">  <rdf:type>  <" + ontoClassLabel + ">");
			instanceClassificationBuilder.append(this.getLineSeparator());
		}
		
		instanceClassificationBuilder.append(this.getLineSeparator());
		instanceClassificationBuilder.append("==================  All Concept-Class Mappings  ================================================");
		instanceClassificationBuilder.append(this.getLineSeparator());
		instanceClassificationBuilder.append(concept2OntoClass.toString());
		return instanceClassificationBuilder.toString();
	}
	
	private String getLineSeparator(){
		return "\n";
	}

	private IButton createSaveButton() {
		IButton button = new IButton("Save");
		// button.setMargin(WidgetSizeController.BUTTON_MARGIN);
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				UpdatedInstancesSaveEvent updatedInstancesSaveEvent = new UpdatedInstancesSaveEvent();
				updatedInstancesSaveEvent.setUpdatedInstanceRecords(instanceRecords);
				updatedInstancesSaveEventHandler.saveUpdatedInstances(updatedInstancesSaveEvent);
			}
		});
		return button;
	}
	
	private HLayout createButtonLayout() {
		HLayout layout = new HLayout();
		layout.setMembersMargin(5);
		layout.setWidth100();
		layout.setHeight(25);
		layout.setPadding(2);
		return layout;
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


	@Override
	public void addSaveUpdatedClassifiedInstanceInformationEventHandler(UpdatedInstancesSaveEventHandler handler) {
		this.updatedInstancesSaveEventHandler = handler;
	}

	@Override
    public void closeWindow() {
	    this.hide();
    }

}
