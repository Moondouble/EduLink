package EduLink.service.packagee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import EduLink.command.PackageCommand;
import EduLink.domain.PackageDTO;
import EduLink.mapper.PackageMapper;

@Service
public class PackageWriteService {
	@Autowired
	PackageMapper packageMapper;
	public void execute(PackageCommand packageCommand) {
		PackageDTO dto = new PackageDTO();
		dto.setPackageNum(packageCommand.getPackageNum());
		dto.setPackageName(packageCommand.getPackageName());
		dto.setPackagePrice(packageCommand.getPackagePrice());
		
		packageMapper.packageInsert(dto);
	}
}
