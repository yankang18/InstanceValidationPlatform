package umbc.ebiquity.kang.ontologypopulating.client.presenter;

import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingServiceAsync;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifiedTriplesEditEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifiedTriplesEditEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifyTriplesEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.ClassifyTriplesEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.InformationZoomInEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.InformationZoomInEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.util.ErrorMessageHandler;
import umbc.ebiquity.kang.ontologypopulating.client.view.ProgressBarWindowView;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTripleStore;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;

public class BasicResultPresenter {
	
	public interface Display {
		Widget asWidget();
		void fillExtractedTriples(SimpleTripleStore tripleStore);
		void fillBasicMappingInfo(SimpleTripleStore tripleStore); 
		void setExtractedTriplesOrigin(String webSiteUrl, String localStorageName);
		void addClassifyTriplesEventHandler(ClassifyTriplesEventHandler handler);
		void addClassifiedTriplesEditEventHandler(ClassifiedTriplesEditEventHandler handler);
		void addInformationZoomInEventHander(InformationZoomInEventHandler handler);
	}
	
	private ApplicationPresenter parentPresenter;
	private final OntologyMatchingServiceAsync ontologyMatchingService;
	private Display display;
	public BasicResultPresenter(OntologyMatchingServiceAsync ontologyMatchingService, Display view, ApplicationPresenter applicationPresenter){
		this.parentPresenter = applicationPresenter;
		this.ontologyMatchingService = ontologyMatchingService;
		this.display = view;
		this.bind();
	}
	
	private void bind() {
//		display.addClassifyTriplesEventHandler(new ClassifyTriplesEventHandler() {
//			@Override
//			public void classifyTriples(ClassifyTriplesEvent event) {
//				ontologyMatchingService.getMappingBasicInfo(event.getWebSiteUrl(), new AsyncCallback<SimpleTripleStore>() {
//					
//					@Override
//					public void onSuccess(SimpleTripleStore tripleStore) {
//						display.fillBasicMappingInfo(tripleStore);
//					}
//					
//					@Override
//					public void onFailure(Throwable caught) {
//						
//					}
//				});
//			}
//		});
		
		display.addClassifyTriplesEventHandler(new ClassifyTriplesEventHandler() {
			@Override
			public void classifyTriples(ClassifyTriplesEvent event) {
				
				String title = "";
				String message = "Mapping Triples to Ontology... It may take serveral minites to finish.";
				ProgressBarWindowView progressBarWindow = new ProgressBarWindowView(title, message);
				progressBarWindow.show();
				
				ontologyMatchingService.getMappingBasicInfo(event.getWebSiteUrl(), new TripleClassificationCallback(progressBarWindow, display));
			}
		});
		
//		display.addClassifiedTriplesEditEventHandler(new ClassifiedTriplesEditEventHandler() {
//			
//			@Override
//			public void editTriples(ClassifiedTriplesEditEvent event) {
//				ontologyMatchingService.getMappingDetailInfo(event.getWebSiteUrl(), new AsyncCallback<SimpleMappingDetailInfo>() {
//					
//					@Override
//					public void onSuccess(SimpleMappingDetailInfo simpleMappingDetailInfo) {
//						ClassifiedTriplesEditWindowPresenter presenter = new ClassifiedTriplesEditWindowPresenter(simpleMappingDetailInfo);
//					}
//					
//					@Override
//					public void onFailure(Throwable caught) {
//						
//					}
//				});				
//			}
//		});
		
		display.addClassifiedTriplesEditEventHandler(new ClassifiedTriplesEditEventHandler() {
			@Override
			public void editTriples(ClassifiedTriplesEditEvent event) {
				
				String title = "";
				String message = "Get detail of classified triples... It may take serveral minites to finish.";
				ProgressBarWindowView progressBarWindow = new ProgressBarWindowView(title, message);
				progressBarWindow.show();
				ontologyMatchingService.getMappingDetailInfo(event.getWebSiteUrl(), new GetClassifiedTriplesDetailInfoCallback(progressBarWindow, event.getWebSiteUrl()));				
			}
		});
		
		display.addInformationZoomInEventHander(new InformationZoomInEventHandler() {
			@Override
			public void zoomIn(InformationZoomInEvent event) {
				InformationZoomInWindowPresenter infoZoomInWindowPresenter = new InformationZoomInWindowPresenter(
						event.getInformation());
				infoZoomInWindowPresenter.showInformationZoomInWindow();

			}
		});
	}

	public void fillExtractedTriples(SimpleTripleStore tripleStore){
		this.display.fillExtractedTriples(tripleStore);
	}
	
	public Widget getPresentation() {
		return this.display.asWidget();
	}
	
	
	class TripleClassificationCallback implements AsyncCallback<SimpleTripleStore> {
		 
		private ProgressBarWindowView progressBarWindow;
		private Display display;
	    public TripleClassificationCallback(ProgressBarWindowView theProgressBarWindow, Display display) {
	        this.progressBarWindow = theProgressBarWindow;
	        this.display = display;
	    }
	 
	    public void onFailure(Throwable caught) {
	    	SC.warn(ErrorMessageHandler.getErrorMessage(caught));
	    	progressBarWindow.hide();
	    }
	 
		@Override
		public void onSuccess(SimpleTripleStore tripleStore) {
			display.fillBasicMappingInfo(tripleStore);
			progressBarWindow.hide();
		}
	}
	
	/**
	 * 
	 * @author kangyan2003
	 *
	 */
	class GetClassifiedTriplesDetailInfoCallback implements AsyncCallback<SimpleMappingDetailInfo> {
		 
	    private ProgressBarWindowView progressBarWindow;
	    private String webSiteURL;
	 
	    public GetClassifiedTriplesDetailInfoCallback(ProgressBarWindowView theProgressBarWindow, String webSiteURL) {
	        this.progressBarWindow = theProgressBarWindow;
	        this.webSiteURL = webSiteURL;
	    }
	 
	    public void onFailure(Throwable caught) {
	        progressBarWindow.hide();
	    	SC.warn(ErrorMessageHandler.getErrorMessage(caught));	
	    }
	 
		@Override
		public void onSuccess(SimpleMappingDetailInfo result) {
			ClassifiedTriplesEditWindowPresenter presenter = new ClassifiedTriplesEditWindowPresenter(this.webSiteURL, result, ontologyMatchingService);
			progressBarWindow.hide();
		}
	}
	
	
	

}
