import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import { createLogger } from 'redux-logger';

// root reducer
import { rootReducer } from './rootReducer';

// utils
import notificationMiddleware from '../middleware/notifications';

const middlewares = [thunk, createLogger(), notificationMiddleware];

const composeEnhancer = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

const store = createStore(rootReducer, composeEnhancer(applyMiddleware(...middlewares)));

export default store;
