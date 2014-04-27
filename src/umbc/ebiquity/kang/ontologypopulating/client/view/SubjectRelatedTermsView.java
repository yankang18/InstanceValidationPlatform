package umbc.ebiquity.kang.ontologypopulating.client.view;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class SubjectRelatedTermsView extends Window {
	
	private VLayout mainLayout;
	private VLayout relatedTermsLayout;
	private HLayout titleLayout;
	private HLayout buttonsLayout;
	
	private IButton creationButton;
	private IButton cancelButton;
	
	private Set<RelatedTermView> relatedTermViewSet;
	
	public SubjectRelatedTermsView(Collection<String> relatedTerm, Collection<String> ontClasses){
		
		mainLayout = new VLayout(10);
//		titleLayout = this.createTitleLayout();
		relatedTermsLayout = new VLayout(3);
		relatedTermsLayout.setHeight100();
//		creationButton = this.createCreationButton();
		cancelButton = this.createCancelButton();
		buttonsLayout = new HLayout(5);
		buttonsLayout.setMargin(5);
//		buttonsLayout.addMember(creationButton);
		buttonsLayout.addMember(cancelButton);
		buttonsLayout.setAlign(Alignment.RIGHT);
//		mainLayout.addMember(titleLayout);
		mainLayout.addMember(relatedTermsLayout);
		mainLayout.addMember(buttonsLayout);
		relatedTermViewSet = new LinkedHashSet<RelatedTermView>();
		this.addRelatedTermView(relatedTerm, ontClasses);
		this.setWidth(600);
		this.setHeight(300);
		this.setShowMinimizeButton(false);
		this.setAutoCenter(true);
		this.setIsModal(true);
		this.setTitle("Related Terms");
		this.addItem(mainLayout);
		this.draw();
		
	}
	
	private HLayout createTitleLayout() {
		HLayout layout = new HLayout();
		Label titleLable = new Label();
		titleLable.setContents("<b>Related Terms</b>");
		titleLable.setStyleName("relatedTermsTitle");
		return layout;
	}

	private void addRelatedTermView(Collection<String> relatedTerm,
			                        Collection<String> ontClasses) {
		
		for(String term : relatedTerm){
			RelatedTermView relatedTermView = new RelatedTermView(term, ontClasses);
			relatedTermViewSet.add(relatedTermView);
			relatedTermsLayout.addMember(relatedTermView.asWidget());
		}
		
	}

	private IButton createCancelButton() {
		IButton button = new IButton("OK");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
//				SubjectRelatedTermsView.this.closeView();
				SubjectRelatedTermsView.this.hideView();
			}
		});

		return button;
    }
	
	private IButton createCreationButton(){
		IButton button = new IButton("OK");
		button.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
//				String ns = getNameSpace();
//				String subNS = getSubNameSpace();
//				CreateNamespaceEvent createNamespaceEvent = new CreateNamespaceEvent(ns, subNS);
////				CreateNamespaceEvent createNamespaceEvent = new CreateNamespaceEvent(ns);
//				createNamespaceEventhandler.createNamespace(createNamespaceEvent);
			}
		});
		return button;
	}
	
	public void closeView() {
		this.destroy();
		
	}
	
	public void showView() {
		this.show();
//		this.centerInPage();
	}
	
	public void hideView(){
		this.hide();
//		this.setVisible(false);
	}
	
	public void setVisible(){
		this.setVisible(true);
	}
	

}
