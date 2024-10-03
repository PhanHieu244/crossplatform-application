package vn.edu.hust.project.crossplatform.repository.mysql.adapter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.constant.ResponseCode;
import vn.edu.hust.project.crossplatform.dto.ClassDto;
import vn.edu.hust.project.crossplatform.dto.request.EditClassRequest;
import vn.edu.hust.project.crossplatform.exception.NoDataException;
import vn.edu.hust.project.crossplatform.exception.UnauthorizedException;
import vn.edu.hust.project.crossplatform.exception.base.ApplicationException;
import vn.edu.hust.project.crossplatform.port.IClassPort;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassDetailRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.IClassRepository;
import vn.edu.hust.project.crossplatform.repository.mysql.mapper.ClassModelMapper;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassDetailModel;
import vn.edu.hust.project.crossplatform.repository.mysql.model.ClassModel;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClassAdapter implements IClassPort {
    private final IClassRepository classRepository;
    private final IClassDetailRepository classDetailRepository;

    @Override
    public ClassDto createClass(ClassDto classDto) {

        if(classDto.getStartDate().isAfter(classDto.getEndDate())){
            throw new ApplicationException(
                    ResponseCode.PARAMETER_VALUE_IS_INVALID,
                    "Start date cannot be after end date"
            );
        }
        classDto.setStatus(ClassStatus.ACTIVE);
        return ClassModelMapper.INSTANCE.toEntity(
                classRepository.save(ClassModelMapper.INSTANCE.toModel(classDto))
        );
    }

    @Override
    public ClassDto findClassById(Integer classId) {
        var classModel = classRepository.findById(classId).orElseThrow(
                () -> {
                    log.error("Class not found with id {}", classId);
                    return new NoDataException("Class not found");
                }
        );
        return ClassModelMapper.INSTANCE.toEntity(classModel);
    }

    @Override
    public ClassDto findClassByCode(String classCode) {
        var classModel = getClassModelByCode(classCode);
        return ClassModelMapper.INSTANCE.toEntity(classModel);
    }

    @Override
    public ClassDto editClass(EditClassRequest request) {
        var existingClass = getClassModelByCode(request.getClassId());
        if(request.getClassName() != null){
            existingClass.setClassName(request.getClassName());
        }
        if(request.getStartDate() != null){
            existingClass.setStartDate(request.getStartDate());
        }
        if(request.getEndDate() != null){
            existingClass.setEndDate(request.getEndDate());
        }
        if (request.getStatus() != null){
            existingClass.setStatus(request.getStatus().name());
        }
        return ClassModelMapper.INSTANCE.toEntity(
                classRepository.save(existingClass)
        );
    }

    @Override
    public List<ClassDto> getStudentClasses(Integer studentId) {
        var classIds = classDetailRepository.findAllByStudentId(studentId)
                .stream()
                .map(ClassDetailModel::getClassId).toList();
        return classRepository.findAllById(classIds)
                .stream()
                .map(ClassModelMapper.INSTANCE::toEntity)
                .toList();
    }

    @Override
    public List<ClassDto> getLecturerClasses(Integer lecturerId) {
        return classRepository.findAllByLecturerId(lecturerId)
                .stream()
                .map(ClassModelMapper.INSTANCE::toEntity)
                .toList();
    }

    @Override
    public void deleteClass(String code) {
        var existingClass = getClassModelByCode(code);
        classRepository.delete(existingClass);
    }

    private ClassModel getClassModelByCode(String code) {
        return classRepository.findClassByCode(code).orElseThrow(
                () -> {
                    log.error("Class not found with code {}", code);
                    return new NoDataException("class is not exist");
                }
        );
    }
}
