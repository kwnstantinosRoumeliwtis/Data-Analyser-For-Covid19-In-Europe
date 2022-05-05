package file.manager;

import java.io.File;

public class FileNew {

	private String pAlias;
	private String pPath;
	private String pSeparator;
	private File file;

	public FileNew(String pAlias, String pPath, String pSeparator) {
		this.pAlias = pAlias;
		this.pPath = pPath;
		this.pSeparator = pSeparator;
		this.file = new File(pPath);

	}

	public String getAlias() {
		return this.pAlias;
	}

	public String getSeparator() {
		return this.pSeparator;
	}

	public File getFile() {
		return this.file;
	}

}
