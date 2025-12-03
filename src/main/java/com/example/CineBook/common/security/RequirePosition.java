package com.example.CineBook.common.security;

import java.lang.annotation.*;

/**
 * Annotation để kiểm tra position của Employee.
 * Sử dụng kết hợp với @PreAuthorize để kiểm tra cả Role và Position.
 * 
 * Ví dụ:
 * @PreAuthorize("hasRole('STAFF')")
 * @RequirePosition({"MANAGER", "CASHIER"})
 * public void managerOrCashierMethod() { ... }
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequirePosition {
    /**
     * Danh sách các position code được phép truy cập.
     */
    String[] value();
    
    /**
     * Thông báo lỗi khi không đủ quyền.
     */
    String message() default "Access denied: insufficient position";
}


/**
 * Giải thích meta-annotations:
 *
 * @Target  → Quy định annotation dùng cho METHOD hoặc CLASS.
 *
 * @Retention(RUNTIME)
 *  → Giúp annotation tồn tại đến khi chương trình chạy.
 *  → Spring dùng Reflection để đọc annotation tại runtime.
 *  → Nếu không để RUNTIME thì Spring Security/AOP sẽ không thấy annotation.
 *
 * @Documented  → Hiển thị annotation trong tài liệu Javadoc (không ảnh hưởng logic).
 *
 * Các khái niệm liên quan:
 * - Reflection: Cách Spring đọc annotation ngay khi method được gọi.
 * - AOP (Aspect-Oriented Programming): Cơ chế Spring chặn method trước khi chạy
 *   để kiểm tra quyền/logic bảo mật.
 * - JVM & bytecode: Annotation được lưu trong file .class, JVM load lên và
 *   Spring đọc được nhờ Retention.RUNTIME.
 *
 * Tóm lại:
 * → RUNTIME + AOP + Reflection = Spring có thể bắt được annotation và xử lý
 *   phân quyền trước khi method chạy.
 */
