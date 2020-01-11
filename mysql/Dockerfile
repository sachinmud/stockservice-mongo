FROM mysql:8.0
COPY replication.cnf /etc/mysql/conf.d

ADD ./common-schema.sql /docker-entrypoint-initdb.d/

RUN chown -R mysql:mysql /docker-entrypoint-initdb.d/
