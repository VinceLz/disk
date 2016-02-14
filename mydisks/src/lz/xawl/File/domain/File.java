package lz.xawl.File.domain;

import java.text.DecimalFormat;

import lz.xawl.Catalog.domain.Catalog;

public class File{
	private String fId;
	private String fPath;
	private long fSize;
	private String fType;
	private String fHash;
	private String fDowncount;
	private String fDesc;
	private String fUploadtime;
	private String isShare;
	private Catalog catalog;
	private String fName;
	private String fDiskName;
	public String getfDiskName() {
		return fDiskName;
	}
	public void setfDiskName(String fDiskName) {
		this.fDiskName = fDiskName;
	}
	public String getfId() {
		return fId;
	}
	@Override
	public String toString() {
		return "File [fId=" + fId + ", fPath=" + fPath + ", fName=" + fName
				+ ", cId="  + "]";
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getfPath() {
		return fPath;
	}
	public void setfPath(String fPath) {
		this.fPath = fPath;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	
	public long getfSize() {
		return fSize;
	}
	public void setfSize(long fSize) {
		this.fSize = fSize;
	}
	public String getfType() {
		return fType;
	}
	public void setfType(String fType) {
		this.fType = fType;
	}
	public String getfHash() {
		return fHash;
	}
	public void setfHash(String fHash) {
		this.fHash = fHash;
	}
	public String getfDowncount() {
		return fDowncount;
	}
	public void setfDowncount(String fDowncount) {
		this.fDowncount = fDowncount;
	}
	public String getfDesc() {
		return fDesc;
	}
	public void setfDesc(String fDesc) {
		this.fDesc = fDesc;
	}
	public String getfUploadtime() {
		return fUploadtime;
	}
	public void setfUploadtime(String fUploadtime) {
		this.fUploadtime = fUploadtime;
	}
	public String getIsShare() {
		return isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	public Catalog getCatalog() {
		return catalog;
	}
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
	
	
}