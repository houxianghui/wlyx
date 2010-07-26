import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.Monitor;
import com.blue.common.User;
import com.blue.tools.Tools;


public class Test {
	public static void main(String[] args)throws Exception {
		//item_id":"20376","role_id":"25337","shop_id":"10","name":"\u795e\u5973\u8170\u5e26","equip_type":"4","w
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("sp_lulu");
		user.setPassword("abc123");
		user.login();
		
		Monitor m = new Monitor();
		String s = m.getScenes(user);
		writeText("huGuan2.txt", s);
	}
	
	public static void getBigMap()throws Exception{
		Pattern p = Pattern.compile("id\":\"(\\d+?)\",\"rank\":\"\\d+?\",\"name\":\"(\\S+)\",\"country_id\":\"(\\d+?)\",");
		Matcher m = p.matcher(readText("bigMap.txt"));
		while(m.find()){
			System.out.println(m.group(1) + " "+Tools.hexToString(m.group(2)));
		}
	}
	public static void makeMap()throws Exception{
		//17      ��  	���ѽ���  20    ��ƽ��|��¹��|��ӵ�
		Pattern p = Pattern.compile("(\\d+).*?(\\d+).*?(\\S+)");
		Matcher m = p.matcher(readText("monstors.txt"));
		while(m.find()){
			System.out.print("monstors.put(\"");
			System.out.print(m.group(1));
			System.out.print("\", new String[]{");
			
			
			String s = m.group(3);
			String[] t = s.split("\\|");
			
			for(int i = 0;i < t.length;i++){
				System.out.print("\""+t[i]+"\"");
				if(i != t.length -1 ){
					System.out.print(",");
				}
			}
			System.out.println("});");
		}
	}
	public static String readText(String fileName)throws Exception{
		File f = new File(fileName);
		FileReader fr = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(fr);
		
		String s = null;
		while((s=br.readLine())!=null){
			sb.append(s+"\n");
		}
		return sb.toString();
	}	
	public static void writeText(String fileName,String s)throws Exception{
		File f = new File(fileName);
		FileWriter fr = new FileWriter(f,true);
		BufferedWriter bw = new BufferedWriter(fr);
		bw.write(s);
		bw.close();
	}	
}