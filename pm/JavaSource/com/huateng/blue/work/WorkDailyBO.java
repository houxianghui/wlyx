package com.huateng.blue.work;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.abc.logic.IbatisBO;
import com.eis.cache.ReDefSDicMap;
import com.eis.cache.RedefSDicCodes;
import com.eis.cache.SingleDic;
import com.eis.cache.SingleDicMap;
import com.eis.config.SysConfig;
import com.eis.portal.UserContext;
import com.eis.util.CheckUtil;
import com.eis.util.DateUtil;
import com.eis.util.NumberToChineseUtil;
import com.eis.util.StringUtil;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class WorkDailyBO extends IbatisBO {	

	@Override
	public void update(Object obj) throws Exception {

	}

	@Override
	public void insert(Object obj) throws Exception {

	}

	@Override
	public Object queryForObject(Object obj) throws Exception {
		return null;
	}

	@Override
	public List queryForList(Object obj) throws Exception {
		return null;
	}

	@Override
	public void delete(Object obj) throws Exception {

	}
	public void setDailyInfo(String date,UserContext user,HttpServletRequest request){
		WorkDailyExample example = new WorkDailyExample();
		String userId = request.getParameter("user");
		if(CheckUtil.isEmptry(userId)){
			userId = user.getUserID();
		}
		example.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(userId);
		List l = dao.queryForListByGenerate("work_daily",example);
		request.setAttribute("work", l);
		
		DailyAchieveExample e = new DailyAchieveExample();
		e.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(userId);
		l = dao.queryForListByGenerate("daily_achieve", e);
		request.setAttribute("achieve", l);
		
		DailyIssueExample issue = new DailyIssueExample();
		issue.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(userId);
		l = dao.queryForListByGenerate("daily_issue", issue);
		request.setAttribute("issue", l);
		
		request.setAttribute("date", date);
	}
	public List getWorkList(String date,UserContext user){
		WorkDailyExample example = new WorkDailyExample();
		example.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		return dao.queryForListByGenerate("work_daily",example);
	}
	public List getAchieveList(String date,UserContext user){
		DailyAchieveExample e = new DailyAchieveExample();
		e.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		return dao.queryForListByGenerate("daily_achieve", e);
		
	}
	public List getIssueList(String date,UserContext user){
		DailyIssueExample issue = new DailyIssueExample();
		issue.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		return dao.queryForListByGenerate("daily_issue", issue);
		
	}
	public List getActiveWork(){
		WorkListExample ex = new WorkListExample();
		ex.createCriteria().andWorkStatusEqualTo("N").addCriterion(" (IS_MAIN = 'Y' or IS_MAIN is null)");// andIsMainEqualTo("Y").;
		return dao.queryForListByGenerate("work_list", ex);
	}
	public List getMainProblems(){	
		return dao.queryForListByGenerate("main_problem", new MainProblemExample());
	}
	public List getDistributeUser(String workId){
		WorkDistributeExample e = new WorkDistributeExample();
		e.createCriteria().andWorkIdEqualTo(workId);
		return dao.queryForListByGenerate("work_distribute", e);
	}
	public List getWeekList(String date,UserContext user){
		String monday = DateUtil.getMondayStr(date);
		String sunday = DateUtil.getSundayStr(date);
		WorkDistributeExample example = new WorkDistributeExample();
		example.createCriteria().addCriterion("a.USER_ID='"+user.getUserID()+"' and (" +
				"(a.START_DATE>='"+monday+"' and a.START_DATE<='"+sunday+"') or " +
				"(a.END_DATE>='"+monday+"' and a.END_DATE<='"+sunday+"') or " +
				"(a.START_DATE<='"+monday+"' and a.END_DATE>='"+sunday+"'))");
		return dao.queryForList("work_distribute.getWeekWork", example);
	}
	public void batchDelete(String date,UserContext user){
		WorkDailyExample example = new WorkDailyExample();
		example.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		dao.deleteByGenerateWithCondition("work_daily", example);
		
		DailyAchieveExample e = new DailyAchieveExample();
		e.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		dao.deleteByGenerateWithCondition("daily_achieve", e);
		
		DailyIssueExample issue = new DailyIssueExample();
		issue.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		dao.deleteByGenerateWithCondition("daily_issue", issue);
	}
	public void batchUpdate(List<WorkDaily> wl,List<DailyAchieve> al,List<DailyIssue> il,String date,UserContext user){
		//clear old data
		batchDelete(date, user);
		
		//add new data
		if(wl != null && wl.size()>0){
			for(WorkDaily wd:wl){
				dao.insertByGenerate("work_daily", wd);
			}
		}
		if(al != null && al.size() >0){
			for(DailyAchieve da:al){
				dao.insertByGenerate("daily_achieve", da);
			}
		}
		if(il != null && il.size()>0){
			for(DailyIssue di:il){
				dao.insertByGenerate("daily_issue", di);
			}
		}
	}
	public boolean checkRepeated(String date,UserContext user)throws Exception{
		WorkDailyExample example = new WorkDailyExample();
		example.createCriteria().andWorkDateEqualTo(date).andUserIdEqualTo(user.getUserID());
		List l = dao.queryForListByGenerate("work_daily",example);
		if(l != null && l.size()>0){
			return true;
		}
		return false;
	}
	public com.itextpdf.text.Document generateDailyDocument(HttpServletRequest request,Document document)throws Exception{
		String date = request.getParameter("date");
		String title = "V+迁移项目工作日报";
		if(!CheckUtil.isEmptry(request.getParameter("title"))){
			title = SingleDicMap.getDicItemVal(SingleDic.TITLE, request.getParameter("title"));
		}
//		document = new com.itextpdf.text.Document(PageSize.A4, 50, 50, 50, 50); 
//		document.open(); // 打开文档
		Image image = Image.getInstance(System.getProperty("resource")+"/logo.jpg"); 
		java.util.List<UserContext> userList = dao.queryForList("ProjectMaintain.getStaff",null);
		
		image.scalePercent(15f);
		document.add(image); 
		document.add(getHead(title)); 
		document.add(getLine()); 
		document.add(getSubHead(date)); 
		document.add(getLine()); 
		document.add(getZongTi(userList));
		
		int i = 0;
		for (UserContext u : userList) { 
			document.add(generateUserChapter(u, ++i,date)); 
		}
//		document.close();
		return document;
	}
	public String generateDaily(HttpServletRequest request)throws Exception{
		String date = request.getParameter("date");
		String title = "V+迁移项目工作日报";
		if(!CheckUtil.isEmptry(request.getParameter("title"))){
			title = SingleDicMap.getDicItemVal(SingleDic.TITLE, request.getParameter("title"));
		}
		String chName = date+".pdf";
		com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 50, 50, 50, 50); 
		File f = new File(SysConfig.getProperty("file.download"));
		if(!f.exists()){
			f.mkdirs();
		}
		PdfWriter.getInstance(document, new FileOutputStream(new File(SysConfig.getProperty("file.download")+File.separator+chName))); 
		document.open(); // 打开文档
		Image image = Image.getInstance(System.getProperty("resource")+"/logo.jpg"); 
		java.util.List<UserContext> userList = dao.queryForList("ProjectMaintain.getStaff",null);
		
		image.scalePercent(15f);
		document.add(image); 
		document.add(getHead(title)); 
		document.add(getLine()); 
		document.add(getSubHead(date)); 
		document.add(getLine()); 
		document.add(getZongTi(userList));
		
		
		int i = 0;
		for (UserContext u : userList) { 
			document.add(generateUserChapter(u, ++i,date)); 
		}
		document.close(); // 关闭文档
		return chName;
	}
	private Chapter getZongTi(List normalUserList) throws Exception {
		Chapter zt = getChapter("总体说明",0);
		
		com.itextpdf.text.List l = new com.itextpdf.text.List(true);
		ListItem item = new ListItem(getNormal("主要问题"));
		item.add(getLine());
		PdfPTable table = getTable(new float[]{0.1f,0.9f});
		List<MainProblem> ml = getMainProblems();
		for(int i = 0;i<ml.size();i++){
			MainProblem m = ml.get(i);
			table.addCell(getCell((i+1)+""));
			table.addCell(getCell(m.getContent()));
		}
		for(int i = ml.size();i<3;i++){
			table.addCell(getCell((i+1)+""));
			table.addCell(getCell(""));
		}
		item.add(table);
		l.add(item);
		
		item =  new ListItem(getNormal("任务分配"));
		item.add(getLine());
		table = getTable(new float[]{0.1f,0.5f,0.25f,0.15f});
		table.addCell(getCell("序号"));
		table.addCell(getCell("主要任务"));
		table.addCell(getCell("人员安排"));
		table.addCell(getCell("结束日期"));
		List<WorkList> works = getActiveWork();
		for(int i = 0;i<works.size();i++){
			WorkList wl = works.get(i);
			List dl = getDistributeUser(wl.getWorkId());
			table.addCell(getCell((i+1)+""));
			table.addCell(getCell(formatContent(wl.getContent())));
			table.addCell(getCell(getUsers(dl,wl.getInputUser(),normalUserList)));
			table.addCell(getCell(DateUtil.format(DateUtil.parseDate(wl.getEndDate()),"yyyy-MM-dd")));
		}
		item.add(table);
		l.add(item);
		zt.add(l);
		return zt;
	}
	private String formatContent(String content){
		content = content.replaceAll("\\s+", "");
		if(content.length() == 0){
			return content;
		}
		if(content.charAt(content.length()-1)==':'){
			content = content.substring(0,content.length()-1);
		}
		return content;
	}
	private String getUsers(List<WorkDistribute> l,String leader,List<UserContext> normalUserList){
		Map<String,String> m = new HashMap<String, String>();
		for(UserContext user:normalUserList){
			m.put(user.getUserID(), "True");
		}
		String[] userNames = new String[l.size()];
		for(int i= 0;i<userNames.length;i++){
			userNames[i]="";
		}
		int i = 0;
		for(WorkDistribute wd:l){
			if(leader.equals(wd.getUserId())){
				userNames[i]="0000"+ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,wd.getUserId());//+"(组长)";
			}else{
				if(m.get(wd.getUserId()) != null){
					userNames[i]=ReDefSDicMap.getDicItemVal(RedefSDicCodes.USER,wd.getUserId());
				}else{
					continue;
				}
			}
			i++;
		}
		Arrays.sort(userNames);
		
		StringBuffer sb = new StringBuffer();
		String sepreator = "";
		for(String s : userNames){
			if(CheckUtil.isEmptry(s)){
				continue;
			}
			sb.append(sepreator);
			sb.append(StringUtil.formatUser(s));
			sepreator = "、";
		}
		return sb.toString();
	}

	public Chapter generateUserChapter(UserContext user,int chapter,String date)throws Exception{
		Chapter zt = getChapter(NumberToChineseUtil.getChinese(chapter)+"、"+user.getUserName(),0,chapter);
		com.itextpdf.text.List l = new com.itextpdf.text.List(true);
		
		geneWeek(date,l,user);
		geneWork(date, l,user);
		geneAchieve(date, l,user);
		geneIssue(date, l,user);
		
		zt.add(l);
		return zt;
		
	}
	protected void geneWeek(String date, com.itextpdf.text.List l,UserContext user)
			throws Exception {
		ListItem section;
		PdfPTable table;
		section = new ListItem(getNormal("本周工作内容"));
		section.add(getLine());
		table = getTable(new float[]{0.1f,0.9f});
		List weekList = getWeekList(date, user);
		for(int i = 0;i<weekList.size();i++){
			WorkDistribute wd = (WorkDistribute)weekList.get(i);
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(formatContent(wd.getContent())));
		}
		for(int i = weekList.size();i<3;i++){
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(" "));
		}
		section.add(table);
		l.add(section);
	}
	protected void geneIssue(String date, com.itextpdf.text.List l,UserContext user)
			throws Exception {
		ListItem section;
		PdfPTable table;
		section = new ListItem(getNormal("遗留问题"));
		section.add(getLine());
		table = getTable(new float[]{0.1f,0.8f,0.1f});
		List issueList = getIssueList(date,user);
		for(int i = 0;i<issueList.size();i++){
			DailyIssue di = (DailyIssue)issueList.get(i);
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(di.getIssue()));
			table.addCell(getCell(di.getIssuePercent()+"%"));
		}
		for(int i = issueList.size();i<3;i++){
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(" "));
			table.addCell(getCell(""));
		}
		section.add(table);
		l.add(section);
	}

	protected void geneAchieve(String date, com.itextpdf.text.List l,UserContext user)
			throws Exception {
		ListItem section;
		PdfPTable table;
		section = new ListItem(getNormal("工作成果"));
		section.add(getLine());
		table = getTable(new float[]{0.1f,0.8f,0.1f});
		List harvestList = getAchieveList(date,user);
		for(int i = 0;i<harvestList.size();i++){
			DailyAchieve achieve = (DailyAchieve)harvestList.get(i);
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(achieve.getAchievement()));
			table.addCell(getCell(achieve.getAchievePercent()+"%"));
		}
		for(int i = harvestList.size();i<3;i++){
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(" "));
			table.addCell(getCell(""));
		}
		section.add(table);
		l.add(section);
	}

	protected void geneWork(String date, com.itextpdf.text.List l,UserContext user) throws Exception {
		ListItem section = new ListItem(getNormal("工作内容"));
		
		section.add(getLine());
		PdfPTable table = getTable(new float[]{0.1f,0.9f});
		List workList =getWorkList(date,user);
		for(int i = 0;i<workList.size();i++){
			WorkDaily wd = (WorkDaily)workList.get(i);
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(wd.getContent()));
		}
		for(int i = workList.size();i<3;i++){
			table.addCell(getCell(""+(i+1)));
			table.addCell(getCell(" "));
		}
		
		section.add(table);
		l.add(section);
	}
	public Paragraph getSubHead(String date)throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
		BaseFont chinese = getFont(SIMKAI);
		Font font = new Font(chinese,12,Font.UNDERLINE);
		Paragraph p = new Paragraph("华腾公司  "+df.format(DateUtil.parseDate(date)),font);
		p.setAlignment(Element.ALIGN_CENTER);
		return p;
	}
	public Paragraph getHead(String title)throws Exception{
		BaseFont chinese = getFont(STXINWEI);
		Font font = new Font(chinese,16);
		Paragraph head = new Paragraph(title,font);
		head.setAlignment(Element.ALIGN_CENTER);
		return head;
	}
	public Paragraph getLine(){
		Paragraph line = new Paragraph(" ");
		return line;
	}
	public Chapter getChapter(String content,int depth,int chapter)throws Exception{
		BaseFont chinese = getFont(SIMSUN);
		Font font = new Font(chinese,12,Font.BOLD);
		Paragraph p = new Paragraph(content,font);
		Chapter c = new Chapter(p,chapter);
		c.setNumberDepth(depth);
		c.setTriggerNewPage(false);
		return c;
	}
	public Chapter getChapter(String content,int chapter)throws Exception{
		return getChapter(content, 0,chapter);
	}
	public Paragraph getNormal(String content)throws Exception{
		BaseFont chinese = getFont(SIMSUN);
		Font font = new Font(chinese,10.5f,Font.NORMAL);
		Paragraph p = new Paragraph(content,font);
		return p;
	}
	public PdfPTable getTable(int cols){
		PdfPTable table = new PdfPTable(cols);
		return table;
	}
	public PdfPTable getTable(float[] width){
		PdfPTable tb = new PdfPTable(width);
		tb.setWidthPercentage(100);
		return tb;
	}
	public PdfPCell getCell(String content)throws Exception{
		PdfPCell cell = new PdfPCell(getNormal(content));
		return cell;
	}
	public static final int SIMSUN=0;
	public static final int STXINWEI=1;
	public static final int SIMKAI=2;
	private BaseFont getFont(int i)throws Exception{
		String resource = System.getProperty("resource");
		if(i == SIMSUN){
			return BaseFont.createFont(resource+"/SIMSUN.TTC,1",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		}else if(i == STXINWEI){
			return BaseFont.createFont(resource+"/STXINWEI.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		}else if(i == SIMKAI){
			return BaseFont.createFont(resource+"/SIMKAI.TTF",BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
		}
		return null;
	}
}
