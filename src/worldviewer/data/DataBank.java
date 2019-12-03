package worldviewer.data;

import java.util.*;

import worldviewer.WorldViwerSelector;

public class DataBank {
	private static List<String> listOfSelectedCountries = Arrays.asList("ARB", "CSS", "CEB");
	private static List<String> listOfSelectedIndicators = Arrays.asList("EN.ATM.CO2E.KT", "SP.POP.1564.MA.IN", "SP.DYN.CBRT.IN");
	private static List<String> listOfSelectedCharts = new ArrayList<String>();
	private static WorldIndicatorReader reader = new WorldIndicatorReader("WDIDataSmall2.csv"); 
	private static Map<String, String> countryMap = reader.listCountries(); 
	private static Map<String, String> indicatorMap = reader.listIndicators();
	private static List<CountryIndicatorData> validData = reader.filterData(listOfSelectedCountries, listOfSelectedIndicators); 
		
	public static Map<String, String> getCountryMap() {
		return countryMap;
	}
	public static Map<String, String> getIndicatorMap() {
		return indicatorMap;
	}
	public static List<CountryIndicatorData> getValidData() {
		return validData;
	}
	public static List<String> getListOfSelectedCountries() {
		return listOfSelectedCountries;
	}
	public static List<String> getListOfSelectedIndicators() {
		return listOfSelectedIndicators;
	}

}
