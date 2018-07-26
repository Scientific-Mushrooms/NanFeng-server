package cloud.module.course.section;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface SectionRepository extends CrudRepository<Section, String> {

    Section findBySectionId(String sectionId);

    @Transactional
    void deleteBySectionId(String sectionId);

    @Transactional
    void deleteAllByCourseId(String courseId);

    Iterable<Section> findAllByCourseId(String courseId);

}
