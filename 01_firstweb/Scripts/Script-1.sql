CREATE USER web IDENTIFIED BY web DEFAULT TABLESPACE USERS QUOTA UNLIMITED ON USERS;
ALTER SESSION SET"_ORACLE_SCRIPT"=TRUE;
GRANT CONNECT, RESOURCE TO web;