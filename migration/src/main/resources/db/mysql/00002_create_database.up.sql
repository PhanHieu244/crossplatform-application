CREATE TABLE users
(
    `id` int PRIMARY KEY AUTO_INCREMENT,
    `first_name` varchar(50) NOT NULL,
    `last_name` varchar(50) NOT NULL,
    `email` varchar(100)   NOT NULL,
    `phone_number` varchar(15),
    `password` TEXT NOT NULL,
    `role_id` int(11) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)ENGINE=InnoDB CHARSET=utf8;