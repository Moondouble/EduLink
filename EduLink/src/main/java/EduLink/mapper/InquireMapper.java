package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.InquireDTO;

@Mapper
public interface InquireMapper {
	public void inquireInsert(InquireDTO dto);
	public List<InquireDTO> inquireSelectAll();
	public InquireDTO inquireSelectOne(String inquireNum);
	public void inquireUpdate(InquireDTO dto);
	public void inquireDelete(String inquireNum);
}
