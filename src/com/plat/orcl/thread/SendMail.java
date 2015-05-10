package com.plat.orcl.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import com.plat.orcl.dao.PersonDao;
import com.plat.orcl.dao.impl.PersonDaoMysqlImpl;
import com.plat.orcl.dao.impl.ScoreDaoMysqlImpl;
import com.plat.orcl.domain.Person;
import com.plat.orcl.service.impl.ScoreServiceImpl;
import com.plat.orcl.utils.PubUtil;
//						 _ooOoo_  
//						o8888888o  
//						88" . "88  
//						(| -_- |)  
//			 			 O\ = /O  
//					 ____/`---'\____  
//				   .   ' \\| |// `.  
//					/ \\||| : |||// \  
//				  / _||||| -:- |||||- \  
//					| | \\\ - /// | |  
//				  | \_| ''\---/'' | |  
//				   \ .-\__ `-` ___/-. /  
//				___`. .' /--.--\ `. . __  
//			 ."" '< `.___\_<|>_/___.' >'"".  
//			| | : `- \`.;`\ _ /`;.`/ - ` : | |  
//			  \ \ `-. \_ __\ /__ _/ .-` / /  
//	  ======`-.____`-.___\_____/___.-`____.-'======  
//						 `=---='  
//	  .............................................  
//					  佛祖保佑             永无BUG 
//			佛曰:  
//				        写字楼里写字间，写字间里程序员；  
//				        程序人员写程序，又拿程序换酒钱。  
//				        酒醒只在网上坐，酒醉还来网下眠；  
//				        酒醉酒醒日复日，网上网下年复年。  
//				        但愿老死电脑间，不愿鞠躬老板前；  
//				        奔驰宝马贵者趣，公交自行程序员。  
//				        别人笑我忒疯癫，我笑自己命太贱；  
//				        不见满街漂亮妹，哪个归得程序员？ 
public class SendMail extends Thread {
	
	private Person tea ;
	private String chaptid ;
	private ScoreServiceImpl ssi ; 
	private ServletContext servletContext;
	public SendMail(Person p,String chaptid, ScoreServiceImpl ssi, ServletContext servletContext) {
		this.tea=p;
		this.chaptid=chaptid;
		this.ssi = ssi;
		this.servletContext = servletContext;
	}

	@Override
	public void run() {
		
		try {
			//随机选5个人
			final ArrayList<Person> pl =  this.getPerson(PubUtil.SELECT_NUM);
			//发邮件
			Properties prop = new Properties();
			prop.setProperty("mail.host",PubUtil.MAIL_HOST );
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			Session session = Session.getInstance(prop);
			Transport ts = session.getTransport();
			ts.connect(PubUtil.MAIL_HOST, PubUtil.TEA_EMAIL, PubUtil.TEA_PASSWORD);
			for(Person stu:pl){
System.out.println(stu.getPname());
				Message m = this.createMessage(session,stu);
				ts.sendMessage(m, m.getAllRecipients());
			}
			ts.close();
			//设定时器
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					for(Person stu:pl){
						ssi.updateTeaScore("score.tea=score.tea-0.5","score.pid = '"+stu.getPid()+"'");
						System.out.println(stu.getPname()+"分数已更改");
					}
				}
			}, 1000*3600*24*14);
			Map<String, Timer>timerMap = (Map<String, Timer>) servletContext.getAttribute("timerMap");
			if(timerMap!=null){
				timerMap.put("mailTimer"+timerMap.size(), timer);
			}
			servletContext.setAttribute("timerMap", timerMap);
			
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	private Message createMessage(Session session, Person stu) {
		MimeMessage mailMessage = new MimeMessage(session);
		try {
	        mailMessage.setFrom(new InternetAddress(PubUtil.TEA_EMAIL));
	        // Message.RecipientType.TO属性表示接收者的类型为TO
	        mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(stu.getEmail()));
	        mailMessage.setSubject("评分提醒", "UTF-8");
			mailMessage.setSentDate(new Date());
			String content =stu.getPname()+"同学 请在一个星期内完成评分工作 不然会进行扣分操作";
			mailMessage.setContent(content, "text/html; charset=utf-8");
			mailMessage.saveChanges();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return mailMessage;
	}

	private ArrayList<Person> getPerson(int selectNum) {
		PersonDao pd = new PersonDaoMysqlImpl();
		ArrayList<Person>pl = (ArrayList<Person>) pd.findPersonAllByString("person_info.group != '"+this.chaptid+"' and pstate ", "s");
		ArrayList<Person>pl2 = new ArrayList<Person>();
		ArrayList<Person>pl3 = new ArrayList<Person>();
		Set<Person> phs = new HashSet<Person>();
		for(Person p:pl){
			phs.add(p);
		}
		for(Person p:phs){
			pl2.add(p);
		}
		for(int i=0;i<selectNum;i++){
			pl3.add(pl2.get(i));
		}
		return pl3;
	}
	
	/*public static void main(String[] args) {
		new SendMail(new Person(), "rt000100").start();
	}*/

}
