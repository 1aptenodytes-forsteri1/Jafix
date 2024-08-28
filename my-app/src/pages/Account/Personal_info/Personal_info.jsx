import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useAuth } from '../../../AuthContext';
import classes from './Personal_info.module.css'

export default function Personal_info() {
    const { userId } = useAuth(); // Предполагая, что useAuth возвращает объект с userId
    const [user, setUser] = useState(null);
    const [editedUser, setEditedUser] = useState(null);
    const [isEditing, setIsEditing] = useState(false);

    useEffect(() => {
        // Получение данных о пользователе при загрузке страницы
        const fetchUserData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/users', {
                    params: { userId }
                });
                console.log('User data fetched:', response.data); // Для отладки
                setUser(response.data);
            } catch (error) {
                console.error('Error fetching user data:', error);
            }
        };

        if (userId) {
            fetchUserData();
        }
    }, [userId]);

    const handleEditClick = () => {
        setEditedUser({ ...user });
        setIsEditing(true);
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setEditedUser(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleSaveClick = async () => {
        try {
            const response = await axios.patch('http://localhost:8080/users', {
                id: userId, 
                ...editedUser
            });


            setUser(response.data);
            setIsEditing(false);
        } catch (error) {
            console.error('Error saving user data:', error);
        }
    };

    return (
        <div>
            {user ? (
                <div className={classes.mainLog}>
                    <h2 className='heading'>User Profile</h2>
                    {isEditing ? (
                        <div className={classes.mainLog}>
                            <label className={classes.inLog}>
                                Login:
                                <input
                                className={classes.intwoLog}
                                    type="text"
                                    name="login"
                                    value={editedUser.login}
                                    onChange={handleInputChange}
                                    
                                />
                            </label >
                            <label className={classes.inLog}>
                                Password:
                                <input
                                className={classes.intwoLog}
                                    type="password"
                                    name="password"
                                    value={editedUser.password}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label className={classes.inLog}>
                                Name:
                                <input
                                className={classes.intwoLog}
                                    type="text"
                                    name="name"
                                    value={editedUser.name}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label className={classes.inLog}>
                                Surname:
                                <input
                                className={classes.intwoLog}
                                    type="text"
                                    name="surname"
                                    value={editedUser.surname}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <button onClick={handleSaveClick} className={classes.butLog}>Save</button>
                        </div>
                    ) : (
                        <div>
                            <p className={classes.intwoLog}>Login: {user.login}</p>
                            <p className={classes.intwoLog}>Password: {user.password}</p>
                            <p className={classes.intwoLog}>Name: {user.name}</p>
                            <p className={classes.intwoLog}>Surname: {user.surname}</p>
                            <button onClick={handleEditClick} className={classes.butLog}>Edit</button>
                        </div>
                    )}
                </div>
            ) : (

                <p>Loading...</p>
            )}
        </div>
    );
}
