package worldviewer;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import worldviewer.chart.BarChartViewer;
import worldviewer.chart.BubbleChartViewer;
import worldviewer.chart.ChartViewer;
import worldviewer.chart.LineChartViewer;
import worldviewer.chart.PieChartViewer;
import worldviewer.chart.ScatterChartViewer;
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
	private List<ChartViewer> charts = newChartViewers();
	private static int year; 
	private final Label note = newNote(); 
	private final Label yearLabel = newLabel(); 
	private final Label titleLabel = newLabel();
	private Stage stage;
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    double width = primaryScreenBounds.getWidth() * 0.9;
    double height = primaryScreenBounds.getHeight() * 0.9;
    
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
//    	slider.resize(40, 10);
    	return slider; 
   	}
    
    /**
     * 
     * new chart viewers
     * 
     * @return
     */
    List<ChartViewer> newChartViewers() {
    	List<ChartViewer> viewers = new ArrayList<>();
    	for (String c : WorldViwerSelector.selectedCharts()) {
    		switch (c) {
    		case "Pie Chart":
    			viewers.add(new PieChartViewer());
    			break;
    		case "Scatter Chart":
    			viewers.add(new ScatterChartViewer());
    			break;
    		case "Line Chart":
    			viewers.add(new LineChartViewer());
    			break;
    		case "Bubble Chart":
    			viewers.add(new BubbleChartViewer());
    			break;
    		case "Bar Chart":
    			viewers.add(new BarChartViewer());
    			break;
    		}
    	}
    	return viewers;
    }
    
    /**
     * 
     * refresh stage for selection changes
     * 
     */
    void refreshStage() {
    	charts = newChartViewers();
        stage.setScene(newScene());
    }
    
    
    /**
     * create new note
     * 
     * @return
     */
    public Label newNote() {
    	Label label = new Label("Note: select up to 2 indicators");
    	label.setAlignment(Pos.CENTER_LEFT);
    	return label;
    }
    
    public Label newLabel() {
    	Label yearLabel = new Label();
    	yearLabel.setFont(Font.font("American Typewriter", 40));
    	
    	return yearLabel;
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
        	DataBank.getListOfSelectedCountries().clear();
        	DataBank.getListOfSelectedCountries().addAll(WorldViwerSelector.selectedCountries());
        	DataBank.getListOfSelectedIndicators().clear();
        	DataBank.getListOfSelectedIndicators().addAll(WorldViwerSelector.selectedIndicators());
        	DataBank.updateValidData();
    		refreshStage();
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
        this.stage = stage;
        stage.setTitle("World Indicator Viewer");
        newTimeline().play();
        stage.setWidth(width);
        stage.setHeight(height);
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
			yearLabel.setText("Year " +Integer.toString(year));
//			System.out.println("year: " + year);
			for (ChartViewer eachChartViewer: charts) {
				eachChartViewer.updateChart(year); 
			}
		}
	}
	
	
	/**
	 * 
	 * get year
	 * 
	 * @return
	 */
	public static int getYear() {
		return year;
	}


	/**
	 * Provide a scene for display with control pane on top and chart pane on bottom
	 * @return
	 */
	public Scene newScene() {

		VBox vbox = new VBox(titlePane(), newControlPane(), newChartPane()); 
		return new Scene(vbox); 
	}

	
	public Pane titlePane() {
		titleLabel.setText("World Indicator Viewer");
		titleLabel.setFont(Font.font("American Typewriter", 28));
	
		HBox newBox = new HBox(titleLabel); 
		newBox.setAlignment(Pos.CENTER);
		return newBox; 
	}
	/**
	 * Create a new Control Pane with slider and playButton 
	 * @return
	 */
	public Pane newControlPane() {
		Label space0 = new Label(); 
		space0.setPrefWidth(30);
		Label space1 = new Label(); 
		space1.setPrefWidth(30);
		Label space2 = new Label(); 
		space2.setPrefWidth(80);
		Label space3 = new Label(); 
		space3.setPrefWidth(80);
		HBox newBox = new HBox(note, space0, menuBar,space1, yearLabel, space2, slider, space3, playButton); 
		newBox.setAlignment(Pos.CENTER);
		return newBox;
	}

	
	/**
	 * Create a chart pane with all charts
	 * @return
	 */
	public ScrollPane newChartPane() {
		TilePane tile = new TilePane(); 
		for (ChartViewer chart: charts) {
			tile.getChildren().add(chart.getChart()); 
		}
		tile.setPadding(new Insets(5, 0, 5, 0));
		tile.setVgap(4);
		tile.setHgap(4);
		tile.setPrefColumns(3);
		tile.setStyle("-fx-background-color: DAE6F3;");
		tile.setPrefWidth(width);
		tile.setPrefHeight(height);
		return new ScrollPane(tile);
	}
	
	/**
	 * Launch the application 
	 * @param args
	 */
	public static void main(String[] args) {
        launch(args);
	}
}
