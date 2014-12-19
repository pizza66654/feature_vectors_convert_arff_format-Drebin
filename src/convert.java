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
         }//�}�l�B�z�p����
	}

	static void permission_search(String path){//�O���C��app�ҨϥΨ쪺�v��
		BufferedReader in;
		ArrayList permission_record = new ArrayList();//�O���v���̫�b���
		try {in = new BufferedReader(new FileReader(path));
	         String s="",temp = "";
	         while((s=in.readLine())!=null){
	           if(s.lastIndexOf("real_permission") != -1){
            	temp = s.substring(s.lastIndexOf("real_permission::")+17);
            	permission_record.add(temp);
	           }
	         }
	         ////////////////////////////////////////����Ū�� �}�l�g�Jarff
	         FileWriter fw = new FileWriter("J:/android_test/feature/data.arff",true);
	         fw.write("\n");
		     for (int i = 0; i < arraylist.size(); i++) { 
		       if(permission_record.contains(arraylist.get(i)))
		       fw.write("1,");//���ϥΨ���v��
		       else
		       fw.write("0,");//�L�ϥ�
		     }
		     //�ھڼ˥����W���ɮ� �P�_�O�_��malware
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
			permission_search(f.getPath());
		   return;
		   }
		  //�ϥ�File.listFiles()��k,�o�즹�ؿ����Ҧ��l�ؿ�(���ɮ�)
		  File flist[] = f.listFiles();
		  //�N�o�줧���ؿ��Ҧ��l�ؿ�(���ɮ�)�̦��ǵ�(call)�����j���,�ӦL�X���U�Ҧ��ɮ׵��c
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
