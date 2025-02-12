package worldviewer;

import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * ui component to allow user select countries / indicators / chars to show
 * and memorize selected items
 * 
 * @author qing
 *
 */
public class WorldViwerSelector {
	
	static List<String> listOfSelectedCountries = new ArrayList<String>(Arrays.asList("ARB", "CHN", "EUU"));
	static List<String> listOfSelectedIndicators = new ArrayList<String>(Arrays.asList("EN.ATM.CO2E.KT", "NY.GDP.MKTP.CD", "SP.POP.TOTL"));
	static List<String> listOfSelectedCharts = new ArrayList<String>(Arrays.asList("Pie Chart",
			"Scatter Chart", "Line Chart", "Bubble Chart", "Bar Chart"));
	
	static Map<String, String> country_code_map = new LinkedHashMap<String, String>() {
		{
			put("Arab World", "ARB");
			put("Australia", "AUS");
			put("Canada", "CAN");
			put("China", "CHN");
			put("Denmark", "DNK");
			put("European Union", "EUU");
			put("France", "FRA");
			put("Germany", "DEU");
			put("India", "IND");
			put("Japan", "JPN");
		}
	};
	
	static Map<String, String> indicator_code_map = new LinkedHashMap<String, String>() {
		{
			put("CO2 emission", "EN.ATM.CO2E.KT");
			put("GDP", "NY.GDP.MKTP.CD");
			put("Population", "SP.POP.TOTL");
			put("Urban population", "SP.URB.TOTL");
			put("Rural population", "SP.RUR.TOTL");
			put("Food production index", "AG.PRD.FOOD.XD");
			put("Life expectancy", "SP.DYN.LE00.IN");
			put("Birth rate", "SP.DYN.CBRT.IN");
			put("Death rate", "SP.DYN.CDRT.IN");
			put("Merchandise exports", "TX.VAL.MRCH.CD.WT");
			put("Merchandise imports", "TM.VAL.MRCH.CD.WT");
		}
	};
	
	static Menu countryMenu = new Menu("Country");
	static Menu indicatorMenu = new Menu("Indicator");
	static Menu chartMenu = new Menu("Chart");
	
	static ArrayList<String> countryList = new ArrayList<String>(Arrays.asList("Arab World",
			"Australia", "Canada", "China" ,"Denmark", "European Union", "France", "Germany",
			"India", "Japan"));
	static ArrayList<String> indicatorList = new ArrayList<String>(Arrays.asList("CO2 emission", "GDP", "Population", "Urban population",
			"Rural population", "Food production index", "Life expectancy", "Birth rate",
			"Death rate", "Merchandise exports", "Merchandise imports"));
	static ArrayList<String> chartList = new ArrayList<String>(Arrays.asList("Pie Chart",
			"Scatter Chart", "Line Chart", "Bubble Chart", "Bar Chart"));

	static HashMap<String, CheckBox> country_checkbox_map = new HashMap<String, CheckBox>();
	static HashMap<String, CheckBox> indicator_checkbox_map = new HashMap<String, CheckBox>();
	static HashMap<String, CheckBox> chart_checkbox_map = new HashMap<String, CheckBox>();
	
	static MenuBar menuBar = new MenuBar();
	
	
	/**
	 * add selected countries/indicators/charts to lists which will be used later to draw charts
	 * @param ae
	 */
	public static void onCountryClicked(ActionEvent ae) {
		listOfSelectedCountries.clear();
		for (Map.Entry<String,CheckBox> entry : country_checkbox_map.entrySet()) { 
            if (entry.getValue().isSelected()) {
            	listOfSelectedCountries.add(country_code_map.get(entry.getKey()));
            }
		}
		printStatus();
    }
	
	public static void onIndicatorClicked(ActionEvent ae) {
		listOfSelectedIndicators.clear();
		for (Map.Entry<String,CheckBox> entry : indicator_checkbox_map.entrySet()) { 
            if (entry.getValue().isSelected()) {
            	listOfSelectedIndicators.add(indicator_code_map.get(entry.getKey()));
            }
		}
		printStatus();
    }
	
