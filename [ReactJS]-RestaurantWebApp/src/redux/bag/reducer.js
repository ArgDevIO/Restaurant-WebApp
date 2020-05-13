import {
    ADD_PRODUCT_TO_BAG,
    REMOVE_PRODUCT_FROM_BAG,
    INCREASE_PRODUCT_QUANTITY,
    DECREASE_PRODUCT_QUANTITY,
} from './actions';

const initialState = {
    bagProducts: [],
};

const bagReducer = (state = initialState, action) => {
    switch (action.type) {
        case ADD_PRODUCT_TO_BAG:
            return {
                ...state,
                bagProducts: [...state.bagProducts, action.payload],
            };
        case REMOVE_PRODUCT_FROM_BAG:
            return {
                ...state,
                bagProducts: [
                    ...state.bagProducts.slice(0, action.payload),
                    ...state.bagProducts.slice(action.payload + 1),
                ],
            };
        case INCREASE_PRODUCT_QUANTITY:
            const updatedProductsIncrease = state.bagProducts.map((product) => {
                if (product.name === action.payload.name) {
                    product.quantity += 1;
                    product.totalPrice = product.quantity * product.price;
                }
                return product;
            });
            return {
                ...state,
                bagProducts: updatedProductsIncrease,
            };
        case DECREASE_PRODUCT_QUANTITY:
            const updatedProductsDecrease = state.bagProducts.map((product) => {
                if (product.name === action.payload.name) {
                    product.quantity -= 1;
                    product.totalPrice = product.quantity * product.price;
                }
                return product;
            });
            return {
                ...state,
                bagProducts: updatedProductsDecrease,
            };
        default: {
            return state;
        }
    }
};

export const getBagProducts = (state) => state.bag.bagProducts;

export default bagReducer;
