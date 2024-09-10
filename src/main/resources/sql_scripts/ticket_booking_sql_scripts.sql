show databases;
use test_db;
CREATE TABLE cities (
    city_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);
INSERT INTO cities (name) VALUES 
('New York'),
('Los Angeles'),
('San Francisco'),
('Chicago');

CREATE TABLE theatres (
    theatre_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    city_id BIGINT,
    total_screens INT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities(city_id) ON DELETE CASCADE
);
INSERT INTO theatres (name, city_id, total_screens) VALUES 
('IMAX New York', 1, 5),
('Regal LA', 2, 7),
('Cinemark SF', 3, 6),
('AMC Chicago', 4, 8);

CREATE TABLE movies (
    movie_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100) NOT NULL
    -- Add fields for images, videos (CDN links)
);
INSERT INTO movies (name, title, genre) VALUES 
('Inception', 'Inception', 'Sci-Fi'),
('Titanic', 'Titanic', 'Romance'),
('Avengers: Endgame', 'Avengers: Endgame', 'Action'),
('The Dark Knight', 'The Dark Knight', 'Action');
select * from movies;

CREATE TABLE shows (
    show_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    theatre_id BIGINT,
    movie_id BIGINT,
    show_date DATE NOT NULL,
    show_start_time TIME NOT NULL,
    show_end_time TIME NOT NULL,
    available_seats INT CHECK (available_seats >= 0),
    FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id) ON DELETE CASCADE,
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
);
INSERT INTO shows (theatre_id, movie_id, show_date, show_start_time, show_end_time, available_seats) VALUES 
(1, 1, '2024-09-11', '14:00:00', '16:30:00', 200),
(1, 2, '2024-09-11', '17:00:00', '19:30:00', 150),
(2, 3, '2024-09-11', '18:00:00', '20:30:00', 250),
(3, 4, '2024-09-11', '15:00:00', '17:30:00', 100);
select * from shows;

CREATE TABLE seats (
    seat_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    theatre_id BIGINT,
    city_id BIGINT,
    screen_number INT NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (theatre_id) REFERENCES theatres(theatre_id) ON DELETE CASCADE,
    FOREIGN KEY (city_id) REFERENCES cities(city_id) ON DELETE CASCADE
);

INSERT INTO seats (theatre_id, city_id, screen_number, seat_number, is_available) VALUES 
(1, 1, 1, 'A1', TRUE),
(1, 1, 1, 'A2', TRUE),
(1, 1, 1, 'A3', TRUE),
(1, 1, 2, 'B1', TRUE),
(2, 2, 1, 'C1', FALSE),
(2, 2, 1, 'C2', TRUE),
(3, 3, 1, 'D1', TRUE),
(4, 4, 1, 'E1', TRUE);
select * from seats;

SELECT 
    t.theatre_id,
    t.name AS theatre_name,
    c.name AS city_name,
    m.movie_id,
    m.name AS movie_name,
    m.title AS movie_title,
    m.genre AS movie_genre,
    s.show_id,
    s.show_date,
    s.show_start_time,
    s.show_end_time,
    s.available_seats
FROM 
    shows s
JOIN 
    theatres t ON s.theatre_id = t.theatre_id
JOIN 
    cities c ON t.city_id = c.city_id
JOIN 
    movies m ON s.movie_id = m.movie_id
WHERE 
    m.movie_id = 1          
    AND c.name = 'New York'  
    AND s.show_date = '2024-09-11'
    AND s.show_start_time = '14:00:00'; 
