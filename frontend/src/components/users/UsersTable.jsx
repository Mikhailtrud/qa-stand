function UsersTable({ users, deleteUser }) {

    return (

        <>

            <h2>Users</h2>

            <table border="1" data-testid="users-table">

                <thead>
                <tr>
                    <th>ID</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>

                {users.map((user) => (

                    <tr
                        key={user.id}
                        data-testid={"user-row-" + user.id}
                    >

                        <td>{user.id}</td>
                        <td>{user.email}</td>
                        <td>{user.name}</td>
                        <td>{user.role}</td>

                        <td>

                            <button
                                data-testid={"delete-user-" + user.id}
                                onClick={() => deleteUser(user.id)}
                            >
                                Delete
                            </button>

                        </td>

                    </tr>

                ))}

                </tbody>

            </table>

            <hr />

        </>

    );

}

export default UsersTable;