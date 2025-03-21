SQL> SELECT DBMS_METADATA.GET_DDL('TABLE', 'DB') FROM dual;

DBMS_METADATA.GET_DDL('TABLE','DB')                                             
--------------------------------------------------------------------------------
                                                                                
  CREATE TABLE "SYSTEM"."DB"                                                    
   (	"NAME" VARCHAR2(20),                                                       
	"PASSWORD" VARCHAR2(2                                                          
                                                                                

SQL> SELECT DBMS_METADATA.GET_DDL('TABLE', 'QUESTIONS') FROM dual;

DBMS_METADATA.GET_DDL('TABLE','QUESTIONS')                                      
--------------------------------------------------------------------------------
                                                                                
  CREATE TABLE "SYSTEM"."QUESTIONS"                                             
   (	"QUESTION_ID" NUMBER(*,0),                                                 
	"QUESTIO                                                                       
                                                                                

SQL> SPOOL OFF;
