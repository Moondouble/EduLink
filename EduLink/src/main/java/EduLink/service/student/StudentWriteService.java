package EduLink.service.student;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.StudentCommand;
import EduLink.domain.StudentDTO;
import EduLink.mapper.StudentMapper;

@Service
public class StudentWriteService {
	@Autowired
	StudentMapper studentMapper;

	public void execute(StudentCommand studentCommand) {
		StudentDTO dto = new StudentDTO();
		dto.setStudentNum(studentCommand.getStudentNum());
		dto.setStudentName(studentCommand.getStudentName());
		dto.setStudentId(studentCommand.getStudentId());
		dto.setStudentPw(studentCommand.getStudentPw());
		dto.setStudentEmail(studentCommand.getStudentEmail());
		dto.setStudentEmailConf(studentCommand.getStudentEmailConf());
		dto.setStudentRegist(studentCommand.getStudentRegist());
		dto.setStudentBirth(studentCommand.getStudentBirth());
		dto.setStudentJumin(studentCommand.getStudentJumin());
		dto.setStudentAddr(studentCommand.getStudentAddr());
		dto.setStudentAddrDetail(studentCommand.getStudentAddrDetail());
		dto.setStudentPost(studentCommand.getStudentPost());
		dto.setStudentPhone1(studentCommand.getStudentPhone1());
		dto.setStudentPhone2(studentCommand.getStudentPhone2());
        
/// 경로
URL resource = getClass().getClassLoader().getResource("static/upload");
System.out.println("resource : " + resource);
String fileDir = resource.getFile();

// 메인이미지 처리
MultipartFile mf = studentCommand.getStudentImage(); // teacherCommand에서 teacherImage 가져오기
String originalFile = mf.getOriginalFilename();

// 확장자 추출
String extension = originalFile.substring(originalFile.lastIndexOf("."));

// 저장 이름 생성
String storeName = UUID.randomUUID().toString().replace("-", "");
String storeFileName = storeName + extension;

// 파일 생성 및 저장
File file = new File(fileDir + "/" + storeFileName);
try {
    mf.transferTo(file);
} catch (Exception e) {
    e.printStackTrace();
}

		// DTO에 저장
		dto.setStudentImage(originalFile); // 원본 파일 이름 저장
		dto.setStudentStoreImage(storeFileName); // 저장된 파일 이름 저장

		
		
		studentMapper.studentInsert(dto);
	}
	
}
