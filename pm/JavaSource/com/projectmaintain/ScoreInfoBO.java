/*
 * @# ScoreInfoBO.java 2009-5-6 houxh
 *
 * Copyright  (c)  2003 	Huateng. All Right Reserv
 */
 
package com.projectmaintain;

import java.util.Iterator;
import java.util.List;

import com.eis.base.IbatisBaseBO;
import com.eis.cache.ReDefSDicMap;


public class ScoreInfoBO extends IbatisBaseBO {

	/* 
	 * @see com.eis.base.IbatisBaseBO#update(java.lang.Object)
	 */
	public void update(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#insert(java.lang.Object)
	 */
	public void insert(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForObject(java.lang.Object)
	 */
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#queryForList(java.lang.Object)
	 */
	public List queryForList(Object obj) throws Exception {
		return dao.queryForList(namespace+".queryScoreList",obj);
	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#delete(java.lang.Object)
	 */
	public void delete(Object obj) throws Exception {

	}

	/* 
	 * @see com.eis.base.IbatisBaseBO#transOperation(java.lang.Object[])
	 */
	public void transOperation(Object[] obj) throws Exception {
		if(obj.length == 0){
			return;
		}
		dao.delete(namespace+".deleteScoreInfo",obj[0]);
		for(int i = 0;i < obj.length;i++){
			dao.insert(namespace+".insertScoreInfo",obj[i]); 
		}
	}
	
	public String calc(ScoreInfoVO vo){
		return ((Double)dao.queryForObject(namespace+".queryForCalcScore",vo)).toString();
	}
	
	public String createTableElements(List l){
		StringBuffer sb = new StringBuffer();
		Iterator it = l.iterator();
		String checkNo = "";
		TableElements te = null;
		ScoreInfoVO vo = null;
		String checkUser = null;
		while(it.hasNext()){
			vo = (ScoreInfoVO)it.next();
			if(checkUser == null){
				checkUser = vo.getCheckUser();
			}
			if(vo.getCheckNo().equals(checkNo)){
				te.memo.append(getRadio(vo));
			}else{
				checkNo = vo.getCheckNo();
				sb.append(processTableElements(te));
				te = new TableElements();
				te.checkNo = vo.getCheckNo();
				te.checkName = vo.getCheckName();
				te.memo = getRadio(vo);
				te.score = getText(vo);
			}
		}
		if(te != null){
			sb.append(processTableElements(te));
		}
		
		sb.append("<td colspan='3' class='dtPanel_Main'>∆¿∑÷»À:"+ReDefSDicMap.getDicItemVal("0003",checkUser)+"</td>");
		return sb.toString();
	}
	
	private StringBuffer getTd(String s,String align){
		return new StringBuffer("<td align='"+align+"'>"+s+"</td>");
	}
	private StringBuffer getTr(String s){
		return new StringBuffer("<tr align=\"left\" class=\"dtPanel_Main\" onclick=\"_clickTr( this )\">"+s+"</tr>");
	}
	/**
	 * generate html elements radio
	 * <input type="radio" name="radioXX" value="XX" 
	 * 		onclick="javascript:document.forms[0].scoreXX.value='';document.forms[0].levelXX.value='';/>
	 * @param vo
	 * @return
	 */
	private StringBuffer getRadio(ScoreInfoVO vo){
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='radio' class='Radio' name='");
		sb.append(getRadioName(vo));
		sb.append("' ");
		sb.append("value='");
		sb.append(vo.getAllGrade());
		sb.append("' ");
		if(vo.getGrade() != null && vo.getGrade().trim().length() > 0 && vo.getGrade().equals(vo.getAllGrade())){
			sb.append("checked");
		}
		sb.append(" onclick=\"javascript:document.forms[0].");
		sb.append(getTextName(vo));
		sb.append(".value='");
		sb.append(vo.getDefaultScore());
		sb.append("';document.forms[0].");
		sb.append(getLevelName(vo));
		sb.append(".value='");
		
		sb.append(vo.getAllGrade());
		
				
		sb.append("';\"/>");
		sb.append(vo.getAllGrade()+"."+vo.getMemo()+"<br>");
		return sb;
	}
	/**
	 * generate html elements text
	 * <input type="text" name="scoreXX" value=''/>
	 * @param vo
	 * @return
	 */
	private StringBuffer getText(ScoreInfoVO vo){
		StringBuffer sb = new StringBuffer();
		sb.append(getAddButton(vo));
		sb.append("<input type=\"text\" size='3' maxsize='3' class='TextField' name='");
		sb.append(getTextName(vo));
		sb.append("'");
		if(vo.getScore() > 0){
			sb.append("value='"+vo.getScore()+"'");
		}
		sb.append("/>");
		sb.append(getHiddenLevel(vo));
		sb.append(getSubButton(vo));
		return sb;
	}
	private StringBuffer getSubButton(ScoreInfoVO vo){
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='button' class=\"Button\" value='&or;' onclick=\"javascript:document.forms[0].");
		sb.append(getTextName(vo));
		sb.append(".value=parseInt(document.forms[0]."+getTextName(vo)+".value)-1;\"/><br>");
		return sb;
	}
	private StringBuffer getAddButton(ScoreInfoVO vo){
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='button' class=\"Button\" value='&and;' onclick=\"javascript:document.forms[0].");
		sb.append(getTextName(vo));
		sb.append(".value=parseInt(document.forms[0]."+getTextName(vo)+".value)+1;\"/><br>");
		return sb;
	}
	/**
	 * generate html element hidden
	 * <input type="hidden" name='' value=''/>
	 * @param vo
	 * @return
	 */	
	private StringBuffer getHiddenLevel(ScoreInfoVO vo){
		StringBuffer sb = new StringBuffer();
		sb.append("<input type='hidden' name='");
		sb.append(getLevelName(vo));
		sb.append("' value='");
		sb.append(vo.getGrade());
		sb.append("'");
		sb.append("/>");
		return sb;
	}
	
	private String getRadioName(ScoreInfoVO vo){
		return "radio"+vo.getCheckNo().trim();	
	}
	private String getLevelName(ScoreInfoVO vo){
		return "level"+vo.getCheckNo().trim();
	}
	private String getTextName(ScoreInfoVO vo){
		return "score"+vo.getCheckNo().trim();
	}
	
	private StringBuffer processTableElements(TableElements te){
		if(te == null){
			return new StringBuffer();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(getTd(te.checkNo+te.checkName,"center"));
		sb.append(getTd(te.memo.toString(),"left"));
		sb.append(getTd(String.valueOf(te.score),"center"));
		sb = getTr(sb.toString());
		return sb;
	}
	
	class TableElements{
		String checkNo="";
		String checkName="";
		StringBuffer memo=new StringBuffer();
		StringBuffer score=new StringBuffer();
	}
}
