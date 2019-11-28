package worldviewer.data;

import java.util.*;

import worldviewer.WorldViwerSelector;

public class DataBank {
	private static WorldIndicatorReader reader = new WorldIndicatorReader("WDIDataSmall.cvs"); 
	private static Map<String, String> countryMap = reader.listCountries(); 
	private static Map<String, String> indicatorMap = reader.listIndicators();
	private static List<CountryIndicatorData> validData = reader.filterData(WorldViwerSelector.selectedCountries(), WorldViwerSelector.selectedIndicators()); 
	
	public static final int COUNT = 2019 - 1960; 
	
	public static Map<String, String> getCountryMap() {
		return countryMap;
	}
	public static Map<String, String> getIndicatorMap() {
		return indicatorMap;
	}
	public static List<CountryIndicatorData> getValidData() {
		return validData;
	}

	

}
