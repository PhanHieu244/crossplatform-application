CREATE TABLE class_details(
    id INT AUTO_INCREMENT PRIMARY KEY,
    class_id INT,
    student_id INT,
    FOREIGN KEY (class_id) REFERENCES classes(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
);

ALTER TABLE classes
    ADD start_date TIMESTAMP NOT NULL,
    ADD end_date TIMESTAMP NOT NULL,
    ADD max_student_amount INT NOT NULL,
    ADD code VARCHAR(6) NOT NULL,
    ADD attached_code VARCHAR(6),
    ADD status VARCHAR(20) NOT NULL;

CREATE INDEX code_index ON classes(code);
