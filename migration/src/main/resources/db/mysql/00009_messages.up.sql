
CREATE TABLE conversations
(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    current_message_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB CHARSET=utf8;


CREATE TABLE conversation_partners
(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    conversation_id INT NOT NULL,
    partner_id INT NOT NULL,
    CONSTRAINT FOREIGN KEY (conversation_id) REFERENCES conversations(id),
    CONSTRAINT FOREIGN KEY (partner_id) REFERENCES accounts(id)
)ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE messages
(
    `id` INT PRIMARY KEY AUTO_INCREMENT,
    conversation_id INT NOT NULL,
    sender INT NOT NULL,
    content TEXT,
    status VARCHAR(25) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT FOREIGN KEY (conversation_id) REFERENCES conversations(id),
    CONSTRAINT FOREIGN KEY (sender) REFERENCES accounts(id)
)ENGINE=InnoDB CHARSET=utf8;

ALTER TABLE conversations ADD CONSTRAINT FOREIGN KEY (current_message_id) REFERENCES messages(id)