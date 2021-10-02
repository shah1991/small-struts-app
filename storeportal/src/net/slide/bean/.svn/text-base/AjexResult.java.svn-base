package net.slide.bean;

@SuppressWarnings("serial")
public class AjexResult implements Comparable<AjexResult>, java.io.Serializable {
    private int id;
    private String cd;
    private String name;

    public AjexResult() {
    	super();
    }

    public AjexResult(int id, String cd, String name) {
	super();
	this.id = id;
	this.cd = cd;
	this.name = name;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public int compareTo(AjexResult o) {
    	return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }

    /*
     * This function is used for the Autocompleter example with seperate label element.
     */
    public String getLabel() {
	return this.name + ((this.cd !=null)?"(" +this.cd +")":"");
    }
}
