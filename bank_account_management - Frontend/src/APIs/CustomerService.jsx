import axios from 'axios';

export const addCustomer = (customerData) => {
  return axios.post('http://localhost:8080/customers', customerData)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
};

export const getAllCustomers = () => {
  return axios.get('http://localhost:8080/customers')
    .then(response => {
      return response.data;
    })
    .catch(error => {
      throw error;
    });
};

export const updateCustomer = (customerId, updateData) => {
  return axios.put(`http://localhost:8080/customers/${customerId}`, updateData);
};

export const getCustomerById = (customerId) => {
  return axios.get(`http://localhost:8080/customers/${customerId}`).then(res => res.data);
};

export const addAccount = (accountData) => {
  return axios.post('http://localhost:8080/accounts', accountData)
    .then(response => response.data)
    .catch(error => { throw error; });
};

export const deleteCustomer = (customerId) => {
  return axios.delete(`http://localhost:8080/customers/${customerId}`)
    .then(response => response.data)
    .catch(error => { throw error; });
};
