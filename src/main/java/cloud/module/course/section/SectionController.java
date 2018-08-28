package cloud.module.course.section;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.module.course.Course;
import cloud.module.course.CourseRepository;
import cloud.module.course.CourseService;
import cloud.module.instructor.Instructor;
import cloud.module.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
public class SectionController extends BaseController {


    @Autowired
    private SectionRepository sectionRepository;

    @Resource
    private InstructorService instructorService;

    @Resource
    private CourseService courseService;


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
    public Result create(@ModelAttribute Section section, HttpServletRequest request) {

        String code = request.getParameter("code");
        String realName = request.getParameter("realName");

        if (isEmpty(code)) {
            return new Result("fail", "course code cannot be empty");
        }

        if (isEmpty(realName)) {
            return new Result("fail", "real name cannot be empty");
        }

        Instructor instructor = instructorService.realNameToInstructor(realName);
        Course course = courseService.codeToCourse(code);

        if (course == null) {
            return new Result("fail", "course not exist");
        }

        if (instructor == null) {
            instructor = instructorService.save(realName);
        }

        section.setCourseId(course.getCourseId());
        section.setInstructorId(instructor.getInstructorId());

        sectionRepository.save(section);

        return new Result("success", "create section", section);

    }
    @PostMapping("/section/import")
    public Result importData(@ModelAttribute Section section, HttpServletRequest request) {

        String realName = request.getParameter("realName");

        Instructor instructor = instructorService.realNameToInstructor(realName);

        if (instructor == null) {
            instructor = instructorService.save(realName);
        }

        section.setInstructorId(instructor.getInstructorId());

        sectionRepository.save(section);

        return new Result("success", "create section", section);

    }




}
