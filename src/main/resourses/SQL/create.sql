
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phoneNumber VARCHAR(15) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'user'
);
 
CREATE TABLE membership (
    membershipId SERIAL PRIMARY KEY,
    membershipType VARCHAR(50) NOT NULL,
    membershipDescription TEXT NOT NULL,
    membershipcost DECIMAL(10, 2) NOT NULL,
    memberId INTEGER NOT NULL,
    FOREIGN KEY (memberId) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE workoutClasses (
    workoutClassId SERIAL PRIMARY KEY,
    workoutClassType VARCHAR(50) NOT NULL,
    workoutClassDescription TEXT NOT NULL,
    trainerId INTEGER NOT NULL,
    FOREIGN KEY (trainerId) REFERENCES users(id) ON DELETE CASCADE
)
   