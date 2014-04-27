package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.TextAreaWrap;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifiedTriplesEditEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifiedTriplesEditEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifyTriplesEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifyTriplesEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.ExtractTriplesEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.InformationZoomInEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.InformationZoomInEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.presenter.BasicResultPresenter.Display;
import umbc.ebiquity.kang.ontologypopulating.client.util.WidgetSizeController;
import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTriple;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTripleStore;

public class BasicResultView implements Display {
	
	
	/*
	 * 
	 */
	private ClassifyTriplesEventHandler classifyTriplesEventHandler;
	private ClassifiedTriplesEditEventHandler classifiedTriplesEditEventHandler;
	private InformationZoomInEventHandler informationZoomInEventHandler;
	
	/*
	 * 
	 */
	private String webSiteUrl;
	private String localStrageName;
	
	/*
	 * 
	 */
	private HLayout BasicResultViewLayout;
	private VLayout mappingResultViewLayout;
	private VLayout tripleResultViewLayout;
	private TextAreaItem extractedTriplesArea;
	private TextAreaItem classifiedTriplesArea;
//	private Canvas extractedTriplesCanvas;
//	private Canvas classifiedTriplesCanvas;
	private DynamicForm extractedTriplesForm;
	private DynamicForm classifiedTriplesForm;
	private IButton classifyTriplesButton;
	private IButton editClassificationInfoButton;
	private IButton editPropertyMappingInfoButton;
	private IButton zoominTriplesButton;
	private IButton zoominClassifiedTriplesButton;
	
	public BasicResultView(){
		extractedTriplesArea = this.createTripleArea("Extracted Triples");
		classifiedTriplesArea = this.createTripleArea("Classifed Triple");
//		extractedTriplesCanvas = this.createCanvas("");
//		classifiedTriplesCanvas = this.createCanvas("");
		extractedTriplesForm = this.createDynamticForm();
		classifiedTriplesForm = this.createDynamticForm();
		
		classifyTriplesButton = this.createClassifyTripleButton();
		editClassificationInfoButton = this.createEditTriplesButton();
		editPropertyMappingInfoButton = this.createEditPropertyMappingInfoButton();
		zoominTriplesButton = this.createZoominTriplesButton();
		zoominClassifiedTriplesButton = this.createZoominClassifiedTripleButton();
		
		HLayout tripleResultViewButtonLayout = this.createButtonLayout();
		tripleResultViewButtonLayout.addMember(zoominTriplesButton);
		HLayout mappingResultViewButtonLayout = this.createButtonLayout();
		mappingResultViewButtonLayout.addMember(editClassificationInfoButton);
		mappingResultViewButtonLayout.addMember(editPropertyMappingInfoButton);		
		mappingResultViewButtonLayout.addMember(zoominClassifiedTriplesButton);
		
		BasicResultViewLayout = this.createHLayout();
		mappingResultViewLayout = this.createVLayout();
		tripleResultViewLayout = this.createVLayout();
		extractedTriplesForm.setFields(extractedTriplesArea);
		classifiedTriplesForm.setFields(classifiedTriplesArea);
		tripleResultViewLayout.addMember(extractedTriplesForm);
		tripleResultViewLayout.addMember(tripleResultViewButtonLayout);
		mappingResultViewLayout.addMember(classifiedTriplesForm);
		mappingResultViewLayout.addMember(mappingResultViewButtonLayout);
		BasicResultViewLayout.addMember(tripleResultViewLayout);
		BasicResultViewLayout.addMember(classifyTriplesButton);
		BasicResultViewLayout.addMember(mappingResultViewLayout);
	}
	
	private HLayout createButtonLayout() {
		HLayout layout = new HLayout();
		layout.setMembersMargin(5);
		layout.setWidth100();
		layout.setHeight(25);
		layout.setPadding(2);
		return layout;
	}

