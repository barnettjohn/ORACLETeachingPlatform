package com.plat.orcl.service.impl;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;

import com.plat.orcl.dao.FormDao;
import com.plat.orcl.dao.FormQuestionDao;
import com.plat.orcl.dao.ScoreDao;
import com.plat.orcl.dao.StuScoreDao;
import com.plat.orcl.dao.TestScoreDao;
import com.plat.orcl.dao.impl.FormDaoMysqlImpl;
import com.plat.orcl.dao.impl.FormQuestionDaoMysqlImpl;
import com.plat.orcl.dao.impl.ScoreDaoMysqlImpl;
import com.plat.orcl.dao.impl.StuScoreDaoMysqlImpl;
import com.plat.orcl.dao.impl.TestScoreDaoMysqlImpl;
import com.plat.orcl.domain.Form;
import com.plat.orcl.domain.FormQuestion;
import com.plat.orcl.domain.Person;
import com.plat.orcl.domain.Score;
import com.plat.orcl.domain.StuScore;
import com.plat.orcl.domain.Test;
import com.plat.orcl.domain.TestScore;
import com.plat.orcl.utils.WebUtil;
import com.plat.orcl.web.formbean.AllScoreForm;

public class ScoreServiceImpl {
	private ScoreDao sd= new ScoreDaoMysqlImpl();
	private StuScoreDao ssd = new StuScoreDaoMysqlImpl();
	private TestScoreDao tsd = new TestScoreDaoMysqlImpl();
	private FormDao fd = new FormDaoMysqlImpl();
	private FormQuestionDao fqd = new FormQuestionDaoMysqlImpl();
	
	public ArrayList<Score> getTeaScoreList(){
		ArrayList<Score> ls = (ArrayList<Score>) sd.findAll();
		return ls;
		
	}
	public void updateFinallScore(Score s) {
		sd.updateScoreByString(s, "finalscore", s.getFinalscore());
	}
	public void updateTeaScore(Score s) {
		sd.updateScoreByString(s, "tea", s.getTea());
	}
	public void updateTeaScore(String set,String where) {
		sd.updateScoreBySql(set, where);
	}
	public ArrayList<AllScoreForm> getAllScoreList(){
		ArrayList<Score> ls = (ArrayList<Score>) sd.findAll();
		ArrayList<AllScoreForm> asl = new ArrayList<AllScoreForm>();
		
		WebUtil.copyBeanList(ls,asl,AllScoreForm.class);
		for(AllScoreForm s: asl){
			ArrayList<StuScore> lss = (ArrayList<StuScore>) ssd.findStuScoreByString("pid", s.getPid());
			ArrayList<TestScore> lts = (ArrayList<TestScore>) tsd.findTestScoreAllByString("pid", s.getPid());
			if(lss!=null&&lts!=null){
				int stunum = lss.size();
				int testnum = lts.size();
				double stuscoreall = 0 ;
				double testscoreall = 0;
				for(StuScore ss:lss){
					//这里也应有课程的判断
					stuscoreall+=ss.getScore();
				}
				for(TestScore ts:lts){
					if(ts.getT().getCid()!=null){
						if(ts.getT().getCid().equals(s.getCid())){
							testscoreall+=ts.getScore();
						}
					}
				}
				double stu = stuscoreall/stunum;
				double test = testscoreall/testnum;
				
				s.setStu(""+stu);
				s.setTest(""+test);
				s.setTotal();
			}
		}
		return asl;
		
	}
	public void addForm(Form form) {
		fd.addForm(form);
	}
	public void addFormQuestion(FormQuestion question) {
		fqd.addFormQuestion(question);
		
	}
	//插入学生评分记录
	public void addStuScoreRecord(String formid,ArrayList<Person>pl,ArrayList<Person>pupl){
		StuScore ss= new StuScore();
		for(Person pup:pupl){
			ss.setPidup(pup.getPid());
			ss.setFormid(formid);
			for(Person p:pl){
				ss.setPid(p.getPid());
				ssd.addStuScore(ss);
			}
		}
	}
	public ArrayList<StuScore> getStuScoreList(String xid, String yid) {
		return (ArrayList<StuScore>) ssd.findStuScoreAllByString(xid, yid);
	}
	public Form getOneForm(String formid) {
		return fd.findFormAllByString("formid",formid).get(0);
	}
	public void updateStuScore(StuScore ss) {
		ssd.updateStuScore(ss);
	}
	
}
