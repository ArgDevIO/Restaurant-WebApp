import {
    FETCH_PRODUCTS_ERROR,
    FETCH_PRODUCTS_PRELOAD,
    FETCH_PRODUCTS_SUCCESS,
    FETCH_DYNAMIC_PRODUCTS,
    ACTIVE_CATEGORY,
} from './actions';

const mockProductsData = {
    Breakfast: [
        {
            productName: 'Breakfast1',
            description: '2 fried eggs, 2 chicken sausages',
            price: 140,
        },
        {
            productName: 'Breakfast2',
            description:
                '5 fried eggs, 5 chicken sausages, white cheese, 2 cucumber, tomatoes, olives, honey',
            price: 440,
        },
        {
            productName: 'Breakfast3',
            description:
                '2 fried eggs, 2 chicken sausages, white cheese, 2 cucumber,',
            price: 340,
        },
        {
            productName: 'Breakfast4',
            description:
                '5 fried eggs, 5 chicken sausages, white cheese, 2 cucumber, tomatoes, olives, honey',
            price: 540,
        },
        {
            productName: 'Breakfast3',
            description:
                '2 fried eggs, 2 chicken sausages, white cheese, 2 cucumber,',
            price: 340,
        },
        {
            productName: 'Breakfast4',
            description:
                '5 fried eggs, 5 chicken sausages, white cheese, 2 cucumber, tomatoes, olives, honey',
            price: 540,
        },
        {
            productName: 'Breakfast3',
            description:
                '2 fried eggs, 2 chicken sausages, white cheese, 2 cucumber,',
            price: 340,
        },
        {
            productName: 'Breakfast4',
            description:
                '5 fried eggs, 5 chicken sausages, white cheese, 2 cucumber, tomatoes, olives, honey',
            price: 540,
        },
    ],
    Salad: [
        {
            productName: 'Salad1',
            description: 'salad description 1',
            price: 240,
        },
        {
            productName: 'Salad2',
            description: 'salad description 2',
            price: 240,
        },
        {
            productName: 'Salad3',
            description: 'salad description 3',
            price: 440,
        },
    ],
    Meze: [7, 8, 9],
};

const initialState = {
    preload: false,
    products: mockProductsData.Breakfast,
    active: Object.keys(mockProductsData)[0],
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
                products: action.payload.products,
            };
        }
        case FETCH_PRODUCTS_ERROR:
            return {
                ...state,
                preload: false,
            };
        case FETCH_DYNAMIC_PRODUCTS: {
            return {
                ...state,
                products: mockProductsData[action.payload],
            };
        }
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
