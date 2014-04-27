package umbc.ebiquity.kang.ontologypopulating.shared;

import java.util.Collection;
import java.util.Map;

//import umbc.csee.ebiquity.ontologymatcher.algorithm.component.OntPropertyInfo;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleClassifiedInstanceInfo implements IsSerializable {
	
	private Entity instance;
	private Entity ontoClass;
	private String similarity;
	private Map<Entity, Collection<String>> property2ValuesMap;
	private Collection<Entity> recommendedLv1OntoClassCollection;
	private Collection<Entity> recommendedLv2OntoClassCollection;
	private Collection<Entity> relatedConceptCollection;
	
	public SimpleClassifiedInstanceInfo() {}
	public SimpleClassifiedInstanceInfo(Entity instance, Entity ontoClass, String similarity){
		this.instance = instance;
		this.ontoClass = ontoClass;
		this.similarity = similarity;
	}
	
	public void setRecommendedLv1OntoClasses(Collection<Entity> ontoClasses){
		this.recommendedLv1OntoClassCollection = ontoClasses;
	}
	public void setRecommendedLv2OntoClasses(Collection<Entity> ontoClasses){
		this.recommendedLv2OntoClassCollection = ontoClasses;
	}
	public void setRelatedConceptCollection(Collection<Entity> relatedConceptCollection) {
		this.relatedConceptCollection = relatedConceptCollection;
	}
	public Collection<Entity> getRecommendedLv1OntoClasses(){
		return this.recommendedLv1OntoClassCollection;
	}
	public Collection<Entity> getRecommendedLv2OntoClasses(){
		return this.recommendedLv2OntoClassCollection;
	}
	public Collection<Entity> getRelatedConceptCollection() {
		return relatedConceptCollection;
	}
	public String getSimilarity() {
		return similarity;
	}
	public Entity getOntoClass() {
		return ontoClass;
	}
	public String getOntoClassLabel(){
		return this.ontoClass.getEntityLabel();
	}
	public Entity getInstance() {
		return instance;
	}
	public String getInstanceLabel(){
		return instance.getEntityLabel();
	}
	public void setProperty2ValuesMap(Map<Entity, Collection<String>> property2ValuesMap) {
		this.property2ValuesMap = property2ValuesMap;
	}
	public Map<Entity, Collection<String>> getProperty2ValuesMap() {
		return property2ValuesMap;
	}
	
//	@Override
//	public int hashCode() {
//		return this.instance.getEntityLabel().hashCode();
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		SimpleClassifiedInstanceInfo instanceInfo = (SimpleClassifiedInstanceInfo) obj;
//		return this.instance.getEntityLabel().equals(instanceInfo.getInstance().getEntityLabel());
//	}
}
