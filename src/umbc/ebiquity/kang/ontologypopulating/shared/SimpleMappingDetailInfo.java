package umbc.ebiquity.kang.ontologypopulating.shared;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleMappingDetailInfo implements IsSerializable{
	
	private String webSiteUrl;
	private String localStorageName;
	
	private Collection<SimpleClassifiedInstanceInfo> classifiedInstanceInfoCollection;
	private Map<Entity, Collection<SimpleClassifiedInstanceInfo>> ontoClass2InstancesMap;
	
	public SimpleMappingDetailInfo(){
		classifiedInstanceInfoCollection = new ArrayList<SimpleClassifiedInstanceInfo>();
		ontoClass2InstancesMap = new HashMap<Entity, Collection<SimpleClassifiedInstanceInfo>>();
	}
	
	public void setOntoClass2InstancesMap(Map<Entity, Collection<SimpleClassifiedInstanceInfo>> ontoClass2InstancesMap) {
		this.ontoClass2InstancesMap = ontoClass2InstancesMap;
	}
	public Map<Entity, Collection<SimpleClassifiedInstanceInfo>> getOntoClass2InstancesMap() {
		return ontoClass2InstancesMap;
	}
	public void setLocalStorageName(String localStorageName) {
		this.localStorageName = localStorageName;
	}
	public String getLocalStorageName() {
		return localStorageName;
	}
	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	public String getWebSiteUrl() {
		return webSiteUrl;
	}
	public void setClassifiedInstanceInfoCollection(
			Collection<SimpleClassifiedInstanceInfo> classifiedInstanceInfoCollection) {
		this.classifiedInstanceInfoCollection = classifiedInstanceInfoCollection;
	}
	public Collection<SimpleClassifiedInstanceInfo> getClassifiedInstanceInfoCollection() {
		return classifiedInstanceInfoCollection;
	}
	
	

}
