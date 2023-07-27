import { ACCESS_TOKEN } from './constant';

// Function to generate authorization header for API requests
function authHeader() {
    // Get the access token from local storage
    const token = localStorage.getItem(ACCESS_TOKEN);

    if (token) {
        // If token exists, return the authorization header with bearer token
        return { Authorization: 'Bearer ' + token };
    } else {
        // If token doesn't exist, return an empty object
        return {};
    }
}

export default authHeader;
