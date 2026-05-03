**TÀI LIỆU ĐẶC TẢ YÊU CẦU PHẦN MỀM (SRS)**

**Hệ thống Game Arkanoid**  
**Trò chơi Brick Breaker trên nền tảng Desktop (JavaFX)**

**Thông tin tài liệu**

| **Mục**            | **Nội dung**   |
|--------------------|----------------|
| Phiên bản tài liệu | 1.0            |
| Tiêu chuẩn         | IEEE 830-1998  |
| Thời gian          | Tháng 5 - 2026 |
| Trạng thái         | Bản nháp       |
| Thể loại dự án     | Game Desktop   |

Tài liệu này là Phụ lục Kỹ thuật của Thỏa thuận Dịch vụ giữa các bên được nêu trong Phụ lục B.

**Lịch sử sửa đổi**

| **Phiên bản** | **Thời gian**  | **Tác giả**     | **Mô tả**                                     |
|---------------|----------------|-----------------|-----------------------------------------------|
| 1.0           | Tháng 5 - 2026 | Nhóm phát triển | Bản nháp đầu tiên gửi để xem xét và phê duyệt |

**Mục lục**

1.  Giới thiệu  
    1.1 Mục đích  
    1.2 Phạm vi dự án  
    1.3 Định nghĩa  
    1.4 Tài liệu tham khảo  
    1.5 Tổng quan

2.  Mô tả tổng quan  
    2.1 Bối cảnh sản phẩm  
    2.2 Chức năng sản phẩm  
    2.3 Người dùng hệ thống  
    2.4 Môi trường hoạt động  
    2.5 Ràng buộc  
    2.6 Giả định và yếu tố phụ thuộc  
    2.7 Nhu cầu người dùng

3.  Yêu cầu chức năng chi tiết  
    3.1 FR-001 --- Vào game từ menu  
    3.2 FR-002 --- Chọn độ khó  
    3.3 FR-003 --- Chơi game  
    3.4 FR-004 --- Xử lý va chạm, mạng và power-up  
    3.5 FR-005 --- Tính điểm và lưu high score  
    3.6 FR-006 --- Game Over và điều hướng sau trận  
    3.7 FR-007 --- Cài đặt âm lượng  
    3.8 Sơ đồ Use Case  
    3.9 Biểu đồ hoạt động

4.  Yêu cầu phi chức năng  
    4.1 Hiệu năng  
    4.2 Độ ổn định và tin cậy  
    4.3 Khả dụng (Usability)  
    4.4 Bảo trì và mở rộng  
    4.5 Bảo mật

5.  Giao diện đối ngoại  
    5.1 Giao diện người dùng  
    5.2 Giao diện phần mềm  
    5.3 Giao diện phần cứng  
    5.4 Giao diện giao tiếp

6.  Tóm tắt ca sử dụng (Use case summary)

7.  Phạm vi dự án, ràng buộc và giới hạn  
    7.1 Những gì trong dự án  
    7.2 Những gì KHÔNG nằm trong phạm vi  
    7.3 Ràng buộc  
    7.4 Giới hạn

Phụ lục A: Bảng thuật ngữ  
Phụ lục B: Phê duyệt tài liệu & Thỏa thuận pháp lý

**1. Giới thiệu**

**1.1 Mục đích**

Tài liệu SRS này mô tả các yêu cầu chức năng và phi chức năng cho hệ thống game Arkanoid phát triển bằng JavaFX. Tài liệu là căn cứ để thống nhất phạm vi triển khai, kiểm thử và nghiệm thu giữa các bên.

**1.2 Phạm vi dự án**

Hệ thống cung cấp game phá gạch 2D một người chơi với các chức năng:

- Menu chính, vào game, chọn độ khó.

- Điều khiển paddle bằng bàn phím.

- Cơ chế bóng--gạch--paddle, mạng chơi, điểm số.

- Power-up (thêm bóng, thêm mạng, giảm tốc).

- Màn hình Game Over, chơi lại, quay về menu.

- Lưu điểm cao nhất theo từng độ khó vào bộ nhớ cục bộ.

Ngoài phạm vi:

- Đăng ký/đăng nhập tài khoản.

- Leaderboard online.

- Multiplayer.

- Đồng bộ cloud.

