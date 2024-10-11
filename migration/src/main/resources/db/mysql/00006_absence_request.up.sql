CREATE TABLE absence_request (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            class_detail_id INT NOT NULL,
                            status VARCHAR(25) NOT NULL,
                            absence_date DATETIME NOT NULL,
                            reason TEXT,
                            FOREIGN KEY (class_detail_id) REFERENCES class_details(id)
);