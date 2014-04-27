package umbc.ebiquity.kang.ontologypopulating.client.ontology;

public class SimpleTriple {
	
	private String subject;
	private OntClassRelation relation;
	private String object;
	
	public SimpleTriple(String subject, OntClassRelation relation, String object){
		this.subject = subject;
		this.relation = relation;
		this.object = object;
	}

	public String getTripleSubject() {
		return subject;
	}

	public OntClassRelation getOntClassRelation() {
		return relation;
	}

	public String getTripleObject() {
		return object;
	}

}
