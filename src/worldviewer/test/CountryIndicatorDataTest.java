package worldviewer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import worldviewer.data.CountryIndicatorData;

import org.junit.Assert;

import org.junit.jupiter.api.Test;

/**
 * test country indicator data 
 * 
 * @author qingjin
 *
 */
class CountryIndicatorDataTest {
	
	/**
	 * check initiation 
	 */
	@Test
	void testInitiation() {
		double arr[] = new double[60];
		for(int i=0;i<arr.length;i++)
		    arr[i] = 0;
		CountryIndicatorData data = new CountryIndicatorData("cN","cC","iN","iC",arr);
		Assert.assertEquals("Initiation check", false, data==null);
	}
	
	/**
	 * test country code 
	 */
	@Test
	void testCountryCode() {
		double arr[] = new double[60];
		for(int i=0;i<arr.length;i++)
		    arr[i] = 0;
		CountryIndicatorData data = new CountryIndicatorData("cN","cC","iN","iC",arr);
		Assert.assertEquals("CountryCode check", "cC", data.getCountryCode());
	}
	
	/**
	 * test country name 
	 */
	@Test
	void testCountryName() {
		double arr[] = new double[60];
		for(int i=0;i<arr.length;i++)
		    arr[i] = 0;
		CountryIndicatorData data = new CountryIndicatorData("cN","cC","iN","iC",arr);
		Assert.assertEquals("CountryName check", "cN", data.getCountryName());
	}
	
	/**
	 * test indicator code
	 */
	@Test
	void testIndicatorCode() {
		double arr[] = new double[60];
		for(int i=0;i<arr.length;i++)
		    arr[i] = 0;
		CountryIndicatorData data = new CountryIndicatorData("cN","cC","iN","iC",arr);
		Assert.assertEquals("IndicatorCode check", "iC", data.getIndicatorCode());
	}
	
	/**
	 * test indicator name 
	 */
	@Test
	void testIndicatorName() {
		double arr[] = new double[60];
		for(int i=0;i<arr.length;i++)
		    arr[i] = 0;
		CountryIndicatorData data = new CountryIndicatorData("cN","cC","iN","iC",arr);
		Assert.assertEquals("IndicatorName check", "iN", data.getIndicatorName());
	}
	
	/**
	 * test yearly data 
	 */
	@Test
	void testYearlyData() {
		double arr[] = new double[60];
		for(int i=0;i<arr.length;i++)
		    arr[i] = 0;
		CountryIndicatorData data = new CountryIndicatorData("cN","cC","iN","iC",arr);
		Assert.assertEquals("YearlyData check", 60, data.getYearlyData().length);
	}
	
	

}
