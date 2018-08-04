package cloud.module.classroom;


import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public Classroom classroomIdToClassroom(String classroomId) {

        Classroom classroom = classroomRepository.findByClassroomId(classroomId);

        return classroom;

    }

    public void deleteByClassroomId(String classroomId) {

        classroomRepository.deleteByClassroomId(classroomId);

    }

    public void deleteAllByInstructorId(String instructorId) {

        classroomRepository.deleteAllByInstructorId(instructorId);

    }
}
