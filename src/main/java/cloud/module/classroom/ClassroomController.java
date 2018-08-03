package cloud.module.classroom;


import cloud.common.BaseController;
import cloud.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ClassroomController extends BaseController {


    @Autowired
    private ClassroomRepository classroomRepository;

    @Resource
    private ClassroomService classroomService;


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
}
