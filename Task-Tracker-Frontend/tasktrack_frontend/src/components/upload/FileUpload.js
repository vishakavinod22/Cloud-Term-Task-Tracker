import React, { useState, useCallback } from 'react';
import {useDropzone} from 'react-dropzone';
import axios from 'axios';
import {BASE_API_URL} from '../../constants';
import './fileupload.css';

const FileUpload = (props) => {
    const onDrop = useCallback(acceptedFiles => {
        const file = acceptedFiles[0];
        console.log("user: " + props.userId + " | file: " + file);

        const formData = new FormData();
        formData.append("file", file);

        axios.post(
            `${BASE_API_URL}/tasktracker-file-upload/${props.userId}/file/upload`,
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            }
        ).then(() => {
            console.log("file upload API called.");
        }).catch(error => {
            console.log("file not uploaded: " + error);
        });
      }, [])
      const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})
    
      return (
        <div {...getRootProps()} className='drop-box'>
          <input {...getInputProps()} />
          {
            isDragActive ?
              <p>Drop the task files here ...</p> :
              <p>Drag 'n' drop task files here, or click to select files</p>
          }
        </div>
      )

};

export default FileUpload;
