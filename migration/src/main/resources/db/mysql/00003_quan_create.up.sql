

-- Bảng tài khoản cá nhân
CREATE TABLE accounts (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          role ENUM('Giảng viên', 'Sinh viên') NOT NULL,
                          token VARCHAR(255),
                          session VARCHAR(255),
                          status ENUM('Kích hoạt', 'Bị khóa') DEFAULT 'Kích hoạt'
);

-- Bảng giảng viên
CREATE TABLE lecturers (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           account_id INT,
                           FOREIGN KEY (account_id) REFERENCES accounts(id)
);

-- Bảng sinh viên
CREATE TABLE students (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL UNIQUE,
                          account_id INT,
                          FOREIGN KEY (account_id) REFERENCES accounts(id)
);

-- Bảng lớp học
CREATE TABLE classes (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         class_name VARCHAR(100) NOT NULL,
                         description TEXT,
                         lecturer_id INT,
                         schedule VARCHAR(255),
                         FOREIGN KEY (lecturer_id) REFERENCES lecturers(id)
);

-- Bảng bài tập
CREATE TABLE assignments (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             title VARCHAR(100) NOT NULL,
                             description TEXT,
                             lecturer_id INT,
                             class_id INT,
                             deadline DATETIME,
                             grading_status ENUM('Chưa chấm', 'Đã chấm') DEFAULT 'Chưa chấm',
                             FOREIGN KEY (lecturer_id) REFERENCES lecturers(id),
                             FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- Bảng điểm danh
CREATE TABLE attendanceModel (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            session_id INT NOT NULL,
                            class_id INT,
                            student_id INT,
                            attendance_status ENUM('Có mặt', 'Vắng mặt có phép', 'Vắng mặt không phép'),
                            attendance_time DATETIME,
                            FOREIGN KEY (class_id) REFERENCES classes(id),
                            FOREIGN KEY (student_id) REFERENCES students(id)
);

-- Bảng tài liệu học tập
CREATE TABLE materials (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           class_id INT,
                           material_name VARCHAR(255) NOT NULL,
                           description TEXT,
                           material_link VARCHAR(255),
                           material_type ENUM('Bài giảng', 'Bài đọc thêm', 'Video hướng dẫn'),
                           FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- Bảng xin phép nghỉ học
CREATE TABLE leave_requests (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                student_id INT,
                                class_id INT,
                                reason TEXT,
                                status ENUM('Đã duyệt', 'Từ chối', 'Chờ xử lý') DEFAULT 'Chờ xử lý',
                                request_time DATETIME,
                                response_time DATETIME,
                                FOREIGN KEY (student_id) REFERENCES students(id),
                                FOREIGN KEY (class_id) REFERENCES classes(id)
);

-- Bảng nộp bài tập
CREATE TABLE assignment_submissions (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        assignment_id INT,
                                        student_id INT,
                                        submission_time DATETIME,
                                        grade FLOAT DEFAULT NULL,
                                        FOREIGN KEY (assignment_id) REFERENCES assignments(id),
                                        FOREIGN KEY (student_id) REFERENCES students(id)
);

-- Bảng thông báo
CREATE TABLE notifications (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               recipient_id INT,
                               message TEXT,
                               sent_time DATETIME,
                               status ENUM('Đã đọc', 'Chưa đọc') DEFAULT 'Chưa đọc',
                               FOREIGN KEY (recipient_id) REFERENCES accounts(id)
);

-- Bảng lịch sử hoạt động
CREATE TABLE activity_logs (
                               id INT AUTO_INCREMENT PRIMARY KEY,
                               account_id INT,
                               activity_description TEXT,
                               activity_time DATETIME,
                               FOREIGN KEY (account_id) REFERENCES accounts(id)
);
