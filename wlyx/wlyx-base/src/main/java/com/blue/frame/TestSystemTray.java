package com.blue.frame;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
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
import java.io.IOException;
import java.io.PrintStream; //import java.net.URLClassLoader;  

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.blue.start.Main;
import com.blue.start.Start;
import com.blue.tools.FileUtil;


public class TestSystemTray {
	public static void startWithFrame(Start start) throws Exception {
		makeWindow();
		start.run();
	}
	private static void makeWindow() {
		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // 判断系统是否支持系统托盘
		{
			SystemTray tray = SystemTray.getSystemTray(); // 获取系统托盘
			Image image = Toolkit.getDefaultToolkit().getImage(FileUtil.readUrl("TrayIcon.png"));// 载入图片
			// 创建一个窗体
			final JFrame frame = makeMainFrame(image);
			final JTextArea jta = makeConsole();
			JButton jb = makeStartButton(jta);

			Container c = new Container();
			FlowLayout fl = new FlowLayout();
			c.setLayout(fl);
			c.add(jb);
			c.add(makeMonitorButton());
			c.add(makeExitButton());
			
			frame.add(c, BorderLayout.NORTH);
			frame.add(new JScrollPane(jta), BorderLayout.CENTER);
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
			MenuItem monitor = new MenuItem("查看用户状态");
			monitor.addActionListener(getMonitorListener());
			popup.add(defaultItem);
			popup.add(monitor);
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
	private static JButton makeStartButton(final JTextArea jta) {
		JButton jb = new JButton("开始挂机");
		

		jb.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new Thread() {
					@Override
					public void run() {
						try {
							Main m = new Main();
							m.run();
						} catch (Exception e) {
							jta.setText(e.getMessage());
						}
					}
				}.start();

			}
		});
		return jb;
	}
	private static JTextArea makeConsole() {
		final JTextArea jta = new JTextArea();
		jta.setAutoscrolls(true);
		jta.setLineWrap(true);
		jta.setDocument(new LimitDocument(jta));
		PrintStream ps = new PrintStream(new MyOutputStream(jta));
		System.setOut(ps);
		System.setErr(ps);
		return jta;
	}
	private static JFrame makeMainFrame(Image image) {
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
		return frame;
	}
	private static JButton makeExitButton() {
		JButton exit = new JButton("退出");
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		return exit;
	}
	private static JButton makeMonitorButton(){
		JButton jb = new JButton("查看用户状态");
		jb.addActionListener(getMonitorListener());
		return jb;
	}
	private static ActionListener getMonitorListener(){
		return new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "http://localhost:"+System.getProperty("server.port")+"/list";
					Runtime.getRuntime().exec("explorer "+url);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
}