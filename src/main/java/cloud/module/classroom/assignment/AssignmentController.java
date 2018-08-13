package cloud.module.classroom.assignment;


import cloud.common.BaseController;
import cloud.common.Result;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class AssignmentController extends BaseController {


    @Autowired
    private AssignmentRepository assignmentRepository;

    @Resource
    private AssignmentService assignmentService;


    @PostMapping("/assignment/all")
    private Result all() {

        Iterable<Assignment> assignments = assignmentRepository.findAll();

        return new Result("success", "all assignments", assignments);

    }

    @PostMapping("/assignment/deleteAll")
    private Result deleteAll() {

        assignmentRepository.deleteAll();

        return new Result("success", "delete all assignments");

    }

    @PostMapping("/assignment/create")
    private Result create(@ModelAttribute Assignment assignment) {

        String classroomId = assignment.getClassroomId();
        String type = assignment.getType();

        if (classroomId == null || classroomId.equals("")) {
            return new Result("fail", "classroom id cannot be empty");
        }

        if (type == null || type.equals("")) {
            return new Result("fail", "type cannot be empty");
        }

        assignmentRepository.save(assignment);

        return new Result("success", "create assignment");

    }

    @PostMapping("/assignement/assignmentItToAssignment")
    private Result assignmentItToAssignment(HttpServletRequest request) {

        String assignmentId = request.getParameter("assignmentId");

        Assignment assignment = assignmentService.assignmentIdToAssignment(assignmentId);

        return new Result("success", "assignment id to assignment", assignment);

    }

    @PostMapping("/assignment/classroomIdToAllAssignments")
    private Result classroomIdToAllAssignments(HttpServletRequest request) {

        String classroomIdToAllAssignments = request.getParameter("classroomIdToAllAssignments");

        Iterable<Assignment> assignments = assignmentService.classroomIdToAllAssignments(classroomIdToAllAssignments);

        return new Result("success", "assignment id to assignment", assignments);

    }

}
