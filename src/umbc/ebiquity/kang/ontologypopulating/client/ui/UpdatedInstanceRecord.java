package umbc.ebiquity.kang.ontologypopulating.client.ui;

public class UpdatedInstanceRecord extends InstanceRecord {
	
	private String changedInstanceLabel;
	private String changedOntoClassLabel;
	private boolean isUpdated = false;
	
	public UpdatedInstanceRecord(){}
	public UpdatedInstanceRecord(String instanceLabel){
		super(instanceLabel);
	}
	public void setChangedInstanceLabel(String changedInstanceLabel) {
		this.changedInstanceLabel = changedInstanceLabel;
	}
	public String getChangedInstanceLabel() {
		return changedInstanceLabel;
	}
	public void setChangedOntoClassLabel(String classLabel) {
		this.changedOntoClassLabel = classLabel;
	}
	public String getChangedOntoClassLabel() {
		return changedOntoClassLabel;
	}
	public boolean isUpdated(){
		return this.isUpdated;
	}
	public void setUpdated(boolean isUpdated){
		this.isUpdated = isUpdated;
	}
//	public String getOntoClassTransferCode() {
//		return this.getOntoClassLabel() + ":" + this.getChangedInstanceLabel();
//	}
	
}
