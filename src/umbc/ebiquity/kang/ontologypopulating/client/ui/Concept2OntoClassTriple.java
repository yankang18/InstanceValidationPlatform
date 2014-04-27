package umbc.ebiquity.kang.ontologypopulating.client.ui;

import umbc.ebiquity.kang.ontologyinitializator.mappingframework.SimilarityAlgorithm;
import umbc.ebiquity.kang.ontologyinitializator.mappingframework.SimilarityAlgorithm.SimilarityType;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Concept2OntoClassTriple implements IsSerializable {

	private String concept;
	private String relation;
	private String updatedClass;
	private String originalClass;
	private double similarity;
	private boolean isMappedConcept = false;
	private boolean isDirectedMapping = true;
	
	public Concept2OntoClassTriple(){}
	public Concept2OntoClassTriple(String concept, String relation, String ontoClass) {
		this.concept = concept;
		this.relation = relation;
		this.originalClass = ontoClass;
		this.updatedClass = ontoClass;
	}
	
	public Concept2OntoClassTriple(Concept2OntoClassTriple updatedConcept2OntoClassMap) {
		this.concept = updatedConcept2OntoClassMap.getConceptLabel();
		this.relation = updatedConcept2OntoClassMap.getRelationLabel();
		this.originalClass = updatedConcept2OntoClassMap.getOriginalOntoClassLabel();
		this.updatedClass = updatedConcept2OntoClassMap.getUpdatedOntoClassLabel();
		this.isMappedConcept = updatedConcept2OntoClassMap.isMappedConcept();
		this.isDirectedMapping = updatedConcept2OntoClassMap.isDirectedMapping();
		this.similarity = updatedConcept2OntoClassMap.getSimilarity();
	}
	
	public void setRelationLabel(String relationLabel){
		this.relation = relationLabel;
	}
	
	public void updateOntoClassLabel(String ontoClassLabel){
		this.updatedClass = ontoClassLabel;
	}

	public String getConceptLabel() {
		return this.concept;
	}
	
	public String getRelationLabel(){
		return this.relation;
	}
	
	public String getUpdatedOntoClassLabel(){
		return this.updatedClass;
	}
	
	public String getOriginalOntoClassLabel(){
		return this.originalClass;
	}
	
	public void setIsMappedConcept(boolean isMappedConcept){
		this.isMappedConcept = isMappedConcept;
	}

	public void setIsDirectedMapping(boolean isDirectedMapping) {
		this.isDirectedMapping = isDirectedMapping;
	}
	
	public boolean isMappedConcept(){
		return this.isMappedConcept;
	}

	public boolean isDirectedMapping(){
		return this.isDirectedMapping;
	}
	
	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	public double getSimilarity() {
		return similarity;
	}
	
	public boolean isMappedOntoClassChanged(){
		return !this.originalClass.equals(updatedClass);
	}
	
	public void clone(Concept2OntoClassTriple updatedTriple) {
		this.relation = updatedTriple.getRelationLabel();
		this.updatedClass = updatedTriple.getUpdatedOntoClassLabel();
		this.originalClass = updatedTriple.getOriginalOntoClassLabel();
		this.similarity = updatedTriple.getSimilarity();
		this.isMappedConcept = updatedTriple.isMappedConcept();
		this.isDirectedMapping = updatedTriple.isDirectedMapping();
	}
	
	@Override
	public String toString(){
		return "<" + concept + "  " + relation + "  " + updatedClass + ">";
	}

}
