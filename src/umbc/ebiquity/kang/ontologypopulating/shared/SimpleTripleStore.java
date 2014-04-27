package umbc.ebiquity.kang.ontologypopulating.shared;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleTripleStore implements IsSerializable{
	
	private String webSiteUrl;
	private String localStorageName;
	private Map<Entity, Collection<SimpleTriple>> triplesOfCustomRelation;
	private Map<Entity, Collection<SimpleTriple>> triplesOfInstance2ConceptRelation;
	private Map<Entity, Collection<SimpleTriple>> ontoClass2InstanceMap;
	private Map<Entity, Collection<SimpleTriple>> property2RelationMap;
	private Set<SimpleTriple> relation2TypeTriple;;
	
	
	public SimpleTripleStore(){
		triplesOfCustomRelation = new HashMap<Entity, Collection<SimpleTriple>>();
		triplesOfInstance2ConceptRelation = new HashMap<Entity, Collection<SimpleTriple>>();
		ontoClass2InstanceMap = new HashMap<Entity, Collection<SimpleTriple>>();
		property2RelationMap = new HashMap<Entity, Collection<SimpleTriple>>();
		relation2TypeTriple = new HashSet<SimpleTriple>();
	}
	
	public void setTriplesOfCustomRelation(Map<Entity, Collection<SimpleTriple>> triplesOfCustomRelation) {
		this.triplesOfCustomRelation = triplesOfCustomRelation;
	}
	public Map<Entity, Collection<SimpleTriple>> getTriplesOfCustomRelation() {
		return triplesOfCustomRelation;
	}
	public void setTriplesOfInstance2ConceptRelation(
			Map<Entity, Collection<SimpleTriple>> triplesOfInstance2ConceptRelation) {
		this.triplesOfInstance2ConceptRelation = triplesOfInstance2ConceptRelation;
	}
	public Map<Entity, Collection<SimpleTriple>> getTriplesOfInstance2ConceptRelation() {
		return triplesOfInstance2ConceptRelation;
	}
	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}
	public String getWebSiteUrl() {
		return webSiteUrl;
	}
	public void setLocalStorageName(String localStorageName) {
		this.localStorageName = localStorageName;
	}
	public String getLocalStorageName() {
		return localStorageName;
	}
	public void setOntoClass2InstanceMap(Map<Entity, Collection<SimpleTriple>> ontoClass2InstanceMap) {
		this.ontoClass2InstanceMap = ontoClass2InstanceMap;
	}
	public Map<Entity, Collection<SimpleTriple>> getOntoClass2InstanceMap() {
		return ontoClass2InstanceMap;
	}
	public void setProperty2RelationMap(Map<Entity, Collection<SimpleTriple>> property2RelationMap) {
		this.property2RelationMap = property2RelationMap;
	}
	public Map<Entity, Collection<SimpleTriple>> getProperty2RelationMap() {
		return property2RelationMap;
	}
	
	public void setRelation2TypeMap(Collection<SimpleTriple> relation2TypeTriple) {
		this.relation2TypeTriple.addAll(relation2TypeTriple);
	}

	public Set<SimpleTriple> getRelation2TypeSimpleTriple(){
		return relation2TypeTriple;
	}

}
