DROP TABLE IF EXISTS COORDINATES;

CREATE TABLE COORDINATES (
    id INT AUTO_INCREMENT PRIMARY KEY,
    latitude VARCHAR(25) NOT NULL,
    longitude VARCHAR(25) NOT NULL
);