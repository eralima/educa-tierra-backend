CREATE TABLE `produto` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` varchar(255) NOT NULL,
	`descricao` varchar(255) NOT NULL,
	`condicao` BOOLEAN NOT NULL,
	`unidade` INT NOT NULL,
	`departamento_id` INT NOT NULL,
	`usuario_id` INT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `categoria` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`departamento` varchar(255) NOT NULL,
	`status` BOOLEAN NOT NULL,
	`descricao` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `usuario` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nomeCompleto` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`senha` varchar(255) NOT NULL,
	`cep` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `produto` ADD CONSTRAINT `produto_fk0` FOREIGN KEY (`departamento_id`) REFERENCES `categoria`(`id`);

ALTER TABLE `produto` ADD CONSTRAINT `produto_fk1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario`(`id`);

