-- 顾客账号表
CREATE TABLE IF NOT EXISTS customer_account (
    customer_id VARCHAR(50) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    active BOOLEAN DEFAULT TRUE
);

-- 员工账号表
CREATE TABLE IF NOT EXISTS staff_account (
    staff_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(50),
    active BOOLEAN DEFAULT TRUE
);

-- 菜单表
CREATE TABLE IF NOT EXISTS menu_item (
    item_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    base_price DECIMAL(10, 2) NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

-- 订单表
CREATE TABLE IF NOT EXISTS orders (
    order_id VARCHAR(50) PRIMARY KEY,
    cart_id VARCHAR(50),
    payment_method_id VARCHAR(50),
    status VARCHAR(50),
    total DECIMAL(10, 2),
    created_at TIMESTAMP
);