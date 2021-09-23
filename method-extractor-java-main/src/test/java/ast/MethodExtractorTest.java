package ast;

import java.io.File;
import java.io.IOException;
import java.lang.module.ModuleDescriptor.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;

import org.junit.Test;

//import b4j.core.Issue;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import model.Method;
import model.Token;

import funing.excel.Voca_freq;
import funing.excel.ExcelWriter;
import funing.excel.Voca_freq;
import funing.excel.bug_id_s;
import funing.excel.id_code;
import funing.excel.Search;

//jdk-1.8
public class MethodExtractorTest {

	// main 
	@Test
	public void extractMethod() throws IOException, EncryptedDocumentException, InvalidFormatException {

		ExcelWriter ew =new ExcelWriter();
		
		ArrayList<id_code> bug_path= ew.get_id_path();
		
		int i=0;
		//放到最外层代表一次整体的输入
		ASTSupportSingleton astTest = ASTSupportSingleton.getInstance();		
		HashMap<String, Integer> tokenSet = new HashMap<String, Integer>();		
		ArrayList tokenarr=new ArrayList<Voca_freq>();		
		
		for(id_code ip :bug_path) {	
			i++;
			List<String> temp=new ArrayList();;     
			List<String> files=Search.getAllFilePaths(new File("d:/test/research/SourceFile/sourceFile_eclipseUI"), temp, ip.getCommit());	//"657bd90" ip.getCommit()
			ArrayList<Voca_freq> voca_fre=new ArrayList();

			for (String path : files) {
				
				String file=FileUtils.readFileToString(new File(path), "UTF-8");				
		        astTest.parse(file, new SourceCodeVisitor(tokenSet, file));	       
				for(Map.Entry<String, Integer> entry : tokenSet.entrySet()) {
					if(voca_fre.size()>=4500) break;					
					voca_fre.add(new Voca_freq(ip.getId(),entry.getKey(),entry.getValue()+""));									
				}	
				
				System.out.println(path);
			}
			System.out.println("In "+i+" round;"+" my length is "+voca_fre.size());
			tokenarr.add(voca_fre);	
			if(i==500)
				break;
		}

		System.out.print(false);
		
     	ew.Write_voca_fre(tokenarr);
					
	}

}
