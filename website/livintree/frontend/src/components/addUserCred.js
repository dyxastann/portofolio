import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import axios from 'axios'
const AddUserCred = () => {
    const [username, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const navigate = useNavigate()
    const saveUserCred = async (e) => {
        e.preventDefault();
        await axios.post('http://localhost:8080/UserCred', {
            username: username,
            password: password
        })
        navigate("/")
    }
    return (
        <div>
            <form onSubmit={ saveUserCred }>
                <div className="field">
                    <label className="label">UserName</label>
                    <input type="text" className="input" value={ username } onChange={ (e) => setUserName(e.target.value) } placeholder="username" />
                </div>
                <div className="field">
                    <label className="label">Password</label>
                    <input type="password" className="input" value={ password } onChange={ (e) => setPassword(e.target.value) } placeholder="password" />
                </div>
                <div className="field">
                    <button className="button is-primary">Save</button>&nbsp;
                    <button className="button is-warning" onClick={() => navigate("/")}>Cancel</button>
                </div>
            </form>
        </div>
    )
};
export default AddUserCred;