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

// [POST] Create a new store
export const createStore = async (request) => {
    const response = await axios.post(API_URL, {
        storeName: request.storeName,
        contactNumber: request.contactNumber,
        openingHours: request.openingHours,
        address: request.address
    }, {
        headers: authHeader()
    });

    return response.data;
};

// [PUT] Update an existing store
export const updateStore = async (storeId, request) => {
    const response = await axios.put(API_URL + "/update/" + storeId, {
        storeName: request.storeName,
        contactNumber: request.contactNumber,
        openingHours: request.openingHours,
        address: request.address
    }, {
        headers: authHeader()
    });

    return response.data;
};

// [DELETE] Delete an existing store
export const deleteSingleStore = async (storeId) => {
    const response = await axios.delete(API_URL + "/delete/" + storeId, {
        headers: authHeader()
    });

    return response.data;
};
