import React from 'react';
import { useLocation } from 'react-router-dom';
import TabTitle from '../common/TabTitle';
import NotFoundImg from '../../assets/images/404.svg';

function NotFound() {
	let location = useLocation();

	return (
		<>
			<TabTitle title="KYN | 404 Not Found" />
			<div style={{ textAlign: 'center', margin: '100px 0' }}>
				<img src={NotFoundImg} alt='404 Not Found' width={360}></img>
				<h2>Resource not found at {location.pathname}</h2>
				<a href="/">Back to Home</a>
			</div>
		</>
	);
}

export default NotFound;
