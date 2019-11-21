package worldviewer.data;

import java.util.Arrays;

/**
 * 
 * yearly data for a given country / indicator
 * 
 * @author huizhu, qingjin
 *
 */
public class CountryIndicatorData {
	private final String countryName;
	private final String countryCode;
	private final String indicatorName;
	private final String indicatorCode;
	private final double[] yearlyData;		// from 1960 to 2019
	
	public CountryIndicatorData(String countryName, String countryCode, 
			String indicatorName, String indicatorCode, double[] yearlyData) {
		this.countryName = countryName;
		this.countryCode = countryCode;
		this.indicatorName = indicatorName;
		this.indicatorCode = indicatorCode;
		this.yearlyData = yearlyData;
	}
	
	/**
	 * get country name
	 * @return
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * get country code
	 * @return
	 */
	public String getCountryCode() {
		return countryCode;
	}
	
	/**
	 * get indicator name
	 * @return
	 */
	public String getIndicatorName() {
		return indicatorName;
	}
	
	/**
	 * get indicator code
	 * @return
	 */
	public String getIndicatorCode() {
		return indicatorCode;
	}
	
	/**
	 * get yearly data
	 * @return
	 */
	public double[] getYearlyData() {
		return yearlyData;
	}

	@Override
	public String toString() {
		return "CountryIndicatorData [countryName=" + countryName + ", countryCode=" + countryCode + ", indicatorName="
				+ indicatorName + ", indicatorCode=" + indicatorCode + ", yearlyData=" + Arrays.toString(yearlyData)
				+ "]";
	}
	
	
}
