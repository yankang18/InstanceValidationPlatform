package umbc.ebiquity.kang.ontologypopulating.client;

import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleOntClassInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTripleStore;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OntologyMatchingServiceAsync {

	void extractTripleStore(String webPageUrl,
			AsyncCallback<SimpleTripleStore> callback);

	void listWebSiteHomeUrls(AsyncCallback<Collection<String>> callback);

	void getMappingBasicInfo(String webPageUrl,
			AsyncCallback<SimpleTripleStore> callback);

	void getMappingDetailInfo(String webPageUrl,
			AsyncCallback<SimpleMappingDetailInfo> callback);

	void saveClassifiedInstances(String webSiteURL, Collection<InstanceRecord> instanceRecord, 
			AsyncCallback<Boolean> callback);

	void getSimpleOntClassInfoCollection(AsyncCallback<Collection<SimpleOntClassInfo>> callback);

}
