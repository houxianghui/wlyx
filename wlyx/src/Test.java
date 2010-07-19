import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	public static void main(String[] args)throws Exception {
		String s = "tesÖÐ»ª ÀÏ×ÖºÅ fdsf";
		Pattern p = Pattern.compile(".*(\\s+).*");
		Matcher m = p.matcher(s);
		while(m.find()){
			System.out.println(m.group());
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
	public static void hexToString(String s)throws Exception{
        byte[] b = new byte[s.length()/2];
        for(int i = 0,j=0;i<s.length();i++,i++,j++){
            byte b1= Integer.valueOf(s.charAt(i)+"", 16).byteValue();
            byte b2 = Integer.valueOf(s.charAt(i+1)+"",16).byteValue();
            b[j] = (byte)((b1<<4)+b2);
        }
        String value = new String(b,"gbk");
        System.out.println(value);
}
}
