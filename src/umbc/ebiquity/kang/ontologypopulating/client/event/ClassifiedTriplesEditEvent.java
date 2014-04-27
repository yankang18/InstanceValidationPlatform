package umbc.ebiquity.kang.ontologypopulating.client.event;

public class ClassifiedTriplesEditEvent {
	private String webSiteUrl;
	private String localStorageName;
	public ClassifiedTriplesEditEvent(String webSiteUrl, String localStorageName){
		this.webSiteUrl = webSiteUrl;
		this.localStorageName = localStorageName;
	}
	public String getWebSiteUrl() {
		return webSiteUrl;
	}
	
	public String getLocalStorageName(){
		return localStorageName;
	}

}
