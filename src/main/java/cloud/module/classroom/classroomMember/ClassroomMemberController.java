package cloud.module.classroom.classroomMember;


import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ClassroomMemberController extends BaseController {


    @Autowired
    private ClassroomMemberRepository classroomMemberRepository;

    @Resource
    private ClassroomMemberService classroomMemberService;


    @PostMapping("/classroomMember/all")
    public Result all() {

        Iterable<ClassroomMember> classrooms = classroomMemberRepository.findAll();

        return new Result("success", "all classroom members", classrooms);

    }

    @PostMapping("/classroomMember/deleteAll")
    public Result deleteAll() {

        classroomMemberRepository.deleteAll();

        return new Result("success", "delete all classrooms");

    }

    @PostMapping("/classroomMember/classroomMemberIdToClassroomMember")
    public Result classroomMemberIdToClassroomMember(HttpServletRequest request) {

        String classroomMemberId = request.getParameter("classroomMemberId");

        ClassroomMember classroomMember = classroomMemberService.classroomMemberIdToClassroomMember(classroomMemberId);

        return new Result("success", "classroom member id to classroom member", classroomMember);

    }

    @PostMapping("/classroomMember/deleteByClassroomMemberId")
    public Result deleteByClassroomMemberId(HttpServletRequest request) {

        String classroomMemberId = request.getParameter("classroomMemberId");

        classroomMemberService.deleteByClassroomMemberId(classroomMemberId);

        return new Result("success", "delete by classroom member id");

    }

    @PostMapping("/classroomMember/create")
    public Result create(@ModelAttribute ClassroomMember classroomMember) {

        return new Result("success", "create member");
    }

}
