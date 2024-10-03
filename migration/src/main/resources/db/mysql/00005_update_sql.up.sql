ALTER TABLE attendance
DROP FOREIGN KEY attendance_ibfk_1,
DROP COLUMN class_id;

ALTER TABLE attendance
DROP FOREIGN KEY attendance_ibfk_2,
DROP COLUMN student_id;

ALTER TABLE attendance
ADD class_detail_id INT,
ADD CONSTRAINT FOREIGN KEY (class_detail_id) REFERENCES class_details(id);

ALTER TABLE attendance MODIFY attendance_status VARCHAR(25) NOT NULL;
ALTER TABLE leave_requests MODIFY status VARCHAR(25) NOT NULL;
ALTER TABLE materials MODIFY material_type VARCHAR(25) NOT NULL;
ALTER TABLE notifications MODIFY status VARCHAR(25) NOT NULL;