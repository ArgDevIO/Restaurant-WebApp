import axios from 'axios';
import {
    fetchCategoriesPreload,
    fetchCategoriesSuccess,
    fetchCategoriesError,
} from './actions';
import { fetchProductsByCategory } from '../products/fetchProducts';

const url = 'http://localhost:8080/api/menu/categories';

export const fetchCategories = () => {
    return async (dispatch) => {
        console.log('FETCH CATEGORIES');
        dispatch(fetchCategoriesPreload());
        try {
            let response = await axios(url);
            const data = response.data;
            dispatch(fetchCategoriesSuccess(data));
            // if(active category here)
            dispatch(fetchProductsByCategory(data[0].id));
        } catch (e) {
            dispatch(fetchCategoriesError());
        }
    };
};
