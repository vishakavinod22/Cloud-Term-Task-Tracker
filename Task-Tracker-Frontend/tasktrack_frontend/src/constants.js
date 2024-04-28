import React, { useState, useEffect } from 'react';
import axios from 'axios'; // Import axios for making HTTP requests

// Navbar constants
const LINKS = [
    // {
    //   id: "1",
    //   url: "/dashboard",
    //   text: "Dashboard"
    // },
    // {
    //   id: "2",
    //   url: "/notes",
    //   text: "Notes"
    // },
    {
      id: "1",
      url: "/logout",
      text: "Logout"
    }
  ];

  // API urls
  // const BASE_API_URL = "http://localhost:8080";
  const BASE_API_URL = "http://3.85.131.27:8080";
  const API_GATEWAY_URL = "https://en11mvsip4.execute-api.us-east-1.amazonaws.com/Build/sendSNS";

  export {LINKS, BASE_API_URL, API_GATEWAY_URL};


  // Table constants
  const headCells = [
    {
      id: 'taskName',
      numeric: false,
      label: 'TASK NAME',
    },
    {
      id: 'description',
      numeric: false,
      label: 'DESCRIPTION',
    },
    {
      id: 'priority',
      numeric: true,
      label: 'PRIORITY',
    },
    {
      id: 'due',
      numeric: false,
      label: 'DUE DATE',
    }
  ];

  function createData(id, task, description, priority, due) {
    return {
      id,
      task,
      description,
      priority,
      due,
    };
  }


//   const rows = [
//     createData(1, 'Task 1', 'Description for Task 1', 3, '2024-04-03', 'In Progress'), // Due on April 3, 2024
//     createData(2, 'Task 2', 'Description for Task 2', 2, '2024-04-05', 'Pending'),     // Due on April 5, 2024
//     createData(3, 'Task 3', 'Description for Task 3', 1, '2024-04-07', 'Completed'),   // Due on April 7, 2024
//     createData(4, 'Task 4', 'Description for Task 4', 2, '2024-04-02', 'In Progress'), // Due on April 2, 2024
//     createData(5, 'Task 5', 'Description for Task 5', 3, '2024-04-04', 'Pending'),     // Due on April 4, 2024
//     createData(6, 'Task 6', 'Description for Task 6', 1, '2024-04-10', 'In Progress'), // Due on April 10, 2024
//     createData(7, 'Task 7', 'Description for Task 7', 2, '2024-04-06', 'Pending'),     // Due on April 6, 2024
//     createData(8, 'Task 8', 'Description for Task 8', 3, '2024-04-08', 'Completed'),   // Due on April 8, 2024
//     createData(9, 'Task 9', 'Description for Task 9', 1, '2024-04-09', 'In Progress'), // Due on April 9, 2024
//     createData(10, 'Task 10', 'Description for Task 10', 2, '2024-04-12', 'Pending'),  // Due on April 12, 2024
// ];

  export {headCells};