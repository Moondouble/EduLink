package EduLink.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import EduLink.domain.ReviewDTO;

@Mapper
public interface ReviewMapper
{
	public List<ReviewDTO> reviewSelectAll(String classNum);
	public void reviewInsert(ReviewDTO dto);
}
