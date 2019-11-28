package worldviewer.chart;

import java.util.*;

import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.*;
import worldviewer.WorldViwerSelector;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

/**
 * 
 * @author huizhu
 *
 */
public abstract class ChartViewer {
	private final Chart chart;
	
	ChartViewer() {
		this.chart = newChart(); 
	}
	
	
	/**
	 * A getter returns chart
	 * @return
	 */
	public Chart getChart() {
		return chart;
	}

	/**
	 * A getter changes chart and returns XYChart
	 * @return
	 */
	public XYChart getXYChart() {
		return (XYChart) chart; 
	}

	
	/**
	 * An abstract method 
	 * A specific type of chart is created by overriding this method 
	 * 
	 * This function returns a new chart
	 * @return
	 */
	abstract public Chart newChart(); 
	
	
	/**
	 * This function is for creating a line series for a country with the country code and year are provided
	 * It returns a jfx series 
	 * x-axis represents indicators;
	 * y-axis represents yearly value of these indicators;
	 * each line series represents a country
	 * 
	 * @param countryCode
	 * @param year
	 * @return
	 */

	public Series<Number, Number> newLineSeries(String countryCode, int year){
		Series<Number, Number> series = new Series<Number, Number>();
		series.setName(DataBank.getCountryMap().get(countryCode));
		
		for (CountryIndicatorData eachData: DataBank.getValidData()) {
			
			for (int i = 0; i < WorldViwerSelector.selectedIndicators().size(); i++) {
				
				if (eachData.getIndicatorCode().equals(WorldViwerSelector.selectedIndicators().get(i)) && eachData.getCountryCode().equals(countryCode)) {
					series.getData().add(new XYChart.Data(i, eachData.getYearlyData()[year])); 
				}
			}
		}
		
		return series; 
	}
	
	
	/**
	 * The function is for creating series of chart type with number, number as x-axis and y-axis
	 * This type of chart tells the relation between three indicators
	 * first chosen indicator is represented by x-axis
	 * second chosen indicator is represented by y-axis
	 * for bubble chart: third indicator is represented by the size of bubble
	 * 
	 * @param countryCode
	 * @param year
	 * @return
	 */
	public Series<Number, Number> newNNChart(String countryCode, int year){
		Series<Number, Number> series = new Series<Number, Number>();
		series.setName(DataBank.getCountryMap().get(countryCode));
		
		String [] threeIndicators = new String[] {WorldViwerSelector.selectedIndicators().get(0), WorldViwerSelector.selectedIndicators().get(1), WorldViwerSelector.selectedIndicators().get(2)};
		double x = 0; 
		double y = 0; 
		
		for (CountryIndicatorData eachData: DataBank.getValidData()) {			
			if (eachData.getCountryCode().equals(countryCode)) {
				if (eachData.getIndicatorCode().equals(threeIndicators[0]))
					x = eachData.getYearlyData()[year]; 
				else if (eachData.getIndicatorCode().equals(threeIndicators[1]))
					y = eachData.getYearlyData()[year]; 
			}	
		}
		
		series.getData().add(new XYChart.Data<Number, Number>(x, y)); 
		
		return series; 
	}
	
	
	/**
	 * An abstract method
	 * A specific type of chart is updated by overriding this method 
	 * 
	 * @param year
	 */
	public abstract void updateChart(int year);
	
	
	/**
	 * This method is for updating data for all countries in the line chart
	 * It calls newLineSeries method
	 * 
	 * @param year
	 */
	public void updateLineChart(int year) {
		List<Series<Number, Number>> serieses = new ArrayList<Series<Number, Number>>();
		
		for (String eachCountry: WorldViwerSelector.selectedCountries()) {
			serieses.add(newLineSeries(eachCountry, year));
		}
		
		getXYChart().getData().setAll(serieses);
		
	}
	
	/**
	 * This method is for updating data for all countries in NN chart 
	 * It calls newNNChart function
	 * 
	 * @param year
	 */
	public void updateNNChart(int year) {
		List<Series<Number, Number>> serieses = new ArrayList<Series<Number, Number>>();
		
		for (String eachCountry: WorldViwerSelector.selectedCountries()) {
			serieses.add(newNNChart(eachCountry, year)); 
		}
		
		getXYChart().getData().setAll(serieses); 
	}
	

}
