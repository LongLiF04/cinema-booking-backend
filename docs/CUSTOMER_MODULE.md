# MODULE CUSTOMER - TÀI LIỆU CHI TIẾT

## 1. DATABASE SCHEMA

### Table: customers
```sql
CREATE TABLE customers (
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

CREATE INDEX idx_customers_membership ON customers(membership_level);
CREATE INDEX idx_customers_city ON customers(city);
CREATE INDEX idx_customers_is_delete ON customers(is_delete);
```

### Membership Levels
- **BRONZE**: Đồng (mặc định khi tạo)
- **SILVER**: Bạc (>= 1000 điểm)
- **GOLD**: Vàng (>= 5000 điểm)
- **PLATINUM**: Bạch kim (>= 10000 điểm)

## 2. QUAN HỆ

```
users (1) ←→ (1) customers
  ↓
user_role (N) → (1) sys_role [CUSTOMER]
```

## 3. BUSINESS FLOW

### 3.1. Tạo Customer (ADMIN)
```
ADMIN gọi POST /api/customers
  ↓
Validate input
  ↓
Tìm role CUSTOMER
  ↓
Tạo User với role CUSTOMER
  ↓
Tạo Customer record (membership = BRONZE, points = 0)
  ↓
Return CustomerResponse
```

### 3.2. Customer tự cập nhật (/me)
```
CUSTOMER gọi PUT /api/customers/me
  ↓
Lấy userId từ JWT token
  ↓
Cập nhật thông tin Customer
  ↓
Return CustomerResponse
```

## 4. API ENDPOINTS

### 4.1. POST /api/customers
**Role:** ADMIN, SUPER_ADMIN  
**Mô tả:** Tạo Customer mới

**Request:**
```json
{
  "username": "customer01",
  "password": "Customer@123",
  "name": "Nguyễn Văn A",
  "email": "customer01@gmail.com",
  "phone": "0901234567",
  "dateOfBirth": "1990-01-15",
  "gender": "MALE",
  "address": "123 Nguyễn Huệ",
  "city": "Hồ Chí Minh"
}
```

**Response:**
```json
{
  "code": 200,
  "message": "Success",
  "data": {
    "userId": "uuid",
    "username": "customer01",
    "name": "Nguyễn Văn A",
    "email": "customer01@gmail.com",
    "phone": "0901234567",
    "dateOfBirth": "1990-01-15",
    "gender": "MALE",
    "address": "123 Nguyễn Huệ",
    "city": "Hồ Chí Minh",
    "membershipLevel": "BRONZE",
    "loyaltyPoints": 0
  }
}
```

### 4.2. GET /api/customers/{userId}
**Role:** ADMIN, SUPER_ADMIN, STAFF  
**Mô tả:** Xem thông tin Customer theo ID

### 4.3. PUT /api/customers/{userId}
**Role:** ADMIN, SUPER_ADMIN  
**Mô tả:** Cập nhật thông tin Customer

**Request:**
```json
{
  "name": "Nguyễn Văn B",
  "phone": "0909999999",
  "dateOfBirth": "1990-02-20",
  "gender": "MALE",
  "address": "456 Lê Lợi",
  "city": "Hà Nội"
}
```

### 4.4. DELETE /api/customers/{userId}
**Role:** ADMIN, SUPER_ADMIN  
**Mô tả:** Xóa mềm Customer

### 4.5. GET /api/customers/search
**Role:** ADMIN, SUPER_ADMIN, STAFF  
**Mô tả:** Tìm kiếm Customer

**Query Params:**
- `keyword`: Tìm theo name, email, phone
- `membershipLevel`: BRONZE, SILVER, GOLD, PLATINUM
- `city`: Tìm theo thành phố
- `page`: Số trang (default: 0)
- `size`: Kích thước trang (default: 10)

**Example:**
```
GET /api/customers/search?keyword=nguyen&membershipLevel=GOLD&page=0&size=10
```

### 4.6. GET /api/customers/me
**Role:** CUSTOMER  
**Mô tả:** Customer xem thông tin của chính mình

**Response:**
```json
{
  "code": 200,
  "data": {
    "userId": "uuid",
    "username": "customer01",
    "name": "Nguyễn Văn A",
    "email": "customer01@gmail.com",
    "phone": "0901234567",
    "dateOfBirth": "1990-01-15",
    "gender": "MALE",
    "address": "123 Nguyễn Huệ",
    "city": "Hồ Chí Minh",
    "membershipLevel": "GOLD",
    "loyaltyPoints": 5500
  }
}
```

### 4.7. PUT /api/customers/me
**Role:** CUSTOMER  
**Mô tả:** Customer cập nhật thông tin của chính mình

**Request:**
```json
{
  "name": "Nguyễn Văn A Updated",
  "phone": "0908888888",
  "dateOfBirth": "1990-01-15",
  "address": "789 Trần Hưng Đạo",
  "city": "Đà Nẵng"
}
```

