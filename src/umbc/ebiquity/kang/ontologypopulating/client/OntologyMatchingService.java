package umbc.ebiquity.kang.ontologypopulating.client;

import java.io.IOException;
import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleOntClassInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTripleStore;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ontologymatching")
public interface OntologyMatchingService extends RemoteService {
	
    public Collection<String> listWebSiteHomeUrls();
    
	public SimpleTripleStore extractTripleStore(String webPageUrl) throws IOException; 

	public SimpleTripleStore getMappingBasicInfo(String webPageUrl);

	public SimpleMappingDetailInfo getMappingDetailInfo(String webPageUrl);

	boolean saveClassifiedInstances(String webSiteURL, Collection<InstanceRecord> instanceRecord);

	Collection<SimpleOntClassInfo> getSimpleOntClassInfoCollection();

}
