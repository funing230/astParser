package funing.excel;

public class id_code {
	public id_code(String id,String commit, String path) {
		super();
		Id = id;
		Commit=commit;
		Path = path;

	}
	private String Id;
    private String Path;
    private String Commit;
	public String getCommit() {
		return Commit;
	}
	public void setCommit(String commit) {
		Commit = commit;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
    

    
}
