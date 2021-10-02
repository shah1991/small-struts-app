package net.slide.action;

import java.util.ArrayList;
import java.util.Map;

import net.slide.dao.PostDao;
import net.slide.dao.PostDao;
import net.slide.dao.TaskPostDao;
import net.slide.dao.TopicDao;
import net.slide.exception.SessionExpiredException;
import net.slide.pojo.TblPostForum;
import net.slide.pojo.TblPost;
import net.slide.pojo.TblPostTopic;
import net.slide.pojo.TblUser;
import net.slide.pojores.TblMasterRes;
import net.slide.pojores.TblTaskRes;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import net.slide.dao.LinkDao;
import net.slide.util.UrlBreadCrumb;

@SuppressWarnings("serial")
public class PostMainAction extends ActionSupport {

	private Integer forumId;
	private Integer topicId;
	private int postId;
	
	private String postLevel;
	private String postYear;
	private String postMonth;
	private Integer postWeek;
        private String breadCrumbt0;
        private String breadCrumbt1;
        private String breadCrumby;
        private String breadCrumbm;
        private String breadCrumbw;
        private String breadCrumbp;
        private String prevUrl0;
        private String prevUrl1;
        private String prevUrly;
        private String prevUrlm;
        private String prevUrlw;
        private String prevUrlp;
	
	private String topicName;
	private String status;
	private boolean actUpdate = false;;
	private String searchTxt;
	private int storeId;
    private int areaId;
	private int forumOwnerRole;
	private int forumOwnerSite;
	

