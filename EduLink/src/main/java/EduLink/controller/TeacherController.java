package EduLink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import EduLink.command.TeacherCommand;
import EduLink.service.AutoNumService;
import EduLink.service.IdcheckService;
import EduLink.service.teacher.TeacherDeleteService;
import EduLink.service.teacher.TeacherDetailService;
import EduLink.service.teacher.TeacherListService;
import EduLink.service.teacher.TeacherUpdateService;
import EduLink.service.teacher.TeacherWriteService;

@Controller
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    TeacherWriteService teacherWriteService;

    @Autowired
    TeacherListService teacherListService;

    @Autowired
    TeacherDetailService teacherDetailService;

    @Autowired
    AutoNumService autoNumService;

    @Autowired
    TeacherUpdateService teacherUpdateService;

    @Autowired
    TeacherDeleteService teacherDeleteService;

    @Autowired
    IdcheckService idcheckService;

    @GetMapping("teacherWrite")
    public String teacherWrite(Model model) {
        String autoNum = autoNumService.execute("teacher_", "teacher_num", 9, "teacher");
        TeacherCommand teacherCommand = new TeacherCommand();
        teacherCommand.setTeacherNum(autoNum);
        model.addAttribute("teacherCommand", teacherCommand);
        return "thymeleaf/teacher/teacherForm";
    }

    @PostMapping("teacherRegist")
    public String teacherRegist(
            @Validated TeacherCommand teacherCommand,
            BindingResult result,
            @RequestParam("teacherImage") MultipartFile teacherImageFile,
            Model model) {
        if (result.hasErrors()) {
            return "thymeleaf/teacher/teacherForm";
        }

        if (!teacherCommand.isTeacherPwEqualTeacherPwCon()) {
            result.rejectValue("teacherPwCon", "teacherCommand.teacherPwCon", "비밀번호가 일치하지 않습니다.");
            return "thymeleaf/teacher/teacherForm";
        }
        teacherWriteService.execute(teacherCommand, teacherImageFile);
        return "redirect:/teacher/teacherList";
    }

    @GetMapping("teacherList")
    public String teacherList(Model model) {
        teacherListService.execute(model);
        return "thymeleaf/teacher/teacherList";
    }

    @GetMapping("teacherDetail")
    public String teacherDetail(@RequestParam("teacherNum") String teacherNum, Model model) {
        teacherDetailService.execute(teacherNum, model);
        return "thymeleaf/teacher/teacherInfo";
    }

    @GetMapping("teacherUpdate")
    public String teacherUpdate(@RequestParam("teacherNum") String teacherNum, Model model) {
        teacherDetailService.execute(teacherNum, model);
        return "thymeleaf/teacher/teacherModify";
    }

    @PostMapping("teacherModify")
    public String teacherModify(TeacherCommand teacherCommand) {
        teacherUpdateService.execute(teacherCommand);
        return "redirect:teacherDetail?teacherNum=" + teacherCommand.getTeacherNum();
    }

    @GetMapping("teacherDelete")
    public String teacherDelete(@RequestParam("teacherNum") String teacherNum) {
        teacherDeleteService.execute(teacherNum);
        return "redirect:/";
    }
}
