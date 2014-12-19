import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class compare_top_feature {
	static ArrayList arraylist_benign=new ArrayList();
	static ArrayList arraylist_suspicious=new ArrayList();
	static int top = 12+1;
	public static void main(String[] args) throws IOException {
		int attribute_count=0; 
		String[] diffrernt_attribute = new String [50];
		read_file(top);
		String [] different_attribute = new String [top];
		System.out.println("benign");
		for(int i = 0; i < arraylist_benign.size(); i++){
			if(!arraylist_suspicious.contains((String)arraylist_benign.get(i)) ){
			 System.out.println((String)arraylist_benign.get(i));
			 different_attribute[attribute_count] = (String)arraylist_benign.get(i);
			 attribute_count++;
			}
		}
		 System.out.println("**************************************************************");
		 System.out.println("suspicious");
		for(int i = 0; i < arraylist_suspicious.size(); i++){
			if(!arraylist_benign.contains((String)arraylist_suspicious.get(i)) ){
			 System.out.println((String)arraylist_suspicious.get(i));
			 different_attribute[attribute_count] = (String)arraylist_suspicious.get(i);
			 attribute_count++;
			//	System.out.println("RRRRRRRRRRRRRRRRRRRRRRRRRRR");

			}	
		}
		for(int i = 0; i < arraylist_suspicious.size(); i++){	
	//	System.out.println((String)arraylist_benign.get(i));	
		}
	}
	static void read_file(int top) throws IOException{
		String path="J:/android_test/feature/rank_feature_benign.txt";
		BufferedReader in;
		int count=0;
		try {
			in = new BufferedReader(new FileReader(path));
	         String s="";      
	         while((s=in.readLine())!=null){
	        	 if(count >= top)
	        		 break;
	        	 count++;
	        	 String []temp = s.split("=");
	        	 arraylist_benign.add(temp[0]);
	         }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String path2="J:/android_test/feature/rank_feature_suspicious.txt";
		BufferedReader in2;
		count=0;
		try {
			in2 = new BufferedReader(new FileReader(path2));
	         String s="";      
	         while((s=in2.readLine())!=null){
	        	 if(count >= top)
	        		 break;
	        	 count++;
	        	 String []temp = s.split("=");
	        	 arraylist_suspicious.add(temp[0]);
	         }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

}
