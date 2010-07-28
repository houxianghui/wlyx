import java.util.Calendar;

import com.blue.common.Portal;
import com.blue.common.User;
import com.blue.monstor.Monstor;


public class Main {
	public static void main(String[] args)throws Exception {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		System.out.println(day+" "+hour+":"+minute);
		if (day == Calendar.TUESDAY){
			if(hour >= 11 && minute>=30){
				System.out.println(true);
			}
		}else if(day == Calendar.WEDNESDAY){
			if(hour >0 && hour < 8){
				System.out.println(true);
			}
		}
		System.out.println(false);
	}
}
