package com.blue.start;

import java.util.Iterator;
import java.util.List;
import com.blue.common.User;
import com.blue.tools.ItemMerge;
import com.blue.tools.ItemTools;

public class CheckAndSell {
	public static void main(String[] args) throws Exception {

		List<User> l = Main.getUserInfo();

		Iterator<User> it = l.iterator();
		while (it.hasNext()) {
			final User user = it.next();
			user.login(false);
			new Thread() {
				public void run() {
					ItemTools.checkAndSell(user);
					ItemMerge.merge(user);
					ItemMerge.mergeSiHaiKuFang(user);
					ItemMerge.mergeStock(user);
				};
			}.start();
			// Monstor.checkAndSell(user);

		}

	}

}
