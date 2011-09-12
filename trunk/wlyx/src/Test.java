import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blue.common.DailyChange;
import com.blue.common.DropWeapon;
import com.blue.common.Monitor;
import com.blue.common.MonitorThread;
import com.blue.common.Move;
import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.daily.CountryChange;
import com.blue.daily.MianChiLingPai;
import com.blue.duel.Duel;
import com.blue.huanjing.HuanJing;
import com.blue.monitor.ItemChange;
import com.blue.monstor.Item;
import com.blue.monstor.ItemMerge;
import com.blue.monstor.Monstor;
import com.blue.slavy.CatchSlavy;
import com.blue.task.AutoTask;
import com.blue.task.Task;
import com.blue.team.WuGuan;
import com.blue.tianjitang.TianJiTang;
import com.blue.tianjitang.TianJiThread;
import com.blue.tools.PageService;
import com.blue.tools.Tools;
import com.blue.warrior.Warrior;


public class Test {
	public static void main(String[] args)throws Exception {
//		int i = Portal.getIntValue("32");
//		System.out.println(i);
		System.setProperty("sun.net.client.defaultConnectTimeout", "15000");
		System.setProperty("sun.net.client.defaultReadTimeout","15000");
		System.setProperty("GZIP","");
		User user = new User();
		user.setUrl("s4.verycd.9wee.com");
		user.setUserName("xianghui_hou");
		user.setPassword("abc123");
		user.login(false);
		
		CountryChange.batchChange(user);
//		HuanJing.readSave(user);
//		MianChiLingPai.getLingPai(user);
//		MianChiLingPai.moveToLianZongXiYing(user);
//		Monitor.buyGlory(user);
//		for(int i =1;i < 10;i++)
//		acceptTask(user, "4100"+i);
//		boolean flag = Portal.isBeating(user);
//		System.out.println(flag);
//		user.setNeedGetAward(true);
//		ItemChange.changeChengJiDan(user);
//		Monitor.getAwards(user);
//		List<Item> l = ItemMerge.getPack(user);
//		Iterator<Item> it = l.iterator();
//		while(it.hasNext()){
//			System.out.println(it.next());
//		}
//		Monitor.getGuoDuAward(user);
//		user.setDuelStartTime(20);
//		Duel.duel(user);
//		Monitor.buyPool(user);
//		DropWeapon.dropWeapon(user);
//		TianJiTang tj = new TianJiTang();
//		tj.autoTask(user);
//		WuGuan.gotoWuGuan(user);
		
	}
	private static void acceptTask(User user,String taskId){
//		if(getTask(user, taskId)){
			String url = user.getUrl()+AutoTask.ACCEPT_TASK_URL+taskId+Tools.getTimeStamp(true);
			String page = PageService.getPageWithCookie(url, user);
			System.out.println(Tools.hexToString(page));
//			return Tools.success(page);
//		}
//		return false;
		
	}
	public static void getBigMap()throws Exception{
		Pattern p = Pattern.compile("id\":\"(\\d+?)\",\"rank\":\"\\d+?\",\"name\":\"(\\S+)\",\"country_id\":\"(\\d+?)\",");
		Matcher m = p.matcher(readText("bigMap.txt"));
		while(m.find()){
			System.out.println(m.group(1) + " "+Tools.hexToString(m.group(2)));
		}
	}
	public static void makeMap()throws Exception{
		//17      能  	爆裂剑首  20    安平郡|巨鹿镇|洛河道
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
		FileWriter fr = new FileWriter(f,false);
		BufferedWriter bw = new BufferedWriter(fr);
		bw.write(s);
		bw.close();
	}	
}
