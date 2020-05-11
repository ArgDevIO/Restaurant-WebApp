import {
    FETCH_PRODUCTS_ERROR,
    FETCH_PRODUCTS_PRELOAD,
    FETCH_PRODUCTS_SUCCESS,
    ACTIVE_CATEGORY,
} from './actions';

const initialState = {
    preload: false,
    products: [],
    active: '',
};

const productsReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_PRODUCTS_PRELOAD:
            return {
                ...state,
                preload: true,
            };
        case FETCH_PRODUCTS_SUCCESS: {
            return {
                ...state,
                products: action.payload,
            };
        }
        case FETCH_PRODUCTS_ERROR:
            return {
                ...state,
                preload: false,
            };
        case ACTIVE_CATEGORY: {
            return {
                ...state,
                active: action.payload,
            };
        }
        default:
            return {
                ...state,
            };
    }
};

export const getProducts = (state) => state.products.products;
export const getProductsPreload = (state) => state.products.preload;
export const getActiveCategory = (state) => state.products.active;

export default productsReducer;
