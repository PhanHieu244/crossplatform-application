ALTER TABLE attendanceModel
DROP FOREIGN KEY attendance_ibfk_1,
DROP COLUMN class_id;

ALTER TABLE attendanceModel
DROP FOREIGN KEY attendance_ibfk_2,
DROP COLUMN student_id;

ALTER TABLE attendanceModel
ADD class_detail_id INT,
ADD CONSTRAINT FOREIGN KEY (class_detail_id) REFERENCES class_details(id);

ALTER TABLE attendanceModel MODIFY attendance_status VARCHAR(25) NOT NULL;
ALTER TABLE leave_requests MODIFY status VARCHAR(25) NOT NULL;
ALTER TABLE materials MODIFY material_type VARCHAR(25) NOT NULL;
ALTER TABLE notifications MODIFY status VARCHAR(25) NOT NULL;

ALTER TABLE attendanceModel DROP session_id;