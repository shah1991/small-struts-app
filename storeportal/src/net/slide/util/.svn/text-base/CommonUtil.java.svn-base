package net.slide.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Properties;

public class CommonUtil {
	private static final String GALLARY_PROPERTY_FILE = "/gallary.properties";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String datafile = "D:\\Gokul\\Images\\Winter1.jpg";
		getCheckSum(datafile);
		convertFileToByteArray(datafile, 0, 0);

	}
	
	public static String getCheckSum(String path) throws Exception {
				 
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    FileInputStream fis = new FileInputStream(path);
	    byte[] dataBytes = new byte[1024];
	 
	    int nread = 0; 
	 
	    while ((nread = fis.read(dataBytes)) != -1) {
	      md.update(dataBytes, 0, nread);
	    };
	 
	    byte[] mdbytes = md.digest();
	 
	    //convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	 
	    System.out.println("Digest(in hex format):: " + sb.toString());
	    return sb.toString();

	}
	
	
	public static String getCheckSum(byte[] b) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		md.update(b);
		
		 byte[] mdbytes = md.digest();
		 StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++) {
		    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    }
	     return sb.toString();
	   }

	
	
	
	public static byte[] convertFileToByteArray(String path, int chunkOffset, int chuckSize) {
		
		File file = new File(path);
		if(file == null || file.exists() == false) {
			return new byte[]{};
		}
		
		int allocsize = 0;
		if((file.length() - chunkOffset) > chuckSize)
			allocsize = chuckSize;
		else
			allocsize = (int)(file.length()-chunkOffset);

		byte[] b = new byte[allocsize];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.skip(chunkOffset);
			fileInputStream.read(b, 0, allocsize);
		} catch (FileNotFoundException e) {
		System.out.println("File Not Found.");
		e.printStackTrace();
		}
		catch (IOException e1)
		{
		System.out.println("Error Reading The File.");
		e1.printStackTrace();
		}
		
		return b;
	}
	
	public static Long getFileSize(String path) {
		
		File file = new File(path);
		
		Long fileSize = (long) 0;
		
		if(file != null && file.exists()) {
			fileSize = file.length();
		}
		
		return fileSize;
	}
	
	 public static Properties loadProperty() throws IOException {
	    
		InputStream inputStream  = CommonUtil.class.getResourceAsStream(GALLARY_PROPERTY_FILE);
    	Properties props = new Properties();
        props.load(inputStream);    
        inputStream.close();
        
        return props;
    }
	 
	 public static int[] convertInt(String paramId) {
			if(paramId.equals("false"))
				return null;

			String res[] = paramId.split(",");
			int[] gid = new int[res.length];
			int i=0;
			for(String val: res)
				gid[i++] = Integer.parseInt(val.trim());
			return gid;
		}

}
