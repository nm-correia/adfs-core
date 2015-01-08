package adfs.core;

import java.io.Serializable;

public class ADFSFileContent implements Serializable, ADFSFile {

	private byte[] content;
	private long maxSize;
	
	public ADFSFileContent(long maxSize) {
		this.content = null;
		this.maxSize = maxSize;
	}
	
	public byte[] getData() {
		return content;
	}
	
	public void setData(byte[] content) {
		this.content = content;
	}
	
	public long getMaxSize() {
		return this.maxSize;
	}
}