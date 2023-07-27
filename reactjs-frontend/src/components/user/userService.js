import axios from 'axios';
import { API_BASE_URL } from '../utils/constant';
import authHeader from '../utils/authHeader';

const API_URL = API_BASE_URL + "/users";

// [GET] Check existence of username
export const checkUsernameExistence = async (request) => {
    const response = await axios.get("/users/checkUsernameExistence?username=" + request);

    return response.data;
};

// [GET] Check existence of email
export const checkEmailExistence = async (request) => {
    const response = await axios.get("/users/checkEmailExistence?email=" + request);

    return response.data;
};

// [GET] Retrieve current user information
export const getCurrentUser = async () => {
    const response = await axios.get(API_URL + "/current", {
        headers: authHeader()
    });

    return response.data;
};

// [GET] Retreive single user profile by id
export const getProfileById = async (request) => {
    const response = await axios.get(API_URL + "/" + request, {
        headers: authHeader()
    });

    return response.data;
};
