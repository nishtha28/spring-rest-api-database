import React, {Component} from 'react';
import { connect } from 'react-redux';

import { push as navigate } from 'redux-first-routing';

class RestaurantList extends Component {

    restaurantSelect(r) {
        this.props.navigate(`/restaurants/${r.id}`);
    }

    get restaurants() {
        return this.props.restaurants.map(r => <li onClick={() => this.restaurantSelect(r)} key={r.id}>{r.name}</li>);
    }

    render() {
        return <div>
            <ul>
                {this.restaurants}
            </ul>
            <button onClick={() => this.props.navigate('/restaurants/new')}>Add Restaurant</button>
        </div>
    }
}

const mapStateToProps = (state) => {
    return {
        restaurants: state.main.restaurants
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        navigate: (url) => dispatch(navigate(url))
    }
};

export default connect(mapStateToProps, mapDispatchToProps)(RestaurantList);