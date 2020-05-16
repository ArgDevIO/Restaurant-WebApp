import { combineReducers } from 'redux';
import authReducer from './auth/reducer';
import productsReducer from './products/reducer';
import bagReducer from './bag/reducer';
import categoriesReducer from './categories/reducer';
import { LOG_OUT_USER } from './auth/actions';
import { initialState as bagInitialState } from './bag/reducer';
import { initialState as authInitialState } from './auth/reducer';

export const appReducer = combineReducers({
    auth: authReducer,
    products: productsReducer,
    bag: bagReducer,
    categories: categoriesReducer,
});

export const rootReducer = (state, action) => {
    if (action.type === LOG_OUT_USER) {
        state = {
            auth: authInitialState,
            products: state.products,
            bag: bagInitialState,
            categories: state.categories,
        };
    }
    return appReducer(state, action);
};
