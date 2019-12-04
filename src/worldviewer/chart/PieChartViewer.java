package worldviewer.chart;

import javafx.scene.chart.PieChart;

/**
 * Pie Chart Viewer 
 * @author huizhu
 *
 */
public class PieChartViewer extends ChartViewer {
	@Override
	public PieChart newChart() {
		
        final PieChart pieChart = new PieChart();
                
        pieChart.setTitle("Pie Chart");
        pieChart.setAnimated(false);
        return pieChart;
		
	}

	@Override
	public void updateChart(int year) {
		super.updatePieChart(year);
	}
}
