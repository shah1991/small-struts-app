package net.slide.util;

import java.io.BufferedInputStream;


import java.io.BufferedOutputStream;

import java.io.File;


import java.io.FileInputStream;


import java.io.FileOutputStream;


import java.io.IOException;

import java.io.InputStream;


import java.io.OutputStream;

import java.util.Enumeration;

import java.util.zip.ZipEntry;


import java.util.zip.ZipFile;


import java.util.zip.ZipInputStream;


public class ExtractFileSubDirectories {
  


        public static void main(String args[])

        {

         //fetch command line argument


                 String strZipFile ="F:\\exam.zip";


                                 

                 if(strZipFile == null || strZipFile.equals(""))


                 {


                        System.out.println("Invalid source file");

                        System.exit(0);

                 }


                unzip(strZipFile);

                

                System.out.println("Zip file extracted!");


        }


        private static void unzip(String strZipFile) {

                

                try


                {

                        File fSourceZip = new File(strZipFile);

                        String zipPath = strZipFile.substring(0, strZipFile.length()-4);

                        File temp = new File(zipPath);


                        temp.mkdir();


                        System.out.println(zipPath + " created");




                        ZipFile zipFile = new ZipFile(fSourceZip);


                        Enumeration e = zipFile.entries();


                        

                     while(e.hasMoreElements())

                   {

                           ZipEntry entry = (ZipEntry)e.nextElement();
                           String fileName[]=entry.getName().split("/");
                         int len=fileName.length-1;
                          		
                          		String sss=fileName[len].toString();
                          		System.out.println(sss);
                          	String ext[]=sss.split(".");
                          	int lenn=ext.length;
                          	System.out.println(lenn);

                           File destinationFilePath = new File(zipPath,entry.getName());

                           	
                           
 
                          // System.out.println("------>"+entry.getName());

                                //create directories if required.


                                destinationFilePath.getParentFile().mkdirs();


                                


                                //if the entry is directory, leave it. Otherwise extract it.


                                if(entry.isDirectory())


                                {

                                    continue;

                                }


                                else


                                {


                                        System.out.println("Extracting " + destinationFilePath);


                                        

                                 BufferedInputStream bis = new BufferedInputStream(zipFile


                                                                                                                        .getInputStream(entry));

                                                                                                                        


                                        int b;

                                        byte buffer[] = new byte[1024];


 

                                        /*


                                         * read the current entry from the zip file, extract it


                                         * and write the extracted file.


                                         */

                                 FileOutputStream fos = new FileOutputStream(destinationFilePath);


                                        BufferedOutputStream bos = new BufferedOutputStream(fos,


                                                                        1024);


 


                                        while ((b = bis.read(buffer, 0, 1024)) != -1) {


                                                        bos.write(buffer, 0, b);

                                        }


                                        


                                        //flush the output stream and close it.


                                        bos.flush();


                                        bos.close();
                                  


                                        //close the input stream.


                                        bis.close();


                                }

                        }


                }


                catch(IOException ioe)

           {


                        System.out.println("IOError :" + ioe);


                }


                


        }


}


 