**1.3 Định nghĩa**

| **Thuật ngữ** | **Định nghĩa**                                       |
|---------------|------------------------------------------------------|
| SRS           | Software Requirements Specification                  |
| FR            | Functional Requirement --- yêu cầu chức năng         |
| NFR           | Non-Functional Requirement --- yêu cầu phi chức năng |
| Player        | Người chơi                                           |
| High Score    | Điểm cao nhất lưu cục bộ theo độ khó                 |
| Power-up      | Vật phẩm tăng cường trong lúc chơi                   |
| Game Over     | Trạng thái kết thúc khi hết mạng                     |

**1.4 Tài liệu tham khảo**

- IEEE Std 830-1998.

**1.5 Tổng quan**

Tài liệu gồm: mô tả tổng quan hệ thống, yêu cầu chức năng chi tiết, yêu cầu phi chức năng, giao diện đối ngoại, use case summary, phạm vi & giới hạn, và phụ lục pháp lý.

**2. Mô tả tổng quan**

**2.1 Bối cảnh sản phẩm**

Arkanoid là ứng dụng game desktop độc lập. Người chơi điều khiển paddle để giữ bóng, phá gạch để ghi điểm, thu thập power-up, và duy trì mạng sống lâu nhất có thể.

**2.2 Chức năng sản phẩm**

| **ID** | **Chức năng**              | **Mô tả**                                      |
|--------|----------------------------|------------------------------------------------|
| FR-001 | Vào game từ menu           | Điều hướng từ Main Menu sang Difficulty        |
| FR-002 | Chọn độ khó                | Chọn Easy / Medium / Hard                      |
| FR-003 | Chơi game                  | Điều khiển paddle, phá gạch, xử lý bóng        |
| FR-004 | Xử lý va chạm & power-up   | Va chạm vật lý, rơi vật phẩm, áp dụng hiệu ứng |
| FR-005 | Tính điểm & lưu high score | Cộng điểm và lưu điểm cao theo độ khó          |
| FR-006 | Game Over flow             | Hiển thị điểm, Play Again, Back to Menu, Exit  |
| FR-007 | Cài đặt âm lượng           | Điều chỉnh volume trong popup settings         |

**2.3 Người dùng hệ thống**

**Người chơi (Player):**

- Trình độ kỹ thuật cơ bản.

- Dùng bàn phím để chơi.

- Mục tiêu: giải trí, đạt điểm cao.

**2.4 Môi trường hoạt động**

- Runtime: Java + JavaFX.

- Thiết bị: máy tính để bàn/laptop.

- Input: bàn phím.

- Mạng: không bắt buộc (chạy offline).

**2.5 Ràng buộc**

- Phải chạy ổn định ở cấu hình máy phổ thông.

- Dữ liệu điểm lưu cục bộ.

- Giao diện nhất quán các màn hình game.

**2.6 Giả định và yếu tố phụ thuộc**

- Có JavaFX runtime phù hợp.

- Có quyền đọc/ghi file local để lưu điểm.

- Tài nguyên ảnh/âm thanh có sẵn đúng đường dẫn.

**2.7 Nhu cầu người dùng**

| **Nhu cầu**         | **Tại sao quan trọng** | **Cách hệ thống xử lý**      |
|---------------------|------------------------|------------------------------|
| Điều khiển đơn giản | Dễ làm quen            | Dùng A/D để di chuyển paddle |
| Chơi mượt           | Trải nghiệm tốt        | Cập nhật frame liên tục      |
| Theo dõi thành tích | Tăng động lực          | Hiển thị score và high score |
| Chơi lại nhanh      | Giữ nhịp chơi          | Có nút Play Again            |

**3. Yêu cầu chức năng chi tiết**

**3.1 FR-001 --- Vào game từ menu**

| **Trường**        | **Nội dung**                                                                   |
|-------------------|--------------------------------------------------------------------------------|
| Use Case Name     | Vào game từ menu                                                               |
| Use Case ID       | FR-001                                                                         |
| Actor             | Người chơi                                                                     |
| Trigger           | Người chơi bấm Start                                                           |
| Precondition      | Ứng dụng đã mở đến Main Menu                                                   |
| Basic Path        | \(1\) Hiển thị Main Menu; (2) Người chơi bấm Start; (3) Chuyển sang Difficulty |
| Alternative Paths | Người chơi chọn Settings hoặc Exit thay vì Start                               |
| Postcondition     | Màn hình Difficulty hiển thị thành công                                        |
| Exception Paths   | Lỗi tải màn hình                                                               |

