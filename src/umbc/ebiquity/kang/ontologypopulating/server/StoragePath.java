package umbc.ebiquity.kang.ontologypopulating.server;

public class StoragePath {
	
    private static String TRIPLE_STORE_DIR = "triplestore";
    private static String MAPPING_INFO_DIR = "mapping_info";
    private static String BASIC_MAPPING_INFO_DIR = "basic";
    private static String DETAIL_MAPPING_INFO_DIR = "detail";
    
    
	public static final String StoragePath;
    public static final String TRIPLE_STORE_PATH;
    public static final String MAPPING_INFO_PATH;
    public static final String BASIC_MAPPING_INFO_PATH;
    public static final String DETAIL_MAPPING_INFO_PATH;
    
	static {
		StoragePath = "";
		TRIPLE_STORE_PATH = StoragePath + "/" + TRIPLE_STORE_DIR;
		MAPPING_INFO_PATH = StoragePath + "/" + MAPPING_INFO_DIR;
		BASIC_MAPPING_INFO_PATH = MAPPING_INFO_PATH + "/" + BASIC_MAPPING_INFO_DIR;
		DETAIL_MAPPING_INFO_PATH = MAPPING_INFO_PATH + "/" + DETAIL_MAPPING_INFO_DIR;
	}

}
