import React from 'react';
import { Navigate, useLocation } from 'react-router-dom';

// PrivateRoute component for protecting routes
const PrivateRoute = ({ authenticated, children }) => {
	const location = useLocation();

	// Check if the user is authenticated
	if (!authenticated) {
		// Redirect to login if not authenticated
		return (
			<Navigate
				to="/login"
				state={{ from: location }}
				replace
			/>
		);
	} else {
		// Render the component if authenticated
		return children;
	}
};

export default PrivateRoute;
