package lz.xawl.Catalog.domain;

import java.util.List;

import lz.xawl.File.domain.File;

public class Catalog {
	
	private String cId;  //对应
	private String cName;  //对应
 	private String cDate;  //对应
	private Catalog parent;
	private String isShare; //对应
	private List<Catalog> children;
	private List<File> myFile;
	@Override
	public String toString() {
		return "Catalog [cId=" + cId + ", cName=" + cName + ", cDate=" + cDate
				+ ", parent=" + parent + ", isShare=" + isShare + ", children="
				+ children + ", myFile=" + myFile + "]";
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}

	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public Catalog getParent() {
		return parent;
	}
	public void setParent(Catalog parent) {
		this.parent = parent;
	}
	public List<Catalog> getChildren() {
		return children;
	}
	public void setChildren(List<Catalog> children) {
		this.children = children;
	}
	public List<File> getMyFile() {
		return myFile;
	}
	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}
	
	
}
