package frame;

import java.awt.AWTException;  
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
import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JPanel;  
import javax.swing.WindowConstants;  
  
public class TrayIconAdvanced extends JFrame {  
    private TrayIcon ti;  
    public static void main(String[] args) {  
        TrayIconAdvanced frame = new TrayIconAdvanced();  
        frame.setTitle("TrayIconAdvanced");  
        frame.setVisible(true);  
        frame.setSize(200, 65);  
        frame.setLocation(300, 200);  
    }  
  
    public TrayIconAdvanced() {  
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);  
        this.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
            if (SystemTray.isSupported()) {  
                setVisible(false);//���ش���  
                minimizeToTray();//���ϵͳ֧������ͼ��,�����ڷ���ϵͳ������  
            } else {  
                System.exit(0);//�����������  
                }  
            }  
        });  
  
        JPanel root = new JPanel();  
        JButton exitButton = new JButton("Exit");  
        exitButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                System.exit(0);//��������  
            }  
        });  
        root.add(exitButton);  
        getContentPane().add(root);  
        pack();  
        initTi();  
    }  
    private void initTi() {  
        Image image = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("TrayIcon.gif"));  
        PopupMenu popupTi = new PopupMenu();//�����˵�  
        MenuItem showItem = new MenuItem("Show");//�˵���  
        ActionListener showListener = new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                setVisible(true);//������ʾ����  
                SystemTray.getSystemTray().remove(ti);//��ϵͳ�������Ƴ�  
            }  
        };  
        showItem.addActionListener(showListener);//�˵�������  
        popupTi.add(showItem);  
        ti = new TrayIcon(image, "TrayIcon", popupTi);//ͼ�꣬���⣬�Ҽ������˵�  
        ti.addActionListener(showListener);//����һ��˫���¼�����  
  
    }  
  
    public void minimizeToTray() {  
        SystemTray tray = SystemTray.getSystemTray();  
        try {  
            tray.add(ti);//��ϵͳ������������ͼ��  
        } catch (AWTException e) {  
            System.err.println(e);  
        }  
    }  
}  