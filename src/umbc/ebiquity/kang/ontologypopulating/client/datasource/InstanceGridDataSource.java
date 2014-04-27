package umbc.ebiquity.kang.ontologypopulating.client.datasource;

import java.util.Collection;

import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleClassifiedInstanceInfo;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class InstanceGridDataSource extends DataSource {
	
	public InstanceGridDataSource() {
		
		DataSourceTextField instanceNameField = new DataSourceTextField("instanceName", "Instance", 128, true);
		instanceNameField.setRequired(true);
		instanceNameField.setPrimaryKey(true);
		DataSourceTextField similarityField = new DataSourceTextField("similarity", "Score",128,true);
		similarityField.setRequired(true);
		setFields(instanceNameField, similarityField);
		setClientOnly(true); 
	}
	
	public void addData(Collection<SimpleClassifiedInstanceInfo> instanceCollection) {
		System.out.println("@@@@ HERE1 !!!");
		if (instanceCollection == null)
			return;
		int size = instanceCollection.size();
		ListGridRecord returnRecords[] = new ListGridRecord[size];
		int i = 0;
		System.out.println("@@@@ HERE2 !!!");
		for (SimpleClassifiedInstanceInfo instance : instanceCollection) {
			System.out.println("added instance " +  instance.getInstance().getEntityLabel());
			returnRecords[i] = new ListGridRecord();
			returnRecords[i].setAttribute("instanceName", instance.getInstance().getEntityLabel());
			returnRecords[i].setAttribute("similarity", instance.getSimilarity());
			this.addData(returnRecords[i]);
			i++;
		}
	}

}