	private HLayout createHLayout() {
		HLayout layout = new HLayout();
		layout.setMembersMargin(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(2);
		return layout;
	}

	private VLayout createVLayout(){
		VLayout layout = new VLayout();
		layout.setMembersMargin(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(2);
		return layout;
	}
	
	private IButton createClassifyTripleButton() {
		IButton button = new IButton("Mapping");
//		button.setMargin(WidgetSizeController.BUTTON_MARGIN);
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ClassifyTriplesEvent classifyTriplesEvent = new ClassifyTriplesEvent(webSiteUrl, localStrageName);
				classifyTriplesEventHandler.classifyTriples(classifyTriplesEvent);
			}
		});
		return button;
	}
	
	private IButton createEditTriplesButton() {
		IButton button = new IButton("Edit Classified Instance");
//		button.setMargin(WidgetSizeController.BUTTON_MARGIN);
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ClassifiedTriplesEditEvent classifiedTriplesEditEvent = new ClassifiedTriplesEditEvent(webSiteUrl, localStrageName);
				classifiedTriplesEditEventHandler.editTriples(classifiedTriplesEditEvent);
			}
		});
		return button;
	}

	private IButton createZoominClassifiedTripleButton() {
		IButton button = new IButton("Zoom In");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String info = classifiedTriplesArea.getValueAsString();
				InformationZoomInEvent infoZoomInEvent = new InformationZoomInEvent(info);
				informationZoomInEventHandler.zoomIn(infoZoomInEvent);
			}
		});
		return button;
	}

	private IButton createZoominTriplesButton() {
		IButton button = new IButton("Zoom In");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String info = extractedTriplesArea.getValueAsString();
				InformationZoomInEvent infoZoomInEvent = new InformationZoomInEvent(info);
				informationZoomInEventHandler.zoomIn(infoZoomInEvent);
			}
		});
		return button;
	}

	private IButton createEditPropertyMappingInfoButton() {
		IButton button = new IButton("Edit Property Mapping");
		return button;
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

	private TextAreaItem createTripleArea(String areaName) {
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
		return textArea;
	}

	@Override
	public Widget asWidget() {
		return BasicResultViewLayout;
	}
	
	@Override
	public void fillExtractedTriples(SimpleTripleStore tripleStore){
		this.setExtractedTriplesOrigin(tripleStore.getWebSiteUrl(), tripleStore.getLocalStorageName());
		Collection<String> tripleStrings = new ArrayList<String>();
		recordTriplesGroupBySuject(tripleStrings, tripleStore.getTriplesOfCustomRelation());
		recordTriplesGroupBySuject(tripleStrings, tripleStore.getTriplesOfInstance2ConceptRelation());
		recordTriples(tripleStrings, tripleStore.getRelation2TypeSimpleTriple());
		StringBuilder tripleStringBuilder = new StringBuilder();
		for(String row : tripleStrings){
			tripleStringBuilder.append(row + "\n");
		}
		
		classifiedTriplesArea.clearValue();
		extractedTriplesArea.setValue(tripleStringBuilder.toString());
//		extractedTriplesCanvas.setContents(tripleStringBuilder.toString());
	}
	
	@Override
	public void fillBasicMappingInfo(SimpleTripleStore tripleStore){
		
		
		Collection<String> tripleStrings = new ArrayList<String>();
		tripleStrings.add("");
		tripleStrings.add("==================== Relation to Property Mapping ======================");
		tripleStrings.add("");
		for (Entity subject : tripleStore.getProperty2RelationMap().keySet()) {
			for (SimpleTriple triple : tripleStore.getProperty2RelationMap().get(subject)) {
				tripleStrings.add(" <" + triple.getSubject().getEntityLabel()
						+ ">  --->  <" + triple.getObject().getEntityLabel()
						+ ">    with score:  <" + triple.getSimiliarty() + ">");
			}
		}
		tripleStrings.add("");
		tripleStrings.add("");
		tripleStrings.add("");
		tripleStrings.add("==================== Instance Classification ===========================");
		for (Entity subject : tripleStore.getOntoClass2InstanceMap().keySet()) {
			tripleStrings.add("");
			tripleStrings.add("Class: " + subject.getEntityLabel());
			for (SimpleTriple triple : tripleStore.getOntoClass2InstanceMap().get(subject)) {
				tripleStrings.add("    score: <" + triple.getSimiliarty() + ">"
						+ "  instance: <" + triple.getSubject().getEntityLabel()
						+ ">");
			}
		}
		
		StringBuilder tripleStringBuilder = new StringBuilder();
		for(String row : tripleStrings){
			tripleStringBuilder.append(row + "\n");
		}
		
		classifiedTriplesArea.setValue(tripleStringBuilder.toString());
	}

	@Override
	public void setExtractedTriplesOrigin(String webSiteUrl, String localStorageName) {
		this.webSiteUrl = webSiteUrl;
		this.localStrageName = localStorageName;
	}
	
	@Override
	public void addClassifyTriplesEventHandler(ClassifyTriplesEventHandler handler){
		this.classifyTriplesEventHandler = handler;
	}
	
	@Override
	public void addClassifiedTriplesEditEventHandler(ClassifiedTriplesEditEventHandler handler){
		this.classifiedTriplesEditEventHandler = handler;
	}

	@Override
	public void addInformationZoomInEventHander(InformationZoomInEventHandler handler){
		this.informationZoomInEventHandler = handler;
		
	}
	
	private void recordTriplesGroupBySuject(Collection<String> tripleStrings, Map<Entity, Collection<SimpleTriple>> triplesGroupBySubject) {
		for (Entity subject : triplesGroupBySubject.keySet()) {
			tripleStrings.add("");
			for (SimpleTriple triple : triplesGroupBySubject.get(subject)) {
				tripleStrings.add("<" + triple.getSubject().getEntityLabel()
						+ "> <" + triple.getPredicate().getEntityLabel()
						+ "> <" + triple.getObject().getEntityLabel()
						+ ">");
			}
		}
	}

	private void recordTriples(Collection<String> tripleStrings, Collection<SimpleTriple> triples) {
		tripleStrings.add("");
		for (SimpleTriple triple : triples) {
			tripleStrings.add(recordTriple(triple));
		}
	}

	private String recordTriple(SimpleTriple triple) {
		return "<" + triple.getSubject().getEntityLabel() + "> <" + triple.getPredicate().getEntityLabel() + "> <"
		                + triple.getObject().getEntityLabel() + ">";
	}
}
