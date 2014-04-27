package umbc.ebiquity.kang.ontologypopulating.client.view;

import java.util.ArrayList;
import java.util.Collection;
import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingService;
import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingServiceAsync;
import umbc.ebiquity.kang.ontologypopulating.client.ui.MultipleValueReceiver;
import umbc.ebiquity.kang.ontologypopulating.client.ui.SingleValueReceiver;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleOntClassInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Window;

public abstract class OntClassChosingWindowView extends Window {

	private final OntologyMatchingServiceAsync ontologyMatchingService = GWT.create(OntologyMatchingService.class);

	public OntClassChosingWindowView() {
		super();
		this.setWidth("60%");
		this.setHeight("60%");
		this.setAutoCenter(true);
		this.setShowModalMask(true);
		this.setIsModal(true);
		this.setShowHeader(false);
		this.addItem(this.createEmptyView());
		this.singleValueReceivers = new ArrayList<SingleValueReceiver>();
		this.multipleValueReceivers = new ArrayList<MultipleValueReceiver>();
		ontologyMatchingService.getSimpleOntClassInfoCollection(new AsyncCallback<Collection<SimpleOntClassInfo>>() {
			@Override
			public void onSuccess(Collection<SimpleOntClassInfo> result) {
				populateView(result);
			}
			@Override
			public void onFailure(Throwable caught) {
				SC.say("Error occured when get Classes");
			}
		});
	}
	
	protected Collection<SingleValueReceiver> singleValueReceivers;
	protected Collection<MultipleValueReceiver> multipleValueReceivers;
	
	public void singleValueReceiverRegister(SingleValueReceiver singleValueReceiver){
		singleValueReceivers.add(singleValueReceiver);
	}
	
	public void multipleValueReceiverRegister(MultipleValueReceiver multipleValueReceiver){
		multipleValueReceivers.add(multipleValueReceiver);
	}
	
	
	protected abstract Canvas createEmptyView();
	
	protected abstract void populateView(Collection<SimpleOntClassInfo> data);
	
	
}
