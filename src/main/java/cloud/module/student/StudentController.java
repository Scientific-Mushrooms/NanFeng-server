package cloud.module.student;


import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class StudentController extends BaseController {


    @Autowired
    private StudentRepository studentRepository;

    @Resource
    private StudentService studentService;


    @PostMapping("/student/all")
    public Result all() {

        Iterable<Student> students = studentRepository.findAll();

        return new Result("success", "all students");

    }

    @PostMapping("/student/deleteAll")
    public Result deleteAll() {

        studentRepository.deleteAll();

        return new Result("success", "delete all students");

    }

    @PostMapping("/student/create")
    public Result create(@ModelAttribute Student student) {

        if (student.getRealName() == null) {
            return new Result("fail", "real name cannot be empty");
        }

        if (student.getCode() == null) {
            return new Result("fail", "student code cannot be empty");
        }

        studentRepository.save(student);

        return new Result("success", "create student", student);

    }

    @PostMapping("/student/userIdToStudent")
    public Result userIdToStudent(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        Student student = studentService.userIdToStudent(userId);

        return new Result("success", "user id to student", student);

    }

    @PostMapping("/student/studentIdToStudent")
    public Result studentIdToStudent(HttpServletRequest request) {

        String studentId = request.getParameter("studentId");

        Student student = studentService.studentIdToStudent(studentId);

        return new Result("success", "student id to student", student);

    }

    @PostMapping("/student/updateByStudentId")
    public Result updateByStudentId(HttpServletRequest request) {

        String studentId = request.getParameter("studentId");
        String code = request.getParameter("code");
        String realName = request.getParameter("realName");

        Student oldStudent = studentService.studentIdToStudent(studentId);

        if (oldStudent == null) {
            return new Result("fail", "student not exist");
        }

        if (code != null && !code.equals("")) {
            studentRepository.updateCodeByStudentId(code, studentId);
        }

        if (realName != null && !realName.equals("")) {
            studentRepository.updateRealNameByStudentId(realName, studentId);
        }

        Student newStudent = studentService.studentIdToStudent(studentId);

        return new Result("success", "update student", newStudent);

    }
}
