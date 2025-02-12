package worldviewer.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

/**
 * bar chart viewer
 * @author qingjin
 *
 */
public class BarChartViewer extends ChartViewer {
	@Override
	public BarChart<String, Number> newChart() {
		String indicatorOfInterest = DataBank.getListOfSelectedIndicators().get(0); 
		List<Double> yMaxes = new ArrayList<Double>();
		List<Double> yMins = new ArrayList<Double>();
		
		for (CountryIndicatorData eachData : DataBank.getValidData()) {
			if (eachData.getIndicatorCode().equals(indicatorOfInterest)) {
				yMaxes.add(findMax(eachData.getYearlyData()));
				yMins.add(findMin(eachData.getYearlyData()));
			}
		}
		
		double yMax = Collections.max(yMaxes);
		double yMin = Collections.min(yMins);
		
		final CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Country");
		final NumberAxis yAxis = new NumberAxis(yMin, yMax, (yMax - yMin) / 5);
		yAxis.setLabel(DataBank.getIndicatorMap().get(DataBank.getListOfSelectedIndicators().get(0)));
		
        final BarChart<String, Number> barChart = 
                new BarChart<String, Number>(xAxis,yAxis);
                
        barChart.setTitle("Bar Chart");
        barChart.setAnimated(false);
        return barChart;
		
	}

	/**
	 * 
	 * update chart 
	 * 
	 */
	@Override
	public void updateChart(int year) {
		super.updateSNChart(year);
	}
}
