-- 插入测试用户（same with existing test data）
INSERT INTO customer_account (customer_id, full_name, email, phone, active) 
VALUES ('CUS-TEST001', 'Test User', 'test@psu.edu', '555-1234', TRUE);

-- 插入测试员工
INSERT INTO staff_account (staff_id, name, email, role, active) 
VALUES ('STF-TEST001', 'Staff User', 'staff@irvings.com', 'MANAGER', TRUE);

-- 插入测试菜单（same as existing test data）
INSERT INTO menu_item (item_id, name, base_price, available) VALUES
('BAGEL-PLAIN', 'Plain Bagel', 1.75, TRUE),
('BAGEL-EVERYTHING', 'Everything Bagel', 1.95, TRUE),
('DRINK-COLDBREW', 'Cold Brew', 3.25, TRUE),
('BAGEL-SOLDOUT', 'Limited Bagel (Sold Out)', 2.25, FALSE);