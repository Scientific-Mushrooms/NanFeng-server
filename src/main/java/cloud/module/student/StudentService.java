package cloud.module.student;


import cloud.common.User.User;
import cloud.common.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;

    @Resource
    private UserService userService;


    public Student studentIdToStudent(String studentId) {

        Student student = studentRepository.findByStudentId(studentId);

        return student;

    }

    public User studentIdToUser(String studentId) {

        Student student = studentRepository.findByStudentId(studentId);

        User user = userService.userIdToUser(student.getUserId());

        return user;

    }

    public Student userIdToStudent(String userId) {

        Student student = studentRepository.findByUserId(userId);

        return student;

    }

    public Iterable<Student> searchByRealName(String realName) {

        String newName = "%" + realName + "%";

        Iterable<Student> students = studentRepository.findByRealNameLike(newName);

        return students;

    }
}
