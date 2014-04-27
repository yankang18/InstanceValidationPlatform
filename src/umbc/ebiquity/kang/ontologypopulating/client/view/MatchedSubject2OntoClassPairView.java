package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.ArrayList;
import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.client.event.ChooseOntClassEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ChooseOntClassEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.util.SizeController;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class MatchedSubject2OntoClassPairView implements ChooseOntClassEventHandler{

	
	private HLayout mainLayout;
	private VLayout subjectLayout;
	private VLayout classLayout;
	private VLayout similarityLayout;
	private DynamicForm confirmOrRejectLayout;
	private RadioGroupItem confirmOrRejectButtons;
	private CheckboxItem confirmButtons;
	
	private Label subjectLabel;
	private Label classLabel;
	
	private ImgButton showRelatedTermButton;
	private ImgButton showClassHierarchyButton;
	
	private SubjectRelatedTermsView relatedTermsView;
	private Collection<String> relatedTerms;
	private Collection<String> ontClasses;
	
	public MatchedSubject2OntoClassPairView(Collection<String> relatedTerms,
                                            Collection<String> ontClasses, double similarity){
		/*
		 * 
		 */
		this.relatedTerms = new ArrayList<String>();
		this.relatedTerms.addAll(relatedTerms);
		this.ontClasses = new ArrayList<String>();
		this.ontClasses.addAll(ontClasses);
		
		/*
		 * 
		 */
		mainLayout = this.createMainLayout();
		subjectLayout = this.createMatchedSubjectLayout();
		classLayout = this.createMatchedOntClassLayout();
		similarityLayout = this.createSimilarityLayout(similarity);
		confirmOrRejectLayout = this.createConfirmButtonLayout();
		subjectLabel = this.createSubjectLabel();
		classLabel = this.createOntClassLabel();
		showRelatedTermButton = this.createShowRelatedTermButton();
		showClassHierarchyButton = this.createShowClassHierarchyButton();
		
		/*
		 * 
		 */
		mainLayout.addMember(subjectLayout);
		mainLayout.addMember(similarityLayout);
		mainLayout.addMember(classLayout);
		mainLayout.addMember(confirmOrRejectLayout);
		subjectLayout.addMember(subjectLabel);
		subjectLayout.addMember(showRelatedTermButton);
		classLayout.addMember(classLabel);
		classLayout.addMember(showClassHierarchyButton);
//		confirmOrRejectLayout.setFields(confirmOrRejectButtons);
	}
	
	private Label createSubjectLabel() {
		Label subjectLabel = new Label();
		subjectLabel.setStyleName("subjectLabel");
		subjectLabel.setHeight("40px");
		subjectLabel.setWidth100();
		subjectLabel.setAlign(Alignment.LEFT);
		subjectLabel.setValign(VerticalAlignment.CENTER);
		return subjectLabel;
	}
	
	private Label createOntClassLabel() {
		Label classLabel = new Label();
		classLabel.setStyleName("classLabel");
		classLabel.setHeight("40px");
		classLabel.setWidth100();
		classLabel.setAlign(Alignment.LEFT);
		classLabel.setValign(VerticalAlignment.CENTER);
		return classLabel;
	}

	private DynamicForm createConfirmButtonLayout() {
		DynamicForm confirmOrRejectLayout = new DynamicForm();
		confirmOrRejectLayout.setNumCols(2);
		confirmOrRejectLayout.setAlign(Alignment.RIGHT);
		confirmOrRejectLayout.setLayoutAlign(VerticalAlignment.CENTER);
		confirmOrRejectLayout.setWidth(100);
		confirmButtons = new CheckboxItem();
		confirmButtons.setTextBoxStyle("confirmOrRejectButtons");
		confirmButtons.setTitle("Confirm");
		confirmButtons.setWidth(100);
		
//		confirmOrRejectButtons = new RadioGroupItem();
//		confirmOrRejectButtons.setVertical(false);
//		confirmOrRejectButtons.setValueMap("Confirm", "Reject");
//		confirmOrRejectButtons.setTextBoxStyle("confirmOrRejectButtons");
//		confirmOrRejectButtons.setTitle("");
//		confirmOrRejectButtons.setWidth(100);
		
		confirmOrRejectLayout.setFields(confirmButtons);
		return confirmOrRejectLayout;
	}
	
	private HLayout createMainLayout(){
		HLayout mainLayout = new HLayout(10);
		mainLayout.setStyleName("matchedSubject2OntoClassPairView");
		mainLayout.setWidth("95%");
		mainLayout.setLayoutAlign(Alignment.CENTER);
//		mainPanel.setBorder("1px solid black");
		mainLayout.setHeight("42px");
		return mainLayout;
	}
	
	private VLayout createMatchedSubjectLayout(){
		VLayout subjectLayout = new VLayout();
//		subjectLayout.setBorder("1px solid blue");
		subjectLayout.setWidth("40%");
		return subjectLayout;
	}
	
	private VLayout createSimilarityLayout(double similarity) {
		
		VLayout similarityLayout = new VLayout();
		similarityLayout.setWidth(150);
		HLayout similarityNumLayout = new HLayout();
//		similarityNumLayout.setBorder("1px solid blue");
		HLayout imgLayout = new HLayout();
		Label similarityLabel = new Label();
		similarityLabel.setLayoutAlign(Alignment.CENTER);
		similarityLabel.setContents(String.valueOf(similarity));
		similarityLabel.setWidth(50);
		similarityLabel.setAlign(Alignment.RIGHT);
		ImgButton explainImgButton = this.createExplainButton();
		String image = "../images/matchArrow.png";
		Img arrowImg = new Img(image, 100, 30);
		arrowImg.setImageHeight(30);
		arrowImg.setImageWidth(100);
		arrowImg.setImageType(ImageStyle.NORMAL);
		arrowImg.setLayoutAlign(Alignment.LEFT);
		
		similarityNumLayout.addMember(similarityLabel);
		similarityNumLayout.addMember(explainImgButton);
		imgLayout.addMember(arrowImg);
		similarityLayout.addMember(similarityNumLayout);
		similarityLayout.addMember(imgLayout);
		return similarityLayout;
	}
	
	private ImgButton createExplainButton() { 
		final ImgButton imgButton = new ImgButton();
		imgButton.setSize(SizeController.SMALL_ICON_SIZE, SizeController.SMALL_ICON_SIZE);
		imgButton.setMargin(3);
		imgButton.setLayoutAlign(VerticalAlignment.CENTER);
		imgButton.setLayoutAlign(Alignment.LEFT);
		imgButton.setSrc("../images/explain.png");
		imgButton.setShowRollOver(false);
		imgButton.setShowDown(false);
		imgButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
			}
		});
		return imgButton;
	}

	private VLayout createMatchedOntClassLayout(){
		VLayout classLayout = new VLayout();
//		classLayout.setBorder("1px solid blue");
		classLayout.setWidth("40%");
		return classLayout;
	}

	public ImgButton createShowRelatedTermButton() {
//		IButton button = new IButton("@");
		ImgButton button = new ImgButton();
		button.setShowRollOver(false);
		button.setShowDown(false);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		button.setLayoutAlign(Alignment.LEFT);
		button.setSrc("../images/related.png");
		button.setSize(SizeController.SMALL_ICON_SIZE, SizeController.SMALL_ICON_SIZE);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (relatedTermsView == null) {
					relatedTermsView = new SubjectRelatedTermsView(relatedTerms, ontClasses);
				}
				relatedTermsView.showView();
			}
		});
		return button;
	}
	
	public ImgButton createShowClassHierarchyButton() {
//		IButton button = new IButton("C");
		ImgButton button = new ImgButton();
		button.setShowRollOver(false);
		button.setShowDown(false);
		button.setLayoutAlign(VerticalAlignment.CENTER);
		button.setLayoutAlign(Alignment.LEFT);
		button.setSrc("../images/change.png");
		button.setSize(SizeController.SMALL_ICON_SIZE, SizeController.SMALL_ICON_SIZE);
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ChangingMatchedOntoClassView changingMatchedOntoClassView = new ChangingMatchedOntoClassView(ontClasses);
				changingMatchedOntoClassView.addChooseOntClassEventHandler(MatchedSubject2OntoClassPairView.this);
				changingMatchedOntoClassView.showView();
			}
		});
		return button;
	}
	
	public void setSubjectLabel(String label){
		/*
		 * the label variable can be any HTML string
		 */
		this.subjectLabel.setContents(label);
	}
	
	public void setMatchedClassLabel(String label){
		/*
		 * the label variable can be any HTML string
		 */
		this.classLabel.setContents(label);
	}
	
	public Widget asWidget() {
		return mainLayout;
	}

	@Override
	public void chooseOntClass(ChooseOntClassEvent event) {
		String ontClassLabel = event.getOntClassLabel();
		if (ontClassLabel != null) {
			this.setMatchedClassLabel(ontClassLabel);
		}
	}

}
