package worldviewer.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
		
		try (FileInputStream fis = new FileInputStream(inputFile); 
				Scanner scanner = new Scanner(fis)) {
			while (scanner.hasNextLine()) {
				data.add(parseLine(scanner.nextLine()));
//				String line = scanner.nextLine();
				lines++;
				if (lines % 100 == 0)
					System.out.printf("line count: %s\n", lines);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
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
				int rand = (int)(Math.random() * perLines);
				if (rand == 1 || lines == 1)
					pw.println(line);
			}
			pw.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		return null;
	}
	
	/**
	 * 
	 * list available indicators
	 * 
	 * @return a map of: indicator code -> indicator description 
	 */
	public Map<String, String> listIndicators() {
		return null;
	}
	
	/**
	 *
	 *	filter data with specified indicator codes and country codes
	 * 
	 * @param countryCodes		a list of country codes
	 * @param indicatorCodes	a list of indicator codes
	 * @return a list of country indicator data filtered by indicator codes and country codes
	 */
	public List<CountryIndicatorData> filterData(List<String> countryCodes, List<String> indicatorCodes) {
		return null;
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
		return new CountryIndicatorData(f[0], f[1], f[2], f[3], yearly);
	}
}
