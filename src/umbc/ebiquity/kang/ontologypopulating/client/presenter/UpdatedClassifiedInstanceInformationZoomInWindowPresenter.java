package umbc.ebiquity.kang.ontologypopulating.client.presenter;

import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingServiceAsync;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.client.view.UpdatedClassifiedInstanceInformationZoomInWindowView;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.util.SC;

public class UpdatedClassifiedInstanceInformationZoomInWindowPresenter {
	
	public interface Display {
		public Widget asWidget();
		void addSaveUpdatedClassifiedInstanceInformationEventHandler(UpdatedInstancesSaveEventHandler handler);
		public void showWindow();
		public void closeWindow();
	}
	
	private OntologyMatchingServiceAsync ontologyMatchingService;
	private Display display;
	private String webSiteURL;
	
	public UpdatedClassifiedInstanceInformationZoomInWindowPresenter(String webSiteURL, Collection<InstanceRecord> instanceRecords, OntologyMatchingServiceAsync ontologyMatchingService){
		this.ontologyMatchingService = ontologyMatchingService;
		display = new UpdatedClassifiedInstanceInformationZoomInWindowView(instanceRecords);
		this.webSiteURL = webSiteURL;
		this.bind();
	}

	private void bind() {
		display.addSaveUpdatedClassifiedInstanceInformationEventHandler(new UpdatedInstancesSaveEventHandler() {

			@Override
			public void saveUpdatedInstances(UpdatedInstancesSaveEvent event) {
				if(ontologyMatchingService != null){
					System.out.println("HERE1");
				} else {
					System.out.println("HERE2");
				}
				ontologyMatchingService.saveClassifiedInstances(webSiteURL, event.getUpdatedInstanceRecords(),
				                new AsyncCallback<Boolean>() {

					                @Override
					                public void onSuccess(Boolean result) {
						                if (result) {
							                SC.say("Save Succeed!");
						                }
						                else {
							                SC.say("Save Failed!");

						                }
						                display.closeWindow();

					                }

					                @Override
					                public void onFailure(Throwable caught) {
						                SC.say("Save Failed:" + caught.getMessage());
						                display.closeWindow();
					                }
				                });
			}
		});
	}
	
	public void show(){
		display.showWindow();
	}

}
