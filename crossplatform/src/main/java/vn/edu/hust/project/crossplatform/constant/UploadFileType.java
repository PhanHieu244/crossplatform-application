package vn.edu.hust.project.crossplatform.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UploadFileType {
    IMAGE("image/"),
    ZIP(""),
    DOCX(""),
    ;
    private final String value;
}
