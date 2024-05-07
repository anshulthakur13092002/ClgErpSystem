package com.example.collegeerp.controller.view;

import com.example.collegeerp.controller.AuthController;
import com.example.collegeerp.controller.StudentController;
import com.example.collegeerp.controller.TeacherController;
import com.example.collegeerp.model.JwtRequest;
import com.example.collegeerp.model.JwtResponse;
import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class loginController {
    private final AuthController authController;

    private final StudentController studentController;
    private final TeacherController teacherController;

    public loginController(AuthController authController, StudentController studentController, TeacherController teacherController) {
        this.authController = authController;

        this.studentController = studentController;
        this.teacherController = teacherController;
    }

    @GetMapping("/home")
    public String home(){
        return "index";
    }

    @GetMapping("/login1")
    public String login(Model model)  {
        model.addAttribute("jwtRequest",new JwtRequest());
        return "login";
    }


    @PostMapping("/doLogin")
    public String submitLogin(@ModelAttribute JwtRequest jwtRequest, Model model, HttpSession session){
        ResponseEntity<JwtResponse> jwtResponseResponseEntity=authController.login(jwtRequest);
        String token = jwtResponseResponseEntity.getBody().getJwtToken();
        String username=jwtResponseResponseEntity.getBody().getUsername();
        session.setAttribute("username",username);
//        String role = studentController.getStudentByEmail(jwtResponseResponseEntity.getBody().getUsername()).getRole();
//        session.setAttribute("Authorization", "Bearer " + token);
//        if (role.equals("STUDENT")) {
//            return "redirect:/home";
//        }

//        String role1=teacherController.getTeacherByEmail(jwtResponseResponseEntity.getBody().getUsername()).getRole();
//        if (role1.equals("TEACHER")){
//            return "redirect"
//
//        }
        String courseName=teacherController.getTeacherByEmail(jwtResponseResponseEntity.getBody().getUsername()).getCourse().getCourseName();
        Long courseId=teacherController.getTeacherByEmail(jwtResponseResponseEntity.getBody().getUsername()).getCourse().getCourseId();
        session.setAttribute("courseName",courseName);
        session.setAttribute("courseId",courseId);


        return "redirect:/admin-home";
    }
    @GetMapping("/admin-home")
    public String adminHome(Model model,HttpSession session){
        String username = (String) session.getAttribute("username");
        model.addAttribute("username",username);
        return "studentHome";
    }

    @GetMapping("/allFaculty")
    public String faculty(Model model,HttpSession session){
        List<Teacher> faculties = teacherController.getAllTeachers();
        model.addAttribute("allFaculty",faculties);
        return "allFaculty";
    }
    @GetMapping("/allStudent")
    public String students(Model model,HttpSession session){
        List<Student> students=studentController.getAllStudents();
        model.addAttribute("allStudents",students);
        return  "allStudent";
    } @GetMapping("/btechStudent")
    public String studentBtech(Model model,HttpSession session){
        List<Student> faculties = studentController.getStudentBYCourse("Btech");
        model.addAttribute("allStudents",faculties);
        return "allStudent";
    }


    @GetMapping("/bfarmStudent")
    public String studentBFarm(Model model,HttpSession session){
        List<Student> faculties = studentController.getStudentBYCourse("BPharm");
        model.addAttribute("allStudents",faculties);
        return "allStudent";
    }


    @GetMapping("/bcaStudent")
    public String studentBCA(Model model,HttpSession session){
        List<Student> faculties = studentController.getStudentBYCourse("BCA");
        model.addAttribute("allStudents",faculties);
        return "allStudent";
    }

    @GetMapping("/bscStudent")
    public String studentBsc(Model model,HttpSession session) {
        List<Student> faculties = studentController.getStudentBYCourse("Bsc");
        model.addAttribute("allStudents", faculties);
        return "allStudent";

    }


    @GetMapping("/btechFaculty")
    public String facultyBtech(Model model,HttpSession session){
        List<Teacher> faculties = teacherController.getTeacherByCourse("Btech");
        model.addAttribute("allFaculty",faculties);
        return "allFaculty";
    }


    @GetMapping("/bfarmFaculty")
    public String facultyBFarm(Model model,HttpSession session){
        List<Teacher> faculties = teacherController.getTeacherByCourse("BPharm");
        model.addAttribute("allFaculty",faculties);
        return "allFaculty";
    }
    @GetMapping("/bcaFaculty")
    public String facultyBCA(Model model,HttpSession session){
        List<Teacher> faculties = teacherController.getTeacherByCourse("BCA");
        model.addAttribute("allFaculty",faculties);
        return "allFaculty";
    }

    @GetMapping("/bscFaculty")
    public String facultyBsc(Model model,HttpSession session) {
        List<Teacher> faculties = teacherController.getTeacherByCourse("Bsc");
        model.addAttribute("allFaculty", faculties);
        return "allFaculty";

    }
    @GetMapping("/register-faculty")
    public  String registerFaculty(Model model){
        model.addAttribute("registerFaculty",new Teacher());
        return "register-faculty";
    }


    @PostMapping("/addFaculty")
    public String submitFaculty(@ModelAttribute Teacher faculty,Model model){

         Teacher createFaculty=teacherController.addTeacher( faculty);
         if (createFaculty.getCourse().getCourseId()==1){
             return "redirect:/btechFaculty";
         }
        if (createFaculty.getCourse().getCourseId()==2){
            return "redirect:/bfarmFaculty";
        }
        if (createFaculty.getCourse().getCourseId()==3){
            return "redirect:/bscFaculty";
        }
       if (createFaculty.getCourse().getCourseId()==4){

            return "redirect:/bcaFaculty";
        }
        return "redirect:/register-faculty";
    }
    @GetMapping("/getStudentDetails/{email}")
    public String studentDetails(Model model, HttpSession session, @PathVariable String email) {
        email = (String) session.getAttribute("username");
        Student getStudentDetails = studentController.getStudentByEmail(email);
//        int totalMarks = 0;
//        for (SubjectDto subject : getStudentDetails.getSubject()) {
//            totalMarks += Integer.parseInt( subject.getMarks());
//
//        }
        model.addAttribute("details", getStudentDetails);
        model.addAttribute("username",email);
//        model.addAttribute("totalMarks", totalMarks);
        return "student-details";
    }
    @GetMapping("getStudentByCourse/{course}")
    public String studentByDepartment(Model model,@PathVariable String course,HttpSession session){
        course=(String) session.getAttribute("courseName");
        List<Student> getDetails=studentController.getStudentBYCourse(course);
        model.addAttribute("allStudentByCourse",getDetails);
        model.addAttribute("courseName",course);
        return "allStudentDetailsForTeachers";


    }









}
