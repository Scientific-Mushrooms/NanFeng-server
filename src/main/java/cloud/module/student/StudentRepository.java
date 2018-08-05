package cloud.module.student;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByStudentId(String studentId);

    Student findByUserId(String userId);

    @Modifying
    @Query("update Student s set s.realName = ?1 where s.studentId = ?2")
    @Transactional
    void updateRealNameByStudentId(String realName, String studentId);

    @Modifying
    @Query("update Student s set s.code = ?1 where s.studentId = ?2")
    @Transactional
    void updateCodeByStudentId(String code, String studentId);
}
