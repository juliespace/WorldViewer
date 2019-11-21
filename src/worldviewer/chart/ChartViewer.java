package worldviewer.chart;

import java.util.List;
import javafx.scene.chart.Chart;
import worldviewer.data.CountryIndicatorData;

public interface ChartViewer {
	Chart newChart(List<String> countries, List<String> indicators, List<CountryIndicatorData> data);
	void setYear(int year);
}
