import { useEffect, useState, useCallback, useMemo } from 'react';
import { getAllCustomers } from '../APIs/CustomerService';
import { FaEdit } from 'react-icons/fa';
import { useNavigate } from 'react-router-dom';
import '../Styles/viewCustomer.css'

const ViewCustomer = () => {
  const [customers, setCustomers] = useState([]);
  const [searchId, setSearchId] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const rowsPerPage = 10;
  const navigate = useNavigate();

  useEffect(() => {
    getAllCustomers()
      .then(data => setCustomers(data))
      .catch(err => console.error(err));
  }, []);

  const handleEditCustomer = useCallback((customerId) => {
  navigate(`/update-customer/${customerId}`);
}, [navigate]);

  const filteredCustomers = useMemo(() =>
  customers.filter(c =>
    searchId === '' ? true : String(c.customerId).includes(searchId)
  ), [customers, searchId]);

  const totalPages = Math.ceil(filteredCustomers.length / rowsPerPage);
  const paginatedCustomers = useMemo(() => {
    const startIdx = (currentPage - 1) * rowsPerPage;
    return filteredCustomers.slice(startIdx, startIdx + rowsPerPage);
  }, [filteredCustomers, currentPage]);

  return (
   <div className="view-customer-container">
  <div className="view-customer-header">
    <input
      type="text"
      placeholder="Search By Customer ID"
      value={searchId}
      onChange={e => setSearchId(e.target.value)}
      className="search-input"
    />
    <button className="search-button">
      <span role="img" aria-label="search">🔍</span>
    </button>
  </div>
  <table className="customer-table">
    <thead>
      <tr>
        <th>Customer ID</th>
        <th>Name</th>
        <th>Customer Type</th>
        <th>Street Number</th>
        <th>City</th>
        <th>Province</th>
        <th>Postal Code</th>
        <th>Accounts</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      {paginatedCustomers.map((c) => (
        <tr key={c.customerId}>
          <td>{c.customerId}</td>
          <td className="customer-name">{c.name}</td>
          <td>{c.businessNumber ? 'company' : 'person'}</td>
          <td>{c.address?.streetNumber || ''}</td>
          <td>{c.address?.city || ''}</td>
          <td>{c.address?.province || ''}</td>
          <td>{c.address?.postalCode || ''}</td>
          <td>
            {c.accounts && c.accounts.length > 0 ? (
              <button
                className="view-accounts-button"
                onClick={() => navigate(`/accounts/${c.customerId}`)}
              >
                View Accounts
              </button>
            ) : (
              <button
                className="view-accounts-button create-account"
                onClick={() => navigate(`/accounts/${c.customerId}`)}
              >
                Create Account
              </button>
            )}
          </td>
          <td>
            <FaEdit className="edit-icon" title="Edit Customer" onClick={() => handleEditCustomer(c.customerId)} style={{ cursor: 'pointer' }} />
          </td>
        </tr>
      ))}
    </tbody>
  </table>
  {totalPages > 1 && (
    <div className="pagination-controls">
      <button
        className="pagination-arrow"
        onClick={() => setCurrentPage(p => Math.max(1, p - 1))}
        disabled={currentPage === 1}
      >
        &#8592;
      </button>
      <span className="pagination-info">Page {currentPage} of {totalPages}</span>
      <button
        className="pagination-arrow"
        onClick={() => setCurrentPage(p => Math.min(totalPages, p + 1))}
        disabled={currentPage === totalPages}
      >
        &#8594;
      </button>
    </div>
  )}
</div>

  );
};

export default ViewCustomer;
