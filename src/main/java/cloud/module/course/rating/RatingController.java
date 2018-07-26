package cloud.module.course.rating;

import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@RestController
public class RatingController extends BaseController {

    @Autowired
    private RatingRepository ratingRepository;

    @Resource
    private RatingService ratingService;

    @PostMapping("/rating/all")
    public Result all(HttpServletRequest request) {

        Iterable<Rating> ratings = ratingRepository.findAll();

        return new Result("success", "all rating", ratings);

    }

    @PostMapping("/rating/deleteAll")
    public Result deleteAll(HttpServletRequest request) {

        ratingRepository.deleteAll();

        return new Result("success", "delete allratings");

    }

    @PostMapping("/rating/deleteAllByUserId")
    public Result deleteAllByUserId(HttpServletRequest request) {

        String userId = request.getParameter("userId");

        ratingRepository.deleteByUserId(userId);

        return new Result("success", "delete all ratings by user id");

    }

    @PostMapping("/rating/deleteAllByCourseId")
    public Result deleteAllByCourseId(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        ratingRepository.deleteByCourseId(courseId);

        return new Result("success", "delete all ratings by course id");

    }

    @PostMapping("/rating/ratingIdToRating")
    public Result ratingIdToRating(HttpServletRequest request) {

        String ratingId = request.getParameter("ratingId");

        Rating rating = ratingService.ratingIdToRating(ratingId);

        return new Result("success", "ratingID to Rating", rating);

    }

    @PostMapping("/rating/courseIdToRatings")
    public Result courseIdToRating(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        Iterable<Rating> ratings = ratingRepository.findAllByCourseId(courseId);

        return new Result("success", "ratingID to Rating", ratings);

    }

    @PostMapping("/rating/create")
    public Result create(@ModelAttribute Rating rating) {

        // verify parameters
        if (rating.getCourseId() == null || rating.getUserId() == null) {
            return new Result("fail", "course id or user id can not be empty");
        }

        rating.setDate(new Date());

        ratingRepository.save(rating);

        return new Result("success", "create rating", rating);

    }

}
