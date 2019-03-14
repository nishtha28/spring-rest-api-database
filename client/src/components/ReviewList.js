import React, {Component} from 'react';
import { connect } from 'react-redux';

import { push as navigate } from 'redux-first-routing';

class ReviewList extends Component {
    get restaurantId() {
        return parseInt(/\/restaurants\/([0-9]*)/.exec(this.props.url)[1], 10);
    }

    get restaurant() {
        return this.props.restaurants.find(r => r.id === this.restaurantId);
    }

    get reviews() {
        if(!this.restaurant) return [];
        return this.restaurant.reviews.map(r => <li key={r.id}>{r.text}</li>);
    }

    render() {
        return <div>
            <ul>
                {this.reviews}
            </ul>
            <button
                onClick={() => this.props.navigate(`/restaurants/${this.restaurantId}/reviews/new`)}
            >Add Review
            </button>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        url: state.router.pathname,
        restaurants: state.main.restaurants
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        navigate: (url) => dispatch(navigate(url))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(ReviewList);
