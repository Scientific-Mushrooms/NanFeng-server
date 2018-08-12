package cloud.module.classroom.classroomMember;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.module.student.Student;
import cloud.module.student.StudentService;
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

    @Resource
    private StudentService studentService;


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

    @PostMapping("/classroomMember/classroomIdToAllStudents")
    public Result classroomIdToAllClassroomMembers(HttpServletRequest request) {

        String classroomId = request.getParameter("classroomId");

        Iterable<Student> students = classroomMemberService.classroomIdToAllStudents(classroomId);

        return new Result("success", "classroom id to all students", students);

    }

    @PostMapping("/classroomMember/deleteByClassroomMemberId")
    public Result deleteByClassroomMemberId(HttpServletRequest request) {

        String classroomMemberId = request.getParameter("classroomMemberId");

        classroomMemberService.deleteByClassroomMemberId(classroomMemberId);

        return new Result("success", "delete by classroom member id");

    }

    @PostMapping("/classroomMember/create")
    public Result create(@ModelAttribute ClassroomMember classroomMember) {

        String classroomId = classroomMember.getClassroomId();
        String studentId = classroomMember.getStudentId();
        Student student = studentService.studentIdToStudent(studentId);
        ClassroomMember oldMember = classroomMemberService.studentIdToClassroomMember(studentId);

        if (classroomId == null || classroomId.equals("")) {
            return new Result("fail", "classroom id cannot be empty");
        }

        if (studentId == null || studentId.equals("")) {
            return new Result("fail", "student id cannot be empty");
        }

        if (student == null) {
            return new Result("fail", "student doesn't exist");
        }

        if (oldMember != null) {
            return new Result("fail", "classroom member already exist");
        }

        classroomMemberRepository.save(classroomMember);

        return new Result("success", "create member");
    }

}
