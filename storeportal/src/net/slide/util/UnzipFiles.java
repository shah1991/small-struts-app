package net.slide.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;


public class UnzipFiles {
	
	public static void main(String args[]) throws IOException{
		UnzipFiles u=new UnzipFiles();
		u.doUnzip("F:\\exam.zip", "F:\\examampl");
	}
	
	
	public void doUnzip(String inputZip, String destinationDirectory) throws IOException {
		
		int BUFFER = 2048;
		ArrayList<List> zipFiles = new ArrayList();
		File sourceZipFile = new File(inputZip);
		File unzipDestinationDirectory = new File(destinationDirectory);
		unzipDestinationDirectory.mkdir();
	
		ZipFile zipFile;
		// Open Zip file for reading
		zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

		// Create an enumeration of the entries in the zip file
		Enumeration zipFileEntries = zipFile.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

			String currentEntry = entry.getName();

			File destFile = new File(unzipDestinationDirectory, currentEntry);
			destFile = new File(unzipDestinationDirectory, destFile.getName());

			if (currentEntry.endsWith(".zip")) {
				((ArrayList) zipFiles).add(destFile.getAbsolutePath());
			}

			// grab file's parent directory structure
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			try {
				// extract file if not a directory
				if (!entry.isDirectory()) {
					BufferedInputStream is = new BufferedInputStream(zipFile
							.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest = new BufferedOutputStream(fos,
							BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		zipFile.close();
		

		for (Iterator iter = ((AbstractList) zipFiles).iterator(); iter.hasNext();) {
			String zipName = (String) iter.next();
			doUnzip(zipName, destinationDirectory + File.separatorChar
					+ zipName.substring(0, zipName.lastIndexOf(".zip")));
		}

	}

}
