import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class convert {
	static ArrayList arraylist=new ArrayList();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		read_all_permission();
		initial_arrf();
		LongDir("J:/Drebin Dataset/feature_vectors/feature_vectors/");
	     for (int i = 0; i < arraylist.size(); i++) { 
           System.out.println(arraylist.get(i)); 
         }//開始處理計算資料
	}

	static void permission_search(String path){//記錄每個app所使用到的權限
		BufferedReader in;
		ArrayList permission_record = new ArrayList();//記錄權限最後在比對
		try {in = new BufferedReader(new FileReader(path));
	         String s="",temp = "";
	         while((s=in.readLine())!=null){
	           if(s.lastIndexOf("real_permission") != -1){
            	temp = s.substring(s.lastIndexOf("real_permission::")+17);
            	permission_record.add(temp);
	           }
	         }
	         ////////////////////////////////////////單檔讀完 開始寫入arff
	         FileWriter fw = new FileWriter("J:/android_test/feature/data.arff",true);
	         fw.write("\n");
		     for (int i = 0; i < arraylist.size(); i++) { 
		       if(permission_record.contains(arraylist.get(i)))
		       fw.write("1,");//有使用到該權限
		       else
		       fw.write("0,");//無使用
		     }
		     //根據樣本附上的檔案 判斷是否為malware
			 BufferedReader csv;
			 csv = new BufferedReader(new FileReader("J:/Drebin Dataset/sha256_family.csv"));
		//	 System.out.println("sha2");
			 String c="";
			 int judgement = 0;
			 while((c=csv.readLine())!=null){
		//		 System.out.println(c);
			   String []sha256_family = c.split(",");
			   String []name = path.split("\\\\");
			//   System.out.println("sha256_family[0]="+sha256_family[0]);
			   if(sha256_family[0].equals(name[name.length-1])){
				  judgement = 1;
				  break;
			   }
			 }
			 if(judgement == 1)
			  fw.write("suspicious");
			 else 
			  fw.write("benign");
			 fw.close();
	         
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
			permission_search(f.getPath());
		   return;
		   }
		  //使用File.listFiles()方法,得到此目錄之所有子目錄(或檔案)
		  File flist[] = f.listFiles();
		  //將得到之此目錄所有子目錄(或檔案)依次傳給(call)本遞迴函數,來印出底下所有檔案結構
		  for (File ftmp : flist)
			   ListDir(ftmp);
		 }
	static void read_all_permission() throws IOException{
	   BufferedReader in;
	   in = new BufferedReader(new FileReader("J:/android_test/feature/all_permission.txt"));
	   String s="";
	   while((s=in.readLine())!=null){
		   arraylist.add(s);
	   }
	}
	static void initial_arrf() throws IOException{
		FileWriter fw = new FileWriter("J:/android_test/feature/data.arff");
		fw.write("@RELATION android_malware");
	    for (int i = 0; i < arraylist.size(); i++) {
	    	fw.write("\n@ATTRIBUTE "+arraylist.get(i)+" REAL"); 
	    }
	    fw.write("\n@ATTRIBUTE class  {suspicious,benign}");
	    fw.write("\n@DATA");
	    fw.close();
	}
		 

}
