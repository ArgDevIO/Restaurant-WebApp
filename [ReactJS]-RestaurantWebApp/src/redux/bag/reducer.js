import { ADD_PRODUCT_TO_BAG, REMOVE_PRODUCT_FROM_BAG } from './actions';

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
        default: {
            return state;
        }
    }
};

export const getBagProducts = (state) => state.bag.bagProducts;

export default bagReducer;
