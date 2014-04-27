package umbc.ebiquity.kang.ontologypopulating.client.event;

public class InformationZoomInEvent {
	
	private String info;
	public InformationZoomInEvent(String info){
		this.info = info;
	}
	
	public String getInformation(){
		return this.info;
	}

}
