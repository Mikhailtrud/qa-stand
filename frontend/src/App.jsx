import { useEffect, useState } from "react";

function App() {

  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [role, setRole] = useState("USER");
  const [users, setUsers] = useState([]);

  async function loadUsers() {

    const response = await fetch("http://localhost:8080/users");

    const data = await response.json();

    setUsers(data);
  }

  async function createUser() {

    const response = await fetch("http://localhost:8080/users", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email,
        name,
        role
      })
    });

    const data = await response.json();

    await loadUsers();

    alert(`User created. ID = ${data.id}`);
  }

  useEffect(() => {
    loadUsers();
  }, []);

  return (
    <div style={{ padding: "20px" }}>

      <h1>QA Sandbox</h1>

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
        <label>Role</label>
        <br />
        <select
          data-testid="role-select"
          value={role}
          onChange={(e) => setRole(e.target.value)}
        >
          <option>USER</option>
          <option>ADMIN</option>
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

      <h2>Users</h2>

      <table border="1">
        <thead>
          <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Name</th>
            <th>Role</th>
          </tr>
        </thead>

        <tbody>
          {users.map((user) => (
            <tr key={user.id}>
              <td>{user.id}</td>
              <td>{user.email}</td>
              <td>{user.name}</td>
              <td>{user.role}</td>
            </tr>
          ))}
        </tbody>
      </table>

    </div>
  );
}

export default App;