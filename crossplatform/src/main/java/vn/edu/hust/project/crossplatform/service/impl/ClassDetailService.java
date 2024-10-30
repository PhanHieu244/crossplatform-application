package vn.edu.hust.project.crossplatform.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.request.AddStudentRequest;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IClassDetailPort;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.port.IStudentPort;
import vn.edu.hust.project.crossplatform.service.IAuthService;
import vn.edu.hust.project.crossplatform.service.IClassDetailService;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassDetailService implements IClassDetailService {
    private final IClassDetailPort classDetailPort;
    private final IClassPort classPort;
    private final IStudentPort studentPort;
    private final IAuthService authService;

    @Override
    public void addStudent(AddStudentRequest addStudentRequest) {
        var classCode = addStudentRequest.getClassId();
        var classEntity = classPort.findClassByCode(classCode);
        var account = authService.getAccountById(addStudentRequest.getAccountId());
        var student = studentPort.getStudent(account);
        if(classDetailPort.isStudentBelongToClass(student.getId(), classEntity.getId())){
            log.error("student already added in this class");
            throw new ApplicationException("student already added in this class");
        }
        if(classEntity.getMaxStudentAmount() <= classDetailPort.getStudentCount(classEntity.getId())) {
            log.error("Class is max student amount exceeded");
            throw new ApplicationException(ResponseCode.ADD_STUDENT_ERROR ,"Class is max student amount exceeded");
        }
        classDetailPort.addStudent(classEntity.getId(), student.getId());
    }
}
