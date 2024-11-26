use vti_cinema;
-- //Account
INSERT INTO account (username, fullname, birth_date, email, password, phone_number, role, status, verification_code)
VALUES
-- password là 123 
('user1', 'Nguyễn Văn A', '1990-01-01', 'user1@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '1234567890', 'USER', 'ACTIVE', '123456'),
('user2', 'Nguyễn Văn C', '1985-02-14', 'user2@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '2345678901', 'ADMIN', 'ACTIVE', '234567'),
('user3', 'Nguyễn văn B', '1992-03-21', 'user3@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '3456789012', 'MANAGER', 'INACTIVE', '345678'),
('user4', 'Nguyễn Văn D', '1988-04-18', 'user4@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '4567890123', 'ADMIN', 'ACTIVE', '456789'),
('user5', 'Nguyễn Văn E', '1995-05-25', 'user5@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '5678901234', 'USER', 'ACTIVE', '567890'),
('user6', 'Nguyễn Văn R', '1980-06-30', 'user6@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '6789012345', 'USER', 'INACTIVE', '678901'),
('user7', 'Nguyễn Văn T', '1998-07-12', 'user7@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '7890123456', 'ADMIN', 'ACTIVE', '789012'),
('user8', 'Nguyễn Văn F', '1991-08-20', 'user8@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '8901234567', 'USER', 'ACTIVE', '890123'),
('user9', 'Nguyễn Văn Y', '1993-09-09', 'user9@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '9012345678', 'ADMIN', 'INACTIVE', '901234'),
('user10', 'Nguyễn Văn O', '1996-10-05', 'user10@example.com', '$2a$12$5RmYj0QwcIt0JC3XDUvOGeSNhA/XaG6ditueUjbUgAFYzGhwVMe1i', '0123456789', 'USER', 'ACTIVE', '012345');

-- //Movie 
INSERT INTO movie (image_movie, name, actor, director, genre, duration, description, language, trailer, start_date, viewing_age, rating, `status`)
VALUES
('https://example.com/movie1.jpg', 'Movie One', 'Actor One', 'Director One', 'ACTION', '02:30:00', 'Action packed movie with thrilling scenes', 'ENGLISH', 'https://example.com/trailer1', '2024-01-01', 'CHILD', 7.5, 'SHOWING'),
('https://example.com/movie2.jpg', 'Movie Two', 'Actor Two', 'Director Two', 'ACTION', '01:45:00', 'A heartwarming drama that touches emotions', 'ENGLISH', 'https://example.com/trailer2', '2024-02-01', 'T15', 8.2, 'SHOWING'),
('https://example.com/movie3.jpg', 'Movie Three', 'Actor Three', 'Director Three', 'ACTION', '01:50:00', 'Comedy movie with hilarious moments', 'VIETNAMESE', 'https://example.com/trailer3', '2024-03-01', 'T15', 6.8, 'SHOWING'),
('https://example.com/movie4.jpg', 'Movie Four', 'Actor Four', 'Director Four', 'ACTION', '02:00:00', 'Romantic movie that will make you cry', 'ENGLISH', 'https://example.com/trailer4', '2024-04-01', 'T18', 7.0, 'COMING_SOON'),
('https://example.com/movie5.jpg', 'Movie Five', 'Actor Five', 'Director Five', 'ACTION', '01:30:00', 'Horror movie with shocking scares', 'ENGLISH', 'https://example.com/trailer5', '2024-05-01', 'T15', 8.0, 'COMING_SOON'),
('https://example.com/movie6.jpg', 'Movie Six', 'Actor Six', 'Director Six', 'ACTION', '02:15:00', 'Sci-fi adventure into the unknown', 'ENGLISH', 'https://example.com/trailer6', '2024-06-01', 'CHILD', 7.3, 'COMING_SOON'),
('https://example.com/movie7.jpg', 'Movie Seven', 'Actor Seven', 'Director Seven', 'ACTION', '02:10:00', 'Action-packed journey through time', 'ENGLISH', 'https://example.com/trailer7', '2024-07-01', 'T18', 8.5, 'SPECIAL'),
('https://example.com/movie8.jpg', 'Movie Eight', 'Actor Eight', 'Director Eight', 'ACTION', '01:40:00', 'Suspense thriller with unexpected twists', 'ENGLISH', 'https://example.com/trailer8', '2024-08-01', 'T15', 7.6, 'SPECIAL'),
('https://example.com/movie9.jpg', 'Movie Nine', 'Actor Nine', 'Director Nine', 'ACTION', '01:55:00', 'A touching story of love and loss', 'ENGLISH', 'https://example.com/trailer9', '2024-09-01', 'T15', 8.1, 'SPECIAL'),

-- //Banner
INSERT INTO banner (image_url, title, description, movie_id, is_Active)
VALUES
('https://example.com/banner1.jpg', 'Banner 1', 'Description of Banner 1', 1, true),
('https://example.com/banner2.jpg', 'Banner 2', 'Description of Banner 2', 2, true),
('https://example.com/banner3.jpg', 'Banner 3', 'Description of Banner 3', 3, true),
('https://example.com/banner4.jpg', 'Banner 4', 'Description of Banner 4', 4, true),
('https://example.com/banner5.jpg', 'Banner 5', 'Description of Banner 5', 5, true),
('https://example.com/banner6.jpg', 'Banner 6', 'Description of Banner 6', 6, true),
('https://example.com/banner7.jpg', 'Banner 7', 'Description of Banner 7', 7, false),
('https://example.com/banner8.jpg', 'Banner 8', 'Description of Banner 8', 8, false),
('https://example.com/banner9.jpg', 'Banner 9', 'Description of Banner 9', 9, false),
('https://example.com/banner10.jpg', 'Banner 10', 'Description of Banner 10', 10, false);

-- //Cinema
INSERT INTO cinema (name, location, status)
VALUES
    ('Cinema Nguyễn Du', 'Hanoi', 'OPEN'),
    ('Cinema Đông Anh', 'Ho Chi Minh City', 'OPEN'),
    ('Cinema Trần Đại Nghĩa', 'Da Nang', 'CLOSED'),
    ('Cinema Hà Đông', 'Hanoi', 'OPEN'),
    ('Cinema Vũng Tàu', 'Ho Chi Minh City', 'CLOSED'),
    ('Cinema Hà Nội', 'Hue', 'OPEN'),
    ('Cinema Hà Tĩnh', 'Can Tho', 'OPEN'),
    ('Cinema Hưng Yên', 'Ha Long', 'CLOSED'),
    ('Cinema Bình Định', 'Hanoi', 'OPEN'),
    ('Cinema Yên Bái', 'Ho Chi Minh City', 'CLOSED');

-- //Room
INSERT INTO room (name, cinema_id, status, screen_type)
VALUES
('Room 1', 1, 'AVAILABLE', 'IMAX'),
('Room 2', 1, 'MAINTENANCE', 'NORMAL'),
('Room 3', 2, 'AVAILABLE', 'NORMAL'),
('Room 4', 2, 'AVAILABLE', 'NORMAL'),
('Room 5', 3, 'BOOKED', 'IMAX'),
('Room 6', 3, 'AVAILABLE', 'IMAX'),
('Room 7', 4, 'AVAILABLE', 'NORMAL'),
('Room 1', 4, 'BOOKED', 'NORMAL'),
('Room 2', 5, 'AVAILABLE', 'IMAX'),
('Room 3', 5, 'AVAILABLE', 'NORMAL');

-- // Seat
INSERT INTO seat (name, seat_type, price)
VALUES
('Seat 1', 'DOUBLE', 200000.0),
('Seat 2', 'SINGLE', 150000.0),
('Seat 3', 'VIP', 300000.0),
('Seat 4', 'DOUBLE', 200000.0),
('Seat 5', 'SINGLE', 150000.0),
('Seat 6', 'VIP', 300000.0),
('Seat 7', 'SINGLE', 150000.0),
('Seat 8', 'DOUBLE', 200000.0),
('Seat 9', 'VIP', 300000.0),
('Seat 10', 'SINGLE', 150000.0);

-- // SeatRoom
INSERT INTO seat_room (room_id, type_seat, status)
VALUES
(1, 'DOUBLE', 'AVAILABLE'),
(2, 'SINGLE', 'OCCUPIED'),
(3, 'VIP', 'AVAILABLE'),
(4, 'DOUBLE', 'OCCUPIED'),
(5, 'SINGLE', 'AVAILABLE'),
(6, 'VIP', 'OCCUPIED'),
(7, 'SINGLE', 'AVAILABLE'),
(8, 'DOUBLE', 'OCCUPIED'),
(9, 'VIP', 'AVAILABLE'),
(10, 'SINGLE', 'OCCUPIED');


-- //Row Seat
INSERT INTO row_seat (seat_room_id, row_name)
VALUES
(1, 'A'),
(1, 'B'),
(2, 'C'),
(3, 'D'),
(4, 'E'),
(5, 'F'),
(6, 'G'),
(7, 'H'),
(8, 'I'),
(9, 'J');

-- // ShowTime
INSERT INTO showtime (movie_id, room_id, cinema_id, show_date)
VALUES
(1, 2, 1, '2024-11-22'),
(2, 3, 1, '2024-11-22'),
(3, 4, 2, '2024-11-23'),
(4, 5, 2, '2024-11-23'),
(5, 6, 3, '2024-11-24'),
(6, 7, 3, '2024-11-24'),
(7, 8, 4, '2024-11-25'),
(8, 9, 4, '2024-11-25'),
(9, 10, 5, '2024-11-26'),
(10, 11, 5, '2024-11-26');

-- //List start time
INSERT INTO list_start_time (showtime_id, start_time)
VALUES
(1, '10:00:00'),
(1, '14:00:00'),
(1, '18:00:00'),
(2, '11:00:00'),
(2, '15:00:00'),
(2, '19:00:00'),
(3, '12:00:00'),
(3, '16:00:00'),
(3, '20:00:00'),
(4, '13:00:00');

-- //Ticket
INSERT INTO ticket (account_id, seat_room_id, show_time_id, total_price, status)
VALUES
(1, 2, 3, 200000.0, 'PAID'),
(2, 4, 5, 250000.0, 'UNPAID'),
(3, 5, 6, 300000.0, 'PAID'),
(4, 6, 7, 150000.0, 'PAID'),
(5, 7, 8, 200000.0, 'CANCELLED');

-- //Voucher 
INSERT INTO voucher (name, description, discount, expiry, status)
VALUES
('Giảm giá 20%', 'Voucher giảm giá 20% cho lần mua tiếp theo', 20.0, '2024-12-31', 'EXPIRED'),
('Voucher đặc biệt', 'Voucher giảm 10% cho toàn bộ đơn hàng', 10.0, '2024-11-30', 'EFFECTIVE'),
('Voucher nghỉ lễ', 'Voucher giảm giá cho dịp lễ', 15.0, '2024-12-25', 'EXPIRED');

-- //More  Service
INSERT INTO more_service (name, image, decription, price, status)
VALUES
('Popcorn', 'https://example.com/popcorn.jpg', 'Crispy and delicious popcorn', 5.0, 'AVAILABLE'),
('Drink', 'https://example.com/drink.jpg', 'Soft drinks of various types', 3.0, 'AVAILABLE'),
('3D Glasses', 'https://example.com/3d_glasses.jpg', 'High-quality 3D glasses for a better movie experience', 2.0, 'AVAILABLE'),
('Snack Combo', 'https://example.com/snack_combo.jpg', 'Combo of chips and soda', 6.5, 'OUT_OF_STOCK'),
('VIP Seat', 'https://example.com/vip_seat.jpg', 'Premium comfortable seating in the front row', 10.0, 'AVAILABLE'),
('Movie Poster', 'https://example.com/movie_poster.jpg', 'Exclusive posters of the movie', 4.5, 'AVAILABLE'),
('Private Screening', 'https://example.com/private_screening.jpg', 'Private screening for VIP guests', 100.0, 'AVAILABLE'),
('Gift Voucher', 'https://example.com/gift_voucher.jpg', 'Gift vouchers for cinema tickets', 20.0, 'AVAILABLE'),
('Seat Reservation', 'https://example.com/seat_reservation.jpg', 'Reserve your favorite seat in advance', 2.5, 'AVAILABLE'),
('Food Combo', 'https://example.com/food_combo.jpg', 'Delicious combo meal with a sandwich and a drink', 8.0, 'OUT_OF_STOCK');

-- // Payment
INSERT INTO Payment (method, address_tranfer, qr_code, status)
VALUES
('E_WALLET', '1234 Payment St, City, Country', 'https://example.com/qr1', 'ACTIVE'),
('BANKING', '5678 Transfer Rd, City, Country', 'https://example.com/qr2', 'ACTIVE'),
('BANKING', '9101 Cash Ave, City, Country', 'https://example.com/qr3', 'INACTIVE'),
('E_WALLET', '1213 Web Transfer Blvd, City, Country', 'https://example.com/qr4', 'INACTIVE'),
('BANKING', '1415 Bank Road, City, Country', 'https://example.com/qr5', 'INACTIVE'),
('BANKING', '1617 Payment St, City, Country', 'https://example.com/qr6', 'INACTIVE'),
('BANKING', '1819 Debit Blvd, City, Country', 'https://example.com/qr7', 'INACTIVE'),
('E_WALLET', '2021 Digital Transfer Ln, City, Country', 'https://example.com/qr8', 'INACTIVE'),
('E_WALLET', '2223 Cash St, City, Country', 'https://example.com/qr9', 'INACTIVE'),
('E_WALLET', '2425 Bank Plaza, City, Country', 'https://example.com/qr10', 'INACTIVE');

-- //Booking
INSERT INTO booking (account_id, ticket_id, more_service_id, voucher_id, total_price, status)
VALUES
(1, 1, 1, 1, 100.50, 'SUCCESS'),
(2, 2, 2, 2, 200.75, 'SUCCESS'),
(3, 3, 3, 3, 150.25, 'CANCELLED'),
(4, 4, 1, 2, 180.00, 'PENDING'),
(5, 5, 2, 1, 250.00, 'SUCCESS'),
(6, 2, 2, 2, 125.40, 'PENDING'),
(7, 3, 3, 1, 90.00, 'SUCCESS'),
(8, 4, 1, 2, 300.00, 'PENDING'),
(9, 1, 2, 1, 200.50, 'CANCELLED'),
(10, 2, 1, 1, 400.75, 'SUCCESS');

-- //Receipt
INSERT INTO receipt (type, booking_id, account_id, reason, amount, status)
VALUES
('REFUND', 1, 1, 'Refund for canceled booking', 100.00, 'PROCESSED'),
('RECEIPT', 2, 2, 'Payment for ticket purchase', 50.00, 'PROCESSED'),
('RECEIPT', 3, 3, 'Payment for service', 30.00, 'UNPROCESSED'),
('REFUND', 4, 4, 'Partial refund for booking change', 20.00, 'PROCESSED'),
('RECEIPT', 5, 5, 'Payment for ticket and snacks', 75.00, 'PROCESSED'),
('REFUND', 6, 6, 'Refund for booking cancellation', 120.00, 'PROCESSED'),
('RECEIPT', 7, 7, 'Payment for VIP ticket', 150.00, 'PROCESSED'),
('RECEIPT', 8, 8, 'Payment for booking with extra services', 200.00, 'PROCESSED'),
('REFUND', 9, 9, 'Refund for error in booking', 50.00, 'PROCESSED'),
('RECEIPT', 10, 10, 'Payment for multiple tickets', 180.00, 'PROCESSED');