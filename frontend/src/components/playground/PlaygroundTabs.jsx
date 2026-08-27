import { useState } from "react";

import "../../styles/playground.css";

function PlaygroundTabs() {

    const [activeTab, setActiveTab] = useState("tab1");

    return (

        <section className="playground-section" data-testid="playground-tabs-section">

            <h2>Tabs</h2>

            <div className="playground-buttons">

                <button
                    data-testid="tab1-button"
                    onClick={() => setActiveTab("tab1")}
                >
                    Tab 1
                </button>

                <button
                    data-testid="tab2-button"
                    onClick={() => setActiveTab("tab2")}
                >
                    Tab 2
                </button>

                <button
                    data-testid="tab3-button"
                    onClick={() => setActiveTab("tab3")}
                >
                    Tab 3
                </button>

            </div>

            <div
                className="playground-tab-content"
                data-testid="tab-content"
            >

                {activeTab === "tab1" && (
                    <p>Content Tab 1</p>
                )}

                {activeTab === "tab2" && (
                    <p>Content Tab 2</p>
                )}

                {activeTab === "tab3" && (
                    <p>Content Tab 3</p>
                )}

            </div>

        </section>

    );

}

export default PlaygroundTabs;