	public static void onChartClicked(ActionEvent ae) {
		listOfSelectedCharts.clear();
		for (Map.Entry<String,CheckBox> entry : chart_checkbox_map.entrySet()) { 
            if (entry.getValue().isSelected()) {
            	listOfSelectedCharts.add(entry.getKey());
            }
		}
		printStatus();
    }
	
	
	/**
	 * print all the selected items
	 */
	public static void printStatus() {
		System.out.println("Selected Countries:");
		for (String country:listOfSelectedCountries) {
			System.out.println(country + ", ");
		}
		
		System.out.println("Selected Indicators:");
		for (String indicator:listOfSelectedIndicators) {
			System.out.println(indicator + ", ");
		}
		
		System.out.println("Selected Charts:");
		for (String chart:listOfSelectedCharts) {
			System.out.println(chart + ", ");
		}
		
	}
	
	
	/**
	 * set up menus for country/indicator/chart
	 * include graphic and options for each menu 
	 */
	public static void setupCountryMenu() {
		ImageView globalImage = new ImageView("file:global_icon.png");
		globalImage.setFitWidth(40);
		globalImage.setFitHeight(40);
		countryMenu.setGraphic(globalImage);
		
		for (String countryName: countryList) {
			CheckBox countryCheckBox = new CheckBox(countryName);
			countryCheckBox.setStyle("-fx-text-fill: -fx-text-base-color");
			country_checkbox_map.put(countryName, countryCheckBox);
			CustomMenuItem countryMenuItem = new CustomMenuItem(countryCheckBox);
			countryMenu.getItems().add(countryMenuItem);
			EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onCountryClicked(event); 
				}
	    	};
	    	countryCheckBox.setOnAction(handler);
	    	if (listOfSelectedCountries.contains(country_code_map.get(countryName)))
	    		countryCheckBox.setSelected(true);
			countryMenuItem.setHideOnClick(false);
		}
	}
	
	public static void setupIndicatorMenu() {
		ImageView indicatorImage = new ImageView("file:development_icon.png");
		indicatorImage.setFitWidth(40);
		indicatorImage.setFitHeight(40);
		indicatorMenu.setGraphic(indicatorImage);
		
		for (String indicatorName: indicatorList) {
			CheckBox indicatorCheckBox = new CheckBox(indicatorName);
			indicatorCheckBox.setStyle("-fx-text-fill: -fx-text-base-color");
			indicator_checkbox_map.put(indicatorName, indicatorCheckBox);
			CustomMenuItem indicatorMenuItem = new CustomMenuItem(indicatorCheckBox);
			indicatorMenu.getItems().add(indicatorMenuItem);
			EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onIndicatorClicked(event); 
				}
	    	};
	    	indicatorCheckBox.setOnAction(handler);
	    	if (listOfSelectedIndicators.contains(indicator_code_map.get(indicatorName)))
	    		indicatorCheckBox.setSelected(true);
			indicatorMenuItem.setHideOnClick(false);
		}
	}
	
	public static void setupChartMenu() {
		ImageView chartImage = new ImageView("file:chart_icon.png");
		chartImage.setFitWidth(40);
		chartImage.setFitHeight(40);
		chartMenu.setGraphic(chartImage);
		
		for (String chartName: chartList) {
			CheckBox chartCheckBox = new CheckBox(chartName);
			chartCheckBox.setStyle("-fx-text-fill: -fx-text-base-color");
			chart_checkbox_map.put(chartName, chartCheckBox);
			CustomMenuItem chartMenuItem = new CustomMenuItem(chartCheckBox);
			chartMenu.getItems().add(chartMenuItem);
			EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					onChartClicked(event); 
				}
	    	};
	    	chartCheckBox.setOnAction(handler);
	    	if (listOfSelectedCharts.contains(chartName))
	    		chartCheckBox.setSelected(true);
			chartMenuItem.setHideOnClick(false);
		}
	}
	
	
	
	/**
	 * set up menu bar which include country menu/indicator menu/chart menu
	 */
	public static void setupMenuBar() {
		setupCountryMenu();
		setupIndicatorMenu();
		setupChartMenu();
		
		menuBar.getMenus().add(countryMenu);
		menuBar.getMenus().add(indicatorMenu);
		menuBar.getMenus().add(chartMenu);
		
		printStatus();
	}
	
	/**
	 * call this method in WorldViewerUI class to add menu bar into UI
	 * @return
	 */
	public static MenuBar getMenuBar() {
		setupMenuBar();
		return menuBar;
	}
	
	
	/**
	 * this method is called when we need to know which country/indicator/chart is selected
	 * @return
	 */
	public static List<String> selectedCountries(){
		return listOfSelectedCountries;
	}
	
	public static List<String> selectedIndicators(){
		return listOfSelectedIndicators;
	}
	
	public static List<String> selectedCharts(){
		return listOfSelectedCharts;
	}
	
	
}
