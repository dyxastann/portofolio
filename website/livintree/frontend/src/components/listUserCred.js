import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'
const ListUserCred = () => {
    const [userCred, setUserCred] = useState([])
    useEffect(() => {
        getUserCred();
    }, [])
    const getUserCred = async() => {
        const userCred = await axios.get('http://localhost:8080/UserCred')
        setUserCred(userCred.data)
    }
    const deleteUserCred = async (id) => {
        await axios.delete(`http://localhost:8080/UserCred/${id}`)
        getUserCred()
    }
    return (
        <div>
            <Link to="/add" className="button is-primary mt-5">Add New Item</Link>
            <table className="table is-striped is-fullwidth">
                <thead>
                    <tr>
                        <th width="150">Action</th>
                        <th>No</th>                        
                        <th>userName</th>
                        <th>password</th>                        
                        <th>isVerified</th>                        
                    </tr>
                </thead>
                <tbody>
                    { userCred.map((userCred, index) => (
                        <tr key={userCred.userid}>
                            <td>
                                <Link to={`/edit/${userCred.userid}`} className="button is-small is-info">Edit</Link>
                                &nbsp;
                                <button onClick={() => deleteUserCred(userCred.userid)} className="button is-small is-danger">Delete</button>
                            </td>
                            <td>{index + 1}</td>
                            <td>{userCred.username}</td>
                            <td>{userCred.password}</td>                            
                            <td>{userCred.isVerified}</td>                            
                        </tr>
                    ))
                    }
                </tbody>
            </table>
        </div>
    )
};
export default ListUserCred