package frame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintStream; //import java.net.URLClassLoader;  

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.start.Main;

public class TestSystemTray {
	public static void startWithFrame() throws Exception {

		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // 判断系统是否支持系统托盘
		{
			SystemTray tray = SystemTray.getSystemTray(); // 获取系统托盘
			Image image = Toolkit.getDefaultToolkit().getImage("TrayIcon.png");// 载入图片
			// 创建一个窗体
			final JFrame frame = new JFrame();
			frame.setIconImage(image);
			frame.setResizable(false);
			frame.setTitle("暗影刺客");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLayout(new BorderLayout());
			frame.setBounds(800, 800, 400, 400);
			frame.setLocationRelativeTo(null);

			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowIconified(WindowEvent e) {
					frame.setVisible(false);
				}
			});

			JButton jb = new JButton("开始挂机");
			final JTextArea jta = new JTextArea();
			jta.setAutoscrolls(true);
			jta.setLineWrap(true);
			jta.setDocument(new LimitDocument(jta));
			PrintStream ps = new PrintStream(new MyOutputStream(jta));
			System.setOut(ps);
			System.setErr(ps);

			jb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					new Thread() {
						@Override
						public void run() {
							try {
								Main.start();
							} catch (Exception e) {
								jta.setText(e.getMessage());
							}
						}
					}.start();

				}
			});

			JButton exit = new JButton("退出");
			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			frame.add(jb, BorderLayout.NORTH);
			frame.add(new JScrollPane(jta), BorderLayout.CENTER);
			frame.add(exit, BorderLayout.SOUTH);
			frame.setVisible(true);

			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.repaint();
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL);
				}
			};

			// 创建托盘图标的右键弹出菜单，open与exit.
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("还原");
			defaultItem.addActionListener(listener);
			MenuItem exitItem = new MenuItem("退出");
			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);

				}
			});
			popup.add(defaultItem);
			popup.add(exitItem);
			trayIcon = new TrayIcon(image, "暗影刺客", popup);// 创建托盘图标

			trayIcon.addActionListener(listener);// 双击托盘图标时打开窗体

			try {
				tray.add(trayIcon);// 将图标加入到系统托盘区
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
}