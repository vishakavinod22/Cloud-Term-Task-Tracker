import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import {BASE_API_URL} from '../../constants'
import './signup.css'; 

let API_URL = BASE_API_URL + "/insert/user";


const SignUpForm = () => {
  const [formData, setFormData] = useState({
    fullName: "",
    email: "",
    password: ""
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleFormSubmit = (e) => {
    // alert("Button clicked")
    e.preventDefault();
    const { fullName, email, password} = formData;
    const requestBody = JSON.stringify({
      full_name: fullName,
      email: email,
      password: password
    });

    fetch(API_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: requestBody
    })
      .then(response => response.json())
      .then(data => {
        console.log("Success:", data);
        window.location.href = "/";
      })
      .catch(error => {
        console.log("Error:", error);
      });
};


  const { fullName, email, password} = formData;

  return (
    <main>
      <section className="left-pane">
        <div className="feature-item first-child">
          <FontAwesomeIcon icon={faCheck} className="check-background" />
          <h4>Quick and free sign-up</h4>
          <p>Enter your email address to create an account.</p>
        </div>
        <div className="feature-item">
          <FontAwesomeIcon icon={faCheck} className="check-background" />
          <h4>Cross-platform solution</h4>
          <p>Preview your newsletters on any device before sending them out.</p>
        </div>
        <div className="feature-item">
          <FontAwesomeIcon icon={faCheck} className="check-background" />
          <h4>Start sending emails</h4>
          <p>Use our API or pick our pre-built templates.</p>
        </div>
      </section>
      <section className="right-pane">
        <form onSubmit={handleFormSubmit}>
          <div>
            <h1>Create your account</h1>
          </div>
          <div>
            <label htmlFor="fullName">Full Name</label>
            <br />
            <input
              type="text"
              name="fullName"
              value={fullName}
              onChange={handleInputChange}
              placeholder="Enter your full name"
            />
          </div>
          <div>
            <label htmlFor="email">Email</label>
            <br />
            <input
              type="email"
              name="email"
              value={email}
              onChange={handleInputChange}
              placeholder="Enter your email"
            />
          </div>
          <div>
            <label htmlFor="password">Password</label>
            <br />
            <input
              type="password"
              name="password"
              value={password}
              onChange={handleInputChange}
              placeholder="Create a password"
            />
          </div>
          <div className="submit">
            <button type="submit">Create your Account</button>
          </div>
        </form>
      </section>
    </main>
  );
};

export default SignUpForm;
