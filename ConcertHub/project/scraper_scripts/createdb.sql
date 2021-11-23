DROP DATABASE IF EXISTS `test`;
CREATE DATABASE `test`;
SET GLOBAL FOREIGN_KEY_CHECKS=0;
use `test`;
CREATE TABLE `performer` (
  `id` int(11) NOT NULL,
  `image` varchar(2083) NOT NULL,
  `score_performer` double NOT NULL,
  `genre_name` varchar(128) DEFAULT NULL,
  `name` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `genre` (
  `id` int(11) NOT NULL,
  `name` varchar(128) NOT NULL,
  `image` varchar(2083) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `concert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `concert_id` int(11) NOT NULL,
  `short_title` varchar(128) DEFAULT NULL,
  `venue` varchar(128) NOT NULL,
  `datetime_local` datetime NOT NULL,
  `score_concert` double NOT NULL,
  `popularity` double NOT NULL,
  `performer` varchar(128) NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `venue` (
  `id` int(11) NOT NULL,
  `venue_name` varchar(128) NOT NULL,
  `city` varchar(128) NOT NULL,
  `timezone` varchar(128) NOT NULL,
  `country` varchar(128) NOT NULL,
  `capacity` float NOT NULL,
  `score_venue` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `concertDetails` (
  `concert_id` int(11) NOT NULL,
  `remaining_seats` int(11) NOT NULL,
  `ticket_price` float(5,2) NOT NULL,
  PRIMARY KEY (`concert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


ALTER TABLE `performer`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index11` (`name`),
  ADD KEY `genre_name` (`genre_name`);

ALTER TABLE `genre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index3` (`name`);

ALTER TABLE `concert`
  ADD KEY `index2` (`performer`),
  ADD KEY `index4` (`performer`),
  ADD KEY `venue` (`venue`),
  ADD KEY `concert_id` (`concert_id`);

ALTER TABLE `venue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `index01` (`venue_name`);

ALTER TABLE `performer`
  ADD CONSTRAINT `performer_ibfk_1` FOREIGN KEY (`name`) REFERENCES `concert` (`performer`) ON DELETE NO ACTION,
  ADD CONSTRAINT `performer_ibfk_2` FOREIGN KEY (`genre_name`) REFERENCES `genre` (`name`);

ALTER TABLE `genre`
  ADD CONSTRAINT `genre_ibfk_1` FOREIGN KEY (`name`) REFERENCES `performer` (`genre_name`);

ALTER TABLE `concert`
  ADD CONSTRAINT `concert_ibfk_1` FOREIGN KEY (`performer`) REFERENCES `performer` (`name`) ON DELETE CASCADE,
  ADD CONSTRAINT `concert_ibfk_2` FOREIGN KEY (`venue`) REFERENCES `venue` (`venue_name`) ON DELETE CASCADE,
  ADD CONSTRAINT `concert_ibfk_3` FOREIGN KEY (`concert_id`) REFERENCES `concertDetails` (`concert_id`) ON DELETE CASCADE;

ALTER TABLE `venue`
  ADD CONSTRAINT `venue_ibfk_1` FOREIGN KEY (`venue_name`) REFERENCES `concert` (`venue`) ON DELETE NO ACTION;

CREATE TABLE IF NOT EXISTS `users` (
  `usersId` int(11) NOT NULL AUTO_INCREMENT,
  `usersName` varchar(128) NOT NULL,
  `usersEmail` varchar(128) NOT NULL,
  `usersUid` varchar(128) NOT NULL,
  `usersPassword` varchar(128) NOT NULL,
  PRIMARY KEY (usersId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
