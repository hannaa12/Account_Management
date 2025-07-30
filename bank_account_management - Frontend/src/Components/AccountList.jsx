import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getCustomerById } from '../APIs/CustomerService';
import '../Styles/accountList.css';
import { FaPlus, FaArrowLeft } from 'react-icons/fa';

const AccountList = () => {
  const { customerId } = useParams();
  const navigate = useNavigate();
  const [accounts, setAccounts] = useState([]);
  const [customerName, setCustomerName] = useState('');

  useEffect(() => {
    getCustomerById(customerId)
      .then(data => {
        setAccounts(data.accounts || []);
        setCustomerName(data.name || '');
      })
      .catch(err => {
        setAccounts([]);
        setCustomerName('');
      });
  }, [customerId]);

  return (
    <div className="account-list-container">
      <FaArrowLeft
        className="back-account-icon"
        title="Back to Customers"
        onClick={() => navigate('/view-customers')}
      />
      <h2 style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        Accounts for {customerName ? customerName : `Customer #${customerId}`}
        <FaPlus
          className="add-account-icon"
          style={{ cursor: 'pointer', marginLeft: '10px' }}
          onClick={() => navigate(`/add-account?customerId=${customerId}`)}
          title="Add Account"
        />
      </h2>
      {accounts.length === 0 ? (
        <p>No accounts found for this customer.</p>
      ) : (
        <table className="account-list-table">
          <thead>
            <tr>
              <th>Account ID</th>
              <th>Balance</th>
              <th>Type</th>
              <th>Next Check Number</th>
              <th>Interest Rate (%)</th>
            </tr>
          </thead>
          <tbody>
            {accounts.map(acc => (
              <tr key={acc.accountId}>
                <td>{acc.accountId}</td>
                <td>{acc.balance}</td>
                <td>{acc.interestRate ? 'Savings' : 'Checking'}</td>
                <td>{acc.nextCheckNumber || '-'}</td>
                <td>{acc.interestRate || '-'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default AccountList;