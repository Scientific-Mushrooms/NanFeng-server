package cloud.module.course.section;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.module.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class SectionController extends BaseController {


    @Autowired
    private SectionRepository sectionRepository;

    @PostMapping("/section/all")
    public Iterable<Section> all(HttpServletRequest request) {
        return sectionRepository.findAll();
    }

    @PostMapping("/section/create")
    public Result create(HttpServletRequest request) {

        return new Result("success", "create section");
    }


    @PostMapping("/section/sectionIdToSection")
    public Result sectionIdToSection(HttpServletRequest request) {

        String sectionId = request.getParameter("sectionId");

        Section section = sectionRepository.findBySectionId(sectionId);

        return new Result("success", "section id to section", section);
    }


}
