package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {
	public String selectNum();
}
