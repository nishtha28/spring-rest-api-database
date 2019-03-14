import {getRestaurants} from "./mainActions";

import {push as navigate} from 'redux-first-routing';

export const reviewAdd = (review) => {
    return async (dispatch) => {
        const req = await fetch(`/api/restaurants/${review.restaurantId}/reviews`, {
            method: 'post',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(review)
        });
        await req.json();
        dispatch(getRestaurants());
        dispatch(navigate(`/restaurants/${review.restaurantId}`));
    }
};
