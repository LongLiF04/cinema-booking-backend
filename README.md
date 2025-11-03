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

### yêu cầu
- JDK 17 hoặc cao hơn
    - Maven 3.8+

### Cấu hình
Dự án sử dụng biến môi trường để cấu hình các thông tin quan trọng. Hãy đảm bảo các biến môi trường sau đã được thiết lập trên hệ thống của bạn:

- `DB_URL`: URL kết nối tới cơ sở dữ liệu.
    - *Ví dụ:* `jdbc:postgresql://localhost:5432/cinebook_db`
- `DB_USERNAME`: Tên đăng nhập cơ sở dữ liệu.
- `DB_PASSWORD`: Mật khẩu cơ sở dữ liệu.
- `JWT_SECRET`: Chuỗi bí mật để ký và xác thực JSON Web Tokens.
- `JWT_EXPIRATION_MS`: Thời gian hết hạn của JWT (tính bằng mili giây).

Mặc định, ứng dụng sẽ khởi chạy tại `http://localhost:8080`.

## 3. API Documentation (Swagger UI)

Sau khi khởi chạy thành công, bạn có thể truy cập vào giao diện Swagger UI để xem tài liệu chi tiết về các API và thực hiện gọi thử.

- **Đường dẫn Swagger UI:** http://localhost:8080/swagger-ui.html

## 4. Quản lý Database Migration với Liquibase

### Thêm một migration mới
Khi bạn thay đổi các class Entity (JPA), bạn có thể tự động tạo một file changeset mới để cập nhật schema cho cơ sở dữ liệu bằng lệnh sau:

**Với Maven:**