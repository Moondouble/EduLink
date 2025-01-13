package EduLink.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.EmployeeDTO;
import EduLink.domain.StartEndPageDTO;
import EduLink.mapper.EmployeeMapper;
import EduLink.service.StartEndPageService;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	
	public void execute(String searchWord, int page, Model model) {
		if (searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		// StartEndPageDTO를 생성하여 페이징 처리에 필요한 정보 설정
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		// 검색어 및 페이지 조건에 맞는 직원 리스트 가져오기
		List<EmployeeDTO> list = employeeMapper.empSelectAll(sepDTO);
		
		// 검색어에 해당하는 직원 수 카운트
		int count = employeeMapper.empCount(this.searchWord);
		// 페이징 처리를 위한 모델 설정
		startEndPageService.execute(page, count, model, list, this.searchWord);
		
		// 직원 리스트를 모델에 추가
		model.addAttribute("list", list);
	}
}