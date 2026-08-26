INSERT INTO users (email, name, role, password)
VALUES (
    'test@email.com',
    'Default Admin',
    'ADMIN',
    '$2a$10$5xmCtH2EMRmWs7PwyRdUSOblWewxJAXI0f6Q4RV4axq99mF5fmdgS'
)
ON CONFLICT (email) DO NOTHING;
