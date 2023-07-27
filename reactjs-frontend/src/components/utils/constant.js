export const BASE_URL = 'http://localhost:8080';
export const API_BASE_URL = process.env.REACT_APP_API_BASE_URL || BASE_URL + '/api';
export const REDIRECT_URI = BASE_URL + '/oauth2/redirect';
export const GOOGLE_AUTH_URL = BASE_URL + '/oauth2/authorization/google?redirect_uri=' + REDIRECT_URI;
export const ACCESS_TOKEN = 'accessToken';

export const USERNAME_MIN_LENGTH = 3;
export const USERNAME_MAX_LENGTH = 30;

export const EMAIL_MAX_LENGTH = 255;

export const PASSWORD_MIN_LENGTH = 8;
export const PASSWORD_MAX_LENGTH = 64;
