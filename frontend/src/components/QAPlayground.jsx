import { useState } from "react";

function QAPlayground() {

  const [showModal, setShowModal] = useState(false);
  const [activeTab, setActiveTab] = useState("tab1");

  const tableData = [
    { id: 1, name: "John", role: "ADMIN" },
    { id: 2, name: "Kate", role: "USER" },
    { id: 3, name: "Mike", role: "USER" },
    { id: 4, name: "Sara", role: "ADMIN" },
    { id: 5, name: "Tom", role: "USER" }
  ];

  return (
    <div>

      <h2>QA Playground</h2>

      <div>
        <label>Text Input</label>
        <br />
        <input
          data-testid="playground-text-input"
          placeholder="Type something"
        />
      </div>

      <br />

      <div>
        <label>Password</label>
        <br />
        <input
          data-testid="playground-password-input"
          type="password"
        />
      </div>

      <br />

      <div>
        <label>Textarea</label>
        <br />
        <textarea
          data-testid="playground-textarea"
          rows="4"
          cols="50"
        />
      </div>

      <br />

      <div>
        <label>
          <input
            data-testid="playground-checkbox"
            type="checkbox"
          />
          Accept Terms
        </label>
      </div>

      <br />

      <div>
        <label>Radio Buttons</label>
        <br />

        <label>
          <input
            data-testid="radio-male"
            type="radio"
            name="gender"
          />
          Male
        </label>

        <br />

        <label>
          <input
            data-testid="radio-female"
            type="radio"
            name="gender"
          />
          Female
        </label>
      </div>

      <br />

      <div>
        <label>Select</label>
        <br />

        <select data-testid="playground-select">
          <option>Option 1</option>
          <option>Option 2</option>
          <option>Option 3</option>
        </select>
      </div>

      <br />

      <div>
        <label>Multi Select</label>
        <br />

        <select
          multiple
          data-testid="playground-multiselect"
        >
          <option>Java</option>
          <option>Python</option>
          <option>JavaScript</option>
          <option>Kotlin</option>
        </select>
      </div>

      <br />

      <div>
        <label>File Upload</label>
        <br />

        <input
          data-testid="playground-file-upload"
          type="file"
        />
      </div>

      <br />

      <button
        data-testid="playground-alert-button"
        onClick={() => alert("Test Alert")}
      >
        Show Alert
      </button>

      <br />
      <br />

      <button
        data-testid="open-modal-button"
        onClick={() => setShowModal(true)}
      >
        Open Modal
      </button>

      {showModal && (
        <div
          data-testid="modal-window"
          style={{
            border: "1px solid black",
            padding: "20px",
            marginTop: "10px"
          }}
        >
          <h3>Modal Window</h3>

          <button
            data-testid="close-modal-button"
            onClick={() => setShowModal(false)}
          >
            Close Modal
          </button>
        </div>
      )}

      <hr />

      <h3>Tabs</h3>

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

      <div
        data-testid="tab-content"
        style={{
          marginTop: "10px",
          border: "1px solid gray",
          padding: "10px"
        }}
      >
        {activeTab === "tab1" && "Content Tab 1"}
        {activeTab === "tab2" && "Content Tab 2"}
        {activeTab === "tab3" && "Content Tab 3"}
      </div>

      <hr />

      <h3>Dynamic Table</h3>

      <table
        border="1"
        data-testid="dynamic-table"
      >
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
          </tr>
        </thead>

        <tbody>
          {tableData.map((row) => (
            <tr key={row.id}>
              <td>{row.id}</td>
              <td>{row.name}</td>
              <td>{row.role}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <hr />

      <h3>Pagination Demo</h3>

      <button data-testid="page-1">1</button>
      <button data-testid="page-2">2</button>
      <button data-testid="page-3">3</button>

    </div>
  );
}

export default QAPlayground;

