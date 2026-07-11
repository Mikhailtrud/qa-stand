import "../../styles/form.css";

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

    const isValid =
        email.trim() !== "" &&
        name.trim() !== "" &&
        password.trim() !== "";

    return (
        <>

            <h2>User Management</h2>

            <div className="form-group">
                <label>Email</label>
                <input
                    className="form-control"
                    data-testid="email-input"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
            </div>

            <div className="form-group">
                <label>Name</label>
                <input
                    className="form-control"
                    data-testid="name-input"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                />
            </div>

            <div className="form-group">
                <label>Password</label>
                <input
                    className="form-control"
                    type="password"
                    data-testid="password-input"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
            </div>

            <div className="form-group">
                <label>Role</label>
                <select
                    className="form-control"
                    data-testid="role-select"
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                >
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
            </div>

            <button
                className="form-button"
                data-testid="create-user-button"
                onClick={createUser}
                disabled={!isValid}
            >
                Create User
            </button>

            <hr />

        </>
    );

}

export default UserForm;