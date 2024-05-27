import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useAuth } from '../../../AuthContext';

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
                <div>
                    <h2>User Profile</h2>
                    {isEditing ? (
                        <div>
                            <label>
                                Login:
                                <input
                                    type="text"
                                    name="login"
                                    value={editedUser.login}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label>
                                Password:
                                <input
                                    type="password"
                                    name="password"
                                    value={editedUser.password}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label>
                                Name:
                                <input
                                    type="text"
                                    name="name"
                                    value={editedUser.name}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <label>
                                Surname:
                                <input
                                    type="text"
                                    name="surname"
                                    value={editedUser.surname}
                                    onChange={handleInputChange}
                                />
                            </label>
                            <button onClick={handleSaveClick}>Save</button>
                        </div>
                    ) : (
                        <div>
                            <p>Login: {user.login}</p>
                            <p>Password: {user.password}</p>
                            <p>Name: {user.name}</p>
                            <p>Surname: {user.surname}</p>
                            <button onClick={handleEditClick}>Edit</button>
                        </div>
                    )}
                </div>
            ) : (

                <p>Loading...</p>
            )}
        </div>
    );
}
