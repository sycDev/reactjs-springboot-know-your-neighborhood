import { useState, useEffect } from 'react';
import { ACCESS_TOKEN } from '../utils/constant';

function RedirectOAuth() {
    const [hasReloaded, setHasReloaded] = useState(false);

    useEffect(() => {
        // Function to extract query parameters from the URL
        const getUrlParameter = (name) => {
            const urlSearchParams = new URLSearchParams(window.location.search);
            return urlSearchParams.get(name);
        };

        // Get the token from the URL query parameter
        const token = getUrlParameter('token');
        const error = getUrlParameter('error');

        if (token) {
            // Set the token to local storage
            localStorage.setItem(ACCESS_TOKEN, token);
            // Navigate to stores page
            window.history.replaceState(null, '', '/stores');
        } else {
            // Navigate to login page and append the login URL with the error message
            window.history.replaceState(null, '', '/login?error=' + error);
        }

        // Reload the page once after redirected
        if (!hasReloaded) {
            window.location.reload();
            // To prevent infinite reload
            setHasReloaded(true);
        }
    }, [hasReloaded]);

    return null; // Redirecting, no content to render
}

export default RedirectOAuth;
