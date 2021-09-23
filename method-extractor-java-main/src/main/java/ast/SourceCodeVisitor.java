package ast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;

import model.Method;
import model.Token;

public class SourceCodeVisitor extends ASTVisitor {

	private HashMap<String, Integer> tokenSet;

	public SourceCodeVisitor(HashMap<String, Integer> tokenSet, String filePath) {
		this.tokenSet = tokenSet;
	}
	
	@Override
	public boolean visit(VariableDeclarationStatement node) {
		//System.out.println("Variable :");
		for (Iterator iter = node.fragments().iterator(); iter.hasNext();) {
			VariableDeclarationFragment fragment = (VariableDeclarationFragment) iter.next();
			//fragment.SIMPLE_TYPE;//.getInitializer().getNodeType();
			//SimpleName aa = (SimpleName) fragment.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY);
			addTokenset(fragment.getName().toString()); 
			//System.out.println(fragment.getName().toString()+"  123");
			//System.out.println(fragment.getInitializer().resolveTypeBinding().getBinaryName());
		}
		node.getModifiers();
		return false; // prevent that SimpleName is interpreted as reference
	}

	@Override
	public boolean visit(CompilationUnit result) {

		try {
			List types = result.types();
			TypeDeclaration typeDec = (TypeDeclaration) types.get(0);
			List importList = result.imports();
			PackageDeclaration packetDec = result.getPackage();
			String className = typeDec.getName().toString();
			MethodDeclaration methodDec[] = typeDec.getMethods();
			FieldDeclaration[] fieldDec = typeDec.getFields();
			addTokenset(packetDec.getName().toString());
			for (Object obj : importList) {
				ImportDeclaration importDec = (ImportDeclaration) obj;
			}
			addTokenset(className);
			for (MethodDeclaration method : methodDec) {
				addTokenset(method.getName().toString());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;	
	}

	@Override
	public void preVisit(ASTNode node) {
		// int nodeType = node.getNodeType();

		// String nodeInfo=node.get

//		String className = node.getClass().getName();
//
//		String[] splitedName = className.split("\\.");
//
//		String astNodeName = splitedName[splitedName.length - 1];
//
//		if (!tokenSet.containsKey(astNodeName)) {
//
//			tokenSet.put(astNodeName, 1);
//
//		} else {
//
//			int oldFrequencyValue = tokenSet.get(astNodeName);
//
//			int newFrequencyValue = oldFrequencyValue + 1;
//
//			tokenSet.replace(astNodeName, oldFrequencyValue, newFrequencyValue);
//		}
	}
	
	public void addTokenset(String tokenName) {
		if (!tokenSet.containsKey(tokenName)) {

			tokenSet.put(tokenName, 1);

		} else {

			int oldFrequencyValue = tokenSet.get(tokenName);

			int newFrequencyValue = oldFrequencyValue + 1;

			tokenSet.replace(tokenName, oldFrequencyValue, newFrequencyValue);
		}
	}

}