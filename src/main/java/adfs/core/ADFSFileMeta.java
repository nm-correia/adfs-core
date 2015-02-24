package adfs.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ADFSFileMeta implements Serializable, ADFSFile {

	private String name;	
	protected boolean active;
	private Set<String> activeFiles;
	private long time;
	private boolean availability;

	public ADFSFileMeta(String name) {
		this.name = name;
		this.active = false;
		this.activeFiles = new HashSet<String>();
		this.time = System.currentTimeMillis();
		this.availability = true;
	}

	public boolean isActive() {
		return active;
	}
	
	public boolean isAvailable() {
		return availability;
	}
	
	public void setAvailability(boolean val) {
		availability = val;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getAssocActiveFiles() {
		return activeFiles;
	}
	
	public boolean assocActiveFile(String afName) {
		return activeFiles.add(afName);
	}
	
	public boolean disassocActiveFile(String afName) {
		return activeFiles.remove(afName);
	}	
	
	public long getTime() {
		return time;
	}
	
	public void updateTime() {
		time = System.currentTimeMillis();
	}
	
	@Override
	public String toString() {
		return "<name:" + name +
				",active?:" + active +
				",assocActiveFiles:" + activeFiles +
				",availability:" + availability +
				",time:" + time + ">";
	}
	
}
