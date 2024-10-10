ALTER TABLE assignments
DROP grading_status

ALTER TABLE assignments
ADD file_url VARCHAR(300)

ALTER TABLE assignment_submissions
ADD file_url VARCHAR(300),
ADD text_response TEXT