import "../../styles/header.css";

function Header({ onLogout }) {

    return (

        <header className="header">

            <div>

                <h2 className="header-title">
                    QA Sandbox
                </h2>

                <div className="header-subtitle">
                    Java • Spring Boot • React • PostgreSQL
                </div>

            </div>

            <button
                className="header-logout"
                data-testid="logout-button"
                onClick={onLogout}
            >
                Logout
            </button>

        </header>

    );

}

export default Header;
