import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ListUserCred from './components/listUserCred';
import AddUserCred from './components/addUserCred';
import EditUserCred from './components/editUserCred';
import './App.css';

function App() {
  return (
    <Router>
      <div className="container">
        <Routes>
          <Route exact path="/" element={<ListUserCred />} />
          <Route path="/add" element={<AddUserCred />} />
          <Route path="/edit/:id" element={<EditUserCred />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
