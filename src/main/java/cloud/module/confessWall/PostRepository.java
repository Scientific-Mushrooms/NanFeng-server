package cloud.module.confessWall;


import org.springframework.data.repository.CrudRepository;

import java.util.Date;


public interface PostRepository extends CrudRepository<Post, Long> {


    Iterable<Post> findTop10ByDateBeforeAndTypeOrderByDateDesc(Date date, String type);
}
