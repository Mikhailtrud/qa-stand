import { Link } from "react-router-dom";

import "../../styles/sidebar.css";

function Sidebar() {

    return (

        <aside className="sidebar">

            <nav className="sidebar-nav">

                <Link
                    className="sidebar-link"
                    to="/dashboard"
                >
                    Dashboard
                </Link>

                <Link
                    className="sidebar-link"
                    to="/users"
                >
                    Users
                </Link>

                <Link
                    className="sidebar-link"
                    to="/playground"
                >
                    Playground
                </Link>

            </nav>

        </aside>

    );

}

export default Sidebar;