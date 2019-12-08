package worldviewer.data;

import org.junit.jupiter.api.Test;
import org.junit.Assert;


class WorldIndicatorReaderTest {

	private WorldIndicatorReader reader = new WorldIndicatorReader("WDIDataSmall.csv");
		
	@Test
	void testInitiation() {
		//System.out.println(reader==null);
		Assert.assertEquals("Initailization check", false, (reader==null));
	}
	
	
	@Test
	void testIndicatorMapSize() {
		//System.out.println(reader.listIndicators().size());
		Assert.assertEquals("IndicatorMapSize check", 44, reader.listIndicators().size());
	}
	
	@Test
	void testCountryMapSize() {
		//System.out.println(reader.listCountries().size());
		Assert.assertEquals("CountryMapSize check", 133, reader.listCountries().size());
	}
	
	@Test
	void testIndicatorMapContent() {
		//System.out.println(reader.listIndicators().get("EN.ATM.CO2E.KT"));
		Assert.assertEquals("IndicatorMapConten check", "CO2 emissions (kt)", reader.listIndicators().get("EN.ATM.CO2E.KT"));
	}
	
	@Test
	void testCountryMapContent() {
		//System.out.println(reader.listCountries().get("CHN"));
		Assert.assertEquals("CountryMapContent check", "China", reader.listCountries().get("CHN"));
	}

}
