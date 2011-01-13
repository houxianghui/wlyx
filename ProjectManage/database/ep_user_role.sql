USE djkwgdb;

CREATE TABLE ep_user_role (
		USER_ID CHAR(8)  NOT NULL,
		ROLE_ID CHAR(8)  NOT NULL
	)
LOCK datarows 
WITH max_rows_per_page=0, exp_row_size=1, reservepagegap=0, identity_gap=0 
ON 'default' 
;

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_user_role','table only',MRU,'ON';

EXEC sp_cachestrategy 'djkwgdb','dbo.ep_user_role','table only',PREFETCH,'ON';

