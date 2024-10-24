ALTER TABLE notifications
ADD from_user INT,
ADD to_user INT NOT NULL,
ADD CONSTRAINT FOREIGN KEY (from_user) REFERENCES accounts(id),
ADD CONSTRAINT FOREIGN KEY (to_user) REFERENCES accounts(id);

ALTER TABLE notifications
DROP CONSTRAINT notifications_ibfk_1;

ALTER TABLE notifications
MODIFY fromUser INT NOT NULL,
MODIFY message TEXT NOT NULL,
MODIFY sent_time DATETIME NOT NULL;

ALTER TABLE notifications
ADD type VARCHAR(50) NOT NULL

