package vn.edu.hust.project.crossplatform.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClassStatus {
    ACTIVE("Active"),
    COMPLETED("Completed"),
    UPCOMING("Upcoming");

    private final String value;
}
