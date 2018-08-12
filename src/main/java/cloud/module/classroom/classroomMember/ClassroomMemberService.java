package cloud.module.classroom.classroomMember;


import cloud.module.classroom.Classroom;
import cloud.module.student.Student;
import cloud.module.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ClassroomMemberService {


    @Autowired
    private ClassroomMemberRepository classroomMemberRepository;

    @Resource
    private StudentService studentService;


    public ClassroomMember classroomMemberIdToClassroomMember(String classroomMemberId) {

        ClassroomMember classroomMember = classroomMemberRepository.findByClassroomMemberId(classroomMemberId);

        return classroomMember;

    }

    public ClassroomMember studentIdToClassroomMember(String studentId) {

        ClassroomMember classroomMember = classroomMemberRepository.findByStudentId(studentId);

        return classroomMember;

    }

    public Iterable<ClassroomMember> classroomIdToAllClassroomMembers(String classroomId) {

        Iterable<ClassroomMember> classroomMembers = classroomMemberRepository.findAllByClassroomId(classroomId);

        return classroomMembers;

    }

    public Iterable<Student> classroomIdToAllStudents(String classroomId) {

        Iterable<ClassroomMember> classroomMembers = classroomMemberRepository.findAllByClassroomId(classroomId);

        List<Student> students = new ArrayList();

        for (ClassroomMember classroomMember : classroomMembers) {
            Student student = studentService.studentIdToStudent(classroomMember.getStudentId());
            students.add(student);
        }

        return students;

    }

    public void deleteByClassroomMemberId(String classroomMemberId) {

        classroomMemberRepository.deleteByClassroomMemberId(classroomMemberId);

    }


}
