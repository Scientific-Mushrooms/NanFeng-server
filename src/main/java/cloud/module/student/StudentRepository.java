package cloud.module.student;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByStudentId(String studentId);

    Student findByUserId(String userId);

}
