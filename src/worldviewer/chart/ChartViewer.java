package worldviewer.chart;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.Chart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

/**
 * 
 * @author huizhu
 *
 */
public abstract class ChartViewer {
	private final Chart chart;
	private final ScaledAxis[] axises; 

	ChartViewer() {
		this.chart = newChart();
		this.axises = newScaledAxis(); 
	}

	public ScaledAxis[] newScaledAxis() {
		return new ScaledAxis[0];
	}

	/**
	 * A getter returns chart
	 * 
	 * @return
	 */
	public Chart getChart() {
		return chart;
	}

	/**
	 * A getter changes chart and returns XYChart
	 * 
	 * @return
	 */
	public XYChart getXYChart() {
		return (XYChart) chart;
	}

	/**
	 * An abstract method A specific type of chart is created by overriding this
	 * method
	 * 
	 * This function returns a new chart
	 * 
	 * @return
	 */
	abstract public Chart newChart();

	/**
	 * This function is for creating a line series for a country The x_axis
	 * represents year of selection and the next 9 years from the year of selection.
	 * The y-axis represents the index for the one chosen indicator.
	 * 
	 * @param countryCode
	 * @param year
	 * @return
	 */

	public Series<Number, Number> newLineSeries(String countryCode, int year) {
		Series<Number, Number> series = new Series<Number, Number>();
		series.setName(DataBank.getCountryMap().get(countryCode));

		CountryIndicatorData dataOfInterest = null;
		int x = 0;

//		System.out.println("idic2: " + DataBank.getListOfSelectedIndicators().get(0));
		for (CountryIndicatorData eachData : DataBank.getValidData()) {

			if (eachData.getCountryCode().equals(countryCode)
					&& eachData.getIndicatorCode().equals(DataBank.getListOfSelectedIndicators().get(0))) {
				dataOfInterest = eachData;
//				System.out.printf("country: %s, indicator: %s\n", eachData.getCountryCode(), eachData.getIndicatorCode());
				for (int i = 0; i < 10; i++) {
					x = (year + i) % 2019 + ((year + i) / 2019) * 1960;

					if (dataOfInterest.getYearlyData()[x - 1960] > 0) {
						double y = dataOfInterest.getYearlyData()[x - 1960];
//						System.out.printf("x: %s, y: %s\n", x, y);
						series.getData().add(new XYChart.Data<Number, Number>(x, y));
					}
				}
				break;
			}
		}
		return series;
	}

	/**
	 * The function is for creating series of chart type with number, number as
	 * x-axis and y-axis This type of chart tells the relation between three
	 * indicators first chosen indicator is represented by x-axis second chosen
	 * indicator is represented by y-axis for bubble chart: third indicator is
	 * represented by the size of bubble
	 * 
	 * @param countryCode
	 * @param year
	 * @return
	 */
	public Series<Number, Number> newNNSeries(String countryCode, int year) {
		Series<Number, Number> series = new Series<Number, Number>();
		series.setName(DataBank.getCountryMap().get(countryCode));

//		System.out.printf("NN country: %s, year: %s\n", countryCode, year);

		List<String> threeIndicators = DataBank.getListOfSelectedIndicators();
		double x = 0;
		double y = 0;
		double z = 0;

		for (CountryIndicatorData eachData : DataBank.getValidData()) {
			if (eachData.getCountryCode().equals(countryCode)) {
				if (eachData.getIndicatorCode().equals(threeIndicators.get(0))) {
					x = eachData.getYearlyData()[year - 1960];
//					System.out.println("x: " + x);
					
				} else if (eachData.getIndicatorCode().equals(threeIndicators.get(1))) {
					y = eachData.getYearlyData()[year - 1960];
//					System.out.println("y: " + y);
					
				} else if (eachData.getIndicatorCode().equals(threeIndicators.get(2))) {
					z = eachData.getYearlyData()[year - 1960];
//					System.out.println("z: " + z);
				}
			}
		}

//		System.out.printf("NN x: %e, y: %e, z: %e\n", x, y, z);

		if (x > 0 && y > 0 && z > 0)
			if (axises.length > 0)
				series.getData().add(new XYChart.Data<Number, Number>(x / axises[0].getScale(), y / axises[1].getScale(), z / axises[2].getScale()));
			else 
				series.getData().add(new XYChart.Data<Number, Number>(x, y, z));

		return series;
	}

