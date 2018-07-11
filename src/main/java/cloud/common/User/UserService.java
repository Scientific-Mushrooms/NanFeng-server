package cloud.common.User;

import cloud.common.User.User;
import cloud.common.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String userIdToUserName(String id) {
        return userRepository.findById(id).getName();
    }

    public User findById(String id) {
        return userRepository.findById(id);
    }

    public User userIdToUser(String id) {
        return userRepository.findById(id);
    }

    public User create(String email, String password) {

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName("default");
        userRepository.save(user);

        return user;
    }

    public void increasePostByOne(String id) {
        User user = userRepository.findById(id);
        int post = user.getPost() + 1;
        userRepository.updatePostById(post, id);
    }

    public void decreasePostByOne(String id) {
        User user = userRepository.findById(id);
        int post = user.getPost() - 1;
        userRepository.updatePostById(post, id);
    }

    public void increaseFollowerByOne(String id) {
        User user = userRepository.findById(id);
        int post = user.getFollower() + 1;
        userRepository.updateFollowerById(post, id);
    }

    public void decreaseFollowerByOne(String id) {
        User user = userRepository.findById(id);
        int post = user.getFollower() - 1;
        userRepository.updateFollowerById(post, id);
    }

    public void increaseFollowingByOne(String id) {
        User user = userRepository.findById(id);
        int post = user.getFollowing() + 1;
        userRepository.updateFollowingById(post, id);
    }

    public void decreaseFollowingByOne(String id) {
        User user = userRepository.findById(id);
        int post = user.getFollowing() - 1;
        userRepository.updateFollowingById(post, id);
    }


}
