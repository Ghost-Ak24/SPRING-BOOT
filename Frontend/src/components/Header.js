import React from 'react';
import { Link } from "react-router-dom";

function Header() {
  return (
    <nav className="navbar navbar-dark bg-dark">
      <Link to="/employees" className="navbar-brand m-2">Employee Management</Link>
    </nav>
  );
}

export default Header;
