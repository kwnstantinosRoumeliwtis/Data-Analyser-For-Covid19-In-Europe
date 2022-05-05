package application.naive.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import file.manager.StructuredFileManagerInterface;
import file.manager.FileCreator;

public class DataAnalysis {

	private static Map<String, List<String>> atomicFilters;

	public static void main(String[] args) throws NullPointerException, IOException {
		NaiveApplicationController k = new NaiveApplicationController();

		k.registerFile("dataFile", "./test/resources/input/CovidData.csv", ",");
		atomicFilters = new HashMap<String, List<String>>();
		List<String> countryFilter = new ArrayList<String>();
		countryFilter.add("Austria");
		

		atomicFilters.put("GEO:countriesAndTerritories", countryFilter);
		Map<String, List<String>> multiCriteriaAtomicFilters = new HashMap<String, List<String>>(atomicFilters);
		List<String> timeFilter = new ArrayList<String>();
		timeFilter.add("9");
		List<String> timeFilter1 = new ArrayList<String>();
		timeFilter1.add("27");
		timeFilter1.add("22");
		timeFilter1.add("16");
		timeFilter1.add("14");
		timeFilter1.add("10");
		timeFilter1.add("1");

		multiCriteriaAtomicFilters.put("DAY:day", timeFilter1);
		multiCriteriaAtomicFilters.put("MONTH:month", timeFilter);

		k.showSingleSeriesBarChart("dataFile", k.executeFilterAndShowJTable("dataFile", multiCriteriaAtomicFilters, "chart"),
				"DAY:day", "MSR:deaths", "m");

		k.showSingleSeriesLineChart("dataFile", k.executeFilterAndShowJTable("dataFile", multiCriteriaAtomicFilters, "chart1"),
				"DAY:day", "MSR:deaths", "r");

	}
}
