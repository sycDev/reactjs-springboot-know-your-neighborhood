import axios from 'axios';
import { ACCESS_TOKEN, API_BASE_URL } from '../utils/constant';

const API_URL = API_BASE_URL + "/auth";

// [POST] Login user with email or username and password
export const login = async (request) => {
    const response = await axios
        .post(API_URL + "/login", {
            emailOrUsername: request.emailOrUsername,
            password: request.password
        });

    if (response.data.accessToken) {
        localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
    }

    return response.data;
};
