package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import umbc.ebiquity.kang.ontologypopulating.client.event.ChooseOntClassEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ChooseOntClassEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.CreateOntClassRelationTripleEvent;
import umbc.ebiquity.kang.ontologypopulating.client.ontology.OntClassRelation;
import umbc.ebiquity.kang.ontologypopulating.client.ontology.SimpleTriple;
import umbc.ebiquity.kang.ontologypopulating.client.util.SizeController;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class ChangingMatchedOntoClassView extends Window {

	private Map<String, Boolean> classLabel2CheckMap;
	private Map<ImgButton, String> button2ClassLabelMap;
	private VLayout classHierarchyLayout;
	private HLayout buttonsLayout;
	
	private IButton creationButton;
	private IButton cancelButton;
	
	private ChooseOntClassEventHandler chooseOntClassEventHandler;
	
	public ChangingMatchedOntoClassView(Collection<String> ontClasses){
		
		creationButton = this.createCreationButton();
		cancelButton = this.createCancelButton();
		buttonsLayout = this.createButtonsLayout();
		classHierarchyLayout = this.createClassHierarchyLayout(ontClasses); 
		
		buttonsLayout.addMember(creationButton);
		buttonsLayout.addMember(cancelButton);
		
		/*
		 * Set values of attributes of this Window instance
		 */
		this.setWidth(300);
		this.setHeight(400);
		this.setShowMinimizeButton(false);
		this.setAutoCenter(true);
		this.setIsModal(true);
		this.setTitle("Choose Ontology Class From Class Hierarchy");
		this.addItem(classHierarchyLayout);
		this.addItem(buttonsLayout);
		this.draw();
		
	}
	
	private VLayout createClassHierarchyLayout(Collection<String> ontClasses){
		classLabel2CheckMap = new LinkedHashMap<String, Boolean>();
		button2ClassLabelMap = new HashMap<ImgButton, String>();
		VLayout classHierarchyLayout = new VLayout(1);
		classHierarchyLayout.setHeight100();
		List<String> ontClassesList = new ArrayList<String>();
		ontClassesList.addAll(ontClasses);
		int size = ontClasses.size();
		for (int i = 0; i < size; i++) {
			String ontClass = ontClassesList.get(i);
			classLabel2CheckMap.put(ontClass, false);
			ImgButton button = this.createRadioButtion();
			
//			IButton button = new IButton("");
			button2ClassLabelMap.put(button, ontClass);
			Label ontClassLabel = this.createOntClassLabel(ontClass);
			HLayout ontClassLayout = this.createOntClassLayout(ontClassLabel, button);
			HLayout subClassImgLayout = this.createSubClassImgLayout();
			classHierarchyLayout.addMember(ontClassLayout);
			if (i < size - 1) {
				classHierarchyLayout.addMember(subClassImgLayout);
			}
		}
		return classHierarchyLayout;
	}
	
	private HLayout createButtonsLayout(){
		HLayout buttonsLayout = new HLayout(5);
		buttonsLayout.setMargin(5);
		buttonsLayout.setAlign(Alignment.RIGHT);
		return buttonsLayout;
	}

	private HLayout createSubClassImgLayout() {
		String image = "../images/subClassOfIcon.png";
		Img subClassOfImg = new Img(image, 150, 45);
		subClassOfImg.setImageHeight(45);
		subClassOfImg.setImageWidth(150);
		subClassOfImg.setImageType(ImageStyle.NORMAL);
//		subClassOfImg.setBorder("1px solid gray");
		HLayout layout = new HLayout();
		layout.setHeight(47);
		layout.addMember(subClassOfImg);
		return layout;
	}

	private HLayout createOntClassLayout(Label ontClassLabel, ImgButton button) {
		HLayout layout = new HLayout(5);
		layout.addMember(ontClassLabel);
		layout.addMember(button);
		layout.setBorder("1px solid black");
		layout.setHeight(20);
		return layout;
	}

	private Label createOntClassLabel(String ontClass) {
		Label label = new Label();
		label.setContents(ontClass);
		label.setStyleName("classLabel");
		label.setAlign(Alignment.CENTER);
		label.setLayoutAlign(Alignment.CENTER);
		label.setMargin(5);
		label.setWidth100();
		return label;
	}

	private ImgButton createRadioButtion() {
		final ImgButton imgButton = new ImgButton();
		imgButton.setSize(SizeController.SMALL_ICON_SIZE, SizeController.SMALL_ICON_SIZE);
		imgButton.setMargin(3);
		imgButton.setLayoutAlign(VerticalAlignment.CENTER);
		imgButton.setSrc("../images/unchecked.png");
		imgButton.setShowRollOver(false);
		imgButton.setShowDown(false);
		imgButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String curr_OntClassLabel = button2ClassLabelMap.get(imgButton);
				boolean checked = classLabel2CheckMap.get(curr_OntClassLabel);
				if (!checked) {
					for (ImgButton button :button2ClassLabelMap.keySet()){
						String ontClassLabel = button2ClassLabelMap.get(button);
						button.setSrc("../images/unchecked.png");
						classLabel2CheckMap.put(ontClassLabel, false);
					}
					classLabel2CheckMap.put(curr_OntClassLabel, true);
					imgButton.setSrc("../images/checked.png");
				}
			}
		});
		return imgButton;
	}
	
	private IButton createCancelButton() {
		IButton button = new IButton("Cancel");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ChangingMatchedOntoClassView.this.closeView();
			}
		});

		return button;
    }
	
	private IButton createCreationButton(){
		IButton button = new IButton("OK");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				String checkedClassLabel = getCheckedOntClassLabel();
				ChooseOntClassEvent chooseOntClassEvent = new ChooseOntClassEvent(checkedClassLabel);
				chooseOntClassEventHandler.chooseOntClass(chooseOntClassEvent);
				ChangingMatchedOntoClassView.this.closeView();
			}
		});
		return button;
	}
	
	private String getCheckedOntClassLabel() {
		for(String ontClassLabel : classLabel2CheckMap.keySet()){
			if(classLabel2CheckMap.get(ontClassLabel)){
				return ontClassLabel;
			}
		}
		return null;
	}

	public void addChooseOntClassEventHandler(ChooseOntClassEventHandler handler){
		this.chooseOntClassEventHandler = handler;
	}
	
	public void closeView() {
		this.destroy();
	}
	
	public void showView() {
		this.centerInPage();
	}
}
