import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class feature_count_ratio {
    static int count=0;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		compare();
	}

	static void write_file() throws IOException {// ºg¿…
		FileWriter fw = new FileWriter(
				"J:/android_test/feature/rank_feature_ratio.txt");
		// for(int i =0; i < arraylist.size(); i++){
		// fw.write('\n'+ (String)arraylist.get(i) +"="+permission_count[i]);
		// }
		fw.close();
	}

	static void compare() throws IOException {
		double ratio1 = 0, ratio2 = 0;
		BufferedReader in, in2;
		in = new BufferedReader(new FileReader(
				"J:/android_test/feature/rank_feature_benign.txt"));
		in2 = new BufferedReader(new FileReader(
				"J:/android_test/feature/rank_feature_suspicious.txt"));
		FileWriter fw = new FileWriter(
				"J:/android_test/feature/rank_feature_ratio.txt");
		String benign = "", suspicious = "";
		while ((benign = in.readLine()) != null) {
			count++;
			System.out.println(count +"     "+benign + "");
			String[] temp = benign.split("=");
			suspicious = in2.readLine();
			String[] temp2 = suspicious.split("=");
			NumberFormat nf = new DecimalFormat("##0.0################################################");
		//	nf.setGroupingUsed( false );
			ratio1 = Float.parseFloat(temp[1]) / 123453;
			ratio2 = Float.parseFloat(temp2[1]) / 5560;
		//	System.out.println(nf.format(ratio1) + ":::::::"+ nf.format(ratio2)+"=="+count );
			fw.write(nf.format(ratio1) + "," + nf.format(ratio2) + '\n');
		}
		fw.close();
	}
}
