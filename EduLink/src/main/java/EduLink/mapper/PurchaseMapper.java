package EduLink.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import EduLink.domain.PaymentDTO;
import EduLink.domain.PurchaseDTO;
import EduLink.domain.PurchaseListDTO;

@Mapper
public interface PurchaseMapper {
	public String selectNum();
	public Integer purchaseInsert(PurchaseDTO dto);
	public int purchaseListInsert(PurchaseListDTO dto);
	public PurchaseDTO purchaseSelect(String purchaseNum);
	public int purchaseClassCount(String purchaseNum);
	public String firstClass(String purchaseNum);
	public int paymentInsert(PaymentDTO dto);
	public int purchaseStatusUpdate(@Param("status") String status,
			@Param("purchaseNum") String purchaseNum);
	
}