**3.2 FR-002 --- Chọn độ khó**

| **Trường**      | **Nội dung**                                               |
|-----------------|------------------------------------------------------------|
| Use Case Name   | Chọn độ khó                                                |
| Use Case ID     | FR-002                                                     |
| Actor           | Người chơi                                                 |
| Trigger         | Bấm Easy/Medium/Hard                                       |
| Precondition    | Đang ở màn hình Difficulty                                 |
| Basic Path      | \(1\) Chọn độ khó; (2) Lưu cấu hình; (3) Khởi tạo gameplay |
| Postcondition   | Trận chơi bắt đầu với tham số độ khó tương ứng             |
| Exception Paths | Người chơi đóng ứng dụng                                   |

**3.3 FR-003 --- Chơi game**

| **Trường**        | **Nội dung**                                                                         |
|-------------------|--------------------------------------------------------------------------------------|
| Use Case Name     | Chơi game                                                                            |
| Use Case ID       | FR-003                                                                               |
| Actor             | Người chơi                                                                           |
| Trigger           | Gameplay bắt đầu                                                                     |
| Precondition      | Đã chọn độ khó                                                                       |
| Basic Path        | \(1\) Điều khiển paddle; (2) Bóng di chuyển và va chạm; (3) Gạch bị phá và cộng điểm |
| Alternative Paths | Người chơi không bắt được bóng, bị mất mạng                                          |
| Postcondition     | Trận tiếp tục hoặc chuyển Game Over                                                  |
| Exception Paths   | Thoát ứng dụng đột ngột                                                              |

**3.4 FR-004 --- Xử lý va chạm, mạng và power-up**

| **Trường**        | **Nội dung**                                                                        |
|-------------------|-------------------------------------------------------------------------------------|
| Use Case Name     | Xử lý va chạm, mạng và power-up                                                     |
| Use Case ID       | FR-004                                                                              |
| Actor             | Hệ thống / Người chơi                                                               |
| Trigger           | Bóng chạm gạch hoặc paddle chạm power-up                                            |
| Precondition      | Gameplay đang hoạt động                                                             |
| Basic Path        | \(1\) Xử lý va chạm bóng; (2) Giảm HP gạch; (3) Sinh power-up; (4) Áp dụng hiệu ứng |
| Alternative Paths | Power-up rơi khỏi vùng chơi thì bị hủy                                              |
| Postcondition     | Trạng thái game cập nhật đúng theo logic                                            |
| Exception Paths   | Không có power-up sinh ra                                                           |

**3.5 FR-005 --- Tính điểm và lưu high score**

| **Trường**      | **Nội dung**                                                                                 |
|-----------------|----------------------------------------------------------------------------------------------|
| Use Case Name   | Tính điểm và lưu high score                                                                  |
| Use Case ID     | FR-005                                                                                       |
| Actor           | Hệ thống                                                                                     |
| Trigger         | Phá gạch hoặc kết thúc trận                                                                  |
| Precondition    | Đang trong hoặc vừa kết thúc gameplay                                                        |
| Basic Path      | \(1\) Cộng điểm theo loại gạch; (2) So sánh high score theo độ khó; (3) Ghi file nếu cao hơn |
| Postcondition   | Điểm phiên và điểm cao được cập nhật                                                         |
| Exception Paths | Lỗi ghi file điểm                                                                            |

**3.6 FR-006 --- Game Over và điều hướng sau trận**

| **Trường**        | **Nội dung**                                                                                  |
|-------------------|-----------------------------------------------------------------------------------------------|
| Use Case Name     | Game Over và điều hướng sau trận                                                              |
| Use Case ID       | FR-006                                                                                        |
| Actor             | Người chơi / Hệ thống                                                                         |
| Trigger           | Hết mạng                                                                                      |
| Precondition      | Gameplay đang chạy                                                                            |
| Basic Path        | \(1\) Dừng game loop; (2) Hiển thị Game Over + score; (3) Người chơi chọn hành động tiếp theo |
| Alternative Paths | Play Again / Back to Menu / Exit                                                              |
| Postcondition     | Chuyển màn đúng theo lựa chọn                                                                 |
| Exception Paths   | Lỗi tải màn Game Over                                                                         |

