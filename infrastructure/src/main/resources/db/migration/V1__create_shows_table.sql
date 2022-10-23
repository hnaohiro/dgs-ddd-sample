CREATE TABLE shows
(
    `id`           BIGINT NOT NULL AUTO_INCREMENT,
    `title`        VARCHAR(100),
    `release_year` INT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
