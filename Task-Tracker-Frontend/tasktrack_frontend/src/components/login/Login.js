import React, { useState } from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from "react-router-dom";
import {BASE_API_URL} from '../../constants'
import './login.css'; 

let API_URL = BASE_API_URL + "/login";

function LoginForm(props){
  const [formData, setFormData] = useState({
    email: "",
    password: ""
  });

  const navigate = useNavigate();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  function handleFormSubmit(e) {
    e.preventDefault();
    const { email, password} = formData;
    const requestBody = JSON.stringify({
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
        if(data.message === "Success.") {
          navigate("/dashboard", { state: data });
        } else {
          alert(data.message);
        }
      })
      .catch(error => {
        console.log("Error:", error);
      });
};


  const { email, password} = formData;

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
            <h1>Login to your account</h1>
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
              placeholder="Enter your password"
            />
          </div>
          <div>
            <p>Don't have an account? Click <a href="/signup">here</a> to Signup!</p>
          </div>
          <div className="submit">
            <button type="submit">Login to your Account</button>
          </div>
        </form>
      </section>
    </main>
  );
};

export default LoginForm;