	public String entry() throws SessionExpiredException {
		String retval = SUCCESS;
		try {
		TblUser curusr=	getUserBean();
		if(curusr.getTblRole().getRoleId()==1){
			retval="entrystore";
		}else if(curusr.getTblRole().getRoleId()==2){
			retval="entryarea";
		}else if(curusr.getTblRole().getRoleId()==3){
			retval="entryhq";
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
	
	
	public String search() throws SessionExpiredException {
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "detail";
	}
	
	// develop by fizd on January 17th 2014
	public String topic() throws SessionExpiredException {
                String retval = "topic";	
		try {
			getUserBean();
//                        TblUser curusr=	getUserBean();
//                       //if(curusr.getTblRole().getRoleId()==2 && forumId==2){
//                        if(forumId==2 && (curusr.getTblRole().getRoleId()==2 || curusr.getTblRole().getRoleId()==8)){
//                              retval="accessdeny";
//                        }/*else{
//                            retval="topic";
//                        }*/
                        
                        
            			//getUserBean();
                        TblUser curusr=	getUserBean();
                       //if(curusr.getTblRole().getRoleId()==2 && forumId==2){
                        if(forumId==2 && ( curusr.getTblRole().getRoleId()==8)){
                              retval="accessdeny";
                        }else{
                            retval="topic";                            
                        }               
                        
                        
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		//return "topic";
                return retval;
	}
	
	
	public String forumpost() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();		
                String url=null;
		String retval = INPUT;
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return retval;
		}
		if(postLevel.equals("Y")){
			retval = "postyear";
                        int curlvlx;;
                        if(session.get("curlvl")!=null){
                            curlvlx=(Integer)session.get("curlvl");
                        }else{
                            curlvlx=0;
                        }
                        if (curlvlx==0){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrl1",url);
                            breadCrumbt1=(String)session.get("breadCrumbt1"); 
                            prevUrl1= url;
                        }else{
                            prevUrl1= (String)session.get("prevUrl1");
                            breadCrumbt1=(String)session.get("breadCrumbt1");                                                
                        }
                        session.remove("curlvl");
                        session.remove("prevUrly");
                        session.remove("prevUrlm");
                        session.remove("prevUrlw");
                        session.remove("breadCrumby");
                        session.remove("breadCrumbm");
                        session.remove("breadCrumbw");
                        session.put("curlvl",1);
                }
		else if(postLevel.equals("M")){
			retval = "postmonth";
                        int curlvlx=(Integer)session.get("curlvl");
                        if (curlvlx==1){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrly",url);
                            session.put("breadCrumby",breadCrumby);
                            prevUrly= url;
                        }else{
                            prevUrly= (String)session.get("prevUrly");
                            breadCrumby=(String)session.get("breadCrumby");
                        }
                        session.remove("curlvl");
                        session.remove("prevUrlm");
                        session.remove("prevUrlw");
                        session.remove("breadCrumbm");
                        session.remove("breadCrumbw");
                        session.put("curlvl",2);
                        prevUrl1= (String)session.get("prevUrl1");
                        breadCrumbt1=(String)session.get("breadCrumbt1");                        
                }
		else if(postLevel.equals("W")){
			retval = "postweek";
                        int curlvlx=(Integer)session.get("curlvl");
                        if (curlvlx==2){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrlm",url);
                            session.put("breadCrumbm",breadCrumbm);
                            prevUrlm= url;
                        }else{
                            prevUrlm= (String)session.get("prevUrlm");
                            breadCrumbm=(String)session.get("breadCrumbm");
                        }
                        session.remove("curlvl");
                        session.remove("prevUrlw");
                        session.remove("breadCrumbw");
                        session.put("curlvl",3);
                        prevUrl1=(String)session.get("prevUrl1");
                        prevUrly=(String)session.get("prevUrly");
                        breadCrumbt1=(String)session.get("breadCrumbt1");                        
                        breadCrumby=(String)session.get("breadCrumby");                        
                }
		else if(postLevel.equals("P")){
			retval = "post";
                        int curlvlx=(Integer)session.get("curlvl");
                        if (curlvlx==3){
                            url=UrlBreadCrumb.getUrl();
                            session.put("prevUrlw",url);
                            session.put("breadCrumbw",breadCrumbw);
                            prevUrlw= url;
                        }else{
                            prevUrlw= (String)session.get("prevUrlw");
                            breadCrumbw=(String)session.get("breadCrumbw");
                        }
                        session.remove("curlvl");
                        session.put("curlvl",4);
                        prevUrl1=(String)session.get("prevUrl1");
                        prevUrly=(String)session.get("prevUrly");
                        prevUrlm=(String)session.get("prevUrlm");
                        breadCrumbt1=(String)session.get("breadCrumbt1");                        
                        breadCrumby=(String)session.get("breadCrumby");                        
                        breadCrumbm=(String)session.get("breadCrumbm");                        
                }
		return retval;
	}
	
	public String hq() throws SessionExpiredException {
                Map<String, Object> session = ActionContext.getContext().getSession();	
                session.remove("curlvl");
                session.remove("prevUrlw");
		try {   
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "managehq";
	}
	

	public String area() throws SessionExpiredException {
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "managearea";
	}
	
	public ArrayList<TblMasterRes> getStorePendingList() {
		PostDao taskDao = new PostDao(); 
		ArrayList<TblMasterRes> resList = taskDao.getStorePending(storeId,topicId);
		return resList;
	}
	
	
	public ArrayList<TblMasterRes> getAreaPendingList() {
		PostDao taskDao = new PostDao(); 
		ArrayList<TblMasterRes> resList = taskDao.getAreaPending(areaId,topicId);
		return resList;
	}
	
	
	public String areapending() throws SessionExpiredException {
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "areapending";
	}
	

	public String store() throws SessionExpiredException {
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "manageuser";
	}
	
	
	public String hqcomplete() throws SessionExpiredException {
		try {
			getUserBean();
		} catch (SessionExpiredException se) {
			addActionError("Session Expired..!!!");
			throw se;
		} catch (Exception e) {
			addActionError("Technical Error Happned..!!!");
			e.printStackTrace();
			return INPUT;
		}
		return "hqcomplete";
	}
	
	private TblUser getUserBean() throws SessionExpiredException, Exception {
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		TblUser curusr = (TblUser)session.get("loginUser");
		
		if(forumId!=null){
			session.put("forumId",forumId);
		}else if(session.get("forumId")!=null){
			forumId=(Integer)session.get("forumId");
		
		}
		if(topicId!=null){
			session.put("topicId",topicId);
		}else if(session.get("topicId")!=null){
			topicId=(Integer)session.get("topicId");
		
		}

		if(breadCrumbt1!=null){
			session.put("breadCrumbt1",breadCrumbt1);
		}else if(session.get("breadCrumbt1")!=null){
			breadCrumbt1=(String)session.get("breadCrumbt1");
		
		}
   
	if(curusr!=null){
		if(curusr.getTblRole().getRoleId()==1){
			
			setStoreId(curusr.getSiteId());
			setForumOwnerSite(curusr.getUserId());
			setForumOwnerRole(curusr.getTblRole().getRoleId());
		}else if(curusr.getTblRole().getRoleId()==2){
			setAreaId(curusr.getSiteId());
			
			setForumOwnerSite(curusr.getUserId());
			setForumOwnerRole(curusr.getTblRole().getRoleId());
		}else if(curusr.getTblRole().getRoleId()==3){
		
			setForumOwnerSite(curusr.getUserId());
			setForumOwnerRole(curusr.getTblRole().getRoleId());
		}
		}
		if(curusr == null) {
			throw new SessionExpiredException("Session Expired");
		}
		return curusr;
	}

	/*public ArrayList<TblPostTopic> getTopicList() {
		TopicDao topicDao = new TopicDao();
		PostDao postDao = new PostDao();
		ArrayList<TblPostTopic> resList = topicDao.topicList(postDao.getForumById(forumId));
		return resList;
	}*/
        
        
        public String getBreadCrumbt0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String header = "";
            LinkDao linkDao = new LinkDao();
            header = linkDao.getTitleByUrlId(forumId);
            session.put("breadCrumbt0",header);            
            return header;
        }
        
        public String getPrevUrl0(){
            Map<String, Object> session = ActionContext.getContext().getSession();
            String url = "";
            LinkDao linkDao = new LinkDao();
            url = linkDao.getLinkByUrlId(forumId);
            session.put("prevUrl0",url);    
            return url;
        }        
        
        public ArrayList<TblMasterRes> getTopicList() {
		TopicDao topicDao = new TopicDao();
		PostDao postDao = new PostDao();
		ArrayList<TblMasterRes> resList = topicDao.topicList(postDao.getForumById(forumId));
		return resList;
	}
        
        /*public ArrayList<TblMasterRes> getTopicList() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblMasterRes> resList = null;        
		
                resList = postDao.getForumById(forumId);
		return resList;
	}*/


	public ArrayList<TblPostForum> getForumList() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblPostForum> resList = postDao.getForumList();
		return resList;
	}

	public ArrayList<TblMasterRes> getPostCategory() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblMasterRes> resList = null;
		
		if(postLevel.equals("Y")){
			resList = postDao.getPostYears(topicId);
			postLevel = "M";
		}else if(postLevel.equals("M")){
			resList = postDao.getPostMonths(topicId, postYear);
			postLevel = "W";
		}else if(postLevel.equals("W")){
			resList = postDao.getPostWeek(topicId, postYear, postMonth);
			postLevel = "P";
		}
		return resList;
	}
	
