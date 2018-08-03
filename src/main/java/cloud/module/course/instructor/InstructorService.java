package cloud.module.course.instructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class InstructorService {


    @Autowired
    private InstructorRepository instructorRepository;


    public Instructor userIdToInstructor(String userId) {

        Instructor instructor = instructorRepository.findByUserId(userId);

        return instructor;
    }

    public Instructor instructorIdToInstructor(String instructorId) {

        Instructor instructor = instructorRepository.findByInstructorId(instructorId);

        return instructor;
    }

    public Instructor realNameToInstructor(String realName) {

        Instructor instructor = instructorRepository.findByRealName(realName);

        return instructor;
    }

    public Instructor save(String realName) {

        Instructor instructor = new Instructor();

        instructor.setRealName(realName);

        instructorRepository.save(instructor);

        return instructor;

    }
}
