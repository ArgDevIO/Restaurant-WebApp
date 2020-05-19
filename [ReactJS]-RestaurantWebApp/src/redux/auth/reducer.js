import { FETCH_DATA_ERROR, FETCH_DATA_PRELOAD, FETCH_DATA_SUCCESS } from './actions';
import jwt from 'jwt-decode';

export const initialState = {
    preload: false,
    user: '',
    token: [],
    isLoggedIn: false,
    registerPreload: false,
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
            };
        case FETCH_DATA_ERROR:
            return {
                ...state,
                preload: false,
            };
        default:
            return state;
    }
};

export const getUser = (state) => state.auth.user;
export const getToken = (state) => state.auth.token;
export const getPreload = (state) => state.auth.preload;
export const getIsLoggedIn = (state) => state.auth.isLoggedIn;

export default authReducer;
