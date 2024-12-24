package EduLink.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("classroomDTO")
public class ClassroomDTO
{
String classNum;
String teacherNum;
String className;
String classContents;
@DateTimeFormat(pattern = "yyyy-MM-dd")
Date classStartDate;
@DateTimeFormat(pattern = "yyyy-MM-dd")
Date classEndDate;
Integer classWeek;
}
