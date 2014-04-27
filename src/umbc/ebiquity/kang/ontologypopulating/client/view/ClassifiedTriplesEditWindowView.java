package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;

import umbc.ebiquity.kang.ontologypopulating.client.datasource.ConceptGridDataSource;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstanceDetailEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstanceDetailEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstancesOfOntoClassEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.presenter.ClassifiedTriplesEditWindowPresenter.Display;
import umbc.ebiquity.kang.ontologypopulating.client.ui.Concept2OntoClassTriple;
import umbc.ebiquity.kang.ontologypopulating.client.ui.SingleValueReceiver;
import umbc.ebiquity.kang.ontologypopulating.client.ui.UpdatedInstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.client.util.Images;
import umbc.ebiquity.kang.ontologypopulating.client.util.WidgetSizeController;
import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleClassifiedInstanceInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SpacerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ClassifiedTriplesEditWindowView extends Window implements Display, SingleValueReceiver {
	
	/*
	 * Get instance information of a particular onto-class
	 */
	private GetInstancesOfOntoClassEventHandler getInstancesOfOntoClassEventHandler;
	
	/*
	 * Get detail information of a particular instance
	 */
	private GetInstanceDetailEventHandler getDetailInstanceEventHandler; 
	
	private UpdatedInstancesSaveEventHandler updatedInstancesSaveEventHandler;
	
	/*
	 * 
	 */
	private DataSource ontoClassesDataSource;
	private DataSource ontoInstanceDataSource;
	
	/*
	 * 
	 */
	private ListGridRecord currentEditingRecord;
	private SimpleClassifiedInstanceInfo currentEditingInstanceInfo;
	private String currentEditingInstanceName;
	
	private Map<String, UpdatedInstanceRecord> updatedInstanceRecordMap;
	private Map<String, Concept2OntoClassTriple> updatedConcept2ClassMap;
	
	/*
	 * 
	 */
	private VLayout windowLayout;
	private HLayout instanceEditLayout;
	private VLayout instanceDetailLayout;
	private HLayout relatedConceptLayout;
	private HLayout buttonLayout;
	
	private ListGrid mainListGrid;
	private ListGrid relatedConceptList; 
//	private ListGrid selectedConceptsList;
	private DynamicForm instanceDetailForm; 
	
	private TextItem instanceTextItem;
	private TextItem classTextItem;  
	private OntClassChoosingTextItem otherClassTextItem;
	private SelectItem recommededClassesSelectItem = new SelectItem(); 
    private RadioGroupItem recommededClassesGroupItem = new RadioGroupItem(); 
	private SelectItem relatedConceptSelectItem = new SelectItem();
	
	private IButton applyChangeButton;
	private IButton saveChangeButton;
	
//	private String webSiteURL;
	
	public ClassifiedTriplesEditWindowView(SimpleMappingDetailInfo simpleMappingDetailInfo) {
//	public ClassifiedTriplesEditWindowView(DataSource dataSource) {
		super();
		this.setTitle("Update");
		this.setWidth100();
		this.setHeight100();
		this.setAlign(Alignment.CENTER);
//		this.noteLabel = new Label("Create a sub-class for <span style=\"color: green; font-weight: bold;\">" + className
//		                + "</span>");

		this.setShowMinimizeButton(false);
		this.setIsModal(true);
		this.setShowModalMask(true);
		this.centerInPage();
		this.addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				ClassifiedTriplesEditWindowView.this.destroy();
			}
		});
		
//		this.ontoInstanceDataSource = dataSource;
		this.updatedInstanceRecordMap = new HashMap<String, UpdatedInstanceRecord>();
		this.updatedConcept2ClassMap = new HashMap<String, Concept2OntoClassTriple>();
		this.instanceDetailLayout = this.createInstanceDetailLayout();
		this.instanceDetailForm = this.createInstanceEditForm();
		this.relatedConceptLayout = this.createRelatedConceptsLayout();
		this.windowLayout = this.createWindowLayout();
		this.instanceEditLayout = this.createInstanceEditLayout();
		this.buttonLayout = this.createButtonLayout(); 
		this.mainListGrid = this.createMainListGrid(simpleMappingDetailInfo);
