package umbc.ebiquity.kang.ontologypopulating.client.event;

public class ChooseOntClassEvent {
	
	private String ontClassLabel;
	public ChooseOntClassEvent(String ontClassLabel){
		this.ontClassLabel = ontClassLabel;
	}

	public String getOntClassLabel() {
		return ontClassLabel;
	}

}
