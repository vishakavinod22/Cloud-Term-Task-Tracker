import React, { useState } from 'react';
import logo from './logo.png';
import './App.css';
import {BrowserRouter, Routes, Route} from "react-router-dom";
import Navbar from './components/navbar/Navbar';
import Navbar2 from './components/navbar/Navbar2';
import Signup from './components/signup/Signup';
import Login from './components/login/Login';
import Dashboard from './components/dashboard/Dashboard';
import AddTask from './components/dashboard/AddTask';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={ <div> <Navbar /> <Login /> </div>} />
          <Route path="/signup" element={ <div> <Navbar /> <Signup /> </div>} />
          <Route path="/dashboard" element={<div> <Navbar2 /> <Dashboard /> </div>} />
          <Route path="/addtask" element={<div> <Navbar2 /> <AddTask /> </div>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
