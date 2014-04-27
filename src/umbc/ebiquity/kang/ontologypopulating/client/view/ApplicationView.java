package umbc.ebiquity.kang.ontologypopulating.client.view;

import umbc.ebiquity.kang.ontologypopulating.client.presenter.ApplicationPresenter.Display;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class ApplicationView implements Display{
	
	private VLayout applicationLayout;
	
	public ApplicationView(){
		applicationLayout = this.createApplicationLayout();
	}
	
	private VLayout createApplicationLayout() {
		VLayout applicationLayout = new VLayout(2);
		applicationLayout.setHeight100();
		applicationLayout.setWidth("95%");
		// applicationLayout.setBorder("1px solid blue");
		applicationLayout.setAlign(Alignment.CENTER);
		applicationLayout.setLayoutAlign(Alignment.CENTER);
		applicationLayout.addStyleName("application-view");
		return applicationLayout;
	}
	
	public void addMatchedPair(MatchedSubject2OntoClassPairView pair){
		applicationLayout.addMember(pair.asWidget());
	}
	
	@Override
	public Widget asWidget(){
		return applicationLayout;
	}

	@Override
	public void setViews(Widget webSiteSelectionBarView, Widget basicResultView) {
		
		Label label = new Label("Just-In-Time Ontology Editor");
		label.setStyleName("h2");
		label.setIconWidth(373);
		label.setIconHeight(100);
		label.setAutoFit(true);
		label.setWrap(false);
		
		HLayout topBar = new HLayout();
		topBar.setWidth100();
		topBar.addMember(label);
		topBar.addMember(new LayoutSpacer());
		applicationLayout.addMember(topBar);

        HLayout spacerLayout = new HLayout();
        spacerLayout.setWidth100();
        spacerLayout.setHeight(5);
		applicationLayout.addMember(spacerLayout);
		applicationLayout.addMember(webSiteSelectionBarView);
		applicationLayout.addMember(basicResultView);
		
	}

}
