package com.blue.task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegexTest {
	public static void main(String[] args) throws Exception{
//		Pattern p = Pattern.compile("<td.*?view_mission.*?(\\d+).*?(战斗型|对话型|收集型).*?mission_auto_complete.*?\\d+.*?(\\d+).*?自动完成",Pattern.DOTALL);
////		Pattern times = Pattern.compile("\\s+今日你已经发起了.*?(\\d+)",Pattern.MULTILINE);
//		Pattern p2 = Pattern.compile("<li>[经验|铜币|物品].*?(\\d+)");
//		Pattern p3 = Pattern.compile("<a href=\"javascript:void\\(0\\);\" onclick=\"view_mission \\( 'day', (\\d+), true \\)\" class=\"purple\">领取奖励",Pattern.DOTALL);
////		Matcher m = p.matcher(readText("task.txt"));
////		while(m.find()){
////			System.out.println(m.group(1)+" "+m.group(2)+" "+m.group(3));
////		}
//		Pattern p4 = Pattern.compile("mission_auto_complete \\( 'day', '(\\d+)', '(\\d+)' \\)\">自动完成");
//		Matcher m4 = p4.matcher(readText("withFinishingAndFinishedTask.txt"));
//		while(m4.find()){
//			System.out.println(m4.group(1)+" "+m4.group(2));
//		}
//		Matcher m2 = p2.matcher(readText("TaskDetail.txt"));
//		while(m2.find()){
//			System.out.println(m2.group(1));
//		}
//		Matcher m3 = p3.matcher(readText("finishTask.txt"));
//		while(m3.find()){
//			System.out.println(m3.group(1));
//		}
//		Matcher m5 = p4.matcher(readText("TaskCenter.txt"));
//		while(m5.find()){
//			System.out.println(m5.group());
//		}
//		Matcher m2 = times.matcher(readText());
//		while(m2.find()){
//			System.out.println(m2.group(1));
//		}
		Pattern p6 = Pattern.compile("免费完成任务.*?complete_auto_mission.*?(\\d+?),");
		String page = readText("autoFinishTask.txt");
		Matcher m6 = p6.matcher(page);
		while(m6.find()){
			System.out.println(m6.group()+"---"+m6.group(1));
		}
	}
	public static String readText(String filename)throws Exception{
		File f = new File(filename);
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
