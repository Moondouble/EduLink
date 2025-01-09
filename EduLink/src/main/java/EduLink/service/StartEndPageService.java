package EduLink.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.StartEndPageDTO;

@Service
public class StartEndPageService {
	int limit;

	public StartEndPageDTO execute(int page, String searchWord) {
		// 회원수가 십만명이라면 한페이지에서 볼 수 없다. 이런 경우 한 페이지에 10명씩 보여주는 페이징을 하려고 한다.
		limit = 10; // 한페이지에 몇 개를 보여줄 것인가?
		// 만약 1페이지라면 1번부터 10번까지, 2페이지면 11번부터 20번까지 가져와야할 것이다.
		// 시작 번호와 끝번호를 알기 위해서는 페이지 번호가 필요하다.
		int startRow = ((page - 1) * limit) + 1; // page를 이용해서 시작번호를 가지고 온다.
		int endRow = startRow + limit - 1; // 시작번호를 이용해서 마지막 번호를 가지고 온다.
		// startRow와 endRow 그리고 searWord를 마이바티스에 넘기기 위해 DTO를 만든다.
		StartEndPageDTO sepDTO = new StartEndPageDTO();
		sepDTO.setEndRow(endRow);
		sepDTO.setSearchWord(searchWord);
		sepDTO.setStartRow(startRow);
		return sepDTO;
	}

	public void execute(int page, int count, Model model, List list, String searchWord) {
		// 페이지에 보여줄 페이지 번호의 갯수를 정해준다.
		int limitPage = 10;
		// 시작 페이지 번호와 마지막 페이지 번호를 가지고 온다.
		int startPage = (int) ((double) page / limitPage + 0.95 - 1) * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		// 전체 페이지 갯수를 구한다.
		int maxPage = (int) ((double) count / limit + 0.95);
		// endPage가 maxPage 페이지 보다 크지 않게 만들어 준다.
		if (maxPage < endPage)
			endPage = maxPage;
		// 리스트 페이지로 넘겨준다.
		model.addAttribute("list", list);
		// 검색할 때 사용한 단어를 html에 출력되게 한다.
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("maxPage", maxPage);
	}
}