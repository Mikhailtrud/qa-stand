import Layout from "../../components/layout/Layout";
import UserForm from "../../components/users/UserForm";
import UsersTable from "../../components/users/UsersTable";

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

    createUser,
    deleteUser
}) {

    return (

        <Layout>

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

            <UsersTable
                users={users}
                deleteUser={deleteUser}
            />

        </Layout>

    );

}

export default UsersPage;