import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {
	public static void main(String[] args) throws Exception{
		Pattern p = Pattern.compile("\\s+<td.*?view_role\\s\\(\\s(\\d+).*?No.(\\d+).*?Lv.(\\d+)",Pattern.DOTALL);
		Pattern times = Pattern.compile("\\s+今日你已经发起了.*?(\\d+)",Pattern.MULTILINE);
		Matcher m = p.matcher(readText());
		while(m.find()){
			System.out.println(m.group(1)+" "+m.group(2)+" "+m.group(3));
		}
		Matcher m2 = times.matcher(readText());
		while(m2.find()){
			System.out.println(m2.group(1));
		}
	}
	public static String readText()throws Exception{
		File f = new File("test.txt");
		FileReader fr = new FileReader(f);
		StringBuffer sb = new StringBuffer();
		BufferedReader br = new BufferedReader(fr);
		
		String s = null;
		while((s=br.readLine())!=null){
			sb.append(s+"\n");
		}
		return sb.toString();
	}
}
