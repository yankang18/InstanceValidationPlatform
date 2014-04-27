package umbc.ebiquity.kang.ontologypopulating.client.event;

public class GetInstancesOfOntoClassEvent {

	private String ontoClassURI;
	private String ontoClassName;
	public GetInstancesOfOntoClassEvent(String URI, String name){
		this.ontoClassURI = URI;
		this.ontoClassName = name;
	}
	
	public String getOntoClassURI(){
		return this.ontoClassURI;
	}
	
	public String getOntoClassName(){
		return this.ontoClassName;
	}
}
