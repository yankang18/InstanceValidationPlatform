package umbc.ebiquity.kang.ontologypopulating.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Entity implements IsSerializable {
	
	private String entityLabel;
	private String URI;
	
	private boolean _isDirectMapping = true;
	private boolean _isMappedConcept = false;
	private double similairty;
	private Entity OntoClass;
	
	public Entity() {}
	public Entity(String label) {
		this.entityLabel = label.trim();
	}
	
	public Entity(String label, String URI) {
		this.entityLabel = label.trim();
		this.URI = URI.trim();
	}

	public void setEntityLable(String label){
		this.entityLabel = label.trim();
	}

	public String getEntityLabel(){
		return this.entityLabel;
	}

	public void setURI(String URI) {
		this.URI = URI.trim();
	}

	public String getURI() {
		return URI;
	}
	
	public void addMappedOntoClass(Entity ontoClass, double mappingSimilarity){
		this.OntoClass = ontoClass;
		this.similairty = mappingSimilarity;
		this._isMappedConcept = true;
	}
	
	public boolean isMappedConcept(){
		return this._isMappedConcept;
	}
	
	public Entity getMappedOntoClass(){
		return this.OntoClass;
	}
	
	public double getMappingSimilarity(){
		return this.similairty;
	}
	
	public void setDirectMapping(boolean isDirectMapping) {
		this._isDirectMapping = isDirectMapping;
	}

	public boolean isDirectMapping() {
		return _isDirectMapping;
	}
	
	
	@Override
	public String toString() {
		return this.URI;
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		Entity entity = (Entity) obj;
		return this.toString().equals(entity.toString());
	}
}
