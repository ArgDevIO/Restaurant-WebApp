import axios from 'axios';
import { fetchDataError, fetchDataPreload, fetchDataSuccess } from './actions';

const url = 'http://localhost:8080/api/users/login';

export const fetchAuthUserData = (email, password) => {
    const loginUserCredentials = {
        email,
        password,
    };

    return async (dispatch) => {
        dispatch(fetchDataPreload());
        try {
            let response = await axios.post(url, loginUserCredentials);
            const data = response.data;
            dispatch(fetchDataSuccess(data));
            // token here
            localStorage.setItem('token', data.token);
            return response;
        } catch (e) {
            dispatch(fetchDataError());
            // show login error
        }
    };
};
