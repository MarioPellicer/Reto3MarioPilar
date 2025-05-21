CREATE DATABASE  IF NOT EXISTS `tienda` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tienda`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: tienda
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Camisetas'),(2,'Pantalones'),(3,'Chaquetas'),(4,'Zapatos'),(5,'Sudaderas'),(6,'Accesorios'),(7,'Ropa Deportiva'),(8,'sudaderas');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `direccion` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `codigo` int(11) NOT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Carlos Perez','Av. Libertad 123, Madrid',10),(2,'Laura Gomez','Calle Falsa 456, Barcelona',20),(3,'Ana Torres','Paseo del Sol 789, Valencia',30),(4,'Miguel Sanchez','Gran Via 101, Bilbao',15),(5,'Isabel Ruiz','Ronda Sur 12, Sevilla',25),(6,'Jorge Lopez','Av. de la Paz 88, Zaragoza',35),(7,'Lucia Morales','Calle Luna 45, Malaga',40),(8,'Raul Castro','Camino Real 11, Valladolid',45),(9,'Marta Diaz','Calle Norte 67, CoruiÂ±a',50),(10,'pedro fernandez','palaza torres 29',72),(11,'mario','tenor fleta 57',30);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidoproducto`
--

DROP TABLE IF EXISTS `pedidoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidoproducto` (
  `idpedidoproducto` int(11) NOT NULL AUTO_INCREMENT,
  `idpedido` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `unidades` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idpedidoproducto`),
  KEY `idpedido` (`idpedido`),
  KEY `idproducto` (`idproducto`),
  CONSTRAINT `pedidoproducto_ibfk_1` FOREIGN KEY (`idpedido`) REFERENCES `pedidos` (`idpedido`),
  CONSTRAINT `pedidoproducto_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `productos` (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidoproducto`
--

LOCK TABLES `pedidoproducto` WRITE;
/*!40000 ALTER TABLE `pedidoproducto` DISABLE KEYS */;
INSERT INTO `pedidoproducto` VALUES (1,1,80,4,47.76),(2,1,5,2,2.60),(3,2,13,3,9.21),(4,2,73,4,14.64),(5,3,80,6,50.36),(6,4,19,5,50.36),(7,4,64,4,50.36),(8,5,73,4,14.64),(9,5,5,4,50.36),(10,6,19,4,50.36),(11,7,5,4,50.36);
/*!40000 ALTER TABLE `pedidoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos`
--

DROP TABLE IF EXISTS `pedidos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos` (
  `idpedido` int(11) NOT NULL AUTO_INCREMENT,
  `idcliente` int(11) NOT NULL,
  `precioTotal` decimal(10,2) NOT NULL,
  `direccionEnvio` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fecha` date NOT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `idcliente` (`idcliente`),
  CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos`
--

LOCK TABLES `pedidos` WRITE;
/*!40000 ALTER TABLE `pedidos` DISABLE KEYS */;
INSERT INTO `pedidos` VALUES (1,2,300.00,'hola','2025-05-13'),(2,5,52.76,'calle2','2025-05-11'),(3,2,20.49,'calle4','2025-05-25'),(4,9,47.21,'calle7','2025-12-04'),(5,5,12.65,'calle2','2025-06-17'),(6,7,9.25,'calle6','2025-01-23'),(7,1,50.04,'calle1','2025-05-04');
/*!40000 ALTER TABLE `pedidos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `idcategoria` int(11) DEFAULT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `descripcion` text,
  `color` varchar(50) DEFAULT NULL,
  `talla` varchar(10) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `idcategoria` (`idcategoria`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categorias` (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,1,'Camiseta blanca basica',9.99,'Camiseta algodon 100%','Blanco','M',50),(2,1,'Camiseta negra estampada',12.99,'DiseiÂ±o moderno','Negro','L',40),(3,1,'Camiseta azul con bolsillo',11.99,'Detalle en pecho','Azul','S',35),(4,1,'Camiseta gris oversize',13.49,'Estilo urbano','Gris','XL',45),(5,1,'Camiseta verde lisa',10.99,'Color solido','Verde','M',20),(6,1,'Camiseta rayas rojas',14.49,'Estilo marinero','Rojo','L',30),(7,1,'Camiseta manga larga negra',15.99,'Algodon suave','Negro','M',25),(8,1,'Camiseta tie-dye',16.99,'Multicolor','Variado','L',18),(9,1,'Camiseta deportiva blanca',13.49,'Secado rapido','Blanco','M',28),(10,1,'Camiseta con texto',12.49,'Tipografia moderna','Beige','S',22),(11,2,'Pantalon vaquero slim',29.99,'Denim ajustado','Azul','32',30),(12,2,'Pantalon chino beige',34.99,'Estilo casual','Beige','34',25),(13,2,'Jogger gris',24.99,'Con puiÂ±os elasticos','Gris','M',40),(14,2,'Pantalon cargo verde',39.99,'Bolsillos laterales','Verde','L',15),(15,2,'Pantalon vestir negro',44.99,'Elegante y formal','Negro','38',12),(16,2,'Pantalon corto deportivo',19.99,'Transpirable','Azul','M',30),(17,2,'Pantalon termico',22.99,'Invierno','Negro','L',10),(18,2,'Pantalon lino blanco',34.49,'Fresco y comodo','Blanco','M',14),(19,2,'Pantalon acampanado',27.99,'Estilo retro','Marron','S',20),(20,2,'Pantalon palazzo',32.49,'Pernera ancha','Gris','M',16),(21,3,'Chaqueta vaquera',49.99,'Denim azul','Azul','M',20),(22,3,'Chaqueta bomber',59.99,'Estilo urbano','Negro','L',18),(23,3,'Chaqueta impermeable',64.99,'Ideal lluvia','Gris','M',22),(24,3,'Chaqueta acolchada',69.99,'Invierno calido','Rojo','L',15),(25,3,'Chaqueta con capucha',54.99,'Cierre frontal','Azul','M',12),(26,3,'Chaqueta piel sintetica',79.99,'Estilo motero','Negro','L',10),(27,3,'Chaqueta camuflaje',57.99,'Estilo militar','Verde','XL',8),(28,3,'Chaqueta blazer gris',89.99,'Formal','Gris','M',5),(29,3,'Chaqueta polar',44.99,'Interior suave','Beige','M',25),(30,3,'Chaqueta deportiva ligera',39.99,'Running','Azul','S',18),(31,4,'Zapatillas deportivas',59.99,'Running ligera','Gris','42',20),(32,4,'Botines cuero marron',89.99,'Estilo casual','Marron','43',10),(33,4,'Zapatos vestir negros',69.99,'Elegante clasico','Negro','42',12),(34,4,'Zapatillas lona blanca',34.99,'Casual urbana','Blanco','41',25),(35,4,'Sandalias tiras',24.99,'Verano','Beige','39',18),(36,4,'Zapatillas plataforma',44.99,'Tendencia','Negro','40',15),(37,4,'Botas montaiÂ±a',74.99,'Agarre fuerte','Marron','44',8),(38,4,'Mocasines piel',64.99,'Trabajo','Negro','42',6),(39,4,'Zapatillas deportivas rojas',58.99,'Running','Rojo','43',10),(40,4,'Chanclas basicas',9.99,'Playa','Azul','41',30),(41,5,'Sudadera capucha roja',39.99,'Interior afelpado','Rojo','L',45),(42,5,'Sudadera gris basica',29.99,'Algodon','Gris','M',60),(43,5,'Sudadera con cremallera',34.99,'Frontal completa','Negro','L',40),(44,5,'Sudadera oversize beige',36.99,'Moda actual','Beige','XL',25),(45,5,'Sudadera tie-dye',41.99,'Colorido','Multicolor','M',15),(46,5,'Sudadera sin capucha',28.99,'Cuello redondo','Verde','S',20),(47,5,'Sudadera estampada',33.49,'DiseiÂ±o grafico','Negro','M',10),(48,5,'Sudadera deportiva',37.49,'Secado rapido','Azul','L',18),(49,5,'Sudadera con texto',32.99,'Frase positiva','Rosa','M',30),(50,5,'Sudadera basica blanca',27.99,'Para diario','Blanco','S',22),(51,6,'Gorra ajustable negra',14.99,'Visera curva','Negro','unica',25),(52,6,'Cinturon cuero marron',19.99,'Hebilla metalica','Marron','L',18),(53,6,'Bufanda lana gris',17.99,'Invierno calido','Gris','unica',20),(54,6,'Mochila urbana',49.99,'Compartimentos multiples','Negro','unica',12),(55,6,'Bolso bandolera',39.99,'Estilo casual','Beige','unica',16),(56,6,'Gafas sol polarizadas',24.99,'Proteccion UV','Negro','unica',30),(57,6,'Calcetines pack 3',9.99,'Colores neutros','Variado','M',35),(58,6,'Pulsera cuero',12.99,'Ajustable','Marron','unica',10),(59,6,'Reloj deportivo',59.99,'Sumergible','Negro','unica',8),(60,6,'Cartera piel',22.99,'Compacta','Negro','unica',14),(61,7,'Camiseta deportiva tecnica',17.99,'Transpirable','Azul','M',25),(62,7,'Leggins compresion',24.99,'Ajuste perfecto','Negro','S',20),(63,7,'Pantalon running',29.99,'Ligero','Gris','M',18),(64,7,'Top deportivo',21.99,'Soporte medio','Rosa','M',22),(65,7,'Sudadera running',35.99,'Con reflectantes','Verde','L',12),(66,7,'Chaqueta deportiva corta',44.99,'Cremallera frontal','Negro','S',10),(67,7,'Zapatillas crossfit',69.99,'Alta resistencia','Rojo','42',14),(68,7,'Guantes gimnasio',14.99,'Antideslizantes','Negro','M',16),(69,7,'Gorra deportiva',12.99,'Secado rapido','Blanco','unica',30),(70,7,'Calcetines deportivos',9.99,'Alto rendimiento','Negro','L',25),(71,1,'Camiseta slim fit azul',11.49,'Corte entallado','Azul','M',30),(72,1,'Camiseta basica rosa',10.99,'Colores pastel','Rosa','S',24),(73,2,'Pantalon corto lino',22.99,'Verano comodo','Beige','M',15),(74,2,'Jeans rectos vintage',34.99,'Estilo clasico','Azul','34',10),(75,3,'Cazadora denim clara',49.99,'Verano','Azul claro','M',12),(76,3,'Parka invierno',89.99,'Acolchada','Verde','L',8),(77,4,'Sandalias cuero planas',29.99,'Verano','Marron','39',18),(78,5,'Hoodie con diseiÂ±o',42.99,'Grafico frontal','Negro','L',15),(79,6,'Bolso tote',34.99,'Gran capacidad','Gris','unica',20),(80,7,'Pantalon yoga',27.99,'Flexible y suave','Negro','M',16),(81,1,'Camiseta basica amarilla',9.99,'Color vibrante','Amarillo','M',14),(82,2,'Pantalon sastre',44.99,'Formal','Negro','40',8),(83,3,'Chaqueta casual',59.99,'Uso diario','Beige','M',10),(84,4,'Zapatillas urbanas',54.99,'Moda joven','Blanco','43',12),(85,5,'Sudadera estampado floral',39.99,'Colorido','Multicolor','S',20),(86,6,'Gorra plana',16.99,'Estilo rap','Negro','unica',15),(87,7,'Chaleco deportivo',34.99,'Sin mangas','Azul','M',12),(88,1,'Camiseta larga',13.49,'Hasta la cadera','Gris','L',22),(89,2,'Jogger termico',28.99,'Invierno','Gris oscuro','L',18),(90,3,'Cazadora acolchada corta',64.99,'Estilo juvenil','Rojo','M',9),(91,4,'Zapatillas trekking',74.99,'MontaiÂ±a','Gris','42',11),(92,5,'Sudadera cropped',33.99,'Juvenil','Negro','S',14),(93,6,'Bandolera pequeiÂ±a',27.99,'Compacta','Marron','unica',20),(94,7,'Mallas compresion alta',31.99,'Entrenamiento','Negro','S',15),(95,1,'Camiseta basica gris',9.49,'Uso diario','Gris','M',18),(96,2,'Pantalon cargo corto',25.99,'Bolsillos multiples','Verde','M',12),(97,3,'Chaqueta media estacion',52.99,'Primavera','Azul','L',10),(98,4,'Zapatillas chunky',64.99,'Estilo urbano','Blanco','40',13),(99,5,'Sudadera college',44.99,'Estilo varsity','Azul marino','M',9),(100,7,'Camiseta entrenamiento sin mangas',18.99,'Alta ventilacion','Negro','L',20);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tienda'
--
/*!50003 DROP PROCEDURE IF EXISTS `buscarProducto` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscarProducto`(IN _nom varchar(50), IN _talla varchar(10), IN _color varchar(50))
BEGIN 
	declare where_clause varchar(100) default 'where';
	IF _nom <> "" THEN 
		set where_clause = concat(where_clause, ' nombre = "', _nom, '"');
	END IF; 
    IF _talla <> "" THEN 
		IF where_clause <> 'where' THEN 
			set where_clause = concat(where_clause, ' and ');
		END IF; 
		set where_clause = concat(where_clause, ' talla = "', _talla, '"');
	END IF; 
    IF _color <> "" THEN 
		IF where_clause <> 'where' THEN 
			set where_clause = concat(where_clause, ' and ');
	END IF; 
		set where_clause = concat(where_clause, ' color = "', _color, '"');
	END IF; 
	IF where_clause = 'where' THEN 
		set @sentencia_sql = 'SELECT idProducto, idCategoria, nombre, precio, descripcion, color, talla, stock FROM productos'; 
	ELSE 
		set @sentencia_sql = concat('SELECT idProducto, idCategoria, nombre, precio, descripcion, color, talla, stock FROM productos ', where_clause); 
	END IF; 
	prepare instruccion from @sentencia_sql; 
	execute instruccion; 
deallocate prepare instruccion; 
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-21 11:44:32
