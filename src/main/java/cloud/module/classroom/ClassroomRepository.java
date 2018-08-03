package cloud.module.classroom;

import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface ClassroomRepository extends CrudRepository<Classroom, String> {
}