//		this.mainListGrid = this.createMainListGrid();
		this.applyChangeButton = this.createApplyChangeButton();
		this.saveChangeButton = this.createPreviewButton();
		this.buttonLayout.addMember(applyChangeButton);
		this.buttonLayout.addMember(saveChangeButton);
		this.instanceDetailLayout.addMember(instanceDetailForm);
		this.instanceDetailLayout.addMember(relatedConceptLayout);
		this.instanceDetailLayout.addMember(buttonLayout);
		this.instanceEditLayout.addMember(mainListGrid);
		this.instanceEditLayout.addMember(instanceDetailLayout);
		this.windowLayout.addMember(instanceEditLayout);
		this.addItem(windowLayout);
		this.draw();
	}

	@Override
	public Widget asWidget(){
		return this;
	}
	
	public void showWindow(){
		this.show();
	}
	
	private VLayout createWindowLayout() {
		VLayout layout = new VLayout();
		layout.setMembersMargin(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(2);
		return layout;
	}


	private HLayout createButtonLayout() {
		HLayout layout = new HLayout();
		layout.setMembersMargin(3);
		layout.setWidth100();
		layout.setHeight(10);
		return layout;
	}
	
	private HLayout createInstanceEditLayout() {
		HLayout layout = new HLayout();
		layout.setMembersMargin(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(2);
		return layout;		
	}
	
	private VLayout createInstanceDetailLayout() {
		VLayout layout = new VLayout();
		layout.setMembersMargin(10);
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(2);
		return layout;
	}
	
	private HLayout createRelatedConceptsLayout() {
		
		HLayout layout = new HLayout();
		layout.setMembersMargin(5);
		layout.setWidth100();
		layout.setHeight100();
		layout.setPadding(2);
		
		VLayout relatedConceptListLayout = new VLayout();
		relatedConceptListLayout.setMembersMargin(2);
		relatedConceptListLayout.setWidth100();
		relatedConceptListLayout.setHeight100();
		
//		VLayout selectedConceptListLayout = new VLayout();
//		selectedConceptListLayout.setMembersMargin(2);
//		selectedConceptListLayout.setWidth100();
//		selectedConceptListLayout.setHeight100();

//		Label label1 = new Label("Selected Concepts");
//		label1.setHeight(22);
//		selectedConceptsList = new ListGrid();
//		ListGridField selectedConceptsField = new ListGridField("Concept", "Concept");
//		selectedConceptsList.setWidth100();
//		selectedConceptsList.setHeight(150);
//		selectedConceptsList.setShowAllRecords(true);
//		selectedConceptsList.setShowHeader(false);
//		selectedConceptsList.setFields(selectedConceptsField);
//		selectedConceptsList.setLayoutAlign(VerticalAlignment.TOP);

		Label label2 = new Label("Related Concepts");
		label2.setHeight(22);
		this.relatedConceptList = this.createRelatedConceptListGrid();
//        selectedConceptListLayout.addMember(label1);
//        selectedConceptListLayout.addMember(selectedConceptsList);
        relatedConceptListLayout.addMember(label2);
        relatedConceptListLayout.addMember(relatedConceptList);
        layout.addMember(relatedConceptListLayout);
//        layout.addMember(selectedConceptListLayout);
		return layout;	
	}
	
	private DynamicForm createInstanceEditForm() {
		
		DynamicForm dynamicForm = new DynamicForm();
		dynamicForm.setCellPadding(2);
		dynamicForm.setOverflow(Overflow.SCROLL);
		dynamicForm.setShowEdges(true);
		dynamicForm.setGroupTitle("Instance Update");
		dynamicForm.setHeight(400);
		dynamicForm.setWidth100();
		dynamicForm.setMargin(2);
//		dynamicForm.setNumCols(2);
//		dynamicForm.setColWidths("*", 0);
		dynamicForm.setAutoFocus(false);
		
		instanceTextItem = new TextItem();  
		instanceTextItem.setWidth(250);
		instanceTextItem.setTitle("Instance");  
//		instanceTextItem.setHint("<nobr>A plain text field</nobr>"); 
		
        classTextItem = new TextItem();  
        classTextItem.setWidth(250);
        classTextItem.setTitle("Class");  
        classTextItem.setCanEdit(false);
//        classTextItem.setHint("<nobr>A plain text field</nobr>"); 
        
        otherClassTextItem = new OntClassChoosingTextItem(this);
        otherClassTextItem.setWidth(250);
        
        otherClassTextItem.setShowTitle(true);
        otherClassTextItem.setTitle("Other Class");
        otherClassTextItem.setCanEdit(false);
        
        /*
         * 
         */
        recommededClassesGroupItem = new RadioGroupItem();  
        recommededClassesGroupItem.setTitle("Recommeded Classes");
        recommededClassesGroupItem.addChangedHandler(new ChangedHandler() {
			
			@Override
			public void onChanged(ChangedEvent event) {
				if("Other".equals(event.getValue())){
					otherClassTextItem.setCanEdit(true);
				} else {
					otherClassTextItem.setCanEdit(false);
				}
				
			}
		});
        
        dynamicForm.setFields(instanceTextItem, classTextItem, recommededClassesGroupItem, otherClassTextItem);
		return dynamicForm;
	}
	
	private IButton createApplyChangeButton() {
		
		/*
		 * 
		 */
		IButton button = new IButton("Apply");
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.setLayoutAlign(Alignment.RIGHT);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				
				StringBuilder messageBuilder = new StringBuilder();
				/*
				 * get values from various interface components, such as TextBoxItem for instance, RadioGroupItem
				 * for mapped class, and ListGrid for concept-to-class mapping
				 */
				String instanceLabel = instanceTextItem.getValueAsString();
				String instanceOriginalClassLabel = classTextItem.getValueAsString();
				String instanceRecommendedClassLabel = recommededClassesGroupItem.getValueAsString();
				if ("None".equals(instanceRecommendedClassLabel)) {
					// if did not choose any recommended class (that's choose "None"), then use the original class.
					instanceRecommendedClassLabel = instanceOriginalClassLabel;
				}
				else if ("Other".equals(instanceRecommendedClassLabel)) {
					// 
					String classLabel = otherClassTextItem.getValueAsString().trim();
					if ("".equals(classLabel)) {
						SC.say("You should input a class in the Other Text Box");
						return;
					}
					instanceRecommendedClassLabel = classLabel;
				}
				
				messageBuilder.append(instanceLabel + " was classfied into " + instanceRecommendedClassLabel + " \n");
				
				Collection<Concept2OntoClassTriple> relatedConcepts = new ArrayList<Concept2OntoClassTriple>();
				RecordList recordList = relatedConceptList.getRecordList();
				for (int i = 0; i < recordList.getLength(); i++) {
					String conceptLabel = recordList.get(i).getAttribute(ConceptGridDataSource.CONCEPT);
					String relationLabel = recordList.get(i).getAttribute(ConceptGridDataSource.RELATION);
					String updatedMappedClassLabel = recordList.get(i).getAttribute(ConceptGridDataSource.UPDATED_ONTOCLASS);
					String originalMappedClassLabel = recordList.get(i).getAttribute(ConceptGridDataSource.ORIGINAL_ONTOCLASS);
					boolean isDirectedMapping = Boolean.valueOf(recordList.get(i).getAttribute(ConceptGridDataSource.IS_DIRECTED_MAPPING));
					double similarity = Double.valueOf(recordList.get(i).getAttribute(ConceptGridDataSource.SIMILARITY));
					boolean isMappedConcept = !updatedMappedClassLabel.equalsIgnoreCase("None") ? true : false;
					/*
					 * record the updated concept-to-class mapping of currently editing instance
					 */
					Concept2OntoClassTriple triple = new Concept2OntoClassTriple(conceptLabel, relationLabel, originalMappedClassLabel);
					triple.updateOntoClassLabel(updatedMappedClassLabel);
					triple.setIsMappedConcept(isMappedConcept);
					triple.setIsDirectedMapping(isDirectedMapping);
					triple.setSimilarity(similarity);
					
					messageBuilder.append("    with conept-class mapping: " + triple.toString() + " \n");
					
					/*
					 * record concept-class mapping of current editing instance
					 */
					relatedConcepts.add(triple);

					/*
					 * trace the updates on concept-to-class mappings of all edited instances
					 */
					if (updatedConcept2ClassMap.containsKey(conceptLabel)) {
						/*
						 * 
						 */
						Concept2OntoClassTriple concept2OntoClassTriple = updatedConcept2ClassMap.get(conceptLabel);
						concept2OntoClassTriple.setIsMappedConcept(isMappedConcept);
						concept2OntoClassTriple.setIsDirectedMapping(isDirectedMapping);
						concept2OntoClassTriple.setRelationLabel(relationLabel);
						concept2OntoClassTriple.updateOntoClassLabel(updatedMappedClassLabel);
						concept2OntoClassTriple.setSimilarity(similarity);
					}
					else {
						/*
						 * 
						 */
						updatedConcept2ClassMap.put(conceptLabel, triple);
					}
				}
				
				/*
				 * trace and save changes on currently editing instance in the client side
				 */
				if(updatedInstanceRecordMap.containsKey(currentEditingInstanceName)){
					UpdatedInstanceRecord record = updatedInstanceRecordMap.get(currentEditingInstanceName);
					record.setChangedInstanceLabel(instanceLabel);
					record.setChangedOntoClassLabel(instanceRecommendedClassLabel);
					record.setConcept2OntoClassTripleCollections(relatedConcepts);
					record.setUpdated(true);
				} else {
					UpdatedInstanceRecord record = new UpdatedInstanceRecord(currentEditingInstanceName);
//					record.setInstanceLabel(currentEditingInstanceName);
					record.setChangedInstanceLabel(instanceLabel);
					record.setOntoClassLabel(instanceOriginalClassLabel);
					record.setChangedOntoClassLabel(instanceRecommendedClassLabel);
					record.setConcept2OntoClassTripleCollections(relatedConcepts);
					record.setUpdated(true);
					updatedInstanceRecordMap.put(currentEditingInstanceName, record);
				}
				
				/*
				 * change the list grid record of current editing instance accordingly
				 */
				classTextItem.setValue(instanceRecommendedClassLabel);
				currentEditingRecord.setAttribute("InstanceName", instanceLabel);
				currentEditingRecord.setAttribute("ClassName", instanceRecommendedClassLabel);
				currentEditingRecord.setAttribute("Similarity", "1.0");
				/*
				 *  set the flag for record that has been updated
				 */
				currentEditingRecord.setAttribute("Changed", "changed");
				int index = mainListGrid.getRecordIndex(currentEditingRecord);
				mainListGrid.refreshRow(index);
//				mainListGrid.redraw();
				
				System.out.println(messageBuilder);
			}
		});
		return button;
	}
	
	private IButton createPreviewButton() {
		IButton button = new IButton("Save");
		button.setWidth(WidgetSizeController.BUTTON_WIDTH);
		button.setLayoutAlign(Alignment.RIGHT);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				UpdatedInstancesSaveEvent updatedInstancesSaveEvent = new UpdatedInstancesSaveEvent();
				updatedInstancesSaveEvent.addUpdatedConcept2OntoClassMap(updatedConcept2ClassMap);
				for (UpdatedInstanceRecord record : updatedInstanceRecordMap.values()) {
					for(Concept2OntoClassTriple triple : record.getRelatedConcepts()){
						if(updatedConcept2ClassMap.containsKey(triple.getConceptLabel())){
							/*
							 * 
							 */
							Concept2OntoClassTriple updatedTriple = updatedConcept2ClassMap.get(triple.getConceptLabel());
							triple.clone(updatedTriple);
						}
					}
					updatedInstancesSaveEvent.addUpdatedInstanceRecord(record);
				}
				updatedInstancesSaveEventHandler.saveUpdatedInstances(updatedInstancesSaveEvent);
			}
		});
		return button;
	}
	
	@Override
	public void addGetInstancesOfOntoClassEventHandler(GetInstancesOfOntoClassEventHandler handler){
		this.getInstancesOfOntoClassEventHandler = handler;
	}
	
	@Override
	public void addGetInstanceDetailEventHandler(GetInstanceDetailEventHandler handler){
		this.getDetailInstanceEventHandler = handler;
	}
	
	@Override
	public void addUpdatedInstancesSaveEventHandler(UpdatedInstancesSaveEventHandler handler){
		this.updatedInstancesSaveEventHandler = handler;
	}
	
	/**
	 * @param instanceInfo
	 */
	private void refreshInstanceDetailForm(SimpleClassifiedInstanceInfo instanceInfo) {
		
		String instanceLabel = instanceInfo.getInstance().getEntityLabel();
		String classLabel = instanceInfo.getOntoClass().getEntityLabel();
		System.out.println("instance: " + instanceLabel);
		System.out.println("class: " + classLabel);

		/*
		 * 
		 */
		otherClassTextItem.clearValue();
		otherClassTextItem.setCanEdit(false);
		recommededClassesGroupItem.clearValue();
		LinkedHashMap<String, String> recommendedClassesForInstance = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> recommendedClassesForConceptInstance = new LinkedHashMap<String, String>();
//		LinkedHashMap<String, String> recommendedClasses = new LinkedHashMap<String, String>();
		int size = instanceInfo.getRecommendedLv1OntoClasses().size() + instanceInfo.getRecommendedLv2OntoClasses().size();
		if (size == 0) {
			recommendedClassesForInstance.put("None", "None");
			recommendedClassesForInstance.put("Other:", "Other");
			recommendedClassesForConceptInstance.put("None", "None");
		}
		else {
			recommendedClassesForInstance.put("None", "None");
			recommendedClassesForConceptInstance.put("None", "None");
			for (Entity recClass : instanceInfo.getRecommendedLv1OntoClasses()) {
				recommendedClassesForInstance.put(recClass.getEntityLabel(), recClass.getEntityLabel());
				recommendedClassesForConceptInstance.put(recClass.getEntityLabel(), recClass.getEntityLabel());
			}
			for (Entity recClass : instanceInfo.getRecommendedLv2OntoClasses()) {
				recommendedClassesForInstance.put(recClass.getEntityLabel(), recClass.getEntityLabel());
				recommendedClassesForConceptInstance.put(recClass.getEntityLabel(), recClass.getEntityLabel());
			}
			recommendedClassesForInstance.put("Other", "Other:");
		}
		recommededClassesGroupItem.setValueMap(recommendedClassesForInstance);

		if (updatedInstanceRecordMap.containsKey(currentEditingInstanceName)) {
			/*
			 * if information about current editing instance has been updated on the client side, fill these components
			 * with updated values.
			 */
			UpdatedInstanceRecord record = updatedInstanceRecordMap.get(currentEditingInstanceName);
			instanceTextItem.setValue(record.getChangedInstanceLabel());
			classTextItem.setValue(record.getChangedOntoClassLabel());
			recommededClassesGroupItem.setDefaultValue("None");

			ConceptGridDataSource datasource = new ConceptGridDataSource(recommendedClassesForConceptInstance);
			datasource.addData(record.getRelatedConcepts(), updatedConcept2ClassMap);
			relatedConceptList.setDataSource(datasource);
			relatedConceptList.fetchData();

		} else {
			/*
			 * 
			 */
			instanceTextItem.setValue(instanceLabel);
	        classTextItem.setValue(classLabel);
	    	recommededClassesGroupItem.setDefaultValue("None");
	    	
	    	ConceptGridDataSource datasource= new ConceptGridDataSource(recommendedClassesForConceptInstance);
			datasource.addData(instanceInfo.getRelatedConceptCollection(), classLabel, updatedConcept2ClassMap);
			relatedConceptList.setDataSource(datasource);
			relatedConceptList.fetchData();
		}
	}
	
	private ListGrid createMainListGrid(SimpleMappingDetailInfo simpleMappingDetailInfo) {
		final ListGrid listGrid = new ListGrid();
		listGrid.setShowRecordComponents(true);
		listGrid.setShowRecordComponentsByCell(true);
		listGrid.setCanEdit(false);
		listGrid.setShowAllRecords(true);
		listGrid.setCanResizeFields(true);
		listGrid.setWidth100();
		listGrid.setHeight100();
//		listGrid.setDrawAheadRatio(4);
//		listGrid.setCanRemoveRecords(true);
//		listGrid.setWarnOnRemoval(true);
		
		listGrid.addRecordClickHandler(new RecordClickHandler() {
			@Override
			public void onRecordClick(RecordClickEvent event) {
				
				/*
				 *  record current editing record;
				 */
				int recordNum = event.getRecordNum();
				currentEditingRecord = listGrid.getRecord(recordNum);
				currentEditingInstanceName = currentEditingRecord.getAttribute("OriginalInstanceName");
				
				/*
				 * 
				 */
				GetInstanceDetailEvent getInsanceDetailEvent = new GetInstanceDetailEvent(currentEditingInstanceName);
//				SimpleClassifiedInstanceInfo instanceInfo = getDetailInstanceEventHandler.getInstanceDetail(getInsanceDetailEvent);
//				fillInstanceDetailForm(instanceInfo);
				currentEditingInstanceInfo = getDetailInstanceEventHandler.getInstanceDetail(getInsanceDetailEvent);
				refreshInstanceDetailForm(currentEditingInstanceInfo);				
			}
		});
		
		/*
		 *  create List Fields
		 */
		ListGridField removeField = new ListGridField("removeAction", 40);
		removeField.setType(ListGridFieldType.ICON);
		removeField.setCellIcon("/images/" + Images.REMOVE);
		removeField.setCanEdit(false);
		removeField.setCanFilter(true);
		removeField.setFilterEditorType(new SpacerItem());
		removeField.setCanGroupBy(false);
		removeField.setCanSort(false);
		removeField.addRecordClickHandler(new RecordClickHandler() {
			public void onRecordClick(final RecordClickEvent event) {
				event.cancel();
				final Record record = event.getRecord();
				
				SC.confirm("Remove?", "Do you really want to remove this instance", new BooleanCallback() {
					public void execute(Boolean value) {
						if (value) {
							listGrid.removeData(record);
//							listGrid.redraw();
							String instanceOrigName = record.getAttribute("OriginalInstanceName");
							if(updatedInstanceRecordMap.containsKey(instanceOrigName)){
								updatedInstanceRecordMap.remove(instanceOrigName);
							}
						}
					}
				});
			}
		});
		
		ListGridField investigateFiled = new ListGridField("Investigate", "", 60);
		investigateFiled.setAlign(Alignment.CENTER);
		investigateFiled.setType(ListGridFieldType.IMAGE);
		investigateFiled.setImageURLPrefix("/images/investigate/");
		investigateFiled.setImageURLSuffix(".png");
		
	    ListGridField changedMarkField = new ListGridField("Changed", "", 60);  
        changedMarkField.setAlign(Alignment.CENTER);  
        changedMarkField.setType(ListGridFieldType.IMAGE);  
        changedMarkField.setImageURLPrefix("/images/change_mark/");  
        changedMarkField.setImageURLSuffix(".png");  
		
		ListGridField origInstanceNameField = new ListGridField("OriginalInstanceName", "Instance");
		origInstanceNameField.setRequired(true);
		origInstanceNameField.setHidden(true);
		ListGridField ontoInstanceNameField = new ListGridField("InstanceName", "Instance");
		ontoInstanceNameField.setRequired(true);
		ListGridField ontoClassNameField = new ListGridField("ClassName", "Class");
		ontoClassNameField.setRequired(true);
		ListGridField similarityField = new ListGridField("Similarity", "Score", 40);
		similarityField.setRequired(true);
		listGrid.setFields(origInstanceNameField, ontoInstanceNameField, ontoClassNameField, similarityField, investigateFiled, changedMarkField, removeField);

		/*
		 * 
		 * Get the data for the List Grid
		 */
		RecordList records = new RecordList();
		if (simpleMappingDetailInfo != null) {
			for (Entity ontoClass : simpleMappingDetailInfo.getOntoClass2InstancesMap().keySet()) {
				for (SimpleClassifiedInstanceInfo instanceInfo : simpleMappingDetailInfo.getOntoClass2InstancesMap().get(ontoClass)) {
					ListGridRecord record = new ListGridRecord();
					record.setAttribute("OriginalInstanceName",instanceInfo.getInstanceLabel());
					record.setAttribute("InstanceName",instanceInfo.getInstanceLabel());
					record.setAttribute("ClassName", ontoClass.getEntityLabel());
					record.setAttribute("Similarity",instanceInfo.getSimilarity());
					int numOfConcept = instanceInfo.getRelatedConceptCollection().size();
					if (numOfConcept > 0) {
						record.setAttribute("Investigate", "related");
					}
					records.add(record);
				}
			}
		}
		
//		listGrid.setDataSource(ontoInstanceDataSource);
//		listGrid.fetchData();
		listGrid.setData(records);
		return listGrid;
	}
	
	