## 5. VALIDATION RULES

### CustomerCreateRequest
- `username`: Required, 4-50 ký tự
- `password`: Required, min 8 ký tự, có chữ hoa, chữ thường, số
- `name`: Required
- `email`: Required, format email hợp lệ
- `phone`: Required, format 0xxxxxxxxx (10-11 số)
- `dateOfBirth`: Optional
- `gender`: Optional (MALE, FEMALE, OTHER)
- `address`: Optional
- `city`: Optional

### CustomerUpdateRequest
- Tất cả fields đều optional
- Validate format nếu có giá trị

## 6. PHÂN QUYỀN

| Action | SUPER_ADMIN | ADMIN | STAFF | CUSTOMER |
|--------|-------------|-------|-------|----------|
| Tạo Customer | ✓ | ✓ | ✗ | ✗ |
| Xem Customer (ID) | ✓ | ✓ | ✓ | ✗ |
| Cập nhật Customer (ID) | ✓ | ✓ | ✗ | ✗ |
| Xóa Customer | ✓ | ✓ | ✗ | ✗ |
| Search Customer | ✓ | ✓ | ✓ | ✗ |
| GET /me | ✗ | ✗ | ✗ | ✓ |
| PUT /me | ✗ | ✗ | ✗ | ✓ |

## 7. TESTING

### 7.1. Tạo Customer (ADMIN)
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {ADMIN_TOKEN}" \
  -d '{
    "username": "testcustomer",
    "password": "Test@123",
    "name": "Test Customer",
    "email": "test@gmail.com",
    "phone": "0901111111",
    "dateOfBirth": "1995-05-15",
    "gender": "MALE",
    "address": "123 Test Street",
    "city": "Hồ Chí Minh"
  }'
```

### 7.2. Customer xem thông tin bản thân
```bash
# Login as customer
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testcustomer",
    "password": "Test@123"
  }'

# Get customer info
curl -X GET http://localhost:8080/api/customers/me \
  -H "Authorization: Bearer {CUSTOMER_TOKEN}"
```

### 7.3. Customer cập nhật thông tin
```bash
curl -X PUT http://localhost:8080/api/customers/me \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {CUSTOMER_TOKEN}" \
  -d '{
    "name": "Updated Name",
    "phone": "0902222222",
    "address": "New Address"
  }'
```

### 7.4. Search Customer (ADMIN)
```bash
curl -X GET "http://localhost:8080/api/customers/search?keyword=test&page=0&size=10" \
  -H "Authorization: Bearer {ADMIN_TOKEN}"
```

## 8. LOYALTY POINTS & MEMBERSHIP

### Tích điểm
- Mỗi 10,000 VND chi tiêu = 1 điểm
- Điểm được cộng sau khi booking thành công

### Nâng hạng tự động
```java
if (loyaltyPoints >= 10000) {
    membershipLevel = "PLATINUM";
} else if (loyaltyPoints >= 5000) {
    membershipLevel = "GOLD";
} else if (loyaltyPoints >= 1000) {
    membershipLevel = "SILVER";
} else {
    membershipLevel = "BRONZE";
}
```

### Quyền lợi theo hạng
- **BRONZE**: Không có ưu đãi
- **SILVER**: Giảm 5% tổng hóa đơn
- **GOLD**: Giảm 10% + ưu tiên đặt vé
- **PLATINUM**: Giảm 15% + ưu tiên + phòng VIP miễn phí

## 9. FILES STRUCTURE

```
src/main/java/com/example/CineBook/
├── model/
│   └── Customer.java
├── dto/customer/
│   ├── CustomerCreateRequest.java
│   ├── CustomerUpdateRequest.java
│   ├── CustomerResponse.java
│   └── CustomerSearchDTO.java
├── repository/
│   ├── irepository/
│   │   └── CustomerRepository.java
│   ├── custom/
│   │   └── CustomerRepositoryCustom.java
│   └── impl/
│       └── CustomerRepositoryImpl.java
├── mapper/
│   └── CustomerMapper.java
├── service/
│   ├── CustomerService.java
│   └── impl/
│       └── CustomerServiceImpl.java
├── controller/
│   └── CustomerController.java
└── common/constant/
    ├── MembershipLevelEnum.java
    └── GenderEnum.java
```

## 10. SUMMARY

✅ **Đã implement:**
- Entity Customer với quan hệ 1-1 với User
- CRUD đầy đủ với validation
- Search & Paging
- Endpoint /customers/me cho Customer
- Phân quyền theo role
- Membership level & Loyalty points
- Soft delete
- MapStruct mapper
- Criteria API cho search

✅ **Business rules:**
- Tạo Customer → tự động tạo User với role CUSTOMER
- Membership mặc định = BRONZE
- Loyalty points mặc định = 0
- Customer chỉ xem/sửa thông tin của mình qua /me
- ADMIN/STAFF quản lý tất cả Customer
