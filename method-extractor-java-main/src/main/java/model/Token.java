package model;

public class Token {
	private String nodeName;
	private int numberOfNode;
	
	public Token(String nodeName, int numberOfNode) {
		this.nodeName = nodeName;
		this.numberOfNode = numberOfNode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public int getNumberOfNode() {
		return numberOfNode;
	}
	
}
