package umbc.ebiquity.kang.ontologypopulating.client.ui;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;

public class InstanceRecord implements IsSerializable{
	
	private String instanceLabel;
	private String ontoClassLabel;
	private Collection<Concept2OntoClassTriple> concept2OntoClassTriples;
	
	public InstanceRecord(){}
	public InstanceRecord(String instanceLabel){
		this.instanceLabel = instanceLabel;
		this.concept2OntoClassTriples = new ArrayList<Concept2OntoClassTriple>();
	}
	public void setInstanceLabel(String originalInstanceLabel) {
		this.instanceLabel = originalInstanceLabel;
		concept2OntoClassTriples.clear();
	}
	public String getInstanceLabel() {
		return instanceLabel;
	}
	
	public void clearRelatedConceptList(){
		this.concept2OntoClassTriples.clear();
	}
	
	public void setConcept2OntoClassTripleCollections(Collection<Concept2OntoClassTriple> concept2OntoClassTriples){
		this.concept2OntoClassTriples = concept2OntoClassTriples;
	}
	
	public void addConcept2OntoClassTriple(Concept2OntoClassTriple triple){
		this.concept2OntoClassTriples.add(triple);
	}
	
	public Collection<Concept2OntoClassTriple> getRelatedConcepts(){
		return concept2OntoClassTriples;
	}
	public void setOntoClassLabel(String originalOntoClassLabel) {
		this.ontoClassLabel = originalOntoClassLabel;
	}
	public String getOntoClassLabel() {
		return ontoClassLabel;
	}
	
	@Override
	public String toString() {
		return this.instanceLabel;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj)
			return false;
		if (this == obj)
			return true;
		InstanceRecord instance = (InstanceRecord) obj;
		return this.toString().equals(instance.toString());
	}

}
