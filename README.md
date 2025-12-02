# Movie Ticket Booking System

## 1. Giới thiệu
Dự án **Movie Ticket Booking System** là một ứng dụng web cho phép người dùng đặt vé xem phim trực tuyến.  
Người dùng có thể xem danh sách phim, chọn suất chiếu, chọn ghế, thanh toán và nhận vé điện tử.

---

## 2. Tính năng chính

- Đăng ký / Đăng nhập người dùng (JWT Authentication)
- Xem danh sách phim đang chiếu và sắp chiếu
- Chọn lịch chiếu, rạp và ghế ngồi
- Thanh toán trực tuyến (giả lập hoặc qua cổng thật)
- Tạo và lưu vé xem phim
- Trang quản trị (quản lý phim, rạp, lịch chiếu, người dùng)

---

## 3. Công nghệ sử dụng

### Frontend
- **ReactJS** + **Tailwind CSS**
- **Axios** – gọi API
- **React Router** – điều hướng giữa các trang

### Backend
- **Spring Boot**
- **Spring Security + JWT** – xác thực và phân quyền
- **PostgreSQL** – cơ sở dữ liệu
- **Docker** – đóng gói ứng dụng
- **Redis** – caching / session

---

## 4. Hướng dẫn chạy dự án

### Yêu cầu
- **Docker** và **Docker Compose** (Khuyến nghị)
- Hoặc: JDK 17+, Maven 3.8+, PostgreSQL, Redis

### Cách 1: Chạy với Docker (Khuyến nghị)

#### Bước 1: Clone dự án
```bash
git clone <repository-url>
cd CineBook
```

#### Bước 2: Cấu hình biến môi trường (Tùy chọn)
Sao chép file `.env.example` thành `.env` và chỉnh sửa nếu cần:
```bash
cp .env.example .env
```

#### Bước 3: Chạy toàn bộ ứng dụng
```bash
docker-compose up -d
```

Ứng dụng sẽ chạy tại: `http://localhost:8080`

#### Dừng ứng dụng
```bash
docker-compose down
```

#### Xóa dữ liệu và khởi động lại
```bash
docker-compose down -v
docker-compose up -d
```

### Cách 2: Chạy môi trường Development

Chỉ chạy PostgreSQL và Redis, code chạy trên máy local:

```bash
# Chạy database và redis
docker-compose -f docker-compose.dev.yml up -d

# Chạy ứng dụng Spring Boot
mvn spring-boot:run
```

### Cách 3: Chạy thủ công (Không dùng Docker)

#### Yêu cầu
- JDK 17 hoặc cao hơn
- Maven 3.8+
- PostgreSQL 15+
- Redis 7+

#### Cấu hình biến môi trường
Thiết lập các biến môi trường sau:

- `DB_URL`: `jdbc:postgresql://localhost:5432/cinebook_db`
- `DB_USERNAME`: Tên đăng nhập database
- `DB_PASSWORD`: Mật khẩu database
- `JWT_SECRET`: Chuỗi bí mật (tối thiểu 256 bits)
- `JWT_EXPIRATION_MS`: `86400000` (24 giờ)
- `EMAIL_USERNAME`: Email Gmail
- `EMAIL_PASSWORD`: App password của Gmail

#### Chạy ứng dụng
```bash
mvn clean install
mvn spring-boot:run
```

### Tài khoản mặc định
Sau khi khởi động, hệ thống tự động tạo tài khoản Super Admin:
- **Username**: `superadmin`
- **Password**: `Admin@123`

## 5. API Documentation (Swagger UI)

Sau khi khởi chạy thành công, bạn có thể truy cập vào giao diện Swagger UI để xem tài liệu chi tiết về các API và thực hiện gọi thử.

- **Đường dẫn Swagger UI:** http://localhost:8080/swagger-ui.html

## 6. Quản lý Database Migration với Liquibase

### Thêm một migration mới
Khi bạn thay đổi các class Entity (JPA), bạn có thể tự động tạo một file changeset mới để cập nhật schema cho cơ sở dữ liệu bằng lệnh sau:

**Với Maven:**