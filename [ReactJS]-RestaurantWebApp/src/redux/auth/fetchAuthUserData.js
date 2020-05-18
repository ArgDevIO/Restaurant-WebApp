import axios from 'axios';
import { fetchDataError, fetchDataPreload, fetchDataSuccess } from './actions';
import { notifRequested } from '../../middleware/notifications/actions';

const url = 'http://localhost:8080/api/users/login';

export const fetchAuthUserData = (email, password) => {
    console.log('ENTER HERE');
    console.log(email, password);
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
            dispatch(
                notifRequested({
                    title: 'SUCCESS',
                    type: 'success',
                    content: {
                        data: 'User logged in successfully',
                    },
                })
            );
            // token here
            localStorage.setItem('token', data.token);
            return response;
        } catch (e) {
            console.log(e.response.data);
            dispatch(
                notifRequested({
                    title: 'ERROR',
                    type: 'danger',
                    content: e.response.data,
                })
            );
            dispatch(fetchDataError());
            // show login error
            return false;
        }
    };
};
