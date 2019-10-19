CREATE TABLE `parkinglot` {
    `name` VARCHAR(255) NOT NULL,
    `capacity` BINARY(16) DEFAULT NULL,
    `location` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`name`)
};