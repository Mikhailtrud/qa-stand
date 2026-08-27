import { useEffect, useState } from "react";

import "../../styles/playground.css";

function PlaygroundJavaScript() {

    const [showModal, setShowModal] = useState(false);
    const [toast, setToast] = useState("");
    const [dialogResult, setDialogResult] = useState("");

    function showAlert() {
        alert("Test Alert");
    }

    function showConfirm() {
        const accepted = confirm("Are you sure?");
        setDialogResult(accepted ? "Confirm accepted" : "Confirm dismissed");
    }

    function showPrompt() {
        const value = prompt("Enter your name");
        setDialogResult(value === null ? "Prompt dismissed" : `Prompt accepted: ${value}`);
    }

    function showToast() {
        setToast("Operation completed successfully");
    }

    useEffect(() => {

        if (!toast) {
            return;
        }

        const timer = setTimeout(() => {
            setToast("");
        }, 3000);

        return () => clearTimeout(timer);

    }, [toast]);

    return (

        <section className="playground-section" data-testid="playground-javascript-section">

            <h2>JavaScript</h2>

            <div className="playground-buttons">

                <button
                    data-testid="playground-alert-button"
                    onClick={showAlert}
                >
                    Alert
                </button>

                <button
                    data-testid="playground-confirm-button"
                    onClick={showConfirm}
                >
                    Confirm
                </button>

                <button
                    data-testid="playground-prompt-button"
                    onClick={showPrompt}
                >
                    Prompt
                </button>

                <button
                    data-testid="playground-toast-button"
                    onClick={showToast}
                >
                    Toast
                </button>

                <button
                    data-testid="open-modal-button"
                    onClick={() => setShowModal(true)}
                >
                    Modal
                </button>

            </div>

            {dialogResult && (

                <div data-testid="dialog-result">
                    {dialogResult}
                </div>

            )}

            {toast && (

                <div
                    className="playground-toast"
                    data-testid="toast"
                >
                    {toast}
                </div>

            )}

            {showModal && (

                <div
                    className="playground-modal"
                    data-testid="modal-window"
                >

                    <h3>Modal Window</h3>

                    <p>
                        This modal is used for UI automation testing.
                    </p>

                    <button
                        data-testid="close-modal-button"
                        onClick={() => setShowModal(false)}
                    >
                        Close
                    </button>

                </div>

            )}

        </section>

    );

}

export default PlaygroundJavaScript;
