package umbc.ebiquity.kang.ontologypopulating.client.presenter;

import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingServiceAsync;
import umbc.ebiquity.kang.ontologypopulating.client.util.ErrorMessageHandler;
import umbc.ebiquity.kang.ontologypopulating.client.view.ApplicationView;
import umbc.ebiquity.kang.ontologypopulating.client.view.BasicResultView;
import umbc.ebiquity.kang.ontologypopulating.client.view.ProgressBarWindowView;
import umbc.ebiquity.kang.ontologypopulating.client.view.WebSiteSelectionBarView;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTripleStore;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;

public class ApplicationPresenter {
	
	public interface Display {
		void setViews(Widget webSiteSelectionBarView, Widget basicResultView);
		Widget asWidget();
	}
	
	private WebSiteSelectionBarPresenter webSiteSelectionBarPresenter;
	private BasicResultPresenter basicResultPresenter;
	private final OntologyMatchingServiceAsync ontologyMatchingService;
	private Display display;
	public ApplicationPresenter(OntologyMatchingServiceAsync ontologyMatchingService){
		this.ontologyMatchingService = ontologyMatchingService;
		this.webSiteSelectionBarPresenter = new WebSiteSelectionBarPresenter(ontologyMatchingService, new WebSiteSelectionBarView(), this);
		this.basicResultPresenter = new BasicResultPresenter(ontologyMatchingService, new BasicResultView(), this);
		this.display = new ApplicationView();
		this.display.setViews(webSiteSelectionBarPresenter.getPresentation(), basicResultPresenter.getPresentation());
		this.init();
	}
	
	private void init(){
		webSiteSelectionBarPresenter.initializeWebSiteSelectionBar();
	}
	
	public Widget getPresentation() {
		return this.display.asWidget();
	}
	
//	public void extractTriples(String webSiteUrl){
//		ontologyMatchingService.extractTripleStore(webSiteUrl, new AsyncCallback<SimpleTripleStore>() {
//			
//			@Override
//			public void onSuccess(SimpleTripleStore result) {
//				basicResultPresenter.fillExtractedTriples(result);
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				SC.warn(ErrorMessageHandler.getErrorMessage(caught));					
//			}
//		});
//	}
	
	public void extractTriples(String webSiteUrl) {

		String title = "";
		String message = "Extracting triples... It may take serveral minites to finish.";
		ProgressBarWindowView progressBarWindow = new ProgressBarWindowView(title, message);
		progressBarWindow.show();
		ontologyMatchingService.extractTripleStore(webSiteUrl,new TripleExtrationCallback(progressBarWindow, basicResultPresenter));
	}
	
	/**
	 * 
	 * @author kangyan2003
	 *
	 */
	class TripleExtrationCallback implements AsyncCallback<SimpleTripleStore> {
		 
	    ProgressBarWindowView progressBarWindow;
	    BasicResultPresenter resultPresenter;
	 
	    TripleExtrationCallback(ProgressBarWindowView theProgressBarWindow, BasicResultPresenter basicResultPresenter) {
	        progressBarWindow = theProgressBarWindow;
	        resultPresenter = basicResultPresenter;
	    }
	 
	    public void onFailure(Throwable caught) {
	        progressBarWindow.hide();
	    	SC.warn(ErrorMessageHandler.getErrorMessage(caught));	
	    }
	 
		@Override
		public void onSuccess(SimpleTripleStore result) {
			progressBarWindow.hide();
			basicResultPresenter.fillExtractedTriples(result);
		}
	}
	
}
