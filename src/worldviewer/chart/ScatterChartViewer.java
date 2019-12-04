package worldviewer.chart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.NumberAxis;
import worldviewer.WorldViewerUI;
import worldviewer.data.CountryIndicatorData;
import worldviewer.data.DataBank;

/**
 * scatter chart viewer
 * @author huizhu
 *
 */
public class ScatterChartViewer extends ChartViewer {
	@Override
	public ScatterChart<Number, Number> newChart() {
		List<String> threeIndicators = DataBank.getListOfSelectedIndicators();
		List<Double> xMaxes = new ArrayList<Double>();
		List<Double> xMins = new ArrayList<Double>();
		List<Double> yMaxes = new ArrayList<Double>();
		List<Double> yMins = new ArrayList<Double>();

		for (CountryIndicatorData eachData : DataBank.getValidData()) {
			if (eachData.getIndicatorCode().equals(threeIndicators.get(0))) {
				xMaxes.add(findMax(eachData.getYearlyData()));
				xMins.add(findMin(eachData.getYearlyData()));
			} else if (eachData.getIndicatorCode().equals(threeIndicators.get(1))) {
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

		final ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(xAxis, yAxis);
		xAxis.setLabel(DataBank.getIndicatorMap().get(threeIndicators.get(0)));
		yAxis.setLabel(DataBank.getIndicatorMap().get(threeIndicators.get(1)));

		scatterChart.setTitle("Scatter Chart");
		scatterChart.setAnimated(false);

		return scatterChart;
		
	}

	@Override
	public void updateChart(int year) {
		super.updateNNChart(year);
	}
}
