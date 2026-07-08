import { Link } from "react-router-dom";

function Sidebar() {

    return (

        <aside
            style={{
                width: "220px",
                borderRight: "1px solid #ccc",
                padding: "20px"
            }}
        >

            <div>
                <Link to="/dashboard">
                    Dashboard
                </Link>
            </div>

            <br />

            <div>
                <Link to="/users">
                    Users
                </Link>
            </div>

            <br />

            <div>
                <Link to="/playground">
                    Playground
                </Link>
            </div>

        </aside>

    );

}

export default Sidebar;