**3.7 FR-007 --- Cài đặt âm lượng**

| **Trường**      | **Nội dung**                                                       |
|-----------------|--------------------------------------------------------------------|
| Use Case Name   | Cài đặt âm lượng                                                   |
| Use Case ID     | FR-007                                                             |
| Actor           | Người chơi                                                         |
| Trigger         | Bấm Settings                                                       |
| Precondition    | Đang ở Main Menu                                                   |
| Basic Path      | \(1\) Mở popup; (2) Kéo slider; (3) Áp dụng volume; (4) Đóng popup |
| Postcondition   | Âm lượng mới được lưu trong cấu hình runtime                       |
| Exception Paths | Không tải được popup settings                                      |

**3.8 Sơ đồ Use Case**

**Hình 3.8 --- Use Case Diagram: Hệ thống game Arkanoid**

![PlantUML Diagram](media/image1.png){width="4.716666666666667in" height="6.758333333333334in"}

**3.9 Biểu đồ hoạt động**

**3.9.1 Luồng chính: Menu → Difficulty → Gameplay → Game Over**

**Hình 3.9.1 --- Activity Diagram: Luồng chơi tổng quát**

![PlantUML Diagram](media/image2.png){width="3.4930555555555554in" height="9.0in"}

**3.9.2 Luồng chơi game và xử lý điểm số**

**Hình 3.9.2 --- Activity Diagram: Gameplay + Score + High Score**

![PlantUML Diagram](media/image3.png){width="4.025in" height="9.0in"}

**3.9.3 Luồng cài đặt âm lượng**

**Hình 3.9.3 --- Activity Diagram: Settings Volume**

![PlantUML Diagram](media/image4.png){width="2.8in" height="7.116666666666666in"}

**4. Yêu cầu phi chức năng**

**4.1 Hiệu năng**

- Phản hồi phím điều khiển: tối đa 0.1--0.2 giây.

- Thời gian tải menu: dưới 3 giây.

- Chuyển màn hình: dưới 1 giây.

- Gameplay mục tiêu mượt, hạn chế giật/khựng trên máy cấu hình trung bình.

**4.2 Độ ổn định và tin cậy**

- Không crash trong luồng chơi thông thường.

- Trạng thái life/score reset đúng khi bắt đầu ván mới.

- Xử lý lỗi tài nguyên ở mức an toàn (không treo ứng dụng).

**4.3 Khả dụng (Usability)**

- UI trực quan, ít thao tác.

- Điều khiển đơn giản.

- Có phản hồi rõ khi game over và khi thao tác menu.

**4.4 Bảo trì và mở rộng**

- Tách lớp logic game, cấu hình, âm thanh, màn hình.

- Dễ thêm level mới hoặc leaderboard online ở bản sau.

**4.5 Bảo mật**

- Phiên bản hiện tại không truyền dữ liệu qua mạng.

- Dữ liệu điểm là local, không chứa thông tin nhạy cảm.

**5. Giao diện đối ngoại**

**5.1 Giao diện người dùng**

- Main Menu (Start/Settings/Exit)

- Difficulty (Easy/Medium/Hard)

- Gameplay screen

- Game Over screen

- Settings popup

**5.2 Giao diện phần mềm**

- JavaFX FXML + Controller.

- Audio qua MediaPlayer/AudioClip.

- Lưu điểm qua file local.

**5.3 Giao diện phần cứng**

- Màn hình hiển thị.

- Bàn phím.

- Loa/tai nghe (tùy chọn).

**5.4 Giao diện giao tiếp**

- Không yêu cầu giao tiếp mạng ở phiên bản hiện tại.

**6. Tóm tắt ca sử dụng (Use case summary)**

