package worldviewer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import worldviewer.data.DataBank;

import org.junit.Assert;

/**
 * test data bank 
 * 
 * 
 * @author qingjin
 *
 */
class DataBankTest {
	
	/**
	 * test valid data 
	 */
	@Test
	void testGetValidData() {
		//System.out.println(DataBank.getValidData().size());
		Assert.assertEquals("GetValidData check", 9, DataBank.getValidData().size());
	}
	
	/**
	 * test list of selected countries 
	 */
	@Test
	void testListOfSelectedCountries() {
		//System.out.println(DataBank.getValidData().size());
		Assert.assertEquals("ListOfSelectedCountries check", 3, DataBank.getListOfSelectedCountries().size());
	}
	
	/**
	 * test list of selected indicators 
	 */
	@Test
	void testListOfSelectedIndicators() {
		//System.out.println(DataBank.getValidData().size());
		Assert.assertEquals("ListOfSelectedIndicators check", 3, DataBank.getListOfSelectedIndicators().size());
	}
	
	/**
	 * test updated valid data 
	 */
	@Test
	void testUpdateValidData() {
		//System.out.println(DataBank.getValidData().size());
		DataBank.getListOfSelectedCountries().clear();
		DataBank.getListOfSelectedCountries().addAll(Arrays.asList("ARB", "CHN"));
		DataBank.updateValidData();
		Assert.assertEquals("UpdateValidData check", 6, DataBank.getValidData().size());
	}

}
