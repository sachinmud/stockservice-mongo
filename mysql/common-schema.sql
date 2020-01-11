create database tradeservicedb;
use tradeservicedb;
CREATE USER 'tradeservice'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON tradeservicedb.* TO 'tradeservice'@'%';
FLUSH PRIVILEGES;

create database portfolioservicedb;
use portfolioservicedb;
CREATE USER 'portfolioservice'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON portfolioservicedb.* TO 'portfolioservice'@'%';
FLUSH PRIVILEGES;

create database userservicedb;
use userservicedb;
CREATE USER 'userservice'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON userservicedb.* TO 'userservice'@'%';
FLUSH PRIVILEGES;


