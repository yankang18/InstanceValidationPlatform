package umbc.ebiquity.kang.ontologypopulating.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleTriple implements IsSerializable{
	
	private Entity subject;
	private Entity predicate;
	private Entity object;
	private String similiarty;
	
	public SimpleTriple(){}
	public SimpleTriple(Entity subject, Entity predicate, Entity object){
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}
	
	public Entity getSubject(){
		return this.subject;
	}
	
	public Entity getPredicate(){
		return this.predicate;
	}

	public Entity getObject(){
		return this.object;
	}
	public void setSimiliarty(String similiarty) {
		this.similiarty = similiarty;
	}
	public String getSimiliarty() {
		return similiarty;
	}

}
