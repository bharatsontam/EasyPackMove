create database easypackmove;

use easypackmove;

CREATE TABLE `purchases` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `QuoteId` int(11) NOT NULL,
  `CardNumber` varchar(20) NOT NULL,
  `CardType` varchar(20) NOT NULL,
  `CVV` varchar(5) NOT NULL,
  `Expiration` varchar(6) NOT NULL,
  `IsSuccess` tinyint(1) DEFAULT NULL,
  `BillingAddress` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  KEY `QuoteId` (`QuoteId`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`QuoteId`) REFERENCES `quotes` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


CREATE TABLE `quotes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `StartAddress` varchar(256) NOT NULL,
  `EndAddress` varchar(256) NOT NULL,
  `QuoteDetails` varchar(1024) NOT NULL,
  `QuotePrice` decimal(10,2) NOT NULL,
  `IsPurchased` tinyint(1) NOT NULL,
  `Distance` decimal(15,8) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  KEY `UserId` (`UserId`),
  CONSTRAINT `quotes_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(128) NOT NULL,
  `FirstName` varchar(128) NOT NULL,
  `LastName` varchar(128) DEFAULT NULL,
  `PhoneNumber` varchar(10) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Address1` varchar(128) NOT NULL,
  `Address2` varchar(128) DEFAULT NULL,
  `City` varchar(128) NOT NULL,
  `State` varchar(128) NOT NULL,
  `Zip` varchar(5) NOT NULL,
  `Password` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `Id` (`Id`),
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
