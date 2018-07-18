package cloud.module.course.section;

import org.springframework.data.repository.CrudRepository;



public interface SectionRepository extends CrudRepository<Section, String> {

    Section findBySectionId(String sectionId);

}
