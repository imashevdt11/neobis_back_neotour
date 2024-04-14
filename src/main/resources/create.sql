CREATE OR REPLACE FUNCTION update_timestamp()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- USERS

CREATE SEQUENCE user_id_seq;

CREATE TABLE users (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('user_id_seq'),
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    image_url TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_users_updated_at
    BEFORE UPDATE ON users
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp();


-- TOURS

CREATE SEQUENCE tour_id_seq;

CREATE TABLE tours (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('tour_id_seq'),
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    image_url TEXT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_tours_updated_at
    BEFORE UPDATE ON tours
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();


-- REVIEWS

CREATE SEQUENCE review_id_seq;

CREATE TABLE reviews (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('review_id_seq'),
    user_id BIGINT NOT NULL REFERENCES users(id),
    tour_id BIGINT NOT NULL REFERENCES tours(id),
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_reviews_updated_at
    BEFORE UPDATE ON reviews
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();


-- BOOKINGS

CREATE SEQUENCE booking_id_seq;

CREATE TYPE booking_status AS ENUM ('PENDING', 'CONFIRMED', 'CANCELED');

CREATE TABLE bookings (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('booking_id_seq'),
    user_id BIGINT NOT NULL REFERENCES users(id),
    tour_id BIGINT NOT NULL REFERENCES tours(id),
    booking_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    number_of_tourists INT NOT NULL CHECK (number_of_tourists > 0),
    total_price DECIMAL(10,2) NOT NULL,
    status booking_status NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TRIGGER update_bookings_updated_at
    BEFORE UPDATE ON bookings
    FOR EACH ROW
EXECUTE FUNCTION update_timestamp();