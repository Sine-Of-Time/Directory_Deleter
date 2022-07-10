import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Directory_Deleter {
	public static void main(String[] args){
				Scanner in=new Scanner(System.in);
				boolean b1=true; //All booleans are for use with while loops.
				boolean b2=true;
				boolean b3=true;
				
				//Basic input verification.
				try {
				System.out.println("Please enter absoulte path that contains files");
				String s =in.nextLine();
				while(b1) {
					if(s.contains(":")) b1=false;
					else {
						System.out.println("Please enter absoulte path that contains files");
						s=in.nextLine();
					}
				}
				
				System.out.println("Please enter keyword that is shared among all files that are selected for deletion.");
				System.out.println("|WARNING|\nTHE CONTENT OF ANY SUBFOLDER,THE SUBFOLDER ITSELF AND ANY FILES IN THE SAME FOLDER AS THE SELECTED ONE THAT CONTAIN THE KEYWORD IN THEIR TITLE WILL BE DELTED.");
				String x=in.nextLine();
				
				
				
				while(b2) {
					if(!(x.contains("\"")||x.contains("/")||x.contains("*")||x.contains("?")||x.contains("<")||x.contains(">")||x.contains("|")||x.endsWith(" ")||x.endsWith(".")||x.startsWith("."))) b2=false;
					else {
						System.out.println("Please enter keyword that is shared amoung all files.");
						x=in.nextLine();
					}
				}
				
				File dir = new File(s); 
				if(!dir.exists()) throw new FileNotFoundException("Path entered does not exist!");
				
				String[] children=dir.list();
				int i=0;
				while(b3) {
					for(i=0;i<children.length;i++){
		                if(children[i].contains(x)) {
		                	System.out.println("Found objects with names containing keyword.");
		                	System.out.println("--------------------------------------------");
		                	b3=false;
		                	break;
		                }
				}
				if(!b3) {
					continue;
				}else {
	                	System.out.println("No file found containg keyword.");
	                	System.out.println("Enter new keyword.");
	                	x=in.nextLine();
	                }
			 }
			delFile(new File(dir,children[i]));
			System.out.println("--------------------------------------------");
		}catch(FileNotFoundException ex) {
			System.out.println(ex);
		}catch(Exception ex1) {
			System.out.println(ex1);
		}
		finally {
			in.close();
		}
		}
		 public static void delFile(File file) {//This method deletes all content of the object passed. 
			  if(!file.isDirectory()) {
				  System.out.println("Deleted File: " + file.getAbsolutePath());
				  file.delete();
			  }else if(file.isDirectory()&&file.list().length==0) {
				  System.out.println("Deleted Directory: " + file.getAbsolutePath());
				  file.delete();
			  }else {
				  while(!(file.listFiles().length==0)) {
					   if(file.isDirectory()) {
						   if(file.list().length==0) {
							   file.delete();
							   System.out.println("Deleted Directory: " + file.getAbsolutePath());
						   }
						   else delFile(file.listFiles()[0]);
		}
	  }
	}
			  file.delete();
  }
}		   