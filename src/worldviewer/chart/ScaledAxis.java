package worldviewer.chart;

import javafx.scene.chart.NumberAxis;

/**
 * This class creates axis after scaled. 
 * It also provide the scale that should be implemented during display
 * 
 * @author huizhu
 *
 */
public class ScaledAxis {
	final NumberAxis axis; 
	final long scale; 
	
	public ScaledAxis(double min, double max) {
		this.axis = null; 
		this.scale = findScale(min, max, 100); 
	}
	
	public ScaledAxis(double min, double max, String label) {
		this.scale = findScale(min, max, 1000); 
		min /= scale; 
		max /= scale; 
		this.axis = new NumberAxis(min, max, (max - min)/5); 
		
		if (scale >1)
			label = String.format("%s (x%s)", label, scale); 
		axis.setLabel(label);
	}
	
	/**
	 * find scale 
	 * 
	 * @param min
	 * @param max
	 * @param displayMax
	 * @return
	 */
	public long findScale(double min, double max, int displayMax) {
		long scale; 
		
		for (scale = 1; scale < Long.MAX_VALUE; scale *= 10) {
			if (max / scale <= displayMax) 
				break; 
		}
		
		return scale;
	}

	/**
	 * get axis 
	 * 
	 * @return
	 */
	public NumberAxis getAxis() {
		return axis;
	}

	
	/**
	 * get scale 
	 * @return
	 */
	public long getScale() {
		return scale;
	}
	
}
