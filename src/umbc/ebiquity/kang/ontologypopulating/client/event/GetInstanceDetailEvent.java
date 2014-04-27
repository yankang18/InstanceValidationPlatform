package umbc.ebiquity.kang.ontologypopulating.client.event;

public class GetInstanceDetailEvent {
	
	private String instanceName;
	public GetInstanceDetailEvent(String instanceName){
		this.instanceName = instanceName;
	}
	
	public String getInstanceName(){
		return this.instanceName;
	}

}
