import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Layout, Spin, notification } from 'antd';
import { Content } from 'antd/es/layout/layout';
import { ACCESS_TOKEN } from '../utils/constant';
import AppRouter from '../app/AppRouter';
import Nav from '../common/Nav';
import Footer from '../common/Footer';
import { getCurrentUser } from '../user/userService';
import './App.css';

function App() {
	const [isAuthenticated, setIsAuthenticated] = useState(false);
	const [isLoading, setIsLoading] = useState(true);
	const [currentUser, setCurrentUser] = useState(null);
	const navigate = useNavigate();

	useEffect(() => {
		const fetchCurrentUser = async () => {
			if (!localStorage.getItem(ACCESS_TOKEN)) {
				setIsLoading(false);
			} else {
				await getCurrentUser()
					.then(response => {
						setIsLoading(false);
						setIsAuthenticated(true);
						setCurrentUser(response);
					}).catch(error => {
						// Handle token expired
						if (error.response.status === 401) {
							notification.warning({
								message: 'Session Expired',
								description: 'Please login again',
								placement: 'bottomLeft'
							});
						} else {
							notification.error({
								message: 'Error',
								description: error.message || 'Something went wrong.',
								placement: 'bottomLeft'
							});
						}

						setIsLoading(false);
					});
			}
		}

		fetchCurrentUser();
	}, []);

	const handleLogout = () => {
		localStorage.removeItem(ACCESS_TOKEN);
		setIsAuthenticated(false);
		setCurrentUser(null);
		navigate('/login');
		notification.success({
			message: 'Success',
			description: 'You\'re successfully logged out.',
			placement: 'bottomLeft'
		});
	};

	const handleLogin = () => {
		navigate('/stores');
		notification.success({
			message: 'Success',
			description: "You're successfully logged in.",
			placement: 'bottomLeft'
		});
	};

	if (isLoading) {
		return (
			<Spin 
				size="large" 
				style={
					{
						fontSize: 30, 
						display: 'block', 
						textAlign: 'center', 
						margin: '20% auto'
					}
				}
			/>
		);
	}

	return (
		<Layout>
			<Nav 
				currentUser={currentUser} 
				onLogout={handleLogout}
			/>
			<Content>
				<AppRouter 
					isAuthenticated={isAuthenticated} 
					onLogin={handleLogin}
				/>
			</Content>
			<Footer />
		</Layout>
	);
}

export default App;
