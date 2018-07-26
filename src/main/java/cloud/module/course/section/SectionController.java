package cloud.module.course.section;


import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class SectionController extends BaseController {


    @Autowired
    private SectionRepository sectionRepository;

    @PostMapping("/section/all")
    public Result all(HttpServletRequest request) {

        Iterable<Section>  sections = sectionRepository.findAll();

        return new Result("success", "all sections", sections);

    }

    @PostMapping("/section/deleteBySectionId")
    public Result deleteBySectionId(HttpServletRequest request) {

        String sectionId = request.getParameter("sectionId");

        sectionRepository.deleteBySectionId(sectionId);

        return new Result("success", "delete by section id");

    }

    @PostMapping("/section/deleteAllByCourseId")
    public Result deleteByCourseId(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        sectionRepository.deleteAllByCourseId(courseId);

        return new Result("success", "delete all sections by course id");

    }

    @PostMapping("/section/delAll")
    public Result delAll(HttpServletRequest request) {

        sectionRepository.deleteAll();

        return new Result("success", "delete all");

    }

    @PostMapping("/section/sectionIdToSection")
    public Result sectionIdToSection(HttpServletRequest request) {

        String sectionId = request.getParameter("sectionId");

        Section section = sectionRepository.findBySectionId(sectionId);

        return new Result("success", "section id to section", section);

    }

    @PostMapping("/section/courseIdToSections")
    public Result courseIdToSections(HttpServletRequest request) {

        String courseId = request.getParameter("courseId");

        Iterable<Section> sections = sectionRepository.findAllByCourseId(courseId);

        return new Result("success", "section id to section", sections);

    }

    @PostMapping("/section/create")
    public Result create(@ModelAttribute Section section) {

        section.setEnrolledStudentNum(0);

        sectionRepository.save(section);

        return new Result("success", "create section", section);

    }




}
