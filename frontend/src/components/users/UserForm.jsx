function UserForm({
    email,
    setEmail,

    name,
    setName,

    password,
    setPassword,

    role,
    setRole,

    createUser
}) {

    return (
        <>

            <h2>User Management</h2>

            <div>
                <label>Email</label>
                <br />
                <input
                    data-testid="email-input"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>

            <br />

            <div>
                <label>Name</label>
                <br />
                <input
                    data-testid="name-input"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
            </div>

            <br />

            <div>
                <label>Password</label>
                <br />
                <input
                    type="password"
                    data-testid="password-input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>

            <br />

            <div>
                <label>Role</label>
                <br />
                <select
                    data-testid="role-select"
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                >
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
            </div>

            <br />

            <button
                data-testid="create-user-button"
                onClick={createUser}
            >
                Create User
            </button>

            <hr />

        </>
    );

}

export default UserForm;