import PlaygroundForms from "./playground/PlaygroundForms";
import PlaygroundJavaScript from "./playground/PlaygroundJavaScript";
import PlaygroundTabs from "./playground/PlaygroundTabs";
import PlaygroundTables from "./playground/PlaygroundTables";
import PlaygroundDynamic from "./playground/PlaygroundDynamic";
import PlaygroundMouse from "./playground/PlaygroundMouse";

import "../styles/playground.css";

function QAPlayground() {

    return (

        <div className="playground">

            <PlaygroundForms />

            <PlaygroundJavaScript />

            <PlaygroundTabs />

            <PlaygroundTables />

            <PlaygroundDynamic />

            <PlaygroundMouse />

        </div>

    );

}

export default QAPlayground;