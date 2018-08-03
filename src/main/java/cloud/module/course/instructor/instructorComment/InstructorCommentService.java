package cloud.module.course.instructor.instructorComment;


import cloud.module.course.instructor.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class InstructorCommentService {


    @Autowired
    private InstructorCommentRepository instructorCommentRepository;


    public InstructorComment instructorCommentIdToInstructorComment(String instructorCommentId) {

        InstructorComment comment = instructorCommentRepository.findByInstructorCommentId(instructorCommentId);

        return comment;

    }

    public void deleteAllByInstructorId(String instructorId) {

        instructorCommentRepository.deleteAllByInstructorId(instructorId);

    }

}
