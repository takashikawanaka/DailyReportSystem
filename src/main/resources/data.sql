INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("煌木　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO employee(name, created_at, updated_at, delete_flag) VALUES ("田中　太郎", CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);
INSERT INTO authentication(code, password, role, valid_date, employee_id) VALUES ("ktaro", "$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S", "管理者", "9999-12-31", 1);
INSERT INTO authentication(code, password, role, valid_date, employee_id) VALUES ("ttaro", "$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56", "一般", "9999-12-31", 2);