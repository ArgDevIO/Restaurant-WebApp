import axios from 'axios';
import {
    fetchProductsPreload,
    fetchProductsSuccess,
    fetchProductsError,
} from './actions';

export const fetchProductsByCategory = (categoryId) => {
    console.log('CATEGORY ID');
    console.log(categoryId);
    const url = `http://localhost:8080/api/menu/categories/${categoryId}/products`;

    return async (dispatch) => {
        dispatch(fetchProductsPreload());
        try {
            let response = await axios(url);
            const data = response.data;
            console.log('FETCH CATEGORY');
            console.log(data);
            dispatch(fetchProductsSuccess(data));
        } catch (e) {
            dispatch(fetchProductsError());
        }
    };
};
