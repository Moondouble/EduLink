package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.InquireDTO;
import EduLink.domain.PackageDTO;

@Mapper
public interface PackageMapper {
	public void packageInsert(PackageDTO dto);
	public List<PackageDTO> packageSelectAll();
}
