package com.blue.monitor;

import com.blue.common.User;
import com.blue.tools.ItemTools;

public class PackageItemMonitor {
	public static void upadtePackage(User user){
		user.setPackageItems(ItemTools.getPack(user));
		user.setStockItems(ItemTools.getStockList(user));
		user.setTeamStockItems(ItemTools.getSiHaiKuFang(user));
	}
}
