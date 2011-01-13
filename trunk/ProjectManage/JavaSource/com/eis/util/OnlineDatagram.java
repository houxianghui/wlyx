/*
 * Created on 2006-8-7
 *
 */
package com.eis.util;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.text.*;
import java.util.HashMap;

import com.eis.base.BaseException;
import com.eis.config.SysConfig;
import com.eis.exception.MessageException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


/**
 * @author chenjun
 *
 * ���߽��ױ��ĸ�ʽ��װ��
 */
public class OnlineDatagram {

    protected Document m_document;
    protected Element m_root;
    private HashMap respMap;
    private HashMap reasonMap;

    public OnlineDatagram() {
        m_document = DocumentHelper.createDocument();
        m_document.addElement("ap");
        m_root = m_document.getRootElement();
    }
    protected OnlineDatagram(Document doc) throws BaseException {
        m_document = doc;
        m_root = m_document.getRootElement();
    }

    public static OnlineDatagram ParseString(String xmlData) throws Exception {
        Document doc;
        doc = DocumentHelper.parseText(xmlData);
        return new OnlineDatagram(doc);
    }

    public static OnlineDatagram ParseStream(InputStream is) throws Exception {
        Document doc;
        SAXReader sar;
        sar = new SAXReader();
        doc = sar.read(is);
        return new OnlineDatagram(doc);
    }

    public String getCardno() throws BaseException {
        String acct;
        acct = getField("Acc", "AccNo");
        if (acct.trim().length() < 19) {
            acct = "0000000000000000000" + acct;
            acct = acct.substring(acct.length() - 19); //����д����ѭ��ƴ���ַ���
        }
        return acct;
    }

    public String getXML() throws IOException {
        StringWriter sw = new StringWriter();
        OutputFormat fmt;
        fmt = new OutputFormat("\t", true, "gb18030");
        fmt.setTrimText(true);
        XMLWriter xw = new XMLWriter(sw, fmt);
        xw.write(m_document);
        return sw.toString();
    }

    public String getFieldOptional(String section, String field) {
        Element sect, fld;
        if (section == null) //�����Ӹ��ڵ�ȡ
            sect = m_root;
        else
            sect = m_root.element(section);
        if (sect == null) //û�иý�
            sect = m_root.addElement(section);
        fld = sect.element(field);
        if (fld == null) //û�и���
            return null;
        return fld.getText();
    }

    public String getField(String section, String field) throws BaseException {
        String ret;
        ret = getFieldOptional(section, field);
        if (ret == null)
            throw new MessageException("ȱ�ٽ��/ap/" + section + "/" + field);
        return ret;
    }

    public void setField(String section, String field, String value) {
        Element sect, fld;
        if (value == null) //ֵΪ���򲻼�����
            return;

        if (section == null) //�����Ӹ��ڵ�ȡ
            sect = m_root;
        else
            sect = m_root.element(section);
        if (sect == null) //û�иý�
            sect = m_root.addElement(section);
        fld = sect.element(field);
        if (fld == null) //û�и���
            fld = sect.addElement(field);

        fld.setText(value);
    }
    public void setField(String field, String value) {
        setField(null, field, value);
    }

    public void setAccField(String field, String value) {
        setField("Acc", field, value);
    }

    public void setAccField(String field, double value) {
        DecimalFormat df = new DecimalFormat("################0.00");
        setField("Acc", field, df.format(value));
    }

    public String getAccField(String field) throws BaseException {
        return getField("Acc", field);
    }

    public void setQryLstField(String field, String value) {
        setField("QryLst", field, value);
    }

    public String getQryLstField(String field) throws BaseException {
        return getField("QryLst", field);
    }

    public void setRootField(String field, String value) {
        setField(null, field, value);
    }

