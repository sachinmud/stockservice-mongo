CREATE TABLE IF NOT EXISTS permission(
  permissionid BIGINT NOT NULL,
  permission VARCHAR(100),
  PRIMARY KEY(permissionid)
);

CREATE TABLE IF NOT EXISTS role(
  roleid BIGINT NOT NULL,
  rolename VARCHAR(100),
  PRIMARY KEY(roleid)
);

CREATE TABLE IF NOT EXISTS rolepermissions(
  permissionid BIGINT NOT NULL,
  roleid BIGINT NOT NULL,
  PRIMARY KEY(permissionid, roleid)
);

CREATE TABLE IF NOT EXISTS user(
  userid BIGINT NOT NULL,
  username VARCHAR(100),
  fullname VARCHAR(250),
  password VARCHAR(250),
  enabled VARCHAR(10),
  PRIMARY KEY(userid)
);

CREATE TABLE IF NOT EXISTS userroles(
  userid BIGINT NOT NULL,
  roleid BIGINT NOT NULL,
  PRIMARY KEY(userid, roleid)
);

insert into permission(permissionid, permission) values (1, 'role:read');
insert into permission(permissionid, permission) values (2, 'user:delete');

insert into permission(permissionid, permission) values (3, 'user:write');
insert into permission(permissionid, permission) values (4, 'user:read');
insert into permission(permissionid, permission) values (5, 'role:modify');
insert into permission(permissionid, permission) values (6, 'role:delete');
insert into permission(permissionid, permission) values (7, 'permission:modify');
insert into permission(permissionid, permission) values (8, 'permission:delete');

insert into role(roleid, rolename) values(1, 'ROLE_ADMIN');
insert into role(roleid, rolename) values(2, 'ROLE_CLIENT');

insert into rolepermissions(permissionid, roleid) values (1, 1);
insert into rolepermissions(permissionid, roleid) values (2, 1);
insert into rolepermissions(permissionid, roleid) values (3, 1);
insert into rolepermissions(permissionid, roleid) values (4, 1);
insert into rolepermissions(permissionid, roleid) values (5, 1);
insert into rolepermissions(permissionid, roleid) values (6, 1);
insert into rolepermissions(permissionid, roleid) values (7, 1);
insert into rolepermissions(permissionid, roleid) values (8, 1);
insert into rolepermissions(permissionid, roleid) values (1, 2);
insert into rolepermissions(permissionid, roleid) values (4, 2);

insert into user(userid, fullname, password, username, enabled) values (1, 'Admin User', '$2a$10$qMEHdyyjNcNoT725gR9ovuAiEiZpdEntbNf.sYik5WfMFNlH3wGpG', 'admin', 'true');
insert into user(userid, fullname, password, username, enabled) values (2, 'Client User', '$2a$10$YsJt517JFcWr9BvtNepuVOjni9vRza3DThVbtc5rvSfLSnLLbB5/.', 'user1', 'true');

insert into userroles(userid, roleid) values(1, 1);
insert into userroles(userid, roleid) values(2, 2);

commit;
