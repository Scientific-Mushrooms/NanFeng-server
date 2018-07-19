package cloud.module.course.instructor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InstructorRepository extends CrudRepository<Instructor, String> {

    Instructor findByUserId(String userId);

    Instructor findByInstructorId(String instructorId);


    @Transactional
    void deleteByInstructorId(String instructorId);


}
