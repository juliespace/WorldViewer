package worldviewer.chart;

import java.util.*;
import javafx.scene.chart.*;
import worldviewer.WorldViwerSelector;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

/**
 * This class creates a bubble chart with x-axis, y-axis, and labels
 * 
 * @author huizhu
 *
 */
public class BubbleChartViewer extends ChartViewer {
	
	/**
	 * This method override the method newChart() in ChartViewer
	 * It creates a new bubble chart
	 * 
	 */
	@Override
	public BubbleChart<Number, Number> newChart() {
		String [] threeIndicators = new String[] {WorldViwerSelector.selectedIndicators().get(0), WorldViwerSelector.selectedIndicators().get(1), WorldViwerSelector.selectedIndicators().get(2)};
		List<Double> xMaxes = new ArrayList<Double>();
		List<Double> xMins = new ArrayList<Double>(); 
		List<Double> yMaxes = new ArrayList<Double>(); 
		List<Double> yMins = new ArrayList<Double>(); 
		
		for (CountryIndicatorData eachData: DataBank.getValidData()) {
			if (eachData.getIndicatorCode().equals(threeIndicators[0])) {
				xMaxes.add(findMax(eachData.getYearlyData())); 
				xMins.add(findMin(eachData.getYearlyData())); 
			}
			else if (eachData.getIndicatorCode().equals(threeIndicators[1])) {
				yMaxes.add(findMax(eachData.getYearlyData())); 
				yMins.add(findMin(eachData.getYearlyData())); 
			}
		}
		double xMax = Collections.max(xMaxes); 
		double xMin = Collections.min(xMins); 
		double yMax = Collections.max(yMaxes); 
		double yMin = Collections.min(yMins); 
		
		final NumberAxis xAxis = new NumberAxis(xMin, xMax, xMax - xMin); 
		final NumberAxis yAxis = new NumberAxis(yMin, yMax, yMax - yMin); 
		
		final BubbleChart<Number, Number> bubbleChart = new BubbleChart<Number, Number>(xAxis, yAxis); 
		xAxis.setLabel(DataBank.getIndicatorMap().get(threeIndicators[0]));
		yAxis.setLabel(DataBank.getIndicatorMap().get(threeIndicators[1]));
		
		bubbleChart.setTitle("Bubble Chart");
		bubbleChart.setAnimated(false);
		
		return bubbleChart; 
		
	}

	/**
	 * This method calls updateNNChart() in ChartViewer class and updates the values for each country
	 */
	@Override
	public void updateChart(int year) {
		updateNNChart(year);
		
	}
	
	/**
	 * This method finds the greatest double in a double array 
	 * 
	 * @param array
	 * @return
	 */
	public double findMax(double[] array) {
		double max = array[0]; 
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) 
				max = array[i]; 
		}
		
		return max;
	}

	/**
	 * This method finds the smallest double in a double array 
	 * @param array
	 * @return
	 */
	public double findMin(double[] array) {
		double min = 100; 
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1 && array[i] < min) 
				min = array[i]; 
		}
		
		return min; 
	}
	
}