| **ID** | **Tên Use Case**           | **Tác nhân**        | **Mô tả**                          |
|--------|----------------------------|---------------------|------------------------------------|
| FR-001 | Vào game từ menu           | Người chơi          | Chuyển từ menu sang chọn độ khó    |
| FR-002 | Chọn độ khó                | Người chơi          | Cấu hình difficulty cho phiên chơi |
| FR-003 | Chơi game                  | Người chơi          | Điều khiển paddle, phá gạch        |
| FR-004 | Xử lý va chạm & power-up   | Hệ thống/Người chơi | Cập nhật vật lý, hiệu ứng vật phẩm |
| FR-005 | Tính điểm & lưu high score | Hệ thống            | Cộng điểm và cập nhật kỷ lục       |
| FR-006 | Game Over & điều hướng     | Người chơi/Hệ thống | Kết thúc trận và chọn hành động    |
| FR-007 | Cài đặt âm lượng           | Người chơi          | Điều chỉnh âm lượng trong popup    |

**7. Phạm vi dự án, các ràng buộc và giới hạn**

**7.1 Những gì trong dự án**

- Gameplay Arkanoid một người chơi.

- Độ khó, điểm số, mạng, power-up.

- Màn hình menu, game over, settings.

- Lưu high score local theo từng độ khó.

**7.2 Những gì KHÔNG nằm trong phạm vi dự án**

- Multiplayer.

- Đăng nhập/đăng ký.

- Leaderboard online/cloud sync.

- In-app purchase.

**7.3 Ràng buộc**

- Dùng Java + JavaFX.

- Hoàn thành theo timeline môn học.

- Tài nguyên phải hợp lệ bản quyền.

**7.4 Giới hạn**

- Chỉ lưu điểm cục bộ.

- Chưa có hệ thống cấp độ phức tạp.

- Chưa hỗ trợ cạnh tranh thời gian thực.

**Phụ lục A: Bảng thuật ngữ**

| **Term**   | **Meaning**                        |
|------------|------------------------------------|
| Arkanoid   | Game phá gạch với bóng và paddle   |
| Paddle     | Thanh đỡ điều khiển bởi người chơi |
| Brick      | Mục tiêu bị phá để cộng điểm       |
| Life       | Số mạng còn lại                    |
| Score      | Điểm trong lượt chơi hiện tại      |
| High Score | Điểm cao nhất theo độ khó          |
| Power-up   | Vật phẩm tăng cường                |
| JavaFX     | Nền tảng giao diện desktop Java    |

**Phụ lục B: Phê duyệt tài liệu & Thỏa thuận pháp lý**

**B.1 Tham chiếu hợp đồng**

| **Trường**             | **Chi tiết**                         |
|------------------------|--------------------------------------|
| Số tham chiếu hợp đồng | \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_ |
| Ngày hợp đồng          | \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_ |
| Nhà phát triển         | \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_ |
| Khách hàng             | \_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_ |
| Tên dự án              | Hệ thống Game Arkanoid               |
| Phiên bản SRS          | 1.0                                  |
| Ngày phát hành SRS     | Tháng 5 - 2026                       |

**B.2 Xác nhận phạm vi**

Khi ký tài liệu này, hai bên xác nhận phạm vi Mục 1--7 là phạm vi đầy đủ đã thống nhất.

**B.3 Quản lý thay đổi**

Mọi thay đổi phải có yêu cầu bằng văn bản (Change Request), đánh giá tác động và được hai bên phê duyệt trước khi thực hiện.

**B.4 Tiêu chí nghiệm thu**

- Các FR hoạt động đúng theo mô tả.

- NFR đạt mức chấp nhận trong môi trường mục tiêu.

- Khách hàng xác nhận hoàn thành.

**B.5 Chữ ký phê duyệt**

| **Nhà phát triển**            | **Khách hàng**                |
|-------------------------------|-------------------------------|
| Họ tên: \_\_\_\_\_\_\_\_\_\_  | Họ tên: \_\_\_\_\_\_\_\_\_\_  |
| Chức vụ: \_\_\_\_\_\_\_\_\_\_ | Chức vụ: \_\_\_\_\_\_\_\_\_\_ |
| Chữ ký: \_\_\_\_\_\_\_\_\_\_  | Chữ ký: \_\_\_\_\_\_\_\_\_\_  |
| Ngày: \_\_\_\_\_\_\_\_\_\_    | Ngày: \_\_\_\_\_\_\_\_\_\_    |
