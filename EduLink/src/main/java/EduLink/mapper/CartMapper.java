package EduLink.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import EduLink.domain.CartDTO;
import EduLink.domain.CartGoodsDTO;

@Mapper
public interface CartMapper {
	public int cartInsert(CartDTO dto); 	
	public List<CartGoodsDTO> cartList(@Param("studentNum") String studentNum, 
			   					       @Param("classNums") String [] nums);
	public Integer sumPrice(String studentNum);
	public int classNumsDelete(Map<String, Object> condition);
	public int classNumDelete(@Param("classNum") String classNum, @Param("studentNum") String studentNum);
}
