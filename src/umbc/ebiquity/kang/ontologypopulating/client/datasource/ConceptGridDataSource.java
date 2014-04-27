package umbc.ebiquity.kang.ontologypopulating.client.datasource;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import umbc.ebiquity.kang.ontologypopulating.client.ui.Concept2OntoClassTriple;
import umbc.ebiquity.kang.ontologypopulating.shared.Entity;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ConceptGridDataSource extends DataSource {
	
	/**
	 * field names
	 */
	public final static String CONCEPT = "Concept";
	public final static String RELATION = "Relation";
	public final static String UPDATED_ONTOCLASS = "UpdatedOntoClass";
	public final static String SIMILARITY = "Similarity";
	public final static String IS_DIRECTED_MAPPING = "DirectedMapping";
	public final static String ORIGINAL_ONTOCLASS = "OriginalOntoClass";

	public ConceptGridDataSource(LinkedHashMap<String, String> recommendedClasses) {

		DataSourceTextField conceptNameField = new DataSourceTextField(CONCEPT, "Concept");
		conceptNameField.setRequired(true);
		conceptNameField.setPrimaryKey(true);
		conceptNameField.setCanEdit(false);

		DataSourceTextField relationField = new DataSourceTextField(RELATION,"Relation", 20);
		relationField.setValueMap("relatedTo", "borderThan", "narrowerThan");
		
		DataSourceTextField relatedClassField = new DataSourceTextField(UPDATED_ONTOCLASS, "Class", 30);
		relatedClassField.setValueMap(recommendedClasses);
		
		DataSourceFloatField similarityField = new DataSourceFloatField(SIMILARITY, "Similarity", 30);
		similarityField.setHidden(true);
		
		DataSourceTextField originalClassField = new DataSourceTextField(ORIGINAL_ONTOCLASS, "OriginalOntoClass", 30);
//		originalClassField.setHidden(true);
		originalClassField.setCanEdit(false);
		
		DataSourceBooleanField directedMappingField = new DataSourceBooleanField(IS_DIRECTED_MAPPING, "DirectedMapping", 30);
//		directedMappingField.setHidden(true);
		directedMappingField.setCanEdit(false);
		
		setFields(conceptNameField, relationField, relatedClassField, directedMappingField, similarityField, originalClassField);
		setClientOnly(true);
	}
	
	public void addData(Collection<Entity> Concepts, String classLabel, Map<String, Concept2OntoClassTriple> existingMappedConcepts) {

		int numOfConcepts = Concepts.size();
		if (numOfConcepts > 0) {
			for (Entity concept : Concepts) {
				ListGridRecord record = new ListGridRecord();
				String conceptLabel = concept.getEntityLabel();
				if (existingMappedConcepts.containsKey(conceptLabel)) {
					Concept2OntoClassTriple triple = existingMappedConcepts.get(conceptLabel);
					record.setAttribute(CONCEPT, conceptLabel);
					record.setAttribute(RELATION, triple.getRelationLabel());
					record.setAttribute(UPDATED_ONTOCLASS, triple.getUpdatedOntoClassLabel());
					record.setAttribute(SIMILARITY, triple.getSimilarity());
					record.setAttribute(IS_DIRECTED_MAPPING, triple.isDirectedMapping());
					record.setAttribute(ORIGINAL_ONTOCLASS, triple.getOriginalOntoClassLabel());
				}
				else {
					record.setAttribute(CONCEPT, conceptLabel);
					record.setAttribute(RELATION, "relatedTo");
					if (concept.isMappedConcept()) {
						record.setAttribute(UPDATED_ONTOCLASS, concept.getMappedOntoClass().getEntityLabel());
						record.setAttribute(ORIGINAL_ONTOCLASS, concept.getMappedOntoClass().getEntityLabel());
						record.setAttribute(SIMILARITY, concept.getMappingSimilarity());
						record.setAttribute(IS_DIRECTED_MAPPING, concept.isDirectMapping());
					}
					else {
						record.setAttribute(UPDATED_ONTOCLASS, "None");
						record.setAttribute(ORIGINAL_ONTOCLASS, "None");
						record.setAttribute(SIMILARITY, 0.0);
						record.setAttribute(IS_DIRECTED_MAPPING, false);
					}
				}
				this.addData(record);
			}
		}
	}
	
	public void addData(Collection<Concept2OntoClassTriple> conceptTriples, Map<String, Concept2OntoClassTriple> existingMappedConcepts) {
		
		int numOfConcepts = conceptTriples.size();
		if (numOfConcepts > 0) {
			for (Concept2OntoClassTriple conceptTriple : conceptTriples) {
				ListGridRecord record = new ListGridRecord();
				if (existingMappedConcepts.containsKey(conceptTriple.getConceptLabel())) {
					/*
					 * 
					 */
					String conceptLabel = conceptTriple.getConceptLabel();
					Concept2OntoClassTriple triple = existingMappedConcepts.get(conceptLabel);
					record.setAttribute(CONCEPT, triple.getConceptLabel());
					record.setAttribute(RELATION, triple.getRelationLabel());
					record.setAttribute(ORIGINAL_ONTOCLASS, triple.getOriginalOntoClassLabel());
					record.setAttribute(UPDATED_ONTOCLASS, triple.getUpdatedOntoClassLabel());
					record.setAttribute(SIMILARITY, triple.getSimilarity());
					record.setAttribute(IS_DIRECTED_MAPPING, triple.isDirectedMapping());

				} else {
					record.setAttribute(CONCEPT, conceptTriple.getConceptLabel());
					record.setAttribute(RELATION, conceptTriple.getRelationLabel());
					record.setAttribute(ORIGINAL_ONTOCLASS, conceptTriple.getOriginalOntoClassLabel());
					record.setAttribute(UPDATED_ONTOCLASS, conceptTriple.getUpdatedOntoClassLabel());
					record.setAttribute(SIMILARITY, conceptTriple.getSimilarity());
					record.setAttribute(IS_DIRECTED_MAPPING, conceptTriple.isDirectedMapping());
				}
				this.addData(record);
			}
		}
	}

}
