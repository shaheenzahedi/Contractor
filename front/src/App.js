import './App.css';

import Schema from './Schema'

function App() {
    return (
            <EditorComponent
                className='editor--outside'
                object={this.state.object}
                type={this.props.type}
                onUpdateElement={this.change.bind(this)}
                onAddElement={this.add.bind(this)}
                onRemoveElements={this.remove.bind(this)}/>
    );
}

export default App;
