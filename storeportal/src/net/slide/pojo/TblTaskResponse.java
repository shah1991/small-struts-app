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
@Table(name = "tbl_task_response", schema = "public")
public class TblTaskResponse implements java.io.Serializable {

	private int id;
	private TblTaskStore tblTaskStore;        
        
        private String taskProofPath;
	private String taskProofFilename;
    
	public TblTaskResponse() {
	}

	public TblTaskResponse(int id) {
		this.id = id;
	}

        
        public TblTaskResponse(int id, TblTaskStore tblTaskStore, String taskProofPath,
			String taskProofFilename) {
            
		this.id = id;
		this.tblTaskStore = tblTaskStore;
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
	@JoinColumn(name = "task_store_id")
	public TblTaskStore getTblTaskStore() {
		return this.tblTaskStore;
	}

	public void setTblTaskStore(TblTaskStore tblTaskStore) {
		this.tblTaskStore = tblTaskStore;
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
