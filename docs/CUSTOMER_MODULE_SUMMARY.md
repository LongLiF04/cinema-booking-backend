# CUSTOMER MODULE - TÓM TẮT TRIỂN KHAI

## ✅ ĐÃ HOÀN THÀNH

### 1. Database
- ✅ Table `customers` với quan hệ 1-1 với `users`
- ✅ Indexes cho performance
- ✅ Soft delete support

### 2. Entities & Enums
- ✅ `Customer.java` - Entity chính
- ✅ `MembershipLevelEnum.java` - BRONZE, SILVER, GOLD, PLATINUM
- ✅ `GenderEnum.java` - MALE, FEMALE, OTHER

### 3. DTOs
- ✅ `CustomerCreateRequest.java` - Validation đầy đủ
- ✅ `CustomerUpdateRequest.java` - Optional fields
- ✅ `CustomerResponse.java` - Response DTO
- ✅ `CustomerSearchDTO.java` - Search với paging

### 4. Repository
- ✅ `CustomerRepository.java` - JPA Repository
- ✅ `CustomerRepositoryCustom.java` - Custom interface
- ✅ `CustomerRepositoryImpl.java` - Criteria API search

### 5. Service
- ✅ `CustomerService.java` - Interface
- ✅ `CustomerServiceImpl.java` - Business logic:
  - createCustomer() - Tạo Customer + User
  - getCustomerById() - Xem theo ID
  - updateCustomer() - Cập nhật
  - deleteCustomer() - Soft delete
  - searchCustomers() - Search & paging
  - getCurrentCustomer() - /me endpoint
  - updateCurrentCustomer() - /me update

### 6. Controller
- ✅ `CustomerController.java` - REST API:
  - POST /api/customers - Tạo (ADMIN)
  - GET /api/customers/{userId} - Xem (ADMIN/STAFF)
  - PUT /api/customers/{userId} - Sửa (ADMIN)
  - DELETE /api/customers/{userId} - Xóa (ADMIN)
  - GET /api/customers/search - Tìm kiếm (ADMIN/STAFF)
  - GET /api/customers/me - Xem bản thân (CUSTOMER)
  - PUT /api/customers/me - Sửa bản thân (CUSTOMER)

### 7. Mapper
- ✅ `CustomerMapper.java` - MapStruct mapper

### 8. Documentation
- ✅ `CUSTOMER_MODULE.md` - Tài liệu chi tiết
- ✅ `CUSTOMER_INIT_DATA.sql` - Init data script
- ✅ `Customer_API.postman_collection.json` - Postman collection
- ✅ `CUSTOMER_MODULE_SUMMARY.md` - File này

---

## 📊 THỐNG KÊ

### Files mới: 17
**Code (11 files):**
1. `model/Customer.java`
2. `common/constant/MembershipLevelEnum.java`
3. `common/constant/GenderEnum.java`
4. `dto/customer/CustomerCreateRequest.java`
5. `dto/customer/CustomerUpdateRequest.java`
6. `dto/customer/CustomerResponse.java`
7. `dto/customer/CustomerSearchDTO.java`
8. `repository/irepository/CustomerRepository.java`
9. `repository/custom/CustomerRepositoryCustom.java`
10. `repository/impl/CustomerRepositoryImpl.java`
11. `mapper/CustomerMapper.java`
12. `service/CustomerService.java`
13. `service/impl/CustomerServiceImpl.java`
14. `controller/CustomerController.java`

**Documentation (3 files):**
15. `docs/CUSTOMER_MODULE.md`
16. `docs/CUSTOMER_INIT_DATA.sql`
17. `docs/Customer_API.postman_collection.json`

### Tổng dòng code: ~1,500 lines
- Java: ~900 lines
- SQL: ~200 lines
- Documentation: ~400 lines

---

## 🎯 BUSINESS FLOW

### Tạo Customer (ADMIN)
```
POST /api/customers
  ↓
Validate input (username, password, email, phone)
  ↓
Tìm role CUSTOMER
  ↓
Tạo User với role CUSTOMER
  ↓
Tạo Customer (membership=BRONZE, points=0)
  ↓
Return CustomerResponse
```

### Customer tự quản lý (/me)
```
GET/PUT /api/customers/me
  ↓
Lấy userId từ JWT token (SecurityUtils)
  ↓
Thực hiện action trên Customer của chính mình
  ↓
Return CustomerResponse
```

---

## 🔐 PHÂN QUYỀN

| Endpoint | SUPER_ADMIN | ADMIN | STAFF | CUSTOMER |
|----------|-------------|-------|-------|----------|
| POST /customers | ✓ | ✓ | ✗ | ✗ |
| GET /customers/{id} | ✓ | ✓ | ✓ | ✗ |
| PUT /customers/{id} | ✓ | ✓ | ✗ | ✗ |
| DELETE /customers/{id} | ✓ | ✓ | ✗ | ✗ |
| GET /customers/search | ✓ | ✓ | ✓ | ✗ |
| GET /customers/me | ✗ | ✗ | ✗ | ✓ |
| PUT /customers/me | ✗ | ✗ | ✗ | ✓ |

---

## 📝 API EXAMPLES

### 1. Tạo Customer (ADMIN)
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {ADMIN_TOKEN}" \
  -d '{
    "username": "newcustomer",
    "password": "Customer@123",
    "name": "Khách Hàng Mới",
    "email": "new@gmail.com",
    "phone": "0901234567",
    "dateOfBirth": "1995-05-15",
    "gender": "MALE",
    "address": "123 Street",
    "city": "Hồ Chí Minh"
  }'
