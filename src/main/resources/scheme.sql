CREATE SEQUENCE user_id_seq;

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
    username VARCHAR(30) NOT NULL UNIQUE,
    image_url TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE SEQUENCE tour_id_seq;

CREATE TABLE tours (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('tour_id_seq'),
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    image_url TEXT NOT NULL,
    city VARCHAR(30) NOT NULL,
    country VARCHAR(30) NOT NULL,
    continent VARCHAR(30) NOT NULL,
    season VARCHAR(30) NOT NULL,
    views INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE SEQUENCE review_id_seq;

CREATE TABLE reviews (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('review_id_seq'),
    user_id BIGINT NOT NULL REFERENCES users(id),
    tour_id BIGINT NOT NULL REFERENCES tours(id),
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE SEQUENCE booking_id_seq;

CREATE TABLE bookings (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('booking_id_seq'),
    tour_id BIGINT NOT NULL REFERENCES tours(id),
    phone_number VARCHAR(20) NOT NULL,
    number_of_tourists INT NOT NULL CHECK (number_of_tourists > 0),
    comment TEXT DEFAULT NUll,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE SEQUENCE image_id_seq;

CREATE TABLE images (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('image_id_seq'),
    name_image VARCHAR(20) NOT NULL,
    url_image TEXT NOT NULL
);