package umbc.ebiquity.kang.ontologypopulating.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SimpleOntClassInfo implements IsSerializable, Comparable<SimpleOntClassInfo> {

	private String _ontClassLabel;
	private String _ontClassURI;
	private String _hierarchyNumber;
	private String _globalCode;
	private String _parentOntClassURI;
	
	public SimpleOntClassInfo() {
	}
	public SimpleOntClassInfo(String ontClassLabel, String ontClassURI) {
		_ontClassLabel = ontClassLabel;
		_ontClassURI = ontClassURI;
	}
	public SimpleOntClassInfo(String ontClassLabel, 
					          String ontClassURI, 
					          String hierarchyNumber,
					          String parentOntClassURI) {
		_ontClassLabel = ontClassLabel;
		_ontClassURI = ontClassURI;
		_hierarchyNumber = hierarchyNumber;
		_parentOntClassURI = parentOntClassURI;
	}
	public String getOntClassLabel() {
	    return _ontClassLabel;
    }
	public String getOntClassURI() {
	    return _ontClassURI;
    }
	public String getHierarchyNumber() {
	    return _hierarchyNumber;
    }
	public String getParentOntClassURI() {
	    return _parentOntClassURI;
    }
	public void setGlobalCode(String _gobalCode) {
	    this._globalCode = _gobalCode;
    }
	public String getGlobalCode() {
	    return _globalCode;
    }
	@Override
    public int compareTo(SimpleOntClassInfo simpleOntClassInfo) { 
	    return this._globalCode.compareTo(simpleOntClassInfo.getGlobalCode());
    }

}
