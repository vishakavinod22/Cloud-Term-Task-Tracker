import React, {useEffect} from "react";
import { useLocation, useNavigate } from "react-router-dom";
import Table from './Table';
import FileUpload from '../upload/FileUpload';
import './table.css';

function Dashboard(props) {

    const location = useLocation();
    const data = location.state;
    const [firstName, ...secondName] = data.name.split(" ");
    const id = data.user_id;
    console.log(`id = ${id}`)

    return (
        <div>
            <h3 className="greeting">Hello {firstName},</h3>
            <FileUpload userId={id} />
            <Table />
        </div>
    );
}

export default Dashboard;
