USE djkwgdb;

CREATE TABLE ep_mdic (
		SYS_ID NUMERIC(12 , 0)  NOT NULL,
		TYPE_ID CHAR(4)  NOT NULL,
		PARENT_ID NUMERIC(12 , 0)  NOT NULL,
		ITEM_ID VARCHAR(12)  NOT NULL,
		ITEM_VAL VARCHAR(40)  NOT NULL,
		LIST_ORDER SMALLINT  NOT NULL,
		ITEM_LEVEL SMALLINT  NOT NULL,
		LOGIC_ID VARCHAR(12)  NULL,
		STATUS CHAR(1)  NOT NULL
	)
LOCK datarows 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_mdic','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_mdic','table only',PREFETCH,'ON';

CREATE TABLE ep_menu (
		MENU_ID CHAR(8)  NOT NULL,
		PARENT_ID CHAR(8)  NOT NULL,
		MENU_NAME VARCHAR(40)  NOT NULL,
		MENU_MARK CHAR(1)  NOT NULL,
		MENU_LEVEL SMALLINT  NOT NULL,
		LIST_ORDER SMALLINT  NOT NULL,
		MENU_URL VARCHAR(100)  NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_menu','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_menu','table only',PREFETCH,'ON';

CREATE TABLE ep_op_def (
		OP_CODE CHAR(20)  NOT NULL,
		MENU_ID CHAR(8)  NOT NULL,
		CAPTION VARCHAR(40)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_op_def','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_op_def','table only',PREFETCH,'ON';

CREATE TABLE ep_pmkey (
		TB_NAME VARCHAR(20)  NOT NULL,
		KEY_FIELD VARCHAR(20)  NOT NULL,
		STEP_LEN INT  NOT NULL,
		MAX_VAL NUMERIC(20 , 0)  NOT NULL
	)
LOCK datarows 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_pmkey','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_pmkey','table only',PREFETCH,'ON';

CREATE TABLE ep_redef_mdic (
		TYPE_ID CHAR(4)  NOT NULL,
		CAPTION VARCHAR(20)  NOT NULL,
		STMT VARCHAR(400)  NOT NULL,
		MEMO VARCHAR(80)  NULL,
		REG_DT CHAR(8)  NOT NULL,
		USER_ID CHAR(8)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_redef_mdic','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_redef_mdic','table only',PREFETCH,'ON';

CREATE TABLE ep_redef_sdic (
		TYPE_ID CHAR(4)  NOT NULL,
		CAPTION VARCHAR(30)  NOT NULL,
		STMT VARCHAR(400)  NOT NULL,
		MEMO VARCHAR(80)  NULL,
		REG_DT CHAR(8)  NOT NULL,
		USER_ID CHAR(8)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_redef_sdic','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_redef_sdic','table only',PREFETCH,'ON';

CREATE TABLE ep_role_auth (
		ROLE_ID CHAR(8)  NOT NULL,
		OP_CODE CHAR(20)  NOT NULL
	)
LOCK datarows 
WITH max_rows_per_page=0, exp_row_size=1, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_role_auth','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_role_auth','table only',PREFETCH,'ON';

CREATE TABLE ep_role_menu (
		ROLE_ID CHAR(8)  NOT NULL,
		MENU_ID CHAR(8)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_role_menu','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_role_menu','table only',PREFETCH,'ON';

CREATE TABLE ep_role (
		ROLE_ID CHAR(8)  NOT NULL,
		ROLE_NAME VARCHAR(40)  NOT NULL,
		LOGIC_ID CHAR(8)  NULL,
		AMSD_STORE CHAR(9)  NULL,
		TEMPL_ID INT  NOT NULL,
		SESN_TIME INT  NOT NULL,
		ROLE_LEVEL VARCHAR(12)  NOT NULL,
		STATUS CHAR(1)  NOT NULL,
		USER_ID CHAR(8)  NOT NULL,
		REG_DT CHAR(8)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_role','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_role','table only',PREFETCH,'ON';

CREATE TABLE ep_sdic (
		SYS_ID NUMERIC(12 , 0)  NOT NULL,
		TYPE_ID CHAR(4)  NULL,
		ITEM_CODE VARCHAR(12)  NOT NULL,
		ITEM_VAL VARCHAR(40)  NOT NULL,
		LIST_ORDER SMALLINT  NOT NULL,
		LOGIC_ID VARCHAR(12)  NULL,
		STATUS CHAR(1)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_sdic','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_sdic','table only',PREFETCH,'ON';

CREATE TABLE ep_user_login (
		USER_ID CHAR(8)  NOT NULL,
		LOGIN_TIME CHAR(14)  NOT NULL,
		CLIENT_IP CHAR(15)  NOT NULL
	)
LOCK datarows 
WITH max_rows_per_page=0, exp_row_size=1, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_user_login','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_user_login','table only',PREFETCH,'ON';

