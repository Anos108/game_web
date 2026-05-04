# Arkanoid — Nhóm 12 (OOP)

Game **Arkanoid** (phá gạch) một người chơi, xây dựng bằng **JavaFX**, có thể chạy **desktop** hoặc **trên trình duyệt** nhờ [**JPro**](https://www.jpro.one/) (Remote UI cho JavaFX).

---

## Tính năng chính

- Menu chính: **Play**, **Settings** (âm lượng), **Exit**
- Chọn **độ khó**: Easy / Medium / Hard
- Điều khiển **thanh đỡ (paddle)** bằng bàn phím
- **Bóng**, **gạch** nhiều loại (độ bền và điểm khác nhau), **va chạm** vật lý
- **Power-up** ngẫu nhiên khi phá gạch: thêm bóng, thêm mạng, làm chậm bóng
- **Điểm** hiển thị trong lúc chơi; **điểm cao (high score)** riêng theo từng độ khó
- **Game Over**: chơi lại, về menu, thoát
- **Âm thanh**: nhạc nền menu, nhạc gameplay, hiệu ứng va chạm

---

## Công nghệ

| Thành phần | Phiên bản (tham chiếu `pom.xml`) |
|------------|----------------------------------|
| Java | 21 |
| JavaFX | 21.0.6 |
| JPro | 2026.1.1 |
| Build | Maven |
| Kiểm thử | JUnit 5, Mockito, TestFX |

Module: `org.example.demo2` (xem `src/main/java/module-info.java`).

---

## Yêu cầu môi trường

- **JDK 21** trở lên (một số bản JPro/Java trên máy có thể dùng JDK mới hơn; nên đồng bộ với môi trường đã build thành công)
- **Apache Maven 3.8+**
- Truy cập Maven repository **JPro** (đã khai báo trong `pom.xml`: `https://sandec.jfrog.io/artifactory/repo`)