	public ArrayList<TblPost> getPostList() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblPost> resList =  postDao.getPosts(topicId, postYear, postMonth, postWeek);
		return resList;
	}

	
	
	public ArrayList<TblMasterRes> getAreaList() {
		PostDao postDao = new PostDao(); 
		ArrayList<TblMasterRes> resList = postDao.getAreaForum(topicId,forumOwnerRole,forumOwnerSite);
		return resList;
	}
	
	
//	public ArrayList<TblMasterRes> getAreaCompletedList() {
//		PostDao postDao = new PostDao(); 
//		ArrayList<TblMasterRes> resList = postDao.getAreaCompletedForum(topicId,forumOwnerRole,forumOwnerSite);
//		return resList;
//	}
//	
	
	/**
	 * 
	 * Master Bean Get/Set
	 */
	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}
	
	
	
	/**
	 * 
	 * Detail Bean Get/Set
	 */
	
	public boolean isActUpdate() {
		return actUpdate;
	}

	public void setActUpdate(boolean actUpdate) {
		this.actUpdate = actUpdate;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

        public String getBreadCrumbt1() {
            return breadCrumbt1;
        }

        public void setBreadCrumbt1(String breadCrumbt1) {
            this.breadCrumbt1 = breadCrumbt1;
        }
        
     
	public Integer getForumId() {
		return forumId;
	}


	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}


	public Integer getTopicId() {
		return topicId;
	}


	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}

	public String getPostYear() {
		return postYear;
	}

	public void setPostYear(String postYear) {
		this.postYear = postYear;
	}

	public String getPostMonth() {
		return postMonth;
	}

	public void setPostMonth(String postMonth) {
		this.postMonth = postMonth;
	}

	public Integer getPostWeek() {
		return postWeek;
	}

	public void setPostWeek(Integer postWeek) {
		this.postWeek = postWeek;
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


	public int getForumOwnerRole() {
		return forumOwnerRole;
	}


	public void setForumOwnerRole(int forumOwnerRole) {
		this.forumOwnerRole = forumOwnerRole;
	}


	public int getForumOwnerSite() {
		return forumOwnerSite;
	}


	public void setForumOwnerSite(int forumOwnerSite) {
		this.forumOwnerSite = forumOwnerSite;
	}


        public String getPrevUrl1() {
            return prevUrl1;
        }

        public void setPrevUrl1(String prevUrl1) {
            this.prevUrl1 = prevUrl1;
        }

    public String getBreadCrumby() {
        return breadCrumby;
    }

    public void setBreadCrumby(String breadCrumby) {
        this.breadCrumby = breadCrumby;
    }

    public String getBreadCrumbm() {
        return breadCrumbm;
    }

    public void setBreadCrumbm(String breadCrumbm) {
        this.breadCrumbm = breadCrumbm;
    }

    public String getBreadCrumbw() {
        return breadCrumbw;
    }

    public void setBreadCrumbw(String breadCrumbw) {
        this.breadCrumbw = breadCrumbw;
    }

    public String getPrevUrly() {
        return prevUrly;
    }

    public void setPrevUrly(String prevUrly) {
        this.prevUrly = prevUrly;
    }


    public String getPrevUrlm() {
        return prevUrlm;
    }

    public void setPrevUrlm(String prevUrlm) {
        this.prevUrlm = prevUrlm;
    }


    public String getPrevUrlw() {
        return prevUrlw;
    }

    public void setPrevUrlw(String prevUrlw) {
        this.prevUrlw = prevUrlw;
    }


    public String getPrevUrlp() {
        return prevUrlp;
    }

    public void setPrevUrlp(String prevUrlp) {
        this.prevUrlp = prevUrlp;
    }


 
}
