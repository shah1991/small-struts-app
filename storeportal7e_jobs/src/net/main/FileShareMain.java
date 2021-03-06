package net.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;


import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;





import net.slide.pojo.TblFileGroup;
import net.slide.pojo.TblFileShare;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblUploadRes;
import net.utils.DateUtils;
import net.utils.HibernateUtil;

import org.apache.commons.io.FileUtils;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class FileShareMain {

	/**
	 * @param args
	 */
	
	private static String INPATH = "/home/system/interface/in";
	private static String ARCHIVE ="/home/system/interface/in/archive";
	
	//private static String SERVER_FOLDER = "D:/workspaces-svn/storeportal-7e-dev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/storeportal/";
	//private static String SERVER_FOLDER = "/home/system/storeportal7e/"; //UAT
	private static String SERVER_FOLDER = "/home/system/storeportal711/"; //PROD	
	private static String storepath="uploads/store/";
	private static String storeCodePrefix="";
	public static void main(String[] args) {
		FileShareMain fileShareMain=new FileShareMain();
		fileShareMain.uploadZip();
	}
	
	public  void uploadZip(){
		Properties prop = new Properties();

		try {
			// load a properties file from class path, inside static method
			prop.load(getClass().getClassLoader().getResourceAsStream(
					"Config.properties"));
			storeCodePrefix=prop.getProperty("store.code.prefix");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		ArrayList<TblUploadRes> uploadRes=new ArrayList<TblUploadRes>();
		List<File> docAttach = new ArrayList<File>();
		List<String> docAttachFileName = new ArrayList<String>();
		
		//String rootpath = "/home/system";
	
		
		
		File IN_DIRECTORY = new File(INPATH);
		File[] files = IN_DIRECTORY.listFiles(new ZIPFileFilter());
		System.out.println(new Date()+": ZIP files.length="+files.length);
		for (File file : files) {					
			System.out.println(new Date()+" file="+file.getName());
				try{
					System.out.println("uploadZip .......");
					/* PLANOGRAM files need to be deleted and new set of files should be loaded */
					if(file.getName().contains("PLANOGRAM") || file.getName().contains("planogram")){
						System.out.println("*** File contains PLANO in .zip file ***");
						ArrayList<TblFileShare> fsList = getFileSharesByGroup(16);  // need to change according to production
						deleteFileShares(fsList);
					}
					/* PLANOGRAM files need to be deleted and new set of files should be loaded */
					
					//String postAttachPath = importFile(file, file.getName());
					uploadRes=doUnzip(file,storepath);
					savePost(uploadRes,new TblUser(), "Y","N");
					archive(file);
				}
				catch(Exception e)
				{System.out.println(new Date()+": Exception : "+e);}
				
		}//for File
	
	}
	
/*	public static String importFile(File mfile, String filename) {
		String retval = null;
		
		try{
			
			String rootpath = "/home/system";
		
			String path = "/archive";
			if(mfile.isFile() == false)
				return retval;
				
			File rp = new File(rootpath+path);
			if(rp == null || rp.exists() == false)
				rp.mkdir();
			
			File importpath= new File(rootpath+path+"/");
			if(importpath == null || importpath.exists() == false)
				importpath.mkdir();
			
			File newpath = new File(rootpath+path+"/"+filename);
			FileUtils.copyFile(mfile, newpath);
			if(newpath != null && mfile.isFile()){
				
				retval = path+"/"+filename;
			}

		}catch (Exception e) {
			System.out.println(e);
		}
		return retval;
	}
	*/
	
	 public static ArrayList<TblUploadRes> doUnzip(File inputZip, String destinationDirectory) throws Exception {
		 
		 System.out.println(new Date()+": doUnzip() *** Start *** ZIP file name :"+inputZip.getName());
		 ArrayList<TblUploadRes> uploadRes=new ArrayList<TblUploadRes>();
		 try{
		 //Session session = HibernateUtil.currentSession();
			//String rootpath = "/home/system";
			

	    	//String SERVER_FOLDER = "D:/workspaces-svn/storeportal-7e-dev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/storeportal/";	
			int BUFFER = 2048;
			ArrayList<List> zipFiles = new ArrayList();
			
			//File sourceZipFile = new File(inputZip);
			File sourceZipFile = inputZip;
			String fileName=sourceZipFile.getName();

			String fName[]=fileName.split("\\.(?=[^\\.]+$)");
			
			destinationDirectory=destinationDirectory+fName[0]+"/";
			File unzipDestinationDirectory = new File(SERVER_FOLDER+destinationDirectory);
			if(unzipDestinationDirectory == null || unzipDestinationDirectory.exists() == false)
			unzipDestinationDirectory.mkdir();
		
			ZipFile zipFile;
			// Open Zip file for reading
			zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

			// Create an enumeration of the entries in the zip file
			Enumeration zipFileEntries = zipFile.entries();
		//	int kk=0;
			
			
			// Process each entry
			while (zipFileEntries.hasMoreElements()) {
			try{	
				TblUploadRes res=new TblUploadRes();
				// grab a zip file entry
				ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

				String currentEntry = entry.getName();
				
				File destFile = new File(unzipDestinationDirectory, currentEntry);
				destFile = new File(unzipDestinationDirectory, destFile.getName());

				System.out.println(new Date()+": destFile Name: "+destFile.getName());
/*				if (currentEntry.endsWith(".zip")) {
					((ArrayList) zipFiles).add(destFile.getAbsolutePath());
				}
*/
				// grab file's parent directory structure
				File destinationParent = destFile.getParentFile();
				
				System.out.println(new Date()+": destinationParent: "+destinationParent);

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
				} catch (Exception ioe) {
					ioe.printStackTrace();
					System.out.println(new Date()+": Exception : "+ioe);
				}
				if(destFile.getName().contains("_")){
				String storeCode[]=destFile.getName().split("_");
				if(storeCode[0]!=null){
					////if(destFile.getName().contains("DD_SE") && destFile.getName().contains("RTE")){ //future file Format of EDD   : 737DD_SE_20150725_819843712RTE.pdf ,  Current Format of EDD: DD_SE737_20150725_819843712RTE.pdf
					if(destFile.getName().contains("_SEDD_")){ //WMS EDO files	
						res.setStoreCode(destFile.getName().substring(0, 3));
						res.setFileGroupId(15);
					}else if(destFile.getName().toLowerCase().contains("prod_rank")){ // MMDS product rank reports  : 001_prod_rank_by_qty.csv 
							res.setStoreCode(storeCode[0]);
							res.setFileGroupId(12);	
					}else if(destFile.getName().contains("_alloc.xls")){ // MMDS allocation files  : 001_alloc.csv 
						res.setStoreCode(storeCode[0]);
						res.setFileGroupId(13);			
					}else if(destFile.getName().contains("_VanRcvRpt-711.pdf")){ // supplier portal 001_VanRcvRpt-711.pdf 
						res.setStoreCode(storeCode[0]);
						res.setFileGroupId(14);			
					}else if(destFile.getName().contains("_PLANOGRAM_") && destFile.getName().contains(".pdf")){ //  spaceman planogram files with .pdf 
						res.setStoreCode(storeCode[0]);
						res.setFileGroupId(16);				
					}else{
						res.setStoreCode(storeCode[0]);
						res.setFileGroupId(8);
					}
				
				}
				}
				
				if(res.getStoreCode()!=null){
				res.setAttachName(destFile.getName());
				res.setAttachPath(destinationDirectory+destFile.getName());
				
				
				uploadRes.add(res);
				}
				
			}
			catch(Exception e){
				System.out.println(new Date()+": Exception :"+e);
			}
			}//while
			zipFile.close();
			
/*
			for (Iterator iter = ((AbstractList) zipFiles).iterator(); iter.hasNext();) {
				String zipName = (String) iter.next();
				doUnzip(zipName, destinationDirectory + File.separatorChar
						+ zipName.substring(0, zipName.lastIndexOf(".zip")));
			}
		*/	
			
		 }//try
		 catch(Exception e)
		 {
			 System.out.println(new Date()+": Exception :"+e);
		 }
			System.out.println(new Date()+": doUnzip() *** End *** ZIP file name :"+inputZip.getName());

			return uploadRes;

		}
	 
		public static boolean savePost(ArrayList<TblUploadRes> uploadList, TblUser tblUser,String sm, String fm){
			System.out.println(new Date()+": savePost() Starts ****");
			boolean retval = false;
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				//TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, groupId);
				
			
				for(TblUploadRes res:uploadList){
					TblFileGroup g = (TblFileGroup)session.get(TblFileGroup.class, res.getFileGroupId());
					
					TblFileShare f=new TblFileShare();
					f.setFileName(res.getAttachName());
					f.setFilePath(res.getAttachPath());
					String folder[]=res.getAttachPath().split("/");
					f.setFileFolder(folder[2]);
					f.setFileStatus("Y");
					f.setStore(sm);
					f.setFranchise(fm);
					f.setTblFileGroup(g);
					//f.setFileOwnerRole(tblUser.getTblRole().getRoleId());
					//f.setFileOwnerSite(tblUser.getUserId());
		/*code added to add prefix to store code by 10055*/	
					String storeCode = res.getStoreCode();
					if(storeCode.charAt(0) !=(storeCodePrefix.trim().equals("")?-1:storeCodePrefix.charAt(0)))
						storeCode=storeCodePrefix+storeCode;
					f.setStoreCode(storeCode);
					//f.setCreatedBy(tblUser.getUserName());
					f.setCreatedDate(new Date());
					f.setUpdatedDate(new Date());
	                f.setHits(0);
				    session.saveOrUpdate(f);
				}
				// Add Task Store
				
				transaction.commit();
				HibernateUtil.closeSession();
				retval = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			
			System.out.println(new Date()+": savePost() Ends ****");
			return retval;		
		}
		
	    private static void archive(File file) {
	    	
	    	String date = DateUtils.formatDate(new Date(), "yyyyMMdd");
	        File dir = new File(ARCHIVE +"/"+ date);
	        if(!dir.exists()||!dir.isDirectory()){
	            dir.mkdir();
	        }
	        try {
	            while (!file.renameTo(new File(ARCHIVE +"/" + date + "/" + file.getName()))) {
	                File oldFile = new File(ARCHIVE +"/" + date + "/" + file.getName());
	                oldFile.delete();
	            }
	        } catch (Exception e) {
	            System.out.println("CRITICAL ERROR! EXCEPTION : " + e.getMessage());
	        }
	    }

//--------- For  Planogram files loading 	    
	    
	    private static ArrayList<TblFileShare> getFileSharesByGroup(int groupId){
	    	System.out.println("1. getFileSharesByGroup : groupId="+groupId);
			Session session = HibernateUtil.currentSession();
			ArrayList<TblFileShare> resList = null;
			
			try {
				TblFileGroup g = (TblFileGroup) session.get(TblFileGroup.class, groupId);
				Query query = session.createQuery("FROM  TblFileShare ta where  ta.tblFileGroup = :fileGroup ")
						.setEntity("fileGroup", g);

				resList = (ArrayList<TblFileShare>) query.list();
				System.out.println("2. getFileSharesByGroup : resList.size()="+resList.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
			HibernateUtil.closeSession();
			return resList;
	    } 
	    
		private static void deleteFileShares(ArrayList<TblFileShare> fsList){	
			System.out.println("*** deleteFileShares ***");
			for(TblFileShare fs : fsList ){
				// 2017-03-01 - Delete Planogram File (changed)
				if ( isPlanogramFileShare(fs) ) {
					// only delete Planogram files created before today
					if ( !DateUtils.isToday(fs.getCreatedDate()) ) {
						// 2017-01-25 - Delete Planogram File				
						boolean result = deleteFile(fs);
						if ( result ) {
							String uploadPath = SERVER_FOLDER + fs.getFilePath();
							File uploadFile = new File(uploadPath);
							result = uploadFile.delete();
							String msg = "delete: " + (result? "SUCCESS" : "FAIL");
							System.out.println(new Date() + ": deleting file: " + uploadFile.getPath() + " => " + msg);
							
							String[] dirFiles = uploadFile.getParentFile().list();
							if ( dirFiles != null && dirFiles.length == 0 ) {
								result = uploadFile.getParentFile().delete();
								msg = "delete: " + (result? "SUCCESS" : "FAIL");
								System.out.println(new Date() + ": deleting dir: " + uploadFile.getParentFile().getPath() + " => " + msg);
							}
						}
					}
				} else {
					deleteFile(fs);
				}
				// 2017-01-25 - Delete Planogram File				
			} 			
		}
	    
		private static boolean deleteFile(TblFileShare f){
			boolean retval = false;
			Transaction transaction = null;
			Session session = HibernateUtil.currentSession();
			try {
				transaction = session.beginTransaction();
				session.delete(f);
				
				transaction.commit();
				HibernateUtil.closeSession();
				retval = true;
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			} 
			
			return retval;		
		}
		
		// 2017-01-25 - Delete Planogram File
		private static boolean isPlanogramFileShare(TblFileShare fileShare) {
			return fileShare.getFilePath().contains("_PLANOGRAM_") ||
				   fileShare.getTblFileGroup().getGroupName().toUpperCase().contains("PLANO");
		}
		// 2017-01-25 - Delete Planogram File
}



class ZIPFileFilter implements FilenameFilter{
	public boolean accept(File dir,String name){
		return (name.endsWith(".zip"));
	}
}