	/**
	 * The function is for creating series of chart type with String, Number as
	 * x-axis and y-axis x-axis represents the country and y-axis represents the
	 * indicator
	 * 
	 * @param countryCode
	 * @param year
	 * @return
	 */
	public Series<String, Number> newSNSeries(String countryCode, int year) {
		Series<String, Number> series = new Series<String, Number>();
		series.setName(DataBank.getCountryMap().get(countryCode));

		CountryIndicatorData dataOfInterest = DataBank.getValidData().get(0);

		for (CountryIndicatorData eachData : DataBank.getValidData()) {
			if (eachData.getCountryCode().equals(countryCode) && eachData.getIndicatorCode().equals(DataBank.getListOfSelectedIndicators().get(0))) {
				dataOfInterest = eachData;
				break;
			}
		}

		if (dataOfInterest.getYearlyData()[year - 1960] > 0)
			series.getData().add(new XYChart.Data<String, Number>(dataOfInterest.getCountryName(),
					dataOfInterest.getYearlyData()[year - 1960]));

		return series;
	}

	/**
	 * An abstract method A specific type of chart is updated by overriding this
	 * method
	 * 
	 * @param year
	 */
	public abstract void updateChart(int year);

	/**
	 * This method is for updating data for all countries in the line chart It calls
	 * newLineSeries method
	 * 
	 * @param year
	 */
	public void updateLineChart(int year) {
		List<Series<Number, Number>> serieses = new ArrayList<Series<Number, Number>>();

		for (String eachCountry : DataBank.getListOfSelectedCountries()) {
			serieses.add(newLineSeries(eachCountry, year));
		}

		getXYChart().getData().setAll(serieses);
		NumberAxis xaxis = (NumberAxis) getXYChart().getXAxis();
		xaxis.setLowerBound(year);
		xaxis.setUpperBound(year + 9);
		xaxis.setTickUnit(3);
	}

	/**
	 * This method is for updating data for all countries in NN chart It calls
	 * newNNSeries function
	 * 
	 * @param year
	 */
	public void updateNNChart(int year) {
		List<Series<Number, Number>> serieses = new ArrayList<Series<Number, Number>>();

		for (String eachCountry : DataBank.getListOfSelectedCountries()) {
			serieses.add(newNNSeries(eachCountry, year));
		}

		getXYChart().getData().setAll(serieses);
	}

	/**
	 * This method is for updating data for all countries in SN chart It calls
	 * newSNSeries function
	 * 
	 * @param year
	 */
	public void updateSNChart(int year) {
		List<Series<String, Number>> serieses = new ArrayList<Series<String, Number>>();

		for (String eachCountry : DataBank.getListOfSelectedCountries()) {
			serieses.add(newSNSeries(eachCountry, year));
		}

		getXYChart().getData().setAll(serieses);
	}

	/**
	 * This method is for adding and updating data for all countries in Pie Chart
	 * 
	 * @param year
	 */
	void updatePieChart(int year) {
		List<PieChart.Data> groups = new ArrayList<>();
		for (CountryIndicatorData eachData : DataBank.getValidData()) {
			if (eachData.getIndicatorCode().equals(DataBank.getListOfSelectedIndicators().get(0))) {
				groups.add(new PieChart.Data(eachData.getCountryName(), eachData.getYearlyData()[year - 1960]));
			}
		}
		((PieChart) chart).getData().setAll(groups);
	}

	/**
	 * This method finds the greatest double in a double array
	 * 
	 * @param array
	 * @return
	 */
	public double findMax(double[] array) {
		double max = array[0];

		for (int i = 1; i < array.length; i++) {
			if (array[i] > max)
				max = array[i];
		}

		return max;
	}

	/**
	 * This method finds the smallest double in a double array
	 * 
	 * @param array
	 * @return
	 */
	public double findMin(double[] array) {
		double min = Double.MAX_VALUE;

		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1 && array[i] < min)
				min = array[i];
		}

		return min;
	}

}
