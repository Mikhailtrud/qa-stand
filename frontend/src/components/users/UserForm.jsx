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

    createUser,
    updateUser,
    cancelEdit,
    editing
}) {

    const isValid =
        email.trim() !== "" &&
        name.trim() !== "" &&
        (editing || password.trim() !== "");

    return (
        <>

            <h2>{editing ? "Edit User" : "User Management"}</h2>

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
                <label>Password{editing ? " (optional)" : ""}</label>
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
                onClick={editing ? updateUser : createUser}
                disabled={!isValid}
            >
                {editing ? "Save Changes" : "Create User"}
            </button>

            {editing && (
                <button
                    className="form-button secondary-button"
                    data-testid="cancel-edit-user"
                    onClick={cancelEdit}
                >
                    Cancel
                </button>
            )}

            <hr />

        </>
    );

}

export default UserForm;
