import './App.css';
import Home from './Components/Home';
import AddCustomer from './Components/AddCustomer';
import ViewCustomers from './Components/ViewCustomer';
import { Routes, Route } from 'react-router-dom';
import Header from './Components/Header';
import UpdateCustomer from './Components/UpdateCustomer';
import AccountList from './Components/AccountList';
import AddAccount from './Components/AddAccount';

function App() {
  return (
    <div className="App">
      <Header></Header>
      <Routes>
          <Route path="/" element={<Home />} /> 
          <Route path="/add-customer" element={<AddCustomer />} />
          <Route path="/view-customers" element={<ViewCustomers />} />
          <Route path="/update-customer/:customerId" element={<UpdateCustomer />} />
          <Route path="/accounts/:customerId" element={<AccountList />} />
          <Route path="/add-account" element={<AddAccount />} />
      </Routes>
    </div>
  );
}

export default App;
