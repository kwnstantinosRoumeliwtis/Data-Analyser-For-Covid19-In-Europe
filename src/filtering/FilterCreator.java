package filtering;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import metadata.MetadataManagerInterface;

public class FilterCreator implements FilteringEngineInterface {
	
	private Map<String, List<String>> pAtomicFilters;
	private MetadataManagerInterface pMetadataManager;
	private List<String[]> Filter = new ArrayList<String[]>();

	public FilterCreator(Map<String, List<String>> pAtomicFilters, MetadataManagerInterface pMetadataManager) {

		this.pAtomicFilters = pAtomicFilters;
		this.pMetadataManager = pMetadataManager;
	}

	public int setupFilteringEngine(Map<String, List<String>> pAtomicFilters,
			MetadataManagerInterface pMetadataManager) {
		this.pAtomicFilters = pAtomicFilters;
		this.pMetadataManager = pMetadataManager;
		if (pAtomicFilters == null || pMetadataManager == null) {
			return -1;
		} else {
			return 0;
		}
	}

	public List<String[]> workWithFile() {

		String[][] arr = new String[this.pMetadataManager.getColumnNames().length][10000];
		String[] array = null;
		int[] positionarr = new int[this.pAtomicFilters.size()];
		int kr = 0;
		for (String field : pAtomicFilters.keySet()) {

			for (String fieldp : pMetadataManager.getFieldPositions().keySet()) {
				if (field.equals(fieldp)) {
					positionarr[kr] = pMetadataManager.getFieldPositions().get(fieldp);
					kr++;

				}
			}
		}

		String line;

		int z = 0;
		try {
			Scanner s = new Scanner(pMetadataManager.getDataFile());

			while (s.hasNextLine()) {
				int r = 0;
				line = s.nextLine();
				array = line.split(pMetadataManager.getSeparator());

				for (String field : pAtomicFilters.keySet()) {
					for (int i = 0; i < pAtomicFilters.get(field).size(); i++) {
						for (int l = 0; l < this.pAtomicFilters.size(); l++) {
							if (pAtomicFilters.get(field).get(i).equals(array[positionarr[l]])) {
								r++;
							}
						}

					}
				}
				if (r == pAtomicFilters.size()) {

					Filter.add(array);

				}

			}
			s.close();

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}

		
		return Filter;
	}

}
