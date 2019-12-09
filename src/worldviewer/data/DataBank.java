package worldviewer.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * save data according to selectors 
 * 
 * @author huizhu
 *
 */
public class DataBank {
	public static final String POPULATION = "SP.POP.TOTL";
	
	private static List<String> listOfSelectedCountries = new ArrayList<String>(Arrays.asList("ARB", "CHN", "EUU"));
	// third indicator need to be population in total: SP.POP.TOTL
	private static List<String> listOfSelectedIndicators = new ArrayList<String>(Arrays.asList("EN.ATM.CO2E.KT", "NY.GDP.MKTP.CD", "SP.POP.TOTL"));
	private static WorldIndicatorReader reader = new WorldIndicatorReader("WDIDataSmall.csv"); 
	private static Map<String, String> countryMap = reader.listCountries(); 
	private static Map<String, String> indicatorMap = reader.listIndicators();
	private static List<CountryIndicatorData> validData = reader.filterData(listOfSelectedCountries, listOfSelectedIndicators);
	
	/**
	 * get country map 
	 * @return
	 */
	public static Map<String, String> getCountryMap() {
		return countryMap;
	}
	
	/**
	 * get indicator map 
	 * @return
	 */
	public static Map<String, String> getIndicatorMap() {
		return indicatorMap;
	}
	
	/**
	 * get valid data list 
	 * @return
	 */
	public static List<CountryIndicatorData> getValidData() {
		return validData;
	}
	
	/**
	 * get list of selected countries 
	 * @return
	 */
	public static List<String> getListOfSelectedCountries() {
		return listOfSelectedCountries;
	}
	
	/**
	 * get list of selected indicators 
	 * @return
	 */
	public static List<String> getListOfSelectedIndicators() {
		return listOfSelectedIndicators;
	}
	
	/**
	 * update valid data 
	 */
	public static void updateValidData() {
		List<String> listOfSelectedIndicators = new ArrayList<>(DataBank.listOfSelectedIndicators);
		if (!listOfSelectedIndicators.contains(POPULATION))
			listOfSelectedIndicators.add(POPULATION);
		System.out.printf("filtering: %s on %s\n", listOfSelectedCountries, listOfSelectedIndicators);
		validData = reader.filterData(listOfSelectedCountries, listOfSelectedIndicators); 
	}

}
