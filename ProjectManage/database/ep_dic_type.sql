USE djkwgdb;

CREATE TABLE ep_dic_type (
		TYPE_ID CHAR(4)  NOT NULL,
		TYPE_NAME VARCHAR(30)  NOT NULL,
		DIC_LEVEL CHAR(1)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_dic_type','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_dic_type','table only',PREFETCH,'ON';

CREATE TABLE ep_error_code (
		ERROR_CODE CHAR(8)  NOT NULL,
		CAPTION VARCHAR(60)  NOT NULL
	)
LOCK allpages 
WITH max_rows_per_page=0, exp_row_size=0, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_error_code','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_error_code','table only',PREFETCH,'ON';

