import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../Styles/header.css';
import { CgProfile } from 'react-icons/cg';

const Header = () => {
  const navigate = useNavigate();

  return (
    <header className="header">
      <nav className="navbar">
        <span className="navbar-logo" onClick={() => navigate('/')}>
           $ Hani Wealth & Trust
        </span>
        <div className="navbar-links">
          <a onClick={() => navigate('/add-customer')}>Add Customer</a>
          <a onClick={() => navigate('/view-customers')}>View All Customers</a>
          <CgProfile className="cgprofile-icon" />
        </div>
      </nav>
    </header>
  );
};

export default Header;
