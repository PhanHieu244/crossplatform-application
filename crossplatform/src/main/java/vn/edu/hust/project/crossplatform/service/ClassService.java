package vn.edu.hust.project.crossplatform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.dto.request.ClassRequest;

import vn.edu.hust.project.crossplatform.dto.request.GetClassRequest;
import vn.edu.hust.project.crossplatform.repository.mysql.ClassRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.StudentRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.LecturerRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Account;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Course;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Lecturer;
import vn.edu.hust.project.crossplatform.repository.mysql.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TokenService tokenService;

    public boolean existsByClass_name(String class_name) {
        return classRepository.existsByClassName(class_name);
    }

    public int createClass(ClassRequest request) throws Exception {

        Account account = tokenService.validateToken(request.getToken());

        Lecturer lecturer = lecturerRepository.findByAccountId(account.getId());

        // Tạo lớp học mới
        Course newClass = new Course();
        newClass.setClassName(request.getClassName());
        newClass.setSemester(request.getSemester());
        newClass.setDescription(request.getDescription());
        newClass.setMaxStudents(request.getMaxStudents().intValue());
        newClass.setStartDate(request.getStartDate());
        newClass.setEndDate(request.getEndDate());
        newClass.setLecturer(lecturer);

        classRepository.save(newClass);
        return lecturer.getId();
    }

    public List<Map<String, Object>> getClassList(GetClassRequest request) throws Exception {
        // Kiểm tra token có hợp lệ không
        Account account = tokenService.validateToken(request.getToken());
        Lecturer lecturer = lecturerRepository.findByAccountId(account.getId());

        List<Map<String, Object>> classList = new ArrayList<>();

        // Truy vấn danh sách lớp học của giảng viên
        List<Course> classes = classRepository.findByLecturer(lecturer);


        for (Course c : classes) {
            Map<String, Object> classInfo = new HashMap<>();
            classInfo.put("class_id", c.getId());
            classInfo.put("class_name", c.getClassName());
            classInfo.put("lecturer_name", lecturerRepository.findByAccount(account).getName());
            Long studentCount = studentRepository.countByClassField(c);
            classInfo.put("student_count", studentCount);
            classInfo.put("start_date", c.getStartDate());
            classInfo.put("end_date", c.getEndDate());

            // Xác định trạng thái lớp học
            LocalDate today = LocalDate.now();
            if (today.isBefore(c.getStartDate())) {
                classInfo.put("status", "Sắp diễn ra");
            } else if (today.isAfter(c.getEndDate())) {
                classInfo.put("status", "Đã kết thúc");
            } else {
                classInfo.put("status", "Đang hoạt động");
            }

            classList.add(classInfo);
        }
        if (classList.isEmpty()) {
            return classList; // Trả về danh sách trống nếu không có lớp học
        }

        return classList;
    }
}

