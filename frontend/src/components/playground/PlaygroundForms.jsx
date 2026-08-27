import "../../styles/playground.css";

function PlaygroundForms() {

    return (

        <section className="playground-section" data-testid="playground-forms-section">

            <h2>Forms</h2>

            <div className="playground-grid">

                <div className="playground-field">

                    <label>Text</label>

                    <input
                        data-testid="playground-text-input"
                        placeholder="Type something"
                    />

                </div>

                <div className="playground-field">

                    <label>Textarea</label>

                    <textarea
                        rows="4"
                        data-testid="playground-textarea"
                    />

                </div>

                <div className="playground-field">

                    <label>Date</label>

                    <input
                        type="date"
                        data-testid="playground-date"
                    />

                </div>

                <div className="playground-field">

                    <label>Select</label>

                    <select data-testid="playground-select">

                        <option>Option 1</option>
                        <option>Option 2</option>
                        <option>Option 3</option>

                    </select>

                </div>

                <div className="playground-field">

                    <label>Multi Select</label>

                    <select
                        multiple
                        data-testid="playground-multiselect"
                    >

                        <option>Java</option>
                        <option>Kotlin</option>
                        <option>Scala</option>
                        <option>Groovy</option>
                        <option>Python</option>
                        <option>JavaScript</option>
                        <option>TypeScript</option>
                        <option>C#</option>
                        <option>C++</option>
                        <option>Go</option>
                        <option>Rust</option>
                        <option>PHP</option>
                        <option>Ruby</option>
                        <option>Swift</option>
                        <option>Dart</option>
                        <option>SQL</option>
                        <option>Bash</option>
                        <option>Docker</option>
                        <option>Kubernetes</option>
                        <option>Spring Boot</option>

                    </select>

                </div>

                <div className="playground-field">

                    <label>File Upload</label>

                    <input
                        type="file"
                        data-testid="playground-file-upload"
                    />

                </div>

            </div>

            <div className="playground-selection">

                <label>

                    <input
                        type="checkbox"
                        data-testid="playground-checkbox"
                    />

                    Accept Terms

                </label>

                <label>

                    <input
                        type="radio"
                        name="gender"
                        data-testid="radio-male"
                    />

                    Male

                </label>

                <label>

                    <input
                        type="radio"
                        name="gender"
                        data-testid="radio-female"
                    />

                    Female

                </label>

            </div>

        </section>

    );

}

export default PlaygroundForms;
