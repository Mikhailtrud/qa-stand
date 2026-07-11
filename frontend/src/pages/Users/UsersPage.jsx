import Layout from "../../components/layout/Layout";

import Alert from "../../components/common/Alert";

import UserForm from "../../components/users/UserForm";
import UsersTable from "../../components/users/UsersTable";

import "../../styles/users.css";

function UsersPage({

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
    deleteUser

}) {

    return (

        <Layout>

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
                    />

                </section>

                <section className="users-table-panel">

                    <UsersTable
                        users={users}
                        deleteUser={deleteUser}
                    />

                </section>

            </div>

        </Layout>

    );

}

export default UsersPage;