package umbc.ebiquity.kang.ontologypopulating.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TripleRepositoriesReference {
	
	
	private static Map<String, Boolean> loadReferenceMap;
	private static Set<String> webSiteURLSet;
	
	static{
		
		webSiteURLSet = new LinkedHashSet<String>();
		loadReferenceMap = new HashMap<String, Boolean>();
		
//		String webPageUrl1 = "http://www.numericalconcepts.com";
//		String webPageUrl2 = "http://www.bassettinc.com";
//		String webPageUrl3 = "http://www.wisconsinmetalparts.com";
//		String webPageUrl4 = "http://www.aerostarmfg.com";
//		String webPageUrl5 = "http://www.accutrex.com";
//		String webPageUrl6 = "http://www.weaverandsons.com";
//		String webPageUrl8 = "http://www.navitekgroup.com";
//		String webPageUrl9 = "http://www.cmc-usa.com";
		
		String webPageUrl1 = "http://www.numericalconcepts.com";
		String webPageUrl2 = "http://www.bassettinc.com";
		String webPageUrl3 = "http://www.wisconsinmetalparts.com";
		String webPageUrl4 = "http://www.aerostarmfg.com";
		String webPageUrl5 = "http://www.accutrex.com";
		String webPageUrl6 = "http://www.weaverandsons.com";
		String webPageUrl8 = "http://www.astromfg.com";
		String webPageUrl9 = "http://www.navitekgroup.com";
		String webPageUrl10 = "http://www.cmc-usa.com";
		String webPageUrl11 = "http://www.soundmfg.com";
		String webPageUrl12 = "http://www.schulermfg.com";
		String webPageUrl13 = "http://www.jitprototyping.com";
		String webPageUrl14 = "http://www.rache.com";
		loadReferenceMap.put(webPageUrl1, true);
		loadReferenceMap.put(webPageUrl2, true);
		loadReferenceMap.put(webPageUrl3, true);
		loadReferenceMap.put(webPageUrl4, true);
		loadReferenceMap.put(webPageUrl5, true);
		loadReferenceMap.put(webPageUrl6, true);
		loadReferenceMap.put(webPageUrl8, true);
		loadReferenceMap.put(webPageUrl9, true);
		loadReferenceMap.put(webPageUrl10, true);
		webSiteURLSet.add(webPageUrl1);
		webSiteURLSet.add(webPageUrl2);
		webSiteURLSet.add(webPageUrl3);
		webSiteURLSet.add(webPageUrl4);
		webSiteURLSet.add(webPageUrl5);
		webSiteURLSet.add(webPageUrl6);
		webSiteURLSet.add(webPageUrl8);
		webSiteURLSet.add(webPageUrl9);
		webSiteURLSet.add(webPageUrl10);
		webSiteURLSet.add(webPageUrl11);
		webSiteURLSet.add(webPageUrl12);
		webSiteURLSet.add(webPageUrl13);
		webSiteURLSet.add(webPageUrl14);
	}
	
	public static Collection<String> getWebSiteURLs(){
		return webSiteURLSet;
	}
	
	public static boolean loadLocally(String webSiteURL){
		return loadReferenceMap.get(webSiteURL);
	}
}