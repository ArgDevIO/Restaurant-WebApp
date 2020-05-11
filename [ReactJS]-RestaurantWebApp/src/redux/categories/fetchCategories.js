import axios from 'axios';
import {
    fetchCategoriesPreload,
    fetchCategoriesSuccess,
    fetchCategoriesError,
} from './actions';

const url = 'http://localhost:8080/api/menu/categories';

export const fetchCategories = () => {
    return async (dispatch) => {
        console.log('FETCH CATEGORIES');
        dispatch(fetchCategoriesPreload());
        try {
            let response = await axios(url);
            const data = response.data;
            dispatch(fetchCategoriesSuccess(data));
        } catch (e) {
            dispatch(fetchCategoriesError());
        }
    };
};
