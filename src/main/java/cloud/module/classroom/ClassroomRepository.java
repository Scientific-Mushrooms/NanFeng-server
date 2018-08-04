package cloud.module.classroom;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.CachedRowSet;

public interface ClassroomRepository extends CrudRepository<Classroom, String> {

    Classroom findByClassroomId(String classroomId);

    @Transactional
    void deleteByClassroomId(String classroomId);

    @Transactional
    void deleteAllByInstructorId(String instructorId);
}
