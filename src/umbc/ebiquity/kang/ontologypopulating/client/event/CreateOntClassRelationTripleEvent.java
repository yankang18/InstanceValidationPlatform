package umbc.ebiquity.kang.ontologypopulating.client.event;

import umbc.ebiquity.kang.ontologypopulating.client.ontology.SimpleTriple;

public class CreateOntClassRelationTripleEvent {
	
	private SimpleTriple simpleTriple;
	public CreateOntClassRelationTripleEvent(SimpleTriple triple){
		this.simpleTriple = triple;
	}
	public SimpleTriple getSimpleTriple() {
		return simpleTriple;
	}

}
