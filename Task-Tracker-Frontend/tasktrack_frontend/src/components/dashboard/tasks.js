import React, { useState, useEffect } from 'react';
import {BASE_API_URL} from '../../constants';
import axios from 'axios'; // Import axios for making HTTP requests

const Tasks = () => {
    const [tasks, setTasks] = useState([]);
    const rows = [];

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(BASE_API_URL+'/dashboard');
                // console.log(response.data);
                const tasks = response.data.tasks;
                // console.log(tasks);
                setTasks(tasks);
            } catch (error) {
                console.error('Error fetching groups:', error);
            }
        };
        fetchData();
    }, []); 

    // for(let i=0; i<tasks.length; i++){
    //     // const rowObj = {
    //     //     task_id: tasks[i].task_id,
    //     //     task: tasks[i].task,
    //     //     description: tasks[i].description,
    //     //     priority: tasks[i].priority,
    //     //     due: tasks[i].due_date,
    //     //     status: tasks[i].status
    //     // };
    //     // rows.push(rowObj);
    //     rows.push(createData(tasks[i].task_id, tasks[i].task, tasks[i].description, tasks[i].priority, tasks[i].due_date, tasks[i].status));
    // }

    // console.log(rows);

    return tasks;


    // return(
    //     <h1>tasks</h1>
    // )
}




// export default rows;
export {Tasks};