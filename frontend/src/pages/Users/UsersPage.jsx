import Layout from "../../components/layout/Layout";

import Alert from "../../components/common/Alert";

import UserForm from "../../components/users/UserForm";
import UsersTable from "../../components/users/UsersTable";

import "../../styles/users.css";

function UsersPage({

    onLogout,

    email,
    setEmail,

    name,
    setName,

    password,
    setPassword,

    role,
    setRole,

    users,

    message,

    createUser,
    updateUser,
    editUser,
    cancelEdit,
    editingUserId,
    deleteUser

}) {

    return (

        <Layout onLogout={onLogout}>

            <Alert
                message={message}
                type={message.toLowerCase().includes("success") ? "success" : "error"}
            />

            <div className="users-page">

                <section className="users-form">

                    <UserForm
                        email={email}
                        setEmail={setEmail}

                        name={name}
                        setName={setName}

                        password={password}
                        setPassword={setPassword}

                        role={role}
                        setRole={setRole}

                        createUser={createUser}
                        updateUser={updateUser}
                        cancelEdit={cancelEdit}
                        editing={editingUserId !== null}
                    />

                </section>

                <section className="users-table-panel">

                    <UsersTable
                        users={users}
                        editUser={editUser}
                        deleteUser={deleteUser}
                    />

                </section>

            </div>

        </Layout>

    );

}

export default UsersPage;
