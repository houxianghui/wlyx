USE djkwgdb;

CREATE TABLE ep_user (
		USER_ID CHAR(8) primary key NOT NULL,
		DEPART_ID CHAR(8)  NULL,
		ROLE_ID CHAR(8)  NOT NULL,
		LOGIN_ID CHAR(20)  NOT NULL,
		USER_NAME CHAR(18)  NOT NULL,
		PASSWORD CHAR(32)  NOT NULL,
		PHONE VARCHAR(30)  NULL,
		MOBILE CHAR(11)  NULL,
		EMAIL VARCHAR(60)  NULL,
		POSTCODE CHAR(6)  NULL,
		ADDRESS VARCHAR(200)  NULL,
		STATUS CHAR(1)  NOT NULL,
		REG_DT CHAR(8)  NOT NULL,
		BEGIN_DT CHAR(8)  NOT NULL,
		INVALID_DT CHAR(8)  NOT NULL,
		MODIFY_DT CHAR(8)  NOT NULL,
		MEMO CHAR(200)  NULL,
		ST_CHG_DT CHAR(8)  NOT NULL,
		ADMIN_ID CHAR(18)  NOT NULL
	)
LOCK datarows 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_user','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_user','table only',PREFETCH,'ON';

