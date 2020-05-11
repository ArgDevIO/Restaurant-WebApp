export const FETCH_PRODUCTS_PRELOAD = 'FETCH_PRODUCTS_PRELOAD';
export const FETCH_PRODUCTS_SUCCESS = 'FETCH_PRODUCTS_SUCCESS';
export const FETCH_PRODUCTS_ERROR = 'FETCH_PRODUCTS_ERROR';
export const ACTIVE_CATEGORY = 'ACTIVE_CATEGORY';

// experimental
export const FETCH_DYNAMIC_PRODUCTS = 'FETCH_DYNAMIC_PRODUCTS';

export const fetchProductsPreload = () => ({ type: FETCH_PRODUCTS_PRELOAD });
export const fetchProductsSuccess = (products) => ({
    type: FETCH_PRODUCTS_SUCCESS,
    payload: products,
});
export const fetchProductsError = () => ({ type: FETCH_PRODUCTS_ERROR });

// experimental
export const fetchDynamicProducts = (categoryType) => ({
    type: FETCH_DYNAMIC_PRODUCTS,
    payload: categoryType,
});

export const setActiveCategory = (activeCategory) => ({
    type: ACTIVE_CATEGORY,
    payload: activeCategory,
});
