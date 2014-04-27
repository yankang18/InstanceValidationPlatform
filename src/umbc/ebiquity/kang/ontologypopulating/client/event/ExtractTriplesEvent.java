package umbc.ebiquity.kang.ontologypopulating.client.event;

public class ExtractTriplesEvent {
	
	private String webSiteUrl;
	public ExtractTriplesEvent(String webSiteUrl){
		this.webSiteUrl = webSiteUrl;
	}

	public String getWebSiteUrl() {
		return webSiteUrl;
	}
}
