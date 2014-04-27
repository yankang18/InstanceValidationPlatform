package umbc.ebiquity.kang.ontologypopulating.client.presenter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingServiceAsync;
import umbc.ebiquity.kang.ontologypopulating.client.datasource.InstanceGridDataSource;
import umbc.ebiquity.kang.ontologypopulating.client.datasource.OntoClassGridDataSource;
import umbc.ebiquity.kang.ontologypopulating.client.datasource.OntoInstanceDataSource;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstanceDetailEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstanceDetailEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstancesOfOntoClassEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.GetInstancesOfOntoClassEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEvent;
import umbc.ebiquity.kang.ontologypopulating.client.event.UpdatedInstancesSaveEventHandler;
import umbc.ebiquity.kang.ontologypopulating.client.ui.Concept2OntoClassTriple;
import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.client.view.ClassifiedTriplesEditWindowView;
import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleClassifiedInstanceInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.DataSource;

public class ClassifiedTriplesEditWindowPresenter {
	
	
	public interface Display {
		public Widget asWidget();
		public void addGetInstancesOfOntoClassEventHandler(GetInstancesOfOntoClassEventHandler handler);
		public void addGetInstanceDetailEventHandler(GetInstanceDetailEventHandler handler);
		public void addUpdatedInstancesSaveEventHandler(UpdatedInstancesSaveEventHandler handler);
//		public void setWebSiteURL(String webSiteURL); 
	}

	private OntologyMatchingServiceAsync ontologyMatchingService;
	/*
	 * This map as a lookup table that is used to look up instances of a
	 * specific onto-class
	 */
	private Map<Entity, Collection<SimpleClassifiedInstanceInfo>> ontoClass2InstancesMap;
	private Map<String, SimpleClassifiedInstanceInfo> classifiedInstanceMap;
	private Display display;
	private String webSiteURL;
	
	public ClassifiedTriplesEditWindowPresenter(String webSiteURL, SimpleMappingDetailInfo simpleMappingDetailInfo, OntologyMatchingServiceAsync ontologyMatchingService) {
		this.webSiteURL = webSiteURL;
		this.ontologyMatchingService = ontologyMatchingService;
		/*
		 * construct client-side data storage
		 */
		ontoClass2InstancesMap = simpleMappingDetailInfo.getOntoClass2InstancesMap();
		classifiedInstanceMap = new LinkedHashMap<String, SimpleClassifiedInstanceInfo>();
		for (Entity entity : ontoClass2InstancesMap.keySet()) {
			Collection<SimpleClassifiedInstanceInfo> instanceInfoes = ontoClass2InstancesMap.get(entity);
			for (SimpleClassifiedInstanceInfo instanceInfo : instanceInfoes) {
				classifiedInstanceMap.put(instanceInfo.getInstanceLabel(), instanceInfo);
			}
		}
		
//		OntoInstanceDataSource dataSource = new OntoInstanceDataSource();
//		dataSource.addData(simpleMappingDetailInfo);
//		display = new ClassifiedTriplesEditWindowView(dataSource);
		
		display = new ClassifiedTriplesEditWindowView(simpleMappingDetailInfo);
		bind();
	}
	
	private void bind(){
		
		display.addGetInstancesOfOntoClassEventHandler(new GetInstancesOfOntoClassEventHandler() {
			
			@Override
			public DataSource getInstances(GetInstancesOfOntoClassEvent event) {
				Entity ontoClass = new Entity(event.getOntoClassName(), event.getOntoClassURI());
				Collection<SimpleClassifiedInstanceInfo> instanceCollection = ontoClass2InstancesMap.get(ontoClass);
				InstanceGridDataSource datasource = new InstanceGridDataSource();
				datasource.addData(instanceCollection);
				return datasource;
			}
		});
		
		display.addGetInstanceDetailEventHandler(new GetInstanceDetailEventHandler() {
			
			@Override
			public SimpleClassifiedInstanceInfo getInstanceDetail(GetInstanceDetailEvent event) {
				return classifiedInstanceMap.get(event.getInstanceName());
			}
		});
		
		display.addUpdatedInstancesSaveEventHandler(new UpdatedInstancesSaveEventHandler() {
			
			@Override
			public void saveUpdatedInstances(UpdatedInstancesSaveEvent event) {
				Collection<InstanceRecord> allClassifiedInstanceRecords = event.getUpdatedInstanceRecords();
				Map<String, Concept2OntoClassTriple> updatedConcept2ClassMap = event.getUpdatedConcept2OntoClassMap();
				for (SimpleClassifiedInstanceInfo instanceInfo : classifiedInstanceMap.values()) {
//					System.out.println("11: " + instanceInfo.getInstanceLabel());
					InstanceRecord record = new InstanceRecord(instanceInfo.getInstanceLabel());
					if (!allClassifiedInstanceRecords.contains(record)) {
//						System.out.println("22: " + record.getInstanceLabel());
						/*
						 * if the instance does not exist in the updated-instance collection, going to record this
						 * instance.
						 */
						record.setOntoClassLabel(instanceInfo.getOntoClassLabel());
						allClassifiedInstanceRecords.add(record);
						for (Entity concept : instanceInfo.getRelatedConceptCollection()) {
							String conceptLabel = concept.getEntityLabel();
							if (updatedConcept2ClassMap.containsKey(conceptLabel)) {
								/*
								 * 
								 */
								Concept2OntoClassTriple updatedConcept2OntoClassMap = updatedConcept2ClassMap.get(conceptLabel);
								Concept2OntoClassTriple triple = new Concept2OntoClassTriple(updatedConcept2OntoClassMap);
								record.addConcept2OntoClassTriple(triple);
//								updatedInstanceRecords.add(record);
							} else {

								if (concept.isMappedConcept()) {
									/*
									 * if the concept is mapped to certain onto-class.
									 */
									String ontoClassLabel = concept.getMappedOntoClass().getEntityLabel();
									double similarity = concept.getMappingSimilarity();
									Concept2OntoClassTriple triple = new Concept2OntoClassTriple(conceptLabel, "relatedTo", ontoClassLabel);
									triple.setIsMappedConcept(true);
									triple.setIsDirectedMapping(concept.isDirectMapping());
									triple.setSimilarity(similarity);
									record.addConcept2OntoClassTriple(triple);
								}
								else {
									Concept2OntoClassTriple triple = new Concept2OntoClassTriple(conceptLabel, "relatedTo", "");
									triple.setIsMappedConcept(false);
									triple.setIsDirectedMapping(concept.isDirectMapping());
									triple.setSimilarity(0.0);
									record.addConcept2OntoClassTriple(triple);
								}
							}
						}
					}
				}
				
				UpdatedClassifiedInstanceInformationZoomInWindowPresenter presenter = new UpdatedClassifiedInstanceInformationZoomInWindowPresenter(webSiteURL, allClassifiedInstanceRecords, ontologyMatchingService);
				presenter.show();
			}

		});
	}

}
