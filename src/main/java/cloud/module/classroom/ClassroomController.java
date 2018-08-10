package cloud.module.classroom;


import cloud.common.BaseController;
import cloud.common.Result;
import cloud.common.image.Image;
import cloud.common.image.ImageService;
import cloud.module.instructor.Instructor;
import cloud.module.instructor.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ClassroomController extends BaseController {


    @Autowired
    private ClassroomRepository classroomRepository;

    @Resource
    private ClassroomService classroomService;

    @Resource
    private ImageService imageService;

    @Resource
    private InstructorService instructorService;


    @PostMapping("/classroom/all")
    public Result all() {

        Iterable<Classroom> classrooms = classroomRepository.findAll();

        return new Result("success", "all classrooms", classrooms);

    }

    @PostMapping("/classroom/deleteAll")
    public Result deleteAll() {

        classroomRepository.deleteAll();

        return new Result("success", "delete all classrooms");

    }

    @PostMapping("/classroom/deleteByClassroomId")
    public Result deleteByClassroomId(HttpServletRequest request) {

        String classroomId = request.getParameter("classroomId");

        classroomService.deleteByClassroomId(classroomId);

        return new Result("success", "delete by classroom id");

    }

    @PostMapping("/classroom/deleteAllByInstructorId")
    public Result deleteAllByInstructorId(HttpServletRequest request) {

        String instructorId = request.getParameter("instructorId");

        classroomService.deleteAllByInstructorId(instructorId);

        return new Result("success", "delete all classrooms by instructor id");
    }

    @PostMapping("/classroom/classroomIdToClassroom")
    public Result classroomIdToClassroom(HttpServletRequest request) {

        String classroomId = request.getParameter("classroomId");

        Classroom classroom = classroomService.classroomIdToClassroom(classroomId);

        return new Result("success", "classroom id to classroom", classroom);

    }

    @PostMapping("/classroom/instructorIdToAllClassrooms")
    public Result instructorIdToAllClassrooms(HttpServletRequest request) {

        String instructorId = request.getParameter("instructorId");

        if (instructorId == null || instructorId.equals("")) {
            return new Result("fail", "instructor id cannot be empty");
        }

        Iterable<Classroom> classrooms = classroomService.instructorIdToAllClassrooms(instructorId);

        return new Result("success", "instructor to all classrooms", classrooms);

    }

    @PostMapping("/classroom/create")
    public Result create(@ModelAttribute Classroom classroom, @RequestParam("avatar") MultipartFile avatar) {

        String instructorId = classroom.getInstructorId();
        Instructor instructor = instructorService.instructorIdToInstructor(instructorId);

        if (instructorId == null || instructorId.equals("") || instructor == null) {
            return new Result("fail", "instructor can not be empty");
        }

        if (avatar != null) {
            Image image = imageService.saveImage(avatar, classroom.getClassroomId(), "avatar");
            classroom.setAvatarId(image.getImageId());
        }

        classroom.setDate(new Date());

        classroomRepository.save(classroom);

        return new Result("success", "create classroom", classroom);

    }

}
