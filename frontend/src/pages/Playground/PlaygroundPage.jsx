import Layout from "../../components/layout/Layout";
import QAPlayground from "../../components/QAPlayground";

function PlaygroundPage({ onLogout }) {

    return (

        <Layout onLogout={onLogout}>

            <QAPlayground />

        </Layout>

    );

}

export default PlaygroundPage;
