import React, {Component} from 'react';
import {saveRestaurant} from '../actions/mainActions';
import {connect} from 'react-redux';

class RestaurantAdd extends Component {
    constructor() {
        super();
        this.state = {
            name: ''
        }
    }

    textChange(e) {
        this.setState({...this.state, name: e.target.value});
    }

    save(name) {
        const restaurant = {
            name: name
        };
        this.props.saveRestaurant(restaurant)
    }

    render() {
        return <div>Add a restaurant
            <input type="text" name="restaurantName" value={this.state.name} onChange={(e) => this.textChange(e)}/>
            <button onClick={() => this.save(this.state.name)}>Save</button>
        </div>
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        saveRestaurant: (restaurant) => dispatch(saveRestaurant(restaurant))
    }
};

export default connect(undefined, mapDispatchToProps)(RestaurantAdd);