import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class extract_all_permission {
	static ArrayList arraylist=new ArrayList();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LongDir("J:/Drebin Dataset/feature_vectors/feature_vectors/");	
	     for (int i = 0; i < arraylist.size(); i++) { 
           System.out.println(arraylist.get(i)); 
           write_arrf((String)arraylist.get(i));
         }//開始處理計算資料
	}
	static void write_arrf(String s) throws IOException{//寫檔
		FileWriter fw = new FileWriter("J:/android_test/feature/all_permission.txt",true);
		fw.write('\n'+s);
		fw.close();		
	}
	static void all_permission_search(String path){//找出所有 有使用到的permission
		BufferedReader in;
		try {in = new BufferedReader(new FileReader(path));
	         String s="",temp ="";
	         while((s=in.readLine())!=null){
	           if(s.lastIndexOf("real_permission") != -1){
            	temp = s.substring(s.lastIndexOf("real_permission::")+17);
	            //System.out.println(temp);
	    		if (!arraylist.contains(temp))
	    		 {arraylist.add(temp);}
	           }
	         }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}      
   }
	 static void LongDir(String fstr) throws IOException {
		  //將傳入之字串參數,產生一個File物件,傳給遞迴函數
		  ListDir(new File(fstr));
		 }
		 static void ListDir(File f) throws IOException {		 
		  //如果傳入之File不存在,結束本次遞迴函數
		  if (!f.exists())
		   return;
		  //印出傳入之檔案或目錄之絕對路徑名稱
		  System.out.println(f.getPath());
		  //如果傳入之File不是目錄,結束本次遞迴函數
		  if (!f.isDirectory()){
			all_permission_search(f.getPath());
		   return;
		   }
		  //使用File.listFiles()方法,得到此目錄之所有子目錄(或檔案)
		  File flist[] = f.listFiles();
		  //將得到之此目錄所有子目錄(或檔案)依次傳給(call)本遞迴函數,來印出底下所有檔案結構
		  for (File ftmp : flist)
			   ListDir(ftmp);
		 }

}
