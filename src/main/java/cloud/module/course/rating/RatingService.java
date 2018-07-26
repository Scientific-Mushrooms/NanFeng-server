package cloud.module.course.rating;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class RatingService {


    @Autowired
    private RatingRepository ratingRepository;


    public Rating ratingIdToRating(String ratingId) {

        Rating rating = ratingRepository.findByRatingId(ratingId);

        return rating;
    }


}
