package umbc.ebiquity.kang.ontologypopulating.client.datasource;

import java.util.Collection;
import java.util.LinkedHashSet;

import umbc.ebiquity.kang.ontologypopulating.shared.Entity;
import umbc.ebiquity.kang.ontologypopulating.shared.SimpleMappingDetailInfo;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class OntoClassGridDataSource extends DataSource {

	public OntoClassGridDataSource() {
		DataSourceTextField ontoClassNameField = new DataSourceTextField("ClassName", "Class", 128, true);
		ontoClassNameField.setRequired(true);
		DataSourceTextField ontoClassURIField = new DataSourceTextField("ClassURI", null);
		ontoClassURIField.setHidden(true);
		ontoClassURIField.setRequired(true);
		ontoClassURIField.setPrimaryKey(true);
		setFields(ontoClassNameField, ontoClassURIField);
//		setFields(ontoClassNameField);
		setClientOnly(true);
	}
	
	public void addData(SimpleMappingDetailInfo simpleMappingDetailInfo) {
		if (simpleMappingDetailInfo == null)
			return;
		Collection<Entity> ontoClasses = simpleMappingDetailInfo.getOntoClass2InstancesMap().keySet();
		int size = ontoClasses.size();
		ListGridRecord returnRecords[] = new ListGridRecord[size];
		int i = 0;
		for (Entity ontoClass : ontoClasses) {
			returnRecords[i] = new ListGridRecord();
			returnRecords[i].setAttribute("ClassName", ontoClass.getEntityLabel());
			returnRecords[i].setAttribute("ClassURI", ontoClass.getURI());
			this.addData(returnRecords[i]);
			i++;
		}
	}
}
