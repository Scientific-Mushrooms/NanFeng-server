package cloud.module.instructor;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface InstructorRepository extends CrudRepository<Instructor, String> {

    Instructor findByUserId(String userId);

    Instructor findByInstructorId(String instructorId);


    @Transactional
    void deleteByInstructorId(String instructorId);


    Instructor findByRealName(String realName);

    @Modifying
    @Query("update Instructor i set i.realName = ?1 where i.instructorId = ?2")
    @Transactional
    void updateRealNameByInstructorId(String realName, String instructorId);

    @Modifying
    @Query("update Instructor i set i.code = ?1 where i.instructorId = ?2")
    @Transactional
    void updateCodeByInstructorId(String code, String instructorId);
}
