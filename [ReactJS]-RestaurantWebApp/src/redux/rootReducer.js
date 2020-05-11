import { combineReducers } from 'redux';
import authReducer from './auth/reducer';
import productsReducer from './products/reducer';
import bagReducer from './bag/reducer';
import categoriesReducer from './categories/reducer';

export default combineReducers({
    auth: authReducer,
    products: productsReducer,
    bag: bagReducer,
    categories: categoriesReducer,
});
