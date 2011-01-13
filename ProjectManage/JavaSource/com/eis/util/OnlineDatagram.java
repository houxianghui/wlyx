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
 * 在线交易报文格式封装类
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
            acct = acct.substring(acct.length() - 19); //这样写避免循环拼接字符串
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
        if (section == null) //表明从根节点取
            sect = m_root;
        else
            sect = m_root.element(section);
        if (sect == null) //没有该节
            sect = m_root.addElement(section);
        fld = sect.element(field);
        if (fld == null) //没有该域
            return null;
        return fld.getText();
    }

    public String getField(String section, String field) throws BaseException {
        String ret;
        ret = getFieldOptional(section, field);
        if (ret == null)
            throw new MessageException("缺少结点/ap/" + section + "/" + field);
        return ret;
    }

    public void setField(String section, String field, String value) {
        Element sect, fld;
        if (value == null) //值为空则不加入结点
            return;

        if (section == null) //表明从根节点取
            sect = m_root;
        else
            sect = m_root.element(section);
        if (sect == null) //没有该节
            sect = m_root.addElement(section);
        fld = sect.element(field);
        if (fld == null) //没有该域
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
     * 设置默认字段值
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
     * 发送组织后的报文,回收接收到的报文
     * 
     * @return 返回的报文
     */
    public OnlineDatagram send() {
        Socket socket = null;
        OnlineDatagram datagram = null;

        try {
            String xml = getXML();
            
            SysLog.debug(xml);	//记录发送报文
            
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
            
            SysLog.debug(datagram.getXML());//记录回送报文
      
        } catch (Exception e)
        {
            SysLog.error("发送报文失败 ", e);
            datagram = null;
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    SysLog.error("Socket 关闭失败", e);
                }
            }
        }
        return datagram;
    }
    
	/**
		 * Create on 2008-4-16 14:22:39 Li Xue Meng
		 * 
		 * 发送组织后的报文,回收接收到的报文
		 * 
		 * @return 返回的报文
		 */
		public OnlineDatagram send(String ip,String port) throws Exception {
			Socket socket = null;
			OnlineDatagram datagram = null;

			try {
				String xml = getXML();
            
				SysLog.debug(xml);	//记录发送报文
				
				
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
            
				SysLog.debug(datagram.getXML());//记录回送报文
      
			} /*catch (Exception e)
			{
				SysLog.error("发送报文失败 ", e);
				datagram = null;
			}*/ finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						SysLog.error("Socket 关闭失败", e);
					}
				}
			}
			return datagram;
		}
    
    /**
     * Create on 2007-3-16 15:44:27 Ranger
     * 
     * 根据传入的响应码返回响应码的描述
     * 
     * @param responseCode 返回报文的响应码
     * @return 响应码的描述
     */
    
    public static String getResponseDesc(String responseCode) {
    	if(responseCode == null){
    		return null;
    	}
       	String[] key = { "0000", "0999", "0998", "0997", "0996", "0995", "0994", "0993", "0992", "0991", "0990", "0901", "0910", "0911", "0912", "0920", "0921", "0922", "0001", "0002", "0003", "0004", "0013", "0008", "0005", "0009", "0006", "0011", "0007", "0010", "0025", "0014", "0015", "0021", "0022", "0023", "0101", "0102", "0111", "0113", "0115", "0112", "0103", "0104", "0105", "0109", "0114", "0106", "0107", "0108", "0110", "0116", "1101", "1102", "1103", "1104", "1105", "1107", "1108", "1116", "1115","9000","1114" ,"1113" ,"9001","9002","9011","0913","1151"};
		String[] value = { "交易成功", "交易异常", "交易失败", "逻辑错误", "系统错误", "交易不一致", "不能处理的报文类型", "报文长度超限", "报文非法，关键域缺乏或错误", "报文键值重复", "报文长度不足", "系统暂停服务", "该项业务不支持", "该项业务暂时停办", "该项业务请稍后重试", "该交易设备没有配备合法柜员", "非法交易设备", "末笔交易应答流水号不匹配", "帐户不存在", "帐户已经挂失", "帐户余额不足", "帐户密码错误", "密码输入次数超限", "金额无效", "金额超限", "费用错误", "原交易不存在", "CUPS2.0原交易正在处理中", "原交易已撤消或冲正", "原交易金额错误", "撤销失败请重做", "结算不平", "贷记交易重复，30分钟后重试", "凭证类型错误", "凭证号码错误", "帐户凭证不存在", "卡号非法", "非受理卡", "没收卡", "过期卡", "卡已经挂失", "查询发卡方", "退货时间超限", "取现次数超限", "退货金额超限", "退货金额已经被结算", "代授权次数超限", "预授权结算金额超限", "原预授权金额已经被结算", "原预授权不存在", "无效商户", "磁条格式错误", "证件号码错误", "未被密码锁定", "约定借记帐号不符", "购汇金额超限", "约定还款已申请", "没有符合条件的记录", "未申请约定还款", "没有外币余额", "本期没有帐单" ,"申请额度不能超过该卡种上限","找不到对应礼品记录","礼品数量不足","不良特约商户不存在","该商户已经存在","V+服务无效","自动化审查未通过","锁定码交易受限，暂不支持积分兑换"};
            for (int i = 0; i < key.length; i++) {
            if(responseCode.equals(key[i])){
            	return value[i];
            }
        }
        return null;
    }

	/**
	 * 获得返回原因码描述
	 * 
	 * @param code 原因码
	 * @return
	 */
	public String getReasonDesc(String code){
		return (String)reasonMap.get(code);
	}
	/**
	 * 获得返回码描述信息
	 * 
	 * @param code 返回码
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
