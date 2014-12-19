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
         }//�}�l�B�z�p����
	}
	static void write_arrf(String s) throws IOException{//�g��
		FileWriter fw = new FileWriter("J:/android_test/feature/all_permission.txt",true);
		fw.write('\n'+s);
		fw.close();		
	}
	static void all_permission_search(String path){//��X�Ҧ� ���ϥΨ쪺permission
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
		  //�N�ǤJ���r��Ѽ�,���ͤ@��File����,�ǵ����j���
		  ListDir(new File(fstr));
		 }
		 static void ListDir(File f) throws IOException {		 
		  //�p�G�ǤJ��File���s�b,�����������j���
		  if (!f.exists())
		   return;
		  //�L�X�ǤJ���ɮשΥؿ���������|�W��
		  System.out.println(f.getPath());
		  //�p�G�ǤJ��File���O�ؿ�,�����������j���
		  if (!f.isDirectory()){
			all_permission_search(f.getPath());
		   return;
		   }
		  //�ϥ�File.listFiles()��k,�o�즹�ؿ����Ҧ��l�ؿ�(���ɮ�)
		  File flist[] = f.listFiles();
		  //�N�o�줧���ؿ��Ҧ��l�ؿ�(���ɮ�)�̦��ǵ�(call)�����j���,�ӦL�X���U�Ҧ��ɮ׵��c
		  for (File ftmp : flist)
			   ListDir(ftmp);
		 }

}