    public String getRootField(String field) throws BaseException {
        return getField(null, field);
    }
    /**
     * Create on 2007-3-14 13:58:09 Ranger
     * 
     * ����Ĭ���ֶ�ֵ
     * 
     */
    public void setDefaultFeilds() {
        setField("ReqTime", DateUtil.getTimeOnly());
        setField("ReqDate", DateUtil.getDTStr());
        setField("ReqType", "99");
        setField("ReqId", "00000000");
    }
    /**
     * Create on 2007-3-14 14:22:39 Ranger
     * 
     * ������֯��ı���,���ս��յ��ı���
     * 
     * @return ���صı���
     */
    public OnlineDatagram send() {
        Socket socket = null;
        OnlineDatagram datagram = null;

        try {
            String xml = getXML();
            
            SysLog.debug(xml);	//��¼���ͱ���
            
            socket = new Socket(InetAddress.getByName(SysConfig.getProperty("onlineServerIP")), Integer.parseInt(SysConfig.getProperty("onlineServerPort")));

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            byte []data = xml.getBytes("GBK");
            out.writeInt(data.length);
            out.write(data);

            int len = in.readInt();
            
            data = new byte[len];
            for (int i=0; i<len;)
            {
            	i += in.read(data, i, len-i);
            }
            ByteArrayInputStream bais = new ByteArrayInputStream(data);

			SysLog.debug(new String(data));
            datagram = OnlineDatagram.ParseStream(bais);
            
            SysLog.debug(datagram.getXML());//��¼���ͱ���
      
        } catch (Exception e)
        {
            SysLog.error("���ͱ���ʧ�� ", e);
            datagram = null;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    SysLog.error("Socket �ر�ʧ��", e);
                }
            }
        }
        return datagram;
    }
    
	/**
		 * Create on 2008-4-16 14:22:39 Li Xue Meng
		 * 
		 * ������֯��ı���,���ս��յ��ı���
		 * 
		 * @return ���صı���
		 */
		public OnlineDatagram send(String ip,String port) throws Exception {
			Socket socket = null;
			OnlineDatagram datagram = null;

			try {
				String xml = getXML();
            
				SysLog.debug(xml);	//��¼���ͱ���
				
				
				socket = new Socket(InetAddress.getByName(ip), Integer.parseInt(port));

				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
            
				byte []data = xml.getBytes();
				out.writeInt(data.length);
				out.write(data);

				int len = in.readInt();
            
				data = new byte[len];
				for (int i=0; i<len;)
				{
					i += in.read(data, i, len-i);
				}
				ByteArrayInputStream bais = new ByteArrayInputStream(data);

				SysLog.debug(new String(data));
				datagram = OnlineDatagram.ParseStream(bais);
            
				SysLog.debug(datagram.getXML());//��¼���ͱ���
      
			} /*catch (Exception e)
			{
				SysLog.error("���ͱ���ʧ�� ", e);
				datagram = null;
			}*/ finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						SysLog.error("Socket �ر�ʧ��", e);
					}
				}
			}
			return datagram;
		}
    
    /**
     * Create on 2007-3-16 15:44:27 Ranger
     * 
     * ���ݴ������Ӧ�뷵����Ӧ�������
     * 
     * @param responseCode ���ر��ĵ���Ӧ��
     * @return ��Ӧ�������
     */
    
    public static String getResponseDesc(String responseCode) {
    	if(responseCode == null){
    		return null;
    	}
       	String[] key = { "0000", "0999", "0998", "0997", "0996", "0995", "0994", "0993", "0992", "0991", "0990", "0901", "0910", "0911", "0912", "0920", "0921", "0922", "0001", "0002", "0003", "0004", "0013", "0008", "0005", "0009", "0006", "0011", "0007", "0010", "0025", "0014", "0015", "0021", "0022", "0023", "0101", "0102", "0111", "0113", "0115", "0112", "0103", "0104", "0105", "0109", "0114", "0106", "0107", "0108", "0110", "0116", "1101", "1102", "1103", "1104", "1105", "1107", "1108", "1116", "1115","9000","1114" ,"1113" ,"9001","9002","9011","0913","1151"};
		String[] value = { "���׳ɹ�", "�����쳣", "����ʧ��", "�߼�����", "ϵͳ����", "���ײ�һ��", "���ܴ���ı�������", "���ĳ��ȳ���", "���ķǷ����ؼ���ȱ�������", "���ļ�ֵ�ظ�", "���ĳ��Ȳ���", "ϵͳ��ͣ����", "����ҵ��֧��", "����ҵ����ʱͣ��", "����ҵ�����Ժ�����", "�ý����豸û���䱸�Ϸ���Ա", "�Ƿ������豸", "ĩ�ʽ���Ӧ����ˮ�Ų�ƥ��", "�ʻ�������", "�ʻ��Ѿ���ʧ", "�ʻ�����", "�ʻ��������", "���������������", "�����Ч", "����", "���ô���", "ԭ���ײ�����", "CUPS2.0ԭ�������ڴ�����", "ԭ�����ѳ��������", "ԭ���׽�����", "����ʧ��������", "���㲻ƽ", "���ǽ����ظ���30���Ӻ�����", "ƾ֤���ʹ���", "ƾ֤�������", "�ʻ�ƾ֤������", "���ŷǷ�", "������", "û�տ�", "���ڿ�", "���Ѿ���ʧ", "��ѯ������", "�˻�ʱ�䳬��", "ȡ�ִ�������", "�˻�����", "�˻�����Ѿ�������", "����Ȩ��������", "Ԥ��Ȩ�������", "ԭԤ��Ȩ����Ѿ�������", "ԭԤ��Ȩ������", "��Ч�̻�", "������ʽ����", "֤���������", "δ����������", "Լ������ʺŲ���", "�������", "Լ������������", "û�з��������ļ�¼", "δ����Լ������", "û��������", "����û���ʵ�" ,"�����Ȳ��ܳ����ÿ�������","�Ҳ�����Ӧ��Ʒ��¼","��Ʒ��������","������Լ�̻�������","���̻��Ѿ�����","V+������Ч","�Զ������δͨ��","�����뽻�����ޣ��ݲ�֧�ֻ��ֶһ�"};
            for (int i = 0; i < key.length; i++) {
            if(responseCode.equals(key[i])){
            	return value[i];
            }
        }
        return null;
    }

	/**
	 * ��÷���ԭ��������
	 * 
	 * @param code ԭ����
	 * @return
	 */
	public String getReasonDesc(String code){
		return (String)reasonMap.get(code);
	}
	/**
	 * ��÷�����������Ϣ
	 * 
	 * @param code ������
	 * @return
	 */
	public String getRespDesc(String code){
		return (String)respMap.get(code);
	}


	/**
	 * @param map
	 */
	public void setReasonMap(HashMap map) {
		reasonMap = map;
	}

	/**
	 * @param map
	 */
	public void setRespMap(HashMap map) {
		respMap = map;
	}

}
