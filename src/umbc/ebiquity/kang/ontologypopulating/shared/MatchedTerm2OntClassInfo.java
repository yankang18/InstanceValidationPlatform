package umbc.ebiquity.kang.ontologypopulating.shared;

import java.util.ArrayList;
import java.util.Collection;

import com.google.gwt.user.client.rpc.IsSerializable;


public class MatchedTerm2OntClassInfo implements IsSerializable{
	
	private String termLabel;
	private String ontClassLabel;
	private double similarity;
	private Collection<String> relatedTerms;
	private Collection<String> superOntClassesInPath;
	private Collection<MatchedPredicate2PropertyInfo> matchedPredicate2PropertyInfoCollection;
	
	public MatchedTerm2OntClassInfo(){}
	
	public MatchedTerm2OntClassInfo(String termLabel, String ontClassLabel, double similarity){
		this.termLabel = termLabel;
		this.ontClassLabel = ontClassLabel;
		this.similarity = similarity;
		
		this.relatedTerms = new ArrayList<String>();
		this.superOntClassesInPath = new ArrayList<String>();
		this.matchedPredicate2PropertyInfoCollection = new ArrayList<MatchedPredicate2PropertyInfo>();
	}

	public void addRelatedTerms(Collection<String> relatedTerms){
		this.relatedTerms.addAll(relatedTerms);
	}
	
	public void addSuperOntClassesInPath(Collection<String> superOntClassesInPath){
		this.superOntClassesInPath.addAll(superOntClassesInPath);
	}
	
	public void addMatchedPredicate2PropertyInfoCollection(Collection<MatchedPredicate2PropertyInfo> matchedPredicate2PropertyInfoCollection){
		this.matchedPredicate2PropertyInfoCollection.addAll(matchedPredicate2PropertyInfoCollection);
	}
	
	public String getTermLabel(){
		return this.termLabel;
	}

	public String getOntClassLabel(){
		return this.ontClassLabel;
	}

	public Collection<String> getSuperOntClassesInPath(){
		return this.superOntClassesInPath;
	}

	public Collection<String> getRelatedTerms(){
		return this.relatedTerms;
	}

	public Collection<MatchedPredicate2PropertyInfo> getMatchedPredicate2PropertyInfoCollection(){
		return this.matchedPredicate2PropertyInfoCollection;
	}

	public double getSimilarity(){
		return this.similarity;
	}

}
