import axios from 'axios';
import { API_BASE_URL } from '../utils/constant';
import authHeader from '../utils/authHeader';

const API_URL = API_BASE_URL + "/stores";

// [GET] Retreive all stores
export const getAllStores = async () => {
    const response = await axios.get(API_URL, {
        headers: authHeader()
    });

    return response.data;
};

// [DELETE] Delete an existing store
export const deleteSingleStore = async (request) => {
    const response = await axios.delete(API_URL + "/delete/" + request, {
        headers: authHeader()
    });

    return response.data;
};
