import axios from 'axios';
import { API_BASE_URL } from '../utils/constant';

const API_URL = API_BASE_URL + "/message";

// [POST] Send message to Slack channel
export const sendMessage = async (request) => {
    const response = await axios
        .post(API_URL + "/send", {
            name: request.name,
            email: request.email,
            text: request.text
        });

    return response.data;
}
