import axios from 'axios';
import { notifRequested } from '../../middleware/notifications/actions';
import { fetchAuthUserData } from './fetchAuthUserData';

const url = 'http://localhost:8080/api/users/register';

export const registerAttempt = (props, history) => {
    const {
        fullName,
        email,
        password,
        confirmPassword,
        phone,
        name,
        street,
        number,
        entry,
        appartment,
        interphoneCode,
        city,
        village,
    } = props;
    const registerPostObject = {
        fullName,
        email,
        password,
        confirmPassword,
        phone,
        addresses: [
            {
                name,
                street,
                number,
                entry,
                appartment,
                interphoneCode,
                city,
                village,
            },
        ],
    };

    return async (dispatch) => {
        try {
            await axios.post(url, registerPostObject);
            dispatch(
                notifRequested({
                    title: 'SUCCESS',
                    type: 'success',
                    content: {
                        data: 'User registered successfully',
                    },
                })
            );
            dispatch(fetchAuthUserData(email, password));
            history.push('/menu');
        } catch (e) {
            dispatch(
                notifRequested({
                    title: 'ERROR',
                    type: 'danger',
                    content: e.response,
                })
            );
        }
    };
};
