package worldviewer.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import worldviewer.WorldViewerUI;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

public class LineChartViewer extends ChartViewer {

	@Override
	public LineChart<Number, Number> newChart() {
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
		
		//final NumberAxis xAxis = new NumberAxis(WorldViewerUI.getYear(), WorldViewerUI.getYear() + 9, 9);
		final NumberAxis xAxis = new NumberAxis(1960, 2019, 59);
		
		final NumberAxis yAxis = new NumberAxis(yMin, yMax, yMax - yMin);
		
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Line Chart");
        lineChart.setAnimated(false);
        return lineChart;
		
	}

	@Override
	public void updateChart(int year) {
		super.updateLineChart(year);
		NumberAxis axis = (NumberAxis) super.getXYChart().getXAxis();
		axis.setLowerBound(WorldViewerUI.getYear());
		axis.setUpperBound(WorldViewerUI.getYear()+9);
	}

}