package vn.edu.hust.project.crossplatform.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UploadDirectory{
    ASSIGNMENT("uploads/assignments"),
    SURVEY("uploads/surveys"),
    MATERIAL("uploads/materials"),
    SUBMISSION("uploads/submissions"),
    ;
    private final String value;
}
