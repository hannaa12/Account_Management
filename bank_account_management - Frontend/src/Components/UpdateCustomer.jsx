import React, { useState, useEffect } from 'react';
import { updateCustomer, getCustomerById, deleteCustomer } from '../APIs/CustomerService';
import { useParams, useNavigate } from 'react-router-dom';
import { FaTrash, FaArrowLeft } from 'react-icons/fa';
import '../Styles/updateCustomer.css'; 

const UpdateCustomer = () => {
  const { customerId } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: '',
    postalCode: '',
    city: '',
    province: ''
  });
  const [success, setSuccess] = useState(false);

  useEffect(() => {
    getCustomerById(customerId).then(data => {
      setForm({
        name: data.name || '',
        postalCode: data.address?.postalCode || '',
        city: data.address?.city || '',
        province: data.address?.province || ''
      });
    });
  }, [customerId]);

  const handleChange = e => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = e => {
    e.preventDefault();
    updateCustomer(customerId, form)
      .then(() => {
        setSuccess(true);
        setTimeout(() => setSuccess(false), 10000);
      })
      .catch(() => alert('Update failed!'));
  };

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete this customer?')) {
      try {
        await deleteCustomer(customerId);
        alert('Customer deleted!');
        navigate('/view-customers');
      } catch (err) {
        alert('Delete failed!');
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className="update-customer-form">
      <FaArrowLeft
        className="back-customer-icon"
        title="Back to Customers"
        onClick={() => navigate('/view-customers')}
      />
      {success && (
        <div className="success-message">
          Customer updated successfully!
        </div>
      )}
      <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
        <h2>Customer Details</h2>
        <FaTrash className="delete-customer-icon" onClick={handleDelete} title="Delete Customer" />
      </div>
      <label>Name</label>
      <input
        name="name"
        value={form.name}
        onChange={handleChange}
      />
      <label>City</label>
      <input
        name="city"
        value={form.city}
        onChange={handleChange}
      />
      <label>Province</label>
      <input
        name="province"
        value={form.province}
        onChange={handleChange}
      />
      <label>Postal Code</label>
      <input
        name="postalCode"
        value={form.postalCode}
        onChange={handleChange}
      />

      <button type="submit" className="update-button">
        Update Customer
      </button>
    </form>
  );
};

export default UpdateCustomer;
