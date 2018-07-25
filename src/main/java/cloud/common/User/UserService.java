package cloud.common.User;

import cloud.common.User.User;
import cloud.common.User.UserRepository;
import cloud.common.image.Image;
import cloud.common.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


@Transactional
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Resource
    private ImageService imageService;


    public User userIdToUser(String id) {
        return userRepository.findByUserId(id);
    }




    public void updateAvatar(String userId, MultipartFile avatar) {

        User user = userIdToUser(userId);

        // new avatar in image db
        Image image = imageService.saveImage(avatar, userId, "avatar");

        // delete the image in the server
        if (user.getAvatarId() != null) {
            imageService.deleteByImageId(user.getAvatarId());
        }

        // replace the old avatar path and id with new data
        userRepository.updateAvatarIdByUserId(image.getImageId(), userId);
        userRepository.updateAvatarPathByUserId(image.getName(), userId);

    }

    public void updateEmail(String userId, String email) {

        userRepository.updateEmailByUserId(email, userId);

    }

    public void updatePassword(String userId, String password) {

        userRepository.updatePasswordByUserId(password, userId);

    }

    public void updateNickName(String userId, String nickName) {

        userRepository.updateNickNameByUserId(nickName, userId);

    }


}
