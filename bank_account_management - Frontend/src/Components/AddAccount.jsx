import React, { useState, useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import '../Styles/addCustomer.css';
import { addAccount } from '../APIs/CustomerService';

const AddAccount = () => {
  const [searchParams] = useSearchParams();
  const customerIdFromQuery = searchParams.get('customerId') || '';
  const [form, setForm] = useState({
    customerId: customerIdFromQuery,
    accountType: 'CHECKING',
    balance: '',
    nextCheckNumber: '',
    interestRate: ''
  });
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    if (customerIdFromQuery) {
      setForm(f => ({ ...f, customerId: customerIdFromQuery }));
    }
  }, [customerIdFromQuery]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm({ ...form, [name]: value });
  };

  const handleTypeChange = (e) => {
    setForm({ ...form, accountType: e.target.value, nextCheckNumber: '', interestRate: '' });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    let payload = {
      customerId: Number(form.customerId),
      accountType: form.accountType,
      balance: Number(form.balance),
      ...(form.accountType === 'CHECKING'
        ? { nextCheckNumber: Number(form.nextCheckNumber) }
        : { interestRate: Number(form.interestRate) })
    };
    try {
      await addAccount(payload);
      setSuccess(true);
      setForm({
        customerId: '',
        accountType: 'CHECKING',
        balance: '',
        nextCheckNumber: '',
        interestRate: ''
      });
      setTimeout(() => setSuccess(false), 10000);
    } catch (error) {
      setSuccess(false);
      alert('Error adding account: ' + (error?.response?.data?.message || error.message));
    }
  };

  return (
    <div className="add-customer-form">
      {success && (
        <div className="success-message">
          Account added successfully!
        </div>
      )}
      <h2>Add Account</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Customer ID
          <input name="customerId" value={form.customerId} onChange={handleChange} required type="number" disabled={!!customerIdFromQuery} />
        </label>
        <br />
        <label>
          Account Type
          <select name="accountType" value={form.accountType} onChange={handleTypeChange}>
            <option value="CHECKING">Checking</option>
            <option value="SAVINGS">Savings</option>
          </select>
        </label>
        <br />
        <label>
          Balance
          <input name="balance" value={form.balance} onChange={handleChange} required type="number" step="0.01" />
        </label>
        <br />
        {form.accountType === 'CHECKING' ? (
          <label>
            Next Check Number
            <input
              name="nextCheckNumber"
              value={form.nextCheckNumber}
              onChange={handleChange}
              required
              type="number"
            />
          </label>
        ) : (
          <label>
            Interest Rate (%)
            <input
              name="interestRate"
              value={form.interestRate}
              onChange={handleChange}
              required
              type="number"
              step="0.01"
            />
          </label>
        )}
        <br />
        <button type="submit">Add Account</button>
      </form>
    </div>
  );
};

export default AddAccount;
