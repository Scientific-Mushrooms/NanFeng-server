package cloud.module.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;


    public Student studentIdToStudent(String studentId) {

        Student student = studentRepository.findByStudentId(studentId);

        return student;

    }

    public Student userIdToStudent(String userId) {

        Student student = studentRepository.findByUserId(userId);

        return student;

    }
}
