package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import EduLink.service.board.BoardDetailService;

@Controller
@RequestMapping("/video")
public class VideoController {
	@Autowired
	BoardDetailService boardDetailService;
    @GetMapping("/{classNum}/{boardNum}")
    public String viewVideo(@PathVariable String classNum, @PathVariable String boardNum, @RequestParam("boardStoreVideo") String boardStoreVideo, Model model){
    	model.addAttribute("classNum", classNum);
    	model.addAttribute("boardStoreVideo", boardStoreVideo);
    	return "thymeleaf/video/videoView";
    }
}
