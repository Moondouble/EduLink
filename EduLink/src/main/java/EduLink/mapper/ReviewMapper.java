package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.ReviewDTO;

@Mapper
public interface ReviewMapper
{
	public List<ReviewDTO> reviewSelectAll(String classNum);
	public List<ReviewDTO> reviewSelectAllWithoutClass();
	public void reviewInsert(ReviewDTO dto);
	public ReviewDTO reviewSelectOne(String classNum);
	public void reviewUpdate(ReviewDTO dto);
	public void reviewDelete(String reviewNum);
}
