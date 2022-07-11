import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Directory_Deleter {
	public static void main(String[] args){//7/10/22 currently does not work with C:\Users\K\eclipse-workspace,  think it has something to do with parent dir.
				Scanner in=new Scanner(System.in);
				boolean b1=true; //All booleans are for use with while loops.
				boolean b2=true;
				boolean b3=true;
				boolean b4=true;
				
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
				
				System.out.println("-----------------------------------------------------------------------------------");
				System.out.println("Please enter keyword that is shared among all files that are selected for deletion.");
				System.out.println("-----------------------------------------------------------------------------------");
				System.out.println("\t\t\t\t|WARNING|");
				System.out.println("THE CONTENT OF ANY SUBFOLDER,THE SUBFOLDER ITSELF AND ANY FILES IN THE SAME FOLDER \n\t\t\tTHAT MATCH THIS KEY WILL BE DELTED.");
				System.out.println("-----------------------------------------------------------------------------------");
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
				ArrayList indexArr= new ArrayList();			
				while(b3) {
					for(int i=0;i<children.length;i++){ 
		                if(children[i].contains(x)) {
		                	System.out.println("Found file named:"+children[i]);
		                	indexArr.add(i);
		                	b3=false;
		                }
				}
				if(!b3) {
					boolean b5=true;
					String yes="Y";
					String no="N";
					while(b5) {
					System.out.println("Delete these Files?");
					System.out.println("Y/N");
					String z=in.nextLine();
						if(z.equalsIgnoreCase(no)||(z.equalsIgnoreCase(yes))) {
							b4=true;
							b5=false;
						}else {
							System.out.println("Please enter Y/N.");
						}
					}
				}else {
	                	System.out.println("No file found containg keyword.");
	                	System.out.println("Enter new keyword.");
	                	x=in.nextLine();
	                }
			 }
			if(b4) {	
				for(int i=0;i<indexArr.size();i++) {
					delFile(new File(dir,children[(int)indexArr.get(i)]));
				} 
			}else {
				System.out.println("Files have not been deleted, exiting program.");
			}
			
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