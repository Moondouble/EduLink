package EduLink.service.packagee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import EduLink.domain.PackageDTO;
import EduLink.mapper.PackageMapper;

@Service
public class PackageListService {
	@Autowired
	PackageMapper packageMapper;
	
	public void execute(Model model) {
		List<PackageDTO> list = packageMapper.packageSelectAll();
		model.addAttribute("list", list);
	}
}
