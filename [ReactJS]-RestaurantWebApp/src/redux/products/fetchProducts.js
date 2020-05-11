import axios from 'axios';
import {
    fetchProductsPreload,
    fetchProductsSuccess,
    fetchProductsError,
} from './actions';

const url = 'http://localhost:8080/api/menu/categories/1/products';
