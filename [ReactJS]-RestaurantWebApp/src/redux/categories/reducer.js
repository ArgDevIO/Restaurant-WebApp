import {
    FETCH_CATEGORIES_SUCCESS,
    FETCH_CATEGORIES_ERROR,
    FETCH_CATEGORIES_PRELOAD,
} from './actions';

const initialState = {
    preload: false,
    categories: [],
};

const categoriesReducer = (state = initialState, action) => {
    switch (action.type) {
        case FETCH_CATEGORIES_SUCCESS:
            return {
                ...state,
                categories: action.payload,
                preload: false,
            };
        case FETCH_CATEGORIES_ERROR: {
            return {
                ...state,
                categories: [],
                preload: false,
            };
        }
        case FETCH_CATEGORIES_PRELOAD: {
            return {
                ...state,
                preload: true,
            };
        }
        default:
            return {
                ...state,
            };
    }
};

export const getCategories = (state) => state.categories.categories;
export const getCategoriesPreload = (state) => state.categories.preload;

export default categoriesReducer;
