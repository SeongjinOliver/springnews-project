package my.spring.springnews;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.NewsMybatisDAO;
import vo.NewsVO;

@Controller
public class NewsController {
	@Autowired
	//NewsDAO dao;
	NewsMybatisDAO dao;

	// 뉴스의 전체 리스트 출력
	// 제목 선택 시 : 해당 뉴스 id 로 해당 뉴스 내용 출력
	// 삭제 버튼 클릭시 : 뉴스 삭제
	@RequestMapping(value = "/newsmain", method = RequestMethod.GET)
	protected ModelAndView doGets(NewsVO vo, String action, String keyword,
			String searchType) {
		ModelAndView mav = new ModelAndView();
		if (action != null) {
			if (action.equals("search")) {
				List<NewsVO> list = null;
				if (searchType != null) {
					if (searchType.equals("title")) {
//						System.out.println(keyword);
//						System.out.println(searchType);
						list = dao.search(keyword, searchType);
						if (keyword != null) {
							if (list != null && list.size() == 0) {
								mav.addObject("list", dao.listAll());
							} else {
								mav.addObject("list", dao.search(keyword, searchType));
							}
						}
					} else if (searchType.equals("writer")) {
						list = dao.listWriter(keyword);
						if (keyword != null) {
							if (list != null && list.size() == 0) {
								mav.addObject("list", dao.listAll());
							} else {
								mav.addObject("list", dao.listWriter(keyword));
							}
						}
					}
				}

			} else {
				if (action.equals("delete")) {
					if (vo.getNewsId() != 0) {
						dao.delete(vo.getNewsId());
					}
				} else if (action.equals("read")) {
					if (vo.getNewsId() != 0)
						mav.addObject("one", dao.listOne(vo.getNewsId()));
				}
				mav.addObject("list", dao.listAll());
			}
		} else
			mav.addObject("list", dao.listAll());
		
		mav.setViewName("news");
		// request.getRequestDispatcher("/jspexam/news.jsp").forward(request, response);
		return mav;
	}

	// POST 방식 요청 시 기능
	// 뉴스 작성
	// 뉴스 수정
	@RequestMapping(value = "/newsmain", method = RequestMethod.POST)
	protected ModelAndView doPost(String action, NewsVO vo) {
		ModelAndView mav = new ModelAndView();
		if (mav.addObject("action") != null) {
			if (action.equals("insert")) {
				dao.insert(vo);
			} else if (action.equals("update")) {
				dao.update(vo);
			}
		}

		mav.addObject("list", dao.listAll());
		mav.setViewName("news");
		return mav;
	}

}
