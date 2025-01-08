package EduLink.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("packageDTO")
public class PackageDTO {
	String packageNum;
	String studentNum;
	String packageName;
	Integer packagePrice;
}
