package worldviewer.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.BubbleChart;
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
	 * 
	 * new scaled axis
	 * 
	 */
	@Override 
	public ScaledAxis[] newScaledAxis() {
		
		ScaledAxis [] axises = new ScaledAxis[3]; 
	
		for (int i = 0; i < 3; i++) {
			List<Double> maxes = new ArrayList<Double>();
			List<Double> mins = new ArrayList<Double>();
			
			for (CountryIndicatorData eachData : DataBank.getValidData()) {
				if (i < 2 && eachData.getIndicatorCode().equals(DataBank.getListOfSelectedIndicators().get(i)) ||
					i == 2 && eachData.getIndicatorCode().equals(DataBank.POPULATION)) {
					maxes.add(findMax(eachData.getYearlyData()));
					mins.add(findMin(eachData.getYearlyData()));
				}
			}
			double max = Collections.max(maxes);
			double min = Collections.min(mins);
		
			System.out.printf("i: %s, min: %s, max: %s\n", i, min, max);
			
			if (i < 2) 
				axises[i] = new ScaledAxis(min, max, DataBank.getIndicatorMap().get(DataBank.getListOfSelectedIndicators().get(i))); 
			else
				axises[i] = new ScaledAxis(min, max); 
			
		}
		
		return axises;
	}
	
	
	/**
	 * This method override the method newChart() in ChartViewer It creates a new
	 * bubble chart
	 * 
	 */
	@Override
	public BubbleChart<Number, Number> newChart() {
		ScaledAxis[] axises = newScaledAxis(); 
		
		final BubbleChart<Number, Number> bubbleChart = new BubbleChart<Number, Number>(axises[0].getAxis(), axises[1].getAxis());
		
		String thirdIndicator = DataBank.getIndicatorMap().get(DataBank.POPULATION);
		bubbleChart.setTitle("Bubble Size: " + thirdIndicator);
		bubbleChart.setAnimated(false);

		return bubbleChart;
	}


	/**
	 * This method calls updateNNChart() in ChartViewer class and updates the values
	 * for each country
	 */
	@Override
	public void updateChart(int year) {
		updateNNChart(year);

	}

}