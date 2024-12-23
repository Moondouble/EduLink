package EduLink.service.classroom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.ClassroomDTO;
import EduLink.mapper.ClassroomMapper;

@Service
public class ClassroomListService
{
	@Autowired
	ClassroomMapper classroomMapper;
	
	public void execute(Model model){
		List<ClassroomDTO> list = classroomMapper.classSelectAll();
		model.addAttribute("list", list);
	}
}
