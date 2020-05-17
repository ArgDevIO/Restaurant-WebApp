import { FETCH_DATA_ERROR, FETCH_DATA_PRELOAD, FETCH_DATA_SUCCESS } from './actions';
import jwt from 'jwt-decode';
import { NOTIFICATION_DISPLAY_REQUEST } from '../../middleware/notifications/actions';

export const initialState = {
    preload: false,
    user: '',
    token: [],
    isLoggedIn: false,
    errorNotification: false,
    errorMessage: '',
};

const authReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_DATA_PRELOAD:
            return {
                ...state,
                preload: true,
            };
        case FETCH_DATA_SUCCESS:
            const jwtDecoded = jwt(action.payload.token);
            return {
                ...state,
                user: jwtDecoded,
                token: action.payload.token,
                preload: false,
                isLoggedIn: true,
                errorNotification: false,
                errorMessage: '',
            };
        case FETCH_DATA_ERROR:
            return {
                ...state,
                preload: false,
            };
        case NOTIFICATION_DISPLAY_REQUEST:
            return {
                ...state,
                errorNotification: true,
                errorMessage: action.payload,
            };
        default:
            return state;
    }
};

export const getUser = (state) => state.auth.user;
export const getToken = (state) => state.auth.token;
export const getPreload = (state) => state.auth.preload;
export const getIsLoggedIn = (state) => state.auth.isLoggedIn;
export const getErrorNotification = (state) => state.auth.errorNotification;
export const getErrorMessage = (state) => state.auth.errorMessage;

export default authReducer;
