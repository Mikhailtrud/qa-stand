import { useState } from "react";

import "../../styles/playground.css";

function PlaygroundMouse() {

    const [message, setMessage] = useState("No action");

    return (

        <section className="playground-section" data-testid="playground-mouse-section">

            <h2>Mouse Actions</h2>

            <div className="playground-buttons">

                <button
                    data-testid="hover-button"
                    onMouseEnter={() => setMessage("Hover")}
                >
                    Hover
                </button>

                <button
                    data-testid="double-click-button"
                    onDoubleClick={() => setMessage("Double Click")}
                >
                    Double Click
                </button>

                <button
                    data-testid="right-click-button"
                    onContextMenu={(e) => {

                        e.preventDefault();

                        setMessage("Right Click");

                    }}
                >
                    Right Click
                </button>

            </div>

            <br />

            <div data-testid="mouse-action-result">

                {message}

            </div>

        </section>

    );

}

export default PlaygroundMouse;
