package umbc.ebiquity.kang.ontologypopulating.client.event;

import umbc.ebiquity.kang.ontologypopulating.shared.SimpleClassifiedInstanceInfo;

public interface GetInstanceDetailEventHandler {

	SimpleClassifiedInstanceInfo getInstanceDetail(GetInstanceDetailEvent event);
}
