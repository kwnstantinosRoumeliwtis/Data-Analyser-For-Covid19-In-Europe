package file.manager;

import java.io.File;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import filtering.FilterCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import metadata.metadataFile;

public class FileCreator implements StructuredFileManagerInterface {

	private ArrayList<FileNew> fileList = new ArrayList<FileNew>();

	public File registerFile(String pAlias, String pPath, String pSeparator) throws IOException, NullPointerException {

		if (pSeparator != "-" && pSeparator != ",") {
			throw new IOException("wrong separator");
		}
		File file = new File(pPath);
		if (!file.exists()) {
			throw new NullPointerException("this file not exists");
		}
		FileNew nFile = new FileNew(pAlias, pPath, pSeparator);
		fileList.add(nFile);
		return file;
	}

	public String[] getFileColumnNames(String pAlias) throws NullPointerException {
		boolean flag = false;
		String[] array = null;
		if (pAlias == null) {
			return new String[] {};
		}

		FileNew fileElement = new FileNew("", "", "");

		for (FileNew nf : fileList) {
			if (nf.getAlias().equals(pAlias)) {
				fileElement = nf;
				flag = true;
				break;
			}
		}
		if (flag == false) {
			return new String[] {};
		}

		try {
			Scanner s = new Scanner(fileElement.getFile());
			String line = s.nextLine();
			array = line.split(fileElement.getSeparator());
			s.close();
		} catch (IOException e) {
			assertNull(array);
			return array;
		}

		return array;
	}

	public ArrayList<FileNew> getFileList() {
		return this.fileList;
	}

	public List<String[]> filterStructuredFile(String pAlias, Map<String, List<String>> pAtomicFilters) {
		String path = "";
        String sep="";
		for (FileNew nf : fileList) {
			if (nf.getAlias().equals(pAlias)) {
				path = nf.getFile().getPath();
				sep=nf.getSeparator();
			}
		}
		File k = new File(path);
		metadataFile a = new metadataFile(pAlias, k,sep );
		FilterCreator filter = new FilterCreator(pAtomicFilters, a);
		filter.setupFilteringEngine(pAtomicFilters, a);

		return filter.workWithFile();

	}

	public int printResultsToPrintStream(List<String[]> recordList, PrintStream pOut) {
		try {
			for (int i = 0; i < recordList.size(); i++) {
				for (int j = 0; j < recordList.get(i).length; j++) {
					if (j == recordList.get(i).length - 1) {
						pOut.printf(recordList.get(i)[j]);
					} else {
						pOut.printf(recordList.get(i)[j] + ",");
					}
				}
				pOut.printf("\n");
			}
			pOut.close();

		} catch (Exception e) {
			return -1;
		}
		return recordList.size();
	}

	public ArrayList<FileNew> getFiles() {
		return this.fileList;
	}
}
