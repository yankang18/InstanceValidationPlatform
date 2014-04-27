package umbc.ebiquity.kang.ontologypopulating.client.view;


import java.util.Collection;
import java.util.LinkedHashMap;

import umbc.ebiquity.kang.ontologypopulating.client.event.CreateOntClassRelationTripleEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.CreateOntClassRelationTripleEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.ontology.OntClassRelation;
import umbc.ebiquity.kang.ontologypopulating.client.ontology.SimpleTriple;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.layout.HLayout;

public class OntoClassHierarchyExtensionView extends Window {
	
//	private VLayout mainLayout;
	private HLayout buttonsLayout;  
	private HLayout ontoClassHierarchyExtensionLayout;
	private DynamicForm ontoClassHierarchyExtensionForm;
	private ComboBoxItem ontClassRelationsCombox;
	private ComboBoxItem ontClassesCombox;
	private Label termLabel;
	
	private IButton creationButton;
	private IButton cancelButton;
	
	private CreateOntClassRelationTripleEventHandler createOntClassRelationTripleEventHandler;
	
	public OntoClassHierarchyExtensionView(String term, Collection<String> ontClasses){
		
		ontoClassHierarchyExtensionLayout = new HLayout();
		ontoClassHierarchyExtensionLayout.setHeight100();
		ontoClassHierarchyExtensionForm = new DynamicForm();
		ontoClassHierarchyExtensionForm.setNumCols(4);
		ontClassRelationsCombox = this.createOntClassRelationCombox();
		ontClassesCombox = this.createOntClassesCombox(ontClasses);
		termLabel = this.createTermLabel(term);
		
		creationButton = this.createCreationButton();
		cancelButton = this.createCancelButton();
		buttonsLayout = new HLayout(5);
		buttonsLayout.setMargin(5);
		buttonsLayout.addMember(creationButton);
		buttonsLayout.addMember(cancelButton);
		buttonsLayout.setAlign(Alignment.RIGHT);
		
		ontoClassHierarchyExtensionForm.setFields(ontClassRelationsCombox, ontClassesCombox);
		ontoClassHierarchyExtensionLayout.addMember(termLabel);
		ontoClassHierarchyExtensionLayout.addMember(ontoClassHierarchyExtensionForm);
//		mainLayout.addMember(ontoClassHierarchyExtensionLayout);
//		mainLayout.addMember(buttons);
		this.setWidth(450);
		this.setHeight(150);
		this.setShowMinimizeButton(false);
		this.setAutoCenter(true);
		this.setIsModal(true);
		this.setTitle("Relate Term to Ontology Class");
		this.addItem(ontoClassHierarchyExtensionLayout);
		this.addItem(buttonsLayout);
		this.draw();
		
	}

	private Label createTermLabel(String termString) {
		Label label = new Label();
		label.setValign(VerticalAlignment.TOP);
		label.setAlign(Alignment.CENTER);
		label.setContents(termString);
		label.setMargin(5);
		return label;
	}

	private ComboBoxItem createOntClassesCombox(Collection<String> ontClasses) {
		ComboBoxItem comboBox = new ComboBoxItem();
		comboBox.setShowTitle(false);
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (String value : ontClasses) {
			valueMap.put(value, value);
		}
		comboBox.setValueMap(valueMap);
		return comboBox;
	}

	private ComboBoxItem createOntClassRelationCombox() {
		ComboBoxItem comboBox = new ComboBoxItem();
		comboBox.setShowTitle(false);
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		String subClassOf = OntClassRelation.subClassOf.toString();
		String equClassOf = OntClassRelation.equivalentClassOf.toString();
		valueMap.put(subClassOf, subClassOf);
		valueMap.put(equClassOf, equClassOf);
		comboBox.setValueMap(valueMap);
		return comboBox;
	}

	private IButton createCancelButton() {
		IButton button = new IButton("Cancel");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				OntoClassHierarchyExtensionView.this.closeView();
			}
		});

		return button;
    }
	
	private IButton createCreationButton(){
		IButton button = new IButton("OK");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				String classRel = ontClassRelationsCombox.getValueAsString();
				String ontClass = ontClassesCombox.getValueAsString();
				
				SimpleTriple triple = null;;
				if(OntClassRelation.subClassOf.toString().equals(classRel)){
					triple = new SimpleTriple(termLabel.getContents(), OntClassRelation.subClassOf, ontClass);
				} else {
					triple = new SimpleTriple(termLabel.getContents(), OntClassRelation.equivalentClassOf, ontClass);
				}
				
				CreateOntClassRelationTripleEvent e = new CreateOntClassRelationTripleEvent(triple);
				createOntClassRelationTripleEventHandler.createOntClassRelationTriple(e);
				OntoClassHierarchyExtensionView.this.closeView();
			}
		});
		return button;
	}
	
	public void addCreateOntClassRelationTripleEventHandler(CreateOntClassRelationTripleEventHandler handler) {
		createOntClassRelationTripleEventHandler = handler;
	}
	
	public void closeView() {
		this.destroy();
	}
	
	public void showView() {
//		this.show();
		this.centerInPage();
	}

}
