export const ADD_PRODUCT_TO_BAG = 'ADD_PRODUCT_TO_BAG';
export const REMOVE_PRODUCT_FROM_BAG = 'REMOVE_PRODUCT_FROM_BAG';
export const INCREASE_PRODUCT_QUANTITY = 'INCREASE_PRODUCT_QUANTITY';
export const DECREASE_PRODUCT_QUANTITY = 'DECREASE_PRODUCT_QUANTITY';
export const EMPTY_CART = 'EMPTY_CART';

export const addProductToBag = (product) => ({
    type: ADD_PRODUCT_TO_BAG,
    payload: product,
});

export const removeProductFromBag = (product) => ({
    type: REMOVE_PRODUCT_FROM_BAG,
    payload: product,
});

export const increaseProductQuantity = (product) => ({
    type: INCREASE_PRODUCT_QUANTITY,
    payload: product,
});

export const decreaseProductQuantity = (product) => ({
    type: DECREASE_PRODUCT_QUANTITY,
    payload: product,
});

export const emptyCart = () => ({
    type: EMPTY_CART,
});
