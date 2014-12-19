import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class feature_count_benign {
	//計算個特徵在各族群最多
	static ArrayList arraylist=new ArrayList();
	static int [] permission_count = new int [70];
	static  int count=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		read_all_permission();
		real_file("J:/android_test/feature/data.arff");
	//	select_sort();
		for(int i=0;i < permission_count.length;i++)
        	System.out.println(arraylist.get(i)+"="+permission_count[i]);
		write_file();
	}
	static void real_file(String path){
		int flag = 0;
		BufferedReader in;
		try {
		in = new BufferedReader(new FileReader(path));
        String s="";
        while((s=in.readLine())!=null){
        	//System.out.println(s);
          flag++;
          if(flag > 73){//忽略前面宣告
        	  String []data = s.split(",");     	  
        	 // for(int i=0;i < data.length; i ++)
        		//  System.out.print(data[i]);
        	  //System.out.println("");      	  
        	  for(int i=0; i < data.length;i++){	  
        		  if(data[i].equals("1") && data[data.length-1].equals("suspicious") ){
        			  permission_count[i]++;
        			  System.out.print( data[data.length-1]);
        		  }
        	  }
          }
        }
        
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}  
	}
	static void read_all_permission() throws IOException{
		   BufferedReader in;
		   in = new BufferedReader(new FileReader("J:/android_test/feature/all_permission.txt"));
		  
		   String s="";
		   while((s=in.readLine())!=null){
			   arraylist.add(s);
			   count++;
		   }
		
		}
	static void select_sort(){	
		for(int i = 0; i < permission_count.length;i++){
			int num_max = i;
			for(int j = i;j < permission_count.length; j++){
				if(permission_count[num_max] < permission_count[j])
					num_max = j;
			}
			int temp = permission_count[i];//swap
			permission_count[i] = permission_count[num_max];
			permission_count[num_max] = temp;
			///////////////////////////////////////
			Collections.swap(arraylist,i,num_max);//arraylist(permission)也需要跟著交換
		}
	}
	static void write_file() throws IOException{//寫檔
		FileWriter fw = new FileWriter("J:/android_test/feature/rank_feature_suspicious.txt");
		for(int i =0; i < arraylist.size(); i++){
			fw.write( (String)arraylist.get(i) +"="+permission_count[i]+'\n');
		}
		fw.close();	
		   System.out.println("count="+count);
	}

}
