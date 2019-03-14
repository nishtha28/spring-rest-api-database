import React, {Component} from 'react';
import { connect } from 'react-redux';

import ReviewList from './ReviewList';

import { restaurantUpdate } from '../actions/mainActions';
import { restaurantDelete } from '../actions/mainActions';


class RestaurantEdit extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isTextChange: false,
            restaurant: {
                id: undefined,
                name: ''
            }
        }
    }

    static getDerivedStateFromProps(nextProps, prevState) {
        //console.log('in getDerivedStateFromProps...nextProps', nextProps)
        //console.log('in getDerivedStateFromProps...prevState', prevState)

        const restaurantId = parseInt(/\/restaurants\/([0-9]*)/.exec(nextProps.url)[1], 10);
        let restaurant;
        if(prevState.isTextChange && prevState){
            restaurant = prevState.restaurant;
        }else {
            restaurant = nextProps.restaurants.find((r) => r.id === restaurantId);
        }
        if(restaurant === undefined) return prevState;

        //console.log('in getDerivedStateFromProps...return Object:', {...prevState, restaurant})

        return {...prevState, restaurant, isTextChange: false};
    }

    textChange(e) {
        const restaurant = {...this.state.restaurant, [e.target.name]: e.target.value};
        super.setState({...this.state, restaurant, isTextChange: true});
    }

    render() {
        console.log("Tanner says hi!")
        return <div>
        <input type="text" name="name" id='1' key='1' value={this.state.restaurant.name} onChange={(e) => this.textChange(e)}/>
        <button onClick={() => this.props.save(this.state.restaurant)}>Save</button>
        <button onClick={() => this.props.delete(this.state.restaurant)}>Delete</button>
        <ReviewList />
        </div>
    }
}

const mapStateToProps = (state) => {
    console.log('in mapStateToProps...', state)
    return {
        url: state.router.pathname,
        restaurants: state.main.restaurants
    }
};

const mapDispatchToProps = (dispatch) => {
    return {
        save: (restaurant) => dispatch(restaurantUpdate(restaurant)),
        delete: (restaurant) => dispatch(restaurantDelete(restaurant))
}
};

export default connect(mapStateToProps, mapDispatchToProps)(RestaurantEdit);