package umbc.ebiquity.kang.ontologypopulating.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import umbc.ebiquity.kang.ontologyinitializator.entityframework.component.Concept;
import umbc.ebiquity.kang.ontologyinitializator.ontology.OntoClassInfo;
import umbc.ebiquity.kang.ontologyinitializator.ontology.Triple;
import umbc.ebiquity.kang.ontologyinitializator.repository.MappingBasicInfo;
import umbc.ebiquity.kang.ontologyinitializator.repository.MappingDetailInfo;
import umbc.ebiquity.kang.ontologyinitializator.repository.MappingInfoSchemaParameter.MappingRelationType;
import umbc.ebiquity.kang.ontologyinitializator.repository.RepositoryParameterConfiguration;
import umbc.ebiquity.kang.ontologyinitializator.repository.factories.ClassifiedInstancesRepositoryFactory;
import umbc.ebiquity.kang.ontologyinitializator.repository.factories.InterpretationCorrectionRepositoryFactory;
import umbc.ebiquity.kang.ontologyinitializator.repository.factories.ManufacturingLexicalMappingRepositoryFactory;
import umbc.ebiquity.kang.ontologyinitializator.repository.factories.OntologyRepositoryFactory;
import umbc.ebiquity.kang.ontologyinitializator.repository.factories.TripleRepositoryFactory;
import umbc.ebiquity.kang.ontologyinitializator.repository.impl.MappingDataGateway;
import umbc.ebiquity.kang.ontologyinitializator.repository.impl.ProprietoryClassifiedInstancesRepository;
import umbc.ebiquity.kang.ontologyinitializator.repository.impl.ProprietoryClassifiedInstancesRepository.ClassifiedInstancesRepositoryType;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IClassificationCorrectionRepository;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IClassifiedInstanceBasicRecord;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IClassifiedInstanceDetailRecord;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IConcept2OntClassMapping;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IManufacturingLexicalMappingRecordsReader;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IManufacturingLexicalMappingRepository;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IMatchedOntProperty;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IOntologyRepository;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IClassifiedInstancesRepository;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.ITripleRepository;
import umbc.ebiquity.kang.ontologyinitializator.repository.interfaces.IInstanceRecord;
import umbc.ebiquity.kang.ontologyinitializator.utilities.FileUtility;
import umbc.ebiquity.kang.ontologypopulating.client.OntologyMatchingService;
import umbc.ebiquity.kang.ontologypopulating.client.ui.Concept2OntoClassTriple;
import umbc.ebiquity.kang.ontologypopulating.client.ui.InstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.client.ui.UpdatedInstanceRecord;
import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleClassifiedInstanceInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleOntClassInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTriple;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleTripleStore;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class OntologyMatchingServiceImpl extends RemoteServiceServlet implements OntologyMatchingService {
	
	
	public static void main(String[] args) throws IOException {
		
//		double sim = 0.88888;
//		System.out.println(roundSimilarity(sim));
		OntologyMatchingServiceImpl kk = new OntologyMatchingServiceImpl();
//		ExtractedKnowledgeBase kb = new ExtractedKnowledgeBase();
//		kb.loadTriples("bassettinc");
//		PrefinedOntology PO = new PrefinedOntology();

//		KB2OntoMatchingAlgorithm alg = new KB2OntoMatchingAlgorithm(kb, PO);
//		Collection<MatchedTripleGroupAndOntClassPair> pairs = alg.identifyOntClassesForSubjectsOfTripleGroups();
//		Collection<MatchedTerm2OntClassInfo> MatchedTerm2OntClassInfoCollection = new ArrayList<MatchedTerm2OntClassInfo>();
//		for (MatchedTripleGroupAndOntClassPair pair : pairs) {
//			
//			String termLabel = pair.getTermLabel();
//			String ontClassLabel = pair.getOntClassLabel();
//			double similarity = pair.getSimilarity();
//			System.out.println(termLabel + "  -  " + ontClassLabel + "  -  " + similarity);
//			MatchedTerm2OntClassInfo matchedInfo = new MatchedTerm2OntClassInfo(termLabel, ontClassLabel, similarity);
//			matchedInfo.addRelatedTerms(pair.getRelatedTerms());
//			matchedInfo.addSuperOntClassesInPath(pair.getSuperOntClassInPath());
//			
//			Collection<MatchedPredicate2PropertyInfo> matchedPredicate2PropertyInfoCollection = new ArrayList<MatchedPredicate2PropertyInfo>();
//			for(MatchedPredicate2OntPropertyPair matchedPredicate2OntPropertyPair : pair.getMatchedOntPropertyPairs()){
//				
//				String predicateLabel = matchedPredicate2OntPropertyPair.getPredicateLabel();
//				String propertyLabel = matchedPredicate2OntPropertyPair.getOntPropertyLabel();
//				double sim = matchedPredicate2OntPropertyPair.getSimilarity();
//				MatchedPredicate2PropertyInfo matchedPredicate2PropertyInfo = new MatchedPredicate2PropertyInfo(predicateLabel, propertyLabel, sim);
//				matchedPredicate2PropertyInfoCollection.add(matchedPredicate2PropertyInfo);
//			}
//			matchedInfo.addMatchedPredicate2PropertyInfoCollection(matchedPredicate2PropertyInfoCollection);
//			MatchedTerm2OntClassInfoCollection.add(matchedInfo);
//
//			System.out.println(pair.getTermLabel() + " - "
//					+ pair.getOntClassLabel() + " - sim: "
//					+ pair.getSimilarity());
//			
//			for(String ontClassStr : matchedInfo.getSuperOntClassesInPath()){
//				System.out.println(" -> " + ontClassStr);
//			}
//		}
		
		/*
		 *
		 */
//		System.out.println();
//		System.out.println("############### Extract Triples ######################");
//		String webPageUrl1 = "http://www.numericalconcepts.com/";
//		SimpleTripleStore tripleStore1 = kk.extractTripleStore(webPageUrl1);
//		Collection<String> tripleStrings1 = new ArrayList<String>();
//		kk.recordTriplesGroupBySuject(tripleStrings1, tripleStore1.getTriplesOfCustomRelation());
//		kk.recordTriplesGroupBySuject(tripleStrings1, tripleStore1.getTriplesOfInstance2ConceptRelation());
//		for(String tripleString: tripleStrings1){
//			System.out.println(tripleString);
//		}
		
		/*
		 * 
		 */
//		System.out.println();
//		System.out.println("############### Get Basic Mapping Info ######################");
//		String webPageUrl2 = "http://www.numericalconcepts.com/";
//		SimpleTripleStore tripleStore = kk.getMappingBasicInfo(webPageUrl2);
//		Collection<String> tripleStrings = new ArrayList<String>();
//		kk.recordTriplesGroupBySuject(tripleStrings, tripleStore.getOntoClass2InstanceMap());
//		kk.recordTriplesGroupBySuject(tripleStrings, tripleStore.getProperty2RelationMap());
//		for(String tripleString: tripleStrings){
//			System.out.println(tripleString);
//		}
		
		/*
		 * 
		 */
		System.out.println();
		System.out.println("############### Get Detail Mapping Info ######################");
		String webPageUrl3 = "http://www.numericalconcepts.com/";
		SimpleMappingDetailInfo simpleMappingDetailInfo = kk.getMappingDetailInfo(webPageUrl3);
		
		Map<Entity, Collection<SimpleClassifiedInstanceInfo>> ontoClass2InstancesMap = simpleMappingDetailInfo.getOntoClass2InstancesMap();
		for(Entity ontoClass : ontoClass2InstancesMap.keySet()){
			System.out.println("Class: " + ontoClass.getEntityLabel());
			for(SimpleClassifiedInstanceInfo instanceInfo : ontoClass2InstancesMap.get(ontoClass)){
				String instanceLabel = instanceInfo.getInstance().getEntityLabel();
				System.out.println("    instance: " + instanceLabel);
				String similarity = instanceInfo.getSimilarity();
				System.out.println("    similarity: " + similarity);
				kk.printCollection(instanceInfo.getRecommendedLv1OntoClasses(),"RCLv1");
				kk.printCollection(instanceInfo.getRecommendedLv2OntoClasses(),"RCLv2");
				kk.printCollection(instanceInfo.getRelatedConceptCollection(),"Concept");
				instanceInfo.getProperty2ValuesMap();
				
			}
		}
	}

	private void printCollection(Collection<Entity> entities, String task) {
		for (Entity entity : entities) {
			System.out.println("        " + task + ": " + entity.getEntityLabel());
		}
	}

	/**
	 * for TEST !!
	 * 
	 * @param tripleStrings
	 * @param triplesGroupBySubject
	 */
	private void recordTriplesGroupBySuject(Collection<String> tripleStrings, Map<Entity, Collection<SimpleTriple>> triplesGroupBySubject) {
		for (Entity entity : triplesGroupBySubject.keySet()) {
			String entityLable = entity.getEntityLabel();
			tripleStrings.add("<" + entityLable + ">");
			int entityLabelLength = entityLable.length() + 2;
			for (SimpleTriple triple : triplesGroupBySubject.get(entity)) {
				StringBuilder sb = new StringBuilder();
				for(int i=0;i<entityLabelLength;i++){
					sb.append(" ");
				}
				tripleStrings.add(sb.toString() + "<"
						+ triple.getSubject().getEntityLabel() + "> <"
						+ triple.getPredicate().getEntityLabel() + "> <"
						+ triple.getObject().getEntityLabel() + ">");
			}
		}
	}
	
	private void initParameter(){
		String host = "/Users/yankang/Desktop/";
		RepositoryParameterConfiguration.REPOSITORIES_DIRECTORY_FULL_PATH = "/Users/yankang/Desktop/";
		RepositoryParameterConfiguration.ONTOLOGY_OWL_FILE_FULL_PATH = "/Users/yankang/Desktop/Ontologies/MSDL-Fullv2.owl";
		
		RepositoryParameterConfiguration.CLASSIFIED_INSTANCE_HOST_DIRECTORY = host;
		RepositoryParameterConfiguration.MANUFACTUIRNG_LEXICON_HOST_DIRECTORY = host;
		RepositoryParameterConfiguration.CLASSIFICATION_CORRECTION_HOST_DIRECTORY = host;
	}
	
	@Override
	public SimpleTripleStore extractTripleStore(String webPageUrl) throws IOException {
		this.initParameter();
		URL webPageURL = new URL(webPageUrl);
		ITripleRepository extractedTripleStore = TripleRepositoryFactory.createTripleRepository(webPageURL);
		
		SimpleTripleStore simpleTripleStore = new SimpleTripleStore();
		Map<String, Collection<Triple>> subject2TriplesOfCustomRelation = extractedTripleStore.getInstanceName2CustomRelationTripleMap();
		Map<String, Collection<Triple>> subject2TriplesOfConceptRelation = extractedTripleStore.getInstanceName2ConceptRelationTripleMap();
		simpleTripleStore.setRelation2TypeMap(this.createInstance2TypeSimpleTriples(extractedTripleStore.getRelationTypeTriple()));
		simpleTripleStore.setTriplesOfCustomRelation(this.createSubject2SimpleTripleMap(subject2TriplesOfCustomRelation));
		simpleTripleStore.setTriplesOfInstance2ConceptRelation(this.createSubject2SimpleTripleMap(subject2TriplesOfConceptRelation));
		simpleTripleStore.setWebSiteUrl(webPageUrl);
		simpleTripleStore.setLocalStorageName(extractedTripleStore.getRepositoryName());
		
		return simpleTripleStore;
	}
	
	public Map<Entity, Collection<SimpleTriple>> createSubject2SimpleTripleMap(Map<String, Collection<Triple>> subject2TripleMap) {
		Map<Entity, Collection<SimpleTriple>> subject2SimpleTriplesOfCustomRelation = new LinkedHashMap<Entity, Collection<SimpleTriple>>();
		for (String subject : subject2TripleMap.keySet()) {
			Entity subjectEntity = new Entity(subject, subject);
			Collection<SimpleTriple> simpleTriples = new ArrayList<SimpleTriple>();
			for (Triple triple : subject2TripleMap.get(subject)) {
				simpleTriples.add(this.createSimpleTriple(triple));
			}
			subject2SimpleTriplesOfCustomRelation.put(subjectEntity, simpleTriples);
		}
		return subject2SimpleTriplesOfCustomRelation;
	}
	
	private Collection<SimpleTriple> createInstance2TypeSimpleTriples(Collection<Triple> triples){
		Collection<SimpleTriple> simpleTriples = new ArrayList<SimpleTriple>();
		for(Triple instance2TypeTriple : triples){
			simpleTriples.add(createSimpleTriple(instance2TypeTriple.getSubject(),instance2TypeTriple.getPredicate(),instance2TypeTriple.getObject()));
		}
		return simpleTriples;
	}
	
	private SimpleTriple createSimpleTriple(String subjectLabel, String predicateLabel, String objectLabel) {
		
		Entity subject = new Entity(subjectLabel);
		Entity predicate = new Entity(predicateLabel);
		Entity object = new Entity(objectLabel);

		subject.setURI(subjectLabel);
		predicate.setURI(predicateLabel);
		object.setURI(objectLabel);

		SimpleTriple simpleTriple = new SimpleTriple(subject, predicate, object);
		return simpleTriple;
	}
	
	private SimpleTriple createSimpleTriple(Triple triple) {
		
		Entity subject = new Entity(triple.getSubject());
		Entity predicate = new Entity(triple.getPredicate());
		Entity object = new Entity(triple.getObject());

		subject.setURI(triple.getSubject());
		predicate.setURI(triple.getPredicate());
		object.setURI(triple.getObject());

		SimpleTriple simpleTriple = new SimpleTriple(subject, predicate, object);
		return simpleTriple;
	}

	@Override
	public SimpleTripleStore getMappingBasicInfo(String webPageUrl) {
		this.initParameter();
		
		
		try {
			URL webPageURL = new URL(webPageUrl);
			String repositoryName = FileUtility.convertURL2FileName(webPageURL);
//			IMappingInfoRepository mappingResult = MappingInfoRepositoryFactory.createMappingInfoRepository(webPageURL,
//			                OntologyRepositoryFactory.createOntologyRepository(),
//			                ClassificationCorrectionRepositoryFactory.createRepository(),
//			                ManufacturingLexicalMappingRepositoryFactory.createManufacturingLexiconRepository(), true);
			
			IOntologyRepository ontologyRepository = OntologyRepositoryFactory.createOntologyRepository();
//			IProprietoryClassifiedInstancesRepository _ProperietoryClassifiedInstancesRepository = ClassifiedInstancesRepositoryFactory
//			                .createProprietoryClassifiedInstancesRepository(
//			                				webPageURL, 
//			                				ontologyRepository, 
//			                				ClassificationCorrectionRepositoryFactory.createRepository(), 
//			                				ManufacturingLexicalMappingRepositoryFactory.createAggregratedManufacturingLexicalMappingRepository(ontologyRepository), 
//			                				true);


//			IManufacturingLexicalMappingRecordsReader _aggregratedManufacturingLexicalMappingRepository = ManufacturingLexicalMappingRepositoryFactory
//			                .createAggregratedManufacturingLexicalMappingRepository(ontologyRepository);
//			IManufacturingLexicalMappingRepository _MLReposiory = ManufacturingLexicalMappingRepositoryFactory
//			                .createProprietaryManufacturingLexiconRepository(repositoryName);

			IClassifiedInstancesRepository _ProperietoryClassifiedInstancesRepository = ClassifiedInstancesRepositoryFactory
			                .createProprietoryClassifiedInstancesRepository(webPageURL, 
			                				ontologyRepository,
			                                true, false, false);


			SimpleTripleStore tripleStore = new SimpleTripleStore();
			Map<Entity, Collection<SimpleTriple>> ontoClass2InstancesMap = new LinkedHashMap<Entity, Collection<SimpleTriple>>();
//			MappingBasicInfo mappinBasicInfo = mappingResult.getMappingBasicInfo();
			MappingBasicInfo mappinBasicInfo = _ProperietoryClassifiedInstancesRepository.getMappingBasicInfo();
			Collection<IClassifiedInstanceBasicRecord> instanceBasicInfoCollection = mappinBasicInfo.getClassifiedInstanceBasicInfoCollection();
			for (IClassifiedInstanceBasicRecord instanceBasicInfo : instanceBasicInfoCollection) {
				String instanceName = instanceBasicInfo.getInstanceLabel();
				double similarity = instanceBasicInfo.getSimilarity();
				String className = instanceBasicInfo.getOntoClassName();
				String classURI = instanceBasicInfo.getOntoClassURI();
				Entity classEntity = new Entity(className, classURI);
				Entity instanceEntity = new Entity(instanceName);
				Entity predicateEntity = new Entity("typeOf");
				SimpleTriple simpleTriple = new SimpleTriple(instanceEntity, predicateEntity, classEntity);
				simpleTriple.setSimiliarty(String.valueOf(similarity));
				Collection<SimpleTriple> instanceTriples = ontoClass2InstancesMap.get(classEntity);
				if (instanceTriples == null) {
					instanceTriples = new ArrayList<SimpleTriple>();
					ontoClass2InstancesMap.put(classEntity, instanceTriples);
				}
				instanceTriples.add(simpleTriple);
			}

			Map<Entity, Collection<SimpleTriple>> property2RelationMap = new LinkedHashMap<Entity, Collection<SimpleTriple>>();
			Collection<IMatchedOntProperty> relation2OntPropertyMappingPairs = mappinBasicInfo.getMappedRelationInfoCollection();
			for (IMatchedOntProperty mappingPair : relation2OntPropertyMappingPairs) {
				String relationName = mappingPair.getRelationName();
				double similarity = mappingPair.getSimilarity();
				String propertyName = mappingPair.getOntPropertyName();
				String propertyURI = mappingPair.getOntPropertyURI();
				Entity relationEntity = new Entity(relationName);
				Entity predicateEntity = new Entity("mappedTo");
				Entity propertyEntity = new Entity(propertyName, propertyURI);
				SimpleTriple simpleTriple = new SimpleTriple(relationEntity, predicateEntity, propertyEntity);
				simpleTriple.setSimiliarty(String.valueOf(similarity));
				Collection<SimpleTriple> relation2propertyTriples = property2RelationMap.get(propertyEntity);
				if (relation2propertyTriples == null) {
					relation2propertyTriples = new ArrayList<SimpleTriple>();
					property2RelationMap.put(propertyEntity, relation2propertyTriples);
				}
				relation2propertyTriples.add(simpleTriple);
			}

			tripleStore.setOntoClass2InstanceMap(ontoClass2InstancesMap);
			tripleStore.setProperty2RelationMap(property2RelationMap);
			return tripleStore;

		} catch (MalformedURLException e) {
			SimpleTripleStore tripleStore = new SimpleTripleStore();
			return tripleStore;
		} catch (IOException e) {
			SimpleTripleStore tripleStore = new SimpleTripleStore();
			return tripleStore;
		}
	}
	
	@Override
	public SimpleMappingDetailInfo getMappingDetailInfo(String webPageUrl) {
		System.out.println("@@@@ getMappingDetailInfo from: " + webPageUrl);
		this.initParameter();
		try {
			URL webPageURL = new URL(webPageUrl);
			String repositoryName = FileUtility.convertURL2FileName(webPageURL);
//			IMappingInfoRepository mappingResult = MappingInfoRepositoryFactory.createMappingInfoRepository(webPageURL,
//			                OntologyRepositoryFactory.createOntologyRepository(),
//			                ClassificationCorrectionRepositoryFactory.createRepository(),
//			                ManufacturingLexicalMappingRepositoryFactory.createManufacturingLexiconRepository(), true);

//			IProprietoryClassifiedInstancesRepository _ProperietoryClassifiedInstancesRepository = ClassifiedInstancesRepositoryFactory
//            .createProprietoryClassifiedInstancesRepository(
//            				webPageURL, 
//            				OntologyRepositoryFactory.createOntologyRepository(), 
//            				ClassificationCorrectionRepositoryFactory.createRepository(), 
//            				ManufacturingLexicalMappingRepositoryFactory.createManufacturingLexiconRepository(), 
//            				true);
			

			IOntologyRepository ontologyRepository = OntologyRepositoryFactory.createOntologyRepository();


			IManufacturingLexicalMappingRecordsReader _aggregratedManufacturingLexicalMappingRepository = ManufacturingLexicalMappingRepositoryFactory
			                .createAggregratedManufacturingLexicalMappingRepository(ontologyRepository);
			IManufacturingLexicalMappingRepository _MLReposiory = ManufacturingLexicalMappingRepositoryFactory
			                .createProprietaryManufacturingLexiconRepository(repositoryName);

			IClassifiedInstancesRepository _ProperietoryClassifiedInstancesRepository = ClassifiedInstancesRepositoryFactory
			                .createProprietoryClassifiedInstancesRepository(webPageURL, 
			                				ontologyRepository,
			                                true, false, false);
			
			Collection<SimpleClassifiedInstanceInfo> allClassifiedInstanceInfoCollection = new ArrayList<SimpleClassifiedInstanceInfo>();
			Map<Entity, Collection<SimpleClassifiedInstanceInfo>> ontoClass2InstancesMap = new LinkedHashMap<Entity, Collection<SimpleClassifiedInstanceInfo>>();
//			MappingDetailInfo mappingDetailInfo = mappingResult.getMappingDetailInfo();
			MappingDetailInfo mappingDetailInfo = _ProperietoryClassifiedInstancesRepository.getMappingDetailInfo();
			
			Collection<IClassifiedInstanceDetailRecord> instanceDetailInfoCollection = mappingDetailInfo.getClassifiedInstanceDetailRecords();
			for (IClassifiedInstanceDetailRecord classifiedInstance: instanceDetailInfoCollection) { 
				System.out.println("----");
				String instanceName = classifiedInstance.getInstanceLabel();
				String className = classifiedInstance.getOntoClassName();
				String classURI = classifiedInstance.getOntoClassURI();
				String similarity = String.valueOf(classifiedInstance.getSimilarity());
				
				System.out.println(instanceName + " of type: " + className + " with similarity: " + similarity);

				Entity instanceEntity = new Entity(instanceName);
				Entity mappedOntoClassEntity = new Entity(className, classURI);
				SimpleClassifiedInstanceInfo simpleClassifiedInstanceInfo = new SimpleClassifiedInstanceInfo(instanceEntity, mappedOntoClassEntity, similarity);
				Collection<SimpleClassifiedInstanceInfo> instanceInfoCollection = ontoClass2InstancesMap.get(mappedOntoClassEntity);
				if (instanceInfoCollection == null) {
					instanceInfoCollection = new ArrayList<SimpleClassifiedInstanceInfo>();
					ontoClass2InstancesMap.put(mappedOntoClassEntity, instanceInfoCollection);
				}
				instanceInfoCollection.add(simpleClassifiedInstanceInfo);
				allClassifiedInstanceInfoCollection.add(simpleClassifiedInstanceInfo);

				Collection<Entity> firstLevelRecommendedOntoClasses = new HashSet<Entity>();
				Collection<Entity> secondLevelRecommendedOntoClasses = new HashSet<Entity>();
				Collection<Entity> relatedConcepts = new ArrayList<Entity>();
				simpleClassifiedInstanceInfo.setRecommendedLv1OntoClasses(firstLevelRecommendedOntoClasses);
				simpleClassifiedInstanceInfo.setRecommendedLv2OntoClasses(secondLevelRecommendedOntoClasses);
				simpleClassifiedInstanceInfo.setRelatedConceptCollection(relatedConcepts);

				for (OntoClassInfo superOntClass : classifiedInstance.getSuperOntoClassesOfMatchedOntoClass()) {
					System.out.println("Super Class: " + superOntClass.getURI());
					Entity superOntClassEntity = new Entity(superOntClass.getOntClassName(), superOntClass.getURI());
					firstLevelRecommendedOntoClasses.add(superOntClassEntity);
				}

				for (OntoClassInfo FLOntClass : classifiedInstance.getFirstLevelRecommendedOntoClasses()) {
					System.out.println("FL Class: " + FLOntClass.getURI() + " " + FLOntClass.getOntClassName());
					Entity FLOntClassEntity = new Entity(FLOntClass.getOntClassName(), FLOntClass.getURI());
					firstLevelRecommendedOntoClasses.add(FLOntClassEntity);
				}

				for (OntoClassInfo SLOntClass : classifiedInstance.getSecondLevelRecommendedOntoClasses()) {
					System.out.println("SL Class: " + SLOntClass.getURI() + " " + SLOntClass.getOntClassName());
					Entity SLOntClassEntity = new Entity(SLOntClass.getOntClassName(), SLOntClass.getURI());
					secondLevelRecommendedOntoClasses.add(SLOntClassEntity);
				}

				for (IConcept2OntClassMapping pair : classifiedInstance.getConcept2OntClassMappingPairs()) {
					Entity conceptEntity = new Entity(pair.getConceptName());
					System.out.println("Concept: " + conceptEntity.getEntityLabel());
					if (pair.isMappedConcept()) {
//						pair.isHittedMapping();
						
						String mappedClassName = pair.getMappedOntoClassName();
						String mappedClassURI = pair.getMappedOntoClass().getURI();
						double mappingSimilarity = pair.getMappingScore();
						Entity mappedOntClassEntity = new Entity(mappedClassName, mappedClassURI);
						conceptEntity.addMappedOntoClass(mappedOntClassEntity, mappingSimilarity);
						conceptEntity.setDirectMapping(pair.isDirectMapping());
						System.out.println("  Mapped: " + conceptEntity.getEntityLabel() + " -> " + mappedClassName + pair.isDirectMapping());
					} else {
						System.out.println("  No Mapped Class");
					}
					relatedConcepts.add(conceptEntity);
				}
			}

			SimpleMappingDetailInfo simpleMappingDetailInfo = new SimpleMappingDetailInfo();
			simpleMappingDetailInfo.setWebSiteUrl(webPageUrl);
//			simpleMappingDetailInfo.setLocalStorageName(mappingResult.getRepositoryName());
			simpleMappingDetailInfo.setLocalStorageName(_ProperietoryClassifiedInstancesRepository.getRepositoryName());
			simpleMappingDetailInfo.setClassifiedInstanceInfoCollection(allClassifiedInstanceInfoCollection);
			simpleMappingDetailInfo.setOntoClass2InstancesMap(ontoClass2InstancesMap);
			return simpleMappingDetailInfo;
		} catch (MalformedURLException e) {
			SimpleMappingDetailInfo simpleMappingDetailInfo = new SimpleMappingDetailInfo();
			return simpleMappingDetailInfo;
		} catch (IOException e) {
			SimpleMappingDetailInfo simpleMappingDetailInfo = new SimpleMappingDetailInfo();
			return simpleMappingDetailInfo;
		}
	}
	
	@Override
	public Collection<SimpleOntClassInfo> getSimpleOntClassInfoCollection() {
		this.initParameter();
		List<SimpleOntClassInfo> simpleOntClassList = new ArrayList<SimpleOntClassInfo>();
		try {
			IOntologyRepository ontologyRepository = OntologyRepositoryFactory.createOntologyRepository();
			// TODO
			
			for(String globalCode : ontologyRepository.getGlobalPathCode2OntClassMap().keySet()){
				OntoClassInfo ontClassInfo = ontologyRepository.getGlobalPathCode2OntClassMap().get(globalCode);
				SimpleOntClassInfo simpleOntClass = new SimpleOntClassInfo(ontClassInfo.getOntClassName(), ontClassInfo.getURI());
				simpleOntClass.setGlobalCode(globalCode);
				simpleOntClassList.add(simpleOntClass);
			}
			Collections.sort(simpleOntClassList);
			return simpleOntClassList;
		}
		catch (IOException e) {
			e.printStackTrace();
			return simpleOntClassList;
		}
	}
	
	@Override
	public boolean saveClassifiedInstances(String webSiteURL, Collection<InstanceRecord> instanceRecords) {
		System.out.println("Save updated instances ... from " + webSiteURL);
		this.initParameter();
		try {
			int numberOfClassifiedInstance = 0;
			int numberOfUpdatedInstance = 0;
			URL webPageURL = new URL(webSiteURL);
			String repositoryName = FileUtility.convertURL2FileName(webPageURL);
			IOntologyRepository ontologyRepository = OntologyRepositoryFactory.createOntologyRepository();
			IClassificationCorrectionRepository classificationCorrectionRepository = InterpretationCorrectionRepositoryFactory.createProprietaryClassificationCorrectionRepository(repositoryName);
			IManufacturingLexicalMappingRepository MLRepository = ManufacturingLexicalMappingRepositoryFactory.createProprietaryManufacturingLexiconRepository(repositoryName);
			IClassifiedInstancesRepository properietoryClassifiedInstancesRepository = new ProprietoryClassifiedInstancesRepository(
			                FileUtility.convertURL2FileName(webPageURL), ClassifiedInstancesRepositoryType.All,
			                ontologyRepository, MLRepository);
			
			MappingDataGateway gateWay = new MappingDataGateway(properietoryClassifiedInstancesRepository, classificationCorrectionRepository, MLRepository);
			
			String prevenanceOfInstances = properietoryClassifiedInstancesRepository.getRepositoryName();
			Collection<IInstanceRecord> updatedInstanceRecords = new ArrayList<IInstanceRecord>();
			for (InstanceRecord record : instanceRecords) {
				numberOfClassifiedInstance++;
				IInstanceRecord instanceRecord = gateWay.createInstanceClassificationRecord();
				updatedInstanceRecords.add(instanceRecord);
				String origInstanceName = record.getInstanceLabel();
				String origOntoClassName = record.getOntoClassLabel();

				instanceRecord.setPrevenanceOfInstance(prevenanceOfInstances);
				instanceRecord.setOriginalInstanceName(origInstanceName);
				instanceRecord.setOriginalClassName(origOntoClassName);

				if (record instanceof UpdatedInstanceRecord) {
					numberOfUpdatedInstance++;
					UpdatedInstanceRecord updatedInstance = (UpdatedInstanceRecord) record;
					String updatedInstanceLabel = updatedInstance.getChangedInstanceLabel();
					String updatedOntoClassLabel = updatedInstance.getChangedOntoClassLabel();
					instanceRecord.setUpdatedInstanceName(updatedInstanceLabel);
					instanceRecord.setUpdatedClassName(updatedOntoClassLabel);
					instanceRecord.isUpdatedInstance(true);
				} else {
					instanceRecord.setUpdatedInstanceName(origInstanceName);
					instanceRecord.setUpdatedClassName(origOntoClassName);
					instanceRecord.isUpdatedInstance(false);
				}

//				SimilarityAlgorithm similarityAlg = new SimilarityAlgorithm();
				for (Concept2OntoClassTriple mappingTriple : record.getRelatedConcepts()) {
					String conceptName = mappingTriple.getConceptLabel();
					Concept concept = new Concept(conceptName);
//					double mappingLabelSimilarity = similarityAlg.getSimilarity(SimilarityType.Kim_Ngram, conceptName, updatedClassName);
//					if ((mappingTriple.isMappedConcept() && mappingTriple.isDirectedMapping())
//					                || (mappingTriple.isMappedConcept() && !mappingTriple.isDirectedMapping() && mappingTriple.isMappedOntoClassChanged())) {
					if (mappingTriple.isMappedConcept()) {
						String updatedClassName = mappingTriple.getUpdatedOntoClassLabel();
						double similarity = mappingTriple.getSimilarity();

						System.out.println("### MAPPING: " + conceptName + " --> " + updatedClassName);
						MappingRelationType relation = this.getRelationType(mappingTriple.getRelationLabel());
						String classNS = ontologyRepository.getLightWeightOntClassByName(updatedClassName).getNameSpace();
						String classURI = ontologyRepository.getLightWeightOntClassByName(updatedClassName).getURI();
						OntoClassInfo updatedOntClass = new OntoClassInfo(classURI, classNS, updatedClassName);
						instanceRecord.addConcept2OntClassMappingPair(
																		concept, 
																		relation, 
																		updatedOntClass,
																		mappingTriple.isDirectedMapping(), 
																		mappingTriple.isMappedOntoClassChanged(),
																		similarity
																     );
					}
					else {
						System.out.println("### UN-MAPPING: " + conceptName);
						instanceRecord.addConcept2OntClassMappingPair(concept, null, null, false, false, 0.0);
					}
				}
			}

			System.out.println("### number of classified instances: " + numberOfClassifiedInstance);
			System.out.println("### number of updated instances: " + numberOfUpdatedInstance);
			gateWay.updateMappingInfo(updatedInstanceRecords);
			((ProprietoryClassifiedInstancesRepository) properietoryClassifiedInstancesRepository).saveHumanReadableFile("/Users/yankang/Desktop/standards/");
			return gateWay.saveRepository();
		
		} catch (MalformedURLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
//		System.out.println("Save updated instances ... from " + webSiteURL);
//		this.initParameter();
//		try {
//			URL webPageURL = new URL(webSiteURL);
//			IOntologyRepository ontologyRepository = OntologyRepositoryFactory.createOntologyRepository();
//			IClassificationCorrectionRepository classificationCorrectionRepository = ClassificationCorrectionRepositoryFactory.createRepository();
//			IManufacturingLexicalMappingRepository MLRepository = ManufacturingLexicalMappingRepositoryFactory.createManufacturingLexiconRepository();
//			IMappingInfoRepository mappingInfoRepo = MappingInfoRepositoryFactory.createMappingInfoRepository(webPageURL,
//							                                                                                  ontologyRepository,
//							                                                                                  classificationCorrectionRepository,
//							                                                                                  MLRepository, true);
//			
////			_MLReposiory = ManufacturingLexicalMappingRepositoryFactory.createManufacturingLexiconRepository();
////			_ontologyRepository = OntologyRepositoryFactory.createOntologyRepository();
////			_ProperietoryClassifiedInstancesRepository = new ProprietoryClassifiedInstancesRepository(FileUtility.convertURL2FileName(webURL),
////					ClassifiedInstancesRepositoryType.All, _ontologyRepository, _MLReposiory);
//			
//			
//			String prevenanceOfInstances = mappingInfoRepo.getRepositoryName();
//			Collection<IUpdatedInstanceRecord> updatedInstanceRecords = new ArrayList<IUpdatedInstanceRecord>();
//			for (InstanceRecord record : instanceRecords) {
//
//				IUpdatedInstanceRecord instanceRecord = mappingInfoRepo.createInstanceClassificationRecord();
//				updatedInstanceRecords.add(instanceRecord);
//				String origInstanceName = record.getInstanceLabel();
//				String origOntoClassName = record.getOntoClassLabel();
//
//				instanceRecord.setPrevenanceOfInstance(prevenanceOfInstances);
//				instanceRecord.setOriginalInstanceName(origInstanceName);
//				instanceRecord.setOriginalClassName(origOntoClassName);
//
//				if (record instanceof UpdatedInstanceRecord) {
//					UpdatedInstanceRecord updatedInstance = (UpdatedInstanceRecord) record;
//					String updatedInstanceLabel = updatedInstance.getChangedInstanceLabel();
//					String updatedOntoClassLabel = updatedInstance.getChangedOntoClassLabel();
//					instanceRecord.setUpdatedInstanceName(updatedInstanceLabel);
//					instanceRecord.setUpdatedClassName(updatedOntoClassLabel);
//					instanceRecord.isUpdatedInstance(true);
//				} else {
//					instanceRecord.setUpdatedInstanceName(origInstanceName);
//					instanceRecord.setUpdatedClassName(origOntoClassName);
//					instanceRecord.isUpdatedInstance(false);
//				}
//
//				SimilarityAlgorithm similarityAlg = new SimilarityAlgorithm();
//				for (Concept2OntoClassTriple mappingTriple : record.getRelatedConcepts()) {
//					String conceptName = mappingTriple.getConceptLabel();
//					String updatedClassName = mappingTriple.getUpdatedOntoClassLabel();
//					Concept concept = new Concept(conceptName);
////					double mappingLabelSimilarity = similarityAlg.getSimilarity(SimilarityType.Kim_Ngram, conceptName, updatedClassName);
////					if ((mappingTriple.isMappedConcept() && mappingTriple.isDirectedMapping())
////					                || (mappingTriple.isMappedConcept() && !mappingTriple.isDirectedMapping() && mappingTriple.isMappedOntoClassChanged())) {
//					if (mappingTriple.isMappedConcept()) {
//						String className = mappingTriple.getUpdatedOntoClassLabel();
//						double similarity = mappingTriple.getSimilarity();
//
//						System.out.println("### MAPPING: " + conceptName + " --> " + className);
//						MappingRelationType relation = this.getRelationType(mappingTriple.getRelationLabel());
//						String classNS = ontologyRepository.getOntClassByName(className).getNameSpace();
//						String classURI = ontologyRepository.getOntClassByName(className).getURI();
//						OntoClassInfo ontClass = new OntoClassInfo(classURI, classNS, className);
//						instanceRecord.addConcept2OntClassMappingPair(concept, relation, ontClass,
//						                mappingTriple.isDirectedMapping(), mappingTriple.isMappedOntoClassChanged(),
//						                similarity);
//					}
//					else {
//						instanceRecord.addConcept2OntClassMappingPair(concept, null, null, false, false, 0.0);
//					}
//				}
//			}
//
//			mappingInfoRepo.updateMappingInfo(updatedInstanceRecords);
//			return mappingInfoRepo.saveRepository();
//		}
//		catch (MalformedURLException e) {
//			return false;
//		} catch (IOException e) {
//			return false;
//		}
	}

	private MappingRelationType getRelationType(String relation) { 
		if(relation.trim().equals(MappingRelationType.relatedTo.toString())){
			return MappingRelationType.relatedTo;
		} else if (relation.trim().equals(MappingRelationType.broader.toString())){
			return MappingRelationType.broader;
		} else if (relation.trim().equals(MappingRelationType.narrower.toString())){
			return MappingRelationType.narrower;
		}
		return null;
	}

	@Override
	public Collection<String> listWebSiteHomeUrls() {
		return TripleRepositoriesReference.getWebSiteURLs();
	}
}
