import React, { Fragment, useEffect, useRef, useState } from "react";
import { FaBars } from "react-icons/fa";
import logo from "../../logo.png";
import { LINKS } from "../../constants";
import "./navbar.css"


function Navbar2() {
  const [showLinks, setShowLinks] = useState(false);
  const linksContainerRef = useRef(null);
  const linksRef = useRef(null);

  useEffect(() => {
    const linksHeight = linksRef.current.getBoundingClientRect().height;
    if (showLinks) {
      linksContainerRef.current.style.height = `${linksHeight}px`;
    } else {
      linksContainerRef.current.style.height = "0px";
    }
  }, [showLinks]);


  return (
    <Fragment>
      <nav>
        <div className="nav-center">
          <div className="nav-header">
            <img src={logo} className="logo" alt="logo" />
            <h2>TaskTrack</h2>
            <button
              className="nav-toggle"
              onClick={() => setShowLinks(!showLinks)}>
              <FaBars />
            </button>
          </div>
          <div className="links-container" ref={linksContainerRef}>
            <ul className="links" ref={linksRef}>
              {LINKS.map((link) => {
                const { id, url, text } = link;
                return (
                  <li key={id}>
                    <a href={url}>{text}</a>
                  </li>
                );
              })}
            </ul>
          </div>
        </div>
      </nav>
    </Fragment>
  );
}

export default Navbar2;