//	DataSource dataSource;
	public ListGrid createRelatedConceptListGrid(){
		
		/*
		 * 
		 */
		ListGrid relatedConceptList = new ListGrid();
		relatedConceptList.setWidth100();
		relatedConceptList.setHeight100();
//		relatedConceptList.setSelectionType(SelectionStyle.SIMPLE);
//		relatedConceptList.setSelectionAppearance(SelectionAppearance.CHECKBOX);
		relatedConceptList.setShowRecordComponents(true);
		relatedConceptList.setShowRecordComponentsByCell(true);
		relatedConceptList.setCanRemoveRecords(false);
		relatedConceptList.setCanEdit(true);
		relatedConceptList.setShowAllRecords(true);
		relatedConceptList.setShowHeader(true);
		relatedConceptList.setLayoutAlign(VerticalAlignment.TOP);
//		relatedConceptList.setAutoFetchData(true);
		
		/*
		 * 
		 */
//        relatedConceptList.addSelectionUpdatedHandler(new SelectionUpdatedHandler() {
//			@Override
//			public void onSelectionUpdated(SelectionUpdatedEvent event) {
//				selectedConceptsList.setData(relatedConceptList.getSelectedRecords());				
//			}
//		});
		
		/*
		 * 
		 */
		ListGridField conceptNameField = new ListGridField("Concept", "Concept");
		conceptNameField.setCanEdit(false);
		ListGridField relationField = new ListGridField("Relation", "Relation");
		ListGridField relatedClassField = new ListGridField("OntoClass", "Class", 250);
		relatedConceptList.setFields(conceptNameField, relationField, relatedClassField);
        return relatedConceptList;
	}

	@Override
	public void setSingleValue(String value) {
		/*
		 * 
		 */
		
		LinkedHashMap<String, String> recommendedClassesForConceptInstance = new LinkedHashMap<String, String>();
		int size = this.currentEditingInstanceInfo.getRecommendedLv1OntoClasses().size() + this.currentEditingInstanceInfo.getRecommendedLv2OntoClasses().size();
		if (size == 0) {
			recommendedClassesForConceptInstance.put("None", "None");
			recommendedClassesForConceptInstance.put(value, value);
		}
		else {
			recommendedClassesForConceptInstance.put("None", "None");
			recommendedClassesForConceptInstance.put(value, value);
			for (Entity recClass : currentEditingInstanceInfo.getRecommendedLv1OntoClasses()) {
				recommendedClassesForConceptInstance.put(recClass.getEntityLabel(), recClass.getEntityLabel());
			}
			for (Entity recClass : currentEditingInstanceInfo.getRecommendedLv2OntoClasses()) {
				recommendedClassesForConceptInstance.put(recClass.getEntityLabel(), recClass.getEntityLabel());
			}
		}

		if (updatedInstanceRecordMap.containsKey(currentEditingInstanceName)) {
			/*
			 * if information about current editing instance has been updated on the client side, fill these components
			 * with updated values.
			 */
			UpdatedInstanceRecord record = updatedInstanceRecordMap.get(currentEditingInstanceName);
			ConceptGridDataSource datasource = new ConceptGridDataSource(recommendedClassesForConceptInstance);
			datasource.addData(record.getRelatedConcepts(), updatedConcept2ClassMap);
			relatedConceptList.setDataSource(datasource);
			relatedConceptList.fetchData();

		}
		else {

			ConceptGridDataSource datasource = new ConceptGridDataSource(recommendedClassesForConceptInstance);
			datasource.addData(currentEditingInstanceInfo.getRelatedConceptCollection(), "", updatedConcept2ClassMap);
			relatedConceptList.setDataSource(datasource);
			relatedConceptList.fetchData();
		}
	}
}
