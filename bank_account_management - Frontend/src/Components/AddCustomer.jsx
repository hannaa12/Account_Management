import React, { useReducer, useRef, useEffect, useState } from 'react';
import '../Styles/addCustomer.css';
import { addCustomer } from '../APIs/CustomerService'; 

const initialState = {
  name: '',
  streetNumber: '',
  postalCode: '',
  type: 'PERSON',
  sinNumber: '',
  businessNumber: ''
};

function formReducer(state, action) {
  switch (action.type) {
    case 'CHANGE':
      return { ...state, [action.name]: action.value };
    case 'TYPE_CHANGE':
      return { ...state, type: action.value, sinNumber: '', businessNumber: '' };
    case 'RESET':
      return initialState;
    default:
      return state;
  }
}

const AddCustomer = () => {
  const [form, dispatch] = useReducer(formReducer, initialState);
  const [success, setSuccess] = useState(false);
  const nameInputRef = useRef();

  useEffect(() => {
    if (success) nameInputRef.current?.focus();
  }, [success]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    dispatch({ type: 'CHANGE', name, value });
  };

  const handleTypeChange = (e) => {
    dispatch({ type: 'TYPE_CHANGE', value: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const payload = {
      name: form.name,
      streetNumber: form.streetNumber,
      postalCode: form.postalCode,
      type: form.type,
      ...(form.type === 'PERSON'
        ? { sinNumber: Number(form.sinNumber) }
        : { businessNumber: Number(form.businessNumber) })
    };
    addCustomer(payload)
      .then(() => {
        console.log('Customer added successfully');
        setSuccess(true);
        setTimeout(() => setSuccess(false), 10000);
      })
      .catch((error) => {
        console.error('Error adding customer:', error);
      });
    
    dispatch({ type: 'RESET' });
    
  };

  return (
    <div className="add-customer-form">
      {success && (
        <div className="success-message">
            Customer added successfully!
        </div>
    )}
      <h2 style={{alignItems: 'center'}}>Add Customer</h2>
      <form onSubmit={handleSubmit}>
        <label>
          Name
          <input ref={nameInputRef} name="name" value={form.name} onChange={handleChange} required />
        </label>
        <br />
        <label>
          Street Number
          <input name="streetNumber" value={form.streetNumber} onChange={handleChange} required />
        </label>
        <br />
        <label>
          Postal Code
          <input name="postalCode" value={form.postalCode} onChange={handleChange} required />
        </label>
        <br />
        <label>
          Customer Type
          <select name="type" value={form.type} onChange={handleTypeChange}>
            <option value="PERSON">Person</option>
            <option value="COMPANY">Company</option>
          </select>
        </label>
        <br />
        {form.type === 'PERSON' ? (
          <label>
            SIN Number
            <input
              name="sinNumber"
              value={form.sinNumber}
              onChange={handleChange}
              required
              type="number"
            />
          </label>
        ) : (
          <label>
            Business Number
            <input
              name="businessNumber"
              value={form.businessNumber}
              onChange={handleChange}
              required
              type="number"
            />
          </label>
        )}
        <br />
        <button type="submit">Add Customer</button>
      </form>
    </div>
  );
};

export default AddCustomer;