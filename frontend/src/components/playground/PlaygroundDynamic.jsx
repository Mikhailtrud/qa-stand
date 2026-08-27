import { useEffect, useState } from "react";

import "../../styles/playground.css";

function PlaygroundDynamic() {

    const [showLoader, setShowLoader] = useState(false);
    const [showHidden, setShowHidden] = useState(false);
    const [showDelayedButton, setShowDelayedButton] = useState(false);

    function startLoading() {

        setShowLoader(true);

        setTimeout(() => {
            setShowLoader(false);
        }, 3000);

    }

    function showButtonLater() {

        setShowDelayedButton(false);

        setTimeout(() => {
            setShowDelayedButton(true);
        }, 3000);

    }

    useEffect(() => {

        const timer = setTimeout(() => {
            setShowHidden(true);
        }, 5000);

        return () => clearTimeout(timer);

    }, []);

    return (

        <section className="playground-section" data-testid="playground-dynamic-section">

            <h2>Dynamic Elements</h2>

            <div className="playground-buttons">

                <button
                    data-testid="start-loader-button"
                    onClick={startLoading}
                >
                    Start Loader
                </button>

                <button
                    data-testid="show-delayed-button"
                    onClick={showButtonLater}
                >
                    Show Delayed Button
                </button>

            </div>

            {showLoader && (

                <div
                    className="playground-loader"
                    data-testid="loader"
                >

                    <div className="loader-spinner"></div>

                    <span>Loading...</span>

                </div>

            )}

            {showDelayedButton && (

                <button
                    className="form-button"
                    data-testid="delayed-button"
                >
                    Delayed Button
                </button>

            )}

            {showHidden && (

                <div
                    className="playground-hidden"
                    data-testid="hidden-element"
                >
                    Hidden element appeared.
                </div>

            )}

        </section>

    );

}

export default PlaygroundDynamic;
