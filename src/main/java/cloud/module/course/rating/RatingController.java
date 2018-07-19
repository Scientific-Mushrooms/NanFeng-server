package cloud.module.course.rating;

import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
}
