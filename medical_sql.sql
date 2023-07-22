Drop database if exists his_registrationsystem;
create database his_registrationsystem;
use his_registrationsystem;

# 病人
drop table if exists patients;
create table patients(
	`id` varchar(18) primary key,
    `password` varchar(16) NOT NULL,
	`name` varchar(16),
    `age` int,
    `sex` boolean,
    `phone` varchar(11)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 部门
drop table if exists department;
CREATE TABLE `department` (
  `name` varchar(20),
  `introduction` varchar(200),
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

insert into department
values('妇产科', '妇产科是临床医学四大主要学科之一，主要研究女性生殖器官疾病的病因、病理、诊断及防治，妊娠、分娩的生理和病理变化，高危妊娠及难产的预防和诊治，女性生殖内分泌，计划生育及妇女保健等。'),
	('儿科', '儿科（或称小儿科）是现代医学其中一个分支,研究儿童的发育、护理和疾病,专门医疗患病的婴儿、儿童及青少年。最大的年龄限制介乎十四至二十一岁之间，而每个国家都有不同的界定。一个受到这方面知识专门训练的医生也叫做儿科医生。'),
	('骨科', '骨科是各大医院最常见的科室之一，主要研究骨骼肌肉系统的解剖、生理与病理，运用药物、手术及物理方法保持和发展这一系统的正常形态与功能。'),
	('眼科', '眼科的全称是“眼病专科”，是研究发生在视觉系统，包括眼球及与其相关联的组织有关疾病的学科。眼科一般研究玻璃体、视网膜疾病，眼视光学，青光眼和视神经病变，白内障等多种眼科疾病。'),
	('皮肤科', '皮肤科属于外科，主要治疗各种皮肤病，常见皮肤病有牛皮癣 、 疱疹 、酒渣鼻 、脓疱疮 、化脓菌感染 、疤痕 、癣 、鱼鳞病 、腋臭 、青春痘 、毛囊炎 、斑秃脱发 、男科炎症 、婴儿尿布疹 、鸡眼 、雀斑 、汗疱疹 、口腔部护理 、脱毛 、黄褐斑等。'),
	('泌尿科', '泌尿科是专门研究男女泌尿道与男性生殖系统的一门医学，它主要是从外科细分而来。男性的泌尿与生殖系统密不可分，而女性的泌尿道则开口于外阴部。'),
	('外科', '外科是研究外科疾病的发生、发展规律及其临床表现，诊断、预防和治疗的科学，是以手术切除、修补为主要治病手段的专业科室。'),
	('内科', '内科学是临床医学的一个专科，几乎是所有其他临床医学的基础，亦有医学之母之称。'),
	('麻醉科', '麻醉学科是一个综合性的学科，它包含多学科的知识。范围很广，不单单是满足手术的要求，还参入各科室的抢救工作，妇科的无痛分娩，无痛流产等等。');

# 医生
drop table if exists doctors;  
CREATE TABLE `doctors` (
  `id` varchar(18) NOT NULL,
  `password` varchar(16) NOT NULL,
  `name` varchar(16) DEFAULT NULL,
  `age` int,
  `dept` varchar(20) ,
  `sex` tinyint DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `fee` decimal(10, 2) NOT NULL,
  `introduction` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `dept` FOREIGN KEY (`dept`) REFERENCES `department` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 病例
drop table if exists case_history;
CREATE TABLE `case_history` (
  `patientNumber` int NOT NULL,
  `Time` datetime DEFAULT NULL,
  `patientWord` varchar(100) DEFAULT NULL,
  `dealAdvice` varchar(100) DEFAULT NULL,
  `medicalResult` varchar(100) DEFAULT NULL,
  `doctorId` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`patientNumber`),
  KEY `doctorId_idx` (`doctorId`),
  CONSTRAINT `doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 处方
drop table if exists prescription;
CREATE TABLE `prescription` (
  `patientNumber` int NOT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `feeStatus` boolean DEFAULT NULL,
  `doctorId` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`patientNumber`),
  KEY `doctorId_idx` (`doctorId`),
  CONSTRAINT `P_doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 接诊记录
drop table if exists medical_record;
CREATE TABLE `medical_record` (
  `patientId` varchar(18) NOT NULL,
  `doctorId` varchar(18) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`patientId`,`doctorId`,`Time`),
  KEY `Rec_doctorId_idx` (`doctorId`),
  CONSTRAINT `Rec_doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`),
  CONSTRAINT `Rec_Patient_ID` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 挂号记录
drop table if exists registration;
CREATE TABLE `registration` (
  `patientNumber` int  AUTO_INCREMENT,
  `registTime` datetime NOT NULL,
  `reserveTime` date NOT NULL,
  `timeRange` boolean NOT NULL,
  `medicalStatus` int DEFAULT NULL,
  `registDept` varchar(20) DEFAULT NULL,
  `doctorId` varchar(18) NOT NULL,
  `patientId` varchar(18) NOT NULL,
  PRIMARY KEY (`patientNumber`),
  KEY `Reg_Dept_idx` (`registDept`),
  CONSTRAINT `registDept` FOREIGN KEY (`registDept`) REFERENCES `department` (`name`),
  CONSTRAINT `Reg_doctorId` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`id`),
  CONSTRAINT `Reg_Patient_ID` FOREIGN KEY (`patientId`) REFERENCES `patients` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


