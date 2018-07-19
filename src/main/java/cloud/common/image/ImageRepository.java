package cloud.common.image;

import cloud.common.image.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Transactional
    void deleteByImageId(String imageId);

    Image findByImageId(String imageId);

    Iterable<Image> findAllByParentId(String parentId);

}
