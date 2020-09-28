/*
SQLyog Ultimate v9.02 
MySQL - 8.0.18 : Database - sga_2020
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sga_2020` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `sga_2020`;

/*Table structure for table `alumno` */

DROP TABLE IF EXISTS `alumno`;

CREATE TABLE `alumno` (
  `alu_dni` bigint(10) unsigned NOT NULL,
  `alu_nombre` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alu_apellido` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `alu_fec_nac` date DEFAULT NULL,
  `alu_domicilio` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `alu_telefono` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `alu_insc_cod` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`alu_dni`),
  KEY `FK_Alumno_Inscripcion` (`alu_insc_cod`),
  CONSTRAINT `FK_Alumno_Inscripcion` FOREIGN KEY (`alu_insc_cod`) REFERENCES `inscripcion` (`insc_cod`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

/*Data for the table `alumno` */

insert  into `alumno`(`alu_dni`,`alu_nombre`,`alu_apellido`,`alu_fec_nac`,`alu_domicilio`,`alu_telefono`,`alu_insc_cod`) values (17252307,'Mónica','Guitart','1985-05-18','Posta del Retamo 1474','+5492615602089',1),(30568974,'Fernando','Solis','1990-02-12','Juan b justo 412','+5492613543236',2),(35689766,'Matias','Gonzalez','2020-05-12','calle 123','+58964712',1),(36766878,'Martin','Silva','1992-03-05','Calle Falsa 123','2615235689',1),(38766878,'asd','asd','1999-09-09','','',NULL);

/*Table structure for table `carrera` */

DROP TABLE IF EXISTS `carrera`;

CREATE TABLE `carrera` (
  `car_cod` bigint(10) unsigned NOT NULL,
  `car_nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `car_duracion` bigint(10) NOT NULL,
  PRIMARY KEY (`car_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

/*Data for the table `carrera` */

insert  into `carrera`(`car_cod`,`car_nombre`,`car_duracion`) values (1,'Programacion',2),(2,'Ingenieria en Sistemas',5),(3,'Abogacía',5);

/*Table structure for table `cursado` */

DROP TABLE IF EXISTS `cursado`;

CREATE TABLE `cursado` (
  `cur_alu_dni` bigint(10) unsigned NOT NULL,
  `cur_mat_cod` bigint(10) unsigned NOT NULL,
  `cur_nota` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`cur_alu_dni`,`cur_mat_cod`),
  KEY `FK_cursado_materia` (`cur_mat_cod`),
  CONSTRAINT `FK_cursado_alumno` FOREIGN KEY (`cur_alu_dni`) REFERENCES `alumno` (`alu_dni`) ON DELETE CASCADE,
  CONSTRAINT `FK_cursado_materia` FOREIGN KEY (`cur_mat_cod`) REFERENCES `materia` (`mat_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

/*Data for the table `cursado` */

insert  into `cursado`(`cur_alu_dni`,`cur_mat_cod`,`cur_nota`) values (30568974,1,4),(30568974,2,6),(36766878,1,8),(36766878,2,9);

/*Table structure for table `inscripcion` */

DROP TABLE IF EXISTS `inscripcion`;

CREATE TABLE `inscripcion` (
  `insc_cod` bigint(10) NOT NULL AUTO_INCREMENT,
  `insc_nombre` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `insc_fecha` date NOT NULL,
  `insc_car_cod` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`insc_cod`),
  KEY `FK_inscripcion_carrera` (`insc_car_cod`),
  CONSTRAINT `FK_inscripcion_carrera` FOREIGN KEY (`insc_car_cod`) REFERENCES `carrera` (`car_cod`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

/*Data for the table `inscripcion` */

insert  into `inscripcion`(`insc_cod`,`insc_nombre`,`insc_fecha`,`insc_car_cod`) values (1,'aasd','1998-03-25',1),(2,'insc2','2000-05-03',2),(3,'nom','1990-03-23',3);

/*Table structure for table `materia` */

DROP TABLE IF EXISTS `materia`;

CREATE TABLE `materia` (
  `mat_cod` bigint(10) unsigned NOT NULL,
  `mat_nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `mat_prof_dni` bigint(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`mat_cod`),
  KEY `FK_materia_profesor` (`mat_prof_dni`),
  CONSTRAINT `FK_materia_profesor` FOREIGN KEY (`mat_prof_dni`) REFERENCES `profesor` (`prof_dni`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

/*Data for the table `materia` */

insert  into `materia`(`mat_cod`,`mat_nombre`,`mat_prof_dni`) values (1,'Sistemas Operativos',14628514),(2,'Estadística',17252307);

/*Table structure for table `profesor` */

DROP TABLE IF EXISTS `profesor`;

CREATE TABLE `profesor` (
  `prof_dni` bigint(10) unsigned NOT NULL,
  `prof_nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prof_apellido` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `prof_fec_nac` date DEFAULT NULL,
  `prof_domicilio` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `prof_telefono` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`prof_dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

/*Data for the table `profesor` */

insert  into `profesor`(`prof_dni`,`prof_nombre`,`prof_apellido`,`prof_fec_nac`,`prof_domicilio`,`prof_telefono`) values (14628514,'Omar','Silva','1961-08-10','Joaquin V Gonzalez 4542','+24912345'),(17252307,'Mónica','Guitart','1985-05-18','',''),(18236789,'Mariela','Suarez','1965-01-03','Luzuriaga 23','+5492613543154');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
