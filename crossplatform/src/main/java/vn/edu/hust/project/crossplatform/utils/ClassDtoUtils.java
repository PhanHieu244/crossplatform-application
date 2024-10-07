package vn.edu.hust.project.crossplatform.utils;

import vn.edu.hust.project.crossplatform.constant.ClassStatus;
import vn.edu.hust.project.crossplatform.dto.ClassDto;

import java.time.LocalDate;

public class ClassDtoUtils {
    public static ClassStatus getStatus(LocalDate startDate, LocalDate endDate){
        var now = LocalDate.now();
        if(now.isBefore(startDate)){
            return ClassStatus.UPCOMING;
        }
        else if(now.isBefore(endDate)){
            return ClassStatus.ACTIVE;
        }
        else {
            return ClassStatus.COMPLETED;
        }
    }

    public static boolean IsDateValid(LocalDate date, ClassDto classModel) {
        return date.isAfter(classModel.getStartDate()) && date.isBefore(classModel.getEndDate());
    }
}
