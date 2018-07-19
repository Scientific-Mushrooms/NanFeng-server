package cloud.module.course.instructor;

import org.springframework.beans.factory.annotation.Autowired;

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
}
