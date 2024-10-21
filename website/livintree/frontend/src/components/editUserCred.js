/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import axios from 'axios'
const EditUserCred = () => {
    const [username, setUserName] = useState('')
    const [password, setPassword] = useState('')
    const [isVerified, setIsVerified] = useState(0)
    const navigate = useNavigate()
    const { id } = useParams()
    const updateUserCred = async (e) => {
        e.preventDefault();
        await axios.patch(`http://localhost:8080/UserCred/${id}`, {
            password: password,
            isVerified: isVerified
        })
        navigate("/")
    }
    useEffect(() => {
        getUserCredById();
    }, [])
    const getUserCredById = async () => {
        const response = await axios.get(`http://localhost:8080/UserCred/${id}`);
        setUserName(response.data.username)
        setPassword(response.data.password)
        setIsVerified(response.data.isVerified)
    }
    return (
        <div>
            <form onSubmit={ updateUserCred }>
                <div className="field">
                    <label className="label">UserName</label>
                    <input type="text" className="input" disabled value={ username } onChange={ (e) => setUserName(e.target.value) } placeholder="username" />
                </div>
                <div className="field">
                    <label className="label">Password</label>
                    <input type="password" className="input" value={ password } onChange={ (e) => setPassword(e.target.value) } placeholder="password" />
                </div>
                <div className="field">
                    <label className="label">isVerified</label>
                    <input type="text" className="input" value={ isVerified } onChange={ (e) => setIsVerified(e.target.value) } placeholder="isVerified" />
                </div>
                <div className="field">
                    <button type="submit" className="button is-primary">Update</button>&nbsp;
                    <button className="button is-warning" onClick={() => navigate("/")}>Cancel</button>
                </div>
            </form>
        </div>
    )
};
export default EditUserCred;