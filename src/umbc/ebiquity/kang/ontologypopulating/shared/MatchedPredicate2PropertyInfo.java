package umbc.ebiquity.kang.ontologypopulating.shared;


import com.google.gwt.user.client.rpc.IsSerializable;

public class MatchedPredicate2PropertyInfo implements IsSerializable{
	
	private String predicateLabel;
	private String ontPropertyLabel;
	private double similarity;
	
	public MatchedPredicate2PropertyInfo(){}
	
	public MatchedPredicate2PropertyInfo(String predicateLabel, String ontPropertyLabel, double similarity){
		this.predicateLabel = predicateLabel;
		this.ontPropertyLabel = ontPropertyLabel;
		this.similarity = similarity;
	}
	
	public String getPredicateLabel(){
		return this.predicateLabel;
	};
	
	public String getOntPropertyLabel(){
		return this.ontPropertyLabel;
	};
	
	public double getSimilarity(){
		return this.similarity;
	};

}
