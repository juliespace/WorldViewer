package worldviewer.chart;

import javafx.scene.chart.PieChart;
import worldviewer.data.DataBank;

/**
 * Pie Chart Viewer 
 * @author qingjin
 *
 */
public class PieChartViewer extends ChartViewer {
	
	/**
	 * new chart
	 */
	@Override
	public PieChart newChart() {
		
        final PieChart pieChart = new PieChart();
                
        pieChart.setTitle(DataBank.getIndicatorMap().get(DataBank.getListOfSelectedIndicators().get(0)));
        pieChart.setAnimated(false);
        return pieChart;
		
	}
	
	
	/**
	 * update chart 
	 */
	@Override
	public void updateChart(int year) {
		super.updatePieChart(year);
	}
}
