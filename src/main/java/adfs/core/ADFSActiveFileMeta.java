package adfs.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ADFSActiveFileMeta extends ADFSFileMeta {

	private String framework;
	private String project;
	private String projectArgs;
	private String computationArgs;
	private Map<String, Long> srcFiles;
	private boolean compute;
	private int reads, writes;
	
	
	public ADFSActiveFileMeta(String name) {
		super(name);
		this.active = true;
		this.srcFiles = new HashMap<String, Long>();
		this.reads = 0;
		this.writes = 0;
		this.compute = true;
	}
	
	public boolean isStale() {
		for(Entry<String, Long> e: srcFiles.entrySet())
			if(e.getValue() >= this.getTime())
				return true;

		return false;
	}
	
	public boolean toCompute() {
		return compute;
	}
	
	public void setCompute(boolean val) {
		compute = val;
	}
	
	public boolean setSrcWriteTime(String src, long newTime) {
		if(!srcFiles.containsKey(src))
			return false;
		else {
			srcFiles.put(src, newTime);
			return true;
		}
	}
	
	public long lastSrcWriteTime() {
		return Collections.max(srcFiles.values());
	}
	
	public Set<String> getSrcFiles() {
		return srcFiles.keySet();
	}
	
	public void assocSrcFile(String srcfName, long time) {
		srcFiles.put(srcfName, time);
	}
	
	public boolean disassocSrcFile(String srcfName) {
		return srcFiles.remove(srcfName) != null;
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
	
	// TODO time and size in consideration
	public boolean isWorthItProcess() {
		if((reads+writes) == 0)
			return false;
		else
			return (reads/(reads+writes)) > 0.6;
	}
	
	@Override
	public String toString() {
		return super.toString().substring(0, super.toString().length()-1) +
				",framework:" + framework +
				",computationSrc:" + project + " " + projectArgs +
				",computationArgs:" + computationArgs +
				",srcFiles:" + srcFiles.toString() +
				",reads:" + reads + 
				",writes:" + writes + 
				",lastSrcWrite:" + lastSrcWriteTime() +
				",isStale?: " + isStale() + ">";
	}

}
