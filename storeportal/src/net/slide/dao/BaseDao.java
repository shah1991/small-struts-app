package net.slide.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.slide.pojo.TblSystxno;

import org.hibernate.Session;

public class BaseDao {

	public BaseDao() {
		super();
	}

	public TblSystxno getSerial(final String TBL, Session session) {
		TblSystxno txn = (TblSystxno)session.get(TblSystxno.class, TBL);
		if(txn == null){
			txn = new TblSystxno();
			txn.setTxnCd(TBL);
			txn.setTxnNo(1);
		}else{
			txn.setTxnNo(txn.getTxnNo()+1);
		}
		session.saveOrUpdate(txn);
		return txn;
	}
	
	public String generateTxnCode(Session session, String txnCd, String txnPrefix) {
		TblSystxno txnno = (TblSystxno)session.get(TblSystxno.class, txnCd);
		if(txnno == null){
			txnno = new TblSystxno();
			txnno.setTxnCd(txnCd);
			txnno.setTxnNo(1);
			txnno.setTxnPrefix(txnPrefix);
		}else{
			txnno.setTxnNo(txnno.getTxnNo()+1);
		}
		String year = new SimpleDateFormat("yyyy").format(new Date());
		String taskCd = String.format("%s%s%06d", txnno.getTxnPrefix(), year, txnno.getTxnNo());
		return taskCd;
	}

}