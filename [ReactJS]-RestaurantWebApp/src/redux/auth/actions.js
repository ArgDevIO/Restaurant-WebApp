export const FETCH_DATA_PRELOAD = 'FETCH_DATA_PRELOAD';
export const FETCH_DATA_SUCCESS = 'FETCH_DATA_SUCCESS';
export const FETCH_DATA_ERROR = 'FETCH_DATA_ERROR';
export const LOG_OUT_USER = 'LOG_OUT_USER';
export const LOGIN_USER = 'LOGIN_USER';

//experimental
export const LOGIN_TOGGLER = 'LOGIN_TOGGLER';

export const fetchDataPreload = () => ({ type: FETCH_DATA_PRELOAD });
export const fetchDataSuccess = (data) => ({
    type: FETCH_DATA_SUCCESS,
    payload: data,
});
export const fetchDataError = () => ({ type: FETCH_DATA_ERROR });
export const logOutUser = () => {
    localStorage.clear();
    return {
        type: LOG_OUT_USER,
    };
};

//experimental
export const loginToggler = () => ({ type: LOGIN_TOGGLER });
export const loginUser = () => ({ type: LOGIN_USER });
