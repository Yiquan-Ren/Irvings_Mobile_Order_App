-- ==============================================
-- H2 Database Schema for Irving's Order App
-- Converted from Supabase migration files
-- ==============================================

-- 1. 用户资料表（兼容Supabase结构，移除Auth依赖）
CREATE TABLE IF NOT EXISTS profiles (
  id UUID PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  full_name VARCHAR(255) NOT NULL DEFAULT '',
  birthday DATE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 2. 菜单表
CREATE TABLE IF NOT EXISTS menu_items (
  item_id VARCHAR(50) PRIMARY KEY,
  category VARCHAR(100) NOT NULL,
  name VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  base_price NUMERIC(10, 2) NOT NULL,
  price_label VARCHAR(100),
  available BOOLEAN NOT NULL DEFAULT TRUE,
  customization_options JSON NOT NULL DEFAULT '[]',
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 3. 积分奖励表
CREATE TABLE IF NOT EXISTS rewards (
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  points_required INTEGER NOT NULL CHECK (points_required >= 0),
  description TEXT NOT NULL,
  cart_item_id VARCHAR(50) NOT NULL UNIQUE,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 4. 购物车表
CREATE TABLE IF NOT EXISTS cart_items (
  id UUID PRIMARY KEY DEFAULT random_uuid(),
  user_id UUID NOT NULL,
  item_id VARCHAR(50) NOT NULL,
  item_type VARCHAR(20) NOT NULL CHECK (item_type IN ('menu', 'reward')),
  selections JSON NOT NULL DEFAULT '[]',
  quantity INTEGER NOT NULL DEFAULT 1 CHECK (quantity > 0),
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 索引（提升查询性能）
CREATE INDEX IF NOT EXISTS cart_items_user_id_idx ON cart_items(user_id);
CREATE INDEX IF NOT EXISTS cart_items_item_type_idx ON cart_items(item_type);