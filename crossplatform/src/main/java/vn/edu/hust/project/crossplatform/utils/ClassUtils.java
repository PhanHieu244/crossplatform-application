package vn.edu.hust.project.crossplatform.utils;

import vn.edu.hust.project.crossplatform.constant.ClassStatus;

import java.time.LocalDate;

public class ClassUtils {
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
}
