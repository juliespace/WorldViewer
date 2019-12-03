package worldviewer.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import worldviewer.WorldViwerSelector;
import worldviewer.data.WorldIndicatorReader;

/**
 *
 * to test world viewer classes
 * 
 * @author huizhu, qingjin
 *
 */
public class WorldViewerTest {
	@BeforeClass
	public static void setup() {
//		WorldIndicatorLoader.sample("WDIData.csv", "WDIDataSmall.csv", 100);
	}

	/**
	 * 
	 * 	test for the listCountries method of WorldIndicatorLoader
	 * 
	 */
	@Test
	public void testListContries() {
	}

	/**
	 *
	 * 	test for the listIndicators method of WorldIndicatorLoader
	 * 
	 */
	@Test
	public void testListIndicators() {
	}
	
	@Test
	public void testData() {
		System.out.println(WorldViwerSelector.selectedCountries());
		System.out.println(WorldViwerSelector.selectedIndicators());
	}

	/**
	 *
	 * 	test the initialization logic for WorldIndicatorLoader, including the loading / parsing part
	 * 
	 */
//	@Test
	public void testWorldIndicatorLoaderInit() {
		WorldIndicatorReader loader = new WorldIndicatorReader("WDIDataSmall.csv"); 
		int dataSize = loader.getData().size(); 
		Assert.assertTrue(dataSize > 0);
		Assert.assertTrue(dataSize < 400000);
	}
}
