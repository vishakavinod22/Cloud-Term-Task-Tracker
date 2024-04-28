import React, { useState } from "react";
import { useNavigate, useLocation } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import {BASE_API_URL, API_GATEWAY_URL} from '../../constants'
import './addtask.css'; 

let API_URL = BASE_API_URL + "/insert/task";


const AddTask = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const userId = location.state ? location.state.userId : -1;

    console.log("User ID:", userId);

    const [formData, setFormData] = useState({
        task: "",
        description: "",
        priority: "",
        dueDate: ""
    });

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const callAPIGateway = () => {
        fetch(API_GATEWAY_URL, {
            mode: 'no-cors',
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
            })
            .then(response => response.json())
            .then(data => {
                console.log("Success:", data);
                //// window.location.reload();
            })
            .catch(error => {
                console.log("Error:", error);
        });
    }

    const handleFormSubmit = (e) => {
        e.preventDefault();
        const { task, description, priority, dueDate} = formData;
        const requestBody = JSON.stringify({
            user_id: userId,
            task: task,
            description: description,
            priority: priority,
            due_date: dueDate,
            status: "Not Started"
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
            alert("Task added.");
            callAPIGateway();
            navigate(-1);
        })
        .catch(error => {
            console.log("Error:", error);
            alert("Task not added.");
        });
    };


    const {task, description, priority, dueDate} = formData;

    return (
        <main className="taskMain">
        <section className="right-pane">
            <form onSubmit={handleFormSubmit}>
            <div>
                <h2>Add Task</h2>
            </div>
            <div>
                <label htmlFor="task">Task</label>
                <br />
                <input
                type="text"
                name="task"
                value={task}
                onChange={handleInputChange}
                placeholder="Enter task name"
                />
            </div>
            <div>
                <label htmlFor="description">Task Description</label>
                <br />
                <textarea
                    name="description"
                    value={description}
                    onChange={handleInputChange}
                    placeholder="Enter Task"
                />
            </div>  
            <div>
                <label htmlFor="priority">Priority</label>
                <br />
                <input
                type="number"
                name="priority"
                value={priority}
                onChange={handleInputChange}
                placeholder="Enter Priority"
                />
            </div>
            <div>
                <label htmlFor="dueDate">Due Date</label>
                <br />
                <input
                type="date"
                name="dueDate"
                value={dueDate}
                onChange={handleInputChange}
                />
            </div>
            <div className="submit">
                <button type="submit">Create Task</button>
            </div>
            </form>
        </section>
        </main>
    );
};

export default AddTask;
