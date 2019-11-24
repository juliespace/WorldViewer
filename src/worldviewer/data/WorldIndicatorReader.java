package worldviewer.data;

import java.io.*;
import java.util.*;

/**
 * 
 * to read country indicator data
 * 
 * @author huizhu, qingjin
 *
 */
public class WorldIndicatorReader {
	private final List<CountryIndicatorData> data;

	public WorldIndicatorReader(String inputFile) {
		data = parseAll(inputFile);
	}

	/**
	 * load and parse all data from input file into memory
	 * 
	 * @param inputFile
	 * @return
	 */
	public List<CountryIndicatorData> parseAll(String inputFile) {
		ArrayList<CountryIndicatorData> data = new ArrayList<CountryIndicatorData>();
		int lines = 0;

		try (FileInputStream fis = new FileInputStream(inputFile); Scanner scanner = new Scanner(fis)) {
			while (scanner.hasNextLine()) {
				data.add(parseLine(scanner.nextLine()));
//				String line = scanner.nextLine();
				lines++;
				if (lines % 1000 == 0)
					System.out.printf("line count: %s\n", lines);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	/**
	 * 
	 * parse a single line of data
	 * 
	 * @param line
	 * @return
	 */
	public CountryIndicatorData parseLine(String line) {
		String[] f = line.split(",");
		double[] yearly = new double[f.length - 4];
		for (int i = 0; i < yearly.length; i++) {
			try {
				yearly[i] = Double.valueOf(f[i + 4]);
			} catch (Exception e) {
				yearly[i] = -1;
			}
		}
		return new CountryIndicatorData(strip(f[0]), strip(f[1]), strip(f[2]), strip(f[3]), yearly);
	}

	public String strip(String word) {
		return word.replace("\"", "");
	}

	/**
	 * get data cached in memory
	 * 
	 * @return cached data
	 */
	public List<CountryIndicatorData> getData() {
		return data;
	}

	/**
	 * 
	 * list countries in the world
	 * 
	 * @return a map of: country code -> country name
	 */
	public Map<String, String> listCountries() {
		HashMap<String, String> countryMap = new HashMap<String, String>();

		for (CountryIndicatorData countryIndicatorData : data) {
			if (!countryMap.containsKey(countryIndicatorData.getCountryCode()))
				countryMap.put(countryIndicatorData.getCountryCode(), countryIndicatorData.getCountryName());
		}
		return countryMap;
	}

	/**
	 * 
	 * list available indicators
	 * 
	 * @return a map of: indicator code -> indicator description
	 */
	public Map<String, String> listIndicators() {
		HashMap<String, String> indicatorMap = new HashMap<String, String>();

		for (CountryIndicatorData countryIndicatorData : data) {
			if (!indicatorMap.containsKey(countryIndicatorData.getIndicatorCode()))
				indicatorMap.put(countryIndicatorData.getIndicatorCode(), countryIndicatorData.getIndicatorName());
		}
		return indicatorMap;
	}

	/**
	 *
	 * filter data with specified indicator codes and country codes
	 * 
	 * @param countryCodes   a list of country codes
	 * @param indicatorCodes a list of indicator codes
	 * @return a list of country indicator data filtered by indicator codes and
	 *         country codes
	 */
	public List<CountryIndicatorData> filterData(List<String> countryCodes, List<String> indicatorCodes) {

		List<CountryIndicatorData> validData = new ArrayList<CountryIndicatorData>();

		for (CountryIndicatorData eachData : data) {
//			System.out.println("eachData: " + eachData);
			for (String ccode : countryCodes) {
//				System.out.printf("compare: %s %s %s\n", eachData.getCountryCode(), ccode,
//						eachData.getCountryCode().equals(ccode));
				if (eachData.getCountryCode().equals(ccode)) {
//					System.out.println("country found: " + ccode);
					for (String icode : indicatorCodes) {
						if (eachData.getIndicatorCode().equals(icode)) {
//							System.out.println("indicator found: " + icode);
							validData.add(eachData);
						}
					}
				}
			}
		}

		return validData;
	}

	/**
	 * sample inputFile to generate a smaller test data file for testing
	 * 
	 * @param inputFile
	 * @param outputFile
	 * @param perLines
	 */
	public static void sample(String inputFile, String outputFile, int perLines) {
		int lines = 0;

		try (FileOutputStream fos = new FileOutputStream(outputFile);
				PrintWriter pw = new PrintWriter(fos);
				FileInputStream fis = new FileInputStream(inputFile);
				Scanner scanner = new Scanner(fis)) {
			while (scanner.hasNextLine()) {
//				parseLine(scanner.nextLine());
				String line = scanner.nextLine();
				lines++;
				if (lines % 10000 == 0)
					System.out.printf("line count: %s\n", lines);
				int rand = (int) (Math.random() * perLines);
				if (rand == 1 || lines == 1)
					pw.println(line);
			}
			pw.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		WorldIndicatorReader newFile = new WorldIndicatorReader("WDIDataSmall.csv");
//
//		System.out.println(newFile.listCountries());
//		System.out.println(newFile.listIndicators());
//
//		List<String> listOfC = Arrays.asList("ARB", "CYP", "CHN", "USA");
//		List<String> listOfI = Arrays.asList("NE.GDI.STKB.CN", "SH.UHC.OOPC.25.TO", "EN.POP.EL5M.RU.ZS");
//
////		List<String> listOfC = new ArrayList<>(newFile.listCountries().keySet());
//
//		System.out.println(newFile.filterData(listOfC, listOfI));
//	}
}
