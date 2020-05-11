export const FETCH_CATEGORIES_SUCCESS = 'FETCH_CATEGOROIES_SUCCESS';
export const FETCH_CATEGORIES_ERROR = 'FETCH_CATEGORIES_ERROR';
export const FETCH_CATEGORIES_PRELOAD = 'FETCH_CATEGORIES_PRELOAD';

export const fetchCategoriesSuccess = (categories) => ({
    type: FETCH_CATEGORIES_SUCCESS,
    payload: categories,
});

export const fetchCategoriesPreload = () => ({
    type: FETCH_CATEGORIES_PRELOAD,
});

export const fetchCategoriesError = () => ({
    type: FETCH_CATEGORIES_ERROR,
});
