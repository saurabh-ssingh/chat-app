CREATE TABLE chat_users (
    id SERIAL PRIMARY KEY,
    cognito_user_name VARCHAR(100) NOT NULL UNIQUE,
    display_name VARCHAR(100),
    email VARCHAR(150) NOT NULL,
    profile_pic_url VARCHAR(255),
    online BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP,
    last_seen TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE chat_messages (
    id BIGSERIAL PRIMARY KEY,
    sender VARCHAR(100) NOT NULL,
    receiver VARCHAR(100) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for optimized queries
CREATE INDEX idx_receiver_created
    ON chat_messages (receiver, created_at);

CREATE INDEX idx_sr_created
    ON chat_messages (sender, receiver, created_at);





