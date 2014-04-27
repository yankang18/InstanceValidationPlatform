package umbc.ebiquity.kang.ontologypopulating.client.datasource;


import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleClassifiedInstanceInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class OntoInstanceDataSource  extends DataSource {
	
	public OntoInstanceDataSource() {
		
		
		DataSourceTextField origInstanceNameField = new DataSourceTextField("OriginalInstanceName", "Instance", 40, true);
		origInstanceNameField.setRequired(true);
		origInstanceNameField.setPrimaryKey(true);
		origInstanceNameField.setHidden(true);
		DataSourceTextField ontoInstanceNameField = new DataSourceTextField("InstanceName", "Instance", 40, true);
		ontoInstanceNameField.setRequired(true);
//		ontoInstanceNameField.setPrimaryKey(true);
		DataSourceTextField ontoClassNameField = new DataSourceTextField("ClassName", "Class", 40, true);
		ontoClassNameField.setRequired(true);
		DataSourceTextField similarityField = new DataSourceTextField("Similarity", "Score",20,true);
		similarityField.setRequired(true);
//		DataSourceTextField ontoClassURIField = new DataSourceTextField("ClassURI", null);
//		ontoClassURIField.setHidden(true);
//		ontoClassURIField.setRequired(true);
//		ontoClassURIField.setPrimaryKey(true);
		setFields(origInstanceNameField, ontoInstanceNameField, ontoClassNameField, similarityField);
		setClientOnly(true);
	}
	
	public void addData(SimpleMappingDetailInfo simpleMappingDetailInfo) {
		if (simpleMappingDetailInfo == null)
			return;
		
//		Collection<ListGridRecord>  ListGridRecordList = new ArrayList<ListGridRecord>();
		for (Entity ontoClass: simpleMappingDetailInfo.getOntoClass2InstancesMap().keySet()){
			for(SimpleClassifiedInstanceInfo instanceInfo : simpleMappingDetailInfo.getOntoClass2InstancesMap().get(ontoClass)){
				ListGridRecord record = new ListGridRecord();
				record.setAttribute("OriginalInstanceName", instanceInfo.getInstanceLabel());
				record.setAttribute("InstanceName", instanceInfo.getInstanceLabel());
				record.setAttribute("ClassName", ontoClass.getEntityLabel());
				record.setAttribute("Similarity", instanceInfo.getSimilarity());

				int numOfConcept = instanceInfo.getRelatedConceptCollection().size();
				if (numOfConcept > 0) {
					record.setAttribute("Investigate", "related");
				}
				this.addData(record);
			}
		}
	}
	
	

}
