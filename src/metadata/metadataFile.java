package metadata;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class metadataFile implements MetadataManagerInterface{
	private String pAlias;
	private File mFile;
	private String pSeparator;
	private Map<String,Integer> positions;
	
	
	public metadataFile(String pAlias, File mFile, String pSeparator) {
		this.pAlias=pAlias;
		this.pSeparator=pSeparator;
		if (mFile.exists()) {
		this.mFile=new File(mFile.getPath());
		}
		else {
			throw new NullPointerException();
			
		}
		
	}
	
	
	
	
	public  Map<String, Integer> getFieldPositions(){
		 String [] array=null;
		 positions = new HashMap<String,Integer>();
		 try { 
		        Scanner s = new Scanner(this.mFile);  
		        String line= s.nextLine();
		        array=line.split(this.pSeparator);
		        s.close();
		        for (int i=0; i<array.length;i++) {
		        	this.positions.put(array[i], i);
		        }
		    } catch (IOException e) {
		        System.out.println("lathos");
		    }	
		 
		 return positions;		
		
	}
	
	
	
	
	public File getDataFile()  {
		return this.mFile;
	}
	
	public String getSeparator() {
		return this.pSeparator;
	}
	
	public String[] getColumnNames() {
		 
		 String [] array=null;		 
		 try { 
		        Scanner s = new Scanner(this.mFile);  
		        String line= s.nextLine();
		        array=line.split(this.pSeparator);
		        s.close();
		    } catch (IOException e) {
		        System.out.println("error");
		    }		
		
		return array;
	}
	
	
	
	public String getAlias() {
		return this.pAlias;
	}


}
