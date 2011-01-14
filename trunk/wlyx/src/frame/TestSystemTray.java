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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.net.URLClassLoader;  

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.start.Main;

public class TestSystemTray {
	public static void startWithFrame() throws Exception {
		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // �ж�ϵͳ�Ƿ�֧��ϵͳ����
		{
			SystemTray tray = SystemTray.getSystemTray(); // ��ȡϵͳ����
			Image image = Toolkit.getDefaultToolkit().getImage(
					"TrayIcon.png");// ����ͼƬ
			// ����һ������
			final JFrame frame = new JFrame();
			frame.setIconImage(image);
			frame.setResizable(false);
			frame.setTitle("��Ӱ�̿�");
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
			
			JButton jb = new JButton("��ʼ�һ�");
			final JTextArea jta = new JTextArea();
			jta.setAutoscrolls(true);
			jta.setLineWrap(true);
			jb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						new Thread() {
							public void run() {
								try {
									Main.start();
								} catch (Exception e) {
									System.exit(-1);
								}
							};
						}.start();
						// Main.start();
						new Thread() {
							@Override
							public void run() {
								BufferedReader br = null;
								try {
									while (true) {
										while(br == null){
											File f = new File("runtime.log");
											if(f.exists()){
												br =  new BufferedReader(new FileReader(f));
											}else
											sleep(1*1000);
										}
										String s = null;
										while ((s = br.readLine()) != null) {											
											StringBuffer sb = new StringBuffer(
													jta.getText() + s + "\n");
											int idx = 0;
											if (sb.length() > 500) {
												idx = sb.length() - 500;
											}
											jta.setText(sb.substring(idx));											
										}
										sleep(5 * 1000);
									}

								} catch (Exception e) {
									jta.setText(e.getStackTrace().toString());
								} finally {
									try {
										if (br != null) {
											br.close();
										}
									} catch (Exception e) {
										jta.setText(e.getStackTrace()
												.toString());
									}
								}
							}
						}.start();

					} catch (Exception e) {
						jta.setText(e.getStackTrace().toString());
					}
				}
			});
			JButton exit = new JButton("�˳�");
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

			// ��������ͼ����Ҽ������˵���open��exit.
			PopupMenu popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("��ԭ");
			defaultItem.addActionListener(listener);
			MenuItem exitItem = new MenuItem("�˳�");
			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);

				}
			});
			popup.add(defaultItem);
			popup.add(exitItem);
			trayIcon = new TrayIcon(image, "��Ӱ�̿�", popup);// ��������ͼ��

			trayIcon.addActionListener(listener);// ˫������ͼ��ʱ�򿪴���

			try {
				tray.add(trayIcon);// ��ͼ����뵽ϵͳ������
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
}