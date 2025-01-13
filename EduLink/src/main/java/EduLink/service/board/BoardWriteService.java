package EduLink.service.board;

import java.io.File;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.BoardCommand;
import EduLink.domain.BoardDTO;
import EduLink.mapper.BoardMapper;
@Service
public class BoardWriteService {
    @Autowired
    BoardMapper boardMapper;

    public void execute(BoardCommand boardCommand, MultipartFile boardVideo){

        // DTO 생성
        BoardDTO dto = new BoardDTO();
        dto.setBoardNum(boardCommand.getBoardNum());
        dto.setBoardContents(boardCommand.getBoardContents());
        dto.setBoardDate(boardCommand.getBoardDate());
        dto.setBoardName(boardCommand.getBoardName());
        dto.setClassNum(boardCommand.getClassNum());
        dto.setWriteNum(boardCommand.getWriteNum());
        dto.setBoardCategory(boardCommand.getBoardCategory());
        dto.setTeacherNum(boardCommand.getTeacherNum() == null ? "" : boardCommand.getTeacherNum());
        dto.setStudentNum(boardCommand.getStudentNum() == null ? "" : boardCommand.getStudentNum());

        URL resource = getClass().getClassLoader().getResource("static/upload/video/");
        String classDir = resource.getFile() + boardCommand.getClassNum();
        

        // 파일 저장
        MultipartFile mf = boardCommand.getBoardVideo();
		String originalFile = mf.getOriginalFilename();
		String extension = originalFile.substring(originalFile.lastIndexOf("."));
		String storeName = UUID.randomUUID().toString().replace("-", "");
		String storeFileName = storeName + extension;
		createDirectory(classDir);
		File file = new File(classDir + "/" + storeFileName);
		try {
			mf.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
        // DTO에 파일 정보 저장
        dto.setBoardVideo(boardVideo.getOriginalFilename());
        dto.setBoardStoreVideo(storeFileName);

        // 데이터베이스에 삽입
        boardMapper.boardInsert(dto);
    }

    public void createDirectory(String classDir) {
        File directory = new File(classDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                throw new RuntimeException("디렉토리 생성에 실패했습니다: " + classDir);
            }
        }
    }


}
