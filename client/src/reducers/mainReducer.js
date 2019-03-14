const initialState = {
    restaurants: [
    ]
};

const reducer = (state = initialState, action) => {
    switch(action.type) {
        case 'GOT_RESTAURANTS':
            return {...state, restaurants: action.restaurants};
        default:
            return state;
    }
};

export default reducer;