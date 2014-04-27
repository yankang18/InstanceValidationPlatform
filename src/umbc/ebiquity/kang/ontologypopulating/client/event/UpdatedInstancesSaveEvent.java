package umbc.ebiquity.kang.ontologypopulating.client.event;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;

import umbc.ebiquity.kang.ontologypopulating.client.ui.Concept2OntoClassTriple;
import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;

public class UpdatedInstancesSaveEvent {
	
	private Collection<InstanceRecord> instanceRecords;
	private Map<String, Concept2OntoClassTriple> updatedConcept2ClassMap;
	
	public UpdatedInstancesSaveEvent(){
		instanceRecords = new LinkedHashSet<InstanceRecord>();
	}
	
	public Collection<InstanceRecord> getUpdatedInstanceRecords(){
		return this.instanceRecords;
	}
	
	public void addUpdatedInstanceRecord(InstanceRecord instanceRecord){
		instanceRecords.add(instanceRecord);
	}
	
	public void setUpdatedInstanceRecords(Collection<InstanceRecord> instanceRecords){
		this.instanceRecords.addAll(instanceRecords);
	}

	public void addUpdatedConcept2OntoClassMap(Map<String, Concept2OntoClassTriple> updatedConcept2ClassMap) {
		this.updatedConcept2ClassMap = updatedConcept2ClassMap;
	}
	
    public Map<String, Concept2OntoClassTriple> getUpdatedConcept2OntoClassMap(){
    	return this.updatedConcept2ClassMap;
    }
	
}
