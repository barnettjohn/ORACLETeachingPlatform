package com.plat.orcl.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.plat.orcl.dao.ChapterDao;
import com.plat.orcl.dao.CourseDao;
import com.plat.orcl.dao.PersonDao;
import com.plat.orcl.dao.impl.ChapterDaoMysImpl;
import com.plat.orcl.dao.impl.CourseDaoMysqlImpl;
import com.plat.orcl.dao.impl.PersonDaoMysqlImpl;
import com.plat.orcl.domain.Chapter;
import com.plat.orcl.domain.Course;
import com.plat.orcl.domain.Person;

public class CourseServiceImpl {
	private CourseDao cd= new CourseDaoMysqlImpl();
	private PersonDao pd = new PersonDaoMysqlImpl();
	private ChapterDao chd = new ChapterDaoMysImpl();
	
	/***
	 * 
	 * @Description: 根据教师id值找到该教师的课程列表
	 * @param pid
	 * @return 
	 * @return ArrayList<Course> 
	 * @date 2015年3月13日
	 */
	public ArrayList<Course> getCourseList(String pState,String pid){
		ArrayList<Course> lc = new ArrayList<Course>();
		if(pState.equals("t"))
		return (ArrayList<Course>) cd.findCourseAllByString("pid", pid);
		else return lc;
	}
	

	public ArrayList<Chapter> getChapterList(String pState){
		//ArrayList<Chapter> lc = new ArrayList<Chapter>();
		//if(pState.equals("t"))
		return (ArrayList<Chapter>)chd.findAll();
		//else return lc;
	} 
	
	/***
	 * 
	 * @Description: 更课程表中数据并给章节表插入id
	 * @param c 
	 * @return void 
	 * @date 2015年3月13日
	 */
	public void UpdateCourse(Course c){
		String cid = c.getCid();
		cd.updateCourse(c);
		List<Chapter> lch = chd.findChapterByString("cid", c.getCid());
		int chnum = Integer.parseInt(c.getChnum());
		if(chnum>lch.size()){
			for(int d=0;d<chnum-lch.size();d++){
				Chapter ch = new Chapter();
				ch.setCid(cid);
				chd.addChapter(ch);
			}
		}else if(chnum<lch.size()){
			//现在这种解决方案是先全删了再插入空白行
			chd.deleteChapterByString("cid", cid);
			for(int d=0;d<chnum;d++){
				Chapter ch = new Chapter();
				ch.setCid(cid);
				chd.addChapter(ch);
			}
		}
		
	}
	
	/***
	 * 
	 * @Description: 更新或添加章节表中的数据
	 * @param chapterList 
	 * @return void 
	 * @date 2015年3月16日
	 */
	public void addOrUpdateChapter(ArrayList<Chapter> chapterList){
		for (Chapter chapter : chapterList) {
			if (chd.findChapterByString("chaptid", chapter.getChaptid()).size()==0) {
				chd.addChapter(chapter);
			}else{
				chd.updateChapter(chapter);
			}
		}
	}
	
	/***
	 * 
	 * @Description: 根据章节列表对学生进行分组
	 * @param chapterlist
	 * @return 更新的学生数目
	 * @return int 
	 * @date 2015年3月20日
	 */

	public int dividedIntoTeams(ArrayList<Chapter> chapterlist) {
		ArrayList<Person>pl = (ArrayList<Person>) pd.findPersonByString("pstate", "s");
		int total = pl.size();
		HashSet<Person> phs = new HashSet<Person>();
		
		int sum=0 ;
		//这里随机取person很巧妙 直接用hashset的特性取随机的hash地址即为随机的
		for(Person p : pl){
			phs.add(p);
		}
		for(Chapter chapter : chapterlist){
			int num = Integer.parseInt(chapter.getDifficulty());
			sum+=num;
		}
		Iterator<Person> iterator = phs.iterator();
		if(sum == total){
			for(Chapter chapter : chapterlist){
				int num = Integer.parseInt(chapter.getDifficulty());
				for(int i=0;i<num;i++){
					if(iterator.hasNext()){
						Person p = iterator.next();
						p.setGroup(chapter.getChaptid());
						pd.updatePerson(p);
					}
				}
			}
			return 0;
		}else {
			return total-sum;
		}
		
	}
	
	
}
