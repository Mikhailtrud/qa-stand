import "./../../styles/layout.css";

import Header from "./Header";
import Sidebar from "./Sidebar";

function Layout({ children, onLogout }) {

    return (

        <div className="layout">

            <Header onLogout={onLogout} />

            <div className="layout-body">

                <Sidebar />

                <main className="layout-content">
                    {children}
                </main>

            </div>

        </div>

    );

}

export default Layout;
