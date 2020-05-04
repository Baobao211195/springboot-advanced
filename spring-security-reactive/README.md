# Springboot-security and reactive programming

### Chương 1 Tổng quan về spring 5 và spring security 5

#### Những yêu cầu cho những ứng dụng mới.
1. Highly scalable : 
    - Những nền tảng phát triển mạnh mẽ trong suốt thập kỷ qua và con người ngày càng có những hiểu biết vê công nghệ hơn trước.
2. Resilient, fault-tolerant, and highly available :
    - Thời gian chết của ứng dụng là một trong những vấn đề mà các doanh nghiệp chưa thể thích nghi trong thời đại hiện này,
    thời gian chết chỉ vài giây  thôi nhưng nó có thể gây ra rât nhiều vấn đề lớn cho doanh nghiệp
3. High performance: 
    - Nếu ứng dụng của bạn chậm, thông thường mọi người sẽ có xu hứng rời bỏ và tìm kiếm một ứng dụng khác để thay thế.
4. Hyper-personalization:
    - Những người dùng cần 1 website mang tính cá nhân hơn là những website thông thường điều này tạo ra những thách thức
    về phía máy chủ để thực hiện nhiều phân tích cụ cụ thể trong thời gian thực.
#### Reactive programming.
 - Là một mô hình lập trình mà việc xử lý dữ liệu bất đồng bộ dưới dang stream. Luồng dữ liệu được xử lý theo từng phần thay đổi giống
 như là nhưng bản tin (messages). Các message này được tạo ra bởi thành phần gọi là Producer và làm việc push message theo thời gian.
 Subscriber là thành phần theo dõi sự thay đổi theo thời gian của các message như là lấy message, xử lý nó và chuyển message tới thành 
 phần cuối của hệ thống được gọi là Consummer.
 - Về mặt cơ sở dữ liệu, NoSQL là một sự chuyển dịch từ các cơ sở dữ liệu quan hệ. Tương tự như vậy , reactive programming
 là một sự chuyển dịch từ mô hình lập trình (imperative programming).
 #### Reactive applications. 
 - Giới thiệu qua về phần này, chúng ta sẽ điểm qua lại những dự thay đổi quan trọng của requirement của các hệ thống trong nhiều năm qua.
 Để có được điều này , đây là khái niệm của việc phát triển những ứng dụng được gọi là reactive applications.
 Điều này rất quan trọng để hiểu sự khác nhau giữa reactive programming và reactive applications. Việc sử dụng mô hình lập trình reative hông tạo
 ra các ứng dụng reactive, nhưng các khái niệm của mô hình lập trình reactive chắc chắn có thể xây dựng các ứng dụng reactive.
 #### Reactive Manifesto.
 - Reactive manifesto được gọi là quan điểm của các nhà phát triển rằng một ứng dụng có thể được phát triển theo mô hình reactive.
 Và theo như Reactive Manifesto (https://www.reactivemanifesto.org/) thì một reactive system nên là sẵn sàng đáp lại (responsive)
 , có khả năng phục hổi nhanh (resilient) , có tính mềm dẻo (elastic) và hướng message (message-driven).
 1. Responsive.
    - Responsive system được hiểu đơn giản là các hệ thông nhanh chống phát hiện và giải quyết hiệu quả các vấn đề. Các hệ thống này
    cũng cho thời gian phản hồi nhất quan và cũng thiết lập các giới hạn, đảm bảo chỉ số QoS tối thiểu. Do những tính chất trên
    các hệ thống này xây dựng tính bảo mật đối với các end users, đơn giản trong xử lý lỗi và khuyến khích nhiều hơn các tương tác từ phía người dùng cuối
 2. Resilient.
    - Trong các trường hợp lỗi, resilient systems dữ nguyên các tương tác và các phản hồi. Tính Resilience trong 1 ứng dụng có thể đạt được nhờ cá
    tiêu chí sau:
        - Replication: 1 component có thể hoạt động ở nhiều hơn 1 nơi(có thể tạo ra nhiều instances cùng hoạt đồng). Nếu một instance lỗi,
        thì ứng dụng vẫn có thể hoạt động bình thường.
        - Containment/isolation: Các vấn đề của một conponent cụ thể được đóng gói và tách biệt chỉ trong component đó và không gây trở ngại tới
        các component khác hoặc các components trong cùng 1 replication.
        - Delegation: Trong trường hợp gặp issue tại 1 component, nếu không có ảnh hưởng lớn thì việc chuyển giao việc thực hiện
        tới một component tương tự khác đang hoạt động tại một phạm vi hoàn toàn khác.
 3. Elastic.
    - Elastic systems có thể dễ dàng tự động scale khi đầu vào ra tăng hoặc giảm mà ko có bất kỳ sự xáo trộn nào trong hệ thống
    , hệ thống có thể tạo ra các bản sao của một component phân chia tải khi gia tăng. Theo cách này thì hệ thống được thiết kế
    để đảm bảo rằng việc scaling theo yêu cầu, nó có thể được hoàn thành vs chi phí thấp bằng việc thêm phần cứng hoặc các phần mềm ngược lại 
    với việc mua licensed của các phần mềm.
 4. Message-driven.
    - Trong các reactive applications, một khía cạnh quan trọng là việc sự dụng các messages bất đồng bộ để truyển tải dữ liệu giữa các component.
    Điều này mang lại tính Loose coupling giữa các thành phần trong hệ thống.
    Việc tạo ra các messages push message, các subscriber đăng ký để nhận các message, xử lý nó rồi gửi các message này tới các subscibe khác để thử 
    hiện các công việc khác. Đây chính là các khái niệm cốt lõi của mô hình lập trình reactive và là các khái niệm cơ bản của một reactive system.
    ![reactive](image/4_factors.png)
    ![reactive_2](image/4_1_factors.png)
    Hình trên là mối quan hệ giữa các khái niệm trong Reactive Manifesto:
#### Tổng quan Spring framework.    