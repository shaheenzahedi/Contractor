import './App.css';

function App() {
    return (
        <>
            <h1>General</h1>
            <label htmlFor='http-method'>Http Method</label>
            <input id='http-method'/><br/>
            <label htmlFor='path'>Path</label>
            <input id='path'/><br/>
            <label htmlFor='status'>Status</label>
            <input id='status'/>
            <h1>Request</h1>
            <label htmlFor='request-header'>Header</label>
            <input id='request-header'/><br/>
            <label htmlFor='request-body'>Body</label>
            <input id='request-body'/><br/>
            <label htmlFor='request-param-rules'>Param Rules</label>
            <input id='request-param-rules'/>
            <h1>Response</h1>
            <label htmlFor='request-header'>Header</label>
            <input id='request-header'/><br/>
            <label htmlFor='request-header-rules'>Header Rules</label>
            <input id='request-header'/><br/>
            <label htmlFor='request-body'>Body</label>
            <input id='request-body'/><br/>
            <label htmlFor='request-body-rules'>Body Rules</label>
            <input id='request-body'/><br/><br/>
            <button>Export</button>
            <button>Import</button>
        </>
    );
}

export default App;
