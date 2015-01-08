package adfs.core;

import java.util.HashSet;
import java.util.Set;

public class ADFSActiveFileMeta extends ADFSFileMeta {

	private String framework;
	private String project;
	private String projectArgs;
	private String computationArgs;
	private boolean stale;
	private Set<String> srcFiles;
	
	private int reads, writes;
	
	
	public ADFSActiveFileMeta (String name) {
		super(name);
		this.active = true;
		this.srcFiles = new HashSet<String>();
		this.stale = true;
		this.reads = 0;
		this.writes = 0;
	}
	
	public boolean isStale() {
		return stale;
	}
	
	public void setStale(boolean val) {
		this.stale = val;
	}
	
	public Set<String> getSrcFiles() {
		return srcFiles;
	}
	
	public boolean assocSrcFile(String srcfName) {
		return srcFiles.add(srcfName);
	}
	
	public boolean disassocSrcFile(String srcfName) {
		return srcFiles.remove(srcfName);
	}
	
	public void setFramework(String framework) {
		this.framework = framework;
	}
	
	public String getFramework() {
		return this.framework;
	}
	
	public void setProject(String project) {
		this.project = project;
	}

	public void setProjectArgs(String args) {
		this.projectArgs = args;
	}
	
	// TODO
	public String getProject() {
		return this.project;
	}
	
	// TODO
	public String getProjectArgs() {
		return this.projectArgs;
	}
	
	public void setComputationArgs(String args) {
		this.computationArgs = args;
	}
	
	public String getComputationArgs() {
		return this.computationArgs;
	}
	
	public void incReads() {
		reads++;
	}
	
	public void incWrites() {
		writes++;
	}
	
	public boolean isWorthItProcess() {
		if((reads+writes) == 0)
			return false;
		else
			return (reads/(reads+writes)) > 0.6;
	}
	
	@Override
	public String toString() {
		return super.toString() +
				" <ACTIVE - framework:" + framework +
				",computationSrc:" + project + " " + projectArgs +
				",computationArgs:" + computationArgs +
				",srcFiles:" + srcFiles +
				",isStale: " + stale + ">";
	}

}
