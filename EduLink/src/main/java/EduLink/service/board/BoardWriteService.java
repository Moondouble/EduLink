package EduLink.service.board;

import java.io.File;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.BoardCommand;
import EduLink.domain.BoardDTO;
import EduLink.mapper.BoardMapper;
import jakarta.servlet.ServletContext;

@Service
public class BoardWriteService {
    @Autowired
    BoardMapper boardMapper;

    @Autowired
    private ServletContext servletContext;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public void execute(BoardCommand boardCommand, MultipartFile boardVideo) {
        BoardDTO dto = new BoardDTO();
        dto.setBoardNum(boardCommand.getBoardNum());
        dto.setBoardContents(boardCommand.getBoardContents());
        dto.setBoardDate(boardCommand.getBoardDate());
        dto.setBoardName(boardCommand.getBoardName());
        dto.setClassNum(boardCommand.getClassNum());
        dto.setWriteNum(boardCommand.getWriteNum());
        dto.setBoardCategory(boardCommand.getBoardCategory());

        if (boardCommand.getTeacherNum() == null) {
            dto.setTeacherNum("");
        } else {
            dto.setTeacherNum(boardCommand.getTeacherNum());
        }
        if (boardCommand.getStudentNum() == null) {
            dto.setStudentNum("");
        } else {
            dto.setStudentNum(boardCommand.getStudentNum());
        }

        // 파일 경로 설정 (config에서 설정된 경로 사용)
        String fileDir = uploadDir + "/" + boardCommand.getClassNum();
        System.out.println("File directory: " + fileDir);

        // 경로 없는 경우 경로 생성
        File directory = new File(fileDir);
        if (!directory.exists()) {
            boolean isDirCreated = directory.mkdirs();
            if (isDirCreated) {
                System.out.println("Directory created: " + fileDir);
            } else {
                System.out.println("Failed to create directory: " + fileDir);
                throw new RuntimeException("Failed to create directory for file storage.");
            }
        }

        // 비디오 파일 처리
        MultipartFile mf = boardCommand.getBoardVideo();
        String originalFile = mf.getOriginalFilename();

        // 확장자 추출
        String extension = originalFile.substring(originalFile.lastIndexOf("."));
        String storeName = UUID.randomUUID().toString().replace("-", "");
        String storeFileName = storeName + extension;

        // 파일 생성 및 저장
        File file = new File(fileDir + "/" + storeFileName);
        try {
            mf.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed", e);
        }

        // DTO에 저장
        dto.setBoardVideo(originalFile); // 원본 파일 이름 저장
        dto.setBoardStoreVideo(storeFileName); // 저장된 파일 이름 저장

        // DB에 게시판 데이터 삽입
        boardMapper.boardInsert(dto);
    }
}
