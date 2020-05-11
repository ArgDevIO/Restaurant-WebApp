export const ADD_PRODUCT_TO_BAG = 'ADD_PRODUCT_TO_BAG';
export const REMOVE_PRODUCT_FROM_BAG = 'REMOVE_PRODUCT_FROM_BAG';

export const addProductToBag = (product) => ({
    type: ADD_PRODUCT_TO_BAG,
    payload: product,
});

export const removeProductFromBag = (product) => ({
    type: REMOVE_PRODUCT_FROM_BAG,
    payload: product,
});
