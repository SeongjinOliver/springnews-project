package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.NewsVO;

@Repository
public class NewsMybatisDAO {
	@Autowired
	SqlSession session = null;

	// @RequestMapping(value = "/insert")
	public boolean insert(NewsVO vo) {
		System.out.println("Mybatis insert");
		boolean result = false;
		String statement = "resource.NewsMapper.insertNews";
		if (session.insert(statement, vo) == 1)
			result = true;
		return result;
	}

	// @RequestMapping(value = "/update")
	public boolean update(NewsVO vo) {
		System.out.println("Mybatis Update");
		boolean result = false;
		String statement = "resource.NewsMapper.updateNews";
		System.out.println(vo.getNewsId());	
		if (session.update(statement, vo) == 1)
			result = true;
		return result;
	}

	// @RequestMapping(value = "/delete")
	public boolean delete(int id) {
		System.out.println("Mybatis Delete");
		boolean result = false;
		String statement = "resource.NewsMapper.deleteNews";
		if (session.delete(statement, id) == 1)
			result = true;
		return result;
	}

	// @RequestMapping(value = "/newsmain")
	public List<NewsVO> listAll() {
		System.out.println("Mybatis listAll");
		List<NewsVO> list = null;
		String statement = "resource.NewsMapper.selectListAllNews";
		list = session.selectList(statement);
		return list;
	}

	// @RequestMapping(value = "/listOne")
	public NewsVO listOne(int id) {
//		클라이언트로부터 전달된 id 값에 해당하는 글 하나만을 추출하여 NewsVO 객체에 담아 리턴한다.
//		만일 추출되지 않으면 null 을 리턴하게 구현해서 컨트롤러에서 이 부분을 처리하도록 한다
		System.out.println("Mybatis listOne");
		NewsVO vo = null;
		boolean result = false;
		String statement = "resource.NewsMapper.selectNewsOne";
		vo = session.selectOne(statement, id);
		statement = "resource.NewsMapper.updateCount";
		if (session.update(statement, id) == 1)
			result = true;
		
		return vo;
		//if(result) return vo;
		//else return null;
	}

	// @RequestMapping(value = "/writer")
	public List<NewsVO> listWriter(String writer) {
		System.out.println("listWriter");
		List<NewsVO> list = null;
		String statement = "resource.NewsMapper.searchNewsWriter";
		list = session.selectList(statement, writer);
		return list;
	}

	// @RequestMapping(value = "/search")
	public List<NewsVO> search(String key, String searchType) {
		System.out.println("search");
		System.out.println("key: " + key);
		System.out.println("searchType: " + searchType);
		List<NewsVO> list = null;
		//String statement = "resource.NewsMapper.selectNewsOne";
		String statement = "resource.NewsMapper.searchTypeTitle_Writer";
		Map<String, String> hmap = new HashMap<String, String>();
		hmap.put("key", key);
		hmap.put("searchType", searchType);
		list = session.selectList(statement, hmap);
		System.out.println("size: "+list.size());
		return list;
	}
}