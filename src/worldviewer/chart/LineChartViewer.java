package worldviewer.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

/**
 * line chart viewer 
 * 
 * @author huizhu
 *
 */
public class LineChartViewer extends ChartViewer {

	/**
	 * 
	 * new chart
	 * 
	 */
	@Override
	public LineChart<Number, Number> newChart() {
		String indicatorOfInterest = DataBank.getListOfSelectedIndicators().get(0); 
		List<Double> yMaxes = new ArrayList<Double>();
		List<Double> yMins = new ArrayList<Double>();
//		System.out.println("idic1: " + DataBank.getListOfSelectedIndicators().get(0));
	
		for (CountryIndicatorData eachData : DataBank.getValidData()) {
			if (eachData.getIndicatorCode().equals(indicatorOfInterest)) {

//				System.out.printf("lineChart country: %s, indicator: %s\n", 
//				eachData.getCountryCode(), eachData.getIndicatorCode());
				
				yMaxes.add(findMax(eachData.getYearlyData()));
				yMins.add(findMin(eachData.getYearlyData()));
			}
		}
		
//		double yMedian = Collections.max(yMedians);
		
		double yMin = Collections.min(yMins);
		double yMax = Collections.max(yMaxes); 
		
		final NumberAxis xAxis = new NumberAxis();
		xAxis.setAutoRanging(false);
		final NumberAxis yAxis = new NumberAxis((long)yMin, (long)yMax, (long)(yMax - yMin) / 5);		
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Line Chart");
        xAxis.setLabel("Year Range");
        yAxis.setLabel(DataBank.getIndicatorMap().get(indicatorOfInterest));
        lineChart.setAnimated(false);
        return lineChart;
		
	}

	/**
	 * 
	 * update chart
	 * 
	 */
	@Override
	public void updateChart(int year) {
		updateLineChart(year);
	}

}