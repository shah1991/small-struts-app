package net.slide.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TblTaskUpload generated by hbm2java
 */
@Entity
@Table(name = "tbl_task_response_area", schema = "public")
public class TblTaskResponseArea implements java.io.Serializable {

	private int id;
	private TblTaskArea tblTaskArea;        
        
        private String taskProofPath;
	private String taskProofFilename;
    
	public TblTaskResponseArea() {
	}

	public TblTaskResponseArea(int id) {
		this.id = id;
	}

        
        public TblTaskResponseArea(int id, TblTaskArea tblTaskArea, String taskProofPath,
			String taskProofFilename) {
            
		this.id = id;
		this.tblTaskArea = tblTaskArea;
                this.taskProofPath = taskProofPath;
		this.taskProofFilename = taskProofFilename;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_area_id")
	public TblTaskArea getTblTaskArea() {
		return this.tblTaskArea;
	}

	public void setTblTaskArea(TblTaskArea tblTaskArea) {
		this.tblTaskArea = tblTaskArea;
	}

	@Column(name = "task_proof_path", length = 1024)
	public String getTaskProofPath() {
		return this.taskProofPath;
	}

	public void setTaskProofPath(String taskProofPath) {
		this.taskProofPath = taskProofPath;
	}

	@Column(name = "task_proof_filename", length = 512)
	public String getTaskProofFilename() {
		return this.taskProofFilename;
	}

	public void setTaskProofFilename(String taskProofFilename) {
		this.taskProofFilename = taskProofFilename;
	}
        
}
