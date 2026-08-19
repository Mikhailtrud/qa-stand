import Layout from "../../components/layout/Layout";

import "../../styles/dashboard.css";

function DashboardPage({ onLogout }) {

    return (

        <Layout onLogout={onLogout}>

            <div className="dashboard">

                <div>

                    <h1 className="dashboard-title">
                        QA Sandbox
                    </h1>

                    <p className="dashboard-description">
                        Sandbox for UI, API and Database automation testing.
                    </p>

                </div>

                <div className="dashboard-grid">

                    <div className="dashboard-card">

                        <h3>Backend</h3>

                        <ul>
                            <li>✅ Authentication</li>
                            <li>✅ CRUD Users</li>
                            <li>✅ Validation</li>
                            <li>✅ Exception Handling</li>
                            <li>✅ PostgreSQL</li>
                            <li>✅ Flyway</li>
                            <li>✅ Swagger</li>
                        </ul>

                    </div>

                    <div className="dashboard-card">

                        <h3>Frontend</h3>

                        <ul>
                            <li>✅ React</li>
                            <li>✅ Routing</li>
                            <li>✅ User Management</li>
                            <li>✅ Playground</li>
                            <li>✅ Services Layer</li>
                        </ul>

                    </div>

                    <div className="dashboard-card">

                        <h3>Automation</h3>

                        <ul>
                            <li>⏳ UI Tests (Selenide)</li>
                            <li>⏳ API Tests</li>
                            <li>⏳ Database Tests</li>
                            <li>⏳ Allure Reports</li>
                            <li>⏳ Docker Execution</li>
                        </ul>

                    </div>

                </div>

            </div>

        </Layout>

    );

}

export default DashboardPage;
