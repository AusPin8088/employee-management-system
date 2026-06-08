INSERT INTO departments (id, name) VALUES
    (1, 'Engineering'),
    (2, 'Finance'),
    (3, 'Human Resources'),
    (4, 'Operations'),
    (5, 'Sales');

INSERT INTO employees (
    first_name,
    last_name,
    email,
    job_title,
    salary,
    status,
    department_id,
    created_at,
    updated_at
) VALUES
    ('Alicia', 'Tan', 'alicia.tan@example.com', 'Backend Developer', 5200.00, 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Ben', 'Lee', 'ben.lee@example.com', 'QA Engineer', 4300.00, 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Carla', 'Ng', 'carla.ng@example.com', 'DevOps Engineer', 6100.00, 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Daniel', 'Lim', 'daniel.lim@example.com', 'Finance Analyst', 4800.00, 'ACTIVE', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Fiona', 'Chua', 'fiona.chua@example.com', 'Accountant', 4600.00, 'INACTIVE', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Grace', 'Wong', 'grace.wong@example.com', 'HR Executive', 4200.00, 'ACTIVE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Harris', 'Ong', 'harris.ong@example.com', 'Recruiter', 4000.00, 'ACTIVE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Irene', 'Koh', 'irene.koh@example.com', 'Operations Manager', 6800.00, 'ACTIVE', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Jason', 'Teo', 'jason.teo@example.com', 'Logistics Coordinator', 3900.00, 'INACTIVE', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Mei', 'Lau', 'mei.lau@example.com', 'Sales Executive', 4500.00, 'ACTIVE', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Nadia', 'Rahman', 'nadia.rahman@example.com', 'Account Manager', 5900.00, 'ACTIVE', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Owen', 'Goh', 'owen.goh@example.com', 'Solutions Consultant', 6400.00, 'ACTIVE', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
