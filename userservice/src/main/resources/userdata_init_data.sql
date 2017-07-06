USE userdata;

INSERT INTO t_user ( userName, passWord, rightLevel ) VALUES ( 'linxiwd_guest', 'linxiwd', 1 );
INSERT INTO t_user ( userName, passWord, rightLevel ) VALUES ( 'linxiwd_employee', 'linxiwd', 2 );
INSERT INTO t_user ( userName, passWord, rightLevel ) VALUES ( 'linxiwd_boss', 'linxiwd', 3 );

INSERT INTO t_right ( rightLevel, rightName, description ) VALUES ( 1, '游客权限', '包含少量权限' );
INSERT INTO t_right ( rightLevel, rightName, description ) VALUES ( 2, '员工权限', '包含部分权限' );
INSERT INTO t_right ( rightLevel, rightName, description ) VALUES ( 3, '主管权限', '包含所有权限' );