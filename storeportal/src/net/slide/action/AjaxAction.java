package net.slide.action;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class AjaxAction extends ActionSupport  {
	private String term;

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

//	public ArrayList<AjexResult> getPlsList(){
//		ArrayList<AjexResult> retval = new ArrayList<AjexResult>();
//		
//		PlayListDao plsDao = new PlayListDao();
//		ArrayList<TblPlaylist> res =  plsDao.ajaxSearch(term);
//		
//		for(TblPlaylist pls: res){
//			retval.add(new AjexResult(pls.getId(), null, pls.getGroupName()));
//		}
//		return retval;
//	}
	
		
	/**
	 * Getters / Setters
	 */
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term)
	{
		this.term = term;
	}

}