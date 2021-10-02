package net.slide.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.servlet.ServletContext;

import net.slide.dao.FileShareDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblFileShare;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblUploadRes;
import net.slide.util.CommonUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class FilePostDetailAction extends ActionSupport {
	private int storeId;
    private int areaId;
	private int delId;
    private Integer groupId;
	private Integer folderId;
	private int fileId;
	private int fileOwnerRole;
	private int fileOwnerSite;
	private String fileName;
	private String filePath;
	private int slist[];
	private int alist[];
	private String recType;
	private boolean actUpdate = false;
	private List<File> docAttach = new ArrayList<File>();
	private List<String> docAttachContentType = new ArrayList<String>();
	private List<String> docAttachFileName = new ArrayList<String>();
	ArrayList<TblUploadRes> uploadRes=new ArrayList<TblUploadRes>();
	private boolean store;
	//private boolean franchise;
	private static String SERVER_FOLDER = "";
        private String prevUrlfs11;        
        private String breadCrumbfs11;  
  
	public String create() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		
	
		return INPUT;
	}
	
	
	public String detaildelete() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
			getUserBean();
			//Set Post detail
			FileShareDao postDao = new FileShareDao();
			TblFileShare f = postDao.getFileShare(fileId); 
			if(f != null) {
				postDao.deleteFile(f);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		return "managehq";
	}
	
	public String detaildeletearea() throws SessionExpiredException {
		
		try {
			Map<String, Object> session = ActionContext.getContext().getSession();
			 session.put("uploadRes",null);
			getUserBean();
			//Set Post detail
			FileShareDao postDao = new FileShareDao();
			TblFileShare f = postDao.getFileShare(fileId); 
			if(f != null) {
				postDao.deleteFile(f);
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
		}
		return "managearea";
	}
	
	@SuppressWarnings("unchecked")
	public String detailadd() throws SessionExpiredException {
		String retval = "managehq";
		
		try {
			getUserBean();
			String sm,fm=null;
			// Get User Name
			Map<String, Object> session = ActionContext.getContext().getSession();
			TblUser curusr = (TblUser)session.get("loginUser");
			boolean res = false;
			FileShareDao postDao = new FileShareDao();
			TblFileShare fs = new TblFileShare();
	        fs.setFileName(fileName);
	        if(store){
	        	sm="Y";
	        }else{
	        	sm="N";
	        }
	        /*if(franchise){
	        	fm="Y";
	        }else{
	        	fm="N";
	        }*/
	        fm="N";
	        if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
	 
	    	res = postDao.savePost(uploadRes,groupId,curusr,sm,fm);
			
			if ( res == false) {
				addActionError("Add failed..!!!");
				retval = INPUT;
			}else{
				addActionMessage(fileName + "Added Successfully !!!!");
			}
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return retval;
	}
	 	
	
	@SuppressWarnings("unchecked")
	public String uploadZip() throws SessionExpiredException {
		String retval = INPUT;
	

		Map<String, Object> session = ActionContext.getContext().getSession();
		String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
		String curpath ="";
		if(store){
			curpath="uploads/store/";
		}else{
			curpath="uploads/franchisee/";
		}
		TblUser curusr = (TblUser)session.get("loginUser");
		if(curusr.getTblRole().getRoleId()==2){
			retval="areacreate";
		}
		try {
			if(session.get("uploadRes")!=null){
			  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
			}
			if(docAttach.size()!=0){
					String postAttachPath = importFile(docAttach.get(0), docAttachFileName.get(0));
					doUnzip(rootpath+postAttachPath,curpath);
					
				}
				session.put("uploadRes",uploadRes);
		
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		
		return retval;
	}
	
	@SuppressWarnings("unchecked")
	public String deleteDoc() throws SessionExpiredException {
		String retval=INPUT;
		try {
			getUserBean();
			Map<String, Object> session = ActionContext.getContext().getSession();
			
			TblUser curusr = (TblUser)session.get("loginUser");
			if(curusr.getTblRole().getRoleId()==2){
				retval="areacreate";
			}
			if(session.get("uploadRes")!=null){
				  uploadRes=(ArrayList<TblUploadRes>)session.get("uploadRes");
				  uploadRes.remove(delId);
			      session.put("uploadRes",uploadRes);
			}
	    	
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		
		
		return retval;
	}
	

	public String importFile(File mfile, String filename) throws Exception{
		String retval = null;
		
		try{
			
			String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
		
			String path = "/archieve";
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
			throw e;
		}
		return retval;
	}
	
	
 public void doUnzip(String inputZip, String destinationDirectory) throws IOException {
		String rootpath = ((ServletContext)ActionContext.getContext().get(StrutsStatics.SERVLET_CONTEXT)).getRealPath("/");
		ResourceBundle bundle = ResourceBundle.getBundle("config");

    	SERVER_FOLDER = bundle.getString("server_folder");	
		int BUFFER = 2048;
		ArrayList<List> zipFiles = new ArrayList();
		
		File sourceZipFile = new File(inputZip);
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
			TblUploadRes res=new TblUploadRes();
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
			if(destFile.getName().contains("_")){
			String storeCode[]=destFile.getName().split("_");
			if(storeCode[0]!=null){
				if(storeCode[0].equals("DD")){
					res.setStoreCode(storeCode[1]);
				}else{
					res.setStoreCode(storeCode[0]);
				}
			}
			}
			
			if(res.getStoreCode()!=null){
			res.setAttachName(destFile.getName());
			res.setAttachPath(destinationDirectory+destFile.getName());
			uploadRes.add(res);
			}
		}
		zipFile.close();
		

		for (Iterator iter = ((AbstractList) zipFiles).iterator(); iter.hasNext();) {
			String zipName = (String) iter.next();
			doUnzip(zipName, destinationDirectory + File.separatorChar
					+ zipName.substring(0, zipName.lastIndexOf(".zip")));
		}

	}

 
 
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		if(curusr!=null){
			if(groupId!=null){
				session.put("groupId",groupId);
			}else if(session.get("groupId")!=null){
				groupId=(Integer)session.get("groupId");
			
			}
                        breadCrumbfs11=(String)session.get("breadCrumbfs0");
                        String url=UrlBreadCrumb.getUrl();
                        session.put("prevUrlfs11",url);
                        prevUrlfs11=url;		
			if(curusr.getTblRole().getRoleId()==1){
				
				setStoreId(curusr.getSiteId());
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==2){
				setAreaId(curusr.getSiteId());
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}else if(curusr.getTblRole().getRoleId()==3 || curusr.getTblRole().getRoleId()==13){
			
				setFileOwnerSite(curusr.getUserId());
				setFileOwnerRole(curusr.getTblRole().getRoleId());
			}
		}else{
		
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}
	
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getFolderId() {
		return folderId;
	}
	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	public int getFileOwnerRole() {
		return fileOwnerRole;
	}
	public void setFileOwnerRole(int fileOwnerRole) {
		this.fileOwnerRole = fileOwnerRole;
	}
	public int getFileOwnerSite() {
		return fileOwnerSite;
	}
	public void setFileOwnerSite(int fileOwnerSite) {
		this.fileOwnerSite = fileOwnerSite;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public int[] getSlist() {
		return slist;
	}

	public void setSlist(String sList) {
		this.slist = CommonUtil.convertInt(sList);
	}
	public int[] getAlist() {
		return alist;
	}

	public void setAlist(String aList) {
		this.alist = CommonUtil.convertInt(aList);
	}
	public String getRecType() {
		return recType;
	}
	public void setRecType(String recType) {
		this.recType = recType;
	}
	public boolean isActUpdate() {
		return actUpdate;
	}
	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}
	public List<File> getDocAttach() {
		return docAttach;
	}
	public void setDocAttach(List<File> docAttach) {
		this.docAttach = docAttach;
	}
	public List<String> getDocAttachContentType() {
		return docAttachContentType;
	}
	public void setDocAttachContentType(List<String> docAttachContentType) {
		this.docAttachContentType = docAttachContentType;
	}
	public List<String> getDocAttachFileName() {
		return docAttachFileName;
	}
	public void setDocAttachFileName(List<String> docAttachFileName) {
		this.docAttachFileName = docAttachFileName;
	}
	public ArrayList<TblUploadRes> getUploadRes() {
		return uploadRes;
	}
	public void setUploadRes(ArrayList<TblUploadRes> uploadRes) {
		this.uploadRes = uploadRes;
	}
	public int getDelId() {
		return delId;
	}
	public void setDelId(int delId) {
		this.delId = delId;
	}


	public int getFileId() {
		return fileId;
	}


	public void setFileId(int fileId) {
		this.fileId = fileId;
	}


	public boolean isStore() {
		return store;
	}


	public void setStore(boolean store) {
		this.store = store;
	}

/*
	public boolean isFranchise() {
		return franchise;
	}


	public void setFranchise(boolean franchise) {
		this.franchise = franchise;
	}
*/

	public static String getSERVER_FOLDER() {
		return SERVER_FOLDER;
	}


	public static void setSERVER_FOLDER(String sERVER_FOLDER) {
		SERVER_FOLDER = sERVER_FOLDER;
	}

        public String getBreadCrumbfs0() {
            Map<String, Object> session = ActionContext.getContext().getSession();
            String breadCrumbfs0=(String)session.get("breadCrumbfs0");
            return breadCrumbfs0;
        }                
                
        public String getPrevUrlfs0() {
               Map<String, Object> session = ActionContext.getContext().getSession();
               String prevUrlfs0=(String)session.get("prevUrlfs0");

            return prevUrlfs0;
        }

       public String getBreadCrumbfs11() {
            return breadCrumbfs11;
        }

        public void setBreadCrumbfs11(String breadCrumbfs11) {
            this.breadCrumbfs11 = breadCrumbfs11;
        }        
        
    public String getPrevUrlfs11() {
        return prevUrlfs11;
    }

    public void setPrevUrlfs11(String prevUrlfs11) {
        this.prevUrlfs11 = prevUrlfs11;
    }
              
	

}
