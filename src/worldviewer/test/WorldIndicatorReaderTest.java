package worldviewer.test;

import org.junit.jupiter.api.Test;

import worldviewer.data.WorldIndicatorReader;

import org.junit.Assert;

/**
 * test world indicator reader
 * 
 * 
 * @author qingjin
 *
 */
class WorldIndicatorReaderTest {

	private WorldIndicatorReader reader = new WorldIndicatorReader("WDIDataSmall.csv");
	
	/**
	 * test inital check 
	 */
	@Test
	void testInitiation() {
		//System.out.println(reader==null);
		Assert.assertEquals("Initailization check", false, (reader==null));
	}
	
	/**
	 * test indicator map size 
	 */
	@Test
	void testIndicatorMapSize() {
		//System.out.println(reader.listIndicators().size());
		Assert.assertEquals("IndicatorMapSize check", 44, reader.listIndicators().size());
	}
	
	/**
	 * test country map size 
	 */
	@Test
	void testCountryMapSize() {
		//System.out.println(reader.listCountries().size());
		Assert.assertEquals("CountryMapSize check", 133, reader.listCountries().size());
	}
	
	/**
	 * test indicator map content 
	 */
	@Test
	void testIndicatorMapContent() {
		//System.out.println(reader.listIndicators().get("EN.ATM.CO2E.KT"));
		Assert.assertEquals("IndicatorMapConten check", "CO2 emissions (kt)", reader.listIndicators().get("EN.ATM.CO2E.KT"));
	}
	
	/**
	 * test country map content h
	 */
	@Test
	void testCountryMapContent() {
		//System.out.println(reader.listCountries().get("CHN"));
		Assert.assertEquals("CountryMapContent check", "China", reader.listCountries().get("CHN"));
	}

}
