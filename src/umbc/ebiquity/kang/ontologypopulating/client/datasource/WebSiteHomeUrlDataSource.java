package umbc.ebiquity.kang.ontologypopulating.client.datasource;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class WebSiteHomeUrlDataSource extends DataSource {
	
	private Set<String> webSiteHomeUrlSet;
	public WebSiteHomeUrlDataSource(){
		DataSourceTextField wehSiteUrlField = new DataSourceTextField("URL", "URL", 128, true);
		this.setFields(wehSiteUrlField);
		this.setClientOnly(true);
	}

	public void addData(Collection<String> webSiteHomeUrlSet) {
		if (webSiteHomeUrlSet == null)
			return;
		int size = webSiteHomeUrlSet.size();
		this.webSiteHomeUrlSet = new LinkedHashSet<String>(webSiteHomeUrlSet);
		ListGridRecord returnRecords[] = new ListGridRecord[size];
		int i = 0;
		for (String webSiteUrl : webSiteHomeUrlSet) {
			returnRecords[i] = new ListGridRecord();
			returnRecords[i].setAttribute("URL", webSiteUrl);
			this.addData(returnRecords[i]);
			i++;
		}
	}
	
	public boolean contains(String namespace){
		if(webSiteHomeUrlSet == null){
			return false;
		}
		return webSiteHomeUrlSet.contains(namespace);
	}
}
