package worldviewer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import worldviewer.chart.BubbleChartViewer;
import worldviewer.chart.ChartViewer;
import worldviewer.chart.LineChartViewer;
import worldviewer.chart.PieChartViewer;
import worldviewer.chart.ScatterChartViewer;
import worldviewer.chart.BarChartViewer;
import worldviewer.data.DataBank;

/**
 * main ui container contains all charts views, stages, and next scene 
 * 
 * @author huizhu
 *
 */
public class WorldViewerUI extends Application {
	private final MenuBar menuBar = WorldViwerSelector.getMenuBar();
	private final Slider slider = newSlider();
	private final Button playButton = newPlayButton();
	//private final ChartViewer[] charts = {new LineChartViewer()};
	private final ChartViewer[] charts = {new BubbleChartViewer(), new LineChartViewer(), new PieChartViewer(), new BarChartViewer(), new ScatterChartViewer()};
	private static int year; 
	
	/**
	 * Create a new slider
	 * @return
	 */
    public Slider newSlider() {
    	Slider slider = new Slider(1960, 2019, 0); 
    	slider.setShowTickLabels(true);
    	slider.setShowTickMarks(true);
    	slider.setMajorTickUnit(50);
    	slider.setMinorTickCount(5);
    	slider.setBlockIncrement(10);
    	return slider; 
   	}
    
    
    /**
     * Create a new button with the text "Stop"
     * @return
     */
    public Button newPlayButton() {
    	Button button = new Button("Stop");
    	
    	EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onPlayButtonClicked(event); 
			}
    	};
    	button.setOnAction(handler);
    	
    	return button; 
	}
 
    /**
     * When button is clicked, change the button text 
     * @param ae
     */
    public void onPlayButtonClicked(ActionEvent ae) {
    	if (isPlaying()) {
    		playButton.setText("Play");
    	} else {
    		playButton.setText("Stop");
    	}
    }
    
    
    /**
     * Animation is playing while button's text is "stop"
     * @return
     */
    public boolean isPlaying() {
    	return playButton.getText().equals("Stop"); 
    }

    
    /**
     * override start method from Application 
     */
	@Override 
    public void start(Stage stage) {
        stage.setTitle("World Indicator Viewer");
        newTimeline().play(); 
        stage.setScene(newScene());
        stage.show();
    }

	
	/**
	 * This method is called for animation
	 * @return
	 */
	public Timeline newTimeline() {
		EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onNextFrame(event); 
			}
		};
	
		KeyFrame keyframe = new KeyFrame(Duration.millis(200), handler); 
		Timeline timeline = new Timeline(keyframe); 
		timeline.setCycleCount(20000);
		timeline.setAutoReverse(true);
		
		return timeline;
	}
	
	
	/**
	 * Provide next KeyFrame for timeline 
	 * Update year value by 1 when isPlaying() is true 
	 * @param event
	 */
	public void onNextFrame(ActionEvent event) {
		if (isPlaying()) {
			year = (int) slider.getValue() + 1;
			if (year > 2019) 
				year = 1960; 
			slider.setValue(year);
			System.out.println("year " + year);
			for (ChartViewer eachChartViewer: charts) {
				eachChartViewer.updateChart(year); 
			}
		}
	}
	
	
	public static int getYear() {
		return year;
	}


	/**
	 * Provide a scene for display with control pane on top and chart pane on bottom
	 * @return
	 */
	public Scene newScene() {
		VBox vbox = new VBox(menuBar, newControlPane(), newChartPane()); 
		return new Scene(vbox); 
	}

	
	/**
	 * Create a new Control Pane with slider and playButton 
	 * @return
	 */
	public Pane newControlPane() {
		return new HBox(slider, playButton);
	}

	
	/**
	 * Create a chart pane with all charts
	 * @return
	 */
	public Pane newChartPane() {
		TilePane tile = new TilePane(); 
		for (ChartViewer chart: charts) {
			tile.getChildren().add(chart.getChart()); 
		}
		tile.setPadding(new Insets(5, 0, 5, 0));
		tile.setVgap(4);
		tile.setHgap(4);
		tile.setPrefColumns(3);
		tile.setStyle("-fx-background-color: DAE6F3;");
		return tile;
	}
	
	/**
	 * Launch the application 
	 * @param args
	 */
	public static void main(String[] args) {
        launch(args);
	}
}