```

### 2. Search Customer
```bash
curl -X GET "http://localhost:8080/api/customers/search?keyword=nguyen&membershipLevel=GOLD&page=0&size=10" \
  -H "Authorization: Bearer {ADMIN_TOKEN}"
```

### 3. Customer xem thông tin bản thân
```bash
curl -X GET http://localhost:8080/api/customers/me \
  -H "Authorization: Bearer {CUSTOMER_TOKEN}"
```

### 4. Customer cập nhật thông tin
```bash
curl -X PUT http://localhost:8080/api/customers/me \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer {CUSTOMER_TOKEN}" \
  -d '{
    "name": "Tên Mới",
    "phone": "0909999999",
    "address": "Địa chỉ mới"
  }'
```

---

## 🧪 TESTING

### Test Accounts (sau khi chạy CUSTOMER_INIT_DATA.sql)
```
customer_bronze / Customer@123 (BRONZE - 500 points)
customer_silver / Customer@123 (SILVER - 2500 points)
customer_gold / Customer@123 (GOLD - 7800 points)
customer_platinum / Customer@123 (PLATINUM - 15000 points)
customer_hanoi / Customer@123 (SILVER - 1200 points - Hà Nội)
```

### Test Scenarios
1. ✅ ADMIN tạo Customer thành công
2. ✅ Customer login và xem /me
3. ✅ Customer cập nhật /me
4. ✅ ADMIN search Customer theo keyword
5. ✅ ADMIN search theo membership level
6. ✅ ADMIN search theo city
7. ✅ STAFF xem Customer info
8. ✅ Customer không thể xem Customer khác
9. ✅ Soft delete Customer

---

## 🚀 DEPLOYMENT

### 1. Compile
```bash
mvn clean compile
```

### 2. Run init data
```bash
psql -U postgres -d cinebook_db -f docs/CUSTOMER_INIT_DATA.sql
```

### 3. Start application
```bash
mvn spring-boot:run
```

### 4. Test với Postman
- Import `Customer_API.postman_collection.json`
- Login as ADMIN
- Test CRUD operations
- Login as CUSTOMER
- Test /me endpoints

---

## 💡 KEY FEATURES

### 1. Quan hệ 1-1 với User
- Customer.userId = User.id (PK & FK)
- Cascade delete
- Soft delete cả User và Customer

### 2. Membership System
- **BRONZE**: Mặc định khi tạo
- **SILVER**: >= 1000 points
- **GOLD**: >= 5000 points
- **PLATINUM**: >= 10000 points

### 3. Loyalty Points
- Tích điểm: 10,000 VND = 1 point
- Tự động nâng hạng theo points
- Quyền lợi theo hạng

### 4. Search & Filter
- Keyword: name, email, phone
- Membership level
- City
- Pagination support

### 5. Self-Service (/me)
- Customer xem thông tin bản thân
- Customer tự cập nhật thông tin
- Không cần biết userId

---

## 🔧 VALIDATION

### CustomerCreateRequest
```java
username: @NotBlank
password: @NotBlank, @Pattern (min 8, có chữ hoa, thường, số)
name: @NotBlank
email: @NotBlank, @Email
phone: @NotBlank, @Pattern (0xxxxxxxxx)
dateOfBirth: Optional
gender: Optional (MALE, FEMALE, OTHER)
address: Optional
city: Optional
```

### CustomerUpdateRequest
- Tất cả fields optional
- Validate format nếu có giá trị

---

## 📚 ARCHITECTURE

### Layers
```
Controller (REST API)
    ↓
Service (Business Logic)
    ↓
Repository (Data Access)
    ↓
Database (PostgreSQL)
```

### Design Patterns
- ✅ Repository Pattern
- ✅ Service Layer Pattern
- ✅ DTO Pattern
- ✅ Mapper Pattern (MapStruct)
- ✅ Criteria API for dynamic queries

---

## 🎓 BEST PRACTICES

### 1. Security
- ✅ JWT authentication
- ✅ Role-based authorization
- ✅ Password validation
- ✅ Soft delete

### 2. Code Quality
- ✅ Validation annotations
- ✅ Exception handling
- ✅ Lombok for boilerplate
- ✅ MapStruct for mapping

### 3. Performance
- ✅ Indexes on search fields
- ✅ Pagination
- ✅ Criteria API (no N+1)
- ✅ Lazy loading

### 4. Maintainability
- ✅ Clean architecture
- ✅ Separation of concerns
- ✅ Interface-based design
- ✅ Comprehensive documentation

---

## ✨ HIGHLIGHTS

### Module Customer hoàn chỉnh với:
1. ✅ Entity với quan hệ 1-1
2. ✅ CRUD đầy đủ
3. ✅ Search & Paging
4. ✅ Endpoint /me cho Customer
5. ✅ Phân quyền theo role
6. ✅ Validation đầy đủ
7. ✅ Membership & Loyalty system
8. ✅ Soft delete
9. ✅ MapStruct mapper
10. ✅ Criteria API search
11. ✅ Documentation đầy đủ
12. ✅ Postman collection
13. ✅ Init data script

---

**Module Customer đã sẵn sàng để sử dụng!**

**Version**: 1.0.0  
**Date**: 2024-01-15
