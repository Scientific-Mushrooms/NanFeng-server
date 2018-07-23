package cloud.common.image;

import cloud.common.image.Image;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ImageRepository extends CrudRepository<Image, Long> {

    @Transactional
    void deleteByImageId(String imageId);

    Image findByImageId(String imageId);

    Iterable<Image> findAllByParentId(String parentId);

    @Modifying
    @Query("update Image i set i.parentId = ?1 where i.imageId = ?2")
    @Transactional
    void updateParentByImageId(String parentId, String imageId);

    @Modifying
    @Query("update Image i set i.type = ?1 where i.imageId = ?2")
    @Transactional
    void updateTypeImageId(String type, String imageId);
}
