package funing.excel;

import java.util.ArrayList;

public class Voca_freq {
	
	public Voca_freq(String bug_id,String aST_Node, String frequency) {
		super();
		Bug_id=bug_id;
		AST_Node = aST_Node;
		Frequency = frequency;
	}
	private String Bug_id;
	private String AST_Node;
    private String Frequency;
    
	public String getBug_id() {
		return Bug_id;
	}
	public void setBug_id(String bug_id) {
		Bug_id = bug_id;
	}
	public String getAST_Node() {
		return AST_Node;
	}
	public void setAST_Node(String aST_Node) {
		AST_Node = aST_Node;
	}
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
    
//	public Voca_freq(ArrayList vode) {
//
//	}
}
