import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import { createBrowserHistory, routerReducer, routerMiddleware as createRouterMiddleware, startListener } from 'redux-first-routing';

import './index.css';
import App from './components/App';
import mainReducer from './reducers/mainReducer';

const history = createBrowserHistory();

const reducers = combineReducers({
    main: mainReducer,
    router: routerReducer
});

const routerMiddleware = createRouterMiddleware(history);
const middleware = applyMiddleware(thunk, routerMiddleware);

const store = createStore(reducers, middleware);

startListener(history, store);

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>
    , document.getElementById('root')
);
