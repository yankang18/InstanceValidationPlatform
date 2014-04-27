package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.ArrayList;
import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.client.event.CreateOntClassRelationTripleEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.CreateOntClassRelationTripleEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.ontology.SimpleTriple;
import umbc.ebiquity.kang.ontologypopulating.client.util.SizeController;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class RelatedTermView implements CreateOntClassRelationTripleEventHandler {
	
	private VLayout relatedTermLayout;
	private HLayout relatedTermInfoLayout;
	private HLayout relatedTermRelationInfoLayout;
	private ImgButton insertToHierarchyButton;
	private Label termLabel;
	private String termString;
	private Collection<String> ontClassSet;
	private SimpleTriple simpleTriple;
	
	public RelatedTermView(String relatedTerm, Collection<String> ontClasses){
		
		termString = relatedTerm;
		ontClassSet = new ArrayList<String>();
		ontClassSet.addAll(ontClasses);
		
		relatedTermLayout = new VLayout();
		relatedTermLayout.setHeight(45);
		relatedTermInfoLayout = new HLayout(5);
		relatedTermInfoLayout.setHeight(20);
		relatedTermInfoLayout.setPadding(2);
//		relatedTermInfoLayout.setBorder("1px solid black");
		relatedTermRelationInfoLayout = this.createRelatedTermRelationInfoLayout();
		relatedTermRelationInfoLayout.setVisible(false); 
		insertToHierarchyButton = this.createAddToHierarchyButton();
		termLabel = this.createRelatedTermLabel(relatedTerm);
		relatedTermInfoLayout.addMember(termLabel);
		relatedTermInfoLayout.addMember(insertToHierarchyButton);
		relatedTermLayout.addMember(relatedTermInfoLayout);
		relatedTermLayout.addMember(relatedTermRelationInfoLayout);	
	}
	
	private Label createRelatedTermLabel(String termStr){
		Label termLabel = new Label();
		termLabel.setContents(termStr);
		termLabel.setStyleName("termLabel");
		termLabel.setWidth100();
		termLabel.setLayoutAlign(Alignment.LEFT);
		termLabel.setLayoutAlign(VerticalAlignment.CENTER);
		termLabel.setStyleName("relatedTermLabel");
		return termLabel;
	}
	
	private ImgButton createAddToHierarchyButton(){
//		IButton button = new IButton("+");
		ImgButton button = new ImgButton();
		button.setShowRollOver(false);
		button.setShowDown(false);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		button.setSrc("../images/add.png");
		button.setSize(SizeController.SMALL_ICON_SIZE, SizeController.SMALL_ICON_SIZE);
		button.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				OntoClassHierarchyExtensionView hierarchyExtensionView = new OntoClassHierarchyExtensionView(termString, ontClassSet);
				hierarchyExtensionView.addCreateOntClassRelationTripleEventHandler(RelatedTermView.this);
				hierarchyExtensionView.showView();
			}
		});
		return button;
	}
	
	@Override
	public void createOntClassRelationTriple(CreateOntClassRelationTripleEvent event) {
		SimpleTriple triple = event.getSimpleTriple();
		this.simpleTriple = triple;
		Label placeHolderLable = new Label();
		placeHolderLable.setWidth(30);
		Label ontClassRelationLabel = new Label();
		ontClassRelationLabel.setStyleName("ontClassRelation");
		ontClassRelationLabel.setContents(triple.getOntClassRelation().toString());
		Label ontClassLabel = new Label();
		ontClassLabel.setContents(triple.getTripleObject());
		ontClassLabel.setStyleName("ontClass");
		relatedTermRelationInfoLayout.destroy();
		relatedTermRelationInfoLayout = this.createRelatedTermRelationInfoLayout();
		relatedTermLayout.addMember(relatedTermRelationInfoLayout);	
		relatedTermRelationInfoLayout.setVisible(true);
		relatedTermRelationInfoLayout.addMember(placeHolderLable);
		relatedTermRelationInfoLayout.addMember(ontClassRelationLabel);
		relatedTermRelationInfoLayout.addMember(ontClassLabel);
	}
	
	private HLayout createRelatedTermRelationInfoLayout(){
		HLayout relatedTermRelationInfoLayout = new HLayout(20);
//		relatedTermRelationInfoLayout.setBorder("1px solid black");
		relatedTermRelationInfoLayout.setHeight(20);
		relatedTermRelationInfoLayout.setPadding(2);
		return relatedTermRelationInfoLayout;
	}
	
	public Widget asWidget(){
		return relatedTermLayout;
	}
}
