package vn.edu.hust.project.crossplatform.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClassType {
    LT("LT"),
    BT("BT"),
    LT_BT("LT+BT");

    private final String value;
}
