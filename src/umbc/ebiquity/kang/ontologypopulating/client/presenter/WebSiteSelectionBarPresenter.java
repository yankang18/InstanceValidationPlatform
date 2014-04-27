package umbc.ebiquity.kang.ontologypopulating.client.presenter;

import java.util.Collection;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;

import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingServiceAsync;
import umbc.ebiquity.kang.ontologypopulating.client.datasource.WebSiteHomeUrlDataSource;
import umbc.ebiquity.kang.ontologypopulating.client.event.ExtractTriplesEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ExtractTriplesEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.util.ErrorMessageHandler;

public class WebSiteSelectionBarPresenter {
	
	public interface Display {
		public Widget asWidget();
		public void fillWebSiteHomeUrls(WebSiteHomeUrlDataSource datasource);
		void addExtractTriplesEventHandler(ExtractTriplesEventHandler handler); 
	}
	
	private final OntologyMatchingServiceAsync ontologyMatchingService;
	private ApplicationPresenter parentPresenter;
	private Display display;
	
	public WebSiteSelectionBarPresenter(OntologyMatchingServiceAsync ontologyMatchingService, Display view, ApplicationPresenter applicationPresenter){
		this.parentPresenter = applicationPresenter;
		this.ontologyMatchingService = ontologyMatchingService;
		this.display = view;
		this.bind();
	}
	
	public Widget getPresentation() {
		return this.display.asWidget();
	}
	
	public void initializeWebSiteSelectionBar(){
		ontologyMatchingService.listWebSiteHomeUrls(new AsyncCallback<Collection<String>>() {
			
			@Override
			public void onSuccess(Collection<String> result) {
				WebSiteHomeUrlDataSource dataSource = new WebSiteHomeUrlDataSource();
				dataSource.addData(result);
				display.fillWebSiteHomeUrls(dataSource);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				SC.warn(ErrorMessageHandler.getErrorMessage(caught));				
			}
		});
	}
	
	private void bind(){
		display.addExtractTriplesEventHandler(new ExtractTriplesEventHandler() {
			
			@Override
			public void extractTriples(ExtractTriplesEvent event) {
				parentPresenter.extractTriples(event.getWebSiteUrl());
			}
		});
	}

}
