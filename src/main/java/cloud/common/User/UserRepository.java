package cloud.common.User;

import cloud.common.User.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


public interface UserRepository extends CrudRepository<User, String> {

    void deleteByUserId(String userId);

    boolean existsById(String userId);

    boolean existsByEmail(String email);

    User findByEmail(String email);

    User findByUserId(String id);


    @Modifying
    @Query("update User u set u.avatarPath = ?1 where u.userId = ?2")
    @Transactional
    void updateAvatarPathByUserId(String avatarPath, String userId);

    @Modifying
    @Query("update User u set u.avatarId = ?1 where u.userId = ?2")
    @Transactional
    void updateAvatarIdByUserId(String avatarPath, String userId);

    @Modifying
    @Query("update User u set u.password = ?1 where u.userId = ?2")
    @Transactional
    void updatePasswordByUserId(String password, String userId);

    @Modifying
    @Query("update User u set u.nickName = ?1 where u.userId = ?2")
    @Transactional
    void updateNickNameByUserId(String nickName, String userId);

    @Modifying
    @Query("update User u set u.email = ?1 where u.userId = ?2")
    @Transactional
    void updateEmailByUserId(String email, String userId);

}