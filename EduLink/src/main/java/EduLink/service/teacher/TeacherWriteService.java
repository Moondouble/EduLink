package EduLink.service.teacher;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.TeacherCommand;
import EduLink.domain.TeacherDTO;
import EduLink.mapper.TeacherMapper;

@Service
public class TeacherWriteService {

    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    TeacherCommand teacherCommand;

    // 파일 저장 경로 (원하는 경로로 변경 가능)
    private final String uploadDir = "C:/uploads/teachers/";

    public void execute(TeacherCommand teacherCommand, MultipartFile teacherImageFile) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherNum(teacherCommand.getTeacherNum());
        dto.setTeacherId(teacherCommand.getTeacherId());
        dto.setTeacherPw(teacherCommand.getTeacherPw());
        dto.setTeacherName(teacherCommand.getTeacherName());
        dto.setTeacherAddr(teacherCommand.getTeacherAddr());
        dto.setTeacherAddrDetail(teacherCommand.getTeacherAddrDetail());
        dto.setTeacherPost(teacherCommand.getTeacherPost());
        dto.setTeacherPhone1(teacherCommand.getTeacherPhone1());
        dto.setTeacherPhone2(teacherCommand.getTeacherPhone2());
        dto.setTeacherJumin(teacherCommand.getTeacherJumin());
        dto.setTeacherEmail(teacherCommand.getTeacherEmail());
        dto.setTeacherEmailConf(teacherCommand.getTeacherEmailConf());
        dto.setTeacherRegist(teacherCommand.getTeacherRegist());
        
        
/// 경로
URL resource = getClass().getClassLoader().getResource("static/upload");
System.out.println("resource : " + resource);
String fileDir = resource.getFile();
//경로 없는 경우 경로 추가
File directory = new File(fileDir);
if (!directory.exists()) {
    boolean isDirCreated = directory.mkdirs(); // 폴더 생성
    if (isDirCreated) {
        System.out.println("Directory created: " + fileDir);
    } else {
        System.out.println("Failed to create directory: " + fileDir);
        throw new RuntimeException("Failed to create directory for file storage.");
    }
}
// 메인이미지 처리
MultipartFile mf = teacherCommand.getTeacherImage(); // teacherCommand에서 teacherImage 가져오기
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
		dto.setTeacherImage(originalFile); // 원본 파일 이름 저장
		dto.setTeacherStoreImage(storeFileName); // 저장된 파일 이름 저장


        // 데이터베이스에 삽입
        teacherMapper.teacherInsert(dto);
    }

}
