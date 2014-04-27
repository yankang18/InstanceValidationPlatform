package umbc.ebiquity.kang.ontologypopulating.client.event;

import com.smartgwt.client.data.DataSource;

public interface GetInstancesOfOntoClassEventHandler {
	
	public DataSource getInstances(GetInstancesOfOntoClassEvent event);

}
