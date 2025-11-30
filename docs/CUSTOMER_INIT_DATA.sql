-- =====================================================
-- INIT DATA CHO MODULE CUSTOMER
-- =====================================================

-- 1. Tạo bảng customers (nếu chưa có)
-- =====================================================
CREATE TABLE IF NOT EXISTS customers (
    user_id UUID PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    date_of_birth DATE,
    gender VARCHAR(10),
    address VARCHAR(500),
    city VARCHAR(100),
    membership_level VARCHAR(20) DEFAULT 'BRONZE',
    loyalty_points INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by UUID,
    updated_by UUID,
    is_delete BOOLEAN DEFAULT FALSE
);

-- Indexes
CREATE INDEX IF NOT EXISTS idx_customers_membership ON customers(membership_level);
CREATE INDEX IF NOT EXISTS idx_customers_city ON customers(city);
CREATE INDEX IF NOT EXISTS idx_customers_is_delete ON customers(is_delete);

-- 2. Tạo Customer mẫu
-- =====================================================

-- Customer 1: BRONZE level
INSERT INTO users (id, username, password, name, email, phone, lock_flag, system_flag, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000001'::uuid, 
     'customer_bronze', 
     '$2a$10$8K1p/a0dL2LH0XZnup/My.id5.ShmQmGe/vkiOoJ8VOwya/Qb9F5m', -- Customer@123
     'Nguyễn Văn Bronze', 
     'bronze@gmail.com', 
     '0911111111', 
     '0', 
     'NORMAL', 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_role (id, user_id, role_id)
SELECT gen_random_uuid(), '40000000-0000-0000-0000-000000000001'::uuid, id
FROM sys_role WHERE code = 'CUSTOMER'
ON CONFLICT DO NOTHING;

INSERT INTO customers (user_id, date_of_birth, gender, address, city, membership_level, loyalty_points, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000001'::uuid, 
     '1990-01-15', 
     'MALE', 
     '123 Nguyễn Huệ, Quận 1', 
     'Hồ Chí Minh', 
     'BRONZE', 
     500, 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (user_id) DO NOTHING;

-- Customer 2: SILVER level
INSERT INTO users (id, username, password, name, email, phone, lock_flag, system_flag, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000002'::uuid, 
     'customer_silver', 
     '$2a$10$8K1p/a0dL2LH0XZnup/My.id5.ShmQmGe/vkiOoJ8VOwya/Qb9F5m',
     'Trần Thị Silver', 
     'silver@gmail.com', 
     '0922222222', 
     '0', 
     'NORMAL', 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_role (id, user_id, role_id)
SELECT gen_random_uuid(), '40000000-0000-0000-0000-000000000002'::uuid, id
FROM sys_role WHERE code = 'CUSTOMER'
ON CONFLICT DO NOTHING;

INSERT INTO customers (user_id, date_of_birth, gender, address, city, membership_level, loyalty_points, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000002'::uuid, 
     '1992-05-20', 
     'FEMALE', 
     '456 Lê Lợi, Quận 3', 
     'Hồ Chí Minh', 
     'SILVER', 
     2500, 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (user_id) DO NOTHING;

-- Customer 3: GOLD level
INSERT INTO users (id, username, password, name, email, phone, lock_flag, system_flag, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000003'::uuid, 
     'customer_gold', 
     '$2a$10$8K1p/a0dL2LH0XZnup/My.id5.ShmQmGe/vkiOoJ8VOwya/Qb9F5m',
     'Lê Văn Gold', 
     'gold@gmail.com', 
     '0933333333', 
     '0', 
     'NORMAL', 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_role (id, user_id, role_id)
SELECT gen_random_uuid(), '40000000-0000-0000-0000-000000000003'::uuid, id
FROM sys_role WHERE code = 'CUSTOMER'
ON CONFLICT DO NOTHING;

INSERT INTO customers (user_id, date_of_birth, gender, address, city, membership_level, loyalty_points, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000003'::uuid, 
     '1988-08-10', 
     'MALE', 
     '789 Trần Hưng Đạo, Quận 5', 
     'Hồ Chí Minh', 
     'GOLD', 
     7800, 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (user_id) DO NOTHING;

-- Customer 4: PLATINUM level
INSERT INTO users (id, username, password, name, email, phone, lock_flag, system_flag, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000004'::uuid, 
     'customer_platinum', 
     '$2a$10$8K1p/a0dL2LH0XZnup/My.id5.ShmQmGe/vkiOoJ8VOwya/Qb9F5m',
     'Phạm Thị Platinum', 
     'platinum@gmail.com', 
     '0944444444', 
     '0', 
     'NORMAL', 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_role (id, user_id, role_id)
SELECT gen_random_uuid(), '40000000-0000-0000-0000-000000000004'::uuid, id
FROM sys_role WHERE code = 'CUSTOMER'
ON CONFLICT DO NOTHING;

INSERT INTO customers (user_id, date_of_birth, gender, address, city, membership_level, loyalty_points, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000004'::uuid, 
     '1985-12-25', 
     'FEMALE', 
     '321 Võ Văn Tần, Quận 3', 
     'Hồ Chí Minh', 
     'PLATINUM', 
     15000, 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (user_id) DO NOTHING;

-- Customer 5: Hà Nội
INSERT INTO users (id, username, password, name, email, phone, lock_flag, system_flag, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000005'::uuid, 
     'customer_hanoi', 
     '$2a$10$8K1p/a0dL2LH0XZnup/My.id5.ShmQmGe/vkiOoJ8VOwya/Qb9F5m',
     'Hoàng Văn Hà Nội', 
     'hanoi@gmail.com', 
     '0955555555', 
     '0', 
     'NORMAL', 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (username) DO NOTHING;

INSERT INTO user_role (id, user_id, role_id)
SELECT gen_random_uuid(), '40000000-0000-0000-0000-000000000005'::uuid, id
FROM sys_role WHERE code = 'CUSTOMER'
ON CONFLICT DO NOTHING;

INSERT INTO customers (user_id, date_of_birth, gender, address, city, membership_level, loyalty_points, created_at, updated_at, is_delete)
VALUES 
    ('40000000-0000-0000-0000-000000000005'::uuid, 
     '1993-03-30', 
     'MALE', 
     '100 Hoàn Kiếm', 
     'Hà Nội', 
     'SILVER', 
     1200, 
     CURRENT_TIMESTAMP, 
     CURRENT_TIMESTAMP, 
     false)
ON CONFLICT (user_id) DO NOTHING;

-- =====================================================
-- SUMMARY
-- =====================================================
-- Customers:
-- 1. customer_bronze / Customer@123 (BRONZE - 500 points - HCM)
-- 2. customer_silver / Customer@123 (SILVER - 2500 points - HCM)
-- 3. customer_gold / Customer@123 (GOLD - 7800 points - HCM)
-- 4. customer_platinum / Customer@123 (PLATINUM - 15000 points - HCM)
-- 5. customer_hanoi / Customer@123 (SILVER - 1200 points - Hà Nội)
-- =====================================================

-- Verify
SELECT 
    u.username,
    u.name,
    u.email,
    c.membership_level,
    c.loyalty_points,
    c.city
FROM customers c
JOIN users u ON c.user_id = u.id
WHERE c.is_delete = false
ORDER BY c.loyalty_points DESC;
